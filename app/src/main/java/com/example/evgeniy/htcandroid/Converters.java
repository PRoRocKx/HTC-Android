package com.example.evgeniy.htcandroid;

import android.content.Context;

public class Converters {
    public static float dpFromPx(float px, Context context) {
        return px / context.getResources().getDisplayMetrics().densityDpi;
    }

    public static float pxFromDp(float dp, Context context) {
        return dp * context.getResources().getDisplayMetrics().densityDpi;
    }
}
