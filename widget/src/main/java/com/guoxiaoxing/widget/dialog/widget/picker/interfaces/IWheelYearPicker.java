package com.guoxiaoxing.widget.dialog.widget.picker.interfaces;

/**
 * 年份选择器方法接口
 * <p>
 * Interface of WheelYearPicker
 *
 * @author AigeStudio 2016-07-12
 * @version 1
 */
public interface IWheelYearPicker {
    /**
     * 设置年份范围
     *
     * @param start 开始的年份
     * @param end   结束的年份
     */
    void setYearRange(int start, int end);

    /**
     * 获取开始的年份
     *
     * @return 开始的年份
     */
    int getMinYear();

    /**
     * 设置开始的年份
     *
     * @param start 开始的年份
     */
    void setMinYear(int start);

    /**
     * 获取结束的年份
     *
     * @return 结束的年份
     */
    int getMaxYear();

    /**
     * 设置结束的年份
     *
     * @param end 结束的年份
     */
    void setMaxYear(int end);

    /**
     * 获取年份选择器初始化时选中的年份
     *
     * @return 年份选择器初始化时选中的年份
     */
    int getPickedYear();

    /**
     * 设置年份选择器初始化时选中的年份
     *
     * @param year 年份选择器初始化时选中的年份
     */
    void setPickedYear(int year);

    /**
     * 获取当前选中的年份
     *
     * @return 当前选中的年份
     */
    int getCurrentYear();
}