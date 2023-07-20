package cn.zjiali.bot.core.event.handler;

import cn.zjiali.bot.common.OpCode;
import cn.zjiali.bot.common.util.JsonUtil;
import cn.zjiali.bot.core.BotConfiguration;
import cn.zjiali.bot.core.enums.EventType;
import cn.zjiali.bot.model.event.GatewayEvent;
import cn.zjiali.bot.model.event.IdentifyEvent;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.List;
import java.util.Set;

/**
 * @author zJiaLi
 * @since 2023-07-19 14:34
 */
public class IdentifyEventHandler {

    private final BotConfiguration botConfiguration;
    private final Set<EventType> eventTypeSet;

    public IdentifyEventHandler(BotConfiguration botConfiguration, Set<EventType> eventTypeSet) {
        this.botConfiguration = botConfiguration;
        this.eventTypeSet = eventTypeSet;
    }

    public void handle(Channel channel) {
        IdentifyEvent identifyEvent = new IdentifyEvent();
        identifyEvent.setToken(botConfiguration.authorization());
        int intents = 0;
        for (EventType eventType : eventTypeSet) {
            intents = intents | eventType.getFlag();
        }
        identifyEvent.setIntents(intents);
        GatewayEvent<IdentifyEvent> gatewayEvent = new GatewayEvent<>();
        gatewayEvent.setD(identifyEvent);
        gatewayEvent.setOp(OpCode.IDENTIFY.getCode());
        channel.writeAndFlush(new TextWebSocketFrame(JsonUtil.toJson(gatewayEvent)));
    }
}
