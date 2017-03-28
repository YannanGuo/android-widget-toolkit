package com.guoxiaoxing.widget.dialog.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guoxiaoxing.widget.R;
import com.guoxiaoxing.widget.dialog.entity.SheetAction;
import com.guoxiaoxing.widget.dialog.widget.WonderSheetDialog;

import java.util.List;

/**
 * For more information, you can contact me by guoxiaoxing@souche.com
 *
 * @author guoxiaoxing
 * @since 17/1/12 下午4:50
 */
public class SheetDialogAdapter extends RecyclerView.Adapter<SheetDialogAdapter.ViewHolder> {

    private WonderSheetDialog mWonderSheetDialog;
    private List<SheetAction> mActionList;

    private WonderSheetDialog.ActionClickListener mActionClickListener;

    public SheetDialogAdapter(WonderSheetDialog WonderSheetDialog, List<SheetAction> actionList) {
        mWonderSheetDialog = WonderSheetDialog;
        mActionList = actionList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.souche_widget_adapter_sheet_action, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (mActionList == null) {
            return;
        }
        holder.mTvAction.setText(mActionList.get(holder.getAdapterPosition()).getActionName());
        if (holder.getAdapterPosition() == mActionList.size() - 1) {
            setViewBackgroundWithoutResettingPadding(holder.mTvAction, R.drawable.souche_widget_selector_sheet_dialog_corners_bottom);

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mActionClickListener != null && mActionList != null) {
                    mActionClickListener.actionClick(mActionList.get(holder.getAdapterPosition()).getActionCode(),
                            mActionList.get(holder.getAdapterPosition()).getActionName());
                    if (mWonderSheetDialog != null) {
                        mWonderSheetDialog.dismiss();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mActionList == null ? 0 : mActionList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTvAction;

        ViewHolder(View itemView) {
            super(itemView);
            mTvAction = (TextView) itemView.findViewById(R.id.adapter_tv_sheet_action);
        }
    }

    private void setViewBackgroundWithoutResettingPadding(final View v, final int backgroundResId) {
        final int paddingBottom = v.getPaddingBottom(), paddingLeft = v.getPaddingLeft();
        final int paddingRight = v.getPaddingRight(), paddingTop = v.getPaddingTop();
        v.setBackgroundResource(backgroundResId);
        v.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    public WonderSheetDialog.ActionClickListener getActionClickListener() {
        return mActionClickListener;
    }

    public void setActionClickListener(WonderSheetDialog.ActionClickListener actionClickListener) {
        mActionClickListener = actionClickListener;
    }

}
