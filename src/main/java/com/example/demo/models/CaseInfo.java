package com.example.demo.models;


import jakarta.persistence.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigInteger;
import java.util.Set;

@Entity
@Table(name="Case-Info")
public class CaseInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String caseIdentityNumber;

    @Column(nullable = false)
    private String courtType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserInfo user;

    @OneToMany(mappedBy = "caseInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CasesReceived> casesReceived;

    @OneToMany(mappedBy = "caseInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CasesCompleted> casesCompleted;

    @OneToMany(mappedBy = "caseInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BillsGiven> billsGiven;

    @OneToMany(mappedBy = "caseInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PaymentReceived> paymentReceived;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<CasesReceived> getCasesReceived() {
        return casesReceived;
    }

    public void setCasesReceived(Set<CasesReceived> casesReceived) {
        this.casesReceived = casesReceived;
    }

    public Set<CasesCompleted> getCasesCompleted() {
        return casesCompleted;
    }

    public void setCasesCompleted(Set<CasesCompleted> casesCompleted) {
        this.casesCompleted = casesCompleted;
    }

    public Set<BillsGiven> getBillsGiven() {
        return billsGiven;
    }

    public void setBillsGiven(Set<BillsGiven> billsGiven) {
        this.billsGiven = billsGiven;
    }

    public Set<PaymentReceived> getPaymentReceived() {
        return paymentReceived;
    }

    public void setPaymentReceived(Set<PaymentReceived> paymentReceived) {
        this.paymentReceived = paymentReceived;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public String getCourtType() {
        return courtType;
    }

    public void setCourtType(String courtType) {
        this.courtType = courtType;
    }
}
