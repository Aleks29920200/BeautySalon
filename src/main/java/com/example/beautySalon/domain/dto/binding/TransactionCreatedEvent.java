package com.example.beautySalon.domain.dto.binding;

import org.springframework.context.ApplicationEvent;

import java.util.ArrayList;
import java.util.List;

public class TransactionCreatedEvent extends ApplicationEvent {
    private List<Long> allTransactionIDs = new ArrayList<>();

    public TransactionCreatedEvent(Object source) {
        super(source);
    }
    public TransactionCreatedEvent setAlTransactionIDs(List<Long> allTransactionIDs) {
        this.allTransactionIDs = allTransactionIDs;
        return this;
    }
    public List<Long> getAllProductIDs() {
        return allTransactionIDs;
    }
    public TransactionCreatedEvent addTransactionId(Long productId) {
        this.allTransactionIDs.add(productId);
        return this;
    }
}
