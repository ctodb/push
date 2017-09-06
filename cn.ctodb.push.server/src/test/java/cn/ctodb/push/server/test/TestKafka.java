package cn.ctodb.push.server.test;

import cn.ctodb.push.server.PushServerApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by cc on 2017/8/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PushServerApp.class)
public class TestKafka {

    @Autowired
    public KafkaTemplate<String, String> kafkaTemplate;

    @Test
    public void test() {
        kafkaTemplate.send("test-topic", "hello");
    }

}
