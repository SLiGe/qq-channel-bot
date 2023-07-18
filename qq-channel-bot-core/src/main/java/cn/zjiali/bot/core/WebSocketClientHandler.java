package cn.zjiali.bot.core;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSocketClientHandler extends SimpleChannelInboundHandler<Object> {
    private final WebSocketClientHandshaker handshaker;
    private ChannelPromise handshakeFuture;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public WebSocketClientHandler(WebSocketClientHandshaker handshaker) {
        this.handshaker = handshaker;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext chx, Object msg) throws Exception {
        try {
            Channel channel = chx.channel();
            if (!this.handshaker.isHandshakeComplete()) {
                handshaker.finishHandshake(channel, (FullHttpResponse) msg);
                logger.debug("WebSocket Client connected!");
                handshakeFuture.setSuccess();
                return;
            }
            if (msg instanceof WebSocketFrame frame) {
                if (frame instanceof TextWebSocketFrame textFrame) {
                    String jsonData = textFrame.text();
                    JSONObject jsonObject = JSONUtil.parseObj(jsonData);
                    Integer op = jsonObject.getInt("op");
                    Integer s = jsonObject.getInt("s");

                    logger.debug("WebSocket Client received message: {}", textFrame.text());
                } else if (frame instanceof CloseWebSocketFrame) {
                    logger.debug("WebSocket Client received closing");
                    channel.close();
                }
            }
        } catch (Exception e) {
            if (!handshakeFuture.isDone()) {
                handshakeFuture.setFailure(e);
            }
            chx.fireExceptionCaught(e);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.handshaker.handshake(ctx.channel());
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        this.handshakeFuture = ctx.newPromise();
    }

    public ChannelPromise handshakeFuture() {
        return this.handshakeFuture;
    }


}
