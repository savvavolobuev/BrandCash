package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by savva.volobuev on 09.06.2017.
 */

public class Document implements Serializable{

    @SerializedName("receiptData")
    private ReceiptData receiptData;

    public ReceiptData getReceiptData() {
        return receiptData;
    }

    public void setReceiptData(ReceiptData receiptData) {
        this.receiptData = receiptData;
    }
}
