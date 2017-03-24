package com.guoxiaoxing.widget.dialog.widget.picker.model;

/**
 * For more information, you can contact me by guoxiaoxing@souche.com
 *
 * @author guoxiaoxing
 * @since 16/12/16 下午2:01
 */
public class PickerModel implements IPickerModel {

    private String code;
    private String name;

    public PickerModel() {

    }

    public PickerModel(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PickerModel)) return false;
        PickerModel that = (PickerModel) o;
        return getCode() != null ? getCode().equals(that.getCode()) : that.getCode() == null;
    }

    @Override
    public int hashCode() {
        return getCode() != null ? getCode().hashCode() : 0;
    }
}