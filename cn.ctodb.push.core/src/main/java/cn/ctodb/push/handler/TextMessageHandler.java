package cn.ctodb.push.handler;

import cn.ctodb.push.dto.Packet;
import cn.ctodb.push.dto.TextMessage;
import io.netty.channel.ChannelHandlerContext;
import org.msgpack.MessagePack;

import java.io.IOException;

/**
 * Created by ohun on 2015/12/22.
 *
 * @author ohun@live.cn
 */
public final class TextMessageHandler extends MessageHandler<TextMessage> {

    @Override
    public TextMessage decode(Packet packet) {
        try {
            return new MessagePack().read(packet.getBody(), TextMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void handle(TextMessage message, ChannelHandlerContext ctx) {
        logger.debug(message.getContent());
    }
}
