package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class AtpcoRecord2GroupByRuleNoCxrTarNo {
	
	@Field("rule_tar_no")
    private String ruleTariffNo;
	
	@Field("cxr_code")
    private String carrierCode;
	
	@Field("rule_no")
    private String ruleNo;
    
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
	
    
	
	
	
}
