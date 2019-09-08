package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.SellBO;

public interface SellRepository extends JpaRepository<SellBO, Long>{

	Optional<SellBO> findByName(String name);
	
}