package com.briup.router.provider;

import android.app.Activity;

import com.alibaba.android.arouter.facade.template.IProvider;


public interface ILoginProvider extends IProvider {

    void goToLogin(Activity activity);
}
