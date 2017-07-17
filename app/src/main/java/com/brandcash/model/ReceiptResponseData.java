package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by savva.volobuev on 09.06.2017.
 */

public class ReceiptResponseData implements Serializable {

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

    public Boolean getFound() {
        return isFound;
    }

    public void setFound(Boolean found) {
        isFound = found;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public ReceiptResponseData() {
    }

}
