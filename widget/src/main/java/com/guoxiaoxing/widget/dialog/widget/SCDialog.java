package com.guoxiaoxing.widget.dialog.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.guoxiaoxing.widget.R;
import com.guoxiaoxing.widget.dialog.listener.OnButtonClickListener;
import com.guoxiaoxing.widget.dialog.widget.base.SCCBaseDialog;

/**
 * For more information, you can contact me by guoxiaoxing@souche.com
 *
 * @author guoxiaoxing
 * @since 16/9/30 下午4:57
 */
public class SCDialog extends SCCBaseDialog {

    private static final String DEFAULT_BTN_LEFT = "取消";
    private static final String DEFAULT_BTN_RIGHT = "确定";
    private static final String DEFAULT_BTN_CENTER = "我知道了";

    private TextView mTvTitle;
    private TextView mTvContent;
    private TextView mTvBtnLeft;
    private View mVLine;
    private TextView mTvBtnRight;

    private String mTitle;
    private String mContent;
    private String mBtnLeftText;
    private String mBtnRightText;
    private String mBtnCenterText;

    private int mContentGravity = Gravity.CENTER;

    private OnButtonClickListener mLeftOnButtonClickListener;
    private OnButtonClickListener mRightOnButtonClickListener;

    /**
     * @param context context
     */
    public SCDialog(Context context) {
        super(context);
        setCancelable(false);
    }

    @Override
    public View onCreateView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.souche_widget_dialog_center, null, false);
        mTvTitle = (TextView) view.findViewById(R.id.dialog_list_tv_title);
        mTvContent = (TextView) view.findViewById(R.id.dialog_tv_content);
        mTvBtnLeft = (TextView) view.findViewById(R.id.dialog_list_tv_buttom);
        mVLine = view.findViewById(R.id.dialog_button_line);
        mTvBtnRight = (TextView) view.findViewById(R.id.dialog_list_tv_top);
        return view;
    }


    @Override
    public void setupView() {

        if (!TextUtils.isEmpty(mTitle)) {
            mTvTitle.setVisibility(View.VISIBLE);
            mTvTitle.setText(mTitle);
        }

        if (!TextUtils.isEmpty(mContent)) {
            mTvContent.setVisibility(View.VISIBLE);
            mTvContent.setText(mContent);
            mTvContent.setGravity(mContentGravity);
        }

        if (TextUtils.isEmpty(mBtnCenterText)) {
            mTvBtnLeft.setText(TextUtils.isEmpty(mBtnLeftText) ? DEFAULT_BTN_LEFT : mBtnLeftText);
            mTvBtnRight.setText(TextUtils.isEmpty(mBtnRightText) ? DEFAULT_BTN_RIGHT : mBtnRightText);
            mTvBtnLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mLeftOnButtonClickListener != null) {
                        mLeftOnButtonClickListener.onButtonClick();
                    } else {
                        dismiss();
                    }
                }
            });
            mTvBtnRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mRightOnButtonClickListener != null) {
                        mRightOnButtonClickListener.onButtonClick();
                    } else {
                        dismiss();
                    }
                }
            });
        } else {
            mTvBtnLeft.setVisibility(View.GONE);
            mVLine.setVisibility(View.GONE);
            mTvBtnRight.setText(TextUtils.isEmpty(mBtnCenterText) ? DEFAULT_BTN_CENTER : mBtnCenterText);
            mTvBtnRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mRightOnButtonClickListener != null) {
                        mRightOnButtonClickListener.onButtonClick();
                    } else {
                        dismiss();
                    }
                }
            });
        }

    }

    /**
     * set withTitle text(设置标题内容) @return MaterialDialog
     */
    public SCDialog withTitle(String title) {
        mTitle = title;
        return this;
    }

    public SCDialog withContent(String content) {
        mContent = content;
        return this;
    }

    public SCDialog withContentGravity(int gravity) {
        mContentGravity = gravity;
        return this;
    }

    public SCDialog withLeftButton(String title) {
        mBtnLeftText = title;
        return this;
    }

    public SCDialog withLeftButton(OnButtonClickListener listener) {
        mLeftOnButtonClickListener = listener;
        return this;
    }

    public SCDialog withLeftButton(String title, OnButtonClickListener listener) {
        mBtnLeftText = title;
        mLeftOnButtonClickListener = listener;
        return this;
    }

    public SCDialog withRightButton(String title) {
        mBtnRightText = title;
        return this;
    }

    public SCDialog withRightButton(OnButtonClickListener listener) {
        mRightOnButtonClickListener = listener;
        return this;
    }

    public SCDialog withRightButton(String title, OnButtonClickListener listener) {
        mBtnRightText = title;
        mRightOnButtonClickListener = listener;
        return this;
    }

    public SCDialog withCenterButton(String title) {
        mBtnCenterText = title;
        return this;
    }

    public SCDialog withCenterButton(String title, OnButtonClickListener listener) {
        mBtnCenterText = title;
        mRightOnButtonClickListener = listener;
        return this;
    }

    @Override
    public void show() {
        try {
            super.show();
        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }
    }
}
