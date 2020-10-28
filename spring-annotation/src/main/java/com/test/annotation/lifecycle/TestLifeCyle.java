package com.test.annotation.lifecycle;

import org.springframework.context.SmartLifecycle;

public class TestLifeCyle implements SmartLifecycle {
    @Override
    public boolean isAutoStartup() {
        return false;
    }

    @Override
    public void stop(Runnable runnable) {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public int getPhase() {
        return 0;
    }
}
