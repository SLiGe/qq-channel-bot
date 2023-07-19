package cn.zjiali.bot.core.model.message;

import java.util.List;

/**
 * @author zJiaLi
 * @since 2023-07-19 10:57
 */
public class MessageArkKv {
    private String key;

    private String value;

    private List<MessageArkObj> obj;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<MessageArkObj> getObj() {
        return obj;
    }

    public void setObj(List<MessageArkObj> obj) {
        this.obj = obj;
    }
}
