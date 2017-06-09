package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by savva.volobuev on 09.06.2017.
 */
public class Receipt implements Parcelable{

    private int receiptCode;
    private String dateTime;
    private String fiscalDriveNumber;
    private int taxationType;
    private int fiscalSign;
    private String userInn;
    private int operationType;
    private int nds18;
    private List<ReceiptItem> items;
    private int ecashTotalSum;
    private int totalSum;
    private String kktRegId;
    private String user;
    private int shiftNumber;
    private String operator;
    private int cashTotalSum;
    private int fiscalDocumentNumber;
    private int requestNumber;

    protected Receipt(Parcel in) {
        receiptCode = in.readInt();
        dateTime = in.readString();
        fiscalDriveNumber = in.readString();
        taxationType = in.readInt();
        fiscalSign = in.readInt();
        userInn = in.readString();
        operationType = in.readInt();
        nds18 = in.readInt();
        items = in.createTypedArrayList(ReceiptItem.CREATOR);
        ecashTotalSum = in.readInt();
        totalSum = in.readInt();
        kktRegId = in.readString();
        user = in.readString();
        shiftNumber = in.readInt();
        operator = in.readString();
        cashTotalSum = in.readInt();
        fiscalDocumentNumber = in.readInt();
        requestNumber = in.readInt();
    }

    public static final Creator<Receipt> CREATOR = new Creator<Receipt>() {
        @Override
        public Receipt createFromParcel(Parcel in) {
            return new Receipt(in);
        }

        @Override
        public Receipt[] newArray(int size) {
            return new Receipt[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(receiptCode);
        dest.writeString(dateTime);
        dest.writeString(fiscalDriveNumber);
        dest.writeInt(taxationType);
        dest.writeInt(fiscalSign);
        dest.writeString(userInn);
        dest.writeInt(operationType);
        dest.writeInt(nds18);
        dest.writeTypedList(items);
        dest.writeInt(ecashTotalSum);
        dest.writeInt(totalSum);
        dest.writeString(kktRegId);
        dest.writeString(user);
        dest.writeInt(shiftNumber);
        dest.writeString(operator);
        dest.writeInt(cashTotalSum);
        dest.writeInt(fiscalDocumentNumber);
        dest.writeInt(requestNumber);
    }

    public int getReceiptCode() {
        return receiptCode;
    }

    public void setReceiptCode(int receiptCode) {
        this.receiptCode = receiptCode;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getFiscalDriveNumber() {
        return fiscalDriveNumber;
    }

    public void setFiscalDriveNumber(String fiscalDriveNumber) {
        this.fiscalDriveNumber = fiscalDriveNumber;
    }

    public int getTaxationType() {
        return taxationType;
    }

    public void setTaxationType(int taxationType) {
        this.taxationType = taxationType;
    }

    public int getFiscalSign() {
        return fiscalSign;
    }

    public void setFiscalSign(int fiscalSign) {
        this.fiscalSign = fiscalSign;
    }

    public String getUserInn() {
        return userInn;
    }

    public void setUserInn(String userInn) {
        this.userInn = userInn;
    }

    public int getOperationType() {
        return operationType;
    }

    public void setOperationType(int operationType) {
        this.operationType = operationType;
    }

    public int getNds18() {
        return nds18;
    }

    public void setNds18(int nds18) {
        this.nds18 = nds18;
    }

    public List<ReceiptItem> getItems() {
        return items;
    }

    public void setItems(List<ReceiptItem> items) {
        this.items = items;
    }

    public int getEcashTotalSum() {
        return ecashTotalSum;
    }

    public void setEcashTotalSum(int ecashTotalSum) {
        this.ecashTotalSum = ecashTotalSum;
    }

    public int getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(int totalSum) {
        this.totalSum = totalSum;
    }

    public String getKktRegId() {
        return kktRegId;
    }

    public void setKktRegId(String kktRegId) {
        this.kktRegId = kktRegId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getShiftNumber() {
        return shiftNumber;
    }

    public void setShiftNumber(int shiftNumber) {
        this.shiftNumber = shiftNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getCashTotalSum() {
        return cashTotalSum;
    }

    public void setCashTotalSum(int cashTotalSum) {
        this.cashTotalSum = cashTotalSum;
    }

    public int getFiscalDocumentNumber() {
        return fiscalDocumentNumber;
    }

    public void setFiscalDocumentNumber(int fiscalDocumentNumber) {
        this.fiscalDocumentNumber = fiscalDocumentNumber;
    }

    public int getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(int requestNumber) {
        this.requestNumber = requestNumber;
    }
}
