package com.atibusinessgroup.fmp.domain.dto;

import java.time.ZonedDateTime;

public class InternetQueryParam {
	
	private String cxr;
	private String website;
	private String origin;
	private String destination;
	private ZonedDateTime departDateFrom;
	private ZonedDateTime departDateTo;
	private int departDOW;
	private ZonedDateTime captureDateFrom;
	private ZonedDateTime captureDateTo;
	private String marketGroup;
	private boolean biDirectional;
	private boolean appendResults;
	private boolean queryByGroup;
	private boolean myMarketOnly;
	
	private int page;
	private int size;
	
	public String getCxr() {
		return cxr;
	}
	public void setCxr(String cxr) {
		this.cxr = cxr;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public ZonedDateTime getDepartDateFrom() {
		return departDateFrom;
	}
	public void setDepartDateFrom(ZonedDateTime departDateFrom) {
		this.departDateFrom = departDateFrom;
	}
	public ZonedDateTime getDepartDateTo() {
		return departDateTo;
	}
	public void setDepartDateTo(ZonedDateTime departDateTo) {
		this.departDateTo = departDateTo;
	}
	public int getDepartDOW() {
		return departDOW;
	}
	public void setDepartDOW(int departDOW) {
		this.departDOW = departDOW;
	}
	public ZonedDateTime getCaptureDateFrom() {
		return captureDateFrom;
	}
	public void setCaptureDateFrom(ZonedDateTime captureDateFrom) {
		this.captureDateFrom = captureDateFrom;
	}
	public ZonedDateTime getCaptureDateTo() {
		return captureDateTo;
	}
	public void setCaptureDateTo(ZonedDateTime captureDateTo) {
		this.captureDateTo = captureDateTo;
	}
	public String getMarketGroup() {
		return marketGroup;
	}
	public void setMarketGroup(String marketGroup) {
		this.marketGroup = marketGroup;
	}
	public boolean isBiDirectional() {
		return biDirectional;
	}
	public void setBiDirectional(boolean biDirectional) {
		this.biDirectional = biDirectional;
	}
	public boolean isAppendResults() {
		return appendResults;
	}
	public void setAppendResults(boolean appendResults) {
		this.appendResults = appendResults;
	}
	public boolean isQueryByGroup() {
		return queryByGroup;
	}
	public void setQueryByGroup(boolean queryByGroup) {
		this.queryByGroup = queryByGroup;
	}
	public boolean isMyMarketOnly() {
		return myMarketOnly;
	}
	public void setMyMarketOnly(boolean myMarketOnly) {
		this.myMarketOnly = myMarketOnly;
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
		return "InternetQueryParam [cxr=" + cxr + ", website=" + website + ", origin=" + origin + ", destination="
				+ destination + ", departDateFrom=" + departDateFrom + ", departDateTo=" + departDateTo + ", departDOW="
				+ departDOW + ", captureDateFrom=" + captureDateFrom + ", captureDateTo=" + captureDateTo
				+ ", marketGroup=" + marketGroup + ", biDirectional=" + biDirectional + ", appendResults="
				+ appendResults + ", queryByGroup=" + queryByGroup + ", myMarketOnly=" + myMarketOnly + ", page=" + page
				+ ", size=" + size + "]";
	}
	

}
