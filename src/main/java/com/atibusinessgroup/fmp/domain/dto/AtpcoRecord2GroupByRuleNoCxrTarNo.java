package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class AtpcoRecord2GroupByRuleNoCxrTarNo {
	
	@Field("rule_tar_no")
    private String ruleTariffNo;
	
	@Field("cxr_code")
    private String carrierCode;
	
	@Field("rule_no")
    private String ruleNo;
	
	@Field("type")
    private String type;
	
	public String getRuleTariffNo() {
		return ruleTariffNo;
	}
	public void setRuleTariffNo(String ruleTariffNo) {
		this.ruleTariffNo = ruleTariffNo;
	}
	public String getCarrierCode() {
		return carrierCode;
	}
	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}
	public String getRuleNo() {
		return ruleNo;
	}
	public void setRuleNo(String ruleNo) {
		this.ruleNo = ruleNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "AtpcoRecord2GroupByRuleNoCxrTarNo [ruleTariffNo=" + ruleTariffNo + ", carrierCode=" + carrierCode
				+ ", ruleNo=" + ruleNo + ", type=" + type + "]";
	}
    
	
	
	
}
