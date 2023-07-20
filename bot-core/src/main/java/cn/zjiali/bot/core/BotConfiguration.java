package cn.zjiali.bot.core;

/**
 * @author zJiaLi
 * @since 2023-07-17 11:26
 */
public class BotConfiguration {

    private String appId;

    private String token;


    public String authorization() {
        return "Bot " + this.appId + "." + this.token;
    }

    public BotConfiguration(String appId, String token) {
        this.appId = appId;
        this.token = token;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
