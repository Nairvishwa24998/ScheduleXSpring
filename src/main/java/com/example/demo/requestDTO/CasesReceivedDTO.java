package com.example.demo.requestDTO;


import java.math.BigInteger;


public class CasesReceivedDTO {

    private String name;

    private String caseIdentityNumber;

    private String courtType;

    private BigInteger feesAgreed;

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


    public BigInteger getFeesAgreed() {
        return feesAgreed;
    }

    public void setFeesAgreed(BigInteger feesAgreed) {
        this.feesAgreed = feesAgreed;
    }

    public String getCourtType() {
        return courtType;
    }

    public void setCourtType(String courtType) {
        this.courtType = courtType;
    }
}
