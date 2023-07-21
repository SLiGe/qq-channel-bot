package cn.zjiali.bot.core.event.listener;

import cn.zjiali.bot.common.util.JsonUtil;
import cn.zjiali.bot.core.event.GatewayEventListener;
import cn.zjiali.bot.model.event.message.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zJiaLi
 * @since 2023-07-20 15:27
 */
public class MessageEventListener implements GatewayEventListener<MessageEvent> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onEvent(MessageEvent payload) {
        logger.debug("message:{}", JsonUtil.toJson(payload));
    }
}
