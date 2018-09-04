package cn.ctodb.push.server.handler;

import cn.ctodb.push.core.Connection;
import cn.ctodb.push.dto.LoginReq;
import cn.ctodb.push.dto.Command;
import cn.ctodb.push.handler.AbstractHandler;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @Copyright: 2018 www.ctodb.cn Inc. All rights reserved.
 */
public final class LoginHandler extends AbstractHandler<LoginReq> {

    @Override
    public Class<LoginReq> getType() {
        return LoginReq.class;
    }

    @Override
    public void handle(LoginReq loginReq, Connection connection) {
        logger.debug("login : ");
    }

    @Override
    public Command cmd() {
        return Command.LOGIN;
    }
}
