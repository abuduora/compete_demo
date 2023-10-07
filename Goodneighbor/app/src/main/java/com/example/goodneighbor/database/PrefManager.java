package com.example.goodneighbor.database;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    private static final String PREF_NAME = "YourAppPref";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String IS_FIRST_Realname = "IsFirstRealname";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public Context context;

    public PrefManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public boolean isFirstRealname() {
        return sharedPreferences.getBoolean(IS_FIRST_Realname, true);
    }

    public boolean isFirstTimeLaunch() {
        return sharedPreferences.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setFirstRealname(boolean isRealname) {
        editor.putBoolean(IS_FIRST_Realname, isRealname);
        editor.apply();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.apply();
    }
}
