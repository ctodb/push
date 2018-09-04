package cn.ctodb.push.reg.controller;

import cn.ctodb.push.reg.entity.ServerNode;
import cn.ctodb.push.reg.util.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class RegController {
    private Logger logger = LoggerFactory.getLogger(RegController.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping("keep")
    public ServerNode keep(@RequestParam String id, @RequestParam(required = false) String ip, @RequestParam String port, HttpServletRequest request) {
        BoundHashOperations imKeep = getNodes();
        ServerNode serverNode = null;
        if (!imKeep.hasKey(id)) {
            serverNode = new ServerNode();
            serverNode.setId(id);
            if (StringUtils.isEmpty(ip))
                ip = IpUtil.getIpAddr(request);
            serverNode.setUrl(ip + ":" + port);
            serverNode.setLastCheckTime(System.currentTimeMillis());
        } else {
            serverNode = (ServerNode) imKeep.get(id);
            serverNode.setLastCheckTime(System.currentTimeMillis());
        }
        logger.debug("keep:{} {} {}", id, ip, port);
        imKeep.put(id, serverNode);
        return serverNode;
    }

    @GetMapping("nodes")
    public Set<String> nodes() {
        BoundHashOperations<String, String, ServerNode> imKeep = getNodes();
        Set<String> set = new HashSet<>();
        for (ServerNode node : Objects.requireNonNull(imKeep.values())) {
            set.add(node.getUrl());
        }
        return set;
    }

    private BoundHashOperations getNodes() {
        BoundHashOperations imKeep = redisTemplate.boundHashOps("im_keep");
        return imKeep;
    }

    @Scheduled(cron = "0/30 * * * * *")
    public void heartbeat() {
        logger.debug("heartbeat");
        BoundHashOperations<String, String, ServerNode> imKeep = getNodes();
        for (Map.Entry<String, ServerNode> node : imKeep.entries().entrySet()) {
            ServerNode serverNode = node.getValue();
            if (System.currentTimeMillis() - serverNode.getLastCheckTime() > 70 * 1000) {
                imKeep.delete(node.getKey());
                logger.debug("heartbeat delete:{} {}", node.getKey(), System.currentTimeMillis() - serverNode.getLastCheckTime());
            }
        }
    }

}
