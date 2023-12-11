package com.example.demo.responseDTO;


import java.math.BigInteger;


public class CasesReceivedResponseDTO {

    private String name;

    private String caseIdentityNumber;

    private String CourtType;

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

    public String getCourtType() {
        return CourtType;
    }

    public void setCourtType(String courtType) {
        CourtType = courtType;
    }

    public BigInteger getFeesAgreed() {
        return feesAgreed;
    }

    public void setFeesAgreed(BigInteger feesAgreed) {
        this.feesAgreed = feesAgreed;
    }
}
