package com.atibusinessgroup.fmp.domain.atpco;

import org.springframework.data.mongodb.core.mapping.Field;

public class AtpcoRecord8SecondaryTypePsgr {
	
	@Field("sec_pass_type")
	private String sec_pass_type;
	
	@Field("geo_type")
	private String geo_type;

	public String getSec_pass_type() {
		return sec_pass_type;
	}

	public void setSec_pass_type(String sec_pass_type) {
		this.sec_pass_type = sec_pass_type;
	}

	public String getGeo_type() {
		return geo_type;
	}

	public void setGeo_type(String geo_type) {
		this.geo_type = geo_type;
	}

	@Override
	public String toString() {
		return "AtpcoRecord8SecondaryTypePsgr [sec_pass_type=" + sec_pass_type + ", geo_type=" + geo_type + "]";
	}
	
	
	
}
