package com.atibusinessgroup.fmp.domain.dto;

public class Rec8Param {
	
	private String cxr;
	private String ruleTarNo;
	private String ruleNo;
	private String accountCode;
	private boolean includeDisc;
	
	private int page;
	private int size;
	public String getCxr() {
		return cxr;
	}
	public void setCxr(String cxr) {
		this.cxr = cxr;
	}
	public String getRuleTarNo() {
		return ruleTarNo;
	}
	public void setRuleTarNo(String ruleTarNo) {
		this.ruleTarNo = ruleTarNo;
	}
	public String getRuleNo() {
		return ruleNo;
	}
	public void setRuleNo(String ruleNo) {
		this.ruleNo = ruleNo;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public boolean isIncludeDisc() {
		return includeDisc;
	}
	public void setIncludeDisc(boolean includeDisc) {
		this.includeDisc = includeDisc;
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
	@Override
	public String toString() {
		return "Rec8Param [cxr=" + cxr + ", ruleTarNo=" + ruleTarNo + ", ruleNo=" + ruleNo + ", accountCode="
				+ accountCode + ", includeDisc=" + includeDisc + ", page=" + page + ", size=" + size + "]";
	}
	
	

}
