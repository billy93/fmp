package com.atibusinessgroup.fmp.domain.dto;

import java.util.Date;

public class RoutingQueryParam {
	
	private String src;
	private String tarNo;
	private String carrier;
	private String routingNo;
	private String entryPoint;
	private String exitPoint;
	private Date effectiveDateFrom;
	private Date effectiveDateTo;
	private boolean showRoutesMaps;
	private int page;
	private int size;
	
	public RoutingQueryParam() {
		// TODO Auto-generated constructor stub
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getTarNo() {
		return tarNo;
	}

	public void setTarNo(String tarNo) {
		this.tarNo = tarNo;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getRoutingNo() {
		return routingNo;
	}

	public void setRoutingNo(String routingNo) {
		this.routingNo = routingNo;
	}

	public String getEntryPoint() {
		return entryPoint;
	}

	public void setEntryPoint(String entryPoint) {
		this.entryPoint = entryPoint;
	}

	public String getExitPoint() {
		return exitPoint;
	}

	public void setExitPoint(String exitPoint) {
		this.exitPoint = exitPoint;
	}

	public Date getEffectiveDateFrom() {
		return effectiveDateFrom;
	}

	public void setEffectiveDateFrom(Date effectiveDateFrom) {
		this.effectiveDateFrom = effectiveDateFrom;
	}

	public Date getEffectiveDateTo() {
		return effectiveDateTo;
	}

	public void setEffectiveDateTo(Date effectiveDateTo) {
		this.effectiveDateTo = effectiveDateTo;
	}

	public boolean isShowRoutesMaps() {
		return showRoutesMaps;
	}

	public void setShowRoutesMaps(boolean showRoutesMaps) {
		this.showRoutesMaps = showRoutesMaps;
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
		return "RoutingQueryParam [src=" + src + ", tarNo=" + tarNo + ", carrier=" + carrier + ", routingNo="
				+ routingNo + ", entryPoint=" + entryPoint + ", exitPoint=" + exitPoint + ", effectiveDateFrom="
				+ effectiveDateFrom + ", effectiveDateTo=" + effectiveDateTo + ", showRoutesMaps=" + showRoutesMaps
				+ ", page=" + page + ", size=" + size + "]";
	}

	
	
}
