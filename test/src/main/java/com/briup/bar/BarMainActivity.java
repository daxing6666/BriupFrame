package com.briup.bar;

import com.briup.R;
import com.briup.bean.InfoResult;
import com.briup.ui.activity.BaseActivity;

public class BarMainActivity extends BaseActivity {


    @Override
    public boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public boolean isSupportSwipeBackEnabled() {
        return true;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {

    }

    @Override
    public void loadData() {

    }

    @Override
    protected void onSuccess(int what, InfoResult t) {

    }

    @Override
    protected void onFail(int what, InfoResult t) {

    }
}
