package com.kcst.sendserver;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.kcst.sendserver.util.Logger;
import com.yanzhenjie.andserver.AndServer;
import com.yanzhenjie.andserver.Server;

import java.util.concurrent.TimeUnit;

/**
 * @author tianfy
 * AndServerMananger
 * 创建AndServerManager，启动AndServer
 */
public class AndServerManager {

    private int port;

    private int timeout;

    private TimeUnit timeUnit;

    private Context context;

    private boolean isStart = false;

    private Server mServer;

    private final String TAG = AndServerManager.class.getSimpleName();


    public AndServerManager(Context context, int port, int timeout, TimeUnit timeUnit) {
        this.context = context;
        this.port = port;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
    }

    /**
     * startServer in android service
     * when restart Server will kill self and restart
     *
     * @param serverListener
     */
    public final void startServer(ServerListener serverListener) {
        if (isStart) {
            stopServer();
        }
        createServer(serverListener);
        startAndServer();
    }

    private void startAndServer() {
        if (!mServer.isRunning()) {
            mServer.startup();
        }
    }

    private void createServer(@Nullable ServerListener serverListener) {
        mServer = AndServer.webServer(context)
                .port(port)
                .timeout(timeout, timeUnit)
                .listener(new Server.ServerListener() {
                    @Override
                    public void onStarted() {
                        isStart = true;
                        Log.d(TAG, "-----------------------AndServer onStarted----------------------");
                        if (serverListener != null)
                            serverListener.onStarted();
                    }

                    @Override
                    public void onStopped() {
                        isStart = false;
                        Log.d(TAG, "-----------------------AndServer onStopped----------------------");
                        if (serverListener != null)
                            serverListener.onStopped();
                    }

                    @Override
                    public void onException(Exception e) {
                        e.printStackTrace();
                        Log.d(TAG, "-----------------------AndServer onException----------------------");
                        Log.d(TAG, "AndServer OnException=" + e.toString());
                        isStart = false;
                        if (serverListener != null)
                            serverListener.onException(e);
                    }
                }).build();
    }

    /**
     * 停止服务
     */
    public final void stopServer() {
        if (mServer.isRunning()) {
            mServer.shutdown();
        }
    }

    public static class Builder {

        private int port;

        private int timeout;

        private TimeUnit timeUnit;

        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setPort(int port) {
            this.port = port;
            return this;
        }

        public Builder setTimeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder setTimeUnit(TimeUnit timeUnit) {
            this.timeUnit = timeUnit;
            return this;
        }

        public AndServerManager build() {
            return new AndServerManager(context, port, timeout, timeUnit);
        }
    }

    public interface ServerListener {

        /**
         * When the server is started.
         */
        void onStarted();

        /**
         * When the server stops running.
         */
        void onStopped();

        /**
         * An error occurred while starting the server.
         */
        void onException(Exception e);
    }

}
