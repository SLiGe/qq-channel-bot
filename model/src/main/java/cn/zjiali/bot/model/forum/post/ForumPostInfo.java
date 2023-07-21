package cn.zjiali.bot.model.forum.post;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ForumPostInfo {
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
     * content	string	帖子内容
     */
    @JsonProperty("content")
    private String content;

    /**
     * date_time	string	评论时间
     */
    @JsonProperty("date_time")
    private String dateTime;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}