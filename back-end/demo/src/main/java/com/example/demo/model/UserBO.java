package com.example.demo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "tb_user")
public class UserBO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	@NotEmpty
	@Size(max = 50)
	@Column(name = "username")
	private String username;
	@NotEmpty
	@Size(max = 50)
	@Column(name = "password")
	@JsonIgnore
	private String password;
	@JsonIgnore
	@OneToMany(mappedBy = "userBO", cascade = CascadeType.ALL)
	private Set<SellBO> sellBO;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<SellBO> getSellBO() {
		return sellBO;
	}
	public void setSellBO(Set<SellBO> sellBO) {
		this.sellBO = sellBO;
	}
	
	

}
