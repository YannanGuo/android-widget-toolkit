package com.guoxiaoixng.gxwidget.flickerview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Dumb class wrapping a FlickerViewHelper
 * <p>
 * For more information, you can contact me by guoxiaoxing@souche.com
 *
 * @author guoxiaoxing
 * @since 16/10/13 下午2:31
 */
public class FCFlickerView extends TextView implements FlickerViewBase {

    private FlickerViewHelper mFlickerViewHelper;

    public FCFlickerView(Context context) {
        super(context);
        mFlickerViewHelper = new FlickerViewHelper(this, getPaint(), null);
        mFlickerViewHelper.setPrimaryColor(getCurrentTextColor());
    }

    public FCFlickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mFlickerViewHelper = new FlickerViewHelper(this, getPaint(), attrs);
        mFlickerViewHelper.setPrimaryColor(getCurrentTextColor());
    }

    public FCFlickerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mFlickerViewHelper = new FlickerViewHelper(this, getPaint(), attrs);
        mFlickerViewHelper.setPrimaryColor(getCurrentTextColor());
    }

    @Override
    public float getGradientX() {
        return mFlickerViewHelper.getGradientX();
    }

    @Override
    public void setGradientX(float gradientX) {
        mFlickerViewHelper.setGradientX(gradientX);
    }

    @Override
    public boolean isFlickering() {
        return mFlickerViewHelper.isFlickering();
    }

    @Override
    public void setFlickering(boolean isFlickering) {
        mFlickerViewHelper.setFlickering(isFlickering);
    }

    @Override
    public boolean isSetUp() {
        return mFlickerViewHelper.isSetUp();
    }

    @Override
    public void setAnimationSetupCallback(FlickerViewHelper.AnimationSetupCallback callback) {
        mFlickerViewHelper.setAnimationSetupCallback(callback);
    }

    @Override
    public int getPrimaryColor() {
        return mFlickerViewHelper.getPrimaryColor();
    }

    @Override
    public void setPrimaryColor(int primaryColor) {
        mFlickerViewHelper.setPrimaryColor(primaryColor);
    }

    @Override
    public int getReflectionColor() {
        return mFlickerViewHelper.getReflectionColor();
    }

    @Override
    public void setReflectionColor(int reflectionColor) {
        mFlickerViewHelper.setReflectionColor(reflectionColor);
    }

    @Override
    public void setTextColor(int color) {
        super.setTextColor(color);
        if (mFlickerViewHelper != null) {
            mFlickerViewHelper.setPrimaryColor(getCurrentTextColor());
        }
    }

    @Override
    public void setTextColor(ColorStateList colors) {
        super.setTextColor(colors);
        if (mFlickerViewHelper != null) {
            mFlickerViewHelper.setPrimaryColor(getCurrentTextColor());
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (mFlickerViewHelper != null) {
            mFlickerViewHelper.onDraw();
        }
        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mFlickerViewHelper != null) {
            mFlickerViewHelper.onSizeChanged();
        }
    }
}
