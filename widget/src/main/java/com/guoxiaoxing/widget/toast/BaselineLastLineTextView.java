package com.guoxiaoxing.widget.toast;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author doslin
 * @description
 * @since 2017/1/6
 */
public class BaselineLastLineTextView extends TextView {

    public BaselineLastLineTextView(Context context) {
        super(context);
    }

    public BaselineLastLineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getBaseline() {
        Layout layout = getLayout();
        if (layout == null) {
            return super.getBaseline();
        }
        int baselineOffset = super.getBaseline() - layout.getLineBaseline(0);
        return baselineOffset + layout.getLineBaseline(layout.getLineCount()-1);
    }
}
