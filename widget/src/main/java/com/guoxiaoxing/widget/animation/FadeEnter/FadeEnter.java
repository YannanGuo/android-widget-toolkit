package com.guoxiaoxing.widget.animation.FadeEnter;

import android.animation.ObjectAnimator;
import android.view.View;

import com.guoxiaoxing.widget.animation.BaseAnimatorSet;

public class FadeEnter extends BaseAnimatorSet {
	@Override
	public void setAnimation(View view) {
		animatorSet.playTogether(//
				ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(duration));
	}
}
