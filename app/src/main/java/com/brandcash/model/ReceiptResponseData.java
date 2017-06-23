package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by savva.volobuev on 09.06.2017.
 */

public class ReceiptResponseData implements Parcelable {

    @SerializedName("data")
    private ReceiptData data;
    @SerializedName("is_found")
    private Boolean isFound;
    @SerializedName("receipt_id")
    private String receiptId;


    public ReceiptData getData() {
        return data;
    }

    public void setData(ReceiptData data) {
        this.data = data;
    }

    public Boolean isFound() {
        return isFound;
    }

    public void setFound(boolean found) {
        isFound = found;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    protected ReceiptResponseData(Parcel in) {
        data = in.readParcelable(ReceiptData.class.getClassLoader());
        isFound = in.readByte() != 0;
        receiptId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(data, flags);
        dest.writeByte((byte) (isFound ? 1 : 0));
        dest.writeString(receiptId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ReceiptResponseData> CREATOR = new Creator<ReceiptResponseData>() {
        @Override
        public ReceiptResponseData createFromParcel(Parcel in) {
            return new ReceiptResponseData(in);
        }

        @Override
        public ReceiptResponseData[] newArray(int size) {
            return new ReceiptResponseData[size];
        }
    };
}
