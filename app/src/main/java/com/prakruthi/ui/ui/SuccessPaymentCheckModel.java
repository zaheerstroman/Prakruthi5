package com.prakruthi.ui.ui;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SuccessPaymentCheckModel {

    @SerializedName("status_code")
    @Expose
    private Boolean statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Order_No")
    @Expose
    private String orderNo;
    @SerializedName("Amount")
    @Expose
    private String amount;

    public Boolean getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Boolean statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
