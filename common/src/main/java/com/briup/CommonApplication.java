package com.briup;

import android.app.Application;
import com.briup.logger.AndroidLogAdapter;
import com.briup.logger.FormatStrategy;
import com.briup.logger.Logger;
import com.briup.logger.PrettyFormatStrategy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class CommonApplication extends Application {

    private static CommonApplication instance;

    public static CommonApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        /**
         *日志打印初始化
         */
        //Initialize初始化
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(2)         // (Optional) How many method line to show. Default 2
                .methodOffset(3)        // (Optional) Skips some method invokes in stack trace. Default 5
//        .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag("Staff")   // (Optional) Custom tag for each log. Default PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        //todo
        Logger.d("message");
        Logger.w("no thread info and only 1 method");
        Logger.i("no thread info and method info");
        Logger.e("Custom tag for only one use");//错误信息 红色的
        Logger.json("{ \"key\": 3, \"value\": something}");
        Logger.d(Arrays.asList("foo", "bar"));
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        map.put("key1", "value2");
        Logger.d(map);
    }

}
