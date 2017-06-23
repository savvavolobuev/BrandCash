package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by savva.volobuev on 09.06.2017.
 */

public class Document implements Parcelable{

    @SerializedName("receiptData")
    private ReceiptData receiptData;

    protected Document(Parcel in) {
        receiptData = in.readParcelable(ReceiptData.class.getClassLoader());
    }

    public static final Creator<Document> CREATOR = new Creator<Document>() {
        @Override
        public Document createFromParcel(Parcel in) {
            return new Document(in);
        }

        @Override
        public Document[] newArray(int size) {
            return new Document[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(receiptData, flags);
    }


    public ReceiptData getReceiptData() {
        return receiptData;
    }

    public void setReceiptData(ReceiptData receiptData) {
        this.receiptData = receiptData;
    }
}
