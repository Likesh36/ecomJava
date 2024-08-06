package com.training.ecommerce.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	@Column(name="description")
	private String description;
	@Column(name="name")
	private String name;

	@Column(name="id")
	private Long categoryId;

	@Column(name="price")
	private double price;
	@Column(name="imageUrl")
	private String imageUrl;

	@Column(name="stock")
	private Long stock;

	@Column(name="status")
	private String status;

	@Column(name="created_by")
	private String created_by;
	@Column(name="updated_by")
	private String updated_by;
	public Product(Long productId2, String description2, String name2, Long categoryId2, double price2,
			String imageUrl2, Long stock2, String status2, String created_by2, String updated_by2) {
		// TODO Auto-generated constructor stub
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Long getStock() {
		return stock;
	}
	public void setStock(Long stock) {
		this.stock = stock;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}


