package com.atibusinessgroup.fmp.domain.dto;

import java.security.acl.Owner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class ProsDataFeedsRecord {

	private final String separator = " ";
	private String recordId;
	private String originAirportCode;
	private String destAirportCode;
	private Object effectiveDate;
	private Object discontinueDate;
	private Object saleStartDate;
	private Object saleEndDate;
	private String bookingClass;
	private String originCountry;
	private String fareBasisGroup;
	private String carrier;
	private String fareClassCode;
	private String owrt;
	private String fareSourceCode;
	private double baseFareAmount;
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
	private Object seasonStartDate;
	private Object seasonEndDate;
	private double endFareAmount;
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

	public void setEffectiveDate(Object effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Object getDiscontinueDate() {
		return discontinueDate;
	}

	public void setDiscontinueDate(Object discontinueDate) {
		this.discontinueDate = discontinueDate;
	}

	public Object getSaleStartDate() {
		return saleStartDate;
	}

	public void setSaleStartDate(Object saleStartDate) {
		this.saleStartDate = saleStartDate;
	}

	public Object getSaleEndDate() {
		return saleEndDate;
	}

	public void setSaleEndDate(Object saleEndDate) {
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

	public double getBaseFareAmount() {
		return baseFareAmount;
	}

	public void setBaseFareAmount(double baseFareAmount) {
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

	public void setSeasonStartDate(Object seasonStartDate) {
		this.seasonStartDate = seasonStartDate;
	}

	public Object getSeasonEndDate() {
		return seasonEndDate;
	}

	public void setSeasonEndDate(Object seasonEndDate) {
		this.seasonEndDate = seasonEndDate;
	}

	public double getEndFareAmount() {
		return endFareAmount;
	}

	public void setEndFareAmount(double endFareAmount) {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((advancePurchase == null) ? 0 : advancePurchase.hashCode());
		result = prime * result + ((airportConnection == null) ? 0 : airportConnection.hashCode());
		long temp;
		temp = Double.doubleToLongBits(baseFareAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((bookingClass == null) ? 0 : bookingClass.hashCode());
		result = prime * result + ((carrier == null) ? 0 : carrier.hashCode());
		result = prime * result + ((currencyCode == null) ? 0 : currencyCode.hashCode());
		result = prime * result + ((destAirportCode == null) ? 0 : destAirportCode.hashCode());
		result = prime * result + ((discontinueDate == null) ? 0 : discontinueDate.hashCode());
		result = prime * result + ((effectiveDate == null) ? 0 : effectiveDate.hashCode());
		temp = Double.doubleToLongBits(endFareAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((fareBasisGroup == null) ? 0 : fareBasisGroup.hashCode());
		result = prime * result + ((fareClassCode == null) ? 0 : fareClassCode.hashCode());
		result = prime * result + ((fareSourceCode == null) ? 0 : fareSourceCode.hashCode());
		result = prime * result + ((fareTypeCode == null) ? 0 : fareTypeCode.hashCode());
		result = prime * result + ((flightFrequency == null) ? 0 : flightFrequency.hashCode());
		result = prime * result + ((flightNumber == null) ? 0 : flightNumber.hashCode());
		result = prime * result + ((fpsRecordLocator == null) ? 0 : fpsRecordLocator.hashCode());
		result = prime * result + ((iataFareFlag == null) ? 0 : iataFareFlag.hashCode());
		result = prime * result + ((itineraryType == null) ? 0 : itineraryType.hashCode());
		result = prime * result + ((minStay == null) ? 0 : minStay.hashCode());
		result = prime * result + ((originAirportCode == null) ? 0 : originAirportCode.hashCode());
		result = prime * result + ((originCountry == null) ? 0 : originCountry.hashCode());
		result = prime * result + ((owrt == null) ? 0 : owrt.hashCode());
		result = prime * result + ((pointOfSales == null) ? 0 : pointOfSales.hashCode());
		result = prime * result + ((recordId == null) ? 0 : recordId.hashCode());
		result = prime * result + ((saleEndDate == null) ? 0 : saleEndDate.hashCode());
		result = prime * result + ((saleStartDate == null) ? 0 : saleStartDate.hashCode());
		result = prime * result + ((seasonEndDate == null) ? 0 : seasonEndDate.hashCode());
		result = prime * result + ((seasonStartDate == null) ? 0 : seasonStartDate.hashCode());
		result = prime * result + ((timeOfDays == null) ? 0 : timeOfDays.hashCode());
		result = prime * result + ((useInCalculation == null) ? 0 : useInCalculation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ProsDataFeedsRecord))
			return false;
		ProsDataFeedsRecord other = (ProsDataFeedsRecord) obj;
		if (advancePurchase == null) {
			if (other.advancePurchase != null)
				return false;
		} else if (!advancePurchase.equals(other.advancePurchase))
			return false;
		if (airportConnection == null) {
			if (other.airportConnection != null)
				return false;
		} else if (!airportConnection.equals(other.airportConnection))
			return false;
		if (Double.doubleToLongBits(baseFareAmount) != Double.doubleToLongBits(other.baseFareAmount))
			return false;
		if (bookingClass == null) {
			if (other.bookingClass != null)
				return false;
		} else if (!bookingClass.equals(other.bookingClass))
			return false;
		if (carrier == null) {
			if (other.carrier != null)
				return false;
		} else if (!carrier.equals(other.carrier))
			return false;
		if (currencyCode == null) {
			if (other.currencyCode != null)
				return false;
		} else if (!currencyCode.equals(other.currencyCode))
			return false;
		if (destAirportCode == null) {
			if (other.destAirportCode != null)
				return false;
		} else if (!destAirportCode.equals(other.destAirportCode))
			return false;
		if (discontinueDate == null) {
			if (other.discontinueDate != null)
				return false;
		} else if (!discontinueDate.equals(other.discontinueDate))
			return false;
		if (effectiveDate == null) {
			if (other.effectiveDate != null)
				return false;
		} else if (!effectiveDate.equals(other.effectiveDate))
			return false;
		if (Double.doubleToLongBits(endFareAmount) != Double.doubleToLongBits(other.endFareAmount))
			return false;
		if (fareBasisGroup == null) {
			if (other.fareBasisGroup != null)
				return false;
		} else if (!fareBasisGroup.equals(other.fareBasisGroup))
			return false;
		if (fareClassCode == null) {
			if (other.fareClassCode != null)
				return false;
		} else if (!fareClassCode.equals(other.fareClassCode))
			return false;
		if (fareSourceCode == null) {
			if (other.fareSourceCode != null)
				return false;
		} else if (!fareSourceCode.equals(other.fareSourceCode))
			return false;
		if (fareTypeCode == null) {
			if (other.fareTypeCode != null)
				return false;
		} else if (!fareTypeCode.equals(other.fareTypeCode))
			return false;
		if (flightFrequency == null) {
			if (other.flightFrequency != null)
				return false;
		} else if (!flightFrequency.equals(other.flightFrequency))
			return false;
		if (flightNumber == null) {
			if (other.flightNumber != null)
				return false;
		} else if (!flightNumber.equals(other.flightNumber))
			return false;
		if (fpsRecordLocator == null) {
			if (other.fpsRecordLocator != null)
				return false;
		} else if (!fpsRecordLocator.equals(other.fpsRecordLocator))
			return false;
		if (iataFareFlag == null) {
			if (other.iataFareFlag != null)
				return false;
		} else if (!iataFareFlag.equals(other.iataFareFlag))
			return false;
		if (itineraryType == null) {
			if (other.itineraryType != null)
				return false;
		} else if (!itineraryType.equals(other.itineraryType))
			return false;
		if (minStay == null) {
			if (other.minStay != null)
				return false;
		} else if (!minStay.equals(other.minStay))
			return false;
		if (originAirportCode == null) {
			if (other.originAirportCode != null)
				return false;
		} else if (!originAirportCode.equals(other.originAirportCode))
			return false;
		if (originCountry == null) {
			if (other.originCountry != null)
				return false;
		} else if (!originCountry.equals(other.originCountry))
			return false;
		if (owrt == null) {
			if (other.owrt != null)
				return false;
		} else if (!owrt.equals(other.owrt))
			return false;
		if (pointOfSales == null) {
			if (other.pointOfSales != null)
				return false;
		} else if (!pointOfSales.equals(other.pointOfSales))
			return false;
		if (recordId == null) {
			if (other.recordId != null)
				return false;
		} else if (!recordId.equals(other.recordId))
			return false;
		if (saleEndDate == null) {
			if (other.saleEndDate != null)
				return false;
		} else if (!saleEndDate.equals(other.saleEndDate))
			return false;
		if (saleStartDate == null) {
			if (other.saleStartDate != null)
				return false;
		} else if (!saleStartDate.equals(other.saleStartDate))
			return false;
		if (seasonEndDate == null) {
			if (other.seasonEndDate != null)
				return false;
		} else if (!seasonEndDate.equals(other.seasonEndDate))
			return false;
		if (seasonStartDate == null) {
			if (other.seasonStartDate != null)
				return false;
		} else if (!seasonStartDate.equals(other.seasonStartDate))
			return false;
		if (timeOfDays == null) {
			if (other.timeOfDays != null)
				return false;
		} else if (!timeOfDays.equals(other.timeOfDays))
			return false;
		if (useInCalculation == null) {
			if (other.useInCalculation != null)
				return false;
		} else if (!useInCalculation.equals(other.useInCalculation))
			return false;
		return true;
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
