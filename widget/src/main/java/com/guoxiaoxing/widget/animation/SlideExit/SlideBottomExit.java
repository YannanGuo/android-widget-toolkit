package com.guoxiaoxing.widget.animation.SlideExit;

import android.animation.ObjectAnimator;
import android.util.DisplayMetrics;
import android.view.View;

import com.guoxiaoxing.widget.animation.BaseAnimatorSet;

public class SlideBottomExit extends BaseAnimatorSet {
    @Override
    public void setAnimation(View view) {
        DisplayMetrics dm = view.getContext().getResources().getDisplayMetrics();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(view, "translationY", 0, 350 * dm.density));
    }
}
