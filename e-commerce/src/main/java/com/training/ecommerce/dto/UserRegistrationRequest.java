package com.training.ecommerce.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationRequest {

	@NotBlank(message = "Username cannot be blank")
	private String userName;

	@NotBlank(message = "First name cannot be blank")
	private String firstName;

	@NotBlank(message = "Last name cannot be blank")
	private String lastName;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;

	@NotBlank(message = "Email cannot be blank")
	@Email(message = "Email should be valid")
	private String emailId;

	@NotBlank(message = "Password cannot be blank")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	private String password;

	private long phoneNumber;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
