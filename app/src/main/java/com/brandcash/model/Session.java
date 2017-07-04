package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by savva.volobuev on 02.07.2017.
 */
public class Session implements Parcelable {

    @SerializedName("expires")
    private String expires;
    @SerializedName("sid")
    private String sid;
    @SerializedName("user_id")
    private int userId;

    protected Session(Parcel in) {
        expires = in.readString();
        sid = in.readString();
        userId = in.readInt();
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static final Creator<Session> CREATOR = new Creator<Session>() {
        @Override
        public Session createFromParcel(Parcel in) {
            return new Session(in);
        }

        @Override
        public Session[] newArray(int size) {
            return new Session[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(expires);
        dest.writeString(sid);
        dest.writeInt(userId);
    }
}
