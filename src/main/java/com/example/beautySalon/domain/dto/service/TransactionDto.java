package com.example.beautySalon.domain.dto.service;

import java.util.ArrayList;
import java.util.List;

public class TransactionDto {
    private List<Long> allTransactionIDs = new ArrayList<>();
    public TransactionDto setAllProductIDs(List<Long> allProductIDs) {
        this.allTransactionIDs = allProductIDs;
        return this;
    }

    public List<Long> getAllProductIDs() {
        return allTransactionIDs;
    }

    public TransactionDto addProductId(Long productId) {
        this.allTransactionIDs.add(productId);
        return this;
    }
}
