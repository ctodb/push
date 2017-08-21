package cn.ctodb.push.dto;

/**
 * Created by cc on 2017/8/11.
 */
@org.msgpack.annotation.Message
public class HandshakeReq {

    public String deviceId;
    public String osName;
    public String osVersion;
    public String clientVersion;

}
