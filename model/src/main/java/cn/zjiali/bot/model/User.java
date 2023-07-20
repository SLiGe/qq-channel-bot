package cn.zjiali.bot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 用户对象
 * 用户对象中所涉及的 ID 类数据，都仅在机器人场景流通，与真实的 ID 无关。请不要理解为真实的 ID
 *
 * @author zJiaLi
 * @since 2023-07-19 10:28
 */
public class User {
    /**
     * 用户 id
     */
    private String id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户头像地址
     */
    private String avatar;
    /**
     * 是否是机器人
     */
    private Boolean bot;
    /**
     * union_openid 只有在单独拉取 member 信息的时候才会提供。
     */
    @JsonProperty("union_openid")
    private String unionOpenid;

    /**
     * union_user_account 只有在单独拉取 member 信息的时候才会提供。
     */
    @JsonProperty("union_user_account")
    private String unionUserAccount;

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getBot() {
        return bot;
    }

    public void setBot(Boolean bot) {
        this.bot = bot;
    }

    public String getUnionOpenid() {
        return unionOpenid;
    }

    public void setUnionOpenid(String unionOpenid) {
        this.unionOpenid = unionOpenid;
    }

    public String getUnionUserAccount() {
        return unionUserAccount;
    }

    public void setUnionUserAccount(String unionUserAccount) {
        this.unionUserAccount = unionUserAccount;
    }
}
