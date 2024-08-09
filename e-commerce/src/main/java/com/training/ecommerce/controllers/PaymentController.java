package com.training.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.training.ecommerce.dto.ChangePasswordRequest;
import com.training.ecommerce.entities.Payment;
import com.training.ecommerce.exception.PasswordException;
import com.training.ecommerce.exception.UserNotFoundException;
import com.training.ecommerce.iservices.PaymentService;
import com.training.ecommerce.services.UserService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins="http://localhost:4200")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        Payment newPayment = paymentService.createPayment(payment);
        return ResponseEntity.ok(newPayment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        return payment != null ? ResponseEntity.ok(payment) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/byOrderIds")
    public ResponseEntity<List<Payment>> getPaymentsByOrderIds(@RequestParam List<Long> orderIds) {
        List<Payment> payments = paymentService.getPaymentsByOrderIds(orderIds);
        return ResponseEntity.ok(payments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable Long id, @RequestBody Payment payment) {
        Payment updatedPayment = paymentService.updatePayment(id, payment);
        return updatedPayment != null ? ResponseEntity.ok(updatedPayment) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
//    @PutMapping()
//    public void update(@PathVariable Long id) {
//    	paymentService.update(id);
//    	
    @PutMapping("/trackrec/{id}")
    public ResponseEntity<String> trackrec(@PathVariable Long id,@Valid @RequestBody Payment payment) {
    	try
    	{
    		paymentService.update(id, payment.getName(), payment.getDescription(), payment.getAmount(), payment.getPaymentMethod(), payment.getTrackrec());
    		return ResponseEntity.ok("Delivered");
    	}
    	catch(Exception ex)
    	{
    		return ResponseEntity.badRequest().body(ex.getMessage());
    	}
    }
    	    
}	
	
    	
    



