package cn.zjiali.bot.core.event;

/**
 * @author zJiaLi
 * @since 2023-07-18 11:16
 */
public class GatewayEvent<T> {

    private int op;
    private T d;
    private int s;
    private String t;

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
