package com.xiamuyao.repidmvclibrary.UItil;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;


import com.xiamuyao.repidmvclibrary.AppContext;
import com.xiamuyao.repidmvclibrary.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * XiaMuYao
 * XiaMuYaodqx@gmail.com
 * 2017/8/17
 * =========================
 * 说明：状态栏相关工具类
 */
public class DeviceUtil {
    public static final int SDK_VERSION_CUPCAKE = 19;
    public static boolean PRE_CUPCAKE = getSDKVersionNumber() >= SDK_VERSION_CUPCAKE ? true : false;
    private static RelativeLayout mLayouTop;

    public static void setTranslucent(Activity activity) {
        // 透明状态栏
        if (PRE_CUPCAKE && !"OPPO".equals(Build.BRAND)) {
            translucentStatus(activity);
        }
    }

    public static int getGoodHight(Activity activity) {
        return DensityUtil.getScreenHeight(activity) - (getStatusHeight(activity) + dip2px(activity, 55) + DensityUtil.getScreenWidth(activity));
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void translucentStatus(Activity activity) {
        activity.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setStatusBarDarkMode(true, activity);
        setStatusBarDarkIcon(activity.getWindow(), true);
    }


    public static int getSDKVersionNumber() {
        int sdkVersion;
        try {
            sdkVersion = Integer.valueOf(Build.VERSION.SDK_INT);
        } catch (NumberFormatException e) {
            sdkVersion = 0;
        }
        return sdkVersion;
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    /**
     * @author WangJie 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context activity, float dpValue) {
        final float scale = activity.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);

    }

//    /**
//     * @author WangJie 增加head的高度到自适配高度沉浸式
//     */
//    public static void loginheight(View view) {
//        // int statusa= loginll.getHeight()+ getStatusHeight( activity);
//        if (PRE_CUPCAKE && !"OPPO".equals(Build.BRAND)) {
//            mLayouTop = (RelativeLayout) view
//                    .findViewById(R.id.title_layout);
//            int height = DensityUtil.dipToPx(AppContext.getInstance(), 50) + getStatusHeight(AppContext.getInstance());
//            ViewGroup.LayoutParams linearParams = mLayouTop
//                    .getLayoutParams();
//            linearParams.height = height;
//            mLayouTop.setLayoutParams(linearParams);
//        }
//    }

    /**
     * @param activity
     * @return
     * @author WangJie 部分手机不适用 状态栏高度算法
     */
    public static int getStatusHeight(Activity activity) {
        int statusHeight = 0;
        Rect localRect = new Rect();
        activity.getWindow().getDecorView()
                .getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass
                        .getField("status_bar_height").get(localObject)
                        .toString());
                statusHeight = activity.getResources()
                        .getDimensionPixelSize(i5);
            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }

    /**
     * 小米适配
     *
     * @param darkmode
     * @param activity
     * @author WangJie
     */
    public static void setStatusBarDarkMode(boolean darkmode, Activity activity) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class
                    .forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams
                    .getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class,
                    int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag
                    : 0, darkModeFlag);
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    /**
     * 魅族适配
     *
     * @author WangJie
     */
    public static boolean setStatusBarDarkIcon(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {
//                Log.e("MeiZu", "setStatusBarDarkIcon: failed");
            }
        }
        return result;
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
