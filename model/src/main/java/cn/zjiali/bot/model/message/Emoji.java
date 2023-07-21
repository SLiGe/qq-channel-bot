package cn.zjiali.bot.model.message;

/**
 * @author zJiaLi
 * @since 2023-07-21 15:33
 */
public class Emoji {
    /**
     * 表情ID，系统表情使用数字为ID，emoji使用emoji本身为id，参考 Emoji 列表
     */
    private String id;

    /**
     * 表情类型
     */
    private Integer type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
