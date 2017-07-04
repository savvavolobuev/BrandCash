package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by savva.volobuev on 04.07.2017.
 */
public class AccountMain implements Parcelable{

    private Cash cash;
    private int offersCount;
    private Points points;

    protected AccountMain(Parcel in) {
        cash = in.readParcelable(Cash.class.getClassLoader());
        offersCount = in.readInt();
        points = in.readParcelable(Points.class.getClassLoader());
    }


    public Cash getCash() {
        return cash;
    }

    public void setCash(Cash cash) {
        this.cash = cash;
    }

    public int getOffersCount() {
        return offersCount;
    }

    public void setOffersCount(int offersCount) {
        this.offersCount = offersCount;
    }

    public Points getPoints() {
        return points;
    }

    public void setPoints(Points points) {
        this.points = points;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(cash, flags);
        dest.writeInt(offersCount);
        dest.writeParcelable(points, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AccountMain> CREATOR = new Creator<AccountMain>() {
        @Override
        public AccountMain createFromParcel(Parcel in) {
            return new AccountMain(in);
        }

        @Override
        public AccountMain[] newArray(int size) {
            return new AccountMain[size];
        }
    };
}
