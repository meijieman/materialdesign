package com.foo.materialdesign.base;

import android.app.Application;

import com.foo.materialdesign.common.CrashHandler;

/**
 * @Desc: TODO
 * @Author: Major
 * @Since: 2016/6/4 9:17
 */
public class App extends Application {

    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        CrashHandler.getInstance().init(this, null);

    }

    public static App getInstance() {
        return app;
    }
}
