package com.example.demo.responseDTO;

import java.math.BigInteger;


public class BillsGivenResponseDTO {

    private String name;

    private String caseIdentityNumber;

    private BigInteger feesBilled;

    private String CourtType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getFeesBilled() {
        return feesBilled;
    }

    public void setFeesBilled(BigInteger feesBilled) {
        this.feesBilled = feesBilled;
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
}
