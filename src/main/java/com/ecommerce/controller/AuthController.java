package com.ecommerce.controller;

import com.ecommerce.model.User;
import com.ecommerce.security.JwtUtil;
import com.ecommerce.service.OtpService;
import com.ecommerce.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private OtpService otpService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil; // ✅ FIXED

    // 📱 SEND OTP
    @PostMapping("/send-otp")
    public String sendOtp(@RequestBody Map<String, String> req){

        String phone = req.get("phone");

        if(phone == null || phone.isEmpty()){
            return "Phone required ❌";
        }

        String otp = otpService.generateOtp(phone);

        return "OTP: " + otp;
    }

    // ✅ VERIFY OTP
    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestBody Map<String, String> req){

        String phone = req.get("phone");
        String otp = req.get("otp");

        if(otpService.verifyOtp(phone, otp)){
            return "OTP valid";
        }

        return "Invalid OTP";
    }

    // 📝 REGISTER
    @PostMapping("/register")
    public String register(@RequestBody Map<String,String> req){

        String username = req.get("username");
        String phone = req.get("phone");
        String password = req.get("password");

        if(username == null || phone == null || password == null){
            return "Missing fields ❌";
        }

        if(userService.existsByPhone(phone)){
            return "User already exists";
        }

        userService.registerUser(username, phone, password);

        return "Registered Successfully";
    }

    // 🔐 LOGIN
    @PostMapping("/login")
    public String login(@RequestBody Map<String,String> req){

        String username = req.get("username");
        String password = req.get("password");

        System.out.println("Entered Username: " + username);
        System.out.println("Entered Password: " + password);

        User user = userService.findByUsername(username);

        if(user == null){
            return "User not found";
        }

        System.out.println("DB Password: " + user.getPassword());

        if(user.getPassword() == null){
            return "Use OTP login";
        }

        if(!user.getPassword().equals(password)){
            return "Invalid password";
        }

        return "SUCCESS:" + jwtUtil.generateToken(username); // ✅ FIXED RESPONSE
    }
}