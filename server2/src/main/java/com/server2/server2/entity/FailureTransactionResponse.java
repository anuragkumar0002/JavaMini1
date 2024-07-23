package com.server2.server2.entity;

import java.util.List;

public class FailureTransactionResponse {

    public String accountNumber;
    public List<TransactionEntity> failure;

    public FailureTransactionResponse(String accountNumber, List<TransactionEntity> failure){
        this.accountNumber = accountNumber;
        this.failure = failure;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<TransactionEntity> getSuccess() {
        return failure;
    }

    public void setSuccess(List<TransactionEntity> success) {
        this.failure = failure;
    }
}
