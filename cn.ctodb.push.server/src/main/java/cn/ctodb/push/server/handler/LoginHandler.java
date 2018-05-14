package cn.ctodb.push.server.handler;

import cn.ctodb.push.core.Connection;
import cn.ctodb.push.dto.LoginReq;
import cn.ctodb.push.dto.Command;
import cn.ctodb.push.handler.AbstractHandler;

/**
 * Created by ohun on 2015/12/22.
 *
 * @author ohun@live.cn
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
