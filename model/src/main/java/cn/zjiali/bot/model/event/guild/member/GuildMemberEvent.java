package cn.zjiali.bot.model.event.guild.member;

import cn.zjiali.bot.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author zJiaLi
 * @since 2023-07-21 15:16
 */
public class GuildMemberEvent {

    @JsonProperty("guild_id")
    private String guildId;
    @JsonProperty("joined_at")
    private String joinedAt;
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

    public String getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(String joinedAt) {
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
