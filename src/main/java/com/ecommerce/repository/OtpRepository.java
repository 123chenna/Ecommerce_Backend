package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.model.Otp;

public interface OtpRepository extends JpaRepository<Otp, Long> {

    Otp findByPhone(String phone);

    void deleteByPhone(String phone);
}