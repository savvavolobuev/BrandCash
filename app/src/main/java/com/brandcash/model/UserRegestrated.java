package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by savva.volobuev on 02.07.2017.
 */
public class UserRegestrated implements Parcelable{
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("phone")
    private String phone;
    @SerializedName("user_id")
    private int userId;

    protected UserRegestrated(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        phone = in.readString();
        userId = in.readInt();
    }

    public static final Creator<UserRegestrated> CREATOR = new Creator<UserRegestrated>() {
        @Override
        public UserRegestrated createFromParcel(Parcel in) {
            return new UserRegestrated(in);
        }

        @Override
        public UserRegestrated[] newArray(int size) {
            return new UserRegestrated[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(phone);
        dest.writeInt(userId);
    }
}
