package cn.ctodb.push.server.handler;

import cn.ctodb.push.core.Connection;
import cn.ctodb.push.dto.Command;
import cn.ctodb.push.dto.TextMessage;
import cn.ctodb.push.handler.AbstractHandler;

/**
 * Created by ohun on 2015/12/22.
 *
 * @author ohun@live.cn
 */
public final class TextMessageHandler extends AbstractHandler<TextMessage> {

    @Override
    public Class<TextMessage> getType() {
        return TextMessage.class;
    }

    @Override
    public void handle(TextMessage message, Connection connection) {
        logger.debug(message.getContent());
    }

    @Override
    public Command cmd() {
        return Command.TEXT_MESSAGE;
    }
}
