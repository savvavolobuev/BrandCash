package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by savva.volobuev on 11.07.2017.
 */
public class CardListResponse implements Parcelable{
    @SerializedName("items")
    private List<CardData> items;

    protected CardListResponse(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CardListResponse> CREATOR = new Creator<CardListResponse>() {
        @Override
        public CardListResponse createFromParcel(Parcel in) {
            return new CardListResponse(in);
        }

        @Override
        public CardListResponse[] newArray(int size) {
            return new CardListResponse[size];
        }
    };

    public List<CardData> getItems() {
        return items;
    }

    public void setItems(List<CardData> items) {
        this.items = items;
    }
}
