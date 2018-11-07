package cn.ctodb.push.client.handler;

import cn.ctodb.push.core.Command;
import cn.ctodb.push.core.Connection;
import cn.ctodb.push.handler.PacketHandler;
import cn.ctodb.push.proto.Auth;

/**
 * Created by cc on 2017/8/18.
 */
public class AuthHandler extends PacketHandler<Auth.AuthResp> {

    @Override
    public Command cmd() {
        return Command.AUTH_RESP;
    }

    public Class getMessageLiteClass() {
        return Auth.AuthResp.class;
    }

    @Override
    public void handle(Auth.AuthResp packet, Connection connection) {
        logger.debug("登录状态:{}", packet.getStatus());
        if (Auth.AuthResp.Status.OK.equals(packet.getStatus())) {
            logger.debug("登录成功");
        } else {
            logger.debug("登录失败");
        }
    }

}
