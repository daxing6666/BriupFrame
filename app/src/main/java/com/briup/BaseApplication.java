package com.briup;

import com.alibaba.android.arouter.launcher.ARouter;
public class BaseApplication extends CommonApplication {

    //ARouter调试开关
    private boolean isDebugArouter = true;
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init(){

        //路由框架
        if (isDebugArouter) {
            //开启InstantRun之后，一定要在ARouter.init之前调用openDebug
            ARouter.openDebug();//打印日志
            ARouter.openLog();//线上版本需要关闭,会有安全风险
        }
        ARouter.init(BaseApplication.getInstance());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }
}
