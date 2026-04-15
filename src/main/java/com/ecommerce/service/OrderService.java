package com.ecommerce.service;

import com.ecommerce.model.Orders;
import com.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    // ✅ PLACE ORDER
    public Orders placeOrder(Orders order) {
        if (order == null) {
            throw new RuntimeException("Order cannot be null");
        }
        return repo.save(order);
    }

    // ✅ GET ORDERS BY USER
    public List<Orders> getOrdersByUser(String username) {
        return repo.findByUsername(username);
    }

    // ✅ GET ALL ORDERS
    public List<Orders> getAllOrders() {
        return repo.findAll();
    }

    // ✅ DELETE ORDER
    public void deleteOrder(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Order not found with id: " + id);
        }
        repo.deleteById(id);
    }
}