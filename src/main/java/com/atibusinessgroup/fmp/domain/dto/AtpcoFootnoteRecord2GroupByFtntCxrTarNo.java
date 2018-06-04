package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class AtpcoFootnoteRecord2GroupByFtntCxrTarNo {
	
	@Field("fare_tar_no")
    private String tariffNumber;
	
	@Field("cxr_code")
    private String carrierCode;
	
	@Field("ftnt")
    private String ftnt;
	
	@Field("cat_no")
    private String catNo;

	public String getTariffNumber() {
		return tariffNumber;
	}

	public void setTariffNumber(String tariffNumber) {
		this.tariffNumber = tariffNumber;
	}

	public String getCarrierCode() {
		return carrierCode;
	}

	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}

	public String getFtnt() {
		return ftnt;
	}

	public void setFtnt(String ftnt) {
		this.ftnt = ftnt;
	}

	public String getCatNo() {
		return catNo;
	}

	public void setCatNo(String catNo) {
		this.catNo = catNo;
	}
	
	
	
	
}
