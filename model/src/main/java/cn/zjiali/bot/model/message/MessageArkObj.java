package cn.zjiali.bot.model.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author zJiaLi
 * @since 2023-07-19 10:57
 */
public class MessageArkObj {

    @JsonProperty("obj_kv")
    private List<MessageArkObjKv> objKv;

    public List<MessageArkObjKv> getObjKv() {
        return objKv;
    }

    public void setObjKv(List<MessageArkObjKv> objKv) {
        this.objKv = objKv;
    }
}
