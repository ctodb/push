package cn.ctodb.push.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.ctodb.push.dto.Command;
import cn.ctodb.push.dto.Packet;
import cn.ctodb.push.handler.PacketHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ohun on 2015/12/22.
 *
 * @author ohun@live.cn
 */
public final class PacketReceiver {

    private static final Logger logger = LoggerFactory.getLogger(PacketReceiver.class);
    private static List<Filter> filterList = new ArrayList<>();
    private static final Map<Command, PacketHandler> handlers = new HashMap<>();

    public void addHandler(PacketHandler handler) {
        logger.debug("register : {}", handler.cmd().name());
        handlers.put(handler.cmd(), handler);
    }

    public void addFilter(Filter filter) {
        logger.debug("filter : {}", filter.getClass());
        filterList.add(filter);
    }

    public void onReceive(Packet packet, Connection connection) {
        Command command = Command.toCMD(packet.getCmd());
        PacketHandler handler = handlers.get(command);
        logger.debug("new packet : {}", command);
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
