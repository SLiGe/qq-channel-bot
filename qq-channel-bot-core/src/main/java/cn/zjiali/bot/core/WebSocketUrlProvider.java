package cn.zjiali.bot.core;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class WebSocketUrlProvider {

    private final String gatewayUrl;

    private final BotConfiguration botConfiguration;

    public WebSocketUrlProvider(String gatewayUrl, BotConfiguration botConfiguration) {
        this.gatewayUrl = gatewayUrl;
        this.botConfiguration = botConfiguration;
    }


    public String getWebSocketUrl() {
        try (HttpResponse gatewayResponse = HttpUtil.createGet(this.gatewayUrl).auth(this.botConfiguration.authorization()).execute()) {
            String body = gatewayResponse.body();
            JSONObject urlObject = JSONUtil.parseObj(body);
            return urlObject.getStr("url");
        }
    }
}
