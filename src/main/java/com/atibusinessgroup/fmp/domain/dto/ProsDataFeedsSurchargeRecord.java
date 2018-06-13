package com.atibusinessgroup.fmp.domain.dto;

public class ProsDataFeedsSurchargeRecord {

	private final String separator = " ";

	private String recordId;
	private String recordLocator;
	private Object departureStartDate;
	private Object departureEndDate;
	private Object saleStartDate;
	private Object saleEndDate;
	private String timeOfDay;
	private String flightFrequency;
	private String surchargeType;
	private String equipmentCode;
	private String surchargeValue;
	private String localCurrencyCode;
	private String surchargeAppl;
	private String flightNumber;

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getRecordLocator() {
		return recordLocator;
	}

	public void setRecordLocator(String recordLocator) {
		this.recordLocator = recordLocator;
	}

	public Object getDepartureStartDate() {
		return departureStartDate;
	}

	public void setDepartureStartDate(Object departureStartDate) {
		this.departureStartDate = departureStartDate;
	}

	public Object getDepartureEndDate() {
		return departureEndDate;
	}

	public void setDepartureEndDate(Object departureEndDate) {
		this.departureEndDate = departureEndDate;
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

	public String getTimeOfDay() {
		return timeOfDay;
	}

	public void setTimeOfDay(String timeOfDay) {
		this.timeOfDay = timeOfDay;
	}

	public String getFlightFrequency() {
		return flightFrequency;
	}

	public void setFlightFrequency(String flightFrequency) {
		this.flightFrequency = flightFrequency;
	}

	public String getSurchargeType() {
		return surchargeType;
	}

	public void setSurchargeType(String surchargeType) {
		this.surchargeType = surchargeType;
	}

	public String getEquipmentCode() {
		return equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	public String getSurchargeValue() {
		return surchargeValue;
	}

	public void setSurchargeValue(String surchargeValue) {
		this.surchargeValue = surchargeValue;
	}

	public String getLocalCurrencyCode() {
		return localCurrencyCode;
	}

	public void setLocalCurrencyCode(String localCurrencyCode) {
		this.localCurrencyCode = localCurrencyCode;
	}

	public String getSurchargeAppl() {
		return surchargeAppl;
	}

	public void setSurchargeAppl(String surchargeAppl) {
		this.surchargeAppl = surchargeAppl;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getSeparator() {
		return separator;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((departureEndDate == null) ? 0 : departureEndDate.hashCode());
		result = prime * result + ((departureStartDate == null) ? 0 : departureStartDate.hashCode());
		result = prime * result + ((equipmentCode == null) ? 0 : equipmentCode.hashCode());
		result = prime * result + ((flightFrequency == null) ? 0 : flightFrequency.hashCode());
		result = prime * result + ((flightNumber == null) ? 0 : flightNumber.hashCode());
		result = prime * result + ((localCurrencyCode == null) ? 0 : localCurrencyCode.hashCode());
		result = prime * result + ((recordId == null) ? 0 : recordId.hashCode());
		result = prime * result + ((recordLocator == null) ? 0 : recordLocator.hashCode());
		result = prime * result + ((saleEndDate == null) ? 0 : saleEndDate.hashCode());
		result = prime * result + ((saleStartDate == null) ? 0 : saleStartDate.hashCode());
		result = prime * result + ((separator == null) ? 0 : separator.hashCode());
		result = prime * result + ((surchargeAppl == null) ? 0 : surchargeAppl.hashCode());
		result = prime * result + ((surchargeType == null) ? 0 : surchargeType.hashCode());
		result = prime * result + ((surchargeValue == null) ? 0 : surchargeValue.hashCode());
		result = prime * result + ((timeOfDay == null) ? 0 : timeOfDay.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ProsDataFeedsSurchargeRecord))
			return false;
		ProsDataFeedsSurchargeRecord other = (ProsDataFeedsSurchargeRecord) obj;
		if (departureEndDate == null) {
			if (other.departureEndDate != null)
				return false;
		} else if (!departureEndDate.equals(other.departureEndDate))
			return false;
		if (departureStartDate == null) {
			if (other.departureStartDate != null)
				return false;
		} else if (!departureStartDate.equals(other.departureStartDate))
			return false;
		if (equipmentCode == null) {
			if (other.equipmentCode != null)
				return false;
		} else if (!equipmentCode.equals(other.equipmentCode))
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
		if (localCurrencyCode == null) {
			if (other.localCurrencyCode != null)
				return false;
		} else if (!localCurrencyCode.equals(other.localCurrencyCode))
			return false;
		if (recordId == null) {
			if (other.recordId != null)
				return false;
		} else if (!recordId.equals(other.recordId))
			return false;
		if (recordLocator == null) {
			if (other.recordLocator != null)
				return false;
		} else if (!recordLocator.equals(other.recordLocator))
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
		if (separator == null) {
			if (other.separator != null)
				return false;
		} else if (!separator.equals(other.separator))
			return false;
		if (surchargeAppl == null) {
			if (other.surchargeAppl != null)
				return false;
		} else if (!surchargeAppl.equals(other.surchargeAppl))
			return false;
		if (surchargeType == null) {
			if (other.surchargeType != null)
				return false;
		} else if (!surchargeType.equals(other.surchargeType))
			return false;
		if (surchargeValue == null) {
			if (other.surchargeValue != null)
				return false;
		} else if (!surchargeValue.equals(other.surchargeValue))
			return false;
		if (timeOfDay == null) {
			if (other.timeOfDay != null)
				return false;
		} else if (!timeOfDay.equals(other.timeOfDay))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProsDataFeedsSurchargeRecord [recordId=" + recordId + ", recordLocator=" + recordLocator
				+ ", departureStartDate=" + departureStartDate + ", departureEndDate=" + departureEndDate
				+ ", saleStartDate=" + saleStartDate + ", saleEndDate=" + saleEndDate + ", timeOfDay=" + timeOfDay
				+ ", flightFrequency=" + flightFrequency + ", surchargeType=" + surchargeType + ", equipmentCode="
				+ equipmentCode + ", surchargeValue=" + surchargeValue + ", localCurrencyCode=" + localCurrencyCode
				+ ", surchargeAppl=" + surchargeAppl + ", flightNumber=" + flightNumber + "]";
	}

	public String build() {
		return recordId + separator + recordLocator + separator + departureStartDate + separator + departureEndDate
				+ separator + saleStartDate + separator + saleEndDate + separator + timeOfDay + separator
				+ flightFrequency + separator + surchargeType + separator + equipmentCode + separator + surchargeValue
				+ separator + localCurrencyCode + separator + surchargeAppl + separator + flightNumber;
	}
}
