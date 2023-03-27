package com.example.beautySalon.services;

import com.example.beautySalon.domain.dto.binding.TransactionCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
  private static final Logger LOGGER = LoggerFactory.getLogger(InventoryService.class);

  @EventListener(TransactionCreatedEvent.class)
  public void onTransactionCreated(TransactionCreatedEvent evt) {
    LOGGER.info("Inventory subtracted for added for transaction {} ", evt.getAllProductIDs());
  }
}