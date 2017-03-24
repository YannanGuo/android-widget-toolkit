package com.guoxiaoxing.widget.dialog.widget.picker.impl;

import android.content.Context;
import android.util.AttributeSet;

import com.guoxiaoxing.widget.dialog.widget.picker.model.IPickerModel;
import com.guoxiaoxing.widget.dialog.widget.picker.model.PickerModel;
import com.guoxiaoxing.widget.dialog.widget.picker.interfaces.IWheelYearPicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 年份选择器
 * <p>
 * Picker for Years
 *
 * @author AigeStudio 2016-07-12
 * @version 1
 */
public class SCWheelYearPicker extends SCWheelPicker implements IWheelYearPicker {

    private int mMinYear;
    private int mMaxYear;
    private int mPickedYear;

    public SCWheelYearPicker(Context context) {
        this(context, null);
    }

    public SCWheelYearPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        updateYears();
        mPickedYear = Calendar.getInstance().get(Calendar.YEAR);
        updateSelectedYear();
    }

    private void updateYears() {
        List<IPickerModel> data = new ArrayList<>();
        for (int i = mMinYear; i <= mMaxYear; i++) {
            IPickerModel pickerModel = new PickerModel();
            String formatYear = String.format("%04d", i);
            pickerModel.setCode(formatYear);
            pickerModel.setName(formatYear + "年");
            data.add(pickerModel);
        }
        super.setData(data);
    }

    private void updateSelectedYear() {
        setSelectedItemPosition(mPickedYear - mMinYear);
    }

    @Override
    public void setData(List data) {
        throw new UnsupportedOperationException("You can not invoke setData in WheelYearPicker");
    }

    @Override
    public void setYearRange(int minYear, int maxYear) {
        mMinYear = minYear;
        mMaxYear = maxYear;
        mPickedYear = getCurrentYear();
        updateYears();
        updateSelectedYear();
    }

    public int getMinYear() {
        return mMinYear;
    }

    public void setMinYear(int minYear) {
        mMinYear = minYear;
        mPickedYear = getCurrentYear();
        updateYears();
        updateSelectedYear();
    }

    public int getMaxYear() {
        return mMaxYear;
    }

    public void setMaxYear(int maxYear) {
        mMaxYear = maxYear;
        updateYears();
    }

    public int getPickedYear() {
        return mPickedYear;
    }

    public void setPickedYear(int year) {
        mPickedYear = year;
        updateSelectedYear();
    }

    @Override
    public int getCurrentYear() {
        return Integer.valueOf(String.valueOf(getData().get(getCurrentItemPosition()).getCode()));
    }
}