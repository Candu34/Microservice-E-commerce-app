package com.candu.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "Prduct is mandatory")
        Integer productId,
        @Positive(message = "Quantity is mandatory")
        Double quantity
) {
}
