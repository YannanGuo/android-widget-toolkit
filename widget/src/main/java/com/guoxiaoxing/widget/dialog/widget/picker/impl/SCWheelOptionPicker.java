package com.guoxiaoxing.widget.dialog.widget.picker.impl;

import android.content.Context;
import android.util.AttributeSet;

import com.guoxiaoxing.widget.dialog.widget.picker.model.IPickerModel;

import java.util.List;

/**
 * For more information, you can contact me by guoxiaoxing@souche.com
 *
 * @author guoxiaoxing
 * @since 16/12/27 下午12:54
 */
public class SCWheelOptionPicker extends SCWheelPicker {

    private IPickerModel mPickedOption;

    public SCWheelOptionPicker(Context context) {
        super(context);
    }

    public SCWheelOptionPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setData(List data) {
        super.setData(data);
        updatePickedOption(mPickedOption);
    }

    public void setPickedOption(IPickerModel pickedOption) {
        mPickedOption = pickedOption;
        updatePickedOption(mPickedOption);
    }

    public IPickerModel getPickedOption() {
        return mPickedOption;
    }

    private void updatePickedOption(IPickerModel pickedOption) {
        int position = getData().indexOf(pickedOption);
        setSelectedItemPosition(position);
    }
}