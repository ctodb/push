package cn.ctodb.push.core;

import cn.ctodb.push.dto.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

// 服务端消息处理统一入口
public class BaseHandlerAdapter extends ChannelInboundHandlerAdapter {

	private PacketReceiver packetReceiver;

	public BaseHandlerAdapter(PacketReceiver packetReceiver) {
		this.packetReceiver = packetReceiver;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception { // (4)
		Packet packet = (Packet) msg;
		packetReceiver.onReceive(packet, ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (7)
		ctx.close();
	}
}