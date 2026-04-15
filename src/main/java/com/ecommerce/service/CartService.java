package com.ecommerce.service;

import com.ecommerce.model.Cart;
import com.ecommerce.model.Product;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private ProductRepository productRepo;

    // ✅ GET CART
    public List<Cart> getCartByUser(String username) {
        return cartRepo.findByUsername(username);
    }

    // ✅ ADD TO CART
    public Cart addToCart(Long productId, String username, int qty) {

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Cart existing = cartRepo.findByProductIdAndUsername(productId, username);

        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + qty);
            return cartRepo.save(existing);
        }

        Cart cart = new Cart();
        cart.setProductId(product.getId());
        cart.setName(product.getName());
        cart.setPrice(product.getPrice());
        cart.setImage(product.getImage()); // ✅ IMPORTANT
        cart.setQuantity(qty);
        cart.setUsername(username);

        return cartRepo.save(cart);
    }

    // ✅ INCREASE QTY
    public Cart increaseQty(Long id) {
        Cart cart = cartRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        cart.setQuantity(cart.getQuantity() + 1);
        return cartRepo.save(cart);
    }

    // ✅ DECREASE QTY
    public Cart decreaseQty(Long id) {
        Cart cart = cartRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        if (cart.getQuantity() > 1) {
            cart.setQuantity(cart.getQuantity() - 1);
            return cartRepo.save(cart);
        } else {
            cartRepo.deleteById(id);
            return null;
        }
    }

    // ✅ REMOVE ITEM
    public void removeItem(Long id) {
        cartRepo.deleteById(id);
    }

    // ✅ CART COUNT
    public int getCartCount(String username) {
        return cartRepo.findByUsername(username)
                .stream()
                .mapToInt(Cart::getQuantity)
                .sum();
    }
}