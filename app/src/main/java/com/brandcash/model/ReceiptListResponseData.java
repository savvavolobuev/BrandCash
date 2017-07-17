package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by savva.volobuev on 25.06.2017.
 */

public class ReceiptListResponseData implements Serializable {
    @SerializedName("items")
    private List<ReceiptResponseData> items;
    @SerializedName("limit")
    private int limit;
    @SerializedName("offset")
    private int offset;
    @SerializedName("total_count")
    private int totalCount;
    @SerializedName("total_sum")
    private int totalSum;


    public List<ReceiptResponseData> getItems() {
        return items;
    }

    public void setItems(List<ReceiptResponseData> items) {
        this.items = items;
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

    public int getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(int totalSum) {
        this.totalSum = totalSum;
    }
}
