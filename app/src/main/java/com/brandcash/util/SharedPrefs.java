package com.brandcash.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.brandcash.BrandcashApp;

/**
 * Created by savva.volobuev on 04.07.2017.
 */

public class SharedPrefs {

    public static final String PREF_GROUP_BRANDCASH = "com.brandcash";
    public static final String PREF_SID = "com.brandcash.preferences.PREF_SID";
    public static final String PREF_USER_ID = "com.brandcash.preferences.PREF_USER_ID";
    public static final String PREF_EXPIRES = "com.brandcash.preferences.PREF_EXPIRES";
    public static final String PREF_FIRST = "com.brandcash.preferences.PREF_FIRST";



    private static class PrefGroupGenericInstanceHolder {
        static final SharedPreferences INSTANCE = BrandcashApp.getAppContext().getSharedPreferences(PREF_GROUP_BRANDCASH, Context.MODE_PRIVATE);
    }

    public static SharedPreferences getPrefGroupGeneric() {
        return PrefGroupGenericInstanceHolder.INSTANCE;
    }

    public static String getPrefExpires() {
        return getPrefGroupGeneric().getString(PREF_EXPIRES, "");
    }
    public static void setPrefExpires(String exp) {
        SharedPreferences.Editor editor = getPrefGroupGeneric().edit();
        editor.putString(PREF_EXPIRES, exp);
        editor.apply();
    }

    public static boolean isPrefFirst() {
        return getPrefGroupGeneric().getBoolean(PREF_FIRST, false);
    }
    public static void setPrefFirst(boolean first) {
        SharedPreferences.Editor editor = getPrefGroupGeneric().edit();
        editor.putBoolean(PREF_FIRST, first);
        editor.apply();
    }



    public static int getPrefUserId() {
        return getPrefGroupGeneric().getInt(PREF_USER_ID, -1);
    }
    public static void setPrefUserId(int userId) {
        SharedPreferences.Editor editor = getPrefGroupGeneric().edit();
        editor.putInt(PREF_USER_ID, userId);
        editor.apply();
    }


    public static String getPrefSid() {
        return getPrefGroupGeneric().getString(PREF_SID, "");
    }
    public static void setPrefSid(String sid) {
        SharedPreferences.Editor editor = getPrefGroupGeneric().edit();
        editor.putString(PREF_SID, sid);
        editor.apply();
    }

}
