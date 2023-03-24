package com.example.beautySalon.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name="services")
public class Service extends BaseEntity{
    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false)
    private float price;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ServiceCategory category;
    @Column(columnDefinition = "TEXT")
    private String info;
    @OneToOne
    private FileEntity img;

    public Service() {
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    public ServiceCategory getCategory() {
        return category;
    }

    public void setCategory(ServiceCategory category) {
        this.category = category;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public FileEntity getImg() {
        return this.img;
    }

    public void setImg(FileEntity img) {
        this.img = img;
    }
}
