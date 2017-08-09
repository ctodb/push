package cn.ctodb.push.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public final class UserManager {

    private static final Logger logger = LoggerFactory.getLogger(UserManager.class);

    @Autowired
    private RedisTemplate redisTemplate;

    private final String onlineUserListKey = "onlineUserListKey";

    public void clearUserOnlineData() {
        redisTemplate.delete(onlineUserListKey);
    }

    public void addToOnlineList(String userId) {
        BoundSetOperations bso = redisTemplate.boundSetOps(onlineUserListKey);
        bso.add(userId);
        logger.info("user online {}", userId);
    }

    public void remFormOnlineList(String userId) {
        BoundSetOperations bso = redisTemplate.boundSetOps(onlineUserListKey);
        bso.remove(userId);
        logger.info("user offline {}", userId);
    }

    //在线用户数量
    public long getOnlineUserNum() {
        BoundSetOperations bso = redisTemplate.boundSetOps(onlineUserListKey);
        Long value = bso.size();
        return value == null ? 0 : value;
    }

    //在线用户数量
    public long getOnlineUserNum(String publicIP) {
        String online_key = CacheKeys.getOnlineUserListKey(publicIP);
        Long value = cacheManager.zCard(online_key);
        return value == null ? 0 : value;
    }

    //在线用户列表
    public List<String> getOnlineUserList(String publicIP, int start, int end) {
        String key = CacheKeys.getOnlineUserListKey(publicIP);
        return cacheManager.zrange(key, start, end, String.class);
    }

    public Set<String> getUserSet(){
        Set<String> userSet = (Set<String>) cacheManager.getCache(cacheName).get(onlineUserListKey);
        return userSet;
    }
}
