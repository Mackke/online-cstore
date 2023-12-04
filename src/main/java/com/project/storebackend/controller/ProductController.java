package com.project.storebackend.controller;


import com.project.storebackend.dto.ProductCategoryDto;
import com.project.storebackend.dto.ProductDto;
import com.project.storebackend.request.ProductRequest;
import com.project.storebackend.response.ProductResponseDto;
import com.project.storebackend.translator.DtoToResponseDtoTranslator;
import com.project.storebackend.translator.RequestToDtoTranslator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Tag(name = "Product Controller")
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController implements ProductSwaggerController {

    @Operation(summary = "create new product")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Ok"),
                    @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
                    @ApiResponse(responseCode = "404", description = "Product not found")}
    )
    @PostMapping("/")
    public ProductResponseDto createProduct(@RequestBody ProductRequest productRequest) {
        return new ProductResponseDto(UUID.randomUUID(), productRequest.getName(), new ProductCategoryDto(UUID.randomUUID(), productRequest.getCategory().getName()), productRequest.getDescription(), productRequest.getPrice(), productRequest.getStock());
    }

    @Operation(summary = "get all products")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Ok"),
                    @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
                    @ApiResponse(responseCode = "404", description = "Product not found")}
    )
    @GetMapping("/")
    public List<ProductDto> getAllProducts(@Parameter(description = "decides if only edibles are returned or not") @RequestParam(required = false) boolean edibles) {

        if (edibles) {
            return List.of(
                    new ProductDto(UUID.fromString("4f32e852-91e9-11ee-b9d1-0242ac120002"), "Product1", new ProductCategoryDto(UUID.fromString("ac47c918-91f3-11ee-b9d1-0242ac120002"), "Drinks"), "Description1", 10.0, 50));
        } else {
            return List.of(
                    new ProductDto(UUID.fromString("4f32e852-91e9-11ee-b9d1-0242ac120002"), "Product1", new ProductCategoryDto(UUID.fromString("ac47c918-91f3-11ee-b9d1-0242ac120002"), "Drinks"), "Description1", 10.0, 50),
                    new ProductDto(UUID.fromString("5a4b1746-91e9-11ee-b9d1-0242ac120002"), "Product2", new ProductCategoryDto(UUID.fromString("b411a132-91f3-11ee-b9d1-0242ac120002"), "Necesseties"), "Description2", 15.0, 30));
        }
        // Add more products as needed
    }

    @Operation(summary = "get product by id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Ok", content =
                            {@Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDto.class))}), //todo change to Product response?
                    @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
                    @ApiResponse(responseCode = "404", description = "Product not found")}
    )
    @GetMapping("/{productId}")
    public ProductResponseDto getProductById(@Parameter(description = "find product by id") @RequestParam(required = true) @NotNull @PathVariable String productId) {

        String id = productId;

        List<ProductDto> products = List.of(
                new ProductDto(UUID.fromString("4f32e852-91e9-11ee-b9d1-0242ac120002"), "Product1", new ProductCategoryDto(UUID.fromString("ac47c918-91f3-11ee-b9d1-0242ac120002"), "Drinks"), "Description1", 10.0, 50),
                new ProductDto(UUID.fromString("5a4b1746-91e9-11ee-b9d1-0242ac120002"), "Product2", new ProductCategoryDto(UUID.fromString("b411a132-91f3-11ee-b9d1-0242ac120002"), "Necesseties"), "Description2", 15.0, 30));

        ProductDto productDto = products.stream().filter(it -> it.getId().toString().equals(id)).findFirst().orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + id));

        return DtoToResponseDtoTranslator.toProductResponseDto(productDto);
        // Add more products as needed
    }

    @Override
    public ProductResponseDto updateProductById(String productId, ProductRequest productRequest) {
        List<ProductDto> products = List.of(
                new ProductDto(UUID.fromString("4f32e852-91e9-11ee-b9d1-0242ac120002"), "Product1", new ProductCategoryDto(UUID.fromString("ac47c918-91f3-11ee-b9d1-0242ac120002"), "Drinks"), "Description1", 10.0, 50),
                new ProductDto(UUID.fromString("5a4b1746-91e9-11ee-b9d1-0242ac120002"), "Product2", new ProductCategoryDto(UUID.fromString("b411a132-91f3-11ee-b9d1-0242ac120002"), "Necesseties"), "Description2", 15.0, 30));
        //todo make everything in product request as nullable. and check which properties have values.


        ProductDto productDto = products.stream().filter(it -> it.getId().toString().equals(productId)).findFirst().orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + productId));

        ProductCategoryDto dto = RequestToDtoTranslator.toProductCategoryDto(productRequest.getCategory());

        return new ProductResponseDto(productDto.getId(), productRequest.getName(), new ProductCategoryDto(UUID.randomUUID(), productRequest.getCategory().getName()), productRequest.getDescription(), productRequest.getPrice(), productRequest.getStock());
    }


    interface ApiProductController {
        @Operation(summary = "update product by id")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Ok", content =
                                {@Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDto.class))}), //todo change to Product response?
                        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
                        @ApiResponse(responseCode = "404", description = "Product not found")}
        )
        @PatchMapping("/{productId}")
        ProductResponseDto updateProductById(@Parameter(description = "update product by id") @RequestParam(required = true) @NotNull @PathVariable String productId);
    }
}
