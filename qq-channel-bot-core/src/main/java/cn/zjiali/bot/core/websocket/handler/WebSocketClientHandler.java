package cn.zjiali.bot.core.websocket.handler;

import cn.zjiali.bot.core.BotConfiguration;
import cn.zjiali.bot.core.enums.EventType;
import cn.zjiali.bot.core.enums.OpCode;
import cn.zjiali.bot.core.enums.SubEventType;
import cn.zjiali.bot.core.event.model.GatewayEvent;
import cn.zjiali.bot.core.event.model.IdentifyEvent;
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
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final BotConfiguration botConfiguration;

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
        JsonNode sNode = jsonNode.findValue("s");
        if (Objects.nonNull(sNode)) {
            int s = sNode.asInt();
            HeartbeatInfo.s.set(s);
        }
        JsonNode opNode = jsonNode.findValue("op");
        int opcode = opNode.asInt();
        if (OpCode.HELLO.getCode() == opcode) {
            //鉴权连接
            IdentifyEvent identifyEvent = new IdentifyEvent();
            identifyEvent.setToken(botConfiguration.authorization());
            identifyEvent.setIntents(EventType.PUBLIC_GUILD_MESSAGES.getFlag());
            GatewayEvent<IdentifyEvent> gatewayEvent = new GatewayEvent<>();
            gatewayEvent.setD(identifyEvent);
            gatewayEvent.setOp(OpCode.IDENTIFY.getCode());
            channel.writeAndFlush(new TextWebSocketFrame(JsonUtil.toJson(gatewayEvent)));
        } else if (OpCode.HEARTBEAT_ACK.getCode() == opcode) {
            logger.debug("heartbeat ack/...");
        } else if (OpCode.DISPATCH.getCode() == opcode) {
            JsonNode tNode = jsonNode.findValue("t");
            String t = tNode.asText();
            SubEventType subEventType = SubEventType.find(t);
            String d = jsonNode.get("d").toString();
            Object eventObj = JsonUtil.fromJson(d, subEventType.getDataClass());
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
