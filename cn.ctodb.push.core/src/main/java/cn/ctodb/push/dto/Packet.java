package cn.ctodb.push.dto;

import org.msgpack.annotation.Message;

@Message
public class Packet {

    private byte cmd; // 命令
    private short cc; // 校验码 暂时没有用到
    private byte flags; // 特性，如是否加密，是否压缩等
    private int sessionId; // 会话id。客户端生成。
    private byte lrc; // 校验，纵向冗余校验。只校验head
    private byte[] body;


    public Packet(byte cmd) {
        this.cmd = cmd;
    }

    public Packet() {
    }

    public Packet(byte cmd, int sessionId) {
        this.cmd = cmd;
        this.sessionId = sessionId;
    }

    public Packet(Command cmd) {
        this.cmd = cmd.cmd;
    }

    public Packet(Command cmd, int sessionId) {
        this.cmd = cmd.cmd;
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "{" + "cmd=" + cmd + ", cc=" + cc + ", flags=" + flags + ", sessionId=" + sessionId + ", lrc=" + lrc
                + ", body=" + (body == null ? 0 : body.length) + '}';
    }

    public byte getCmd() {
        return cmd;
    }

    public void setCmd(byte cmd) {
        this.cmd = cmd;
    }

    public short getCc() {
        return cc;
    }

    public void setCc(short cc) {
        this.cc = cc;
    }

    public byte getFlags() {
        return flags;
    }

    public void setFlags(byte flags) {
        this.flags = flags;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public byte getLrc() {
        return lrc;
    }

    public void setLrc(byte lrc) {
        this.lrc = lrc;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
