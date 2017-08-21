package cn.ctodb.push.client.handler;

import cn.ctodb.push.core.Connection;
import cn.ctodb.push.dto.Command;
import cn.ctodb.push.dto.HandshakeResp;
import cn.ctodb.push.handler.AbstractHandler;

/**
 * Created by cc on 2017/8/18.
 */
public class HandshakeHandler extends AbstractHandler<HandshakeResp> {

    @Override
    public Command cmd() {
        return Command.HANDSHAKE_RESP;
    }

    @Override
    public Class<HandshakeResp> getType() {
        return HandshakeResp.class;
    }

    @Override
    public void handle(HandshakeResp message, Connection connection) {
        logger.debug("握手成功:{}", message.sessionId);
    }
}
