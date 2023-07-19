package cn.zjiali.bot.core.event.processor;

import cn.zjiali.bot.core.event.model.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zJiaLi
 * @since 2023-07-19 14:57
 */
public class PublicGuildMessagesProcessor {

    public static final PublicGuildMessagesProcessor INSTANCE = new PublicGuildMessagesProcessor();

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void processAtMessageCreate(MessageEvent messageEvent) {
        logger.debug("User: {} ,Content: {}", messageEvent.getAuthor().getUsername(), messageEvent.getContent());
    }

    public void processPublicMessageDelete(MessageEvent messageEvent) {

    }

}
