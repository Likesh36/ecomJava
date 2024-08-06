package com.training.ecommerce.services;

import com.training.ecommerce.dto.UserRegistrationRequest;
import com.training.ecommerce.dto.UserResponse;
import com.training.ecommerce.entities.Password;
import com.training.ecommerce.entities.User;
import com.training.ecommerce.exception.PasswordException;
import com.training.ecommerce.exception.UserAlreadyExistsException;
import com.training.ecommerce.exception.UserNotFoundException;

import com.training.ecommerce.repository.PasswordRepository;
import com.training.ecommerce.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserService {

	private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*[\\W])(?=.*\\d).+$");

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordRepository passwordRepository;

	public User registerUser(UserRegistrationRequest request) throws UserAlreadyExistsException {
		Optional<User> existingUserByUsername = userRepository.findByUserName(request.getUserName());
		if (existingUserByUsername.isPresent()) {
			throw new UserAlreadyExistsException("Username already exists");
		}

		Optional<User> existingUserByEmail = userRepository.findByEmailId(request.getEmailId());
		if (existingUserByEmail.isPresent()) {
			throw new UserAlreadyExistsException("Email ID already exists");
		}

		Optional<User> existingUserByPhone = userRepository.findByPhoneNumber(request.getPhoneNumber());
		if (existingUserByPhone.isPresent()) {
			throw new UserAlreadyExistsException("Phone number already exists");
		}

		User user = new User();
        user.setUserName(request.getUserName());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob()); // Ensure dob is set correctly
        user.setEmailId(request.getEmailId());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(request.getPassword()); // Store password in the user entity as well
        user.setCreatedOn(new Timestamp(System.currentTimeMillis()));
        user.setAdmin(false); // Default to non-admin, change as needed

		User savedUser = userRepository.save(user);

		Password initialPassword = new Password();
		initialPassword.setUser(savedUser);
		initialPassword.setPassword(request.getPassword()); // Consider hashing the password
		initialPassword.setCreatedOn(new Timestamp(System.currentTimeMillis()));
		initialPassword.setLastExpireDate(LocalDate.now().plusDays(90));

		passwordRepository.save(initialPassword);
		return savedUser;
	}
	public List<User> getAllUsers() {

		return userRepository.findAll();
	}

	public User updateUserById(int userId, UserRegistrationRequest updateRequest) throws UserNotFoundException {
		User existingUser = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User ID not found"));
		existingUser.setUserName(updateRequest.getUserName());
		existingUser.setFirstName(updateRequest.getFirstName());
		existingUser.setLastName(updateRequest.getLastName());
		existingUser.setDob(updateRequest.getDob());
		existingUser.setEmailId(updateRequest.getEmailId());
		existingUser.setPhoneNumber(updateRequest.getPhoneNumber());
		return userRepository.save(existingUser);
	}

	public User updateUserByUsername(String username, UserRegistrationRequest updateRequest) throws UserNotFoundException {
		User existingUser = userRepository.findByUserName(username)
				.orElseThrow(() -> new UserNotFoundException("Username not found"));
		existingUser.setUserName(updateRequest.getUserName());
		existingUser.setFirstName(updateRequest.getFirstName());
		existingUser.setLastName(updateRequest.getLastName());
		existingUser.setDob(updateRequest.getDob());
		existingUser.setEmailId(updateRequest.getEmailId());
		existingUser.setPhoneNumber(updateRequest.getPhoneNumber());
		return userRepository.save(existingUser);
	}

	public UserResponse getUserById(int userId) throws UserNotFoundException {

		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User ID not found"));
		
		UserResponse response = new UserResponse();
		response.setUserName(user.getUserName());
		response.setFirstName(user.getFirstName());
		response.setLastName(user.getLastName());
		response.setDob(user.getDob());
		response.setEmailId(user.getEmailId());
		response.setPassword(user.getPassword());
		response.setPhoneNumber(user.getPhoneNumber());

		return response;
	}
	
	

	public Password changePassword(int userId, String newPassword) throws PasswordException, UserNotFoundException {
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
 
		// Check if the new password meets the criteria
		if (!isPasswordValid(newPassword)) {
			throw new PasswordException(
					"Password must contain at least one uppercase letter, one special character, and one number");
		}
 
		// Check if the new password is the same as any of the last three passwords
		List<Password> passwordHistories = passwordRepository.findTop3ByUserOrderByCreatedOnDesc(user);
		List<String> lastThreePasswords = passwordHistories.stream().map(Password::getPassword)
				.collect(Collectors.toList());
 
		if (lastThreePasswords.contains(newPassword)) {
			throw new PasswordException("New password cannot be the same as the last 3 passwords");
		}
 
		Password newPasswordEntry = new Password();
		newPasswordEntry.setUser(user);
		newPasswordEntry.setPassword(newPassword); // Consider hashing the password
		newPasswordEntry.setCreatedOn(new Timestamp(System.currentTimeMillis()));
		newPasswordEntry.setLastExpireDate(LocalDate.now().plusDays(90));
 
		// Save the new password
	    Password savedPassword = passwordRepository.save(newPasswordEntry);
 
	    // Delete passwords older than the three most recent
	    if (passwordHistories.size() >= 2) {
	        List<Password> passwordsToDelete = passwordRepository.findByUserAndCreatedOnBefore(user, passwordHistories.get(1).getCreatedOn());
	        passwordRepository.deleteAll(passwordsToDelete);
	    }
 
	    return savedPassword;
	}
	private boolean isPasswordValid(String password) {
		return PASSWORD_PATTERN.matcher(password).matches();
	}



}
