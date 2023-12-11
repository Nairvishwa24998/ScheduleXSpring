package com.example.demo.usercontroller;


import com.example.demo.models.*;
import com.example.demo.requestDTO.*;
import com.example.demo.service.implementation.CustomUserServiceImplementation;
import com.example.demo.specifications.CaseSearchFilters;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.http.HttpSession;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/schedulex")
public class ScheduleXController {

    private final CustomUserServiceImplementation customUserServiceImplementation;

    public ScheduleXController(CustomUserServiceImplementation customUserServiceImplementation) {
        this.customUserServiceImplementation = customUserServiceImplementation;
    }


    @GetMapping("/home")
    public String getHome() {
        return "Home";
    }

    @GetMapping("/casesreceived")
    public String getCasesReceived(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        List<CasesReceived> caseList = customUserServiceImplementation.getAllCasesReceived(userName);
        model.addAttribute("cases", caseList);
        return "CasesReceived";
    }

    @GetMapping("/allcases")
    public String getAllCases(Model model, CaseSearchFilters caseSearchFilters) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        List<CaseInfo> caseInfoList = customUserServiceImplementation.getAllCases(userName, caseSearchFilters);
        model.addAttribute("cases", caseInfoList);
        return "AllCases";
    }
    @GetMapping("/casescompleted")
    public String getCasesCompleted(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        List<CasesCompleted> caseList = customUserServiceImplementation.getAllCasesCompleted(userName);
        model.addAttribute("cases", caseList);
        return "CasesCompleted";
    }
    @GetMapping("/billssubmitted")
    public String getBillsSubmitted(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        List<BillsGiven> caseList = customUserServiceImplementation.getAllCasesBilled(userName);
        model.addAttribute("cases", caseList);
        return "BillsSubmitted";
    }

    @GetMapping("/paymentreceived")
    public String getPaymentReceived(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        List<PaymentReceived> caseList = customUserServiceImplementation.getAllCasesPaid(userName);
        model.addAttribute("cases", caseList);
        return "PaymentReceived";
    }

    @PostMapping("/createuser")
    public ResponseEntity<String> createUser(@RequestBody CreateUserDTO createUserDTO, HttpSession session) {
        try {
            customUserServiceImplementation.createUser(createUserDTO);
            return ResponseEntity.ok().body("User created successfully");
        } catch (DataIntegrityViolationException ex) {
            session.setAttribute("errorMessage", ex.getMessage());
            return ResponseEntity.internalServerError().body("User creation failed");
        } catch (PersistenceException ex) {
            session.setAttribute("errorMessage", ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/casesreceived")
    public ResponseEntity<String> addToCasesReceived(@RequestBody CasesReceivedDTO casesReceivedDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        String caseId = customUserServiceImplementation.addCasesReceived(userName, casesReceivedDTO);
        if (caseId == null) {
            return ResponseEntity.badRequest().body("Invalid");
        }
        return ResponseEntity.ok().body(caseId);
    }


    @PostMapping("/casescompleted")
    public ResponseEntity<String> addToCasesCompleted(@RequestBody CasesCompletedDTO casesCompletedDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        String caseId = customUserServiceImplementation.addCasesCompleted(userName, casesCompletedDTO);
        if (caseId == null) {
            return ResponseEntity.badRequest().body("Invalid");
        }
        return ResponseEntity.ok().body(caseId);
    }

    @PostMapping("/billssubmitted")
    public ResponseEntity<String> addToBillsGiven(@RequestBody BillsGivenDTO billsGivenDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        String caseId = customUserServiceImplementation.addBillsGiven(userName, billsGivenDTO);
        if (caseId == null) {
            return ResponseEntity.badRequest().body("Invalid");
        }
        return ResponseEntity.ok().body(caseId);
    }

    @DeleteMapping("/billssubmitted/{id}")
    public ResponseEntity<?> deleteBilledCase(@PathVariable String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        return customUserServiceImplementation.deleteBilledCase(userName, id);

    }
    @DeleteMapping("/paymentreceived/{id}")
    public ResponseEntity<?> deletePaidCase(@PathVariable String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        return customUserServiceImplementation.deletePaidCase(userName, id);

    }
    @DeleteMapping("/casescompleted/{id}")
    public ResponseEntity<?> deleteCompletedCase(@PathVariable String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        return customUserServiceImplementation.deleteCompletedCase(userName, id);

    }

    @DeleteMapping("/casesreceived/{id}")
    public ResponseEntity<?> deleteReceivedCase(@PathVariable String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        return customUserServiceImplementation.deleteReceivedCase(userName, id);

    }

    @DeleteMapping("/removefromall/{id}")
    public ResponseEntity<?> deleteFromAll(@PathVariable String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        return customUserServiceImplementation.deleteFromAll(userName, id);
    }


    @PostMapping("/paymentreceived")
    public ResponseEntity<String> addToPaymentReceived(@RequestBody PaymentReceivedDTO paymentReceivedDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        String caseId = customUserServiceImplementation.addPaymentReceived(userName, paymentReceivedDTO);
        if (caseId == null) {
            return ResponseEntity.badRequest().body("Invalid");
        }
        return ResponseEntity.ok().body(caseId);
    }


    @GetMapping("/login")
    public String login(Model model, HttpSession session) {
        if (session.getAttribute("errorMessage") != null) {
            String errorMessage = (String) session.getAttribute("errorMessage");
            model.addAttribute("displayErrorMessage", errorMessage);
            session.removeAttribute("errorMessage");
        }
        return "Signinup";
    }


}
