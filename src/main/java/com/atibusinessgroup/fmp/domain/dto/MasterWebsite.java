package com.atibusinessgroup.fmp.domain.dto;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "voltras_fare")
public class MasterWebsite implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Field("value")
	private String website;

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Override
	public String toString() {
		return "MasterWebsite [website=" + website + "]";
	}
}
