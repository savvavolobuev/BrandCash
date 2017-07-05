package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by savva.volobuev on 05.07.2017.
 */
public class CashListResponseData implements Parcelable {
    @SerializedName("balance")
    private int balance;
    @SerializedName("limit")
    private int limit;
    @SerializedName("offset")
    private int offset;
    @SerializedName("total_count")
    private int totalCount;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("transactions")
    private List<Transaction> transactions;

    protected CashListResponseData(Parcel in) {
        balance = in.readInt();
        limit = in.readInt();
        offset = in.readInt();
        totalCount = in.readInt();
        updatedAt = in.readString();
        transactions = in.createTypedArrayList(Transaction.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(balance);
        dest.writeInt(limit);
        dest.writeInt(offset);
        dest.writeInt(totalCount);
        dest.writeString(updatedAt);
        dest.writeTypedList(transactions);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CashListResponseData> CREATOR = new Creator<CashListResponseData>() {
        @Override
        public CashListResponseData createFromParcel(Parcel in) {
            return new CashListResponseData(in);
        }

        @Override
        public CashListResponseData[] newArray(int size) {
            return new CashListResponseData[size];
        }
    };

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
