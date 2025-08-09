package com.codewithmosh.store.orders;

import com.codewithmosh.store.auth.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {
    private final AuthService authService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderDto> getAllOrders() {
        var customer = authService.getCurrentUser();
        var orders = orderRepository.getAllByCustomer(customer);
        return orders.stream().map(orderMapper::toDto).toList();
    }

    public OrderDto getOrderById(Long id) {
        var order = orderRepository.getOrderWithItems(id).orElseThrow(OrderNotFoundException::new);

        var customer = authService.getCurrentUser();
        if (!order.isPlacedBy(customer)) {
            throw new AccessDeniedException("You don´t have access to this order");
        }

        return orderMapper.toDto(order);
    }
}
