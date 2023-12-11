package com.example.demo.repository;

import com.example.demo.models.BillsGiven;
import com.example.demo.models.CaseInfo;
import com.example.demo.models.CasesReceived;
import com.example.demo.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;



public interface BillsGivenRepository extends JpaRepository<BillsGiven, Long>, JpaSpecificationExecutor<BillsGiven> {
    BillsGiven findByCaseInfo(CaseInfo caseInfo);

    List<BillsGiven> findByUser(UserInfo user);
}
