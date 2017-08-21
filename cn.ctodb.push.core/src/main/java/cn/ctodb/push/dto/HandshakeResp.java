package cn.ctodb.push.dto;

/**
 * Created by cc on 2017/8/11.
 */
@org.msgpack.annotation.Message
public class HandshakeResp {


    public byte[] serverKey;
    public int heartbeat;
    public String sessionId;
    public long expireTime;

}
