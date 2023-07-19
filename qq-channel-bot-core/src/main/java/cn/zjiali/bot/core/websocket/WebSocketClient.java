package cn.zjiali.bot.core.websocket;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.zjiali.bot.core.BotConfiguration;
import cn.zjiali.bot.core.websocket.handler.WebSocketClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketClient {

    private final WebSocketUrlProvider webSocketUrlProvider;
    private final BotConfiguration botConfiguration;
    private Channel channel;
    private final NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public WebSocketClient(WebSocketUrlProvider webSocketUrlProvider, BotConfiguration botConfiguration) {
        this.webSocketUrlProvider = webSocketUrlProvider;
        this.botConfiguration = botConfiguration;
    }

    public void connect() throws URISyntaxException, InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        String webSocketUrl = this.webSocketUrlProvider.getWebSocketUrl();
        URI webSocketURL = new URI(webSocketUrl);
        WebSocketClientHandshaker webSocketClientHandshaker = WebSocketClientHandshakerFactory
                .newHandshaker(webSocketURL, WebSocketVersion.V13, null, true, new DefaultHttpHeaders());
        WebSocketClientHandler webSocketClientHandler = new WebSocketClientHandler(webSocketClientHandshaker, botConfiguration);
        int port = webSocketURL.getPort() == -1 ? 443 : webSocketURL.getPort();
        boolean ssl = "wss".equals(webSocketURL.getScheme());
        String host = webSocketURL.getHost();
        WebSocketChannelInitializer webSocketChannelInitializer = new WebSocketChannelInitializer(host, port, ssl, webSocketClientHandler);
        this.channel = bootstrap.option(ChannelOption.SO_KEEPALIVE, true)
                .group(nioEventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(webSocketChannelInitializer).connect(host, port).addListener(it -> {
                    if (it.cause() != null) {
                        logger.error("WebSocket connect failed! e:{}", ExceptionUtil.stacktraceToString(it.cause()));
                    }
                }).sync().channel();
        webSocketClientHandler.handshakeFuture().sync();
    }

    public Channel channel() {
        return this.channel;
    }

    public void shutdown() {
        this.nioEventLoopGroup.shutdownGracefully();
    }
}
