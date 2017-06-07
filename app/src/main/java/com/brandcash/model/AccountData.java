package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by savva.volobuev on 07.06.2017.
 */
public class AccountData implements Parcelable{

    @SerializedName("reflink")
    private String reflink;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("brand_id")
    private int brandId;
    @SerializedName("user_profile_id")
    private int userProfileId;
    @SerializedName("user_email")
    private String userEmail;


    protected AccountData(Parcel in) {
        reflink = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        brandId = in.readInt();
        userProfileId = in.readInt();
        userEmail = in.readString();
    }

    public static final Creator<AccountData> CREATOR = new Creator<AccountData>() {
        @Override
        public AccountData createFromParcel(Parcel in) {
            return new AccountData(in);
        }

        @Override
        public AccountData[] newArray(int size) {
            return new AccountData[size];
        }
    };

    public String getReflink() {
        return reflink;
    }

    public void setReflink(String reflink) {
        this.reflink = reflink;
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

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(int userProfileId) {
        this.userProfileId = userProfileId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(reflink);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeInt(brandId);
        dest.writeInt(userProfileId);
        dest.writeString(userEmail);
    }
}
