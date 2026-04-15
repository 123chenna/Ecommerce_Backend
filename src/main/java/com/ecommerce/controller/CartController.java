package com.ecommerce.controller;

import com.ecommerce.model.Cart;
import com.ecommerce.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
public class CartController {

    @Autowired
    private CartService service;

    // ✅ GET CART
    @GetMapping("/{username}")
    public List<Cart> getCart(@PathVariable String username) {
        return service.getCartByUser(username);
    }

    // ✅ ADD TO CART
    @PostMapping("/add")
    public Cart addToCart(@RequestBody Cart request) {
        return service.addToCart(
                request.getProductId(),
                request.getUsername(),
                request.getQuantity()
        );
    }

    // ✅ INCREASE
    @PutMapping("/increase/{id}")
    public Cart increase(@PathVariable Long id) {
        return service.increaseQty(id);
    }

    // ✅ DECREASE
    @PutMapping("/decrease/{id}")
    public Cart decrease(@PathVariable Long id) {
        return service.decreaseQty(id);
    }

    // ✅ REMOVE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.removeItem(id);
    }

    // ✅ CART COUNT
    @GetMapping("/count/{username}")
    public int getCount(@PathVariable String username) {
        return service.getCartCount(username);
    }
    @DeleteMapping("/clear/{username}")
    public void clearCart(@PathVariable String username) {
        List<Cart> items = service.getCartByUser(username);
        items.forEach(item -> service.removeItem(item.getId()));
    }
}