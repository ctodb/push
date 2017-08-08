package cn.ctodb.push.dto;

public enum Command {
    UNKNOWN(-1),
    HEARTBEAT(1),
    LOGIN(2),
    LOGOUT(3),
    BIND(4),
    UNBIND(5),
    PUSH(6),
    TEXT_MESSAGE(10);

    Command(int cmd) {
        this.cmd = (byte) cmd;
    }

    public final byte cmd;

    public static Command toCMD(byte b) {
        Command[] values = values();
        if (b > 0 && b < values.length)
            return values[b - 1];
        return UNKNOWN;
    }
}
