package cn.zjiali.bot.core;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {
    private final String host;
    private final Integer port;
    private final boolean ssl;
    private final WebSocketClientHandler webSocketClientHandler;

    public WebSocketChannelInitializer(String host, Integer port, boolean ssl, WebSocketClientHandler webSocketClientHandler) {
        this.host = host;
        this.port = port;
        this.ssl = ssl;
        this.webSocketClientHandler = webSocketClientHandler;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        if (ssl) {
            var sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
            pipeline.addLast(sslContext.newHandler(socketChannel.alloc(), host, port));
        }
        pipeline.addLast(new IdleStateHandler(30, 30, 0, TimeUnit.SECONDS));
        pipeline.addLast(
                new HttpClientCodec(),
                new HttpObjectAggregator(1024 * 1024 * 10),
                CustomWebSocketClientCompressionHandler.INSTANCE,
                webSocketClientHandler
        );
        //pipeline.addLast(new HeartBeatServerHandler());
    }
}
