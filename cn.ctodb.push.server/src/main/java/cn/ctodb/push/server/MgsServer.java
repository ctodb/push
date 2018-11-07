package cn.ctodb.push.server;

import cn.ctodb.push.utils.ProtobufDecoder;
import cn.ctodb.push.utils.ProtobufEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

import java.util.UUID;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @Copyright: 2018 www.ctodb.cn Inc. All rights reserved.
 */
public class MgsServer implements DisposableBean, Runnable {

    private static final Logger logger = LoggerFactory.getLogger(MgsServer.class);

    /**
     * 用于分配处理业务线程的线程组个数
     */
    protected static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors() * 2; // 默认
    /**
     * 业务出现线程大小
     */
    protected static final int BIZTHREADSIZE = 4;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private Channel channel;
    private final int port;
    private String status = "stop";
    private String id;

    public MgsServer(int port, ServerHandler serverHandler) {
        super();
        this.id = UUID.randomUUID().toString();
        this.port = port;
        this.serverHandler = serverHandler;
    }

    private final ServerHandler serverHandler;

    @Override
    public void run() {
        ServerBootstrap b = new ServerBootstrap();// 引导辅助程序

        bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
        workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);
        try {
            b.group(bossGroup, workerGroup);
            b.channel(NioServerSocketChannel.class);// 设置nio类型的channel
            b.childHandler(new ChannelInitializer<SocketChannel>() {// 有连接到达时会创建一个channel
                protected void initChannel(SocketChannel ch) throws Exception {
                    logger.debug("客户端:{} 初始化", ch.remoteAddress());
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(new ProtobufVarint32FrameDecoder());
                    pipeline.addLast(new ProtobufDecoder());
                    pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                    pipeline.addLast(new ProtobufEncoder());
                    pipeline.addLast(serverHandler);
                }
            });
            b.option(ChannelOption.SO_BACKLOG, 128);
            b.childOption(ChannelOption.SO_KEEPALIVE, true);
            logger.info("server start : {}", port);
            status = "run";
            ChannelFuture f = b.bind(port).sync();// 配置完成，开始绑定server，通过调用sync同步方法阻塞直到绑定成功
            channel = f.channel();
            channel.closeFuture().await();// 应用程序会一直等待，直到channel关闭
        } catch (Exception e) {
            status = "error";
            logger.error(e.getMessage(), e);
        }

    }

    public void stop() {
        status = "stop";
        logger.info("destroy server resources");
        if (null == channel) {
            logger.error("server channel is null");
        }
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
        channel.closeFuture().syncUninterruptibly();
        channel = null;
    }

    @Override
    public void destroy() throws Exception {
        stop();
    }

    public String getId() {
        return id;
    }

    public int getPort() {
        return port;
    }

}