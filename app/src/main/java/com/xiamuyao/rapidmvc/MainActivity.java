package com.xiamuyao.rapidmvc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xiamuyao.repidmvclibrary.Net.NetHelp;
import com.xiamuyao.repidmvclibrary.Net.NetInterface;
import com.xiamuyao.repidmvclibrary.Util.LL;
import com.xiamuyao.repidmvclibrary.Util.SpUtils;
import com.xiamuyao.repidmvclibrary.base.BaseActivity;

import java.util.List;

public class MainActivity extends BaseActivity implements NetInterface {
    private TestBean mTestBean = new TestBean();
    private Button mButton;

    @Override
    public int getLayout() {
        return R.layout.activity_main;

    }

    @Override
    public void initObject(Bundle savedInstanceState) {
        NetHelp.netInterface = this;
        netHelp.GetNet(1, "http://192.168.31.210:8080/user/all/1/10");
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
    public void doSuccess(int what, String jsonBody, String msg) {
        switch (what) {
            case 1:
                Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                TestBean mTestBean = JSON.parseObject(jsonBody, TestBean.class);
                for (TestBean.DataBean mDataBean : mTestBean.getData()) {
                    LL.d(mDataBean.getUserName());
                }
                break;
        }
    }

    @Override
    public void doError(int what, int code) {

    }

    @Override
    public void doCacheSuccess(int what, String response, Response<String> stringResponse) {

    }
}