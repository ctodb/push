package cn.ctodb.push.client;

import org.msgpack.MessagePack;

import java.io.IOException;

/**
 * Created by cc on 2017/8/21.
 */
public class Util {

    private static MessagePack messagePack = new MessagePack();

    public static byte[] msg2bytes(Object o) throws IOException {
        return messagePack.write(o);
    }

}
