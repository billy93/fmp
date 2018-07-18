package com.atibusinessgroup.fmp.domain.dto;

import java.util.Date;

public class FbrQueryParam {

	private String ruleTittle;
	private String carrier;
	private String source;
	private String publicPrivate;
	private String fbrTariff;
	private String fbrRule;
	private String bookingClass;
	private String cat25FareBasis;
	private String tourCode;
	private String accountCode;
	private String tktDesignator;
	private String paxAge;
	private String accountCodeBlank;
	private String tktDesignatorBlank;
	private boolean appendResults;
	private String origin;
	private String destination;
	private Date effectiveDateFrom;
	private Date effectiveDateTo;
	private String effectiveDateOption;
	private String paxType;
	private boolean biDirectional;
	private Date saleDateFrom;
	private Date saleDateTo;
	private String saleDateOption;
	private Date travelDateFrom;
	private Date travelDateTo;
	private String travelDateOption;
	private String cabin;
	private String fareBasis;
	private String fareType;
	private String globalIndicator;
	private String owrt;
	
	private int page;
	private int size;
	private int lastIndex;
	
	public FbrQueryParam() {
		
	}
	
	public String getRuleTittle() {
		return ruleTittle;
	}
	public void setRuleTittle(String ruleTittle) {
		this.ruleTittle = ruleTittle;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getPublicPrivate() {
		return publicPrivate;
	}
	public void setPublicPrivate(String publicPrivate) {
		this.publicPrivate = publicPrivate;
	}
	public String getFbrTariff() {
		return fbrTariff;
	}
	public void setFbrTariff(String fbrTariff) {
		this.fbrTariff = fbrTariff;
	}
	public String getFbrRule() {
		return fbrRule;
	}
	public void setFbrRule(String fbrRule) {
		this.fbrRule = fbrRule;
	}
	public String getBookingClass() {
		return bookingClass;
	}
	public void setBookingClass(String bookingClass) {
		this.bookingClass = bookingClass;
	}
	public String getCat25FareBasis() {
		return cat25FareBasis;
	}
	public void setCat25FareBasis(String cat25FareBasis) {
		this.cat25FareBasis = cat25FareBasis;
	}
	public String getTourCode() {
		return tourCode;
	}
	public void setTourCode(String tourCode) {
		this.tourCode = tourCode;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public String getTktDesignator() {
		return tktDesignator;
	}
	public void setTktDesignator(String tktDesignator) {
		this.tktDesignator = tktDesignator;
	}
	public String getPaxAge() {
		return paxAge;
	}
	public void setPaxAge(String paxAge) {
		this.paxAge = paxAge;
	}
	public String getAccountCodeBlank() {
		return accountCodeBlank;
	}
	public void setAccountCodeBlank(String accountCodeBlank) {
		this.accountCodeBlank = accountCodeBlank;
	}
	public String getTktDesignatorBlank() {
		return tktDesignatorBlank;
	}
	public void setTktDesignatorBlank(String tktDesignatorBlank) {
		this.tktDesignatorBlank = tktDesignatorBlank;
	}
	public boolean isAppendResults() {
		return appendResults;
	}
	public void setAppendResults(boolean appendResults) {
		this.appendResults = appendResults;
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
	public String getEffectiveDateOption() {
		return effectiveDateOption;
	}
	public void setEffectiveDateOption(String effectiveDateOption) {
		this.effectiveDateOption = effectiveDateOption;
	}
	public String getPaxType() {
		return paxType;
	}
	public void setPaxType(String paxType) {
		this.paxType = paxType;
	}
	public boolean isBiDirectional() {
		return biDirectional;
	}
	public void setBiDirectional(boolean biDirectional) {
		this.biDirectional = biDirectional;
	}
	public Date getSaleDateFrom() {
		return saleDateFrom;
	}
	public void setSaleDateFrom(Date saleDateFrom) {
		this.saleDateFrom = saleDateFrom;
	}
	public Date getSaleDateTo() {
		return saleDateTo;
	}
	public void setSaleDateTo(Date saleDateTo) {
		this.saleDateTo = saleDateTo;
	}
	public String getSaleDateOption() {
		return saleDateOption;
	}
	public void setSaleDateOption(String saleDateOption) {
		this.saleDateOption = saleDateOption;
	}
	public Date getTravelDateFrom() {
		return travelDateFrom;
	}
	public void setTravelDateFrom(Date travelDateFrom) {
		this.travelDateFrom = travelDateFrom;
	}
	public Date getTravelDateTo() {
		return travelDateTo;
	}
	public void setTravelDateTo(Date travelDateTo) {
		this.travelDateTo = travelDateTo;
	}
	public String getTravelDateOption() {
		return travelDateOption;
	}
	public void setTravelDateOption(String travelDateOption) {
		this.travelDateOption = travelDateOption;
	}
	public String getCabin() {
		return cabin;
	}
	public void setCabin(String cabin) {
		this.cabin = cabin;
	}
	public String getFareBasis() {
		return fareBasis;
	}
	public void setFareBasis(String fareBasis) {
		this.fareBasis = fareBasis;
	}
	public String getFareType() {
		return fareType;
	}
	public void setFareType(String fareType) {
		this.fareType = fareType;
	}
	public String getGlobalIndicator() {
		return globalIndicator;
	}
	public void setGlobalIndicator(String globalIndicator) {
		this.globalIndicator = globalIndicator;
	}
	public String getOwrt() {
		return owrt;
	}
	public void setOwrt(String owrt) {
		this.owrt = owrt;
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
	public int getLastIndex() {
		return lastIndex;
	}
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}
	
	@Override
	public String toString() {
		return "FbrQueryParam [ruleTittle=" + ruleTittle + ", carrier=" + carrier + ", source=" + source
				+ ", publicPrivate=" + publicPrivate + ", fbrTariff=" + fbrTariff + ", fbrRule=" + fbrRule
				+ ", bookingClass=" + bookingClass + ", cat25FareBasis=" + cat25FareBasis + ", tourCode=" + tourCode
				+ ", accountCode=" + accountCode + ", tktDesignator=" + tktDesignator + ", paxAge=" + paxAge
				+ ", accountCodeBlank=" + accountCodeBlank + ", tktDesignatorBlank=" + tktDesignatorBlank
				+ ", appendResults=" + appendResults + ", origin=" + origin + ", destination=" + destination
				+ ", effectiveDateFrom=" + effectiveDateFrom + ", effectiveDateTo=" + effectiveDateTo
				+ ", effectiveDateOption=" + effectiveDateOption + ", paxType=" + paxType + ", biDirectional="
				+ biDirectional + ", saleDateFrom=" + saleDateFrom + ", saleDateTo=" + saleDateTo + ", saleDateOption="
				+ saleDateOption + ", travelDateFrom=" + travelDateFrom + ", travelDateTo=" + travelDateTo
				+ ", travelDateOption=" + travelDateOption + ", cabin=" + cabin + ", fareBasis=" + fareBasis
				+ ", fareType=" + fareType + ", globalIndicator=" + globalIndicator + ", owrt=" + owrt + ", page="
				+ page + ", size=" + size + ", lastIndex=" + lastIndex + "]";
	}
	
	
}
