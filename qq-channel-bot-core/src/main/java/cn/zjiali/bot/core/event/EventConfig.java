package cn.zjiali.bot.core.event;

import cn.zjiali.bot.core.enums.EventType;

import java.util.List;

/**
 * @author zJiaLi
 * @since 2023-07-19 15:45
 */
public class EventConfig {

    private EventConfig() {
    }

    private static List<EventType> eventTypeList;

    public static void setEventTypeList(List<EventType> eventTypeList) {
        EventConfig.eventTypeList = eventTypeList;
    }

    public static List<EventType> getEventTypeList() {
        return eventTypeList;
    }
}
