package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by savva.volobuev on 05.07.2017.
 */
public class OfferCash implements Parcelable {
    @SerializedName("name")
    private String name;
    @SerializedName("offer_id")
    private int offerId;
    @SerializedName("brand")
    private BrandCash brand;

    protected OfferCash(Parcel in) {
        name = in.readString();
        offerId = in.readInt();
        brand = in.readParcelable(BrandCash.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(offerId);
        dest.writeParcelable(brand, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OfferCash> CREATOR = new Creator<OfferCash>() {
        @Override
        public OfferCash createFromParcel(Parcel in) {
            return new OfferCash(in);
        }

        @Override
        public OfferCash[] newArray(int size) {
            return new OfferCash[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public BrandCash getBrand() {
        return brand;
    }

    public void setBrand(BrandCash brand) {
        this.brand = brand;
    }
}
