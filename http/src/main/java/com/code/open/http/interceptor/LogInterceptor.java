package com.code.open.http.interceptor;

import android.text.TextUtils;
import android.util.Log;

import com.code.open.http.Config;
import com.code.open.http.util.Util;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * ================================================
 * Created by zhaokai on 2017/4/12.
 * Email zhaokai1033@126.com
 * Describe :
 * ================================================
 * 网络请求日志工具
 */

public class LogInterceptor implements Interceptor {

    private static final String TAG = "LogInterceptor";
    private final Config config;
    private final String tag;

    public LogInterceptor(Config config) {
        this.tag = config.tag;
        this.config = config;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        try {
            if (config.isDebug) {
                if (config.isShowRequest)
                    logRequest(request);
                if (config.isShowResponse)
                    response = logResponse(response);
            }
        } catch (Exception e) {
            Log.e(TAG, "log error");
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Response 的日志
     */
    private Response logResponse(Response response) throws IOException {
        Log.w(tag, "=========================response start=========================");
        Response.Builder builder = response.newBuilder();
        Response clone = builder.build();
        Log.w(tag, "url : " + clone.request().url());
        Log.w(tag, "code : " + clone.code());
        Log.w(tag, "protocol : " + clone.protocol());
        if (!TextUtils.isEmpty(clone.message()))
            Log.w(tag, "message : " + clone.message());

        if (config.isShowResponse) {
            ResponseBody body = clone.body();
            if (body != null) {
                MediaType mediaType = body.contentType();
                if (mediaType != null) {
                    Log.w(tag, "responseBody's contentType : " + mediaType.toString());
                    if (isText(mediaType)) {
                        String resp = body.string();
                        if (config.isFormat) {
                            Log.w(tag, "responseBody's content : \n" + Util.jsonFormat(resp));
                        } else {
                            Log.w(tag, "responseBody's content : " + resp);
                        }
                        body = ResponseBody.create(mediaType, resp);
                        return response.newBuilder().body(body).build();
                    } else {
                        Log.w(tag, "responseBody's content : " + " too large too print ");
                    }
                }
            }
        }

        Log.w(tag, "=========================response end=========================");
        return clone;
    }

    /**
     * Request 的日志
     */
    private void logRequest(Request request) {
        if (request == null) return;
        HttpUrl httpUrl = request.url();
        Headers headers = request.headers();
        boolean isHttps = request.isHttps();
        String method = request.method();
        RequestBody requestBody = request.body();
        Log.d(tag, "=========================request start=========================");
        Log.d(tag, "Https:" + isHttps + "\t method:" + method
                + " url:" + (httpUrl == null ? "null" : httpUrl.toString()));
        if (headers != null && headers.size() > 0) {
            Log.d(tag, "Header:\n" + headers.toString());
        }
        if (requestBody != null) {
            MediaType type = requestBody.contentType();
            if (type != null) {
                Log.d(tag, "MediaType:" + type);
                if (isText(type)) {
                    Log.d(tag, "requestBody:" + bodyToString(request));
                } else {
                    Log.d(tag, "requestBody:too large too print");
                }
            }
        }
        Log.d(tag, "=========================request end=========================");
    }

    /**
     * 请求类型 是否是文本
     */
    private boolean isText(MediaType mediaType) {
        if (mediaType.type() != null && mediaType.type().equals("text")) {
            return true;
        }
        if (mediaType.subtype() != null) {
            if (mediaType.subtype().equals("json") ||
                    mediaType.subtype().equals("xml") ||
                    mediaType.subtype().equals("html") ||
                    mediaType.subtype().equals("webviewhtml")
                    )
                return true;
        }
        return false;
    }

    /**
     * 复制请求体转为字符串形式
     */
    private String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "something error when show requestBody.";
        }
    }
}
