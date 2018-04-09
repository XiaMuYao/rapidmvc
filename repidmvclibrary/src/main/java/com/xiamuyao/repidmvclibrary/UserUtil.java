//package com.xiamuyao.repidmvclibrary;
//
//
//import com.sikkha.skline.App;
//import com.sikkha.skline.bean.UserBean;
//
//public class UserUtil {
//    private static String session_hash = "";
//
//    public static final String cacheName = "account";
//
//    /**
//     * 存储用户信息方法
//     *
//     * @param app
//     * @return
//     */
//    public static void saveAccount(App app, UserBean accountBean) {
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
//    public static UserBean.DataBean readUser(App app) {
//        UserBean accountBean = (UserBean) app.readObject(cacheName);
//        if (accountBean == null)
//            return null;
//        return accountBean.getData();
//    }
//
//
//}
