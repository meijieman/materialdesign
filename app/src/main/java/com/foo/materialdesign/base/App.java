package com.foo.materialdesign.base;

import android.app.Application;

import com.litesuits.orm.LiteOrm;
import com.major.base.crash.CrashHandler;

/**
 * @Desc: TODO
 * @Author: Major
 * @Since: 2016/6/4 9:17
 */
public class App extends Application {

    private static App app;
    private static LiteOrm liteOrm;

    public static LiteOrm getLiteOrm() {
        return liteOrm;
    }

    public static App getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        CrashHandler.getInstance().init(this, getFilesDir().toString(), true);

        if (liteOrm == null) {
            liteOrm = LiteOrm.newSingleInstance(this, "liteorm.db");
        }
        liteOrm.setDebugged(true); // open the log
    }
}
