package com.radiantraccon.probe;

public class KeywordData {
    private int imageId;
    private String title;
    private String desc;

    public String getTitle() {
        return title;
    }

    public void SetTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return desc;
    }

    public void SetDescription(String desc) {
        this.desc = desc;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
