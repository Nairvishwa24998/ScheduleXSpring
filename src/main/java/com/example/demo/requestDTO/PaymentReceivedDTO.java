package com.example.demo.requestDTO;

import com.example.demo.models.UserInfo;

import java.math.BigInteger;


public class PaymentReceivedDTO {

    private String name;

    private BigInteger paymentReceived;

    private String caseIdentityNumber;

    private String courtType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaseIdentityNumber() {
        return caseIdentityNumber;
    }

    public void setCaseIdentityNumber(String caseIdentityNumber) {
        this.caseIdentityNumber = caseIdentityNumber;
    }

    public BigInteger getPaymentReceived() {
        return paymentReceived;
    }

    public void setPaymentReceived(BigInteger paymentReceived) {
        this.paymentReceived = paymentReceived;
    }

    public String getCourtType() {
        return courtType;
    }

    public void setCourtType(String courtType) {
        this.courtType = courtType;
    }
}
