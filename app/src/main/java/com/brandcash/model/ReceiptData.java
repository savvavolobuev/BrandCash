package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by savva.volobuev on 09.06.2017.
 */
public class ReceiptData implements Parcelable {

    private String addressToCheckFiscalSign;
    private int cashTotalSum;
    private String dateTime;
    private int ecashTotalSum;
    private int fiscalDocumentNumber;
    private String fiscalDriveNumber;
    private long fiscalSign;
    private List<ReceiptItem> items;
    private String kktRegId;
    private List<ModifierItem> modifiers;
    private int nds;
    private int operationType;
    private String operator;
    private int receiptCode;
    private int requestNumber;
    private String retailPlaceAddress;
    private int shiftNumber;
    private List<StoreNoItems> storenoItems;
    private int taxationType;
    private int totalSum;
    private String user;
    private String userInn;
    private Boolean isFound;

    protected ReceiptData(Parcel in) {
        addressToCheckFiscalSign = in.readString();
        cashTotalSum = in.readInt();
        dateTime = in.readString();
        ecashTotalSum = in.readInt();
        fiscalDocumentNumber = in.readInt();
        fiscalDriveNumber = in.readString();
        fiscalSign = in.readInt();
        items = in.createTypedArrayList(ReceiptItem.CREATOR);
        kktRegId = in.readString();
        nds = in.readInt();
        operationType = in.readInt();
        operator = in.readString();
        receiptCode = in.readInt();
        requestNumber = in.readInt();
        retailPlaceAddress = in.readString();
        shiftNumber = in.readInt();
        taxationType = in.readInt();
        totalSum = in.readInt();
        user = in.readString();
        userInn = in.readString();
    }


    public String getAddressToCheckFiscalSign() {
        return addressToCheckFiscalSign;
    }

    public void setAddressToCheckFiscalSign(String addressToCheckFiscalSign) {
        this.addressToCheckFiscalSign = addressToCheckFiscalSign;
    }

    public int getCashTotalSum() {
        return cashTotalSum;
    }

    public void setCashTotalSum(int cashTotalSum) {
        this.cashTotalSum = cashTotalSum;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getEcashTotalSum() {
        return ecashTotalSum;
    }

    public void setEcashTotalSum(int ecashTotalSum) {
        this.ecashTotalSum = ecashTotalSum;
    }

    public int getFiscalDocumentNumber() {
        return fiscalDocumentNumber;
    }

    public void setFiscalDocumentNumber(int fiscalDocumentNumber) {
        this.fiscalDocumentNumber = fiscalDocumentNumber;
    }

    public String getFiscalDriveNumber() {
        return fiscalDriveNumber;
    }

    public void setFiscalDriveNumber(String fiscalDriveNumber) {
        this.fiscalDriveNumber = fiscalDriveNumber;
    }

    public long getFiscalSign() {
        return fiscalSign;
    }

    public void setFiscalSign(int fiscalSign) {
        this.fiscalSign = fiscalSign;
    }

    public List<ReceiptItem> getItems() {
        return items;
    }

    public void setItems(List<ReceiptItem> items) {
        this.items = items;
    }

    public String getKktRegId() {
        return kktRegId;
    }

    public void setKktRegId(String kktRegId) {
        this.kktRegId = kktRegId;
    }

    public List<ModifierItem> getModifiers() {
        return modifiers;
    }

    public void setModifiers(List<ModifierItem> modifiers) {
        this.modifiers = modifiers;
    }

    public int getNds() {
        return nds;
    }

    public void setNds(int nds) {
        this.nds = nds;
    }

    public int getOperationType() {
        return operationType;
    }

    public void setOperationType(int operationType) {
        this.operationType = operationType;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getReceiptCode() {
        return receiptCode;
    }

    public void setReceiptCode(int receiptCode) {
        this.receiptCode = receiptCode;
    }

    public int getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(int requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String getRetailPlaceAddress() {
        return retailPlaceAddress;
    }

    public void setRetailPlaceAddress(String retailPlaceAddress) {
        this.retailPlaceAddress = retailPlaceAddress;
    }

    public int getShiftNumber() {
        return shiftNumber;
    }

    public void setShiftNumber(int shiftNumber) {
        this.shiftNumber = shiftNumber;
    }

    public List<StoreNoItems> getStorenoItems() {
        return storenoItems;
    }

    public void setStorenoItems(List<StoreNoItems> storenoItems) {
        this.storenoItems = storenoItems;
    }

    public int getTaxationType() {
        return taxationType;
    }

    public void setTaxationType(int taxationType) {
        this.taxationType = taxationType;
    }

    public int getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(int totalSum) {
        this.totalSum = totalSum;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserInn() {
        return userInn;
    }

    public void setUserInn(String userInn) {
        this.userInn = userInn;
    }

    public static final Creator<ReceiptData> CREATOR = new Creator<ReceiptData>() {
        @Override
        public ReceiptData createFromParcel(Parcel in) {
            return new ReceiptData(in);
        }

        @Override
        public ReceiptData[] newArray(int size) {
            return new ReceiptData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(addressToCheckFiscalSign);
        dest.writeInt(cashTotalSum);
        dest.writeString(dateTime);
        dest.writeInt(ecashTotalSum);
        dest.writeInt(fiscalDocumentNumber);
        dest.writeString(fiscalDriveNumber);
        dest.writeLong(fiscalSign);
        dest.writeTypedList(items);
        dest.writeString(kktRegId);
        dest.writeInt(nds);
        dest.writeInt(operationType);
        dest.writeString(operator);
        dest.writeInt(receiptCode);
        dest.writeInt(requestNumber);
        dest.writeString(retailPlaceAddress);
        dest.writeInt(shiftNumber);
        dest.writeInt(taxationType);
        dest.writeInt(totalSum);
        dest.writeString(user);
        dest.writeString(userInn);
    }
}
