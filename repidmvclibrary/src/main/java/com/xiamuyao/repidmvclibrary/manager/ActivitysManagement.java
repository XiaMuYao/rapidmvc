package com.xiamuyao.repidmvclibrary.manager;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;


import java.util.Stack;

/**
 * ================================================
 * 作    者：夏沐尧  Github地址：https://github.com/XiaMuYaoDQX
 * 版    本：1.0
 * 创建日期： 2018/2/23
 * 描    述：应用程序Activity管理类：用于Activity管理和应用程序退出 提供方法获取屏幕的尺寸：displayMetrics
 * 修订历史：
 * ================================================
 */

public class ActivitysManagement {

    private static Stack<Activity> activityStack;
    private static ActivitysManagement instance;

    private ActivitysManagement() {
    }

    /**
     * 单一实例
     */
    public static ActivitysManagement getManagement() {
        if (instance == null) {
            instance = new ActivitysManagement();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = null;
        if (activityStack == null || activityStack.size() < 0)
            return null;
        try {
            activity = activityStack.lastElement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        activity.finish();
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                activity.finish();
            }
        }
    }

    public Activity getActivity(Class<?> cls) {
        if (activityStack == null)
            return null;
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

    public Activity getActivity(String className) {
        if (activityStack == null)
            return null;
        for (Activity activity : activityStack) {
            if (activity.getClass().getSimpleName().equals(className)) {
                return activity;
            }
        }
        return null;
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    public int getActivityCount() {
        return activityStack.size();
    }

    /**
     * 获取屏幕实时尺寸
     *
     * @param activity
     * @return DisplayMetrics.widthPixels, DisplayMetrics.heightPixels
     * DisplayMetrics.xdpi, DisplayMetrics.ydpi
     */
    public DisplayMetrics displayMetrics(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay()
                .getMetrics(displayMetrics);
        return displayMetrics;
    }

    /**
     * @param activity
     * @return sample:getWindowView(activity).getHeight()
     */
    public View getWindowView(Activity activity) {
        View view = null;
        try {
            view = activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    /**
     * 获取状态栏的高度, 需要在页面渲染完成之后调用
     *
     * @param activity
     * @return
     */
    public int getStatusBarHeight(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }

    public void finishOtherActivity(Activity activity) {
        if (activityStack == null)
            return;
        for (int i = 0; i < activityStack.size(); i++) {
            if (i == activityStack.size() - 1) {
                activityStack.get(i).startActivity(new Intent(activityStack.get(i), activity.getClass()));
                activityStack.get(i).finish();

            } else {
                activityStack.get(i).finish();
            }
        }
    }
}