//package com.training.ecommerce.repository;
//
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
////import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.stereotype.Repository;
//
//import com.training.ecommerce.entities.Category;
//
//@Repository
//public interface CategoryRepository extends JpaRepository<Category, Long> {
//
//	void save(Optional<Category> category);
//
//
//
//}
//	  

package com.training.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.training.ecommerce.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // No need for custom save method here
}
