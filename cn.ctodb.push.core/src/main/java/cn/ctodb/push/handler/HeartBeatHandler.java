package cn.ctodb.push.handler;

import cn.ctodb.push.dto.Packet;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by ohun on 2015/12/22.
 *
 * @author ohun@live.cn
 */
public final class HeartBeatHandler extends PacketHandler {

    @Override
    public void handle(Packet packet, ChannelHandlerContext ctx) {
        logger.debug("heart : {}", ctx.channel());
        ctx.channel().writeAndFlush(packet);
    }

}
