package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by savva.volobuev on 11.07.2017.
 */
public class CardData implements Parcelable{

    @SerializedName("card_id")
    private int cardId;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("number")
    private String number;


    public CardData() {
    }

    protected CardData(Parcel in) {
        cardId = in.readInt();
        firstName = in.readString();
        lastName = in.readString();
        number = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(cardId);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(number);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CardData> CREATOR = new Creator<CardData>() {
        @Override
        public CardData createFromParcel(Parcel in) {
            return new CardData(in);
        }

        @Override
        public CardData[] newArray(int size) {
            return new CardData[size];
        }
    };

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
