package com.xiamuyao.repidmvclibrary.Net;


import android.widget.Toast;

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
                         * @param response
                         */
                        @Override
                        public void onSuccess(Response<String> response) {
                            netInterface.doSuccess(What, response.body(), response);
                        }

                        /**
                         * 获取错误
                         * @param response
                         */
                        @Override
                        public void onError(Response<String> response) {
                            super.onError(response);
                            netInterface.doError(What, response.code(), response.message(), response);
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
                        netInterface.doSuccess(What, response.body(), response);
                        LL.d("走网络", "走网络");
                    }

                    /**
                     * 读取缓存
                     * @param response
                     */
                    @Override
                    public void onCacheSuccess(Response<String> response) {
                        super.onCacheSuccess(response);
                        LL.d("读取缓存", "读取缓存");
                        netInterface.doCacheSuccess(What, response.body(), response);
                    }

                    /**
                     * 获取失败
                     * @param response
                     */
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        netInterface.doError(What, response.code(), response.message(), response);
                    }
                });


    }

    /**
     * 多个文件一个Key
     *
     * @param What         请求类别
     * @param url          请求地址
     * @param mListfileKey 多个文件的Key
     * @param mListfile    多文件列列表(文件地址)
     */
    public void NetPost(final int What, String url, String mListfileKey, List<File> mListfile) {
        if (AppContext.getInstance().isNetworkConnected()) {
            OkGo.<String>post(url)
                    .tag(this)
                    .addFileParams(mListfileKey, mListfile)
                    .execute(new StringCallback() {
                        /**
                         * 上传开始
                         * @param request
                         */
                        @Override
                        public void onStart(Request<String, ? extends Request> request) {
                            super.onStart(request);
                            netInterface.doStart(What, request);
                        }

                        /**
                         * 上传完成
                         * @param response
                         */
                        @Override
                        public void onSuccess(Response<String> response) {
                            netInterface.doSuccess(What, response.body(), response);
                        }

                        /**
                         * 上传错误
                         * @param response
                         */
                        @Override
                        public void onError(Response<String> response) {
                            super.onError(response);
                            netInterface.doError(What, response.code(), response.message(), response);
                        }

                        /**
                         * 上传进度
                         * @param progress
                         */
                        @Override
                        public void uploadProgress(Progress progress) {
                            super.uploadProgress(progress);
                            netInterface.doUploadProgress(What, progress);
                        }
                    });
        } else {
            Toast.makeText(AppContext.getInstance(), "请求失败 请检查网络", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 一个文件对应一个Key
     *
     * @param What         请求类别
     * @param url          请求地址
     * @param mListfileKey 多个文件的Key
     * @param mListfile    多文件列列表(文件地址)
     */
    public void NetPost(final int What, String url, List<String> mListfileKey, List<File> mListfile) {
        if (AppContext.getInstance().isNetworkConnected()) {
            final PostRequest request = OkGo.<String>post(url).tag(this);
            for (int i = 0; i < mListfileKey.size(); i++) {
                request.params(mListfileKey.get(i), mListfile.get(i));
            }
            request.execute(new StringCallback() {
                /**
                 * 上传开始
                 *
                 * @param request
                 */
                @Override
                public void onStart(Request<String, ? extends Request> request) {
                    super.onStart(request);
                    netInterface.doStart(What, request);
                }

                /**
                 * 上传完成
                 *
                 * @param response
                 */
                @Override
                public void onSuccess(Response<String> response) {
                    netInterface.doSuccess(What, response.body(), response);
                }

                /**
                 * 上传错误
                 *
                 * @param response
                 */
                @Override
                public void onError(Response<String> response) {
                    super.onError(response);
                    netInterface.doError(What, response.code(), response.message(), response);
                }

                /**
                 * 上传进度
                 *
                 * @param progress
                 */
                @Override
                public void uploadProgress(Progress progress) {
                    super.uploadProgress(progress);
                    netInterface.doUploadProgress(What, progress);
                }
            });
        } else {
            Toast.makeText(AppContext.getInstance(), "请求失败 请检查网络", Toast.LENGTH_SHORT).show();
        }
    }
}