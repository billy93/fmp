package com.atibusinessgroup.fmp.domain.dto;

import java.util.Date;

public class RuleQueryParam {
	
	private String cxr;
	private String ruleTarNo;
	private String ruleNo;
	private String type;
	private String src;
	private String category;
	private String catNo;
	private boolean chart1;
	private boolean chart2;
	
	private boolean includeDisc;
	
	private int page;
	private int size;
	
	private Date effectiveDateFrom;	

	public boolean isChart1() {
		return chart1;
	}

	public void setChart1(boolean chart1) {
		this.chart1 = chart1;
	}

	public boolean isChart2() {
		return chart2;
	}

	public void setChart2(boolean chart2) {
		this.chart2 = chart2;
	}

	public Date getEffectiveDateFrom() {
		return effectiveDateFrom;
	}

	public void setEffectiveDateFrom(Date effectiveDateFrom) {
		this.effectiveDateFrom = effectiveDateFrom;
	}

	public RuleQueryParam() {
		
	}

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

	public String getCatNo() {
		return catNo;
	}

	public void setCatNo(String catNo) {
		this.catNo = catNo;
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
		return "RuleQueryParam [cxr=" + cxr + ", ruleTarNo=" + ruleTarNo + ", ruleNo=" + ruleNo + ", type=" + type
				+ ", src=" + src + ", category=" + category + ", catNo=" + catNo + ", includeDisc=" + includeDisc
				+ ", page=" + page + ", size=" + size + "]";
	}
	
	

	
}
