import cn.ctodb.push.client.Client;
import cn.ctodb.push.client.ClientProperties;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @date: 2018-11-07 15:54
 * @Copyright: 2018 www.ctodb.cn All rights reserved.
 */
public abstract class TestBase {

    protected static Client client;
    protected static Logger logger = LoggerFactory.getLogger(ClientTest.class);

    @BeforeClass
    public static void init() {
        logger.debug("init");
//        String re = getJsonByInternet("http://localhost:8080/nodes");
//        logger.debug("发现服务器地址：{}", re);
//        JSONArray json = new JSONArray(re);
//        String str = json.get(0).toString();
//        String[] serverUrl = str.split("[:]");
        ClientProperties properties = new ClientProperties();
//        properties.setServerHost(serverUrl[0]);
//        properties.setServerPort(Integer.parseInt(serverUrl[1]));
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
        while (true) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!client.getStatus().equals(Client.Status.STARTING)) break;
        }
    }

}
