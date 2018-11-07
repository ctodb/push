package cn.ctodb.push.core;

import java.util.HashMap;
import java.util.Map;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @date: 2018-11-06 10:25
 * @Copyright: 2018 www.ctodb.cn All rights reserved.
 */
public class Version {

    private static Map<Byte, Version> map = new HashMap<>();

    public final static Version V1_0_0 = new Version((byte) 1, "1.0.0");

    private Byte b;
    private String version;

    public static Version get() {
        return V1_0_0;
    }

    private Version(Byte b, String version) {
        this.b = b;
        this.version = version;
        map.put(b, this);
    }

    public Byte toByte() {
        return b;
    }

    public String getVersion() {
        return version;
    }
}
