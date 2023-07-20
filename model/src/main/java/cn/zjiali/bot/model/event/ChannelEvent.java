
package cn.zjiali.bot.model.event;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ChannelEvent {

    @JsonProperty("guild_id")
    private String guildId;
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("op_user_id")
    private String opUserId;
    @JsonProperty("owner_id")
    private String ownerId;
    @JsonProperty("sub_type")
    private Long subType;
    @JsonProperty("type")
    private Long type;

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Long getSubType() {
        return subType;
    }

    public void setSubType(Long subType) {
        this.subType = subType;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

}
