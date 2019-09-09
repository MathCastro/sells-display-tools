package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.model.SellBO;

public interface SellRepository extends PagingAndSortingRepository<SellBO, Long>{

	Optional<SellBO> findByName(String name);
	
}