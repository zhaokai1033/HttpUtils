package com.code.open.httpdemo;

import android.app.Application;

import com.code.open.http.HttpUtils;

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
        HttpUtils.init(getApplicationContext(), null);
    }
}
