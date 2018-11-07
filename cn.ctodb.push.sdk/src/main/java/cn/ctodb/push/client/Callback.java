package cn.ctodb.push.client;

import cn.ctodb.push.core.Connection;
import cn.ctodb.push.proto.Message;
import com.google.protobuf.MessageLite;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @date: 2018-11-07 15:39
 * @Copyright: 2018 www.ctodb.cn All rights reserved.
 */
public interface Callback<T> {

    void exec(T packet, Connection connection);

}
