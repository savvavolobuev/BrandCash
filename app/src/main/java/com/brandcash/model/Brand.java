package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by savva.volobuev on 23.05.2017.
 */

public class Brand implements Parcelable {

    @SerializedName("brand_description")
    private String brandDescription;
    @SerializedName("brand_name")
    private String brandName;
    @SerializedName("brand_id")
    private int brandId;
    @SerializedName("brand_image_url")
    private String brandImageUrl;
    @SerializedName("brand_site_url")
    private String brandSiteUrl;

    protected Brand(Parcel in) {
        brandDescription = in.readString();
        brandName = in.readString();
        brandId = in.readInt();
        brandImageUrl = in.readString();
        brandSiteUrl = in.readString();
    }

    public static final Creator<Brand> CREATOR = new Creator<Brand>() {
        @Override
        public Brand createFromParcel(Parcel in) {
            return new Brand(in);
        }

        @Override
        public Brand[] newArray(int size) {
            return new Brand[size];
        }
    };

    public String getBrandDescription() {
        return brandDescription;
    }

    public void setBrandDescription(String brandDescription) {
        this.brandDescription = brandDescription;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrandImageUrl() {
        return brandImageUrl;
    }

    public void setBrandImageUrl(String brandImageUrl) {
        this.brandImageUrl = brandImageUrl;
    }

    public String getBrandSiteUrl() {
        return brandSiteUrl;
    }

    public void setBrandSiteUrl(String brandSiteUrl) {
        this.brandSiteUrl = brandSiteUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(brandDescription);
        dest.writeString(brandName);
        dest.writeInt(brandId);
        dest.writeString(brandImageUrl);
        dest.writeString(brandSiteUrl);
    }
}
