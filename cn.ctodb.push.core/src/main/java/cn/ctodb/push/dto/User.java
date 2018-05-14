package cn.ctodb.push.dto;

import java.util.Set;

@org.msgpack.annotation.Message
public class User {
    public String id;
    public Set<String> servers;
}
