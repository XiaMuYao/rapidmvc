package com.xiamuyao.rapidmvc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xiamuyao.repidmvclibrary.Net.NetHelp;
import com.xiamuyao.repidmvclibrary.Net.NetInterface;
import com.xiamuyao.repidmvclibrary.Util.LL;
import com.xiamuyao.repidmvclibrary.Util.SpUtils;
import com.xiamuyao.repidmvclibrary.base.BaseActivity;

public class MainActivity extends BaseActivity implements NetInterface {


    private Button mButton;

    @Override
    public int getLayout() {
        return R.layout.activity_main;

    }

    @Override
    public void initObject(Bundle savedInstanceState) {
        NetHelp.netInterface = this;
    }

    @Override
    public void initView() {
        mButton = findViewById(R.id.mButton);
    }

    @Override
    public void initData() {
        LL.d("我打印的是什么");
    }

    @Override
    public void initListener() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spTest();
                startActivity(new Intent(mContext, Main2TestActivity.class));
            }
        });
    }

    private void spTest() {
        SpUtils.put(mContext, "int数据", "shudddju");
        Object mO = SpUtils.get(mContext, "int数据", "默认");
        LL.d(String.valueOf(mO));
    }

    @Override
    public void GetFirstNetMessage() {

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