package cn.zjiali.bot.core.enums;

import cn.zjiali.bot.model.event.audio.AudioFinishEvent;
import cn.zjiali.bot.model.event.audio.AudioOffMicEvent;
import cn.zjiali.bot.model.event.audio.AudioOnMicEvent;
import cn.zjiali.bot.model.event.audio.AudioStartEvent;
import cn.zjiali.bot.model.event.channel.ChannelCreateEvent;
import cn.zjiali.bot.model.event.channel.ChannelDeleteEvent;
import cn.zjiali.bot.model.event.channel.ChannelUpdateEvent;
import cn.zjiali.bot.model.event.forum.*;
import cn.zjiali.bot.model.event.guild.GuildCreateEvent;
import cn.zjiali.bot.model.event.guild.GuildDeleteEvent;
import cn.zjiali.bot.model.event.guild.GuildEvent;
import cn.zjiali.bot.model.event.guild.GuildUpdateEvent;
import cn.zjiali.bot.model.event.guild.member.GuildMemberAddEvent;
import cn.zjiali.bot.model.event.guild.member.GuildMemberRemoveEvent;
import cn.zjiali.bot.model.event.guild.member.GuildMemberUpdateEvent;
import cn.zjiali.bot.model.event.message.*;
import cn.zjiali.bot.model.event.ReadyEvent;

/**
 * @author zJiaLi
 * @since 2023-07-19 12:30
 */
public enum SubEventType {

    /**
     * 鉴权成功之后，后台会下发一个 Ready Event
     */
    READY(ReadyEvent.class),

    /**
     * 发送时机:
     * 机器人被加入到某个频道的时候
     */
    GUILD_CREATE(GuildCreateEvent.class),
    /**
     * 发送时机:
     * 频道信息变更
     * 事件内容为变更后的数据
     */
    GUILD_UPDATE(GuildUpdateEvent.class),
    /**
     * 发送时机:
     * 频道被解散
     * 机器人被移除
     * 事件内容为变更前的数据
     */
    GUILD_DELETE(GuildDeleteEvent.class),

    /**
     * 发送时机:
     * 用户发送消息，@当前机器人或回复机器人消息时
     * 为保障消息投递的速度，消息顺序我们虽然会尽量有序，但是并不保证是严格有序的，如开发者对消息顺序有严格有序的需求，可以自行缓冲消息事件之后，基于 Message.seq 进行排序
     */
    AT_MESSAGE_CREATE(AtMessageCreateEvent.class),

    PUBLIC_MESSAGE_DELETE(PublicMessageDeleteEvent.class),

    CHANNEL_CREATE(ChannelCreateEvent.class),

    CHANNEL_UPDATE(ChannelUpdateEvent.class),

    CHANNEL_DELETE(ChannelDeleteEvent.class),
    GUILD_MEMBER_ADD(GuildMemberAddEvent.class),
    GUILD_MEMBER_UPDATE(GuildMemberUpdateEvent.class),
    GUILD_MEMBER_REMOVE(GuildMemberRemoveEvent.class),
    MESSAGE_AUDIT_PASS(MessageAuditPassEvent.class),
    MESSAGE_AUDIT_REJECT(MessageAuditRejectEvent.class),
    DIRECT_MESSAGE_CREATE(DirectMessageCreateEvent.class),
    DIRECT_MESSAGE_DELETE(DirectMessageDeleteEvent.class),
    MESSAGE_REACTION_ADD(MessageReactionAddEvent.class),
    MESSAGE_REACTION_REMOVE(MessageReactionRemoveEvent.class),
    AUDIO_START(AudioStartEvent.class),
    AUDIO_FINISH(AudioFinishEvent.class),
    AUDIO_ON_MIC(AudioOnMicEvent.class),
    AUDIO_OFF_MIC(AudioOffMicEvent.class),
    FORUM_THREAD_CREATE(ForumThreadCreateEvent.class),
    FORUM_THREAD_UPDATE(ForumThreadUpdateEvent.class),
    FORUM_THREAD_DELETE(ForumThreadDeleteEvent.class),
    FORUM_POST_CREATE(ForumPostCreateEvent.class),
    FORUM_POST_DELETE(ForumPostDeleteEvent.class),
    FORUM_REPLY_CREATE(ForumReplyCreateEvent.class),
    FORUM_REPLY_DELETE(ForumReplyDeleteEvent.class),
    FORUM_PUBLISH_AUDIT_RESULT(ForumPublishAuditResultEvent.class),
    ;

    private final Class<?> dataClass;


    public static SubEventType find(String name) {
        SubEventType[] values = SubEventType.values();
        for (SubEventType value : values) {
            if (value.name().equals(name)) {
                return value;
            }
        }
        return null;
    }

    SubEventType(Class<?> dataClass) {
        this.dataClass = dataClass;
    }

    public Class<?> getDataClass() {
        return dataClass;
    }

}
