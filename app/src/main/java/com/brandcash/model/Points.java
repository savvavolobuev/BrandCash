package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by savva.volobuev on 04.07.2017.
 */
public class Points implements Parcelable{

    @SerializedName("balance")
    private int balance;
    @SerializedName("receipts_counts")
    private String receiptsCounts;
    @SerializedName("receipts_sum")
    private String receiptsSum;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getReceiptsCounts() {
        return receiptsCounts;
    }

    public void setReceiptsCounts(String receiptsCounts) {
        this.receiptsCounts = receiptsCounts;
    }

    public String getReceiptsSum() {
        return receiptsSum;
    }

    public void setReceiptsSum(String receiptsSum) {
        this.receiptsSum = receiptsSum;
    }

    protected Points(Parcel in) {
        balance = in.readInt();
        receiptsCounts = in.readString();
        receiptsSum = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(balance);
        dest.writeString(receiptsCounts);
        dest.writeString(receiptsSum);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Points> CREATOR = new Creator<Points>() {
        @Override
        public Points createFromParcel(Parcel in) {
            return new Points(in);
        }

        @Override
        public Points[] newArray(int size) {
            return new Points[size];
        }
    };
}
