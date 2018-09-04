package cn.ctodb.push.server.session;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class SessionManager {

    private static final Logger logger = LoggerFactory.getLogger(SessionManager.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private final String cacheKey = "__SESSIONS__";

    public void on(Session session) {
        logger.info("new session : {}", session.getId());
        getCache().put(session.getId(), JSON.toJSONString(session));
    }

    public void off(Session session) {
        getCache().delete(session.getId());
    }

    public Session get(String sessionId) {
        return JSON.parseObject(getCache().get(sessionId), PushSession.class);
    }

    public BoundHashOperations<String, String, String> getCache() {
        return redisTemplate.boundHashOps(cacheKey);
    }
}
