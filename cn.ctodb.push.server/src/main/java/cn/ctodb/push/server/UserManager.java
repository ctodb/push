package cn.ctodb.push.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserManager {

    private static final Logger logger = LoggerFactory.getLogger(UserManager.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private final String onlineUserListKey = "onlineUserListKey";

    public void clearUserOnlineData() {
        redisTemplate.delete(onlineUserListKey);
    }

    public void on(String userId) {
        getUserSet().add(userId, 0);
        logger.info("user online {}", userId);
    }

    public void off(String userId) {
        getUserSet().remove(userId);
        logger.info("user offline {}", userId);
    }

    //在线用户数量
    public long getOnlineUserNum() {
        Long value = getUserSet().size();
        return value == null ? 0 : value;
    }

    //在线用户列表
    public Set<String> getOnlineUserList(String publicIP, int start, int end) {
        return getUserSet().range(start, end);
    }

    public BoundZSetOperations<String, String> getUserSet() {
        return redisTemplate.boundZSetOps(onlineUserListKey);
    }
}
