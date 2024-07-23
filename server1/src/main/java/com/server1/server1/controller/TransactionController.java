package com.server1.server1.controller;

import com.server1.server1.entity.SuccessTransactionResponse;
import com.server1.server1.entity.TransactionEntity;
import com.server1.server1.services.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/backendserver1")

public class TransactionController {

    @Autowired
    public TransactionServices transactionServices;

    @PostMapping("/success/{accountNumber}")

    public SuccessTransactionResponse getSuccessTransactions(@PathVariable String accountNumber) {
        return transactionServices.getSuccessTransaction(accountNumber);

    }

}