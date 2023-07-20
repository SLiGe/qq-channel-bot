package cn.zjiali.bot.model.event;

import java.util.Map;

/**
 * @author zJiaLi
 * @since 2023-07-18 11:17
 */
public class IdentifyEvent {
    private String token;

    private Integer intents;

    private Integer[] shard;

    private Map<String, Object> properties;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getIntents() {
        return intents;
    }

    public void setIntents(Integer intents) {
        this.intents = intents;
    }

    public Integer[] getShard() {
        return shard;
    }

    public void setShard(Integer[] shard) {
        this.shard = shard;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
