package cn.ctodb.push.server.info;

import java.util.Arrays;

public enum ClientType {
    MOBILE(1, "android", "ios"),
    PC(2, "windows", "mac", "linux"),
    WEB(3, "web", "h5"),
    UNKNOWN(-1);

    public final int type;
    public final String[] os;

    ClientType(int type, String... os) {
        this.type = type;
        this.os = os;
    }

    public boolean contains(String osName) {
        return Arrays.stream(os).anyMatch(osName::contains);
    }

    public static ClientType find(String osName) {
        for (ClientType type : values()) {
            if (type.contains(osName.toLowerCase())) return type;
        }
        return UNKNOWN;
    }

    public static boolean isSameClient(String osNameA, String osNameB) {
        if (osNameA.equals(osNameB)) return true;
        return find(osNameA).contains(osNameB);
    }
}
