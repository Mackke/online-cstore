package com.project.storebackend.dto;

import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
public class ProductDto {
    private UUID id;
    private String name;

    private ProductCategoryDto category;
    private String description;
    private Double price;
    private int stock;

    public ProductDto(UUID id, String name, ProductCategoryDto category, String description, Double price, int stock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductCategoryDto getCategory() {
        return category;
    }

    public void setCategory(ProductCategoryDto category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
