package cn.zjiali.bot.core;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

public class WebSocketClientHandler extends SimpleChannelInboundHandler<Object> {
    private final WebSocketClientHandshaker handshaker;

    public WebSocketClientHandler(WebSocketClientHandshaker handshaker) {
        this.handshaker = handshaker;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext chx, Object msg) throws Exception {
        Channel channel = chx.channel();
        if (!this.handshaker.isHandshakeComplete()) {
            this.handshaker.finishHandshake(channel, (FullHttpResponse) msg);
        } else if (msg instanceof WebSocketFrame frame) {
            if (frame instanceof TextWebSocketFrame textFrame) {
                System.out.println("WebSocket Client received message: " + textFrame.text());
            } else if (frame instanceof CloseWebSocketFrame) {
                System.out.println("WebSocket Client received closing");
                channel.close();
            }
        }

    }
}
