package com.example.demo.service.implementation;

import com.example.demo.models.*;
import com.example.demo.repository.*;
import com.example.demo.requestDTO.*;
import com.example.demo.specifications.CaseSearchFilters;
import com.example.demo.specifications.CaseSearchSpecifications;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class CustomUserServiceImplementation implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CasesReceivedRepository casesReceivedRepository;


    @Autowired
    private CasesCompletedRepository casesCompletedRepository;

    @Autowired
    private BillsGivenRepository billsGivenRepository;


    @Autowired
    private PaymentReceivedRepository paymentReceivedRepository;


    @Autowired
    private CaseRepository caseRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userRepository.findByUsername(username);
        if (user != null) {
            return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
        }
        throw new UsernameNotFoundException("Invalid User Details entered");
    }

    public UserInfo createUser(CreateUserDTO createUserDTO) {
        UserInfo savedUser;
        try {
            UserInfo user = new UserInfo();
            user.setUsername(createUserDTO.getUsername());
//			Encrypting password for security
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = passwordEncoder.encode(createUserDTO.getPassword());
            user.setPassword(password);
            user.setAccountCreationDate(LocalDateTime.now().toString().split("\\.")[0]);
            String userName = createUserDTO.getUsername();
            if (userRepository.findByUsername(userName) != null) {
                throw new PersistenceException(String.format("A User is already associated with the email - %s. Try logging in if that's yours. Else use a different email id", userName));
            }
            savedUser = userRepository.save(user);
            return savedUser;
        } catch (
                DataIntegrityViolationException e) {
            String userName = createUserDTO.getUsername();
            throw new DataIntegrityViolationException("Unable to create User with given details");

        }

    }

//    public String generateCaseId(UserInfo userInfo) {
//        UUID uuid = UUID.randomUUID();
//        do {
//            uuid = UUID.randomUUID();
//        }
//        while (caseRepository.findByCaseIdentityNumberAndUser(uuid.toString(), userInfo) == null);
//        return uuid.toString();
//    }


    public String addCasesReceived(String userName, CasesReceivedDTO casesReceivedDTO) {
        CasesReceived casesReceived = new CasesReceived();
        UserInfo userInfo = userRepository.findByUsername(userName);
        String caseId = null;
        if (caseRepository.findByCaseIdentityNumberAndUserAndCourtType(casesReceivedDTO.getCaseIdentityNumber(), userInfo, casesReceivedDTO.getCourtType()) == null) {
            CaseInfo caseInfo = new CaseInfo();
            caseInfo.setName(casesReceivedDTO.getName());
            caseInfo.setCourtType(casesReceivedDTO.getCourtType());
            caseInfo.setCaseIdentityNumber(casesReceivedDTO.getCaseIdentityNumber());
            caseInfo.setUser(userInfo);
            caseRepository.save(caseInfo);
            casesReceived.setName(caseInfo.getName());
            casesReceived.setFeesAgreed(casesReceivedDTO.getFeesAgreed());
            casesReceived.setDateOfEntry(LocalDateTime.now().toString().split("\\.")[0]);
            casesReceived.setCaseInfo(caseInfo);
            casesReceived.setUser(userInfo);
            casesReceivedRepository.save(casesReceived);
            caseId = caseInfo.getCaseIdentityNumber();
        } else {
            CaseInfo caseInfo = caseRepository.findByCaseIdentityNumberAndUserAndCourtType(casesReceivedDTO.getCaseIdentityNumber(), userInfo, casesReceivedDTO.getCourtType());
            if (casesReceivedRepository.findByCaseInfo(caseInfo) != null) {

            } else {
                casesReceived.setCaseInfo(caseInfo);
                casesReceived.setDateOfEntry(LocalDateTime.now().toString().split("\\.")[0]);
                casesReceived.setFeesAgreed(casesReceivedDTO.getFeesAgreed());
                casesReceived.setName(caseInfo.getName());
                casesReceived.setUser(userInfo);
                casesReceivedRepository.save(casesReceived);
                caseId = caseInfo.getCaseIdentityNumber();
            }
        }
        return caseId;
    }

    public String addCasesCompleted(String userName, CasesCompletedDTO casesCompletedDTO) {
        CasesCompleted casesCompleted = new CasesCompleted();
        UserInfo userInfo = userRepository.findByUsername(userName);
        String caseId = null;
        if (caseRepository.findByCaseIdentityNumberAndUserAndCourtType(casesCompletedDTO.getCaseIdentityNumber(), userInfo, casesCompletedDTO.getCourtType()) == null) {
            CaseInfo caseInfo = new CaseInfo();
            caseInfo.setName(casesCompletedDTO.getName());
            caseInfo.setCourtType(casesCompletedDTO.getCourtType());
            caseInfo.setCaseIdentityNumber(casesCompletedDTO.getCaseIdentityNumber());
            caseInfo.setUser(userInfo);
            caseRepository.save(caseInfo);
            casesCompleted.setName(caseInfo.getName());
            casesCompleted.setDateOfEntry(LocalDateTime.now().toString().split("\\.")[0]);
            casesCompleted.setCaseInfo(caseInfo);
            casesCompleted.setUser(userInfo);
            casesCompletedRepository.save(casesCompleted);
            caseId = caseInfo.getCaseIdentityNumber();
        } else {
            CaseInfo caseInfo = caseRepository.findByCaseIdentityNumberAndUserAndCourtType(casesCompletedDTO.getCaseIdentityNumber(), userInfo, casesCompletedDTO.getCourtType());
            if (casesCompletedRepository.findByCaseInfo(caseInfo) != null) {

            } else {
                casesCompleted.setCaseInfo(caseInfo);
                casesCompleted.setDateOfEntry(LocalDateTime.now().toString().split("\\.")[0]);
                casesCompleted.setName(caseInfo.getName());
                casesCompleted.setUser(userInfo);
                casesCompletedRepository.save(casesCompleted);
                caseId = caseInfo.getCaseIdentityNumber();
            }
        }
        return caseId;

    }


    public String addBillsGiven(String userName, BillsGivenDTO billsGivenDTO) {
        BillsGiven billsGiven = new BillsGiven();
        UserInfo userInfo = userRepository.findByUsername(userName);
        String caseId = null;
        if (caseRepository.findByCaseIdentityNumberAndUserAndCourtType(billsGivenDTO.getCaseIdentityNumber(), userInfo, billsGivenDTO.getCourtType()) == null) {
            CaseInfo caseInfo = new CaseInfo();
            caseInfo.setName(billsGivenDTO.getName());
            caseInfo.setCourtType(billsGivenDTO.getCourtType());
            caseInfo.setCaseIdentityNumber(billsGivenDTO.getCaseIdentityNumber());
            caseInfo.setUser(userInfo);
            caseRepository.save(caseInfo);
            billsGiven.setName(caseInfo.getName());
            billsGiven.setDateOfEntry(LocalDateTime.now().toString().split("\\.")[0]);
            billsGiven.setCaseInfo(caseInfo);
            billsGiven.setFeesBilled(billsGivenDTO.getFeesBilled());
            billsGiven.setUser(userInfo);
            billsGivenRepository.save(billsGiven);
            caseId = caseInfo.getCaseIdentityNumber();
        } else {
            CaseInfo caseInfo = caseRepository.findByCaseIdentityNumberAndUserAndCourtType(billsGivenDTO.getCaseIdentityNumber(), userInfo, billsGivenDTO.getCourtType());
            if (billsGivenRepository.findByCaseInfo(caseInfo) != null) {

            } else {
                billsGiven.setCaseInfo(caseInfo);
                billsGiven.setDateOfEntry(LocalDateTime.now().toString().split("\\.")[0]);
                billsGiven.setName(caseInfo.getName());
                billsGiven.setFeesBilled(billsGivenDTO.getFeesBilled());
                billsGiven.setUser(userInfo);
                billsGivenRepository.save(billsGiven);
                caseId = caseInfo.getCaseIdentityNumber();
            }
        }
        return caseId;

    }

    public ResponseEntity<?> deleteBilledCase(String userName, String id) {
        UserInfo userInfo = userRepository.findByUsername(userName);
        BillsGiven billsGiven = null;
        Optional<BillsGiven> optionalBillsGiven = billsGivenRepository.findById(Long.valueOf(id));
        if (optionalBillsGiven.isPresent()) {
            billsGiven = optionalBillsGiven.get();
            if (casesReceivedRepository.findByCaseInfo(billsGiven.getCaseInfo()) == null && paymentReceivedRepository.findByCaseInfo(billsGiven.getCaseInfo()) == null && casesCompletedRepository.findByCaseInfo(billsGiven.getCaseInfo()) == null) {
                caseRepository.delete(billsGiven.getCaseInfo());
            }
            billsGivenRepository.delete(billsGiven);
            return new ResponseEntity<>("Delete Successful", HttpStatus.OK);
        }
        return new ResponseEntity<>("Delete failed", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> deletePaidCase(String userName, String id) {
        UserInfo userInfo = userRepository.findByUsername(userName);
        PaymentReceived paymentReceived = null;
        Optional<PaymentReceived> optionalPaymentReceived = paymentReceivedRepository.findById(Long.valueOf(id));
        if (optionalPaymentReceived.isPresent()) {
            paymentReceived = optionalPaymentReceived.get();
            if (casesReceivedRepository.findByCaseInfo(paymentReceived.getCaseInfo()) == null && billsGivenRepository.findByCaseInfo(paymentReceived.getCaseInfo()) == null && casesCompletedRepository.findByCaseInfo(paymentReceived.getCaseInfo()) == null) {
                caseRepository.delete(paymentReceived.getCaseInfo());
            }
            paymentReceivedRepository.delete(paymentReceived);
            return new ResponseEntity<>("Delete Successful", HttpStatus.OK);
        }
        return new ResponseEntity<>("Delete failed", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> deleteCompletedCase(String userName, String id) {
        UserInfo userInfo = userRepository.findByUsername(userName);
        CasesCompleted casesCompleted = null;
        Optional<CasesCompleted> optionalCasesCompleted = casesCompletedRepository.findById(Long.valueOf(id));
        if (optionalCasesCompleted.isPresent()) {
            casesCompleted = optionalCasesCompleted.get();
            if (casesReceivedRepository.findByCaseInfo(casesCompleted.getCaseInfo()) == null && paymentReceivedRepository.findByCaseInfo(casesCompleted.getCaseInfo()) == null && billsGivenRepository.findByCaseInfo(casesCompleted.getCaseInfo()) == null) {
                caseRepository.delete(casesCompleted.getCaseInfo());
            }
            casesCompletedRepository.delete(casesCompleted);
            return new ResponseEntity<>("Delete Successful", HttpStatus.OK);
        }
        return new ResponseEntity<>("Delete failed", HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<?> deleteReceivedCase(String userName, String id) {
        UserInfo userInfo = userRepository.findByUsername(userName);
        CasesReceived casesReceived = null;
        Optional<CasesReceived> optionalCasesReceived = casesReceivedRepository.findById(Long.valueOf(id));
        if (optionalCasesReceived.isPresent()) {
            casesReceived = optionalCasesReceived.get();
            if (billsGivenRepository.findByCaseInfo(casesReceived.getCaseInfo()) == null && paymentReceivedRepository.findByCaseInfo(casesReceived.getCaseInfo()) == null && casesCompletedRepository.findByCaseInfo(casesReceived.getCaseInfo()) == null) {
                caseRepository.delete(casesReceived.getCaseInfo());
            }
            casesReceivedRepository.delete(casesReceived);
            return new ResponseEntity<>("Delete Successful", HttpStatus.OK);
        }
        return new ResponseEntity<>("Delete failed", HttpStatus.NOT_FOUND);
    }

public ResponseEntity<?> deleteFromAll(String userName, String id){
    UserInfo userInfo = userRepository.findByUsername(userName);
    Optional<CaseInfo> optionalCaseInfo = caseRepository.findById(Long.valueOf(id));
    if (optionalCaseInfo.isPresent()){
        CaseInfo caseInfo = optionalCaseInfo.get();
        caseRepository.delete(caseInfo);
        return new ResponseEntity<>("Delete Successful", HttpStatus.OK);
    }
    else{
        return new ResponseEntity<>("Delete failed", HttpStatus.NOT_FOUND);
    }
}

    public String addPaymentReceived(String userName, PaymentReceivedDTO paymentReceivedDTO) {
        PaymentReceived paymentReceived = new PaymentReceived();
        UserInfo userInfo = userRepository.findByUsername(userName);
        String caseId = null;
        if (caseRepository.findByCaseIdentityNumberAndUserAndCourtType(paymentReceivedDTO.getCaseIdentityNumber(), userInfo, paymentReceivedDTO.getCourtType()) == null) {
            CaseInfo caseInfo = new CaseInfo();
            caseInfo.setName(paymentReceivedDTO.getName());
            caseInfo.setCourtType(paymentReceivedDTO.getCourtType());
            caseInfo.setCaseIdentityNumber(paymentReceivedDTO.getCaseIdentityNumber());
            caseInfo.setUser(userInfo);
            caseRepository.save(caseInfo);
            paymentReceived.setName(caseInfo.getName());
            paymentReceived.setDateOfEntry(LocalDateTime.now().toString().split("\\.")[0]);
            paymentReceived.setCaseInfo(caseInfo);
            paymentReceived.setPaymentReceived(paymentReceivedDTO.getPaymentReceived());
            paymentReceived.setUser(userInfo);
            paymentReceivedRepository.save(paymentReceived);
            caseId = caseInfo.getCaseIdentityNumber();
        } else {
            CaseInfo caseInfo = caseRepository.findByCaseIdentityNumberAndUserAndCourtType(paymentReceivedDTO.getCaseIdentityNumber(), userInfo, paymentReceivedDTO.getCourtType());
            if (paymentReceivedRepository.findByCaseInfo(caseInfo) != null) {
            } else {
                paymentReceived.setCaseInfo(caseInfo);
                paymentReceived.setDateOfEntry(LocalDateTime.now().toString().split("\\.")[0]);
                paymentReceived.setName(caseInfo.getName());
                paymentReceived.setPaymentReceived(paymentReceivedDTO.getPaymentReceived());
                paymentReceived.setUser(userInfo);
                paymentReceivedRepository.save(paymentReceived);
                caseId = caseInfo.getCaseIdentityNumber();
            }
        }
        return caseId;

    }



    public List<CaseInfo> getAllCases(String userName, CaseSearchFilters searchFilters) {
        UserInfo userInfo = userRepository.findByUsername(userName);
        Specification<CaseInfo> spec = Specification.where(CaseSearchSpecifications.byUser(userInfo));
        if (searchFilters.getName() != null && searchFilters.getName().isEmpty()) {
            searchFilters.setName(null);
        }
        if (searchFilters.getCaseId() != null && searchFilters.getCaseId().isEmpty()) {
            searchFilters.setCaseId(null);
        }
        if (searchFilters.getCourtType() != null && searchFilters.getCourtType().isEmpty()) {
            searchFilters.setCourtType(null);
        }

        if (searchFilters.getName() != null) {
            spec = spec.and(CaseSearchSpecifications.byName(searchFilters.getName()));
        }
        if (searchFilters.getCourtType() != null) {
            spec = spec.and(CaseSearchSpecifications.byCourtType(searchFilters.getCourtType()));
        }
        if (searchFilters.getCaseId() != null) {
            spec = spec.and(CaseSearchSpecifications.byCaseIdentityNumber(searchFilters.getCaseId()));
        }
        return caseRepository.findAll(spec);
    }

    public List<CasesReceived> getAllCasesReceived(String userName) {
        UserInfo userInfo = userRepository.findByUsername(userName);
        return casesReceivedRepository.findByUser(userInfo);
    }

    public List<CasesCompleted> getAllCasesCompleted(String userName) {
        UserInfo userInfo = userRepository.findByUsername(userName);
        return casesCompletedRepository.findByUser(userInfo);
    }

    public List<BillsGiven> getAllCasesBilled(String userName) {
        UserInfo userInfo = userRepository.findByUsername(userName);
        return billsGivenRepository.findByUser(userInfo);
    }

    public List<PaymentReceived> getAllCasesPaid(String userName) {
        UserInfo userInfo = userRepository.findByUsername(userName);
        return paymentReceivedRepository.findByUser(userInfo);
    }

}
