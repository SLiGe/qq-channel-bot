package cn.zjiali.bot.model.forum.thread;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ForumThreadInfo {
    /**
     * thread_id	string	主帖ID
     */
    private String threadId;
    /**
     * title	string	帖子标题
     */
    private String title;
    /**
     * content	string	帖子内容
     */
    private String content;
    /**
     * date_time	ISO8601 timestamp	发表时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private LocalDateTime dateTime;

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}