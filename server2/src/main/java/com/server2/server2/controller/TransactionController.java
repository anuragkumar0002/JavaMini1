package com.server2.server2.controller;

import com.server2.server2.entity.FailureTransactionResponse;
import com.server2.server2.services.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backendserver2")
public class TransactionController {

    @Autowired
    public TransactionServices transactionServices;


    @GetMapping("/failure/{accountNumber}")
    public FailureTransactionResponse getSuccessTransactions(@PathVariable String accountNumber){
        return transactionServices.getSuccessTransaction(accountNumber);



    }

}
