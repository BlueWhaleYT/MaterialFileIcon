package com.bluewhaleyt.materialfileicon.model;

public class DrawableResModel {

    int count, drawablePreview;
    String drawableRes;

    public DrawableResModel() {

    }

    public DrawableResModel(int count, String drawableRes, int drawablePreview) {
        this.count = count;
        this.drawableRes = drawableRes;
        this.drawablePreview = drawablePreview;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setDrawableRes(String drawableRes) {
        this.drawableRes = drawableRes;
    }

    public String getDrawableRes() {
        return drawableRes;
    }

    public void setDrawablePreview(int drawablePreview) {
        this.drawablePreview = drawablePreview;
    }

    public int getDrawablePreview() {
        return drawablePreview;
    }

}