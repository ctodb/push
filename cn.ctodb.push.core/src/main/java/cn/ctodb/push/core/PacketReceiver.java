package cn.ctodb.push.core;

import cn.ctodb.push.handler.PacketHandler;
import cn.ctodb.push.proto.ProtoMapping;
import com.google.protobuf.MessageLite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @Copyright: 2018 www.ctodb.cn Inc. All rights reserved.
 */
public final class PacketReceiver {

    private static final Logger logger = LoggerFactory.getLogger(PacketReceiver.class);
    private static List<Filter> filterList = new ArrayList<>();

    public void addFilter(Filter filter) {
        logger.debug("filter : {}", filter.getClass());
        filterList.add(filter);
    }

    public void onReceive(MessageLite packet, Connection connection) {
        Command cmd = ProtoMapping.getCommand(packet.getClass());
        PacketHandler handler = PacketHandler.getHandler(packet);
        logger.debug("new packet : {}", cmd);
        if (handler == null) return;
        for (Filter filter : filterList) {
            if (filter.exec(packet, connection).equals(FilterResult.END)) return;
        }
        try {
            handler.handle(packet, connection);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
