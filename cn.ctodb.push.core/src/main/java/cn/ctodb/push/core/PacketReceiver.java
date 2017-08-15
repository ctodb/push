package cn.ctodb.push.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.ctodb.push.dto.Command;
import cn.ctodb.push.dto.Packet;
import cn.ctodb.push.handler.PacketHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ohun on 2015/12/22.
 *
 * @author ohun@live.cn
 */
public final class PacketReceiver {

    private static final Logger logger = LoggerFactory.getLogger(PacketReceiver.class);
    private final Map<Byte, PacketHandler> handlers = new HashMap<>();

    public void register(PacketHandler handler) {
        logger.debug("register : {}", handler.cmd().name());
        handlers.put(handler.cmd().cmd, handler);
    }

    public void onReceive(Packet packet, ChannelHandlerContext ctx) {
        PacketHandler handler = handlers.get(packet.getCmd());
        if (handler != null) {
            try {
                handler.handle(packet, ctx);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
}
