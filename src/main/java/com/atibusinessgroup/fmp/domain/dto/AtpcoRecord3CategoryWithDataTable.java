package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

import com.mongodb.DBObject;

public class AtpcoRecord3CategoryWithDataTable {
	
	@Field("category")
	private DBObject category;
	
	public DBObject getCategory() {
		return category;
	}

	public void setCategory(DBObject category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "AtpcoRecord3CategoryWithDataTable [category=" + category + "]";
	}
}
