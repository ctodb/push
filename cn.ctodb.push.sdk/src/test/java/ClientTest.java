import cn.ctodb.push.client.Client;
import cn.ctodb.push.client.ClientProperties;
import cn.ctodb.push.client.Util;
import cn.ctodb.push.dto.Command;
import cn.ctodb.push.dto.HandshakeReq;
import cn.ctodb.push.dto.Packet;
import cn.ctodb.push.dto.TextMessage;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by cc on 2017/7/7.
 */
public class ClientTest {

    private static Client client;
    private static Logger logger = LoggerFactory.getLogger(ClientTest.class);

    @BeforeClass
    public static void init() {
        logger.debug("init");
        ClientProperties properties = new ClientProperties();
        properties.setServerHost("localhost");
        properties.setServerPort(9901);
        client = new Client(properties);
        try {
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (; ; ) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!client.getStatus().equals(Client.Status.STARTING)) break;
        }
    }

    @Test
    public void HandshakeHandler() throws IOException, InterruptedException {
        Packet packet = new Packet(Command.HANDSHAKE_REQ);
        HandshakeReq handshakeReq = new HandshakeReq();
        handshakeReq.deviceId = "deviceId";
        handshakeReq.osName = "ios";
        handshakeReq.osVersion = "10.10";
        handshakeReq.clientVersion = "1.0.0";
        packet.setBody(Util.msg2bytes(handshakeReq));
        client.send(packet);
        Thread.sleep(2000L);
    }

    @Test
    public void sendMsg() throws InterruptedException {
        TextMessage textMessage = new TextMessage();
        textMessage.setContent("测试文本消息");
        logger.debug(client.getStatus() + " : " + "测试文本消息");
        client.sendMessage(textMessage);
        Thread.sleep(2000L);
    }

}
