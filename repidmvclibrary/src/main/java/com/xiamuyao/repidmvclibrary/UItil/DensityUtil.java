package com.xiamuyao.repidmvclibrary.UItil;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.xiamuyao.repidmvclibrary.AppContext;


/**
 * XiaMuYao
 * XiaMuYaodqx@gmail.com
 * 2017/8/17
 * =========================
 * 说明：dp和px的转换
 */
public class DensityUtil {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dipToPx(Context context, float dpValue) {
        if (context == null)
            context = AppContext.getInstance();
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int pxToDip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @param （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @param （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (spValue * scale + 0.5f);
    }

    public static int getScreenHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        return dm.heightPixels;
    }

    public static int getScreenWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        LogUtil.i("zzj", dm.widthPixels + "");
        return dm.widthPixels;
    }

    public static float getDensity(Activity activity) {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        float density = metric.density;  // 屏幕密度（0.75 / 1.0 / 1.5）
        return density;
    }

    public static int getNavigationBarHeight(Activity mActivity) {
        int height = 0;
        try {
            Resources resources = mActivity.getResources();
            int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
            height = resources.getDimensionPixelSize(resourceId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return height;
    }
}
