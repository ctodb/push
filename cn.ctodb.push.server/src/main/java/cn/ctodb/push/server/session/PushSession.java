package cn.ctodb.push.server.session;

import java.net.UnknownHostException;
import java.security.cert.Certificate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class PushSession implements Session {

    private String osName;
    private String osVersion;
    private String clientVersion;
    private String deviceId;
    private String userId;
    private String tags;
    private int heartbeat = 10000;// 10s

    private String sessionId;
    private long expireTime;

    private final Map<String, Object> sessionData = new HashMap<>();
    private int status;
    private long startDate = System.currentTimeMillis();
    private long lastActiveDate;
    private long clientPacketCount = 0;
    private long serverPacketCount = 0;

    @Override
    public String getId() {
        return getSessionId();
    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public Date getCreationDate() {
        return null;
    }

    @Override
    public Date getLastActiveDate() {
        return null;
    }

    @Override
    public long getNumClientPackets() {
        return 0;
    }

    @Override
    public long getNumServerPackets() {
        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public Certificate[] getPeerCertificates() {
        return new Certificate[0];
    }

    @Override
    public String getHostAddress() throws UnknownHostException {
        return null;
    }

    @Override
    public String getHostName() throws UnknownHostException {
        return null;
    }

    @Override
    public boolean validate() {
        return false;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }
}
