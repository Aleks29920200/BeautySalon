package com.example.beautySalon.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name="roles")
public class Role extends BaseEntity{
    @Column(nullable = false)
    private String authority;
    public Role() {
    }

    public String getAuthority() {
        return authority;
    }
    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
