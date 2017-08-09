package cn.ctodb.push.server.test;

import cn.ctodb.push.server.UserManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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
public class TestUser {
    private Logger logger = LoggerFactory.getLogger(TestRedis.class);

    @Autowired
    private UserManager userManager;

    @Test
    public void before() throws Exception {
        userManager.clearUserOnlineData();
        logger.debug("清理缓存");
    }

    @Test
    public void test() throws Exception {
        logger.debug("模拟添加用户");
        userManager.on("user001");
        userManager.on("user002");
        userManager.on("user003");
        userManager.on("user004");
        userManager.on("user005");
        userManager.on("user001");
        userManager.on("user001");
    }

    @Test
    public void count() {
        logger.debug("在线人数 : {}", userManager.getOnlineUserNum());
    }

}