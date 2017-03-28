package com.guoxiaoxing.widget.dialog.widget.picker;

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
import com.guoxiaoxing.widget.dialog.widget.base.BaseDialog;
import com.guoxiaoxing.widget.dialog.widget.picker.impl.SCWheelOptionPicker;
import com.guoxiaoxing.widget.dialog.widget.picker.impl.SCWheelPicker;
import com.guoxiaoxing.widget.dialog.widget.picker.model.IPickerModel;

import java.util.List;

/**
 * For more information, you can contact me by guoxiaoxing@souche.com
 *
 * @author guoxiaoxing
 * @since 16/12/16 上午11:32
 */
public class SCOptionPicker extends BaseDialog {

    private TextView mTvConfirm;
    private TextView mTvCancel;
    private SCWheelOptionPicker mWopOption;

    private IPickerModel mPickedOption;
    private List<IPickerModel> mData;
    private OnOptionPickedListener mOnOptionPickedListener;

    /**
     * @param context context
     */
    public SCOptionPicker(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.souche_widget_dialog_option_picker, null);
        mTvConfirm = (TextView) view.findViewById(R.id.fcprompt_option_picker_tv_confirm);
        mTvCancel = (TextView) view.findViewById(R.id.fcprompt_option_picker_tv_cancel);
        mWopOption = (SCWheelOptionPicker) view.findViewById(R.id.fcprompt_picker_wop_option);
        return view;
    }

    @Override
    public void setupView() {
        if (getWindow() != null) {
            getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        }

        mWopOption.setOnItemSelectedListener(new SCWheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(SCWheelPicker picker, IPickerModel data, int position) {
                mPickedOption.setCode(data.getCode());
                mPickedOption.setName(data.getName());
            }
        });

        mTvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnOptionPickedListener != null) {
                    if (mData != null && mData.size() > 0 && mPickedOption != null) {
                        for (IPickerModel pickerModel : mData) {
                            if (TextUtils.equals(pickerModel.getCode(), mPickedOption.getCode())) {
                                mPickedOption.setName(pickerModel.getName());
                                break;
                            }
                        }
                        mOnOptionPickedListener.onOptionPicked(mPickedOption.getCode(), mPickedOption.getName());
                    }
                }
                dismiss();
            }
        });

        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        if (mData == null || mData.size() == 0) {
            return;
        }
        mWopOption.setData(mData);
        if (mPickedOption == null) {
            mPickedOption = mData.get(0);
        }
        if (mPickedOption != null) {
            boolean hasCode = false;
            for (IPickerModel pickerModel : mData) {
                if (TextUtils.equals(pickerModel.getCode(), mPickedOption.getCode())) {
                    hasCode = true;
                }
            }
            if (!hasCode) {
                mPickedOption = mData.get(0);
            }
        }
        mWopOption.setPickedOption(mPickedOption);
    }

    @Override
    public BaseAnimatorSet createShowAnimation() {
        return new SlideBottomEnter();
    }

    @Override
    public BaseAnimatorSet createDismissAnimation() {
        return new SlideBottomExit();
    }

    public SCOptionPicker withData(List<IPickerModel> data) {
        mData = data;
        return this;
    }

    public SCOptionPicker withPickedOption(IPickerModel pickedOption) {
        mPickedOption = pickedOption;
        return this;
    }

    public SCOptionPicker withOptionPickedListener(OnOptionPickedListener listener) {
        mOnOptionPickedListener = listener;
        return this;
    }

    public interface OnOptionPickedListener {
        void onOptionPicked(String code, String name);
    }
}