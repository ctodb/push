package cn.ctodb.push.client.handler;

import cn.ctodb.push.client.Callback;
import cn.ctodb.push.core.Command;
import cn.ctodb.push.core.Connection;
import cn.ctodb.push.handler.PacketHandler;
import cn.ctodb.push.proto.Message;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @date: 2018-11-07 15:36
 * @Copyright: 2018 www.ctodb.cn All rights reserved.
 */
public class MessageResultHandler extends CallbackPacketHandler<Message.MessageResp> {

    public MessageResultHandler(Callback callback) {
        super(callback);
    }

    @Override
    public Command cmd() {
        return Command.MESSAGE_RESP;
    }

    public Class getMessageLiteClass() {
        return Message.MessageResp.class;
    }

}
