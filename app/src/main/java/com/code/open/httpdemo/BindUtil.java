package com.code.open.httpdemo;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;

/**
 * ================================================
 * Created by zhaokai on 2017/3/23.
 * Email zhaokai1033@126.com
 * Describe :
 * ================================================
 */

public class BindUtil {

    private static final String TAG = "BindUtil";

    /**
     * requireAll 默认为true 如果不设置怎三个必须同时具备才可以正常编译
     */
    //    @BindingAdapter("imageUrl")
    //    @BindingAdapter({"imageUrl", "error", "placeHolder"})
    @BindingAdapter(value = {"imageUrl", "error", "placeHolder"}, requireAll = false)
    public static void loadImage(ImageView view, String newValue, Drawable error, Drawable placeHolder) {
        Log.d(TAG, "load image ->url：" + newValue);
        DrawableTypeRequest<String> request = Glide.with(view.getContext()).load(newValue);

        if (error != null) {
            request.error(error);
        }
        if (placeHolder != null) {
            request.placeholder(placeHolder);
        }
        request.into(view);
    }
}
