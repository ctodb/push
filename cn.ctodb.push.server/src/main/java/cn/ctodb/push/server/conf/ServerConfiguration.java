package cn.ctodb.push.server.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.ctodb.push.core.PacketReceiver;
import cn.ctodb.push.handler.HeartBeatHandler;
import cn.ctodb.push.handler.TextMessageHandler;
import cn.ctodb.push.server.ServerHandler;
import cn.ctodb.push.server.service.MgsServer;
import cn.ctodb.push.utils.MsgPackDecode;
import cn.ctodb.push.utils.MsgPackEncode;

@Configuration
@EnableConfigurationProperties(ApplicationProperties.class)
public class ServerConfiguration {

    @Autowired
    private ApplicationProperties serverProperties;

    @Bean
    public MgsServer mgsServer() {
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
    public MsgPackDecode msgPackDecode() {
        return new MsgPackDecode();
    }

    @Bean
    public PacketReceiver packetReceiver() {
        PacketReceiver packetReceiver = new PacketReceiver();
        packetReceiver.register(heartBeatHandler());
        packetReceiver.register(textMessageHandler());
        return packetReceiver;
    }

    @Bean
    public HeartBeatHandler heartBeatHandler() {
        return new HeartBeatHandler();
    }

    @Bean
    public TextMessageHandler textMessageHandler() {
        return new TextMessageHandler();
    }

}
