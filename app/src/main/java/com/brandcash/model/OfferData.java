package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by savva.volobuev on 05.07.2017.
 */
public class OfferData implements Parcelable{
    @SerializedName("brand")
    private BrandCash brand;
    @SerializedName("cash_back")
    private int cashBack;
    @SerializedName("description")
    private String description;
    @SerializedName("name")
    private String name;
    @SerializedName("offer_id")
    private int offerId;

    public BrandCash getBrand() {
        return brand;
    }

    public void setBrand(BrandCash brand) {
        this.brand = brand;
    }

    public int getCashBack() {
        return cashBack;
    }

    public void setCashBack(int cashBack) {
        this.cashBack = cashBack;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    protected OfferData(Parcel in) {
        brand = in.readParcelable(BrandCash.class.getClassLoader());
        cashBack = in.readInt();
        description = in.readString();
        name = in.readString();
        offerId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(brand, flags);
        dest.writeInt(cashBack);
        dest.writeString(description);
        dest.writeString(name);
        dest.writeInt(offerId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OfferData> CREATOR = new Creator<OfferData>() {
        @Override
        public OfferData createFromParcel(Parcel in) {
            return new OfferData(in);
        }

        @Override
        public OfferData[] newArray(int size) {
            return new OfferData[size];
        }
    };
}
