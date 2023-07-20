package cn.zjiali.bot.core.event;

/**
 * 事件监听器
 *
 * @author zJiaLi
 * @since 2023-07-18 11:28
 */
public interface GatewayEventListener<T> {

    void onEvent(T payload);

}
