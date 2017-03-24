package com.guoxiaoxing.widget.dialog.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.guoxiaoxing.widget.R;
import com.guoxiaoxing.widget.animation.BaseAnimatorSet;
import com.guoxiaoxing.widget.animation.SlideEnter.SlideBottomEnter;
import com.guoxiaoxing.widget.animation.SlideExit.SlideBottomExit;
import com.guoxiaoxing.widget.dialog.listener.OnButtonClickListener;
import com.guoxiaoxing.widget.dialog.widget.base.SCCBaseDialog;

/**
 * Sheet Dialog
 * For more information, you can contact me by guoxiaoxing@souche.com
 *
 * @author guoxiaoxing
 * @since 16/9/30 下午4:57
 */
public class SCSheetDialog extends SCCBaseDialog {

    private static final String DEFAULT_BTN_BUTTOM = "取消";
    private static final String DEFAULT_BTN_TOP = "确定";

    private TextView mTvContent;
    private TextView mTvBtnBottom;
    private TextView mTvBtnTop;

    private String mContent;
    private String mBtnButtomText;
    private String mBtnTopText;

    private OnButtonClickListener mButtomOnClickListener;
    private OnButtonClickListener mTopOnClickListener;


    public SCSheetDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.souche_widget_dialog_sheet, null, false);
        mTvContent = (TextView) view.findViewById(R.id.dialog_tv_content);
        mTvBtnBottom = (TextView) view.findViewById(R.id.dialog_list_tv_buttom);
        mTvBtnTop = (TextView) view.findViewById(R.id.dialog_list_tv_top);
        return view;
    }

    @Override
    public void setupView() {
        if (getWindow() != null) {
            getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        }
        mTvContent.setText(mContent);
        mTvBtnBottom.setText(TextUtils.isEmpty(mBtnButtomText) ? DEFAULT_BTN_BUTTOM : mBtnButtomText);
        mTvBtnTop.setText(TextUtils.isEmpty(mBtnTopText) ? DEFAULT_BTN_TOP : mBtnTopText);
        mTvBtnBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtomOnClickListener != null) {
                    mButtomOnClickListener.onButtonClick();
                } else {
                    dismiss();
                }
            }
        });
        mTvBtnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTopOnClickListener != null) {
                    mTopOnClickListener.onButtonClick();
                } else {
                    dismiss();
                }
            }
        });
    }

    @Override
    public BaseAnimatorSet createShowAnimation() {
        return new SlideBottomEnter();
    }

    @Override
    public BaseAnimatorSet createDismissAnimation() {
        return new SlideBottomExit();
    }

    /**
     * set withContent text(设置正文内容)
     */
    public SCSheetDialog withContent(String content) {
        mContent = content;
        return this;
    }

    public SCSheetDialog withButtomButton(String title) {
        mBtnButtomText = title;
        return this;
    }

    public SCSheetDialog withButtomButton(OnButtonClickListener listener) {
        mButtomOnClickListener = listener;
        return this;
    }


    public SCSheetDialog withButtomButton(String title, OnButtonClickListener listener) {
        mBtnButtomText = title;
        mButtomOnClickListener = listener;
        return this;
    }

    public SCSheetDialog withTopButton(String title) {
        mBtnTopText = title;
        return this;
    }

    public SCSheetDialog withTopButton(OnButtonClickListener listener) {
        mTopOnClickListener = listener;
        return this;
    }

    public SCSheetDialog withTopButton(String title, OnButtonClickListener listener) {
        mBtnTopText = title;
        mTopOnClickListener = listener;
        return this;
    }
}
