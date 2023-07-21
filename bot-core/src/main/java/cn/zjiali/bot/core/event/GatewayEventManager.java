package cn.zjiali.bot.core.event;

import cn.zjiali.bot.core.enums.EventType;
import cn.zjiali.bot.core.enums.SubEventType;

import java.util.*;

/**
 * @author zJiaLi
 * @since 2023-07-20 10:38
 */
public class GatewayEventManager {

    private final Map<Class<?>, List<GatewayEventListener<?>>> eventListeners = new HashMap<>();
    private ExceptionHandler exceptionHandler = e -> {
    };

    /**
     * 订阅事件
     *
     * @param eventType 事件类型
     * @param listener  监听器
     * @param <T>       事件类型
     */
    public <T> void subscribe(Class<T> eventType, GatewayEventListener<T> listener) {
        // 添加事件监听器到对应类型的监听器列表中
        List<GatewayEventListener<?>> listeners = eventListeners.computeIfAbsent(eventType, k -> new ArrayList<>());
        listeners.add(listener);
    }

    /**
     * 取消订阅事件
     *
     * @param eventType 事件类型
     * @param listener  监听器
     * @param <T>       事件类型
     */
    public <T> void unsubscribe(Class<T> eventType, GatewayEventListener<T> listener) {
        // 从对应类型的监听器列表中移除事件监听器
        List<GatewayEventListener<?>> listeners = eventListeners.get(eventType);
        if (listeners != null) {
            listeners.remove(listener);
        }
    }

    public void exceptionHandler(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    @SuppressWarnings("unchecked")
    public <T> void notifyListeners(Class<T> eventType, Object eventObj) {
        // 获取对应类型的事件监听器列表
        List<GatewayEventListener<?>> listeners = this.eventListeners.getOrDefault(eventType, Collections.emptyList());
        // 通知所有事件监听器
        for (GatewayEventListener<?> listener : listeners) {
            GatewayEventListener<T> eventListener = (GatewayEventListener<T>) listener;
            T event = eventType.cast(eventObj);
            try {
                eventListener.onEvent(event);
            } catch (Exception e) {
                this.exceptionHandler.handleException(e);
            }
        }
    }

    public Set<EventType> eventTypeSet() {
        Set<Class<?>> eventKeySet = this.eventListeners.keySet();
        Set<EventType> eventTypeSet = new HashSet<>();
        for (EventType eventType : EventType.values()) {
            if (eventTypeSet.contains(eventType)) continue;
            for (SubEventType subEventType : eventType.getSubEventType()) {
                if (eventKeySet.contains(subEventType.getDataClass())) {
                    eventTypeSet.add(eventType);
                }
            }
        }
        return eventTypeSet;
    }

}
