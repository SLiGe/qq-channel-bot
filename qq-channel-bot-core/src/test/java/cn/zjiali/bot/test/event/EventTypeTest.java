package cn.zjiali.bot.test.event;

import cn.zjiali.bot.core.enums.EventType;
import org.junit.jupiter.api.Test;

/**
 * @author zJiaLi
 * @since 2023-07-19 11:38
 */
class EventTypeTest {

    @Test
    void testGetEventType() {
        for (EventType value : EventType.values()) {
            System.out.println(value.name());
        }
    }
}
