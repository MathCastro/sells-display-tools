package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.UserBO;
import com.example.demo.repository.UserRepository;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository repository;
	
	@GetMapping
	public List<UserBO> list() {
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserBO> get(@PathVariable Long id) {
		Optional<UserBO> sell = repository.findById(id);
	
		if (sell == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(sell.get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserBO create(@Valid @RequestBody UserBO sell) {
		Optional<UserBO> existingSell = repository
				.findByUsername(sell.getUsername());
		
		if (existingSell.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"There is already a user with this username");
		}
		
		return repository.save(sell);
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<UserBO> update(@Valid @RequestBody UserBO sell) {
		Optional<UserBO> newsell = repository.findById(sell.getId());
		
		if (newsell == null) {
			return ResponseEntity.notFound().build();
		} else {
			this.repository.save(sell);
	        return ResponseEntity.ok().build();
		}
	}

	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<UserBO> delete(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	
}
