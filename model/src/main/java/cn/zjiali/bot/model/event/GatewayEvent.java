package cn.zjiali.bot.model.event;

/**
 * @author zJiaLi
 * @since 2023-07-18 11:16
 */
public class GatewayEvent<T> {

    /**
     * op属性 操作代码
     */
    public static final String P_OP = "op";
    /**
     * s属性 消息序列号
     */
    public static final String P_S = "s";
    /**
     * t属性 事件类型
     */
    public static final String P_T = "t";
    /**
     * d属性 事件内容
     */
    public static final String P_D = "d";

    /**
     * 操作代码
     *
     * @see cn.zjiali.bot.core.enums.OpCode
     */
    private int op;
    /**
     * 下行消息都会有一个序列号，标识消息的唯一性，客户端需要再发送心跳的时候，携带客户端收到的最新的s
     */
    private int s;
    /**
     * 事件类型
     */
    private String t;
    /**
     * 事件内容
     */
    private T d;

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        this.op = op;
    }

    public T getD() {
        return d;
    }

    public void setD(T d) {
        this.d = d;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }
}
