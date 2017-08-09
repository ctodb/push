import cn.ctodb.push.client.Client;
import cn.ctodb.push.client.ClientProperties;
import cn.ctodb.push.dto.TextMessage;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by cc on 2017/7/7.
 */
public class ClientTest {

    private Client client;
    private Logger logger = LoggerFactory.getLogger(ClientTest.class);

    @Before
    public void init() {
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
    public void sendMsg() {
        for (; ; ) {
            if (client.getStatus().equals(Client.Status.STOP)) break;
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TextMessage textMessage = new TextMessage();
            textMessage.setContent("测试文本消息");
            logger.debug(client.getStatus() + " : " + "测试文本消息");
            client.sendMessage(textMessage);
        }
    }

}
