package com.example.demo.requestDTO;


public class CasesCompletedDTO {

    private String name;

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

    public void setCourtType(String courtType) {
        this.courtType = courtType;
    }

    public String getCourtType() {
        return courtType;
    }
}
