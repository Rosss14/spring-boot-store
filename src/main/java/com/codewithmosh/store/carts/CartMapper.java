package com.codewithmosh.store.carts;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

//@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(source="cartItems", target="items")
    @Mapping(target="totalPrice", expression="java(cart.getTotalPrice())")
    CartDto toDto(Cart cart);

    @Mapping(target="totalPrice", expression="java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItem cartItem);

    void update(UpdateCartItemRequest updateCartItemRequest, @MappingTarget CartItem cartItem);

}
