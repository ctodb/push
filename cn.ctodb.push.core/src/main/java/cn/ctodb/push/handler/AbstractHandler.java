package cn.ctodb.push.handler;

import cn.ctodb.push.core.Connection;
import cn.ctodb.push.dto.Message;
import cn.ctodb.push.dto.Packet;
import io.netty.channel.ChannelHandlerContext;
import org.msgpack.MessagePack;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @Copyright: 2018 www.ctodb.cn Inc. All rights reserved.
 */
public abstract class AbstractHandler<T> extends PacketHandler {

    public T decode(Packet packet) {
        try {
            return new MessagePack().read(packet.getBody(), getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract Class<T> getType();

    public abstract void handle(T message, Connection connection);

    public void handle(Packet packet, Connection connection) {
        T t = decode(packet);
        if (t != null) {
            handle(t, connection);
        }
    }
}
