package cn.ctodb.push.handler;

import cn.ctodb.push.core.Connection;
import cn.ctodb.push.core.Command;
import cn.ctodb.push.proto.ProtoMapping;
import com.google.protobuf.MessageLite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @Copyright: 2018 www.ctodb.cn Inc. All rights reserved.
 */
public abstract class PacketHandler<T> {

    private final static ConcurrentHashMap<Command, PacketHandler> handlers = new ConcurrentHashMap<>();

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public abstract Command cmd();

    public abstract void handle(T packet, Connection connection);

    public abstract Class<T> getMessageLiteClass();

    public PacketHandler() {
        handlers.put(cmd(), this);
    }

    public static PacketHandler getHandler(Object t) {
        return handlers.get(ProtoMapping.getCommand(t.getClass()));
    }
}
