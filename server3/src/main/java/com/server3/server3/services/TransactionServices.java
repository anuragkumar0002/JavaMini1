package com.server3.server3.services;

import com.server3.server3.entity.PendingTransactionResponse;
import com.server3.server3.entity.TransactionEntity;
//import com.server1.server1.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class TransactionServices {



    public PendingTransactionResponse getSuccessTransaction(String accountNumber){
        List<TransactionEntity> transactions;
        if (accountNumber.equals("123456")){
            transactions =  Arrays.asList(
                    new TransactionEntity(1,"9999","pending", "1000","20-03-2024"),
                    new TransactionEntity(2,"8888", "pending","2000", "11-01-2020"),
                    new TransactionEntity(3,"7777", "pending","5000", "14-09-2019")
            );
        }
        else {
            transactions = Collections.emptyList();
        }
        return new PendingTransactionResponse(accountNumber,transactions);
    }

}
