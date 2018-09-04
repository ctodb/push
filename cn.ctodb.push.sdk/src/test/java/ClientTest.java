import cn.ctodb.push.client.Client;
import cn.ctodb.push.client.ClientProperties;
import cn.ctodb.push.client.Util;
import cn.ctodb.push.dto.Command;
import cn.ctodb.push.dto.HandshakeReq;
import cn.ctodb.push.dto.Packet;
import cn.ctodb.push.dto.TextMessage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by cc on 2017/7/7.
 */
public class ClientTest {

    private static Client client;
    private static Logger logger = LoggerFactory.getLogger(ClientTest.class);

    @BeforeClass
    public static void init() {
        logger.debug("init");
        String re = getJsonByInternet("http://localhost:8080/nodes");
        logger.debug("发现服务器地址：{}", re);
        JSONArray json = new JSONArray(re);
        String str = json.get(0).toString();
        String[] serverUrl = str.split("[:]");
        ClientProperties properties = new ClientProperties();
        properties.setServerHost(serverUrl[0]);
        properties.setServerPort(Integer.parseInt(serverUrl[1]));
        client = new Client(properties);
        try {
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
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

    public static String getJsonByInternet(String path) {
        try {
            URL url = new URL(path.trim());
            //打开连接
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            if (200 == urlConnection.getResponseCode()) {
                //得到输入流
                InputStream is = urlConnection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while (-1 != (len = is.read(buffer))) {
                    baos.write(buffer, 0, len);
                    baos.flush();
                }
                return baos.toString("utf-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
