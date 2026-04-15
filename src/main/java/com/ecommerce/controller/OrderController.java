package com.ecommerce.controller;

import com.ecommerce.model.Orders;
import com.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService service;

    // ✅ PLACE ORDER
    @PostMapping
    public Orders placeOrder(@RequestBody Orders order) {
        if (order == null) {
            throw new RuntimeException("Order data is missing");
        }
        return service.placeOrder(order);
    }

    // ✅ GET USER ORDERS
    @GetMapping("/{username}")
    public List<Orders> getOrders(@PathVariable String username) {
        return service.getOrdersByUser(username);
    }

    // ✅ GET ALL ORDERS (Optional - useful for admin)
    @GetMapping
    public List<Orders> getAllOrders() {
        return service.getAllOrders();
    }

    // ✅ DELETE ORDER (Optional)
    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Long id) {
        service.deleteOrder(id);
        return "Order deleted successfully";
    }
}