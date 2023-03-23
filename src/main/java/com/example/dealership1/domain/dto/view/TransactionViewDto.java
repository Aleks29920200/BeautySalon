package com.example.dealership1.domain.dto.view;

import com.example.dealership1.domain.dto.service.BillDto;
import com.example.dealership1.domain.dto.service.ServiceDto;
import com.example.dealership1.domain.entity.BaseEntity;

public class TransactionViewDto extends BaseEntity{
    private String startDate;
    private String expirationDate;
    private BillViewDto bill;
    private ServiceViewDto service;

    public TransactionViewDto() {
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
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
}
