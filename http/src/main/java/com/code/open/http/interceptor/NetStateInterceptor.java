package com.code.open.http.interceptor;

import android.content.Context;
import android.os.Looper;

import com.code.open.http.Config;
import com.code.open.http.util.ToastUtils;
import com.code.open.http.util.Util;

import java.io.IOException;
import java.net.SocketTimeoutException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * ================================================
 * Created by zhaokai on 2017/4/12.
 * Email zhaokai1033@126.com
 * Describe :
 * ================================================
 */

public class NetStateInterceptor implements Interceptor {

    private final Context mContext;
    private final Config config;

    public NetStateInterceptor(Context context, Config config) {
        this.mContext = context;
        this.config = config;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!Util.isNetConnect(mContext)) {
            Looper.prepare();
            if (!config.onNoConnect()) {
                ToastUtils.showShortToast(mContext, config.msg_no_connect);
            }
            Looper.loop();
            return null;
        }
        Response response = null;
        try {
            response = chain.proceed(chain.request());
        } catch (Exception e) {
            if (e instanceof SocketTimeoutException) {
                Looper.prepare();
                if (!config.onConnectTimeOut()) {
                    ToastUtils.showShortToast(mContext, config.msg_no_timeout);
                }
                Looper.loop();
            }
            e.printStackTrace();
        }
        return response;
    }
}
