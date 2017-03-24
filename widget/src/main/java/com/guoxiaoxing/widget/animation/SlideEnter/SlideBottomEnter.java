package com.guoxiaoxing.widget.animation.SlideEnter;

import android.animation.ObjectAnimator;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.guoxiaoxing.widget.animation.BaseAnimatorSet;

public class SlideBottomEnter extends BaseAnimatorSet {
    @Override
    public void setAnimation(View view) {
        DisplayMetrics dm = view.getContext().getResources().getDisplayMetrics();
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(view, "translationY", dm.heightPixels, 0));
    }
}
