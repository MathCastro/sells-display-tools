package com.example.demo.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class FilterSellBO {
	
	@NotEmpty
	@Size(max = 50)
	private String filter;

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

}
