package cn.zjiali.bot.core.websocket;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.zjiali.bot.core.BotConfiguration;
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
            JSONObject urlObject = JSONUtil.parseObj(body);
            String url = urlObject.getStr("url");
            logger.debug("WebSocket url: {}", url);
            return url;
        }
    }
}
