package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by savva.volobuev on 09.06.2017.
 */
public class ReceiptItem implements Serializable{

    private String barcode;
    private int nds18;
    private int sum;
    private String name;
    private int quantity;
    private int price;

    protected ReceiptItem(Parcel in) {
        barcode = in.readString();
        nds18 = in.readInt();
        sum = in.readInt();
        name = in.readString();
        quantity = in.readInt();
        price = in.readInt();
    }


    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getNds18() {
        return nds18;
    }

    public void setNds18(int nds18) {
        this.nds18 = nds18;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
