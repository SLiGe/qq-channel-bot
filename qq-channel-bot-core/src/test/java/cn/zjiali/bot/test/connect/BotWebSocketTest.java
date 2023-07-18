package cn.zjiali.bot.test.connect;

import cn.zjiali.bot.core.BotConfiguration;
import cn.zjiali.bot.core.WebSocketClient;
import cn.zjiali.bot.core.WebSocketUrlProvider;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;

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
        WebSocketUrlProvider webSocketUrlProvider = new WebSocketUrlProvider(gatewayUrl, botConfiguration);
        WebSocketClient webSocketClient = new WebSocketClient(webSocketUrlProvider);
        webSocketClient.connect();
        Thread.currentThread().join();
    }
}
