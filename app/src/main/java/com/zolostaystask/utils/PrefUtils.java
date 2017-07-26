package com.zolostaystask.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtils {

    private static SharedPreferences sharedPreferences;
    private static final String KEY_SP_PHONE = "user_phone";
    private static final String PATIENT_SHARED_PREF = "user_shared_pref";

    public static void clearSP(Context mContext) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(PATIENT_SHARED_PREF,
                mContext.MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
    }

    public static void setUserPhone(Context mContext, String phone) {
        sharedPreferences = mContext.getSharedPreferences(PATIENT_SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(KEY_SP_PHONE, phone);
        edit.commit();
    }

    public static String getUserPhone(Context mContext) {
        sharedPreferences = mContext.getSharedPreferences(PATIENT_SHARED_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_SP_PHONE, null);
    }
}