package cn.zjiali.bot.core.event.handler;

import cn.zjiali.bot.core.BotConfiguration;
import cn.zjiali.bot.core.enums.EventType;
import cn.zjiali.bot.core.enums.OpCode;
import cn.zjiali.bot.core.event.EventConfig;
import cn.zjiali.bot.core.event.model.GatewayEvent;
import cn.zjiali.bot.core.event.model.IdentifyEvent;
import cn.zjiali.bot.core.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.List;

/**
 * @author zJiaLi
 * @since 2023-07-19 14:34
 */
public class IdentifyEventHandler {

    private final BotConfiguration botConfiguration;

    public IdentifyEventHandler(BotConfiguration botConfiguration) {
        this.botConfiguration = botConfiguration;
    }

    public void handle(Channel channel) throws JsonProcessingException {
        IdentifyEvent identifyEvent = new IdentifyEvent();
        identifyEvent.setToken(botConfiguration.authorization());
        int intents = 0;
        List<EventType> eventTypeList = EventConfig.getEventTypeList();
        for (EventType eventType : eventTypeList) {
            intents = intents | eventType.getFlag();
        }
        identifyEvent.setIntents(intents);
        GatewayEvent<IdentifyEvent> gatewayEvent = new GatewayEvent<>();
        gatewayEvent.setD(identifyEvent);
        gatewayEvent.setOp(OpCode.IDENTIFY.getCode());
        channel.writeAndFlush(new TextWebSocketFrame(JsonUtil.toJson(gatewayEvent)));
    }
}
