package com.example.demo.models;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name="Payment-Received")
public class PaymentReceived {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;;

    @ManyToOne
    @JoinColumn(name = "case_id")
    private CaseInfo caseInfo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserInfo user;


    private String dateOfEntry;

    private BigInteger paymentReceived;

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


    public CaseInfo getCaseInfo() {
        return caseInfo;
    }

    public void setCaseInfo(CaseInfo caseInfo) {
        this.caseInfo = caseInfo;
    }

    public String getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(String dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public BigInteger getPaymentReceived() {
        return paymentReceived;
    }

    public void setPaymentReceived(BigInteger paymentReceived) {
        this.paymentReceived = paymentReceived;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
}
