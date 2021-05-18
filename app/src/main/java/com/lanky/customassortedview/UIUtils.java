package com.lanky.customassortedview;

import android.view.View;

import androidx.core.view.ViewCompat;

/**
 * @ClassName UIUtils
 * @Description TODO
 * @Author 90629
 * @Date 2021/5/13 9:24
 * @Version 1.0
 */
public class UIUtils {
    public static void animateView(View v, boolean hasFocus, float mWProportion, float mHProportion){
        if (hasFocus) {
            ViewCompat.animate(v)
                    .setDuration(200)
                    .scaleX(mWProportion)
                    .scaleY(mHProportion)
                    .start();
        } else {
            ViewCompat.animate(v)
                    .setDuration(200)
                    .scaleX(1f)
                    .scaleY(1f)
                    .start();
        }
    }
}
