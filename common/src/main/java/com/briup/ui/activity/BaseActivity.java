package com.briup.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.briup.R;
import com.briup.bean.InfoResult;
import com.briup.bean.MsgBean;
import com.briup.ui.alerter.Alerter;
import com.briup.ui.alerter.OnHideAlertListener;
import com.briup.ui.alerter.OnShowAlertListener;
import com.briup.ui.barlibrary.ImmersionBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public abstract class BaseActivity extends SwipeBackActivity {

    private ActivityManager activityManager = ActivityManager.getActivityManager();
    private EventBus eventBus;
    private Toast toast = null;
    private SwipeBackLayout swipeBackLayout;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        if(null != activityManager) {
            activityManager.putActivity(this);
        }
        //把设置布局文件的操作交给继承的子类
        setContentView(getLayoutResId());
        //ButterKnife.bind(this);
        //初始化沉浸式
        initImmersionBar();
        initSwipeBack();
        setScreenVertical(this);
        eventBus = EventBus.getDefault();
        eventBus.register(this);
        init();
    }

    @Override
    protected void onDestroy() {
        if(null != activityManager) {
            activityManager.removeActivity(this);
        }
        super.onDestroy();
    }

    protected void initImmersionBar() {
        if (isImmersionBarEnabled()) {
            //在BaseActivity里初始化
            ImmersionBar.with(this).navigationBarColor(R.color.colorPrimary).init();
        }
    }

    private void initSwipeBack(){
        if(isSupportSwipeBackEnabled()){
            // 可以调用该方法，设置是否允许滑动退出
            setSwipeBackEnable(true);
            swipeBackLayout = getSwipeBackLayout();
            // 设置滑动方向，可设置EDGE_LEFT, EDGE_RIGHT, EDGE_ALL, EDGE_BOTTOM
            swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
            // 滑动退出的效果只能从边界滑动才有效果，如果要扩大touch的范围，可以调用这个方法
            //swipeBackLayout.setEdgeSize(200);
        }else {
            setSwipeBackEnable(false);
        }
    }
    /**
     * 设置Activity的显示方向为横向
     *
     * @param activity
     */
    private void setScreenHorizontal(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    /**
     * 设置Activity的显示方向为垂直方向
     *
     * @param activity
     */
    private void setScreenVertical(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    //===================================alerter==========================================
    public void showAlert(String msg){
        Alerter.create(this)
                .setText(msg)
                .disableOutsideTouch()
                .setDuration(700)
                .show();
    }

    private void showAlertDefault() {
        Alerter.create(this)
                .setTitle("Alert Title")
                .setText("Alert text...")
                .disableOutsideTouch()
                .show();
    }

    private void showAlertColoured() {
        Alerter.create(this)
                .setTitle("Alert Title")
                .setText("Alert text...")
                .setBackgroundColor(R.color.colorAccent)
                .show();
    }

    private void showAlertWithIcon() {
        Alerter.create(this)
                .setText("Alert text...")
                .setIcon(R.drawable.alerter_ic_face)
                .show();
    }

    private void showAlertTextOnly() {
        Alerter.create(this)
                .setText("Alert text...")
                .show();
    }

    private void showAlertWithOnClick() {
        Alerter.create(this)
                .setTitle("Alert Title")
                .setText("Alert text...")
                .setDuration(10000)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(BaseActivity.this, "OnClick Called", Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }

    private void showAlertVerbose() {
        Alerter.create(BaseActivity.this)
                .setTitle("Alert Title")
                .setText("The alert scales to accommodate larger bodies of text. " +
                        "The alert scales to accommodate larger bodies of text. " +
                        "The alert scales to accommodate larger bodies of text.")
                .show();
    }

    private void showAlertCallbacks(){
        Alerter.create(BaseActivity.this)
                .setTitle("Alert Title")
                .setText("Alert text...")
                .setDuration(10000)
                .setOnShowListener(new OnShowAlertListener() {
                    @Override
                    public void onShow() {
                        Toast.makeText(BaseActivity.this, "Show Alert", Toast.LENGTH_LONG).show();
                    }
                })
                .setOnHideListener(new OnHideAlertListener() {
                    @Override
                    public void onHide() {
                        Toast.makeText(BaseActivity.this, "Hide Alert", Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }

    private void showAlertInfiniteDuration() {
        Alerter.create(BaseActivity.this)
                .setTitle("Alert Title")
                .setText("Alert text...")
                .enableInfiniteDuration(true)
                .show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handlerMeg(MsgBean msgBean) {

    }

    public void showToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    public int getColor(Context context, int id) {
        return ContextCompat.getColor(context, id);
    }

    // -------------------- BaseActivity的辅助封装 --------------------- //

    public abstract boolean isImmersionBarEnabled();
    /**
     * 是否支持滑动返回
     *
     * @return
     */
    public abstract boolean isSupportSwipeBackEnabled();

    /**
     * 返回当前Activity布局文件的id
     *
     * @return
     */
    public abstract int getLayoutResId();

    /**
     * 初始化的一些操作
     */
    public abstract void init();

    /**
     * 加载网络数据
     */
    public abstract void loadData();

    /**
     * 网络数据返回成功
     */
    protected abstract void onSuccess(int what, InfoResult t);

    /**
     * 网络数据返回失败
     */
    protected abstract void onFail(int what,InfoResult t);

    public EventBus getEventBus() {
        return eventBus;
    }
}
