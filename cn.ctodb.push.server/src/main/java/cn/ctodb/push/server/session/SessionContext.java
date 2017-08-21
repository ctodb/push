package cn.ctodb.push.server.session;


public final class SessionContext {
    public String osName;
    public String osVersion;
    public String clientVersion;
    public String deviceId;
    public String userId;
    public String tags;
    public int heartbeat = 10000;// 10s
    private byte clientType;

    public SessionContext setOsName(String osName) {
        this.osName = osName;
        return this;
    }

    public SessionContext setOsVersion(String osVersion) {
        this.osVersion = osVersion;
        return this;
    }

    public SessionContext setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
        return this;
    }

    public SessionContext setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public SessionContext setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setHeartbeat(int heartbeat) {
        this.heartbeat = heartbeat;
    }

    public boolean handshakeOk() {
        return deviceId != null && deviceId.length() > 0;
    }

    public int getClientType() {
        if (clientType == 0) {
//            clientType = (byte) ClientClassifier.I.getClientType(osName);
        }
        return clientType;
    }

    @Override
    public String toString() {
        return "{" +
                "osName='" + osName + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", userId='" + userId + '\'' +
                ", tags='" + tags + '\'' +
                ", heartbeat=" + heartbeat +
                '}';
    }
}
