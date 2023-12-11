package com.example.demo.requestDTO;


import jakarta.persistence.*;


public class CaseInfoDTO {


    private String name;

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
}
