package cn.ctodb.push.handler;

import cn.ctodb.push.dto.Packet;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ohun on 2015/12/22.
 *
 * @author ohun@live.cn
 */
public abstract class PacketHandler {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public abstract void handle(Packet packet, ChannelHandlerContext ctx);
}
