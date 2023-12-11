package com.example.demo.specifications;

import com.example.demo.models.CaseInfo;
import com.example.demo.models.UserInfo;
import org.springframework.data.jpa.domain.Specification;

public class CaseSearchSpecifications {


    public static Specification<CaseInfo> byCourtType(String courtType) {
        return (root, query, builder) -> {
            return builder.equal(root.get("courtType"),courtType);
        };
    }

    public static Specification<CaseInfo> byName(String name) {
        return (root, query, builder) -> {
            return builder.equal(root.get("name"),name);
        };
    }

    public static Specification<CaseInfo> byCaseIdentityNumber(String caseIdentityNumber) {
        return (root, query, builder) -> {
            return builder.equal(root.get("caseIdentityNumber"),caseIdentityNumber);
        };
    }

    public static Specification<CaseInfo> byUser(UserInfo user) {
        return (root, query, builder) -> {
            return builder.equal(root.get("user"),user);
        };
    }


}
