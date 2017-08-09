package cn.ctodb.push.server.test;

import cn.ctodb.push.server.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TestRedis {
    private Logger logger = LoggerFactory.getLogger(TestRedis.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() throws Exception {
        redisTemplate.boundSetOps("userOnline").add("user01");
        redisTemplate.boundSetOps("userOnline").add("user02");
        logger.debug(redisTemplate.boundSetOps("userOnline").size() + "");
        redisTemplate.boundSetOps("userOnline").remove("user01");
    }
}