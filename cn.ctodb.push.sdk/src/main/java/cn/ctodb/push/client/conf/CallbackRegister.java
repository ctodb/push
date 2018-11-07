package cn.ctodb.push.client.conf;

import cn.ctodb.push.client.Callback;
import cn.ctodb.push.client.handler.MessageResultHandler;
import cn.ctodb.push.proto.Message;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @date: 2018-11-07 15:48
 * @Copyright: 2018 www.ctodb.cn All rights reserved.
 */
public class CallbackRegister {

    public static void MessageResp(Callback<Message.MessageResp> callback) {
        new MessageResultHandler(callback);
    }

}
