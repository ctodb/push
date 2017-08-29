package cn.ctodb.push.client;

import cn.ctodb.push.client.conf.ClientConfiguration;
import cn.ctodb.push.dto.Message;
import cn.ctodb.push.dto.Packet;
import cn.ctodb.push.utils.MsgPackDecode;
import cn.ctodb.push.utils.MsgPackEncode;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import org.msgpack.MessagePack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Netty 服务端代码
 *
 * @author lihzh
 * @alia OneCoder
 * @blog http://www.coderli.com
 */
public class Client implements Runnable {

    private Logger logger = LoggerFactory.getLogger(Client.class);

    private Status status = Status.STOP;

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

    public void send(Packet packet) {
        channel.writeAndFlush(packet);
    }

    public void sendMessage(Message message) {
        Packet packet = new Packet(message.getCmd());
        try {
            packet.setBody(new MessagePack().write(message));
            channel.writeAndFlush(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
                    pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65536, 0, 4, 0, 4));
                    pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                    pipeline.addLast("decoder", new MsgPackDecode());
                    pipeline.addLast("encoder", new MsgPackEncode());
                    pipeline.addLast(ClientConfiguration.clientHandler());
                }
            });
            channel = b.connect(clientProperties.getServerHost(), clientProperties.getServerPort()).sync().channel();
            status = Status.START;
            channel.closeFuture().sync();
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

    public enum Status {
        STOP, START, STARTING
    }
}
