package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.UserBO;

public interface UserRepository extends JpaRepository<UserBO, Long>{

	Optional<UserBO> findByUsername(String username);
	
}