package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by savva.volobuev on 04.07.2017.
 */
public class Cash implements Parcelable{
    @SerializedName("balance")
    private int balance;
    @SerializedName("updated_at")
    private String updatedSt;

    protected Cash(Parcel in) {
        balance = in.readInt();
        updatedSt = in.readString();
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getUpdatedSt() {
        return updatedSt;
    }

    public void setUpdatedSt(String updatedSt) {
        this.updatedSt = updatedSt;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(balance);
        dest.writeString(updatedSt);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Cash> CREATOR = new Creator<Cash>() {
        @Override
        public Cash createFromParcel(Parcel in) {
            return new Cash(in);
        }

        @Override
        public Cash[] newArray(int size) {
            return new Cash[size];
        }
    };
}
