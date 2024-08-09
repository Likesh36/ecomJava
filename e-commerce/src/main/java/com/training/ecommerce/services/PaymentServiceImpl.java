package com.training.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.training.ecommerce.entities.Payment;
import com.training.ecommerce.iservices.PaymentService;
import com.training.ecommerce.repository.PaymentRepository;

import java.util.List;
import java.util.Optional;


@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        return optionalPayment.orElse(null);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public List<Payment> getPaymentsByOrderIds(List<Long> orderIds) {
        return paymentRepository.findByOrderIdIn(orderIds);
    }

    @Override
    public Payment updatePayment(Long id, Payment payment) {
        if (paymentRepository.existsById(id)) {
            payment.setId(id);
            return paymentRepository.save(payment);
        }
        return null;
    }

    @Override
    public void deletePayment(Long id) {
        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
        }
    }

    public Payment updatetrackrec(Long id, String name, String description, Double amount, String paymentMethod, String updateInfo, String trackrec) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);

        if (optionalPayment.isPresent()) {
        	 Payment payment = optionalPayment.get();
                 payment.setTrackrec(trackrec);
            return paymentRepository.save(payment);
        } else {
            throw new RuntimeException("Payment not found with id " + id);
        }


}

	@Override
	public Payment update(Long id, String name, String description, Double amount, String paymentMethod,
			String updateInfo) {
		// TODO Auto-generated method stub
		return null;
	}
}


