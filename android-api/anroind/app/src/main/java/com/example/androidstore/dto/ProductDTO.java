package com.example.androidstore.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private String name;
    private double price;
    private String image;

    public ProductDTO() {
    }

    public ProductDTO(String name, double price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }
}
