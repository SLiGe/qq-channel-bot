package cn.zjiali.bot.core;

import cn.zjiali.bot.core.enums.EventType;
import cn.zjiali.bot.core.event.EventConfig;
import cn.zjiali.bot.core.websocket.WebSocketClient;
import cn.zjiali.bot.core.websocket.WebSocketUrlProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.util.List;

/**
 * @author zJiaLi
 * @since 2023-07-19 15:32
 */
public class Bot {

    private final String gatewayUrl;
    private final BotConfiguration botConfiguration;
    private final List<EventType> eventTypeList;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Bot(String gatewayUrl, BotConfiguration botConfiguration, List<EventType> eventTypeList) {
        this.gatewayUrl = gatewayUrl;
        this.botConfiguration = botConfiguration;
        this.eventTypeList = eventTypeList;
    }

    public void start() {
        EventConfig.setEventTypeList(eventTypeList);
        WebSocketUrlProvider webSocketUrlProvider = new WebSocketUrlProvider(gatewayUrl, botConfiguration);
        WebSocketClient webSocketClient = new WebSocketClient(webSocketUrlProvider, botConfiguration);
        try {
            webSocketClient.connect();
        } catch (URISyntaxException | InterruptedException e) {
            logger.warn("Interrupted!", e);
            Thread.currentThread().interrupt();
        }
    }
}
