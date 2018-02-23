package com.xiamuyao.repidmvclibrary;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

public class AppContext extends Application {
    private static AppContext appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        initOkgo();
//        initSmartLayout();
//        initUmengPsu();
    }

//    /**
//     * 注册友盟推送
//     */
//    private void initUmengPsu() {
//        PushAgent mPushAgent = PushAgent.getInstance(this);
////        注册推送服务，每次调用register方法都会回调该接口
//        mPushAgent.register(new IUmengRegisterCallback() {
//
//            @Override
//            public void onSuccess(String deviceToken) {
////                注册成功会返回device token
//                Log.d("asdasdas", "onSuccess: " + deviceToken);
//            }
//
//            @Override
//            public void onFailure(String s, String s1) {
//
//            }
//        });
//    }

//    /**
//     * 初始化上拉刷新下拉加载
//     */
//    private void initSmartLayout() {
//        //设置全局的Header构建器
//        SmartRefreshLayout.setDefaultRefreshHeaderCreater((context, layout) -> {
//            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
//            return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);//指定为经典Header，默认是 贝塞尔雷达Header
//        });
//        //设置全局的Footer构建器
//        SmartRefreshLayout.setDefaultRefreshFooterCreater((context, layout) -> {
//            //指定为经典Footer，默认是 BallPulseFooter
//            return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
//        });
//    }

    /**
     * 网络框架初始化
     */
    private void initOkgo() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);
        //全局的读取超时时间
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //使用sp保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));

        OkGo.getInstance().init(this)                             //必须调用初始化
                .setOkHttpClient(builder.build())                 //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)                 //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)     //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3);                                //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
    }

    public static AppContext getInstance() {
        return appContext;
    }

    /**
     * 检测网络是否可用
     *
     * @return true有网 flase 没网
     */
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    /**
     * 保存用户信息
     *
     * @param fileName 文件名
     * @param ser      序列化对象
     * @return
     */
    public boolean saveObject(String fileName, Serializable ser) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = openFileOutput(fileName, MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(ser);
            oos.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                oos.close();
            } catch (Exception e) {
            }
            try {
                fos.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * 删除用户信息
     *
     * @param fileName
     */
    public void removeObject(String fileName) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = openFileOutput(fileName, MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.reset();
            deleteFile(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                fos.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * 同步读取对象
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public Serializable readObject(String fileName) {
        if (!isExistDataCache(fileName))
            return null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Serializable serializable = null;
        try {
            fis = openFileInput(fileName);
            ois = new ObjectInputStream(fis);
            serializable = (Serializable) ois.readObject();

        } catch (FileNotFoundException e) {
        } catch (Exception e) {
            e.printStackTrace();
            // 反序列化失败 - 删除缓存文件
            if (e instanceof InvalidClassException) {
                File data = getFileStreamPath(fileName);
                data.delete();
            }
        } finally {
            try {
                ois.close();
                fis.close();
            } catch (Exception e) {
            }
        }
        return serializable;
    }

    /**
     * 判断缓存是否存在
     *
     * @param cachefile
     * @return
     */
    public boolean isExistDataCache(String cachefile) {
        boolean exist = false;
        File data = getFileStreamPath(cachefile);
        if (data.exists())
            exist = true;
        return exist;
    }
}
