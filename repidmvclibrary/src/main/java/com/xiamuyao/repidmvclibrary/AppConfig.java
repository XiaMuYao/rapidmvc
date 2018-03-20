package com.xiamuyao.repidmvclibrary;

import java.lang.annotation.ElementType;

/**
 * XiaMuYao
 * XiaMuYaodqx@gmail.com
 * 2017/8/22
 * =========================
 * 说明：
 */
public class AppConfig {

    public enum RunType {
        QA, ONLINE
    }

    public static final RunType URL_TYPE = RunType.QA;  //打正式包时须改成 ONLINE
    public static final boolean DEBUG = true;   //log的开关
    public static String URL_Base = UrlConstants.getApi(); //接口域名

}

/**
 * 内部类 用来分辨接口域名
 */
class UrlConstants {

    public static final String Api_Qq = "";
    //    public static final String Api_Qq = "测试地址";
    public static final String Api_Online = "";
//    public static final String Api_Online = "正式地址";

    public static String getApi() {
        if (AppConfig.URL_TYPE == AppConfig.RunType.QA)
            return Api_Qq;
        else
            return Api_Online;
    }
}
