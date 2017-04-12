package com.code.open.httpdemo;

import android.app.Application;

import com.code.open.http.Config;
import com.code.open.http.HttpHelper;

/**
 * ================================================
 * Created by zhaokai on 2017/3/28.
 * Email zhaokai1033@126.com
 * Describe :
 * ================================================
 */

public class SampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Config config = new Config(true);
        config.isFormat = true;
        HttpHelper.init(getApplicationContext(), config);
    }
}
