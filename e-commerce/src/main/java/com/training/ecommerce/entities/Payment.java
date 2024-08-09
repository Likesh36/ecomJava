package com.training.ecommerce.entities;
import jakarta.persistence.*;

@Entity
@Table(name="payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    private String description;
    
    private Double amount;
    
    private String paymentMethod;
    
    private String trackrec;
    
    private Long orderId;
    
    
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getTrackrec() {
		return trackrec;
	}
	public void setTrackrec(String trackrec) {
		this.trackrec = trackrec;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Payment(Long id, String name, String description, Double amount, String paymentMethod, String trackrec,
			Long orderId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.trackrec = trackrec;
		this.orderId = orderId;
	}
	@Override
	public String toString() {
		return "Payment [id=" + id + ", name=" + name + ", description=" + description + ", amount=" + amount
				+ ", paymentMethod=" + paymentMethod + ", trackrec=" + trackrec + ", orderId=" + orderId + "]";
	}
	
	
	
	
    
    
}
