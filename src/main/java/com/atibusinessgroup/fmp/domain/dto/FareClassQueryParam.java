package com.atibusinessgroup.fmp.domain.dto;

public class FareClassQueryParam {

	private String cxr;
	private String ruleNo;
	private String fareClass;
	private String tarNo;
	private String psgrType;
	private String fareType;
	private String bookingClass;
	
	private int page;
	private int size;
	
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
	public String getFareClass() {
		return fareClass;
	}
	public void setFareClass(String fareClass) {
		this.fareClass = fareClass;
	}
	public String getTarNo() {
		return tarNo;
	}
	public void setTarNo(String tarNo) {
		this.tarNo = tarNo;
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
		return "FareClassQueryParam [cxr=" + cxr + ", ruleNo=" + ruleNo + ", fareClass=" + fareClass + ", tarNo="
				+ tarNo + ", psgrType=" + psgrType + ", fareType=" + fareType + ", bookingClass=" + bookingClass
				+ ", page=" + page + ", size=" + size + "]";
	}
	
}
