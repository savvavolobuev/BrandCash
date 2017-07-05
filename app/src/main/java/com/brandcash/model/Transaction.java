package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by savva.volobuev on 05.07.2017.
 */
public class Transaction implements Parcelable{
    @SerializedName("sum")
    private String sum;
    @SerializedName("time")
    private String time;
    @SerializedName("offer")
    private OfferCash offer;

    protected Transaction(Parcel in) {
        sum = in.readString();
        time = in.readString();
        offer = in.readParcelable(OfferCash.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sum);
        dest.writeString(time);
        dest.writeParcelable(offer, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public OfferCash getOffer() {
        return offer;
    }

    public void setOffer(OfferCash offer) {
        this.offer = offer;
    }
}
