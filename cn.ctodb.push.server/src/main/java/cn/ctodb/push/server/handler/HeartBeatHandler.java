package cn.ctodb.push.server.handler;

import cn.ctodb.push.core.Connection;
import cn.ctodb.push.dto.Command;
import cn.ctodb.push.dto.Packet;
import cn.ctodb.push.handler.PacketHandler;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by ohun on 2015/12/22.
 *
 * @author ohun@live.cn
 */
public final class HeartBeatHandler extends PacketHandler {

    @Override
    public Command cmd() {
        return Command.HEARTBEAT;
    }

    @Override
    public void handle(Packet packet, Connection connection) {
        logger.debug("heart : {}", connection.getChc().channel());
        connection.getChc().channel().writeAndFlush(packet);
    }

}
