package cn.zjiali.bot.model.forum.thread;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ForumAuditResult {
    /**
     * guild_id	string	频道ID
     */
    @JsonProperty("guild_id")
    private String guildId;

    /**
     * channel_id	string	子频道ID
     */
    @JsonProperty("channel_id")
    private String channelId;

    /**
     * author_id	string	作者ID
     */
    @JsonProperty("author_id")
    private String authorId;

    /**
     * thread_id	string	主题ID
     */
    @JsonProperty("thread_id")
    private String threadId;

    /**
     * post_id	string	帖子ID
     */
    @JsonProperty("post_id")
    private String postId;

    /**
     * reply_id	string	回复ID
     */
    @JsonProperty("reply_id")
    private String replyId;

    /**
     * type	uint32	AuditType审核的类型
     */
    @JsonProperty("type")
    private Integer type;

    /**
     * result	uint32	审核结果. 0:成功 1:失败
     */
    @JsonProperty("result")
    private Integer result;

    /**
     * err_msg	string	result不为0时错误信息
     */
    @JsonProperty("err_msg")
    private String errMsg;

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}