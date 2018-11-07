package cn.ctodb.push.client;

import cn.ctodb.push.client.conf.ClientConfiguration;
import cn.ctodb.push.proto.Auth;
import cn.ctodb.push.utils.ProtobufDecoder;
import cn.ctodb.push.utils.ProtobufEncoder;
import com.google.protobuf.MessageLite;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @Copyright: 2018 www.ctodb.cn Inc. All rights reserved.
 */
public class Client implements Runnable {

    private Logger logger = LoggerFactory.getLogger(Client.class);

    private Status status = Status.STOP;
    private String sessionId = null;

    private ClientProperties clientProperties;
    private Channel channel;
    private EventLoopGroup workerGroup;

    public Client(ClientProperties clientProperties) {
        this.clientProperties = clientProperties;
    }

    public void start() throws IOException, InterruptedException {
        if (status.equals(Status.STOP)) {
            status = Status.STARTING;
            new Thread(this).start();
            new Thread(new HeartBeatTasker(this)).start();
            logger.info("client start...");
        }
    }

    public void send(MessageLite message) {
        channel.writeAndFlush(message);
    }

    @Override
    public void run() {
        workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            // b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(new ProtobufVarint32FrameDecoder());
                    pipeline.addLast(new ProtobufDecoder());
                    pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                    pipeline.addLast(new ProtobufEncoder());
                    pipeline.addLast(ClientConfiguration.clientHandler());
                }
            });
            channel = b.connect(clientProperties.getServerHost(), clientProperties.getServerPort()).sync().channel();
            status = Status.START;
            channel.closeFuture().await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        status = Status.STOP;
    }

    public void stop() {
        if (workerGroup != null) {
            channel.close();
            workerGroup.shutdownGracefully();
        }
        status = Status.STOP;
    }

    public Status getStatus() {
        return status;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public enum Status {
        STOP, START, STARTING
    }
}
