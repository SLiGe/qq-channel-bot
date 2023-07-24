package cn.zjiali.bot.core.enums;


/**
 * @author zJiaLi
 * @since 2023-07-19 11:34
 */
public enum EventType {

    /**
     * 频道事件
     */
    GUILDS(1 << 0, new SubEventType[]{SubEventType.GUILD_CREATE, SubEventType.GUILD_UPDATE, SubEventType.GUILD_DELETE, SubEventType.CHANNEL_CREATE, SubEventType.CHANNEL_UPDATE, SubEventType.GUILD_DELETE}),

    GUILD_MEMBERS(1 << 1, new SubEventType[]{SubEventType.GUILD_MEMBER_ADD, SubEventType.GUILD_MEMBER_UPDATE, SubEventType.GUILD_MEMBER_REMOVE}),

    GUILD_MESSAGE_REACTIONS(1 << 10, new SubEventType[]{SubEventType.MESSAGE_REACTION_ADD, SubEventType.MESSAGE_REACTION_REMOVE}),

    DIRECT_MESSAGE(1 << 12, new SubEventType[]{SubEventType.DIRECT_MESSAGE_CREATE, SubEventType.DIRECT_MESSAGE_DELETE}),

    /**
     * 消息事件
     */
    PUBLIC_GUILD_MESSAGES(1 << 30, new SubEventType[]{SubEventType.AT_MESSAGE_CREATE, SubEventType.PUBLIC_MESSAGE_DELETE}),

    ;
    private final int flag;

    private final SubEventType[] subEventType;

    EventType(int flag, SubEventType[] subEventType) {
        this.flag = flag;
        this.subEventType = subEventType;
    }

    public int getFlag() {
        return flag;
    }

    public SubEventType[] getSubEventType() {
        return subEventType;
    }
}
