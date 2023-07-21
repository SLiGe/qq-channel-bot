package cn.zjiali.bot.core.websocket.handler;

import cn.zjiali.bot.common.OpCode;
import cn.zjiali.bot.common.util.JsonUtil;
import cn.zjiali.bot.core.BotConfiguration;
import cn.zjiali.bot.core.enums.SubEventType;
import cn.zjiali.bot.core.event.GatewayEventManager;
import cn.zjiali.bot.core.event.handler.IdentifyEventHandler;
import cn.zjiali.bot.model.event.GatewayEvent;
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

import java.util.Optional;

public class WebSocketClientHandler extends SimpleChannelInboundHandler<Object> {
    private final WebSocketClientHandshaker handshaker;
    private ChannelPromise handshakeFuture;
    private final BotConfiguration botConfiguration;
    private final GatewayEventManager gatewayEventManager;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public WebSocketClientHandler(WebSocketClientHandshaker handshaker, BotConfiguration botConfiguration, GatewayEventManager gatewayEventManager) {
        this.handshaker = handshaker;
        this.botConfiguration = botConfiguration;
        this.gatewayEventManager = gatewayEventManager;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext chx, Object msg) throws Exception {
        try {
            Channel channel = chx.channel();
            if (!this.handshaker.isHandshakeComplete()) {
                handshaker.finishHandshake(channel, (FullHttpResponse) msg);
                logger.debug("connected!");
                handshakeFuture.setSuccess();
                return;
            }
            if (msg instanceof WebSocketFrame frame) {
                if (frame instanceof TextWebSocketFrame textFrame) {
                    String jsonData = textFrame.text();
                    logger.debug("received message: {}", jsonData);
                    handleGatewayEvent(jsonData, channel);
                } else if (frame instanceof CloseWebSocketFrame) {
                    logger.debug("received closing");
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

    private void handleGatewayEvent(String gateEventJson, Channel channel) {
        JsonNode jsonNode = JsonUtil.parseJson(gateEventJson);
        Optional.ofNullable(jsonNode.findValue(GatewayEvent.P_S))
                .map(JsonNode::asInt)
                .ifPresent(HeartbeatInfo.s::set);
        Optional.ofNullable(jsonNode.findValue(GatewayEvent.P_OP)).map(JsonNode::asInt)
                .ifPresent(opcode -> {
                    switch (OpCode.fromValue(opcode)) {
                        case HELLO ->
                                new IdentifyEventHandler(botConfiguration, this.gatewayEventManager.eventTypeSet()).handle(channel);
                        case HEARTBEAT_ACK -> logger.debug("gateway heartbeat ack...");
                        case DISPATCH -> doDispatch(jsonNode);
                        default -> logger.warn("unknown opcode: {}", opcode);
                    }
                });
    }

    private void doDispatch(JsonNode jsonNode) {
        JsonNode tNode = jsonNode.findValue(GatewayEvent.P_T);
        String t = tNode.asText();
        SubEventType subEventType = SubEventType.find(t);
        if (this.gatewayEventManager.inSub(subEventType)) {
            String d = jsonNode.get(GatewayEvent.P_D).toString();
            if (subEventType != null && d != null) {
                Class<?> eventClassType = subEventType.getDataClass();
                Object eventObj = JsonUtil.fromJson(d, eventClassType);
                this.gatewayEventManager.notifyListeners(eventClassType, eventObj);
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
