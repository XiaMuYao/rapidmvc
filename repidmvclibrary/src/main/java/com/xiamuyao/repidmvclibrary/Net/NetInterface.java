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
     * @param what 是什么请求
     * @param jsonBody 返回json
     * @param msg 显示的信息
     */
    void doSuccess(int what, String jsonBody,String msg);

    /**
     * 失败回掉
     * code = 404 or > 500
     *
     * @param what
     * @param code
     */
    void doError(int what, int code);

    /**
     * 读取缓存数据成功回掉
     *
     * @param what
     * @param response
     * @param stringResponse
     */
    void doCacheSuccess(int what, String response, Response<String> stringResponse);
}
