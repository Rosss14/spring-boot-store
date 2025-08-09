package com.codewithmosh.store.carts;

import com.codewithmosh.store.products.Product;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id")
    private UUID id;

    @Column(name="date_created", insertable=false, updatable=false)
    private LocalDate dateCreated;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<CartItem> cartItems = new LinkedHashSet<>();

    public BigDecimal getTotalPrice(){
        var totalPrice = BigDecimal.ZERO;
        for (CartItem item : cartItems) {
            totalPrice = totalPrice.add(item.getTotalPrice());
        }
        return totalPrice;
    }

    public CartItem getItem(Long productId){
        return cartItems.stream()
                .filter(item->item.getProduct().getId().equals(productId))
                .findFirst().orElse(null);
    }

    public CartItem addItem(Product product){
        var cartItem = getItem(product.getId());
        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity()+1);
        }else {
            cartItem = new CartItem();
            cartItem.setCart(this);
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            cartItems.add(cartItem);
        }
        return cartItem;
    }

    public void removeItem(Long productId){
        var cartItem = getItem(productId);
        if (cartItem != null) {
            cartItems.remove(cartItem);
            cartItem.setCart(null);
        }
    }

    public void clear(){
        cartItems.clear();
    }

    public boolean isEmpty(){
        return cartItems.isEmpty();
    }
}
