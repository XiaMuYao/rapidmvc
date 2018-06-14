package com.xiamuyao.repidmvclibrary.Net;


import android.content.Context;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okgo.request.base.Request;
import com.xiamuyao.repidmvclibrary.AppConfig;
import com.xiamuyao.repidmvclibrary.AppContext;
import com.xiamuyao.repidmvclibrary.Util.LL;

import java.io.File;
import java.util.List;

/**
 * ================================================
 * 作    者：夏沐尧  Github地址：https://github.com/XiaMuYaoDQX
 * 版    本：1.0
 * 创建日期： 2018/2/23
 * 描    述：上拉加载请使用无缓存。上拉刷新使用有缓存
 * 基类new好了 子类直接可以用 nethelp.get
 * 有的页面不需要网络请求 所以接口new不放在基类里面了
 * 修订历史：
 * ================================================
 */
public class NetHelp {

    public static NetInterface netInterface;
    private static final NetHelp nethelp = new NetHelp(null);

    private NetHelp(NetInterface netInterface) {
        NetHelp.netInterface = netInterface;
    }

    public static NetHelp getNethelp() {
        return nethelp;
    }

    /**
     * 基本Get请求。无缓存
     *
     * @param What 请求类别
     * @param url  请求url
     */
    public void GetNet(final int What, String url) {
        OkGo.<String>get(AppConfig.URL_Base + url)
                .tag(this)
                .execute(new StringCallback() {
                    /**
                     * 获取成功
                     * code = 200
                     * 这里在构建之初 要和服务端预定好你们所有的code 200是成功无误 300可能是验证码错误？ 等等等等。。。。。
                     * @param response
                     */
                    @Override
                    public void onSuccess(Response<String> response) {
                        Msg mMsg = JSON.parseObject(response.body(), Msg.class);
                        if (mMsg.getCode() == 200) {
                            netInterface.doSuccess(What, response.body(), mMsg.getMsg());
                        }
                    }

                    /**
                     * 获取错误
                     * code 404 or >500
                     * @param response
                     */
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        netInterface.doError(What, response.code());
                    }
                });
    }

    /**
     * 基本Get请求.有缓存
     * 缓存缓存模式：先使用缓存，不管是否存在，仍然请求网络。
     * 缓存时间默认永不过期
     *
     * @param What     请求类别
     * @param url      请求url
     * @param Cachekey 缓存Key
     */
    public void GetNet(final int What, String url, String Cachekey) {
        OkGo.<String>get(AppConfig.URL_Base + url)
                .tag(this)
                .cacheKey(Cachekey)
                .cacheTime(-1)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .execute(new StringCallback() {
                    /**
                     * 获取成功
                     * @param response
                     */
                    @Override
                    public void onSuccess(Response<String> response) {
                        Msg mMsg = JSON.parseObject(response.body(), Msg.class);
                        if (mMsg.getCode() == 200) {
                            netInterface.doSuccess(What, response.body(), mMsg.getMsg());
                        }
                    }

                    /**
                     * 读取缓存
                     * @param response
                     */
                    @Override
                    public void onCacheSuccess(Response<String> response) {
                        super.onCacheSuccess(response);
                        netInterface.doCacheSuccess(What, response.body(), response);
                    }

                    /**
                     * 获取失败
                     * @param response
                     */
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        netInterface.doError(What, response.code());
                    }
                });


    }

    /**
     * 基本post请求。无缓存
     *
     * @param What 请求类别
     * @param url  请求url
     */
    public void PostNet(final int What, String url, String jsonBody) {
        OkGo.<String>post(AppConfig.URL_Base + url)
                .tag(this)
                .upJson(jsonBody)
                .execute(new StringCallback() {
                    /**
                     * 获取成功
                     * @param response
                     */
                    @Override
                    public void onSuccess(Response<String> response) {
                        Msg mMsg = JSON.parseObject(response.body(), Msg.class);
                        if (mMsg.getCode() == 200) {
                            netInterface.doSuccess(What, response.body(), mMsg.getMsg());
                        }
                    }

                    /**
                     * 获取错误
                     * @param response
                     */
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        netInterface.doError(What, response.code());
                    }
                });

    }

}
