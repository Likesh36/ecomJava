package com.training.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.ecommerce.entities.Payment;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByOrderIdIn(List<Long> orderIds);
}


