package com.atibusinessgroup.fmp.domain.dto;

public class RuleQueryParam {
	
	private String cxr;
	private String tarNo;
	private String ruleNo;
	private String type;
	private String src;
	private String category;
	
	private int page;
	private int size;
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
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	
	
	
}
