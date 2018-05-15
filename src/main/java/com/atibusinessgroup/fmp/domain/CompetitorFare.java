package com.atibusinessgroup.fmp.domain;

import java.util.List;

public class CompetitorFare {

	private String id;
    private String origin;
    private String direction;
    private String destination;
    private String passengerType;
    private String minAge;
    private String maxAge;
    private String global;
    private String fareBasis;
    private String currency;
    private String amount;
    private List<Tax> taxes;
    private String bookingClass;
    private String typeOfJourney;
    private String seasonType;
    private String ticketCode;
    private String ticketDesignator;
    private String cabinClass;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getPassengerType() {
		return passengerType;
	}
	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}
	public String getMinAge() {
		return minAge;
	}
	public void setMinAge(String minAge) {
		this.minAge = minAge;
	}
	public String getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(String maxAge) {
		this.maxAge = maxAge;
	}
	public String getGlobal() {
		return global;
	}
	public void setGlobal(String global) {
		this.global = global;
	}
	public String getFareBasis() {
		return fareBasis;
	}
	public void setFareBasis(String fareBasis) {
		this.fareBasis = fareBasis;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public List<Tax> getTaxes() {
		return taxes;
	}
	public void setTaxes(List<Tax> taxes) {
		this.taxes = taxes;
	}
	public String getBookingClass() {
		return bookingClass;
	}
	public void setBookingClass(String bookingClass) {
		this.bookingClass = bookingClass;
	}
	public String getTypeOfJourney() {
		return typeOfJourney;
	}
	public void setTypeOfJourney(String typeOfJourney) {
		this.typeOfJourney = typeOfJourney;
	}
	public String getSeasonType() {
		return seasonType;
	}
	public void setSeasonType(String seasonType) {
		this.seasonType = seasonType;
	}
	public String getTicketCode() {
		return ticketCode;
	}
	public void setTicketCode(String ticketCode) {
		this.ticketCode = ticketCode;
	}
	public String getTicketDesignator() {
		return ticketDesignator;
	}
	public void setTicketDesignator(String ticketDesignator) {
		this.ticketDesignator = ticketDesignator;
	}
	public String getCabinClass() {
		return cabinClass;
	}
	public void setCabinClass(String cabinClass) {
		this.cabinClass = cabinClass;
	}
    
    
}
