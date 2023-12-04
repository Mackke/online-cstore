package com.project.storebackend.translator;

import com.project.storebackend.dto.ProductCategoryDto;

import java.util.UUID;

public class RequestToDtoTranslator {

    private RequestToDtoTranslator() { // This is so that we don't have to instantiate when using it.
    }

    public static ProductCategoryDto toProductCategoryDto(ProductCategoryDto request) {
        ProductCategoryDto dto = new ProductCategoryDto(UUID.randomUUID(), request.getName()); //todo fix the id issue when we add a service
        return dto;
    }
}
