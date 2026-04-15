package com.ecommerce.service;

import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public User registerUser(String username, String phone, String password){

        User user = new User();
        user.setUsername(username);
        user.setPhone(phone);
        user.setPassword(password);

        return repo.save(user);
    }

    public boolean existsByPhone(String phone){
        return repo.findByPhone(phone) != null;
    }
    public User findByUsername(String username){
        return repo.findByUsername(username);
    }
    
}