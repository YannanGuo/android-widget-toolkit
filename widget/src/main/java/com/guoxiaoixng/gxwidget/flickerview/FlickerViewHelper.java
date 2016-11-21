package com.guoxiaoixng.gxwidget.flickerview;

import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import com.guoxiaoxing.gxwidget.R;


/**
 * For more information, you can contact me by guoxiaoxing@souche.com
 *
 * @author guoxiaoxing
 * @since 16/10/13 下午2:31
 */
public class FlickerViewHelper {

    private static final int DEFAULT_REFLECTION_COLOR = 0xFFFFFFFF;
    private View view;
    private Paint paint;
    // center position of the gradient
    private float gradientX;
    // shader applied on the text view
    // only null until the first global layout
    private LinearGradient linearGradient;
    // shader's local matrix
    // never null
    private Matrix linearGradientMatrix;
    private int primaryColor;
    // shimmer reflection color
    private int reflectionColor;
    // true when animating
    private boolean isFlickering;
    // true after first global layout
    private boolean isSetUp;
    // callback called after first global layout
    private AnimationSetupCallback callback;

    public FlickerViewHelper(View view, Paint paint, AttributeSet attributeSet) {
        this.view = view;
        this.paint = paint;
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {

        reflectionColor = DEFAULT_REFLECTION_COLOR;

        if (attributeSet != null) {
            TypedArray a = view.getContext().obtainStyledAttributes(attributeSet, R.styleable.FCWidget_Flicker, 0, 0);
            if (a != null) {
                try {
                    reflectionColor = a.getColor(R.styleable.FCWidget_Flicker_reflectionColor, DEFAULT_REFLECTION_COLOR);
                } catch (Exception e) {
                    android.util.Log.e("FCWidget_Flicker", "Error while creating the view:", e);
                } finally {
                    a.recycle();
                }
            }
        }

        linearGradientMatrix = new Matrix();
    }

    public float getGradientX() {
        return gradientX;
    }

    public void setGradientX(float gradientX) {
        this.gradientX = gradientX;
        view.invalidate();
    }

    public boolean isFlickering() {
        return isFlickering;
    }

    public void setFlickering(boolean isFlickering) {
        this.isFlickering = isFlickering;
    }

    public boolean isSetUp() {
        return isSetUp;
    }

    public void setAnimationSetupCallback(AnimationSetupCallback callback) {
        this.callback = callback;
    }

    public int getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(int primaryColor) {
        this.primaryColor = primaryColor;
        if (isSetUp) {
            resetLinearGradient();
        }
    }

    private void resetLinearGradient() {

        // our gradient is a simple linear gradient from textColor to reflectionColor. its axis is at the center
        // when it's outside of the view, the outer color (textColor) will be repeated (Shader.TileMode.CLAMP)
        // initially, the linear gradient is positioned on the left side of the view
        linearGradient = new LinearGradient(-view.getWidth(), 0, 0, 0,
                new int[]{
                        primaryColor,
                        reflectionColor,
                        primaryColor,
                },
                new float[]{
                        0,
                        0.5f,
                        1
                },
                Shader.TileMode.CLAMP
        );

        paint.setShader(linearGradient);
    }

    public int getReflectionColor() {
        return reflectionColor;
    }

    public void setReflectionColor(int reflectionColor) {
        this.reflectionColor = reflectionColor;
        if (isSetUp) {
            resetLinearGradient();
        }
    }

    protected void onSizeChanged() {

        resetLinearGradient();

        if (!isSetUp) {
            isSetUp = true;

            if (callback != null) {
                callback.onSetupAnimation(view);
            }
        }
    }

    /**
     * content of the wrapping view's onDraw(Canvas)
     * MUST BE CALLED BEFORE SUPER STATEMENT
     */
    public void onDraw() {

        // only draw the shader gradient over the text while animating
        if (isFlickering) {

            // first onDraw() when shimmering
            if (paint.getShader() == null) {
                paint.setShader(linearGradient);
            }

            // translate the shader local matrix
            linearGradientMatrix.setTranslate(2 * gradientX, 0);

            // this is required in order to invalidate the shader's position
            linearGradient.setLocalMatrix(linearGradientMatrix);

        } else {
            // we're not animating, remove the shader from the paint
            paint.setShader(null);
        }

    }

    public interface AnimationSetupCallback {
        void onSetupAnimation(View target);
    }
}
