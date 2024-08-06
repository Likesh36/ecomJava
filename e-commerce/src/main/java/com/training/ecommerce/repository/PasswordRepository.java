package com.training.ecommerce.repository;

import com.training.ecommerce.entities.Password;
import com.training.ecommerce.entities.User;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface PasswordRepository extends JpaRepository<Password, Integer> {

	List<Password> findTop3ByUserOrderByCreatedOnDesc(User user);
	List<Password> findByUserAndCreatedOnBefore(User user, Timestamp createdOn);
}
