package com.atibusinessgroup.fmp.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.bson.types.Decimal128;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "voltras_fare")
public class InternetQuery implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Field("website")
	private String website;
	
	@Field("capture_date_time")
	private Object captureDateTime;
	
	@Field("ap_days")
	private int apDays;
	
	@Field("cxr")
	private String cxr;
	
	@Field("airline")
	private String airline;
	
	@Field("trip_type")
	private String tripType;
	
	@Field("origin")
	private String origin;
	
	@Field("origin_name")
	private String originName;
	
	@Field("destination")
	private String destination;
	
	@Field("destination_name")
	private String destinationName;
	
	@Field("fare_basis")
	private String fareBasis;
	
	@Field("depart_date_time")
	private Object departDateTime;
	
	@Field("return_date_time")
	private Object returnDateTime;
	
	@Field("flight_number")
	private String flightNumber;
	
	@Field("base_amt")
	private Decimal128 baseAmt;
	
	@Field("taxes")
	private Decimal128 taxes;
	
	@Field("aif")
	private Decimal128 aif;
	
	@Field("currency")
	private String currency;
	
	@Field("ref_amt")
	private String refAmt;
	
	@Field("depart_dow")
	private int departDOW;
	
	private BigDecimal baseAmtBD;
	private BigDecimal taxesBD;
	private BigDecimal aifBD;

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Object getCaptureDateTime() {
		return captureDateTime;
	}

	public void setCaptureDateTime(Object captureDateTime) {
		this.captureDateTime = captureDateTime;
	}

	public int getApDays() {
		return apDays;
	}

	public void setApDays(int apDays) {
		this.apDays = apDays;
	}

	public String getCxr() {
		return cxr;
	}

	public void setCxr(String cxr) {
		this.cxr = cxr;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getTripType() {
		return tripType;
	}

	public void setTripType(String tripType) {
		this.tripType = tripType;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getFareBasis() {
		return fareBasis;
	}

	public void setFareBasis(String fareBasis) {
		this.fareBasis = fareBasis;
	}

	public Object getDepartDateTime() {
		return departDateTime;
	}

	public void setDepartDateTime(Object departDateTime) {
		this.departDateTime = departDateTime;
	}

	public Object getReturnDateTime() {
		return returnDateTime;
	}

	public void setReturnDateTime(Object returnDateTime) {
		this.returnDateTime = returnDateTime;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Decimal128 getBaseAmt() {
		return baseAmt;
	}

	public void setBaseAmt(Decimal128 baseAmt) {
		this.baseAmt = baseAmt;
	}

	public Decimal128 getTaxes() {
		return taxes;
	}

	public void setTaxes(Decimal128 taxes) {
		this.taxes = taxes;
	}

	public Decimal128 getAif() {
		return aif;
	}

	public void setAif(Decimal128 aif) {
		this.aif = aif;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getRefAmt() {
		return refAmt;
	}

	public void setRefAmt(String refAmt) {
		this.refAmt = refAmt;
	}

	public int getDepartDOW() {
		return departDOW;
	}

	public void setDepartDOW(int departDOW) {
		this.departDOW = departDOW;
	}
	
	public BigDecimal getBaseAmtBD() {
		return baseAmtBD;
	}

	public void setBaseAmtBD(BigDecimal baseAmtBD) {
		this.baseAmtBD = baseAmtBD;
	}
	
	public BigDecimal getTaxesBD() {
		return taxesBD;
	}

	public void setTaxesBD(BigDecimal taxesBD) {
		this.taxesBD = taxesBD;
	}

	public BigDecimal getAifBD() {
		return aifBD;
	}

	public void setAifBD(BigDecimal aifBD) {
		this.aifBD = aifBD;
	}

	@Override
	public String toString() {
		return "InternetQuery [website=" + website + ", captureDateTime=" + captureDateTime + ", apDays=" + apDays
				+ ", cxr=" + cxr + ", airline=" + airline + ", tripType=" + tripType + ", origin=" + origin
				+ ", originName=" + originName + ", destination=" + destination + ", destinationName=" + destinationName
				+ ", fareBasis=" + fareBasis + ", departDateTime=" + departDateTime + ", returnDateTime="
				+ returnDateTime + ", flightNumber=" + flightNumber + ", baseAmt=" + baseAmt + ", taxes=" + taxes
				+ ", aif=" + aif + ", currency=" + currency + ", refAmt=" + refAmt + ", departDOW=" + departDOW
				+ ", baseAmtBD=" + baseAmtBD + ", taxesBD=" + taxesBD + ", aifBD=" + aifBD + "]";
	}


}
