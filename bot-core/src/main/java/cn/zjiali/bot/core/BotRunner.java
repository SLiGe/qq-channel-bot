package cn.zjiali.bot.core;


import java.util.concurrent.CountDownLatch;

public class BotRunner {

    private BotRunner() {
    }

    public static void run(Bot bot) {
        bot.start();
        // 等待所有任务完成
        CountDownLatch latch = new CountDownLatch(1);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            bot.close();
            latch.countDown();
        }));
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}