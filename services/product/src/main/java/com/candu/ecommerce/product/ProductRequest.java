package com.candu.ecommerce.product;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,

        @NotNull(message = "the name should not be null")
        String name,

        @NotNull(message = "the description should not be null")
        String description,

        @NotNull(message = "the Quantity should not be null")
        @Min(value = 0, message = "Quantity should not be negative")
        double availableQuantity,

        @Min(value = 0, message = "Price should not be negative")
        BigDecimal price,

        @NotNull(message = "Product category is required")
        Integer categoryId

) {
}
