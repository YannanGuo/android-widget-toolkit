package com.guoxiaoxing.widget.dialog.widget.picker.model;


import java.io.Serializable;
import java.util.List;

/**
 * For more information, you can contact me by guoxiaoxing@souche.com
 * 
 * @author guoxiaoxing
 * @since 16/12/27 下午2:47
 */     
public class Province implements Serializable, IPickerModel {

    private String code;
    private String name;
    private List<City> city;

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

    public List<City> getCity() {
        return city;
    }

    public void setCity(List<City> city) {
        this.city = city;
    }
}
