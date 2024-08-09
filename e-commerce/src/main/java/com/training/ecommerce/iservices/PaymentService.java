package com.training.ecommerce.iservices;

import java.util.List;
import com.training.ecommerce.entities.Payment;

public interface PaymentService {
    Payment createPayment(Payment payment);
    Payment getPaymentById(Long id);
    List<Payment> getAllPayments();
    List<Payment> getPaymentsByOrderIds(List<Long> orderIds);
    Payment updatePayment(Long id, Payment payment);
    void deletePayment(Long id);
    

    Payment update(Long id, String name, String description, Double amount, String paymentMethod, String updateInfo);
       
}

