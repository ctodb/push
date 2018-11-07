package cn.ctodb.push.client.handler;

import cn.ctodb.push.client.Callback;
import cn.ctodb.push.core.Connection;
import cn.ctodb.push.handler.PacketHandler;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @date: 2018-11-07 15:41
 * @Copyright: 2018 www.ctodb.cn All rights reserved.
 */
public abstract class CallbackPacketHandler<T> extends PacketHandler<T> {

    private Callback<T> callback;

    public CallbackPacketHandler(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void handle(T packet, Connection connection) {
        callback.exec(packet, connection);
    }
}
