package cn.ctodb.push.utils;

import org.msgpack.MessagePack;

import cn.ctodb.push.dto.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码工具
 */
@Sharable
public class MsgPackEncode extends MessageToByteEncoder<Packet> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) throws Exception {
		out.writeBytes(new MessagePack().write(packet));
	}

}