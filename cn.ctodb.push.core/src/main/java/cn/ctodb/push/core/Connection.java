package cn.ctodb.push.core;

import cn.ctodb.push.dto.Packet;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by cc on 2017/8/18.
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
