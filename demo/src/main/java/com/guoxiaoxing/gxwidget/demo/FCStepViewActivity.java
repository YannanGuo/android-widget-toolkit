package com.guoxiaoxing.gxwidget.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.guoxiaoixng.gxwidget.stepview.HorizontalStepView;
import com.guoxiaoixng.gxwidget.stepview.StepBean;

import java.util.ArrayList;
import java.util.List;

public class FCStepViewActivity extends AppCompatActivity implements View.OnClickListener {

    private HorizontalStepView horizontalStepView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcstep_view);

        horizontalStepView = (HorizontalStepView) findViewById(R.id.horizontal_step_view);
        findViewById(R.id.play_horizontal_step_view).setOnClickListener(this);

        List<StepBean> stepsBeanList = new ArrayList<>();
        StepBean stepBean0 = new StepBean("发起", 1);
        StepBean stepBean1 = new StepBean("报价", 1);
        StepBean stepBean4 = new StepBean("完成", -1);
        stepsBeanList.add(stepBean0);
        stepsBeanList.add(stepBean1);
        stepsBeanList.add(stepBean4);
        horizontalStepView.setStepViewTexts(stepsBeanList);//总步骤
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play_horizontal_step_view:

                break;
        }
    }
}
