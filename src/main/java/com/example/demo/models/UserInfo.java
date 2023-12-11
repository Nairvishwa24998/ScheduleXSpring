package com.example.demo.models;


import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "Users")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private String accountCreationDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CasesReceived> casesReceived;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CasesCompleted> casesCompleted;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BillsGiven> billsGiven;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PaymentReceived> paymentReceived;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CaseInfo> caseInfo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountCreationDate() {
        return accountCreationDate;
    }

    public void setAccountCreationDate(String accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    public Set<CaseInfo> getCaseInfo() {
        return caseInfo;
    }

    public void setCaseInfo(Set<CaseInfo> caseInfo) {
        this.caseInfo = caseInfo;
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
}
