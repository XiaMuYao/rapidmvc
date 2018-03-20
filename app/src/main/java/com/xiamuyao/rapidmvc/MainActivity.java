package com.xiamuyao.rapidmvc;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xiamuyao.repidmvclibrary.Util.LL;
import com.xiamuyao.repidmvclibrary.Util.SpUtils;
import com.xiamuyao.repidmvclibrary.base.BaseActivity;

public class MainActivity extends BaseActivity {


    private Button mButton;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initObject(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mButton = findViewById(R.id.mButton);
    }

    @Override
    public void initData() {
        LL.d(this.toString(), "我打印的是什么");
    }

    @Override
    public void initListener() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpUtils.put(mContext, "int数据", "shuju");
                Object mO = SpUtils.get(mContext, "int数据", "默认");
                LL.e("nishish", String.valueOf(mO));
                Log.e("nishish", "onClick: "+String.valueOf(mO));
            }
        });
    }

    @Override
    public void GetFirstNetMessage() {

    }

}