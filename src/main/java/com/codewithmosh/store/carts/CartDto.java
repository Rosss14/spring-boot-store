package com.codewithmosh.store.carts;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Data
public class CartDto {
    private UUID id;
    private Set<CartItemDto> items;
    private BigDecimal totalPrice;
}
