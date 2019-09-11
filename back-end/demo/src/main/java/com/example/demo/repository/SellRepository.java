package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.SellBO;

public interface SellRepository extends PagingAndSortingRepository<SellBO, Long>{

	Optional<SellBO> findByName(String name);
	
//	@Query("SELECT u FROM User u WHERE u.status = ?1")
	@Query("SELECT s, u FROM SellBO s JOIN s.userBO u WHERE s.name like %?1%"
			+ "OR s.value like %?1%"
			+ "OR u.username like %?1%")
	Optional<List<SellBO>> filterSellsByName(String name);
	
	@Query(value = "SELECT s FROM SellBO s JOIN s.userBO u WHERE s.name like %?1%"
					+ "OR s.value like %?1%"
					+ "OR u.username like %?1%",
		    countQuery = "SELECT count(*) FROM SellBO s JOIN s.userBO u WHERE s.name like %?1%"
		    			+ "OR s.value like %?1%"
		    			+ "OR u.username like %?1%",
		    nativeQuery = false)
	Page<SellBO> filterSells(@Param("value") String value, Pageable pageable);
	
}