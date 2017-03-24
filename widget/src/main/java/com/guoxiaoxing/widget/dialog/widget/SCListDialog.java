package com.guoxiaoxing.widget.dialog.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.guoxiaoxing.widget.R;
import com.guoxiaoxing.widget.dialog.adapter.ListDialogAdapter;
import com.guoxiaoxing.widget.dialog.entity.ListDialogEntity;
import com.guoxiaoxing.widget.dialog.widget.base.SCCBaseDialog;

import java.util.List;

/**
 * For more information, you can contact me by guoxiaoxing@souche.com
 *
 * @author guoxiaoxing
 * @since 16/9/30 下午4:57
 */
public class SCListDialog extends SCCBaseDialog {

    private static final String DEFAULT_BTN_LEFT = "取消";
    private static final String DEFAULT_BTN_RIGHT = "确定";

    private List<ListDialogEntity> mData;
    private RecyclerView mRvList;
    private ListDialogAdapter mListDialogAdapter;

    private TextView mTvTitle;
    private TextView mTvBtnLeft;
    private TextView mTvBtnRight;

    private String mTitle;
    private String mBtnLeftText;
    private String mBtnRightText;

    private OnCheckedListener mOnCheckedListener;

    public SCListDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.souche_widget_dialog_list, null, false);
        mRvList = (RecyclerView) view.findViewById(R.id.dialog_list_rv_list);
        mTvTitle = (TextView) view.findViewById(R.id.dialog_list_tv_title);
        mTvBtnLeft = (TextView) view.findViewById(R.id.dialog_list_tv_buttom);
        mTvBtnRight = (TextView) view.findViewById(R.id.dialog_list_tv_top);
        return view;
    }


    @Override
    public void setupView() {

        mTvTitle.setText(mTitle);
        mTvBtnLeft.setText(TextUtils.isEmpty(mBtnLeftText) ? DEFAULT_BTN_LEFT : mBtnLeftText);
        mTvBtnRight.setText(TextUtils.isEmpty(mBtnRightText) ? DEFAULT_BTN_RIGHT : mBtnRightText);
        mTvBtnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mTvBtnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnCheckedListener != null) {
                    ListDialogEntity entity = mListDialogAdapter.getCheckedItem();
                    if (entity != null) {
                        mOnCheckedListener.onChecked(entity.getCode(), entity.getName());
                    }
                }
                dismiss();
            }
        });

        mListDialogAdapter = new ListDialogAdapter(mData);
        mRvList.setLayoutManager(new LinearLayoutManager(mContext));
        mRvList.setAdapter(mListDialogAdapter);
    }

    /**
     * set withTitle text(设置标题内容) @return MaterialDialog
     */
    public SCListDialog withTitle(String title) {
        mTitle = title;
        return this;
    }

    public SCListDialog withLeftButton(String title) {
        mBtnLeftText = title;
        return this;
    }

    public SCListDialog withRightButton(String title) {
        mBtnRightText = title;
        return this;
    }

    public SCListDialog withData(List<ListDialogEntity> data) {
        mData = data;
        return this;
    }

    public SCListDialog withOnCheckedListener(OnCheckedListener listener) {
        mOnCheckedListener = listener;
        return this;
    }

    public interface OnCheckedListener {
        void onChecked(String code, String name);
    }
}
