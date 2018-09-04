package cn.ctodb.push.dto;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @Copyright: 2018 www.ctodb.cn Inc. All rights reserved.
 */
@org.msgpack.annotation.Message
public class HandshakeResp {

    public byte[] serverKey;
    public int heartbeat;
    public String sessionId;
    public long expireTime;

}
