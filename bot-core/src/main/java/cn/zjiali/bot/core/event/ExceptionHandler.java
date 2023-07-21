package cn.zjiali.bot.core.event;

/**
 * @author zJiaLi
 * @since 2023-07-21 09:15
 */
@FunctionalInterface
public interface ExceptionHandler {
    void handleException(Throwable exception);
}
