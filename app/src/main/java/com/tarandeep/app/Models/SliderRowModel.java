package com.tarandeep.app.Models;

/**
 * Created by Tarandeep on 1/27/2016.
 */
public class SliderRowModel extends  BaseModel {

    private String name;
    private String icon_name;
    private Integer position;

    public String getIcon_name() {
        return icon_name;
    }

    public String getName() {
        return name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setIcon_name(String icon_name) {
        this.icon_name = icon_name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }


}
