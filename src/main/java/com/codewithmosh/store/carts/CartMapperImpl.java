package com.codewithmosh.store.carts;

import com.codewithmosh.store.products.Product;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Component
public class CartMapperImpl implements CartMapper {

    @Override
    public CartDto toDto(Cart cart) {
        if ( cart == null ) {
            return null;
        }

        CartDto cartDto = new CartDto();

        cartDto.setItems( cartItemSetToCartItemDtoSet( cart.getCartItems() ) );
        cartDto.setId( cart.getId() );

        cartDto.setTotalPrice( cart.getTotalPrice() );

        return cartDto;
    }

    @Override
    public CartItemDto toDto(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }

        CartItemDto cartItemDto = new CartItemDto();

        cartItemDto.setProduct( productToProductDto( cartItem.getProduct() ) );
        if ( cartItem.getQuantity() != null ) {
            cartItemDto.setQuantity( cartItem.getQuantity() );
        }

        cartItemDto.setTotalPrice( cartItem.getTotalPrice() );

        return cartItemDto;
    }

    @Override
    public void update(UpdateCartItemRequest updateCartItemRequest, CartItem cartItem) {
        if ( updateCartItemRequest == null ) {
            return;
        }

        cartItem.setQuantity( updateCartItemRequest.getQuantity() );
    }

    protected Set<CartItemDto> cartItemSetToCartItemDtoSet(Set<CartItem> set) {
        if ( set == null ) {
            return null;
        }

        Set<CartItemDto> set1 = new LinkedHashSet<CartItemDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CartItem cartItem : set ) {
            set1.add( toDto( cartItem ) );
        }

        return set1;
    }

    protected ProductDto productToProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setId( product.getId() );
        productDto.setName( product.getName() );
        productDto.setPrice( product.getPrice() );

        return productDto;
    }
}

