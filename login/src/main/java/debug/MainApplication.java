package debug;

import android.app.Application;

public class MainApplication extends Application {
    public static MainApplication mainApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mainApplication = this;
        init();
    }

    public void init(){
    }
}
