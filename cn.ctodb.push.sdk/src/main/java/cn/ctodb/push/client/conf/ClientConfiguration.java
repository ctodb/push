package cn.ctodb.push.client.conf;

import cn.ctodb.push.client.ClientHandler;
import cn.ctodb.push.client.handler.AuthHandler;
import cn.ctodb.push.core.PacketReceiver;

/**
 * Created by cc on 2017/8/21.
 */
public class ClientConfiguration {

    private static PacketReceiver packetReceiver;

    static {
        init();
    }

    public static void init() {
        packetReceiver = new PacketReceiver();
        new AuthHandler();
    }

    public static PacketReceiver packetReceiver() {
        return packetReceiver;
    }

    public static ClientHandler clientHandler() {
        return new ClientHandler(packetReceiver());
    }

}
