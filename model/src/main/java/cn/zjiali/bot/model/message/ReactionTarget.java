package cn.zjiali.bot.model.message;

/**
 * @author zJiaLi
 * @since 2023-07-21 15:31
 */
public class ReactionTarget {
    /**
     * 表态对象ID
     */
    private String id;

    /**
     * 表态对象类型
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
