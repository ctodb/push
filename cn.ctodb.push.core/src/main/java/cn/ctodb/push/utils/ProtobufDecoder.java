package cn.ctodb.push.utils;

import cn.ctodb.push.core.Command;
import cn.ctodb.push.proto.ProtoMapping;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.List;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @Copyright: 2018 www.ctodb.cn Inc. All rights reserved.
 */
@Sharable
public class ProtobufDecoder extends MessageToMessageDecoder<ByteBuf> {

    private Logger logger = LoggerFactory.getLogger(ProtobufDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        logger.debug("---------------------- 接收到新消息");
        byte b = msg.readByte();
        Class cla = ProtoMapping.getClass(Command.toCMD(b));
        ByteBufInputStream bufInputStream = new ByteBufInputStream(msg);
        MessageLite messageLite = (MessageLite) cla.getMethod("parseFrom", InputStream.class).invoke(null, bufInputStream);
        out.add(messageLite);
    }
}