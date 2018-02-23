package com.xiamuyao.repidmvclibrary.UItil;

import android.text.TextUtils;

import com.xiamuyao.repidmvclibrary.AppConfig;

/**
 * XiaMuYao
 * XiaMuYaodqx@gmail.com
 * 2017/8/17
 * =========================
 * 说明：Log工具
 */
public class LogUtil {
    private static final boolean DEBUG = AppConfig.DEBUG;

    /**
     * 输出任何消息 verbose
     */
    public static void v(String tag, String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            try {
                android.util.Log.v(tag, msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            try {
                android.util.Log.v(tag, msg, tr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 一般提示性的消息 information 会显示i、w和e的信息
     */
    public static void i(String tag, String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            try {
                android.util.Log.i(tag, msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            try {
                android.util.Log.i(tag, msg, tr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 仅输出debug调试
     */
    public static void d(String tag, String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            try {
                android.util.Log.d(tag, msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            try {
                android.util.Log.d(tag, msg, tr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 仅显示红色的错误信息
     */
    public static void e(String tag, String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            try {
                android.util.Log.e(tag, msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            try {
                android.util.Log.e(tag, msg, tr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * warning警告 会输出Log.e的信息 需要注意优化代码
     */
    public static void w(String tag, String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            try {
                android.util.Log.w(tag, msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            try {
                android.util.Log.w(tag, msg, tr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
