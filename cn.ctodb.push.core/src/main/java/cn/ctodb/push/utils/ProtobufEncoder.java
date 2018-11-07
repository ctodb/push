package cn.ctodb.push.utils;

import cn.ctodb.push.proto.ProtoMapping;
import com.google.protobuf.MessageLite;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @Copyright: 2018 www.ctodb.cn Inc. All rights reserved.
 */
@Sharable
public class ProtobufEncoder extends MessageToByteEncoder<MessageLite> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void encode(ChannelHandlerContext ctx, MessageLite msg, ByteBuf out) {
        try {
            logger.debug(msg.toString());
            byte[] body = msg.toByteArray();
            byte header = getType(msg);
            out.writeByte(header);
            out.writeBytes(body);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private byte getType(MessageLite msg) {
        return ProtoMapping.getCommand(msg.getClass()).cmd;
    }

}