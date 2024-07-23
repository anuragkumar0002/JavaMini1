package com.MainApplication.Main.Application.Services;

import com.MainApplication.Main.Application.Entity.Transaction;
import com.MainApplication.Main.Application.Entity.TransactionResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TransactionService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public CompletableFuture<TransactionResponse> fetchTransactions(String accountNumber) {
        WebClient webClient = webClientBuilder.build();

        CompletableFuture<String> successResponseFuture = webClient.get()
                .uri("http://localhost:8081/backendserver1/success/{accountNumber}", accountNumber)
                .retrieve()
                .bodyToMono(String.class)
                .toFuture();

        CompletableFuture<String> failureResponseFuture = webClient.get()
                .uri("http://localhost:8082/backendserver2/failure/{accountNumber}", accountNumber)
                .retrieve()
                .bodyToMono(String.class)
                .toFuture();

        CompletableFuture<String> pendingResponseFuture = webClient.get()
                .uri("http://localhost:8083/backendserver3/pending/{accountNumber}", accountNumber)
                .retrieve()
                .bodyToMono(String.class)
                .toFuture();

        return CompletableFuture.allOf(successResponseFuture, failureResponseFuture, pendingResponseFuture)
                .thenApply(voided -> {
                    try {
                        String successResponse = successResponseFuture.join();
                        String failureResponse = failureResponseFuture.join();
                        String pendingResponse = pendingResponseFuture.join();
//                        System.out.println(successResponse);

                        ObjectMapper objectMapper = new ObjectMapper();
//                        System.out.println(objectMapper.readTree(successResponse));
                        List<Transaction> successTransactions = parseTransactions(objectMapper.readTree(successResponse), "success");
//                        System.out.println(successTransactions);

                        List<Transaction> failureTransactions = parseTransactions(objectMapper.readTree(failureResponse), "fail");
                        List<Transaction> pendingTransactions = parseTransactions(objectMapper.readTree(pendingResponse), "pending");

                        return new TransactionResponse(successTransactions, failureTransactions, pendingTransactions);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return new TransactionResponse(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
                    }
                });
    }

    private List<Transaction> parseTransactions(JsonNode responseNode, String status) {
        List<Transaction> transactions = new ArrayList<>();
        if (responseNode != null && responseNode.has("success")) {
            JsonNode successNode = responseNode.get("success");

            if (successNode.isArray()) {
                for (JsonNode transactionNode : successNode) {
                    if (transactionNode.has("id") && transactionNode.has("transactionId") &&
                            transactionNode.has("amount") && transactionNode.has("date")) {
                        Transaction transaction = new Transaction();
                        transaction.setId(transactionNode.get("id").asText());
                        transaction.setTransactionId(transactionNode.get("transactionId").asText());
                        transaction.setStatus(status);
                        transaction.setAmount(transactionNode.get("amount").asText());
                        transaction.setDate(transactionNode.get("date").asText());
                        transactions.add(transaction);
                    } else {
                        // Handle case where required fields are missing
                    }
                }
            }
        }
        return transactions;
    }

    public CompletableFuture<List<Transaction>> filterTransactionsByStatus(String accountNumber, String status) {
        System.out.println(fetchTransactions((accountNumber)));
        return fetchTransactions(accountNumber).thenApply(response -> {
            System.out.println(response.getSuccess());
            switch (status.toLowerCase()) {
                case "success":
                    return response.getSuccess();
                case "failure":
                    return response.getFailure();
                case "pending":
                    return response.getPending();
                case "all":
                default:
                    return response.getAllTransactions();
            }
        });
    }

}
