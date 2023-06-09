package com.example.beautySalon.domain.dto.binding;


import com.example.beautySalon.domain.dto.service.BillDto;
import com.example.beautySalon.domain.dto.service.ServiceDto;
import com.example.beautySalon.domain.dto.service.UserDto;

public class AddTransactionDto {
    private String startDate;
    private String expirationDate;
    private boolean cancelled;
    private UserDto user;
    private ServiceDto serviceName;
    private BillDto billDto;


    public AddTransactionDto() {
    }



    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }



    public ServiceDto getServiceName() {
        return serviceName;
    }

    public void setServiceName(ServiceDto serviceName) {
        this.serviceName = serviceName;
    }

    public BillDto getBillDto() {
        return billDto;
    }

    public void setBillDto(BillDto billDto) {
        this.billDto = billDto;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
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
}
