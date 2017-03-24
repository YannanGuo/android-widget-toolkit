package com.guoxiaoxing.widget.dialog.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.guoxiaoxing.widget.R;

/**
 * For more information, you can contact me by guoxiaoxing@souche.com
 *
 * @author guoxiaoxing
 * @since 16/9/30 下午5:02
 */
public class SCLoadingDialog extends Dialog {

    public static final String LOADING_TYPE_FENGCHE = "dfc";
    public static final String LOADING_TYPE_CHENIU = "cheniu";
    public static final String LOADING_TYPE_TANGECHE = "tangeche";
    public static final String LOADING_TYPE_WAITING = "waiting";

    private String mLoadingText = "正在加载...";
    private String mLoaddingType = LOADING_TYPE_TANGECHE;

    public SCLoadingDialog(Context context) {
        super(context, R.style.fcprompt_dialog);
    }

    public SCLoadingDialog(Context context, String loadingText, String loadingType) {
        super(context, R.style.fcprompt_dialog);
        if (!TextUtils.isEmpty(loadingText)) {
            mLoadingText = loadingText;
        }

        if (!TextUtils.isDigitsOnly(loadingType)) {
            mLoaddingType = loadingType;
        }
    }

    public SCLoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.souche_widget_dialog_loading);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.loading_progress);
        TextView textView = (TextView) findViewById(R.id.fc_loading_dialog_text);
        ImageView icon = (ImageView) findViewById(R.id.iv_loading_type);
        textView.setText(mLoadingText);

        switch (mLoaddingType) {
            case LOADING_TYPE_FENGCHE:
                icon.setImageResource(R.drawable.souche_widget_loading_cheniu);
                break;
            case LOADING_TYPE_CHENIU:
                icon.setImageResource(R.drawable.souche_widget_fengche_white);
                break;
            case LOADING_TYPE_TANGECHE:
                icon.setVisibility(View.GONE);
                break;
            case LOADING_TYPE_WAITING:
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.souche_widget_drawable_loading_flower);
                progressBar.setIndeterminateDrawable(drawable);
                progressBar.setIndeterminate(true);
                icon.setVisibility(View.GONE);
                break;
        }

    }

    @Override
    public void show() {
        try {
            if (!isShowing()) {
                super.show();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dismiss() {
        try {
            if (isShowing()) {
                super.dismiss();
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}