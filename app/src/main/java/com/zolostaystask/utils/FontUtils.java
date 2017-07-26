package com.zolostaystask.utils;

import android.content.Context;
import android.graphics.Typeface;

public class FontUtils {

    private Context mContext;
    private static FontUtils mInstance = null;
    private static Typeface robotoRegularTypeFace = null;
    private static final String ROBOTO_REGULAR = "Fonts/Roboto-Regular.ttf";

    public FontUtils(Context context) {
        this.mContext = context;
    }

    public synchronized static FontUtils getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new FontUtils(context);
        }
        return mInstance;
    }

    public Typeface getRobotoRegularTypeFace() {
        if (robotoRegularTypeFace == null) {
            robotoRegularTypeFace = Typeface.createFromAsset(mContext.getAssets(), ROBOTO_REGULAR);
        }
        return robotoRegularTypeFace;
    }
}