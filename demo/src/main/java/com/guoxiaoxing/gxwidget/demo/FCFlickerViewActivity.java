package com.guoxiaoxing.gxwidget.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.guoxiaoixng.gxwidget.flickerview.FCFlickerView;
import com.guoxiaoixng.gxwidget.flickerview.Flicker;

public class FCFlickerViewActivity extends AppCompatActivity {

    FCFlickerView tv;
    Flicker mFlicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcflicker_view);
        tv = (FCFlickerView) findViewById(R.id.tv_flicker);
    }

    public void toggleAnimation(View target) {
        if (mFlicker != null && mFlicker.isAnimating()) {
            mFlicker.cancel();
        } else {
            mFlicker = new Flicker();
            mFlicker.start(tv);
        }
    }
}
