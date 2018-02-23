package com.xiamuyao.repidmvclibrary.UItil;//package com.example.xiamuyao.mydevelop.UItil;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.text.TextUtils;
//
//import com.example.xiamuyao.mydevelop.AppConfig;
//import com.example.xiamuyao.mydevelop.AppContext;
//
//
///**
// * XiaMuYao
// * XiaMuYaodqx@gmail.com
// * 2017/8/17
// * =========================
// * 说明：用户信息操作
// */
//public class UserUtil {
//    private static String session_hash = "";
//
//    private static final String cacheName = "account";
//
//    /**
//     * 获取session_hash方法
//     *
//     * @param app
//     * @return
//     */
//    public static String getSessionHash(AppContext app) {
//        if (TextUtils.isEmpty(session_hash)) {
//            AccountBean accountBean = (AccountBean) app.readObject(cacheName);
//            if (accountBean != null)
//                session_hash = accountBean.getSessionHash();
//        }
//        LogUtil.i("H-SESSION-HASH", session_hash);
//        return session_hash;
//    }
//
//    /**
//     * 存储用户信息方法
//     *
//     * @param app
//     * @return
//     */
//    public static void saveAccount(AppContext app, AccountBean accountBean) {
//        if (accountBean != null)
//            app.saveObject(cacheName, accountBean);
//    }
//
//    /**
//     * 读取用户信息方法
//     *
//     * @param app
//     * @return
//     */
//    public static UserBean readUser(AppContext app) {
//        AccountBean accountBean = (AccountBean) app.readObject(cacheName);
//        if (accountBean == null)
//            return null;
//        return accountBean.getUser();
//    }
//
//    public static void setSession_hash(String session_hash) {
//        UserUtil.session_hash = session_hash;
//    }
//
//    /**
//     * 删除用户信息方法
//     *
//     * @param app
//     * @return
//     */
//    public static void removeUser(AppContext app) {
//        try {
//            session_hash = null;
//            app.removeObject(cacheName);
//            app.getSharedPreferences(
//                    "Message", Activity.MODE_PRIVATE).edit().clear().commit();
//            new CacheHelper<List<GoodsCartBean>>().removeObject(AppConfig.CartCacheName);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void isGoToLogin(AppContext app, Activity activity, Runnable runnable) {
//        UserBean userBean = UserUtil.readUser(app);
//        if (userBean == null || TextUtils.isEmpty(getSessionHash(app)))
//            activity.startActivity(new Intent(activity, LoginActivity.class));
//        else if (UIHelper.IsUser(userBean.getRealm())) {
//            if ((TextUtils.isEmpty(userBean.getNickname()) || TextUtils.isEmpty(userBean.getAvatar())))
//                activity.startActivity(new Intent(activity, PerfectUserInfoActivity.class));
//            else
//                runnable.run();
//
//        } else
//            runnable.run();
//    }
//
//    public static void isGoToLogin(AppContext app, Activity activity, String intoClassName, Runnable runnable) {
//        UserBean userBean = UserUtil.readUser(app);
//        if (userBean == null || TextUtils.isEmpty(getSessionHash(app))) {
//            activity.startActivity(new Intent(activity, LoginActivity.class).putExtra("intoClassName", intoClassName));
//        } else if (UIHelper.IsUser(userBean.getRealm())) {
//            if ((TextUtils.isEmpty(userBean.getNickname()) || TextUtils.isEmpty(userBean.getAvatar())))
//                activity.startActivity(new Intent(activity, PerfectUserInfoActivity.class).putExtra("intoClassName", intoClassName));
//            else
//                runnable.run();
//        } else
//            runnable.run();
//    }
//
//    public static boolean isLogin(AppContext app) {
//        if (TextUtils.isEmpty(getSessionHash(app)))
//            return false;
//        return true;
//    }
//
//    public static void updateUser(AppContext app, UserBean userBean) {
//        AccountBean accountBean = (AccountBean) app.readObject(cacheName);
//        if (accountBean != null)
//            accountBean.setUser(userBean);
//        app.saveObject(cacheName, accountBean);
//    }
//
//    public static String getUserRole(String userType) {
//        if ("MANAGER".equals(userType) || "DECORATOR".equals(userType))
//            return "工长";
//        if ("CUSTOMER".equals(userType))
//            return "业主";
//        if ("OPERATOR".equals(userType) || "INSPECTOR".equals(userType))
//            return "监理";
//        if ("DESIGNER".equals(userType))
//            return "设计师";
//        if ("ASSISTANT".equals(userType))
//            return "家装顾问";
//        return "";
//    }
//
//}
