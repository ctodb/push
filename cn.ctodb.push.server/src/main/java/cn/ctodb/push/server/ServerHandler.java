package cn.ctodb.push.server;

import cn.ctodb.push.core.Connection;
import cn.ctodb.push.core.PacketReceiver;
import cn.ctodb.push.dto.Packet;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 服务端消息处理统一入口
@Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {
    private Logger logger = LoggerFactory.getLogger(ServerHandler.class);
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private PacketReceiver packetReceiver;

    public ServerHandler(PacketReceiver packetReceiver) {
        this.packetReceiver = packetReceiver;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) { // (2)
        Channel incoming = ctx.channel();
        logger.debug("handlerAdded : {}", incoming.remoteAddress());
        channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) { // (3)
        Channel incoming = ctx.channel();
        logger.debug("handlerRemoved : {}", incoming.remoteAddress());
        channels.remove(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception { // (4)
        Packet packet = (Packet) msg;
        Connection connection = new Connection();
        connection.setChc(ctx);
        Channel channel = ctx.channel();
        packetReceiver.onReceive(packet, connection);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        Channel incoming = ctx.channel();
        logger.debug("channelActive : {}", incoming.remoteAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
        Channel incoming = ctx.channel();
        logger.debug("channelInactive : {}", incoming.remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 当出现异常就关闭连接
        Channel incoming = ctx.channel();
        logger.error("exceptionCaught : " + incoming.remoteAddress(), cause);
        ctx.close();
    }
}