package com.radiantraccon.probe;

import android.os.Parcel;
import android.os.Parcelable;

public class KeywordData implements Parcelable {
    private int imageId;
    private String keyword;
    private String desc;

    public KeywordData(int imageId , String keyword, String desc) {
        this.imageId = imageId ;
        this.keyword= keyword;
        this.desc = desc;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageId);
        dest.writeString(keyword);
        dest.writeString(desc);
    }

    protected KeywordData(Parcel in) {
        imageId = in.readInt();
        keyword = in.readString();
        desc = in.readString();
    }

    public static final Creator<KeywordData> CREATOR = new Creator<KeywordData>() {
        @Override
        public KeywordData createFromParcel(Parcel in) {
            return new KeywordData(in);
        }

        @Override
        public KeywordData[] newArray(int size) {
            return new KeywordData[size];
        }
    };
}
