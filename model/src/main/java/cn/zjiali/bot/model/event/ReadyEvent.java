package cn.zjiali.bot.model.event;

import cn.zjiali.bot.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author zJiaLi
 * @since 2023-07-19 13:09
 */

public class ReadyEvent {

    @JsonProperty("version")
    private Integer version;
    @JsonProperty("session_id")
    private String sessionId;
    @JsonProperty("user")
    private User user;
    @JsonProperty("shard")
    private List<Integer> shard;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Integer> getShard() {
        return shard;
    }

    public void setShard(List<Integer> shard) {
        this.shard = shard;
    }
}
