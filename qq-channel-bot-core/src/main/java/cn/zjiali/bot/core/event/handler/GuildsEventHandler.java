package cn.zjiali.bot.core.event.handler;

/**
 * @author zJiaLi
 * @since 2023-07-19 14:57
 */
public interface GuildsEventHandler {

    void handleGuildCreate();

    void handleGuildUpdate();

    void handleGuildDelete();

    void handleChannelCreate();

    void handleChannelUpdate();

    void handleChannelDelete();
}
