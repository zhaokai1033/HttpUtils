package com.code.open.http;

/**
 * ================================================
 * Created by zhaokai on 2017/3/17.
 * Email zhaokai1033@126.com
 * Describe :
 * 网络请求参数
 * ================================================
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class Config {
    public String tag = "HttpUtil";
    public int readTimeout = 10;//读取超时
    public int writeTimeout = 10;//写超时
    public int connectTimeout = 10;//连接超时
    public boolean isDebug = true;//是否开启测试模式 true 则输出日志
    public boolean isShowRequest = true;//是否打印请求日志
    public boolean isShowResponse = true;//是否打印返回日志
    public boolean isFormat = false;//是否格式化返回数据
    public boolean isAddNetListener = true;//是否添加网络监听
    public String msg_no_connect = "无网络连接，请联网后重试.";//断网提示语
    public String msg_no_timeout = "网络不给力，请重试.";//网络连接超时提示语

    public Config(boolean isDebug) {
        this.isDebug = isDebug;
    }

    /**
     * 网络请求参数  单位 秒
     *
     * @param connectTimeout 连接超时
     * @param writeTimeout   写超时
     * @param readTimeout    读取超时
     */
    public Config(int connectTimeout, int writeTimeout, int readTimeout, boolean isDebug, String tag) {
        this.isDebug = isDebug;
        this.tag = tag;
        this.connectTimeout = connectTimeout;
        this.writeTimeout = writeTimeout;
        this.readTimeout = readTimeout;
    }

    /**
     * 无网处理
     *
     * @return true 则不提示，false 进行默认提示
     */
    public boolean onNoConnect() {
        return false;
    }

    /**
     * 连接超时处理
     *
     * @return true 则不提示，false 进行默认提示
     */
    public boolean onConnectTimeOut() {
        return false;
    }
}
