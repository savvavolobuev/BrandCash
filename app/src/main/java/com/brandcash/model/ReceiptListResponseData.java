package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by savva.volobuev on 25.06.2017.
 */

public class ReceiptListResponseData implements Parcelable {
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

    protected ReceiptListResponseData(Parcel in) {
        items = in.createTypedArrayList(ReceiptResponseData.CREATOR);
        limit = in.readInt();
        offset = in.readInt();
        totalCount = in.readInt();
        totalSum = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(items);
        dest.writeInt(limit);
        dest.writeInt(offset);
        dest.writeInt(totalCount);
        dest.writeInt(totalSum);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ReceiptListResponseData> CREATOR = new Creator<ReceiptListResponseData>() {
        @Override
        public ReceiptListResponseData createFromParcel(Parcel in) {
            return new ReceiptListResponseData(in);
        }

        @Override
        public ReceiptListResponseData[] newArray(int size) {
            return new ReceiptListResponseData[size];
        }
    };

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
