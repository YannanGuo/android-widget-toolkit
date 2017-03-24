package com.guoxiaoxing.widget.dialog.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.guoxiaoxing.widget.R;
import com.guoxiaoxing.widget.animation.BaseAnimatorSet;
import com.guoxiaoxing.widget.animation.SlideEnter.SlideBottomEnter;
import com.guoxiaoxing.widget.animation.SlideExit.SlideBottomExit;
import com.guoxiaoxing.widget.dialog.adapter.SheetDialogAdapter;
import com.guoxiaoxing.widget.dialog.entity.SheetAction;
import com.guoxiaoxing.widget.dialog.widget.base.SCCBaseDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Sheet Dialog
 * For more information, you can contact me by guoxiaoxing@souche.com
 *
 * @author guoxiaoxing
 * @since 16/9/30 下午4:57
 */
public class SCSheetListDialog extends SCCBaseDialog {

    private TextView mTvContent;
    private RecyclerView mRvAction;
    private TextView mTvCancel;

    private String mContent;
    private List<SheetAction> mActionList = new ArrayList<>();

    private ActionClickListener mActionClickListener;

    public SCSheetListDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.souche_widget_dialog_sheet_list, null, false);
        mTvContent = (TextView) view.findViewById(R.id.dialog_tv_content);
        mRvAction = (RecyclerView) view.findViewById(R.id.dialog_rv_action);
        mTvCancel = (TextView) view.findViewById(R.id.dialog_list_tv_cancel);
        return view;
    }

    @Override
    public void setupView() {
        if (getWindow() != null) {
            getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        }
        mTvContent.setText(mContent);
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mRvAction.setLayoutManager(new LinearLayoutManager(mContext));
        SheetDialogAdapter sheetDialogAdapter = new SheetDialogAdapter(this, mActionList);
        sheetDialogAdapter.setActionClickListener(mActionClickListener);
        mRvAction.setAdapter(sheetDialogAdapter);
    }

    @Override
    public BaseAnimatorSet createShowAnimation() {
        return new SlideBottomEnter();
    }

    @Override
    public BaseAnimatorSet createDismissAnimation() {
        return new SlideBottomExit();
    }

    public SCSheetListDialog withContent(String content) {
        mContent = content;
        return this;
    }

    public SCSheetListDialog withAction(String actionCode, String actionName) {
        SheetAction sheetAction = new SheetAction();
        sheetAction.setActionCode(actionCode);
        sheetAction.setActionName(actionName);
        mActionList.add(sheetAction);
        return this;
    }

    public SCSheetListDialog withActionClickListener(ActionClickListener listener) {
        mActionClickListener = listener;
        return this;
    }

    public interface ActionClickListener {
        void actionClick(String actionCode, String actionName);
    }
}
