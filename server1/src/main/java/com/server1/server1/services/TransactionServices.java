package com.server1.server1.services;

import com.server1.server1.entity.SuccessTransactionResponse;
import com.server1.server1.entity.TransactionEntity;
//import com.server1.server1.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class TransactionServices {



    public SuccessTransactionResponse getSuccessTransaction(String accountNumber){
        List<TransactionEntity> transactions;
        if (accountNumber.equals("1234")){
            transactions =  Arrays.asList(
                    new TransactionEntity("1","12351","success", "344542","23-04-2024"),
                    new TransactionEntity("2","34533", "success","32423", "12-02-2022"),
                    new TransactionEntity("3","0000", "success","212", "13-02-2022")
            );
        }
        else {
            transactions = Collections.emptyList();
        }
        return new SuccessTransactionResponse(accountNumber,transactions);
    }

}
