package cn.ctodb.push.handler;

import cn.ctodb.push.dto.Command;
import cn.ctodb.push.dto.TextMessage;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by ohun on 2015/12/22.
 *
 * @author ohun@live.cn
 */
public final class TextMessageHandler extends AbstractHandler<TextMessage> {

    @Override
    public void handle(TextMessage message, ChannelHandlerContext ctx) {
        logger.debug(message.getContent());
    }

    @Override
    public Command cmd() {
        return Command.TEXT_MESSAGE;
    }
}
