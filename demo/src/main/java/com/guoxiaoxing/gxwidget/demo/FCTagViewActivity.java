package com.guoxiaoxing.gxwidget.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.guoxiaoixng.gxwidget.tagview.FCTagView;

import java.util.ArrayList;
import java.util.List;

public class FCTagViewActivity extends AppCompatActivity implements View.OnClickListener {

    FCTagView mFCTagViewDefault;
    FCTagView mFCTagViewNoBorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fctag_view);
        setupView();
    }

    private void setupView() {

        findViewById(R.id.btn_add_tag).setOnClickListener(this);
        findViewById(R.id.btn_remove_tag).setOnClickListener(this);
        findViewById(R.id.btn_remove_all_tag).setOnClickListener(this);

        mFCTagViewDefault = (FCTagView) findViewById(R.id.fc_tag_view_default);
        mFCTagViewNoBorder = (FCTagView) findViewById(R.id.fc_tag_view_no_border);

        List<String> tags1 = new ArrayList<>();
        tags1.add("在售");
        tags1.add("实车寄售");
        tags1.add("在售20天");
        mFCTagViewDefault.setStyle(FCTagView.TAG_VIEW_STYLE_DEFAULT);
        mFCTagViewDefault.setTagViewList(tags1);

        List<String> tags2 = new ArrayList<>();
        tags2.add("支持按揭");
        tags2.add("首付10,05万, 月供0.37万");
        mFCTagViewNoBorder.setStyle(FCTagView.TAG_VIEW_STYLE_NO_BORDER);
        mFCTagViewNoBorder.setTagViewList(tags2);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_tag:
                mFCTagViewDefault.addTagView("新标签新标签新标签");
                //TODO implement
            case R.id.btn_remove_tag:
                mFCTagViewDefault.removeTagView(2);
                break;
            case R.id.btn_remove_all_tag:
                mFCTagViewDefault.removeAllTagView();
                break;
        }
    }
}
