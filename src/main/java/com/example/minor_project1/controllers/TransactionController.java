package com.example.minor_project1.controllers;

import com.example.minor_project1.dtos.CreatePaymentRequest;
import com.example.minor_project1.dtos.CreateTransactionRequest;
import com.example.minor_project1.models.SecuredUser;
import com.example.minor_project1.models.Transactions;
import com.example.minor_project1.services.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    TransactionServices transactionServices;

    // Accessible to admin only
    @PostMapping("/transaction")
    public String createNewTransaction(@RequestBody @Valid CreateTransactionRequest transactionRequest) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecuredUser loginDetails = (SecuredUser) authentication.getPrincipal();
        Integer adminId = loginDetails.getAdmin().getId();

        return transactionServices.initCreate(transactionRequest,adminId);
    }


    // Accessible to student only
    @PostMapping("/transaction/payment")
    public void paymentStatus(@RequestBody @Valid CreatePaymentRequest paymentRequest) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecuredUser loginDetails = (SecuredUser) authentication.getPrincipal();
        Integer studentId = loginDetails.getStudent().getId();
        transactionServices.payFine(paymentRequest,studentId);
    }

    // Accessible to both only
    @GetMapping("/transaction")
    public List<Transactions> getTransactions(@RequestParam("studentId") Integer studentId,
                                              @RequestParam("daysData") Integer daysData){
        return transactionServices.getLastNDays(studentId,daysData);
    }




}
