package com.example.paymentbatch.repository;

import com.example.paymentbatch.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
