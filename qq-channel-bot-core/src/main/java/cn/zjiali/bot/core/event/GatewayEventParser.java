package cn.zjiali.bot.core.event;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;

/**
 * @author zJiaLi
 * @since 2023-07-18 11:35
 */
public class GatewayEventParser<T> {

    public GatewayEvent<T> parse(String gatewayEventJson) {
        return JSONUtil.toBean(gatewayEventJson, new TypeReference<GatewayEvent<T>>() {
        }.getType(), true);
    }
}
