package cn.ctodb.push.core;

import cn.ctodb.push.dto.Packet;

public interface Filter {

    FilterResult exec(Packet packet, Connection connection);

}
