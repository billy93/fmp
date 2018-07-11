package com.atibusinessgroup.fmp.domain.dto;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_record_1")
public class FareClassGroup implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Field("cxr_code")
	private String cxr;
	
	@Field("tar_no")
	private String tarNo;
	
	@Field("tar_cd")
	private String tarCd;
	
	@Field("description")
	private String description;
	
	@Field("rule_no")
	private String ruleNo;
	
	private int page;
	private int size;
	private String fareClass;
	private String psgrType;
	private String fareType;
	private String bookingClass;

	public String getCxr() {
		return cxr;
	}

	public void setCxr(String cxr) {
		this.cxr = cxr;
	}

	public String getTarNo() {
		return tarNo;
	}

	public void setTarNo(String tarNo) {
		this.tarNo = tarNo;
	}

	public String getTarCd() {
		return tarCd;
	}

	public void setTarCd(String tarCd) {
		this.tarCd = tarCd;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRuleNo() {
		return ruleNo;
	}

	public void setRuleNo(String ruleNo) {
		this.ruleNo = ruleNo;
	}
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public String getFareClass() {
		return fareClass;
	}

	public void setFareClass(String fareClass) {
		this.fareClass = fareClass;
	}

	public String getPsgrType() {
		return psgrType;
	}

	public void setPsgrType(String psgrType) {
		this.psgrType = psgrType;
	}

	public String getFareType() {
		return fareType;
	}

	public void setFareType(String fareType) {
		this.fareType = fareType;
	}

	public String getBookingClass() {
		return bookingClass;
	}

	public void setBookingClass(String bookingClass) {
		this.bookingClass = bookingClass;
	}

	@Override
	public String toString() {
		return "FareClassGroup [cxr=" + cxr + ", tarNo=" + tarNo + ", tarCd=" + tarCd + ", description=" + description
				+ ", ruleNo=" + ruleNo + ", page=" + page + ", size=" + size + ", fareClass=" + fareClass
				+ ", psgrType=" + psgrType + ", fareType=" + fareType + ", bookingClass=" + bookingClass + "]";
	}


}