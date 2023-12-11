package com.example.demo.repository;

import com.example.demo.models.CaseInfo;
import com.example.demo.models.CasesCompleted;
import com.example.demo.models.CasesReceived;
import com.example.demo.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CasesCompletedRepository extends JpaRepository<CasesCompleted, Long>, JpaSpecificationExecutor<CasesCompleted> {
    CasesCompleted findByCaseInfo(CaseInfo caseInfo);
    List<CasesCompleted> findByUser(UserInfo user);
}
