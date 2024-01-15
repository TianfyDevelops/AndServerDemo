package com.kcst.sendserver.room;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerThreadPool extends ThreadPoolExecutor {

    public ServerThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    private static class ServerThreadFactory implements ThreadFactory {

        private final AtomicInteger atomicInteger = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "ServerThread" + atomicInteger.incrementAndGet());
        }
    }

    public static class Builder {
        private int corePoolSize = Runtime.getRuntime().availableProcessors() + 1;
        private int maximumPoolSize = Runtime.getRuntime().availableProcessors() * 2;
        private long keepAliveTime = 60;
        private TimeUnit unit = TimeUnit.SECONDS;

        private BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(256);

        private ThreadFactory serverThreadFactory = new ServerThreadFactory();
        private RejectedExecutionHandler serverRejectHandler = new AbortPolicy();


        public Builder setCorePoolSize(int corePoolSize) {
            this.corePoolSize = corePoolSize;
            return this;
        }

        public Builder setMaximumPoolSize(int maximumPoolSize) {
            this.maximumPoolSize = maximumPoolSize;
            return this;
        }

        public Builder setKeepAliveTime(long keepAliveTime) {
            this.keepAliveTime = keepAliveTime;
            return this;
        }

        public Builder setUnit(TimeUnit unit) {
            this.unit = unit;
            return this;
        }

        public Builder setBlockingQueue(BlockingQueue<Runnable> blockingQueue) {
            this.blockingQueue = blockingQueue;
            return this;
        }

        public Builder setServerThreadFactory(ServerThreadFactory serverThreadFactory) {
            this.serverThreadFactory = serverThreadFactory;
            return this;
        }

        public Builder setServerRejectHandler(RejectedExecutionHandler serverRejectHandler) {
            this.serverRejectHandler = serverRejectHandler;
            return this;
        }

        public ServerThreadPool build() {
            return new ServerThreadPool(corePoolSize,
                    maximumPoolSize,
                    keepAliveTime,
                    unit,
                    blockingQueue,
                    serverThreadFactory,
                    serverRejectHandler);
        }
    }

}
