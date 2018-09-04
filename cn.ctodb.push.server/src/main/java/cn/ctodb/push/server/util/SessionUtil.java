package cn.ctodb.push.server.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

/**
 * Created by cc on 2017/8/11.
 */
public class SessionUtil {

    private static final int SESSION_ID_BYTES = 16;

    public static enum SessionIdType {SHORT, LANG}

    public static String get() {
        return RandomStringUtils.randomAlphanumeric(16);
    }

}
