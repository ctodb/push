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

// 服务端消息处理统一入口
@Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private PacketReceiver packetReceiver;

    public ServerHandler(PacketReceiver packetReceiver) {
        this.packetReceiver = packetReceiver;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception { // (2)
//		Channel incoming = ctx.channel();
        channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception { // (3)
//		Channel incoming = ctx.channel();
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
        System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "在线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "掉线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 当出现异常就关闭连接
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "异常");
        ctx.close();
    }
}