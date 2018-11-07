package cn.ctodb.push.server.handler;

import cn.ctodb.push.core.Command;
import cn.ctodb.push.core.Connection;
import cn.ctodb.push.handler.PacketHandler;
import cn.ctodb.push.proto.Auth;
import cn.ctodb.push.server.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @Copyright: 2018 www.ctodb.cn Inc. All rights reserved.
 */
@Component
public final class AuthHandler extends PacketHandler<Auth.AuthReq> {

    @Autowired
    private AuthService authService;

    @Override
    public Command cmd() {
        return Command.AUTH_REQ;
    }

    @Override
    public void handle(Auth.AuthReq packet, Connection connection) {
        logger.debug("接收到登录请求：{} {}", packet.getUid(), packet.getToken());
        if (authService.check(packet.getUid(), packet.getToken())) {
            Auth.AuthResp authResp = Auth.AuthResp.newBuilder().setStatus(Auth.AuthResp.Status.OK).build();
            connection.send(authResp);
            logger.debug("反馈登录成功");
        } else {
            Auth.AuthResp authResp = Auth.AuthResp.newBuilder().setStatus(Auth.AuthResp.Status.ERROR).build();
            connection.send(authResp);
            logger.debug("反馈登录失败");
        }
    }

    @Override
    public Class<Auth.AuthReq> getMessageLiteClass() {
        return Auth.AuthReq.class;
    }

}
