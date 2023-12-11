package com.example.demo.repository;

import com.example.demo.models.CaseInfo;
import com.example.demo.models.CasesReceived;
import com.example.demo.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CasesReceivedRepository extends JpaRepository<CasesReceived, Long>,JpaSpecificationExecutor<CasesReceived> {

    CasesReceived findByCaseInfo(CaseInfo caseInfo);

    List<CasesReceived> findByUser(UserInfo user);

}
