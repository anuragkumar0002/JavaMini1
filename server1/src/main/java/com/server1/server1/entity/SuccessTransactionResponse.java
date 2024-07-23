package com.server1.server1.entity;

import java.util.List;
import java.util.ListIterator;

public class SuccessTransactionResponse {

    public String accountNumber;
    public List<TransactionEntity> success;

    public SuccessTransactionResponse(String accountNumber, List<TransactionEntity> success){
        this.accountNumber = accountNumber;
        this.success = success;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<TransactionEntity> getSuccess() {
        return success;
    }

    public void setSuccess(List<TransactionEntity> success) {
        this.success = success;
    }
}
