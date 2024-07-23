package com.server2.server2.services;

import com.server2.server2.entity.FailureTransactionResponse;
import com.server2.server2.entity.TransactionEntity;
//import com.server1.server1.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class TransactionServices {

    public FailureTransactionResponse getSuccessTransaction(String accountNumber){
        List<TransactionEntity> transactions;
        if (accountNumber.equals("1234")){
            transactions =  Arrays.asList(
                    new TransactionEntity(1,"2222","fail", "100","25-05-2024"),
                    new TransactionEntity(2,"3333", "fail","200", "12-09-2020"),
                    new TransactionEntity(3,"4444", "fail","500", "13-02-2019")
            );
        }
        else {
            transactions = Collections.emptyList();
        }
        return new FailureTransactionResponse(accountNumber,transactions);
    }

}
