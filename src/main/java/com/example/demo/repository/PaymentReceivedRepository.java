package com.example.demo.repository;

import com.example.demo.models.CaseInfo;
import com.example.demo.models.PaymentReceived;
import com.example.demo.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PaymentReceivedRepository extends JpaRepository<PaymentReceived, Long>, JpaSpecificationExecutor<PaymentReceived> {
    PaymentReceived findByCaseInfo(CaseInfo caseInfo);

    List<PaymentReceived> findByUser(UserInfo user);
}
