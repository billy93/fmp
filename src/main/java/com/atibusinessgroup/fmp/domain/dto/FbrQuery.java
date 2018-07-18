package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_record_8")
public class FbrQuery {

	@Id
    private String id;
	
	@Field("cxr")
	private String cxr;
	
	@Field("tar_no")
	private String tarNo;
	
	@Field("tar_cd")
	private String tarCd;
	
	@Field("rule_no")
	private String ruleNo;
	
	@Field("rule_title")
	private String ruleTitle;
	
	private String source;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getRuleNo() {
		return ruleNo;
	}

	public void setRuleNo(String ruleNo) {
		this.ruleNo = ruleNo;
	}

	public String getRuleTitle() {
		return ruleTitle;
	}

	public void setRuleTitle(String ruleTitle) {
		this.ruleTitle = ruleTitle;
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "FbrQuery [id=" + id + ", cxr=" + cxr + ", tarNo=" + tarNo + ", tarCd=" + tarCd + ", ruleNo=" + ruleNo
				+ ", ruleTitle=" + ruleTitle + ", source=" + source + "]";
	}

}
