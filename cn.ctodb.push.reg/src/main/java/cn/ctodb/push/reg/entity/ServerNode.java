package cn.ctodb.push.reg.entity;

public class ServerNode {

    private String id;
    private String url;
    private long lastCheckTime;
    private long connectCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getLastCheckTime() {
        return lastCheckTime;
    }

    public void setLastCheckTime(long lastCheckTime) {
        this.lastCheckTime = lastCheckTime;
    }

    public boolean isTimeout() {
        if (System.currentTimeMillis() - lastCheckTime > (1000 * 60)) {
            return true;
        }
        return false;
    }
}
