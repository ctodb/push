package cn.ctodb.push.core;

import cn.ctodb.push.dto.Packet;
import io.netty.channel.ChannelHandlerContext;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @Copyright: 2018 www.ctodb.cn Inc. All rights reserved.
 */
public class Connection {

    private ChannelHandlerContext chc;

    public void send(Packet packet) {
        chc.channel().writeAndFlush(packet);
    }

    public ChannelHandlerContext getChc() {
        return chc;
    }

    public void setChc(ChannelHandlerContext chc) {
        this.chc = chc;
    }
}
