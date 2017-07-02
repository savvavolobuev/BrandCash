package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by savva.volobuev on 02.07.2017.
 */

public class QrLine implements Parcelable {
    private String n;
    private String t;
    private String s;
    private String fn;
    private String fp;
    private String i;

    public QrLine() {
    }

    protected QrLine(Parcel in) {
        n = in.readString();
        t = in.readString();
        s = in.readString();
        fn = in.readString();
        fp = in.readString();
        i = in.readString();
    }

    public static final Creator<QrLine> CREATOR = new Creator<QrLine>() {
        @Override
        public QrLine createFromParcel(Parcel in) {
            return new QrLine(in);
        }

        @Override
        public QrLine[] newArray(int size) {
            return new QrLine[size];
        }
    };

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public String getFp() {
        return fp;
    }

    public void setFp(String fp) {
        this.fp = fp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(n);
        dest.writeString(t);
        dest.writeString(s);
        dest.writeString(fn);
        dest.writeString(fp);
        dest.writeString(i);
    }
}
