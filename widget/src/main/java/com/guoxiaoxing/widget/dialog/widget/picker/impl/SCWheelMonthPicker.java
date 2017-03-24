package com.guoxiaoxing.widget.dialog.widget.picker.impl;

import android.content.Context;
import android.util.AttributeSet;

import com.guoxiaoxing.widget.dialog.widget.picker.model.IPickerModel;
import com.guoxiaoxing.widget.dialog.widget.picker.model.PickerModel;
import com.guoxiaoxing.widget.dialog.widget.picker.interfaces.IWheelMonthPicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 月份选择器
 * <p>
 * Picker for Months
 *
 * @author AigeStudio 2016-07-12
 * @version 1
 */
public class SCWheelMonthPicker extends SCWheelPicker implements IWheelMonthPicker {

    private int mPickedMonth;

    public SCWheelMonthPicker(Context context) {
        this(context, null);
    }

    public SCWheelMonthPicker(Context context, AttributeSet attrs) {
        super(context, attrs);

        List<IPickerModel> data = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            IPickerModel pickerModel = new PickerModel();
            String formatMonth = String.format("%02d", i);
            pickerModel.setCode(formatMonth);
            pickerModel.setName(formatMonth + "月");
            data.add(pickerModel);
        }
        super.setData(data);

        mPickedMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        updatePickedMonth();
    }

    private void updatePickedMonth() {
        setSelectedItemPosition(mPickedMonth - 1);
    }

    @Override
    public void setData(List data) {
        throw new UnsupportedOperationException("You can not invoke setData in WheelMonthPicker");
    }

    @Override
    public int getPickedMonth() {
        return mPickedMonth;
    }

    @Override
    public void setPickedMonth(int month) {
        mPickedMonth = month;
        updatePickedMonth();
    }

    @Override
    public int getCurrentMonth() {
        return Integer.valueOf(String.valueOf(getData().get(getCurrentItemPosition()).getCode()));
    }
}