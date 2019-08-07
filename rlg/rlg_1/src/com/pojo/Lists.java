package com.pojo;

import java.util.Date;

public class Lists {

    private  int  orderNo;
    private  double  payment;
    private  int paymentType;
    private  String paymentTypeDesc;
private int postage;
    private int status;
    private  String   statusDesc;
    private Date paymentTime;
    private Date sendTime;
    private Date endTime;
    private Date closeTime;
    private  Date createTime;

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentTypeDesc() {
        return paymentTypeDesc;
    }

    public void setPaymentTypeDesc(String paymentTypeDesc) {
        this.paymentTypeDesc = paymentTypeDesc;
    }

    public int getPostage() {
        return postage;
    }

    public void setPostage(int postage) {
        this.postage = postage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Lists{" +
                "orderNo=" + orderNo +
                ", payment=" + payment +
                ", paymentType=" + paymentType +
                ", paymentTypeDesc='" + paymentTypeDesc + '\'' +
                ", postage=" + postage +
                ", status=" + status +
                ", statusDesc='" + statusDesc + '\'' +
                ", paymentTime=" + paymentTime +
                ", sendTime=" + sendTime +
                ", endTime=" + endTime +
                ", closeTime=" + closeTime +
                ", createTime=" + createTime +
                '}';
    }
}
