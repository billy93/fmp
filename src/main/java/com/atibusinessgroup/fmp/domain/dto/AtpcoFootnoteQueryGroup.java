package com.atibusinessgroup.fmp.domain.dto;

public class AtpcoFootnoteQueryGroup {
	
	private Integer total;
	private String cxr;
	private String tarNo;
	private String tarCd;
	private String ftnt;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
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
	public String getFtnt() {
		return ftnt;
	}
	public void setFtnt(String ftnt) {
		this.ftnt = ftnt;
	}
	@Override
	public String toString() {
		return "AtpcoFootnoteQueryGroup [total=" + total + ", cxr=" + cxr + ", tarNo=" + tarNo + ", tarCd=" + tarCd
				+ ", ftnt=" + ftnt + "]";
	}
	
	
	
	
	
	
}
