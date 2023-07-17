package cn.zjiali.bot.core;

import cn.hutool.core.exceptions.ExceptionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;

import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketClient {

    private final WebSocketUrlProvider webSocketUrlProvider;
    private final BotConfiguration botConfiguration;
    private Channel channel;

    public WebSocketClient(WebSocketUrlProvider webSocketUrlProvider, BotConfiguration botConfiguration) {
        this.webSocketUrlProvider = webSocketUrlProvider;
        this.botConfiguration = botConfiguration;
    }

    public void connect() throws URISyntaxException, InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        String webSocketUrl = this.webSocketUrlProvider.getWebSocketUrl();
        URI webSocketURL = new URI(webSocketUrl);
        WebSocketClientHandshaker webSocketClientHandshaker = WebSocketClientHandshakerFactory.newHandshaker(webSocketURL, WebSocketVersion.V13, null, true, null);
        WebSocketClientHandler webSocketClientHandler = new WebSocketClientHandler(webSocketClientHandshaker);
        int port = webSocketURL.getPort() == -1 ? 443 : webSocketURL.getPort();
        boolean ssl = "wss".equals(webSocketURL.getScheme());
        WebSocketChannelInitializer webSocketChannelInitializer = new WebSocketChannelInitializer(webSocketURL.getHost(), port, ssl, webSocketClientHandler);
        Channel channel = bootstrap.option(ChannelOption.SO_KEEPALIVE, true)
                .group(nioEventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(webSocketChannelInitializer).connect().addListener(it -> {
                    if (it.cause() != null) {
                        System.out.println("Websocket connect failed! e:{}" + ExceptionUtil.stacktraceToString(it.cause()));
                    }
                }).sync().channel();
        this.channel = channel;
    }
}
