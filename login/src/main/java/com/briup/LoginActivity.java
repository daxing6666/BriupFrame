package com.briup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.briup.bean.InfoResult;
import com.briup.router.RouterPath;
import com.briup.ui.activity.BaseActivity;

@Route(path = RouterPath.ROUTER_PATH_LOGIN)
public class LoginActivity extends BaseActivity {

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
        return R.layout.activity_login;
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
