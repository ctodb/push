package cn.ctodb.push.handler;

import cn.ctodb.push.dto.Message;
import cn.ctodb.push.dto.Packet;
import io.netty.channel.ChannelHandlerContext;
import org.msgpack.MessagePack;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

/**
 * Created by cc on 2017/7/7.
 */
public abstract class AbstractHandler<T> extends PacketHandler {

    public T decode(Packet packet) {
        try {
            logger.info((T) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0] + "");
            return new MessagePack().read(packet.getBody(), (T) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public abstract void handle(T message, ChannelHandlerContext ctx);

    public void handle(Packet packet, ChannelHandlerContext ctx) {
        T t = decode(packet);
        if (t != null) {
            handle(t, ctx);
        }
    }
}
