package cn.zjiali.bot.core;

import cn.zjiali.bot.core.event.GatewayEventManager;
import cn.zjiali.bot.core.event.listener.MessageEventListener;
import cn.zjiali.bot.core.event.listener.ReadyEventListener;
import cn.zjiali.bot.core.websocket.WebSocketClient;
import cn.zjiali.bot.core.websocket.WebSocketUrlProvider;
import cn.zjiali.bot.model.event.MessageEvent;
import cn.zjiali.bot.model.event.ReadyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;

/**
 * @author zJiaLi
 * @since 2023-07-19 15:32
 */
public class Bot {

    private final String gatewayUrl;
    private final BotConfiguration botConfiguration;
    private final GatewayEventManager gatewayEventManager;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Bot(String gatewayUrl, BotConfiguration botConfiguration, GatewayEventManager gatewayEventManager) {
        this.gatewayUrl = gatewayUrl;
        this.botConfiguration = botConfiguration;
        this.gatewayEventManager = gatewayEventManager;
    }

    private WebSocketClient webSocketClient;

    public void start() {
        Thread botThread = new Thread(() -> {
            WebSocketUrlProvider webSocketUrlProvider = new WebSocketUrlProvider(gatewayUrl, botConfiguration);
            this.webSocketClient = new WebSocketClient(webSocketUrlProvider, botConfiguration, gatewayEventManager);
            try {
                this.webSocketClient.connect();
            } catch (URISyntaxException | InterruptedException e) {
                logger.warn("Interrupted!", e);
                Thread.currentThread().interrupt();
            }

        });
        botThread.start();
    }

    public void close() {
        if (this.webSocketClient != null) {
            this.webSocketClient.close();
        }
    }
}
