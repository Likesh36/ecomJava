package com.training.ecommerce.entities;

import java.sql.Timestamp;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "password_tbl")
public class Password {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pass_id")
	private int passId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonBackReference
	private User user;
	
	@Column(name="password")
	private String password;

	@Column(name = "created_on")
	private Timestamp createdOn;

	@Column(name = "last_expire_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate lastExpireDate;

	/*
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "user_id", nullable = false)
	 * 
	 * @JsonBackReference private User user;
	 */

	public int getPassId() {
		return passId;
	}

	public void setPassId(int passId) {
		this.passId = passId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDate getLastExpireDate() {
		return lastExpireDate;
	}

	public void setLastExpireDate(LocalDate lastExpireDate) {
		this.lastExpireDate = lastExpireDate;
	}

	
	/*
	 * public User getUser() { return user; }
	 * 
	 * public void setUser(User user) { this.user = user; }
	 */

}
