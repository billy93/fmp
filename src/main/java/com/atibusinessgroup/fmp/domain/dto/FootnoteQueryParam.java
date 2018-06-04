package com.atibusinessgroup.fmp.domain.dto;

public class FootnoteQueryParam {
	
	private String cxr;
	private String tarNo;
	private String ftnt;
	private String catNo;
	private Object saleDateFrom;
	private Object saleDateTo;
	private Object travelDateFrom;
	private Object travelDateTo;
	private Object completedDateFrom;
	private String travelOpt;
	
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
	public void setSaleDateFrom(Object saleDateFrom) {
		this.saleDateFrom = saleDateFrom;
	}
	public Object getSaleDateTo() {
		return saleDateTo;
	}
	public void setSaleDateTo(Object saleDateTo) {
		this.saleDateTo = saleDateTo;
	}
	public Object getTravelDateFrom() {
		return travelDateFrom;
	}
	public void setTravelDateFrom(Object travelDateFrom) {
		this.travelDateFrom = travelDateFrom;
	}
	public Object getTravelDateTo() {
		return travelDateTo;
	}
	public void setTravelDateTo(Object travelDateTo) {
		this.travelDateTo = travelDateTo;
	}
	public Object getCompletedDateFrom() {
		return completedDateFrom;
	}
	public void setCompletedDateFrom(Object completedDateFrom) {
		this.completedDateFrom = completedDateFrom;
	}
	public String getTravelOpt() {
		return travelOpt;
	}
	public void setTravelOpt(String travelOpt) {
		this.travelOpt = travelOpt;
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
