package com.xiamuyao.repidmvclibrary.View;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.xiamuyao.repidmvclibrary.R;


/**
 * XiaMuYao
 * XiaMuYaodqx@gmail.com
 * 2017/8/16
 * =========================
 * 说明：统一的等待框
 */
public class LoadingDialog extends Dialog {
    private long exitTime = 0;
    private TextView textTV;
    RotateLoading rotateloading;

    public LoadingDialog(Context context) {
        super(context);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setContentView(R.layout.dialog_loading);
        setCanceledOnTouchOutside(false);
        setCancelable(true);
        rotateloading = (RotateLoading) findViewById(R.id.rotateloading);
    }


    @Override
    public void onBackPressed() {
        // System.currentTimeMillis()无论何时调用，肯定大于2000
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast toast = Toast.makeText(getContext(), "再按一次取消",
                    Toast.LENGTH_SHORT);
            toast.show();
            exitTime = System.currentTimeMillis();
        } else {
            this.cancel();
        }
    }

    @Override
    public void show() {
        try {
            if (!isShowing()) {
                super.show();
                rotateloading.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destory() {
        rotateloading.stop(new Runnable() {
            @Override
            public void run() {
                try {
                    dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void hide() {
        try {
            rotateloading.stop(null);
            dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
