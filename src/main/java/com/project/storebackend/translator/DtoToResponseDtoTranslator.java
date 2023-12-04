package com.project.storebackend.translator;

import com.project.storebackend.dto.ProductDto;
import com.project.storebackend.response.ProductResponseDto;

public class DtoToResponseDtoTranslator {

    private DtoToResponseDtoTranslator() { // This is so that we don't have to instantiate when using it.
    }

    public static ProductResponseDto toProductResponseDto(ProductDto productDto) {
        return new ProductResponseDto(
                productDto.getId(),
                productDto.getName(),
                productDto.getCategory(),
                productDto.getDescription(),
                productDto.getPrice(),
                productDto.getStock());
    }
}
