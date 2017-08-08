package cn.ctodb.push.utils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;
import org.msgpack.MessagePack;

import cn.ctodb.push.dto.Packet;

/**
 * 解码工具
 */
@Sharable
public class MsgPackDecode extends MessageToMessageDecoder<ByteBuf> {
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
		final int length = msg.readableBytes();
		final byte[] array = new byte[length];
		msg.getBytes(msg.readerIndex(), array, 0, length);
		out.add(new MessagePack().read(array, Packet.class));
	}
}