package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class AtpcoRecord2GroupByRuleNoCxrTarNo {
	
	@Field("tarNo")
    private String tarNo;
	
	@Field("cxr")
    private String cxr;
	
	@Field("ruleNo")
    private String ruleNo;
	
	@Field("tarCd")
    private String tarCd;
	
	@Field("total")
    private String total;

	public String getTarNo() {
		return tarNo;
	}

	public void setTarNo(String tarNo) {
		this.tarNo = tarNo;
	}

	public String getCxr() {
		return cxr;
	}

	public void setCxr(String cxr) {
		this.cxr = cxr;
	}

	public String getRuleNo() {
		return ruleNo;
	}

	public void setRuleNo(String ruleNo) {
		this.ruleNo = ruleNo;
	}

	public String getTarCd() {
		return tarCd;
	}

	public void setTarCd(String tarCd) {
		this.tarCd = tarCd;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "AtpcoRecord2GroupByRuleNoCxrTarNo [tarNo=" + tarNo + ", cxr=" + cxr + ", ruleNo=" + ruleNo + ", tarCd="
				+ tarCd + ", total=" + total + "]";
	}
	
	
	
	
}
