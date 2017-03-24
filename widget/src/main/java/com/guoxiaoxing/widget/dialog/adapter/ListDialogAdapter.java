package com.guoxiaoxing.widget.dialog.adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.guoxiaoxing.widget.R;
import com.guoxiaoxing.widget.dialog.entity.ListDialogEntity;

import java.util.List;

/**
 * For more information, you can contact me by guoxiaoxing@souche.com
 *
 * @author guoxiaoxing
 * @since 16/10/8 下午3:54
 */
public class ListDialogAdapter extends RecyclerView.Adapter<ListDialogAdapter.ViewHolder> {

    private List<ListDialogEntity> mData;

    private int mCheckedPosition = -1;
    private int mOldPosition = -1;
    private boolean isCanCancel = true;

    public ListDialogAdapter(List<ListDialogEntity> data) {
        mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.souche_widget_item_dialog_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (mData != null) {
            holder.mTvItem.setText(mData.get(position).getName());
        }

        if (mCheckedPosition == holder.getAdapterPosition()) {
            holder.mTvItem.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.fcprompt_fc_c1));
            holder.mIvItem.setImageResource(R.drawable.souche_widget_select_icon);
        } else {
            holder.mTvItem.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.fcprompt_fc_c3));
            holder.mIvItem.setImageResource(0);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCheckedPosition = holder.getAdapterPosition();
                if (isCanCancel) {
                    if (mOldPosition == mCheckedPosition) {
                        mCheckedPosition = -1;
                    }
                    mOldPosition = mCheckedPosition;
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public ListDialogEntity getCheckedItem() {
        if (mData != null) {
            return mData.get(mCheckedPosition);
        }
        return null;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTvItem;
        ImageView mIvItem;

        ViewHolder(View itemView) {
            super(itemView);
            mTvItem = (TextView) itemView.findViewById(R.id.dialog_list_tv_item);
            mIvItem = (ImageView) itemView.findViewById(R.id.dialog_list_iv_item);
        }
    }
}  