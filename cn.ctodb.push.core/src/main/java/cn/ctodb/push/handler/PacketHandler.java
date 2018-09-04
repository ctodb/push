package cn.ctodb.push.handler;

import cn.ctodb.push.core.Connection;
import cn.ctodb.push.dto.Command;
import cn.ctodb.push.dto.Packet;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @Copyright: 2018 www.ctodb.cn Inc. All rights reserved.
 */
public abstract class PacketHandler {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public abstract Command cmd();

    public abstract void handle(Packet packet, Connection connection);

}
