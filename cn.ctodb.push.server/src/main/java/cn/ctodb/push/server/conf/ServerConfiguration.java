package cn.ctodb.push.server.conf;

import cn.ctodb.push.core.PacketReceiver;
import cn.ctodb.push.dto.Command;
import cn.ctodb.push.server.filter.AuthFilter;
import cn.ctodb.push.server.handler.HandshakeHandler;
import cn.ctodb.push.server.handler.HeartBeatHandler;
import cn.ctodb.push.server.handler.TextMessageHandler;
import org.msgpack.MessagePack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.ctodb.push.server.ServerHandler;
import cn.ctodb.push.server.service.MgsServer;
import cn.ctodb.push.utils.MsgPackDecode;
import cn.ctodb.push.utils.MsgPackEncode;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @Copyright: 2018 www.ctodb.cn Inc. All rights reserved.
 */
@Configuration
@EnableConfigurationProperties(ApplicationProperties.class)
public class ServerConfiguration {
    private Logger logger = LoggerFactory.getLogger(ServerConfiguration.class);

    @Autowired
    private ApplicationProperties serverProperties;

    @Autowired
    private RestTemplateBuilder builder;

    @Bean
    public RestTemplate restTemplate() {
        return builder.build();
    }

    @Bean
    public MgsServer mgsServer() throws IOException {
        if (serverProperties.getServer().getPort() < 0) {
            ServerSocket serverSocket = new ServerSocket(0); //读取空闲的可用端口
            int port = serverSocket.getLocalPort();
            serverProperties.getServer().setPort(port);
            logger.info("使用随机端口号:" + port);
        }
        return new MgsServer(serverProperties.getServer().getPort(), serverHandler(), msgPackDecode(), msgPackEncode());
    }

    @Bean
    public ServerHandler serverHandler() {
        return new ServerHandler(packetReceiver());
    }

    @Bean
    public MsgPackEncode msgPackEncode() {
        return new MsgPackEncode();
    }

    @Bean
    public MessagePack messagePack() {
        return new MessagePack();
    }

    @Bean
    public MsgPackDecode msgPackDecode() {
        return new MsgPackDecode();
    }

    @Bean
    public PacketReceiver packetReceiver() {
        PacketReceiver packetReceiver = new PacketReceiver();
        packetReceiver.addHandler(heartBeatHandler());
        packetReceiver.addHandler(handshakeHandler());
        packetReceiver.addHandler(textMessageHandler());
        packetReceiver.addFilter(authFilter());
        return packetReceiver;
    }

    @Bean
    public AuthFilter authFilter() {
        AuthFilter authFilter = new AuthFilter();
        authFilter.addCmd(Command.TEXT_MESSAGE);
        return authFilter;
    }

    @Bean
    public HeartBeatHandler heartBeatHandler() {
        return new HeartBeatHandler();
    }

    @Bean
    public TextMessageHandler textMessageHandler() {
        return new TextMessageHandler();
    }

    @Bean
    public HandshakeHandler handshakeHandler() {
        return new HandshakeHandler();
    }
}
