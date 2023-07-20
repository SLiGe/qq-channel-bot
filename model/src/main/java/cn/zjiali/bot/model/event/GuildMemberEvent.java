package cn.zjiali.bot.model.event;

import cn.zjiali.bot.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zJiaLi
 * @since 2023-07-19 09:56
 */

public class GuildMemberEvent {

    @JsonProperty("guild_id")
    private String guildId;
    @JsonProperty("joined_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private LocalDateTime joinedAt;
    @JsonProperty("nick")
    private String nick;
    @JsonProperty("op_user_id")
    private String opUserId;
    @JsonProperty("roles")
    private List<String> roles;
    @JsonProperty("user")
    private User user;

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
