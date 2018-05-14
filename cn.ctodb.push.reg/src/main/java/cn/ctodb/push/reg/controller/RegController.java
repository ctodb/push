package cn.ctodb.push.reg.controller;

import cn.ctodb.push.reg.entity.ServerNode;
import cn.ctodb.push.reg.util.IpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class RegController {
    private static Map<String, ServerNode> nodes = new HashMap<>();

    @PostMapping("keep")
    public String keep(@RequestParam String id, @RequestParam String port, HttpServletRequest request) {
        if (!nodes.containsKey(id)) {
            ServerNode serverNode = new ServerNode();
            serverNode.setId(id);
            String ip = IpUtil.getIpAddr(request);
            serverNode.setUrl(ip + ":" + port);
            serverNode.setLastCheckTime(System.nanoTime());
            nodes.put(id, serverNode);
        } else {
            nodes.get(id).setLastCheckTime(System.nanoTime());
        }
        return "";
    }

    @GetMapping("nodes")
    public Set<String> nodes() {
        Set<String> set = new HashSet<>();
        for (ServerNode node : nodes.values()) {
            set.add(node.getUrl());
        }
        return set;
    }

}
