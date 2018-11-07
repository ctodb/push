//package cn.ctodb.push.server.filter;
//
//import cn.ctodb.push.core.Command;
//import cn.ctodb.push.core.Connection;
//import cn.ctodb.push.core.Filter;
//import cn.ctodb.push.core.FilterResult;
//import cn.ctodb.push.server.session.Session;
//import cn.ctodb.push.server.session.SessionManager;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.io.IOException;
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * All rights Reserved, Designed By www.ctodb.cn
// *
// * @version V1.0
// * @author: lichaohn@163.com
// * @Copyright: 2018 www.ctodb.cn Inc. All rights reserved.
// */
//public class AuthFilter implements Filter {
//
//    private Logger logger = LoggerFactory.getLogger(AuthFilter.class);
//
//    public Set<Command> commands = new HashSet<>();
//
//    public void addCmd(Command command) {
//        commands.add(command);
//    }
//
//    @Autowired
//    private MessagePack messagePack;
//    @Autowired
//    private SessionManager sessionManager;
//
//    @Override
//    public FilterResult exec(Packet packet, Connection connection) {
//        Command command = Command.toCMD(packet.getCmd());
//        logger.debug("command : {}", command);
//        if (commands.contains(command)) {
//            String sessionId = packet.getSessionId();
//            if (sessionId == null) {
//                logger.info("sessionId is null");
//                sendOffline(packet,connection);
//                return FilterResult.END;
//            } else {
//                logger.info("sessionId is [{}]", sessionId);
//                Session session = sessionManager.get(sessionId);
//                if (session == null) {
//                    logger.info("session is Invalid : {}", sessionId);
//                    sendOffline(packet,connection);
//                    return FilterResult.END;
//                }
//                logger.debug("session is {}", session);
//            }
//        }
//        return FilterResult.NEXT;
//    }
//
//    public void sendOffline(Packet packet, Connection connection){
//        Packet re = new Packet(Command.ERROR);
//        re.setSessionId(packet.getSessionId());
//        Error error = new Error();
//        error.setCode(ErrorCode.OFFLINE, "用户不在线");
//        try {
//            re.setBody(messagePack.write(error));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        connection.send(re);
//    }
//
//}
