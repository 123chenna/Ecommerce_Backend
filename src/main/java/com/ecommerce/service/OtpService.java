package com.ecommerce.service;

import com.ecommerce.model.Otp;
import com.ecommerce.repository.OtpRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private OtpRepository repo;

    // 📱 GENERATE OTP
    @Transactional  // ✅ IMPORTANT FIX
    public String generateOtp(String phone){

        String otp = String.valueOf(new Random().nextInt(900000) + 100000);

        // delete old OTP
        repo.deleteByPhone(phone);

        Otp otpEntity = new Otp();
        otpEntity.setPhone(phone);
        otpEntity.setOtp(otp);
        otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(5));

        repo.save(otpEntity);

        System.out.println("OTP for " + phone + " : " + otp);

        return otp;
    }

    // ✅ VERIFY OTP
    
    @Transactional
    public boolean verifyOtp(String phone, String enteredOtp){

        Otp otpEntity = repo.findByPhone(phone);

        if(otpEntity == null){
            System.out.println("❌ No OTP found");
            return false;
        }

        System.out.println("DB OTP: " + otpEntity.getOtp());
        System.out.println("Entered OTP: " + enteredOtp);

        // expiry check
        if(otpEntity.getExpiryTime().isBefore(LocalDateTime.now())){
            repo.deleteByPhone(phone);
            System.out.println("❌ OTP expired");
            return false;
        }

        // strict match
        if(!otpEntity.getOtp().equals(enteredOtp)){
            System.out.println("❌ OTP mismatch");
            return false;
        }

        // success
        repo.deleteByPhone(phone);
        return true;
    }
}