package com.guoxiaoxing.widget.animation.FlipExit;

import android.animation.ObjectAnimator;
import android.view.View;

import com.guoxiaoxing.widget.animation.BaseAnimatorSet;

public class FlipHorizontalExit extends BaseAnimatorSet {
	@Override
	public void setAnimation(View view) {
		animatorSet.playTogether(ObjectAnimator.ofFloat(view, "rotationY", 0, 90),//
				ObjectAnimator.ofFloat(view, "alpha", 1, 0));
	}
}
