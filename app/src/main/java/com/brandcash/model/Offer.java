package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by savva.volobuev on 23.05.2017.
 */

public class Offer implements Parcelable{

    private String startDate;
    private String actionId;
    private String budget;
    private Brand brand;
    private String expirationDate;
    private int count;
    private String description;
    private int singleCb;
    private String status;
    private String pictUrl;
    private List<ActionShop> actionShops;
    private String name;

    protected Offer(Parcel in) {
        startDate = in.readString();
        actionId = in.readString();
        budget = in.readString();
        brand = in.readParcelable(Brand.class.getClassLoader());
        expirationDate = in.readString();
        count = in.readInt();
        description = in.readString();
        singleCb = in.readInt();
        status = in.readString();
        pictUrl = in.readString();
        actionShops = in.createTypedArrayList(ActionShop.CREATOR);
        name = in.readString();
    }

    public static final Creator<Offer> CREATOR = new Creator<Offer>() {
        @Override
        public Offer createFromParcel(Parcel in) {
            return new Offer(in);
        }

        @Override
        public Offer[] newArray(int size) {
            return new Offer[size];
        }
    };

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSingleCb() {
        return singleCb;
    }

    public void setSingleCb(int singleCb) {
        this.singleCb = singleCb;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPictUrl() {
        return pictUrl;
    }

    public void setPictUrl(String pictUrl) {
        this.pictUrl = pictUrl;
    }

    public List<ActionShop> getActionShops() {
        return actionShops;
    }

    public void setActionShops(List<ActionShop> actionShops) {
        this.actionShops = actionShops;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(startDate);
        dest.writeString(actionId);
        dest.writeString(budget);
        dest.writeParcelable(brand, flags);
        dest.writeString(expirationDate);
        dest.writeInt(count);
        dest.writeString(description);
        dest.writeInt(singleCb);
        dest.writeString(status);
        dest.writeString(pictUrl);
        dest.writeTypedList(actionShops);
        dest.writeString(name);
    }
}
