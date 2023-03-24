package com.example.dealership1.domain.dto.view;

import com.example.dealership1.domain.dto.service.BillDto;
import com.example.dealership1.domain.dto.service.ServiceDto;
import com.example.dealership1.domain.entity.BaseEntity;

import java.time.LocalDateTime;

public class TransactionViewDto extends BaseEntity{
    private LocalDateTime startDate;
    private LocalDateTime expirationDate;
    private BillViewDto bill;
    private ServiceViewDto service;

    public TransactionViewDto() {
    }



    public ServiceViewDto getService() {
        return service;
    }

    public void setService(ServiceViewDto service) {
        this.service = service;
    }

    public BillViewDto getBill() {
        return bill;
    }

    public void setBill(BillViewDto bill) {
        this.bill = bill;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
}
