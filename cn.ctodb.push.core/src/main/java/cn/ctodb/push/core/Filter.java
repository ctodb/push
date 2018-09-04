package cn.ctodb.push.core;

import cn.ctodb.push.dto.Packet;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @Copyright: 2018 www.ctodb.cn Inc. All rights reserved.
 */
public interface Filter {

    FilterResult exec(Packet packet, Connection connection);

}
