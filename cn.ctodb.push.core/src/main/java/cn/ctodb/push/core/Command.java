package cn.ctodb.push.core;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @Copyright: 2018 www.ctodb.cn Inc. All rights reserved.
 */
public enum Command {
    AUTH_REQ(1),
    AUTH_RESP(2),
    MESSAGE_RESP(3),
    MESSAGE_REQ_TEXT(4),
    UNKNOWN(-1);

    Command(int cmd) {
        this.cmd = (byte) cmd;
    }

    public final byte cmd;

    public static Command toCMD(byte b) {
        for (Command command : values()) {
            if (command.cmd == b) {
                return command;
            }
        }
        return UNKNOWN;
    }

}
