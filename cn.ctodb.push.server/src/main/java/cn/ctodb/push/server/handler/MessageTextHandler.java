package cn.ctodb.push.server.handler;

import cn.ctodb.push.core.Command;
import cn.ctodb.push.core.Connection;
import cn.ctodb.push.handler.PacketHandler;
import cn.ctodb.push.proto.Auth;
import cn.ctodb.push.proto.Message;
import cn.ctodb.push.server.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @date: 2018-11-07 16:01
 * @Copyright: 2018 www.ctodb.cn All rights reserved.
 */
@Component
public class MessageTextHandler extends PacketHandler<Message.TextReq> {

    @Override
    public Command cmd() {
        return Command.MESSAGE_REQ_TEXT;
    }

    @Override
    public void handle(Message.TextReq packet, Connection connection) {
        logger.debug("接收到请求：{} {}", cmd(), packet);
        Message.MessageResp messageResp = Message.MessageResp.newBuilder().setId(10000).setCc(packet.getBaseInfo().getCc()).setTs(System.currentTimeMillis()).build();
        connection.send(messageResp);
        logger.debug("反馈");
    }

    @Override
    public Class<Message.TextReq> getMessageLiteClass() {
        return Message.TextReq.class;
    }

}
