package cn.ctodb.push.server.handler;

import cn.ctodb.push.core.Connection;
import cn.ctodb.push.dto.Command;
import cn.ctodb.push.dto.HandshakeReq;
import cn.ctodb.push.dto.HandshakeResp;
import cn.ctodb.push.dto.Packet;
import cn.ctodb.push.handler.AbstractHandler;
import cn.ctodb.push.server.session.PushSession;
import cn.ctodb.push.server.session.SessionManager;
import cn.ctodb.push.server.util.SessionUtil;
import org.msgpack.MessagePack;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by cc on 2017/8/18.
 */
public class HandshakeHandler extends AbstractHandler<HandshakeReq> {

    @Autowired
    private SessionManager sessionManager;
    @Autowired
    private MessagePack messagePack;

    @Override
    public Command cmd() {
        return Command.HANDSHAKE_REQ;
    }

    @Override
    public Class<HandshakeReq> getType() {
        return HandshakeReq.class;
    }

    @Override
    public void handle(HandshakeReq message, Connection connection) {
        logger.debug("接收到新的握手请求:{}", message.deviceId);
        PushSession session = new PushSession();
        session.setSessionId(SessionUtil.get());
        session.setDeviceId(message.deviceId);
        session.setOsName(message.osName);
        session.setClientVersion(message.clientVersion);
        session.setOsVersion(message.osVersion);
        sessionManager.on(session);
        Packet packet = new Packet(Command.HANDSHAKE_RESP);
        HandshakeResp handshakeResp = new HandshakeResp();
        handshakeResp.sessionId = session.getId();
        try {
            packet.setBody(messagePack.write(handshakeResp));
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection.send(packet);
    }
}
