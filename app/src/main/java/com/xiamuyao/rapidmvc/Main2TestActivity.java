package com.xiamuyao.rapidmvc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lzy.okgo.model.Response;
import com.xiamuyao.repidmvclibrary.Net.NetHelp;
import com.xiamuyao.repidmvclibrary.Net.NetInterface;
import com.xiamuyao.repidmvclibrary.Util.LL;
import com.xiamuyao.repidmvclibrary.base.BaseActivity;

public class Main2TestActivity extends BaseActivity implements NetInterface {


    @Override
    public int getLayout() {
        return R.layout.activity_main2_test;
    }

    @Override
    public void initObject(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

        netHelp.GetNet(1, "http://blog.csdn.net/fuwei52331314/article/details/55823689");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void GetFirstNetMessage() {
        NetHelp.netInterface =this;
    }


    @Override
    public void doSuccess(int what, String response, Response<String> stringResponse) {
        switch (what) {
            case 1:
                LL.d(stringResponse.toString());
                break;
        }
    }

    @Override
    public void doError(int what, int code, String message, Response<String> response) {

    }

    @Override
    public void doCacheSuccess(int what, String response, Response<String> stringResponse) {

    }
}
