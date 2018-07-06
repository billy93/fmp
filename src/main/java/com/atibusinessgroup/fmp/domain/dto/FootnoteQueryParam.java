package com.atibusinessgroup.fmp.domain.dto;

import java.util.Date;

public class FootnoteQueryParam {
	
	private String cxr;
	private String tarNo;
	private String ftnt;
	private String catNo;
	private Date saleDateFrom;
	private Date saleDateTo;
	private String saleDateType;
	private Date travelDateFrom;
	private Date travelDateTo;
	private String travelDateType;
	private Date completedDateFrom;
	private String travelOpt;
	private boolean includeDiscDate;
	
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
	public Object getSaleDateFrom() {
		return saleDateFrom;
	}
	public void setSaleDateFrom(Date saleDateFrom) {
		this.saleDateFrom = saleDateFrom;
	}
	public Object getSaleDateTo() {
		return saleDateTo;
	}
	public void setSaleDateTo(Date saleDateTo) {
		this.saleDateTo = saleDateTo;
	}
	public String getSaleDateType() {
		return saleDateType;
	}
	public void setSaleDateType(String saleDateType) {
		this.saleDateType = saleDateType;
	}
	public Object getTravelDateFrom() {
		return travelDateFrom;
	}
	public void setTravelDateFrom(Date travelDateFrom) {
		this.travelDateFrom = travelDateFrom;
	}
	public Object getTravelDateTo() {
		return travelDateTo;
	}
	public void setTravelDateTo(Date travelDateTo) {
		this.travelDateTo = travelDateTo;
	}
	public String getTravelDateType() {
		return travelDateType;
	}
	public void setTravelDateType(String travelDateType) {
		this.travelDateType = travelDateType;
	}
	public Object getCompletedDateFrom() {
		return completedDateFrom;
	}
	public void setCompletedDateFrom(Date completedDateFrom) {
		this.completedDateFrom = completedDateFrom;
	}
	public String getTravelOpt() {
		return travelOpt;
	}
	public void setTravelOpt(String travelOpt) {
		this.travelOpt = travelOpt;
	}
	public boolean isIncludeDiscDate() {
		return includeDiscDate;
	}
	public void setIncludeDiscDate(boolean includeDiscDate) {
		this.includeDiscDate = includeDiscDate;
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
		return "FootnoteQueryParam [cxr=" + cxr + ", tarNo=" + tarNo + ", ftnt=" + ftnt + ", catNo=" + catNo
				+ ", saleDateFrom=" + saleDateFrom + ", saleDateTo=" + saleDateTo + ", saleDateType=" + saleDateType
				+ ", travelDateFrom=" + travelDateFrom + ", travelDateTo=" + travelDateTo + ", travelDateType="
				+ travelDateType + ", completedDateFrom=" + completedDateFrom + ", travelOpt=" + travelOpt
				+ ", includeDiscDate=" + includeDiscDate + ", page=" + page + ", size=" + size + "]";
	}
	
	
	
	
}
