package cn.zjiali.bot.test.connect;

import cn.zjiali.bot.core.Bot;
import cn.zjiali.bot.core.BotConfiguration;
import cn.zjiali.bot.core.BotRunner;
import cn.zjiali.bot.core.event.GatewayEventManager;
import cn.zjiali.bot.core.event.listener.MessageEventListener;
import cn.zjiali.bot.core.event.listener.ReadyEventListener;
import cn.zjiali.bot.model.event.MessageEvent;
import cn.zjiali.bot.model.event.ReadyEvent;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zJiaLi
 * @since 2023-07-18 10:12
 */
class BotWebSocketTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    void testConnect() throws InterruptedException {
        String qqRobotAppId = System.getenv("QQ_ROBOT_APP_ID");
        String qqRobotToken = System.getenv("QQ_ROBOT_TOKEN");
        String gatewayUrl = System.getenv("QQ_ROBOT_GATEWAY");
        logger.info("qqRobotAppId :{}", qqRobotAppId);
        logger.info("qqRobotToken :{}", qqRobotToken);
        logger.info("gatewayUrl :{}", gatewayUrl);
        assert qqRobotAppId != null;
        GatewayEventManager gatewayEventManager = new GatewayEventManager();
        gatewayEventManager.subscribe(ReadyEvent.class, new ReadyEventListener());
        gatewayEventManager.subscribe(MessageEvent.class, new MessageEventListener());
        BotConfiguration botConfiguration = new BotConfiguration(qqRobotAppId, qqRobotToken);
        Bot bot = new Bot(gatewayUrl, botConfiguration, gatewayEventManager);
        BotRunner.run(bot);
//        bot.start();
        logger.info("bot online......");
//        bot.join();
        for (int i = 0; i < 20; i++) {
            logger.info("bot {}", i);
        }
//        WebSocketUrlProvider webSocketUrlProvider = new WebSocketUrlProvider(gatewayUrl, botConfiguration);
//        WebSocketClient webSocketClient = new WebSocketClient(webSocketUrlProvider, botConfiguration);
//        webSocketClient.connect();
//        Thread.currentThread().join();
    }
}
