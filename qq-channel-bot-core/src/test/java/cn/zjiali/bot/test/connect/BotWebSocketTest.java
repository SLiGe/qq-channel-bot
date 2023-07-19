package cn.zjiali.bot.test.connect;

import cn.zjiali.bot.core.Bot;
import cn.zjiali.bot.core.BotConfiguration;
import cn.zjiali.bot.core.enums.EventType;
import cn.zjiali.bot.core.websocket.WebSocketClient;
import cn.zjiali.bot.core.websocket.WebSocketUrlProvider;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.util.List;

/**
 * @author zJiaLi
 * @since 2023-07-18 10:12
 */
class BotWebSocketTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    void testConnect() throws URISyntaxException, InterruptedException {
        String qqRobotAppId = System.getenv("QQ_ROBOT_APP_ID");
        String qqRobotToken = System.getenv("QQ_ROBOT_TOKEN");
        String gatewayUrl = System.getenv("QQ_ROBOT_GATEWAY");
        logger.info("qqRobotAppId :{}", qqRobotAppId);
        logger.info("qqRobotToken :{}", qqRobotToken);
        logger.info("gatewayUrl :{}", gatewayUrl);
        assert qqRobotAppId != null;
        BotConfiguration botConfiguration = new BotConfiguration(qqRobotAppId, qqRobotToken);
        new Bot(gatewayUrl, botConfiguration, List.of(EventType.GUILDS, EventType.GUILDS)).start();
//        WebSocketUrlProvider webSocketUrlProvider = new WebSocketUrlProvider(gatewayUrl, botConfiguration);
//        WebSocketClient webSocketClient = new WebSocketClient(webSocketUrlProvider, botConfiguration);
//        webSocketClient.connect();
        Thread.currentThread().join();
    }
}
