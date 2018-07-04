package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

import com.mongodb.DBObject;

public class AtpcoRecord3CategoryWithDataTable {
	
	@Field("tbl_no")
	private String tableNo;
	
	@Field("category")
	private DBObject category;
	
	public String getTableNo() {
		return tableNo;
	}

	public void setTableNo(String tableNo) {
		this.tableNo = tableNo;
	}

	public DBObject getCategory() {
		return category;
	}

	public void setCategory(DBObject category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "AtpcoRecord3CategoryWithDataTable [tableNo=" + tableNo + ", category=" + category + "]";
	}
}
