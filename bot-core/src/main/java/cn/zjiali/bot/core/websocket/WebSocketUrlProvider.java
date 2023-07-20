package cn.zjiali.bot.core.websocket;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.zjiali.bot.common.util.JsonUtil;
import cn.zjiali.bot.core.BotConfiguration;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSocketUrlProvider {

    private final String gatewayUrl;
    private final BotConfiguration botConfiguration;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public WebSocketUrlProvider(String gatewayUrl, BotConfiguration botConfiguration) {
        this.gatewayUrl = gatewayUrl;
        this.botConfiguration = botConfiguration;
    }


    public String getWebSocketUrl() {
        try (HttpResponse gatewayResponse = HttpUtil.createGet(this.gatewayUrl).auth(this.botConfiguration.authorization()).execute()) {
            String body = gatewayResponse.body();
            JsonNode jsonNode = JsonUtil.parseJson(body);
            JsonNode urlNode = jsonNode.findValue("url");
            String url = urlNode.asText();
            logger.debug("WebSocket url: {}", url);
            return url;
        }
    }
}
