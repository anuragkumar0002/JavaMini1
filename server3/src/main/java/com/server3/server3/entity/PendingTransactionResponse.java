package com.server3.server3.entity;

import java.util.List;

public class PendingTransactionResponse {

    public String accountNumber;
    public List<TransactionEntity> pending;

    public PendingTransactionResponse(String accountNumber, List<TransactionEntity> pending){
        this.accountNumber = accountNumber;
        this.pending = pending;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<TransactionEntity> getSuccess() {
        return pending;
    }

    public void setSuccess(List<TransactionEntity> success) {
        this.pending = pending;
    }
}
