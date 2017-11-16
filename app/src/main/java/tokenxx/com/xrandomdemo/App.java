package tokenxx.com.xrandomdemo;

import android.app.Application;

/**
 * Created by wangzheng on 2017/11/14.
 */

public class App extends Application {
    public static App app;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static App getApp(){
        return app;
    }


}
