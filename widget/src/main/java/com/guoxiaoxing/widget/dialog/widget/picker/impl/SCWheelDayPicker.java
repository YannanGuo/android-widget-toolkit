package com.guoxiaoxing.widget.dialog.widget.picker.impl;

import android.content.Context;
import android.util.AttributeSet;

import com.guoxiaoxing.widget.dialog.widget.picker.model.IPickerModel;
import com.guoxiaoxing.widget.dialog.widget.picker.model.PickerModel;
import com.guoxiaoxing.widget.dialog.widget.picker.interfaces.IWheelDayPicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日期选择器
 * <p>
 * Picker for Day
 *
 * @author AigeStudio 2016-07-12
 * @version 1
 */
public class SCWheelDayPicker extends SCWheelPicker implements IWheelDayPicker {

    private static final Map<Integer, List<IPickerModel>> DAYS = new HashMap<>();

    private Calendar mCalendar;

    private int mYear, mMonth;
    private int mPickedDay;

    public SCWheelDayPicker(Context context) {
        this(context, null);
    }

    public SCWheelDayPicker(Context context, AttributeSet attrs) {
        super(context, attrs);

        mCalendar = Calendar.getInstance();

        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH);

        updateDays();

        mPickedDay = mCalendar.get(Calendar.DAY_OF_MONTH);

        updateSelectedDay();
    }

    private void updateDays() {
        mCalendar.set(Calendar.YEAR, mYear);
        mCalendar.set(Calendar.MONTH, mMonth);

        int days = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        List<IPickerModel> data = DAYS.get(days);
        if (null == data) {
            data = new ArrayList<>();
            for (int i = 1; i <= days; i++) {
                IPickerModel pickerModel = new PickerModel();
                String formatDay = String.format("%02d", i);
                pickerModel.setCode(formatDay);
                pickerModel.setName(formatDay + "日");
                data.add(pickerModel);
            }
            DAYS.put(days, data);
        }
        super.setData(data);
    }

    private void updateSelectedDay() {
        setSelectedItemPosition(mPickedDay - 1);
    }

    @Override
    public void setData(List data) {
        throw new UnsupportedOperationException("You can not invoke setData in WheelDayPicker");
    }

    @Override
    public int getPickedDay() {
        return mPickedDay;
    }

    @Override
    public void setPickedDay(int day) {
        mPickedDay = day;
        updateSelectedDay();
    }

    @Override
    public int getCurrentDay() {
        return Integer.valueOf(String.valueOf(getData().get(getCurrentItemPosition()).getCode()));
    }

    @Override
    public void setYearAndMonth(int year, int month) {
        mYear = year;
        mMonth = month - 1;
        updateDays();
    }

    @Override
    public int getYear() {
        return mYear;
    }

    @Override
    public void setYear(int year) {
        mYear = year;
        updateDays();
    }

    @Override
    public int getMonth() {
        return mMonth;
    }

    @Override
    public void setMonth(int month) {
        mMonth = month - 1;
        updateDays();
    }
}