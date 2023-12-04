package com.project.storebackend.request;

import java.util.Objects;

public class ProductCategoryRequest {
    private String name;

    public ProductCategoryRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategoryRequest that = (ProductCategoryRequest) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "ProductCategoryRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
