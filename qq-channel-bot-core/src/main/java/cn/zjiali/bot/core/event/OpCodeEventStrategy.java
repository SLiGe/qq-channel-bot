package cn.zjiali.bot.core.event;

import java.util.function.Function;

/**
 * @author zJiaLi
 * @since 2023-07-18 11:32
 */
public enum OpCodeEventStrategy {

    ;

    private int opcode;

    private Function<String,GatewayEvent<?>> parseFun;

    private GatewayEventHandler<?> gatewayEventHandler;
}
