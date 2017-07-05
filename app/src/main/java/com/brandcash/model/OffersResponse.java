package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by savva.volobuev on 05.07.2017.
 */

public class OffersResponse implements Parcelable {
    @SerializedName("items")
    private List<OfferData> items;
    @SerializedName("limit")
    private int limit;
    @SerializedName("offser")
    private int offset;
    @SerializedName("total_count")
    private int totalCount;


    public List<OfferData> getItems() {
        return items;
    }

    public void setItems(List<OfferData> items) {
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

    protected OffersResponse(Parcel in) {
        items = in.createTypedArrayList(OfferData.CREATOR);
        limit = in.readInt();
        offset = in.readInt();
        totalCount = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(items);
        dest.writeInt(limit);
        dest.writeInt(offset);
        dest.writeInt(totalCount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OffersResponse> CREATOR = new Creator<OffersResponse>() {
        @Override
        public OffersResponse createFromParcel(Parcel in) {
            return new OffersResponse(in);
        }

        @Override
        public OffersResponse[] newArray(int size) {
            return new OffersResponse[size];
        }
    };
}
