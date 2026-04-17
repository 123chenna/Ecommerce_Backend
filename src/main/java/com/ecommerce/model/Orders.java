package com.ecommerce.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Data;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Data
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;     // ✅ ADD
    private String phone;    // ✅ ADD
    private Long total;
    private String address;
    private String payment;
    private String username;
    
    private String status;

   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
private ZonedDateTime orderTime;

@PrePersist
public void onCreate() {
    this.orderTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata")); // ✅ FIXED
    this.status = "Pending";
}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ZonedDateTime getOrderTime() {
    return orderTime;
}

public void setOrderTime(ZonedDateTime orderTime) {
    this.orderTime = orderTime;
}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
}
