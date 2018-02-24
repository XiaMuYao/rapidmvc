package com.xiamuyao.repidmvclibrary.base;

import android.os.Bundle;

import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xiamuyao.repidmvclibrary.Net.NetHelp;
import com.xiamuyao.repidmvclibrary.Net.NetInterface;

import static com.xiamuyao.repidmvclibrary.Net.NetHelp.*;

/**
 * ================================================
 * 作    者：夏沐尧  Github地址：https://github.com/XiaMuYaoDQX
 * 版    本：1.0
 * 创建日期： 2018/2/24
 * 描    述：
 * 修订历史：
 * ================================================
 */
public abstract class NetBaseActivity extends BaseActivity implements NetInterface {
    /**
     * 是否还有更多
     */
    private boolean HaveMore = false;

    @Override
    public void doSuccess(int what, String response, Response<String> stringResponse) {
    }

    @Override
    public void doError(int what, int code, String message, Response<String> response) {

    }

    @Override
    public void doCacheSuccess(int what, String response, Response<String> stringResponse) {

    }

    @Override
    public void doStart(int what, Request<String, ? extends Request> request) {

    }

    @Override
    public void doUploadProgress(int what, Progress progress) {

    }
}
