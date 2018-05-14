package cn.ctodb.push.server.test;

import cn.ctodb.push.server.UserManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.ctodb.push.server.PushServerApp;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PushServerApp.class)
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
//        userManager.on("user001");
//        userManager.on("user002");
//        userManager.on("user003");
//        userManager.on("user004");
//        userManager.on("user005");
//        userManager.on("user001");
//        userManager.on("user001");
    }

    @Test
    public void count() {
        logger.debug("在线人数 : {}", userManager.getOnlineUserNum());
    }

}