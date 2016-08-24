package com.guoxiaoxing.widget.util;

import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: guoxiaoxing
 * Date: 16/4/15 下午3:45
 * Function: ViewUtils
 * <p>
 * For more information, you can visit https://github.com/guoxiaoxing or contact me by
 * guoxiaoxingv@163.com
 */
public class ViewUtils {

    private ViewUtils() {
        throw new AssertionError();
    }

    /**
     * 手动测量布局大小
     *
     * @param view   被测量的布局
     * @param width  布局默认宽度
     * @param height 布局默认高度
     *               示例： measureView(view, ViewGroup.LayoutParams.MATCH_PARENT,
     *               ViewGroup.LayoutParams.WRAP_CONTENT);
     */
    public static void measureView(View view, int width, int height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.LayoutParams(width, height);
        }
        int mWidth = ViewGroup.getChildMeasureSpec(0, 0, params.width);

        int mHeight;
        int tempHeight = params.height;
        if (tempHeight > 0) {
            mHeight = View.MeasureSpec.makeMeasureSpec(tempHeight,
                    View.MeasureSpec.EXACTLY);
        } else {
            mHeight = View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED);
        }
        view.measure(mWidth, mHeight);
    }

    /**
     * 设置View的外边距(Margins)
     *
     * @param view   要设置外边距的View
     * @param left   左侧外边距
     * @param top    顶部外边距
     * @param right  右侧外边距
     * @param bottom 底部外边距
     */
    public static void setMargins(View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view
                    .getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();       //请求重绘
        }
    }

    private long mLastClickTime;

    /**
     * 屏蔽用户频繁点击
     *
     * @param waitTime 间隔时间
     * @return boolean
     */
    public boolean isWindowLock(long waitTime) {
        long currentTime = SystemClock.elapsedRealtime();
        if (currentTime - mLastClickTime > waitTime) {
            mLastClickTime = currentTime;
            return false;
        }
        return true;
    }
}
