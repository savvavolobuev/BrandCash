package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by savva.volobuev on 02.07.2017.
 */

public class PhoneNum implements Parcelable{

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public PhoneNum() {
    }

    protected PhoneNum(Parcel in) {
        phone = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(phone);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PhoneNum> CREATOR = new Creator<PhoneNum>() {
        @Override
        public PhoneNum createFromParcel(Parcel in) {
            return new PhoneNum(in);
        }

        @Override
        public PhoneNum[] newArray(int size) {
            return new PhoneNum[size];
        }
    };
}
