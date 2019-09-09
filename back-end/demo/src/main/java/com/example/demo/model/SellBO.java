package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_sells")
public class SellBO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sell_id")
	private Long id;
	@NotEmpty
	@Size(max = 50)
	@Column(name = "value")
	private String value;
	@NotEmpty
	@Size(max = 50)
	@Column(name = "name")
	private String name;
	@ManyToOne
    @JoinColumn(name="user_id")
	private UserBO userBO;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UserBO getUserBO() {
		return userBO;
	}
	public void setUserBO(UserBO userBO) {
		this.userBO = userBO;
	}

	
}
