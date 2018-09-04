package cn.ctodb.push.server.handler;

import cn.ctodb.push.core.Connection;
import cn.ctodb.push.dto.Command;
import cn.ctodb.push.dto.TextMessage;
import cn.ctodb.push.handler.AbstractHandler;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @Copyright: 2018 www.ctodb.cn Inc. All rights reserved.
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
