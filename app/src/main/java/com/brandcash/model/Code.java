package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by savva.volobuev on 02.07.2017.
 */
public class Code implements Parcelable{

    private String code;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    protected Code(Parcel in) {
        code = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Code> CREATOR = new Creator<Code>() {
        @Override
        public Code createFromParcel(Parcel in) {
            return new Code(in);
        }

        @Override
        public Code[] newArray(int size) {
            return new Code[size];
        }
    };
}
