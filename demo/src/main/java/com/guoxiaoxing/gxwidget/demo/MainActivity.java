package com.guoxiaoxing.gxwidget.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_fc_flicker_view).setOnClickListener(this);
        findViewById(R.id.btn_fc_step_view).setOnClickListener(this);
        findViewById(R.id.btn_fc_tag_view).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_fc_flicker_view:
                startActivity(new Intent(MainActivity.this, FCFlickerViewActivity.class));
                break;
            case R.id.btn_fc_step_view:
                startActivity(new Intent(MainActivity.this, FCStepViewActivity.class));
            case R.id.btn_fc_tag_view:
                startActivity(new Intent(MainActivity.this, FCTagViewActivity.class));
                break;
        }
    }
}
