package com.project.storebackend.controller;

import com.project.storebackend.request.ProductRequest;
import com.project.storebackend.response.ProductResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface ProductSwaggerController {

    @Operation(summary = "update product by id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Ok", content =
                            {@Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDto.class))}), //todo change to Product response?
                    @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
                    @ApiResponse(responseCode = "404", description = "Product not found")}
    )
    @PatchMapping("/{productId}")
    ProductResponseDto updateProductById(@Parameter(description = "update product by id") @RequestParam(required = true) @NotNull @PathVariable String productId,
                                         @Valid @RequestBody ProductRequest productRequest); //TODO add requestBody that conatins the updated properties
}
