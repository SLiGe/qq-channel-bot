package cn.zjiali.bot.core.event.listener;

import cn.zjiali.bot.common.util.JsonUtil;
import cn.zjiali.bot.core.event.GatewayEventListener;
import cn.zjiali.bot.model.event.ReadyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zJiaLi
 * @since 2023-07-20 11:32
 */
public class ReadyEventListener implements GatewayEventListener<ReadyEvent> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onEvent(ReadyEvent payload) {
        String json = JsonUtil.toJson(payload);
        logger.debug("ready: {}", json);
    }
}
