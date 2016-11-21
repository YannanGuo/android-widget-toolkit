package com.guoxiaoixng.gxwidget.flickerview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.view.View;

/**
 * Flicker Animation
 * <p>
 * For more information, you can contact me by guoxiaoxing@souche.com
 *
 * @author guoxiaoxing
 * @since 16/10/13 下午2:30
 */
public class Flicker {

    public static final int ANIMATION_DIRECTION_LTR = 0;
    public static final int ANIMATION_DIRECTION_RTL = 1;

    private static final int DEFAULT_REPEAT_COUNT = ValueAnimator.INFINITE;
    private static final long DEFAULT_DURATION = 1000;
    private static final long DEFAULT_START_DELAY = 0;
    private static final int DEFAULT_DIRECTION = ANIMATION_DIRECTION_LTR;

    private int repeatCount;
    private long duration;
    private long startDelay;
    private int direction;
    private Animator.AnimatorListener animatorListener;

    private ObjectAnimator animator;

    public Flicker() {
        repeatCount = DEFAULT_REPEAT_COUNT;
        duration = DEFAULT_DURATION;
        startDelay = DEFAULT_START_DELAY;
        direction = DEFAULT_DIRECTION;
    }

    public int getRepeatCount() {
        return repeatCount;
    }

    public Flicker setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
        return this;
    }

    public long getDuration() {
        return duration;
    }

    public Flicker setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    public long getStartDelay() {
        return startDelay;
    }

    public Flicker setStartDelay(long startDelay) {
        this.startDelay = startDelay;
        return this;
    }

    public int getDirection() {
        return direction;
    }

    public Flicker setDirection(int direction) {

        if (direction != ANIMATION_DIRECTION_LTR && direction != ANIMATION_DIRECTION_RTL) {
            throw new IllegalArgumentException("The animation direction must be either ANIMATION_DIRECTION_LTR or ANIMATION_DIRECTION_RTL");
        }

        this.direction = direction;
        return this;
    }

    public Animator.AnimatorListener getAnimatorListener() {
        return animatorListener;
    }

    public Flicker setAnimatorListener(Animator.AnimatorListener animatorListener) {
        this.animatorListener = animatorListener;
        return this;
    }

    public <V extends View & FlickerViewBase> void start(final V flickerView) {

        if (isAnimating()) {
            return;
        }

        final Runnable animate = new Runnable() {
            @Override
            public void run() {

                flickerView.setFlickering(true);

                float fromX = 0;
                float toX = flickerView.getWidth();
                if (direction == ANIMATION_DIRECTION_RTL) {
                    fromX = flickerView.getWidth();
                    toX = 0;
                }

                animator = ObjectAnimator.ofFloat(flickerView, "gradientX", fromX, toX);
                animator.setRepeatCount(repeatCount);
                animator.setDuration(duration);
                animator.setStartDelay(startDelay);
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        flickerView.setFlickering(false);

                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            flickerView.postInvalidate();
                        } else {
                            flickerView.postInvalidateOnAnimation();
                        }

                        animator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

                if (animatorListener != null) {
                    animator.addListener(animatorListener);
                }

                animator.start();
            }
        };

        if (!flickerView.isSetUp()) {
            flickerView.setAnimationSetupCallback(new FlickerViewHelper.AnimationSetupCallback() {
                @Override
                public void onSetupAnimation(final View target) {
                    animate.run();
                }
            });
        } else {
            animate.run();
        }
    }

    public boolean isAnimating() {
        return animator != null && animator.isRunning();
    }

    public void cancel() {
        if (animator != null) {
            animator.cancel();
        }
    }
}
