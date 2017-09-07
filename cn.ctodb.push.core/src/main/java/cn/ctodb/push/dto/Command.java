package cn.ctodb.push.dto;

public enum Command {
    UNKNOWN(-1),
    HEARTBEAT(1),
    HANDSHAKE_REQ(10),
    HANDSHAKE_RESP(11),
    LOGIN(3),
    LOGOUT(4),
    BIND(5),
    UNBIND(6),
    PUSH(7),
    TEXT_MESSAGE(100);

    Command(int cmd) {
        this.cmd = (byte) cmd;
    }

    public final byte cmd;

    public static Command toCMD(byte b) {
        switch (b) {
            case 1:
                return HEARTBEAT;
            case 10:
                return HANDSHAKE_REQ;
            case 11:
                return HANDSHAKE_RESP;
            case 100:
                return TEXT_MESSAGE;
            default:
                return UNKNOWN;
        }
    }

}
