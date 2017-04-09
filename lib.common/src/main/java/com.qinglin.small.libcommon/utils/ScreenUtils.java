package com.qinglin.small.libcommon.utils;


import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * 从7.8.0开始，这个类废弃<br/>
 * 新替代类{@link com.autohome.commontools.android.ScreenUtils}
 *
 * ScreenUtils
 * Convert between dp and sp
 */
@Deprecated
public class ScreenUtils {
    public final static int WIDTH_1080 = 1080;
    public static int mScreenHeight = 0;

    /**
     * dp转px  返回float类型
     *
     * @param context 上下文环境
     * @param dp      要转换的dp值
     * @return float类型
     */
    public static float dpToPx(Context context, float dp) {
        if (context == null) {
            return -1;
        }
        return dp * context.getResources().getDisplayMetrics().density;
    }

    /**
     * dp转px  返回int类型
     *
     * @param context
     * @param dp      要转换的dp值
     * @return int类型
     */
    public static int dpToPxInt(Context context, float dp) {
        return (int) (dpToPx(context, dp) + 0.5f);
    }

    /**
     * sp转px
     *
     * @param context 上下文环境
     * @param spValue 要转换的spValue值
     * @return int类型
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * px转dp
     *
     * @param context 上下文环境
     * @param px      要转换的px值
     * @return float类型
     */
    public static float pxToDp(Context context, float px) {
        if (context == null || px == 0) {
            return -1;
        }
        return px / context.getResources().getDisplayMetrics().density;
    }

    /**
     * px转dp
     *
     * @param context 上下文环境
     * @param px      要转换的px值
     * @return int类型
     */
    public static int pxToDpInt(Context context, float px) {
        return (int) (pxToDp(context, px) + 0.5f);
    }

    /**
     * 获取 status_bar_height 的高度
     *
     * @param mContext 上下文环境
     * @return int类型
     */
    public static int getStatusBarHeight(Context mContext) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = mContext.getResources().getDimensionPixelSize(x);

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }

    /**
     * 获取手机屏幕宽度
     *
     * @param context 上下文环境
     * @return int类型
     */
    public static int getScreenWidth(Context context) {
        if (context == null) {
            return 0;
        }
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metric = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metric);
        return metric.widthPixels;
    }

    /**
     * 获取手机屏幕高度
     *
     * @param context 上下文环境
     * @return int类型
     */
    public static int getScreenHeight(Context context) {
        if (context == null) {
            return 0;
        }
        if (mScreenHeight == 0) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics metric = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(metric);
            mScreenHeight = metric.heightPixels;
        }
        return mScreenHeight;
    }

    /**
     * 当前是否是竖屏
     *
     * @param context context
     * @return boolean
     */
    public static final boolean isPortrait(Context context) {
        return context.getApplicationContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    /**
     * 判断是否有NavigationBar
     *
     * @param context
     * @return
     */
    public static boolean hasNavBar(Context context) {
        int id = context.getResources().getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            return context.getResources().getBoolean(id);
        }
        return false;
    }

    /**
     * 获取系统亮度
     * 取值在(0 -- 255)之间
     */
    public static int getSystemScreenBrightness(Context context) {
        int values = 0;
        try {
            values = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return values;
    }

    /**
     * 设置当前屏幕量度0到255
     */
    public static void setActivityScreenBrightness(Activity activity, int paramInt) {
        if (activity == null) {
            return;
        }

        Window localWindow = activity.getWindow();
        WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
        localLayoutParams.screenBrightness = calculationScreenBrightnessValue(paramInt);
        localWindow.setAttributes(localLayoutParams);
    }

    /**
     * 将0到255之间的值转化为0-1之前的浮点小数，代表量度
     *
     * @param paramInt 0-255
     * @return 最小为0：最低屏幕亮度，最大为1，最高屏幕亮度。
     */
    public static float calculationScreenBrightnessValue(int paramInt) {
        if (paramInt < 0) {
            paramInt = 0;
        }

        if (paramInt > 255) {
            paramInt = 255;
        }

        return paramInt / 255.0F;
    }

    public static void resetActivityScreenBrightness(Activity activity) {
        if (activity == null) {
            return;
        }

        Window localWindow = activity.getWindow();
        WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
        localLayoutParams.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE;
        localWindow.setAttributes(localLayoutParams);
    }

}
