package com.xiamuyao.repidmvclibrary.base;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.BuildConfig;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.xiamuyao.repidmvclibrary.Net.NetHelp;
import com.xiamuyao.repidmvclibrary.R;
import com.xiamuyao.repidmvclibrary.UItil.AppApplicationMgr;
import com.xiamuyao.repidmvclibrary.View.LoadingDialog;
import com.xiamuyao.repidmvclibrary.manager.ActivitysManagement;

/**
 * ================================================
 * 作    者：夏沐尧  Github地址：https://github.com/XiaMuYaoDQX
 * 版    本：1.0
 * 创建日期： 2018/2/23
 * 描    述：
 * 修订历史：
 * ================================================
 */
public abstract class BaseActivity extends AppCompatActivity {
    private ConstraintLayout mTitleLayout;
    private TextView mTitleTitle, mSunbtitle, mTitleLeftText, mTitleRightText;
    private ImageView mTitleLeftImg, mTitleRightimgOne, mTitleRightimgTwo;
    /**
     * 封装得等待框
     */
    public LoadingDialog loadDialog;
    /**
     * 权限请求码
     */
    private int request_UPDATA = 100;
    public NetHelp netHelp = NetHelp.getNethelp();

    //是否还有更多
    private boolean HaveMore = false;
    public String[] permissions = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //锁定屏幕竖向
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //沉浸式状态栏
        ImmersionBar.with(this).init();  //透明状态栏，不写默认透明色
        setContentView(getLayout());
        //添加当前Activity到堆栈中
        ActivitysManagement.getManagement().addActivity(this);
        initObject(savedInstanceState);
        initTitle();
        isOpenNeedPermission(permissions);
        loadDialog = new LoadingDialog(this, R.style.loading_dialog);
        if (BuildConfig.DEBUG) {
            initView();
            initData();
            initListener();
            GetFirstNetMessage();
        } else {
            try {
                initView();
                initData();
                initListener();
                GetFirstNetMessage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 进行公共头的封装
     * 如果后续需要其他get方法然后在补充
     */
    public void initTitle() {
        mTitleLayout = findViewById(R.id.title_layout);
        mTitleTitle = findViewById(R.id.title_title);
        mSunbtitle = findViewById(R.id.sunbtitle);
        mTitleLeftImg = findViewById(R.id.title_LeftImg);
        mTitleLeftText = findViewById(R.id.title_LeftText);
        mTitleRightText = findViewById(R.id.title_RightText);
        mTitleRightimgOne = findViewById(R.id.title_RightimgOne);
        mTitleRightimgTwo = findViewById(R.id.title_RightimgTwo);
    }

    public void setTitle(String title) {
        mTitleTitle.setVisibility(View.VISIBLE);
        mTitleTitle.setText(title);
    }

    public void setSubTitle(String subTitle) {
        mSunbtitle.setVisibility(View.VISIBLE);
        mSunbtitle.setText(subTitle);
    }

    public void setLeftImage(int resid) {
        mTitleLeftImg.setBackgroundResource(resid);
        mTitleLeftImg.setVisibility(View.VISIBLE);
        mTitleLeftImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setLeftText(String leftText, View.OnClickListener onClickListener) {
        mTitleLeftText.setVisibility(View.VISIBLE);
        mTitleLeftText.setText(leftText);
        mTitleLeftText.setOnClickListener(onClickListener);
    }

    public void setRightText(String RightText, View.OnClickListener onClickListener) {
        mTitleRightText.setVisibility(View.VISIBLE);
        mTitleRightText.setText(RightText);
        mTitleRightText.setOnClickListener(onClickListener);
    }

    public void setRightimgOne(int resId, View.OnClickListener onClickListener) {
        mTitleRightimgOne.setVisibility(View.VISIBLE);
        mTitleRightimgOne.setImageResource(resId);
        mTitleRightimgOne.setOnClickListener(onClickListener);
    }

    public void setRightimgTwo(int resId, View.OnClickListener onClickListener) {
        mTitleRightimgTwo.setVisibility(View.VISIBLE);
        mTitleRightimgTwo.setImageResource(resId);
        mTitleRightimgTwo.setOnClickListener(onClickListener);
    }

    public abstract int getLayout();

    abstract public void initObject(Bundle savedInstanceState);

    abstract public void initView();

    abstract public void initData();

    abstract public void initListener();

    /**
     * 第一次进入页面获取数据
     */
    abstract public void GetFirstNetMessage();

    /**
     * 取消等待框
     */
    public void dismissLoadDialog() {
        if (isFinishing()) {
            return;
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (null != loadDialog && loadDialog.isShowing()) {
                    loadDialog.hide();
                }
            }
        });
    }

    /**
     * 是否打开了需要的权限，如果没有就开启
     * 在那个页面需要什么权限就请求什么权限，不要再一开始请求所有权限
     */
    public void isOpenNeedPermission(String[] permissions) {
        if (Build.VERSION.SDK_INT >= 23) {
            for (String str : permissions) {
                if (!AppApplicationMgr.hasPermission(this, str)) {
                    ActivityCompat.requestPermissions(this, permissions, request_UPDATA);
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissLoadDialog();
        ImmersionBar.with(this).destroy();
    }
}
