package com.server3.server3.controller;

import com.server3.server3.entity.PendingTransactionResponse;
import com.server3.server3.services.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backendserver3")
public class TransactionController {

    @Autowired
    public TransactionServices transactionServices;


    @GetMapping("/pending/{accountNumber}")
    public PendingTransactionResponse getSuccessTransactions(@PathVariable String accountNumber){
        return transactionServices.getSuccessTransaction(accountNumber);



    }

}
