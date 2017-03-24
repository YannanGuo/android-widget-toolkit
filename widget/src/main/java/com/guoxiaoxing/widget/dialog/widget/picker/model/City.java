package com.guoxiaoxing.widget.dialog.widget.picker.model;

import java.io.Serializable;
import java.util.List;

/**
 * For more information, you can contact me by guoxiaoxing@souche.com
 *
 * @author guoxiaoxing
 * @since 16/12/27 下午2:25
 */
public class City implements Serializable, IPickerModel {

    private String code;
    private String name;
    private List<IPickerModel> area;

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public List<IPickerModel> getArea() {
        return area;
    }

    public void setArea(List<IPickerModel> area) {
        this.area = area;
    }
}
