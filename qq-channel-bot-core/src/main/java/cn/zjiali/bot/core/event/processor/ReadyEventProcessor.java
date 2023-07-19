package cn.zjiali.bot.core.event.processor;

import cn.zjiali.bot.core.event.model.ReadyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zJiaLi
 * @since 2023-07-19 15:17
 */
public class ReadyEventProcessor {
    public static final ReadyEventProcessor INSTANCE = new ReadyEventProcessor();
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void process(ReadyEvent readyEvent) {
        logger.debug("WebSocket Ready: {}", readyEvent);
    }
}
