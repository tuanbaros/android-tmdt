package bookstore.android.com.bookstore.app;

import android.app.Application;

import com.facebook.FacebookSdk;

/**
 * Created by vxhuy176 on 26/12/2016.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
