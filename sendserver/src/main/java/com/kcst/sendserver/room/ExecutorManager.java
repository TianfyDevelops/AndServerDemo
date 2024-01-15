package com.kcst.sendserver.room;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @author Tianfy
 * 线程管理类
 */
public class ExecutorManager {
    private static volatile ExecutorManager INSTANCE;

    public static ExecutorManager getInstance() {
        if (INSTANCE == null) {
            synchronized (ExecutorManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ExecutorManager();
                }
            }
        }
        return INSTANCE;
    }

    private ExecutorManager() {
    }

    private ExecutorService executorService = new ServerThreadPool.Builder().build();

    public <T> T submit(Callable<T> callable) {
        Future<T> submit = executorService.submit(callable);
        try {
            return submit.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void submit(Runnable runnable) {
        executorService.submit(runnable);
    }
    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

}
