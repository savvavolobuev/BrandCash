package com.brandcash.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by savva.volobuev on 09.06.2017.
 */
public class ReceiptData implements Serializable {

    @SerializedName("cashTotalSum")
    private int cashTotalSum;
    @SerializedName("dateTime")
    private String dateTime;
    @SerializedName("ecashTotalSum")
    private int ecashTotalSum;
    @SerializedName("fiscalDocumentNumber")
    private int fiscalDocumentNumber;
    @SerializedName("fiscalDriveNumber")
    private String fiscalDriveNumber;
    @SerializedName("fiscalSign")
    private long fiscalSign;
    @SerializedName("items")
    private List<ReceiptItem> items;
    @SerializedName("kktRegId")
    private String kktRegId;
    @SerializedName("nds18")
    private int nds;
    @SerializedName("operationType")
    private int operationType;
    @SerializedName("operator")
    private String operator;
    @SerializedName("receiptCode")
    private int receiptCode;
    @SerializedName("requestNumber")
    private int requestNumber;
    @SerializedName("shiftNumber")
    private int shiftNumber;
    @SerializedName("taxationType")
    private int taxationType;
    @SerializedName("totalSum")
    private int totalSum;
    @SerializedName("user")
    private String user;
    @SerializedName("userInn")
    private String userInn;

    public ReceiptData() {
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

    public void setFiscalSign(long fiscalSign) {
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

    public int getShiftNumber() {
        return shiftNumber;
    }

    public void setShiftNumber(int shiftNumber) {
        this.shiftNumber = shiftNumber;
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

}
