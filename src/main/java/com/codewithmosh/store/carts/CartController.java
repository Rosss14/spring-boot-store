package com.codewithmosh.store.carts;

import com.codewithmosh.store.common.ErrorDto;
import com.codewithmosh.store.products.ProductNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/carts")
@Tag(name="Carts")
public class CartController {
    private final CartService cartService;

//    @GetMapping
//    public ResponseEntity<List<Cart>> getAllCarts() {
//        return ResponseEntity.ok(cartRepository.findAll());
//    }

    @GetMapping("/{id}")
    public CartDto getCart(@PathVariable UUID id) {
        return cartService.getCart(id);
    }

    @PostMapping
    public ResponseEntity<CartDto> createCart(
            UriComponentsBuilder uriBuilder
    ) {
        var cartDto = cartService.createCart();
        var uri = uriBuilder.path("/carts/{id}").buildAndExpand(cartDto.getId()).toUri();

        return ResponseEntity.created(uri).body(cartDto);
    }

    @PostMapping("/{cartId}/items")
    @Operation(summary = "Adds a product to the cart.")
    public ResponseEntity<CartItemDto> addProductToCart(
            @Parameter(description = "The ID of the cart.") @PathVariable UUID cartId,
            @RequestBody AddItemToCartRequest request) {

        var cartItemDto = cartService.addToCart(cartId, request.getProductId());

        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto);
    }

    @PutMapping("/{cartId}/items/{productId}")
    public CartItemDto updateCartItem(
            @PathVariable(name="cartId") UUID cartId,
            @PathVariable(name="productId") Long productId,
            @Valid @RequestBody UpdateCartItemRequest request
    ){
        return cartService.updateCartItem(cartId, productId, request);
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> deleteCartItem(
            @PathVariable(name="cartId") UUID cartId,
            @PathVariable(name="productId") Long productId) {

        cartService.deleteCartItem(cartId, productId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cartId}/items")
    public ResponseEntity<?> clearCart(@PathVariable UUID cartId) {

        cartService.clearCart(cartId);

        return ResponseEntity.noContent().build();

    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ErrorDto> handleCartNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto( "Cart not found"));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFound(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto("Product not found in the cart"));
    }
}
