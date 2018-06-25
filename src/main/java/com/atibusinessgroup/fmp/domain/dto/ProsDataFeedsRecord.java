package com.atibusinessgroup.fmp.domain.dto;

import java.security.acl.Owner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class ProsDataFeedsRecord {

	private final String separator = " ";
	private String recordId;
	private String originAirportCode;
	private String destAirportCode;
	private String effectiveDate;
	private String discontinueDate;
	private String saleStartDate;
	private String saleEndDate;
	private String bookingClass;
	private String originCountry;
	private String fareBasisGroup;
	private String carrier;
	private String fareClassCode;
	private String owrt;
	private String fareSourceCode;
	private String baseFareAmount;
	private String currencyCode;
	private String iataFareFlag;
	private String advancePurchase;
	private String minStay;
	private String pointOfSales;
	private String fareTypeCode;
	private String useInCalculation;
	private String timeOfDays;
	private String flightNumber;
	private String flightFrequency;
	private String seasonStartDate;
	private String seasonEndDate;
	private String endFareAmount;
	private String fpsRecordLocator;
	private String itineraryType;
	private String airportConnection;

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getOriginAirportCode() {
		return originAirportCode;
	}

	public void setOriginAirportCode(String originAirportCode) {
		this.originAirportCode = originAirportCode;
	}

	public String getDestAirportCode() {
		return destAirportCode;
	}

	public void setDestAirportCode(String destAirportCode) {
		this.destAirportCode = destAirportCode;
	}

	public Object getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getDiscontinueDate() {
		return discontinueDate;
	}

	public void setDiscontinueDate(String discontinueDate) {
		this.discontinueDate = discontinueDate;
	}

	public String getSaleStartDate() {
		return saleStartDate;
	}

	public void setSaleStartDate(String saleStartDate) {
		this.saleStartDate = saleStartDate;
	}

	public String getSaleEndDate() {
		return saleEndDate;
	}

	public void setSaleEndDate(String saleEndDate) {
		this.saleEndDate = saleEndDate;
	}

	public String getBookingClass() {
		return bookingClass;
	}

	public void setBookingClass(String bookingClass) {
		this.bookingClass = bookingClass;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public String getFareBasisGroup() {
		return fareBasisGroup;
	}

	public void setFareBasisGroup(String fareBasisGroup) {
		this.fareBasisGroup = fareBasisGroup;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getFareClassCode() {
		return fareClassCode;
	}

	public void setFareClassCode(String fareClassCode) {
		this.fareClassCode = fareClassCode;
	}

	public String getOwrt() {
		return owrt;
	}

	public void setOwrt(String owrt) {
		this.owrt = owrt;
	}

	public String getFareSourceCode() {
		return fareSourceCode;
	}

	public void setFareSourceCode(String fareSourceCode) {
		this.fareSourceCode = fareSourceCode;
	}

	public String getBaseFareAmount() {
		return baseFareAmount;
	}

	public void setBaseFareAmount(String baseFareAmount) {
		this.baseFareAmount = baseFareAmount;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getIataFareFlag() {
		return iataFareFlag;
	}

	public void setIataFareFlag(String iataFareFlag) {
		this.iataFareFlag = iataFareFlag;
	}

	public String getAdvancePurchase() {
		return advancePurchase;
	}

	public void setAdvancePurchase(String advancePurchase) {
		this.advancePurchase = advancePurchase;
	}

	public String getMinStay() {
		return minStay;
	}

	public void setMinStay(String minStay) {
		this.minStay = minStay;
	}

	public String getPointOfSales() {
		return pointOfSales;
	}

	public void setPointOfSales(String pointOfSales) {
		this.pointOfSales = pointOfSales;
	}

	public String getFareTypeCode() {
		return fareTypeCode;
	}

	public void setFareTypeCode(String fareTypeCode) {
		this.fareTypeCode = fareTypeCode;
	}

	public String getUseInCalculation() {
		return useInCalculation;
	}

	public void setUseInCalculation(String useInCalculation) {
		this.useInCalculation = useInCalculation;
	}

	public String getTimeOfDays() {
		return timeOfDays;
	}

	public void setTimeOfDays(String timeOfDays) {
		this.timeOfDays = timeOfDays;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getFlightFrequency() {
		return flightFrequency;
	}

	public void setFlightFrequency(String flightFrequency) {
		this.flightFrequency = flightFrequency;
	}

	public Object getSeasonStartDate() {
		return seasonStartDate;
	}

	public void setSeasonStartDate(String seasonStartDate) {
		this.seasonStartDate = seasonStartDate;
	}

	public String getSeasonEndDate() {
		return seasonEndDate;
	}

	public void setSeasonEndDate(String seasonEndDate) {
		this.seasonEndDate = seasonEndDate;
	}

	public String getEndFareAmount() {
		return endFareAmount;
	}

	public void setEndFareAmount(String endFareAmount) {
		this.endFareAmount = endFareAmount;
	}

	public String getFpsRecordLocator() {
		return fpsRecordLocator;
	}

	public void setFpsRecordLocator(String fpsRecordLocator) {
		this.fpsRecordLocator = fpsRecordLocator;
	}

	public String getItineraryType() {
		return itineraryType;
	}

	public void setItineraryType(String itineraryType) {
		this.itineraryType = itineraryType;
	}

	public String getAirportConnection() {
		return airportConnection;
	}

	public void setAirportConnection(String airportConnection) {
		this.airportConnection = airportConnection;
	}

	@Override
	public String toString() {
		return "ProsDataFeedsRecord [recordId=" + recordId + ", originAirportCode=" + originAirportCode
				+ ", destAirportCode=" + destAirportCode + ", effectiveDate=" + effectiveDate + ", discontinueDate="
				+ discontinueDate + ", saleStartDate=" + saleStartDate + ", saleEndDate=" + saleEndDate
				+ ", bookingClass=" + bookingClass + ", originCountry=" + originCountry + ", fareBasisGroup="
				+ fareBasisGroup + ", carrier=" + carrier + ", fareClassCode=" + fareClassCode + ", owrt=" + owrt
				+ ", fareSourceCode=" + fareSourceCode + ", baseFareAmount=" + baseFareAmount + ", currencyCode="
				+ currencyCode + ", iataFareFlag=" + iataFareFlag + ", advancePurchase=" + advancePurchase
				+ ", minStay=" + minStay + ", pointOfSales=" + pointOfSales + ", fareTypeCode=" + fareTypeCode
				+ ", useInCalculation=" + useInCalculation + ", timeOfDays=" + timeOfDays + ", flightNumber="
				+ flightNumber + ", flightFrequency=" + flightFrequency + ", seasonStartDate=" + seasonStartDate
				+ ", seasonEndDate=" + seasonEndDate + ", endFareAmount=" + endFareAmount + ", fpsRecordLocator="
				+ fpsRecordLocator + ", itineraryType=" + itineraryType + ", airportConnection=" + airportConnection
				+ "]";
	}

	public String build() {
		return recordId + separator + originAirportCode + separator + destAirportCode + separator + effectiveDate
				+ separator + discontinueDate + separator + saleStartDate + separator + saleEndDate + separator
				+ bookingClass + separator + originCountry + separator + fareBasisGroup + separator + carrier
				+ separator + fareClassCode + separator + owrt + separator + fareSourceCode + separator + baseFareAmount
				+ separator + currencyCode + separator + iataFareFlag + separator + advancePurchase + separator
				+ minStay + separator + pointOfSales + separator + fareTypeCode + separator + useInCalculation
				+ separator + timeOfDays + separator + flightNumber + separator + flightFrequency + separator
				+ seasonStartDate + separator + seasonEndDate + separator + endFareAmount + separator + fpsRecordLocator
				+ separator + itineraryType + separator + airportConnection;
	}
}
