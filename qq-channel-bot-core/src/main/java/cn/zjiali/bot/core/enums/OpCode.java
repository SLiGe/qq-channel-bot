package cn.zjiali.bot.core.enums;

/**
 * @author zJiaLi
 * @since 2023-07-19 09:26
 */
public enum OpCode {

    /**
     * 0	Dispatch	Receive	服务端进行消息推送
     */
    DISPATCH(0, Object.class),
    /**
     * 1	Heartbeat	Send/Receive	客户端或服务端发送心跳
     */
    HEARTBEAT(1, Void.class),
    /**
     * 2	Identify	Send	客户端发送鉴权
     */
    IDENTIFY(2, Object.class),
    /**
     * 6	Resume	Send	客户端恢复连接
     */
    RESUME(6, Object.class),
    /**
     * 7	Reconnect	Receive	服务端通知客户端重新连接
     */
    RECONNECT(7, Object.class),
    /**
     * 9	Invalid Session	Receive	当identify或resume的时候，如果参数有错，服务端会返回该消息
     */
    INVALID_SESSION(9, Object.class),
    /**
     * 10	Hello	Receive	当客户端与网关建立ws连接之后，网关下发的第一条消息
     */
    HELLO(10, Object.class),
    /**
     * 11	Heartbeat ACK	Receive/Reply	当发送心跳成功之后，就会收到该消息
     */
    HEARTBEAT_ACK(11, Object.class),
    /**
     * 12	HTTP Callback ACK	Reply	仅用于 http 回调模式的回包，代表机器人收到了平台推送的数据
     */
    HTTP_CALLBACK_ACK(12, Object.class),
    ;
    private final int code;

    private final Class<?> dataClass;

    OpCode(int code, Class<?> dataClass) {
        this.code = code;
        this.dataClass = dataClass;
    }

    public int getCode() {
        return code;
    }

    public Class<?> getDataClass() {
        return dataClass;
    }
}
