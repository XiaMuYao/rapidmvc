package com.xiamuyao.rapidmvc;

import android.renderscript.Short2;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xiamuyao.repidmvclibrary.Net.NetHelp;
import com.xiamuyao.repidmvclibrary.Net.NetInterface;
import com.xiamuyao.repidmvclibrary.base.BaseActivity;
import com.xiamuyao.repidmvclibrary.base.NetBaseActivity;

public class MainActivity extends BaseActivity {

    private Button yincang, xianshi;


    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initObject(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        yincang = findViewById(R.id.yincang);
        xianshi = findViewById(R.id.xianshi);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        yincang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissLoadDialog();
            }
        });
        xianshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDialog.show();
            }
        });
    }

    @Override
    public void GetFirstNetMessage() {

    }

}