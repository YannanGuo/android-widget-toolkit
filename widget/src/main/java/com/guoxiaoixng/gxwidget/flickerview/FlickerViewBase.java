package com.guoxiaoixng.gxwidget.flickerview;

/**
 * For more information, you can contact me by guoxiaoxing@souche.com
 *
 * @author guoxiaoxing
 * @since 16/10/13 下午2:31
 */
public interface FlickerViewBase {
    float getGradientX();

    void setGradientX(float gradientX);

    boolean isFlickering();

    void setFlickering(boolean isFlickering);

    boolean isSetUp();

    void setAnimationSetupCallback(FlickerViewHelper.AnimationSetupCallback callback);

    int getPrimaryColor();

    void setPrimaryColor(int primaryColor);

    int getReflectionColor();

    void setReflectionColor(int reflectionColor);
}
