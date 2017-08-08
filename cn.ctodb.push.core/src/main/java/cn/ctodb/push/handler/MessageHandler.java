package cn.ctodb.push.handler;

import cn.ctodb.push.dto.Message;
import cn.ctodb.push.dto.Packet;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by cc on 2017/7/7.
 */
public abstract class MessageHandler<T extends Message> extends PacketHandler {

    public abstract T decode(Packet packet);

    public abstract void handle(T message, ChannelHandlerContext ctx);

    public void handle(Packet packet, ChannelHandlerContext ctx) {
        T t = decode(packet);
        if (t != null) {
            handle(t, ctx);
        }
    }
}
