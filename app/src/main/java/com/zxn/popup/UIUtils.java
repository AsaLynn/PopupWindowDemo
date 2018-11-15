package com.zxn.popup;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by zxn on 2018/11/14.
 */
public class UIUtils {

    /**
     * dp尺寸转px尺寸使用
     *
     * @param context
     * @param dpSize
     * @return
     */
    public static float dp2px(Context context, int dpSize) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float density = displayMetrics.density;
        float pxSize = dpSize * density;
        return pxSize;
    }
}
