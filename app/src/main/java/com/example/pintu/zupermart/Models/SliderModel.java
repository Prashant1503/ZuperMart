package com.example.pintu.zupermart.Models;

public class SliderModel {

    private int Banner;
    private String backgroundColor;

    public SliderModel(int banner, String backgroundColor) {
        Banner = banner;
        this.backgroundColor = backgroundColor;
    }

    public int getBanner() {
        return Banner;
    }

    public void setBanner(int banner) {
        Banner = banner;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
