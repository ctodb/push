package cn.ctodb.push.server.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.ctodb.push.server.Application;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TestRedis {
    private Logger logger = LoggerFactory.getLogger(TestRedis.class);

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() throws Exception {
        redisTemplate.boundSetOps("userOnline").add("user01");
        redisTemplate.boundSetOps("userOnline").add("user02");
        logger.debug(redisTemplate.boundSetOps("userOnline").size()+"");
        redisTemplate.boundSetOps("userOnline").remove("user01");
    }

    @Test
    public void testObj() throws Exception {
        // User user = new User("aa@126.com", "aa", "aa123456", "aa", "123");
        // ValueOperations<String, User> operations =
        // redisTemplate.opsForValue();
        // operations.set("com.neox", user);
        // operations.set("com.neo.f", user, 1, TimeUnit.SECONDS);
        // Thread.sleep(1000);
        // // redisTemplate.delete("com.neo.f");
        // boolean exists = redisTemplate.hasKey("com.neo.f");
        // if (exists) {
        // System.out.println("exists is true");
        // } else {
        // System.out.println("exists is false");
        // }
        // Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
    }
}