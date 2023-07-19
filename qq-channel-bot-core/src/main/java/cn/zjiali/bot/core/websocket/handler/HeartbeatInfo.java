package cn.zjiali.bot.core.websocket.handler;

import java.util.concurrent.atomic.AtomicReference;

public class HeartbeatInfo {

    private HeartbeatInfo(){}
    public static final AtomicReference<Integer> s = new AtomicReference<>(null);

}