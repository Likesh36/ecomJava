package com.training.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.ecommerce.dto.ChangePasswordRequest;
import com.training.ecommerce.dto.UserRegistrationRequest;
import com.training.ecommerce.dto.UserResponse;
import com.training.ecommerce.entities.Password;
import com.training.ecommerce.entities.User;
import com.training.ecommerce.exception.PasswordException;
import com.training.ecommerce.exception.UserAlreadyExistsException;
import com.training.ecommerce.exception.UserNotFoundException;
import com.training.ecommerce.services.UserService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins="http://localhost:4200")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser( @RequestBody UserRegistrationRequest request) {
		User response;
		try {
			response = userService.registerUser(request);
			return new ResponseEntity<>(response,HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
			
		}
	}

	@GetMapping("/getUser/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable("userId") int userId) {
		try {
			UserResponse userResponse = userService.getUserById(userId);
			return new ResponseEntity<>(userResponse, HttpStatus.OK);
		} catch (UserNotFoundException e) {

			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while creating the user");
		}
	}

	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}

	@PutMapping("/updateUserById/{userId}")
	public ResponseEntity<?> updateUserById(@PathVariable("userId") int userId, @RequestBody UserRegistrationRequest updatedRequest) {
		try {
			User updatedUser = userService.updateUserById(userId, updatedRequest);
			ResponseEntity<?> responseEntity = new ResponseEntity<>(updatedUser, HttpStatus.OK);
			return responseEntity;
		} catch (UserNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while creating the user");
		}
	}

	@PutMapping("/updateUserByUsername/{username}")
	public ResponseEntity<?> updateUserByUsername(@PathVariable("username") String username, @RequestBody UserRegistrationRequest updatedRequest) {
		try {
			User updatedUser = userService.updateUserByUsername(username, updatedRequest);
			ResponseEntity<?> responseEntity = new ResponseEntity<>(updatedUser, HttpStatus.OK);
			return responseEntity;
		} catch (UserNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while creating the user");
		}
	}

	@PostMapping("/{userId}/change-password")
	public ResponseEntity<?> changePassword(@PathVariable int userId,
			@Valid @RequestBody ChangePasswordRequest request) {
		Password response;
		try {
			response = userService.changePassword(userId, request.getNewPassword());
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (UserNotFoundException | PasswordException  e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
