import cn.ctodb.callback.MessageRespCallback;
import cn.ctodb.push.client.conf.CallbackRegister;
import cn.ctodb.push.proto.Message;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @date: 2018-11-07 15:54
 * @Copyright: 2018 www.ctodb.cn All rights reserved.
 */
public class SendMessageTest extends TestBase {


    @BeforeClass
    public static void r() {
        CallbackRegister.MessageResp(new MessageRespCallback());
    }

    @Test
    public void send() throws InterruptedException {
        logger.debug("测试发送文本消息");
        Message.TextReq.Builder tb = Message.TextReq.newBuilder();
        Message.BaseInfo.Builder bb = Message.BaseInfo.newBuilder();
        bb.setCc(System.currentTimeMillis());
        bb.setFrom("u00001");
        bb.setTo("u000002");
        tb.setContent("消息正文");
        tb.setBaseInfo(bb);
        Message.TextReq textReq = tb.build();
        client.send(textReq);
        Thread.sleep(2 * 1000L);
        logger.debug("测试结束");
    }

}
