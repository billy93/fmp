package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

import com.mongodb.DBObject;

public class AtpcoRecord3CategoryWithDataTable {
	
	@Field("category")
	private DBObject category;
	
	@Field("text_table_996")
	private TextTable textTable996;

	public DBObject getCategory() {
		return category;
	}

	public void setCategory(DBObject category) {
		this.category = category;
	}
	
	public TextTable getTextTable996() {
		return textTable996;
	}

	public void setTextTable996(TextTable textTable996) {
		this.textTable996 = textTable996;
	}

	@Override
	public String toString() {
		return "AtpcoRecord3CategoryWithDataTable [category=" + category + ", textTable996=" + textTable996 + "]";
	}
}
