package com.xiamuyao.repidmvclibrary.Net;

import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

/**
 * ================================================
 * 作    者：夏沐尧  Github地址：https://github.com/XiaMuYaoDQX
 * 版    本：1.0
 * 创建日期： 2018/2/23
 * 描    述：
 * 修订历史：
 * ================================================
 */
public interface NetInterface {

    /**
     * 成功回掉
     *
     * @param what
     * @param response
     * @param stringResponse
     */
    void doSuccess(int what, String response, Response<String> stringResponse);

    /**
     * 失败回掉
     *
     * @param what
     * @param code
     * @param message
     * @param response
     */
    void doError(int what, int code, String message, Response<String> response);

    /**
     * 读取网络数据成功回掉
     *
     * @param what
     * @param response
     * @param stringResponse
     */
    void doCacheSuccess(int what, String response, Response<String> stringResponse);

//    /**
//     * 文件上传开始
//     *
//     * @param what
//     * @param request
//     */
//    void doStart(int what, Request<String, ? extends Request> request);
//
//    /**
//     * 文件下载进度
//     *
//     * @param what
//     * @param progress
//     */
//    void doUploadProgress(int what, Progress progress);

}
