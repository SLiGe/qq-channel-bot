package cn.zjiali.bot.core.enums;


/**
 * @author zJiaLi
 * @since 2023-07-19 11:34
 */
public enum EventType {

    /**
     * 频道事件
     */
    GUILDS(1 << 0, new SubEventType[]{SubEventType.GUILD_CREATE, SubEventType.GUILD_UPDATE, SubEventType.GUILD_DELETE}),

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
