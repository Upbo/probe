package com.radiantraccon.probe.data;

public class ResultData {
    private int imageId;
    private String title;
    private String address;
    private String desc;

    public ResultData(int imageId , String title, String address, String desc) {
        this.imageId = imageId;
        this.title = title;
        this.address = address;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return desc;
    }
    public void setDescription(String desc) {
        this.desc = desc;
    }

    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

}
