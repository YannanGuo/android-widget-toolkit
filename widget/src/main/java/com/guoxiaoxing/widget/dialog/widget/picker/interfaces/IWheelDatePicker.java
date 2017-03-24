package com.guoxiaoxing.widget.dialog.widget.picker.interfaces;

import  com.guoxiaoxing.widget.dialog.widget.picker.impl.SCWheelDayPicker;
import  com.guoxiaoxing.widget.dialog.widget.picker.impl.SCWheelMonthPicker;
import  com.guoxiaoxing.widget.dialog.widget.picker.impl.SCWheelYearPicker;

import java.util.Date;

public interface IWheelDatePicker {

    Date getCurrentDate();

    int getItemAlignYear();

    void setItemAlignYear(int align);

    int getItemAlignMonth();

    void setItemAlignMonth(int align);

    int getItemAlignDay();

    void setItemAlignDay(int align);

    SCWheelYearPicker getWheelYearPicker();

    SCWheelMonthPicker getWheelMonthPicker();

    SCWheelDayPicker getWheelDayPicker();
}