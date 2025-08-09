package com.codewithmosh.store.carts;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UpdateCartItemRequest {
    @Min(value=1, message = "The quantity must be greater than or equal to 1")
    @Max(value=100, message = "The quantity must not surpass 100")
    private int quantity;
}
