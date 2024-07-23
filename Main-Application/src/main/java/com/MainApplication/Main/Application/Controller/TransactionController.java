package com.MainApplication.Main.Application.Controller;

import com.MainApplication.Main.Application.Entity.Transaction;
import com.MainApplication.Main.Application.Entity.TransactionResponse;
import com.MainApplication.Main.Application.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<TransactionResponse> getTransactions(@PathVariable String accountNumber) {
        try {
            TransactionResponse response = transactionService.fetchTransactions(accountNumber).get();
            System.out.println(response);
            return ResponseEntity.ok(response);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new TransactionResponse());
        }
    }


    @GetMapping("/{accountNumber}/filter")
    public ResponseEntity<List<Transaction>> filterTransactionsByStatus( @PathVariable String accountNumber,  @RequestParam("status") String status) {
        try {
            List<Transaction> filteredTransactions = transactionService.filterTransactionsByStatus(accountNumber, status).get();
            return ResponseEntity.ok(filteredTransactions);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(List.of());
        }
    }
}