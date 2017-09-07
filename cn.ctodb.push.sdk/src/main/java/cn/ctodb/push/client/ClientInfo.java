package cn.ctodb.push.client;

public class ClientInfo {
    public static String sessionId;

    public static String getSessionId() {
        return sessionId;
    }

    public static void setSessionId(String sessionId) {
        ClientInfo.sessionId = sessionId;
    }
}
