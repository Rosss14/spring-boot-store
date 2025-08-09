package com.codewithmosh.store.orders;

import com.codewithmosh.store.products.Product;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDto toDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setId( order.getId() );
        orderDto.setStatus( order.getStatus() );
        orderDto.setCreatedAt( order.getCreatedAt() );
        orderDto.setItems( orderItemSetToOrderItemDtoSet( order.getItems() ) );
        orderDto.setTotalPrice( order.getTotalPrice() );

        return orderDto;
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

    protected OrderItemDto orderItemToOrderItemDto(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemDto orderItemDto = new OrderItemDto();

        orderItemDto.setProduct( productToProductDto( orderItem.getProduct() ) );
        if ( orderItem.getQuantity() != null ) {
            orderItemDto.setQuantity( orderItem.getQuantity() );
        }
        orderItemDto.setTotalPrice( orderItem.getTotalPrice() );

        return orderItemDto;
    }

    protected Set<OrderItemDto> orderItemSetToOrderItemDtoSet(Set<OrderItem> set) {
        if ( set == null ) {
            return null;
        }

        Set<OrderItemDto> set1 = new LinkedHashSet<OrderItemDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( OrderItem orderItem : set ) {
            set1.add( orderItemToOrderItemDto( orderItem ) );
        }

        return set1;
    }
}
