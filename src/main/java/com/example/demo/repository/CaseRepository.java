package com.example.demo.repository;

import com.example.demo.models.CaseInfo;
import com.example.demo.models.UserInfo;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CaseRepository extends JpaRepository<CaseInfo, Long> , JpaSpecificationExecutor<CaseInfo> {

    CaseInfo findByCaseIdentityNumberAndUserAndCourtType (String caseIdentityNumber, UserInfo user, String courType);


    List<CaseInfo> findAll(Specification<CaseInfo> spec);
    List<CaseInfo> findByUser(UserInfo user);

}
