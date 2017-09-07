package cn.ctodb.push.server.filter;

import cn.ctodb.push.core.Connection;
import cn.ctodb.push.core.Filter;
import cn.ctodb.push.core.FilterResult;
import cn.ctodb.push.dto.Command;
import cn.ctodb.push.dto.Packet;
import cn.ctodb.push.server.session.Session;
import cn.ctodb.push.server.session.SessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class AuthFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    public Set<Command> commands = new HashSet<>();

    public void addCmd(Command command) {
        commands.add(command);
    }

    @Autowired
    private SessionManager sessionManager;

    @Override
    public FilterResult exec(Packet packet, Connection connection) {
        Command command = Command.toCMD(packet.getCmd());
        logger.debug("command : {}", command);
        if (commands.contains(command)) {
            String sessionId = packet.getSessionId();
            if (sessionId == null) {
                logger.info("sessionId is null");
                return FilterResult.END;
            } else {
                logger.info("sessionId is [{}]", sessionId);
                Session session = sessionManager.get(sessionId);
                if (session == null) {
                    logger.info("session is Invalid : {}", sessionId);
                    return FilterResult.END;
                }
                logger.debug("session is {}", session);
            }
        }
        return FilterResult.NEXT;
    }

}
