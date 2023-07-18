package cn.zjiali.bot.core.event;

/**
 * @author zJiaLi
 * @since 2023-07-18 11:28
 */
public interface GatewayEventHandler<T> {

    int opcode();

    void handle(GatewayEvent<T> gatewayEvent);

}
