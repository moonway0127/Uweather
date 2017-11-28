package xin.moonway.uweather;

import android.app.Application;
import android.content.Context;

/**
 *
 * Created by 辽科大_Moonway on 2017/9/16.
 *
 * https://github.com/moonway0127
 */
public class MainApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context.getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
