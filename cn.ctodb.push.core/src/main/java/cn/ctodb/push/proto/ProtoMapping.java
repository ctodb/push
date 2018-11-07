package cn.ctodb.push.proto;

import cn.ctodb.push.core.Command;

import java.util.concurrent.ConcurrentHashMap;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @date: 2018-11-07 13:58
 * @Copyright: 2018 www.ctodb.cn All rights reserved.
 */
public class ProtoMapping {

    private final static ConcurrentHashMap<Class, Command> classCommandConcurrentHashMap = new ConcurrentHashMap<>();
    private final static ConcurrentHashMap<Command, Class> commandClassConcurrentHashMap = new ConcurrentHashMap<>();

    static {
        add(Command.AUTH_REQ, Auth.AuthReq.class);
        add(Command.AUTH_RESP, Auth.AuthResp.class);
        add(Command.MESSAGE_REQ_TEXT, Message.TextReq.class);
        add(Command.MESSAGE_RESP, Message.MessageResp.class);
    }

    public static Command getCommand(Class cla) {
        return classCommandConcurrentHashMap.get(cla);
    }

    public static Class getClass(Command command) {
        return commandClassConcurrentHashMap.get(command);
    }

    public static void add(Command command, Class cla) {
        commandClassConcurrentHashMap.put(command, cla);
        classCommandConcurrentHashMap.put(cla, command);
    }

}
