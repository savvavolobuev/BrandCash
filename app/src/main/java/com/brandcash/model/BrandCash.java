package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by savva.volobuev on 05.07.2017.
 */
public class BrandCash implements Parcelable{

    @SerializedName("name")
    private String name;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("category")
    private String category;


    protected BrandCash(Parcel in) {
        name = in.readString();
        imageUrl = in.readString();
        category = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(imageUrl);
        dest.writeString(category);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BrandCash> CREATOR = new Creator<BrandCash>() {
        @Override
        public BrandCash createFromParcel(Parcel in) {
            return new BrandCash(in);
        }

        @Override
        public BrandCash[] newArray(int size) {
            return new BrandCash[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
