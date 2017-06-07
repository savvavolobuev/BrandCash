package com.brandcash;

import android.app.Application;
import android.content.Context;

/**
 * Created by savva.volobuev on 07.06.2017.
 */

public class BrandcashApp extends Application {

    private static Context sAppContext;

    public static Context getAppContext() {
        return sAppContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = getApplicationContext();

    }
}
