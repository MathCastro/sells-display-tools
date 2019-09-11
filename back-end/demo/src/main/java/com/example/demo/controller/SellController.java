package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.FilterSellBO;
import com.example.demo.model.SellBO;
import com.example.demo.repository.SellRepository;
import com.example.demo.service.SellService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/sell")
public class SellController {

	@Autowired
	private SellRepository repository;
	
	@Autowired
	private SellService service;
	
	@GetMapping
	public ResponseEntity<Page<SellBO>> list(
			@RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
		Page<SellBO> list = service.getAllSells(pageNo, pageSize, sortBy);
		 
        return new ResponseEntity<Page<SellBO>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SellBO> get(@PathVariable Long id) {
		Optional<SellBO> sell = repository.findById(id);
	
		if (sell == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(sell.get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public SellBO create(@Valid @RequestBody SellBO sell) {
		Optional<SellBO> existingSell = repository
				.findByName(sell.getName());
		
		if (existingSell.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"There is already a sell with this name");
		}
		
		return repository.save(sell);
	}
	
	@PostMapping("/filter")
	@ResponseStatus(HttpStatus.CREATED)
	public Optional<List<SellBO>> filter(@Valid @RequestBody FilterSellBO sell) {
		Optional<List<SellBO>> existingSell = repository
				.filterSellsByName(sell.getFilter());
		
		return existingSell;
	}
	
	@GetMapping("/filter-sells")
	public ResponseEntity<Page<SellBO>> filterSells(
			@RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "") String value) {
		Page<SellBO> list = service.getAllSellsFiltered(pageNo, pageSize, sortBy, value);
		 
        return new ResponseEntity<Page<SellBO>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<SellBO> update(@Valid @RequestBody SellBO sell) {
		Optional<SellBO> newsell = repository.findById(sell.getId());
		
		if (newsell == null) {
			return ResponseEntity.notFound().build();
		} else {
			this.repository.save(sell);
	        return ResponseEntity.ok().build();
		}
	}

	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<SellBO> delete(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	
}