package com.example.beautySalon.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="bills")
public class Bill extends BaseEntity{
    @Column(nullable = false)
private BigDecimal amount;
    @Column(nullable = false)
private BigDecimal price;
    public Bill() {
    }
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
