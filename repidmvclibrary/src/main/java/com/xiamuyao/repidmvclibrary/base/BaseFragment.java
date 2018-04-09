package com.xiamuyao.repidmvclibrary.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ================================================
 * 作    者：夏沐尧  Github地址：https://github.com/XiaMuYaoDQX
 * 版    本：1.0
 * 创建日期： 2018/3/21
 * 描    述：
 * 修订历史：
 * ================================================
 */
public abstract class BaseFragment extends Fragment {

    public Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getContext();
        return inflater.inflate(getLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
        initListener();
        GetFirstNetMessage();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initObject(savedInstanceState);
    }


    protected abstract int getLayout();

    protected abstract void initObject(Bundle savedInstanceState);

    protected abstract void initView(View view);

    protected abstract void initData();


    protected abstract void initListener();

    protected abstract void GetFirstNetMessage();

}
