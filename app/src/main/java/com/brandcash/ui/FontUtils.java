package com.brandcash.ui;

import android.graphics.Typeface;

import com.brandcash.BrandcashApp;

/**
 * Created by savva.volobuev on 07.06.2017.
 */

public class FontUtils {

    private static Typeface sRobotoCondensed;
    private static Typeface sRobotoBoldCondensed;
    public static Typeface getRobotoBoldCondensed() {
        if (sRobotoBoldCondensed == null) {
            sRobotoBoldCondensed = Typeface.createFromAsset(BrandcashApp.getAppContext().getAssets(), "fonts/RobotoCondensed-Bold.ttf");
        }
        return sRobotoBoldCondensed;
    }

    public static Typeface getRobotoCondensed() {
        if (sRobotoCondensed == null) {
            sRobotoCondensed = Typeface.createFromAsset(BrandcashApp.getAppContext().getAssets(), "fonts/RobotoCondensed-Regular.ttf");
        }
        return sRobotoCondensed;
    }


}