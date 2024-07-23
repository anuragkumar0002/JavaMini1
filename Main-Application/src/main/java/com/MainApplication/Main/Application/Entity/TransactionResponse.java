package com.MainApplication.Main.Application.Entity;

import java.util.List;

public class TransactionResponse {
    private List<Transaction> success;
    private List<Transaction> failure;
    private List<Transaction> pending;
    private List<Transaction> allTransactions;

    public TransactionResponse() {

    }

    public List<Transaction> getAllTransactions() {
        return allTransactions;
    }

    public void setAllTransactions(List<Transaction> allTransactions) {
        this.allTransactions = allTransactions;
    }

    public List<Transaction> getSuccess() {
        return success;
    }

    public void setSuccess(List<Transaction> success) {
        this.success = success;
    }

    public List<Transaction> getFailure() {
        return failure;
    }

    public void setFailure(List<Transaction> failure) {
        this.failure = failure;
    }

    public List<Transaction> getPending() {
        return pending;
    }

    public void setPending(List<Transaction> pending) {
        this.pending = pending;
    }

    public TransactionResponse(List<Transaction> success, List<Transaction> failure, List<Transaction> pending) {
        this.success = success;
        this.failure = failure;
        this.pending = pending;
    }
}
