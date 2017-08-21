package cn.ctodb.push.server.conf;

import cn.ctodb.push.core.PacketReceiver;
import cn.ctodb.push.server.handler.HandshakeHandler;
import cn.ctodb.push.server.handler.HeartBeatHandler;
import cn.ctodb.push.server.handler.TextMessageHandler;
import org.msgpack.MessagePack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public MessagePack messagePack(){
        return new MessagePack();
    }

    @Bean
    public MsgPackDecode msgPackDecode() {
        return new MsgPackDecode();
    }

    @Bean
    public PacketReceiver packetReceiver() {
        PacketReceiver packetReceiver = new PacketReceiver();
        packetReceiver.register(heartBeatHandler());
        packetReceiver.register(handshakeHandler());
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

    @Bean
    public HandshakeHandler handshakeHandler(){
        return new HandshakeHandler();
    }
}
