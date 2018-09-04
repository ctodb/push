package cn.ctodb.push.handler;

import cn.ctodb.push.core.Connection;
import cn.ctodb.push.dto.Command;
import cn.ctodb.push.dto.Error;
import cn.ctodb.push.dto.HandshakeResp;
import cn.ctodb.push.dto.Packet;

import java.util.Map;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @Copyright: 2018 www.ctodb.cn Inc. All rights reserved.
 */
public final class ErrorHandler extends AbstractHandler<Error> {

    @Override
    public Command cmd() {
        return Command.ERROR;
    }

    @Override
    public Class<Error> getType() {
        return Error.class;
    }

    @Override
    public void handle(Error message, Connection connection) {
        logger.debug("error : {}", message.getErrorCode());
    }

}
