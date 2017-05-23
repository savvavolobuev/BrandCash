package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by savva.volobuev on 23.05.2017.
 */

public class ActionShop implements Parcelable{

    @SerializedName("y")
    private String y;
    @SerializedName("x")
    private String x;
    @SerializedName("shop_id")
    private int shopId;
    @SerializedName("adress")
    private String address;


    protected ActionShop(Parcel in) {
        y = in.readString();
        x = in.readString();
        shopId = in.readInt();
        address = in.readString();
    }

    public static final Creator<ActionShop> CREATOR = new Creator<ActionShop>() {
        @Override
        public ActionShop createFromParcel(Parcel in) {
            return new ActionShop(in);
        }

        @Override
        public ActionShop[] newArray(int size) {
            return new ActionShop[size];
        }
    };

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(y);
        dest.writeString(x);
        dest.writeInt(shopId);
        dest.writeString(address);
    }
}
