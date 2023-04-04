package com.example.beautySalon.domain.dto.binding;

import com.example.beautySalon.domain.dto.service.FileDto;
import com.example.beautySalon.vallidation.annotation.UniqueServiceName;
import jakarta.validation.constraints.*;

public class AddServiceDto {
    @Size(min=3,max=40,message = "Name must be between 3 and 40 symbols")
    @NotBlank(message = "Name cannot be empty!")
    @UniqueServiceName
    private String name;
    @Positive(message = "Price must be positive number")
    @NotNull(message = "Price cannot be null!")
    private Float price;
    private String category;
    @NotBlank(message = "Info must not be null!")
    private String info;
    private FileDto img;
    public AddServiceDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public FileDto getImg() {
        return img;
    }

    public void setImg(FileDto img) {
        this.img = img;
    }
}
