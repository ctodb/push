package cn.ctodb.callback;

import cn.ctodb.push.client.Callback;
import cn.ctodb.push.core.Connection;
import cn.ctodb.push.proto.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @date: 2018-11-07 15:52
 * @Copyright: 2018 www.ctodb.cn All rights reserved.
 */
public class MessageRespCallback implements Callback<Message.MessageResp> {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void exec(Message.MessageResp packet, Connection connection) {
        logger.debug("收到 ： MessageRespCallback");
    }
}
