package com.example.demo.responseDTO;

import java.math.BigInteger;


public class PaymentReceivedResponseDTO {

    private String name;

    private BigInteger paymentReceived;

    private String caseIdentityNumber;

    private String CourtType;

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

    public String getCourtType() {
        return CourtType;
    }

    public void setCourtType(String courtType) {
        CourtType = courtType;
    }

    public BigInteger getPaymentReceived() {
        return paymentReceived;
    }

    public void setPaymentReceived(BigInteger paymentReceived) {
        this.paymentReceived = paymentReceived;
    }
}
