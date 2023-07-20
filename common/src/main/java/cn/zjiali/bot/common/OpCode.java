package cn.zjiali.bot.common;

/**
 * @author zJiaLi
 * @since 2023-07-19 09:26
 */
public enum OpCode {

    /**
     * 0	Dispatch	Receive	服务端进行消息推送
     */
    DISPATCH(0),
    /**
     * 1	Heartbeat	Send/Receive	客户端或服务端发送心跳
     */
    HEARTBEAT(1),
    /**
     * 2	Identify	Send	客户端发送鉴权
     */
    IDENTIFY(2),
    /**
     * 6	Resume	Send	客户端恢复连接
     */
    RESUME(6),
    /**
     * 7	Reconnect	Receive	服务端通知客户端重新连接
     */
    RECONNECT(7),
    /**
     * 9	Invalid Session	Receive	当identify或resume的时候，如果参数有错，服务端会返回该消息
     */
    INVALID_SESSION(9),
    /**
     * 10	Hello	Receive	当客户端与网关建立ws连接之后，网关下发的第一条消息
     */
    HELLO(10),
    /**
     * 11	Heartbeat ACK	Receive/Reply	当发送心跳成功之后，就会收到该消息
     */
    HEARTBEAT_ACK(11),
    /**
     * 12	HTTP Callback ACK	Reply	仅用于 http 回调模式的回包，代表机器人收到了平台推送的数据
     */
    HTTP_CALLBACK_ACK(12),
    ;
    private final int code;


    OpCode(int code) {
        this.code = code;
    }

    public static OpCode fromValue(int code) {
        for (OpCode opcode : values()) {
            if (opcode.getCode() == code) {
                return opcode;
            }
        }
        throw new IllegalArgumentException("Invalid OpCode: " + code);
    }

    public int getCode() {
        return code;
    }

}
