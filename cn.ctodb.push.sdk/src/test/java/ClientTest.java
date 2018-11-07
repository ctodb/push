import cn.ctodb.push.client.Client;
import cn.ctodb.push.client.ClientProperties;
import cn.ctodb.push.core.Command;
import cn.ctodb.push.proto.Auth;
import org.json.JSONArray;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

/**
 * Created by cc on 2017/7/7.
 */
public class ClientTest extends TestBase {

    @Test
    public void login() throws InterruptedException {
        logger.debug("测试登录");
        Auth.AuthReq authReq = Auth.AuthReq.newBuilder().setUid("U000001").setToken(UUID.randomUUID().toString()).build();
        client.send(authReq);
//        while (true){
        Thread.sleep(2 * 1000L);
//        }
//        logger.debug("测试结束");
    }
//    @Test
//    public void sendMsg() throws InterruptedException {
//        TextMessage textMessage = new TextMessage();
//        textMessage.setContent("测试文本消息");
//        logger.debug(client.getStatus() + " : " + "测试文本消息");
//        client.sendMessage(textMessage);
//        Thread.sleep(2000L);
//    }

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
