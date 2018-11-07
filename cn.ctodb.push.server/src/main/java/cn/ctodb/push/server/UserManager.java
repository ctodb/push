package cn.ctodb.push.server;

import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

@Component
public class UserManager {

    private static final Logger logger = LoggerFactory.getLogger(UserManager.class);

    private static HashMap<String, ChannelHandlerContext> onlineUser = new HashMap<>();

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private MgsServer mgsServer;

    private final String onlineUserListKey = "onlineUserListKey";

    public void clearUserOnlineData() {
        redisTemplate.delete(onlineUserListKey);
    }

//    public void on(User user, ChannelHandlerContext context) {
////        // 查询是否有其他在线情况
////
////        // 记录但前进程连接的用户信息
////        onlineUser.put(user.id, context);
////        User u = getUserHash().get(user.id);
////        if (u != null && u.servers != null) {
////            user.servers = u.servers;
////        } else {
////            user.servers = new HashSet<>();
////        }
////        user.servers.add(mgsServer.getId());
////        // 记录信息进redis
////        getUserHash().put(user.id, user);
////        logger.info("user online {}", user.id);
//    }

    public void off(String userId) {
//        User u = getUserHash().get(userId);
//        u.servers.remove(mgsServer.getId());
//        getUserHash().put(userId, u);
//        onlineUser.remove(userId).disconnect();
//        logger.info("user offline {}", userId);
    }

    public ChannelHandlerContext getContext(String userId) {
        return onlineUser.get(userId);
    }

    //在线用户数量
    public int getOnlineUserNum() {
        Integer value = onlineUser.size();
        return value == null ? 0 : value;
    }

    //在线用户列表
    public Set<String> getOnlineUserList(String publicIP, int start, int end) {
        return onlineUser.keySet();
    }

//    public BoundHashOperations<String, String, String> getUserHash() {
//        return redisTemplate.bound(onlineUserListKey);
//    }

}
