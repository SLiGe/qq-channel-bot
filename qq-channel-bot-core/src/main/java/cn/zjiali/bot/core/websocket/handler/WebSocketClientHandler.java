package cn.zjiali.bot.core.websocket.handler;

import cn.zjiali.bot.core.BotConfiguration;
import cn.zjiali.bot.core.enums.OpCode;
import cn.zjiali.bot.core.enums.SubEventType;
import cn.zjiali.bot.core.event.handler.IdentifyEventHandler;
import cn.zjiali.bot.core.event.model.GatewayEvent;
import cn.zjiali.bot.core.util.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
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

import java.io.IOException;
import java.util.Objects;

public class WebSocketClientHandler extends SimpleChannelInboundHandler<Object> {
    private final WebSocketClientHandshaker handshaker;
    private ChannelPromise handshakeFuture;
    private final BotConfiguration botConfiguration;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public WebSocketClientHandler(WebSocketClientHandshaker handshaker, BotConfiguration botConfiguration) {
        this.handshaker = handshaker;
        this.botConfiguration = botConfiguration;
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
                    logger.debug("WebSocket Client received message: {}", jsonData);
                    handleGatewayEvent(jsonData, channel);
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

    private void handleGatewayEvent(String gateEventJson, Channel channel) throws IOException {
        JsonNode jsonNode = JsonUtil.parseJson(gateEventJson);
        JsonNode sNode = jsonNode.findValue(GatewayEvent.P_S);
        if (Objects.nonNull(sNode)) {
            int s = sNode.asInt();
            HeartbeatInfo.s.set(s);
        }
        JsonNode opNode = jsonNode.findValue(GatewayEvent.P_OP);
        int opcode = opNode.asInt();
        if (OpCode.HELLO.getCode() == opcode) {
            //鉴权连接
            new IdentifyEventHandler(botConfiguration).handle(channel);
        } else if (OpCode.HEARTBEAT_ACK.getCode() == opcode) {
            logger.debug("heartbeat ack...");
        } else if (OpCode.DISPATCH.getCode() == opcode) {
            JsonNode tNode = jsonNode.findValue(GatewayEvent.P_T);
            String t = tNode.asText();
            SubEventType subEventType = SubEventType.find(t);
            String d = jsonNode.get(GatewayEvent.P_D).toString();
            Object eventObj = null;
            if (subEventType != null) {
                eventObj = JsonUtil.fromJson(d, subEventType.getDataClass());
                subEventType.getEventConsumer().accept(eventObj);
            }
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
