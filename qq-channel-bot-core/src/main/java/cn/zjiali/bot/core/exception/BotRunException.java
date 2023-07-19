package cn.zjiali.bot.core.exception;

/**
 * @author zJiaLi
 * @since 2023-07-19 15:37
 */
public class BotRunException extends RuntimeException{
    public BotRunException(String message) {
        super(message);
    }

    public BotRunException(Throwable cause) {
        super(cause);
    }
}
