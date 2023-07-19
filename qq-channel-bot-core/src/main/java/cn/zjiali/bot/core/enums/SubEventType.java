package cn.zjiali.bot.core.enums;

import cn.zjiali.bot.core.event.model.GuildEvent;
import cn.zjiali.bot.core.event.model.MessageEvent;
import cn.zjiali.bot.core.event.model.ReadyEvent;
import cn.zjiali.bot.core.event.processor.PublicGuildMessagesProcessor;
import cn.zjiali.bot.core.event.processor.ReadyEventProcessor;

import java.util.function.Consumer;

/**
 * @author zJiaLi
 * @since 2023-07-19 12:30
 */
public enum SubEventType {

    /**
     * 鉴权成功之后，后台会下发一个 Ready Event
     */
    READY(ReadyEvent.class, e -> ReadyEventProcessor.INSTANCE.process((ReadyEvent) e)),

    /**
     * 发送时机:
     * 机器人被加入到某个频道的时候
     */
    GUILD_CREATE(GuildEvent.class, e -> {
    }),
    /**
     * 发送时机:
     * 频道信息变更
     * 事件内容为变更后的数据
     */
    GUILD_UPDATE(GuildEvent.class, e -> {
    }),
    /**
     * 发送时机:
     * 频道被解散
     * 机器人被移除
     * 事件内容为变更前的数据
     */
    GUILD_DELETE(GuildEvent.class, e -> {
    }),

    /**
     * 发送时机:
     * 用户发送消息，@当前机器人或回复机器人消息时
     * 为保障消息投递的速度，消息顺序我们虽然会尽量有序，但是并不保证是严格有序的，如开发者对消息顺序有严格有序的需求，可以自行缓冲消息事件之后，基于 Message.seq 进行排序
     */
    AT_MESSAGE_CREATE(MessageEvent.class, e -> PublicGuildMessagesProcessor.INSTANCE.processAtMessageCreate((MessageEvent) e)),

    PUBLIC_MESSAGE_DELETE(MessageEvent.class, e -> PublicGuildMessagesProcessor.INSTANCE.processPublicMessageDelete((MessageEvent) e)),
    ;

    private final Class<?> dataClass;
    private final Consumer<Object> eventConsumer;

    public static SubEventType find(String name) {
        SubEventType[] values = SubEventType.values();
        for (SubEventType value : values) {
            if (value.name().equals(name)) {
                return value;
            }
        }
        return null;
    }

    SubEventType(Class<?> dataClass, Consumer<Object> eventConsumer) {
        this.dataClass = dataClass;
        this.eventConsumer = eventConsumer;
    }

    public Class<?> getDataClass() {
        return dataClass;
    }

    public Consumer<Object> getEventConsumer() {
        return eventConsumer;
    }
}
