package com.radiantraccon.probe.data;

import com.radiantraccon.probe.site.Quasarzone;
import com.radiantraccon.probe.site.Site;

public class AddressData {
    private int imageId;
    private String title;
    private String address;
    private Site site;

    public AddressData(int imageId, String title, String address, Site site) {
        this.imageId = imageId;
        this.title = title;
        this.address = address;
        this.site = site;
    }

    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
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

    public Site getSite() {
        return site;
    }
    public void setSite(Site site){
        this.site = site;
    }

}
