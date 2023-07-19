package cn.zjiali.bot.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 成员对象
 *
 * @author zJiaLi
 * @since 2023-07-19 10:28
 */
public class Member {
    /**
     * 用户的频道基础信息，只有成员相关接口中会填充此信息
     */
    private User user;
    /**
     * 用户的昵称
     */
    private String nick;
    /**
     * 用户在频道内的身份组ID, 默认值可参考DefaultRoles
     */
    private List<String> roles;
    /**
     * 用户加入频道的时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @JsonProperty("joined_at")
    private LocalDateTime joinedAt;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }
}
