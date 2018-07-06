package com.atibusinessgroup.fmp.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class AfdQuery {
	private String fareId;
	private String source;
	private String sc;
	private String tariffNo;
	private String tariffCode;
	private String carrierCode;
	private String originCity;
	private String originCountry;
	private String destinationCity;
	private String destinationCountry;
	private String fareClassCode;
	private List<String> bookingClass = new ArrayList<>();
	private String cabin;
	private String owrt;
	private String footnote;
	private String routingNo;
	private String ruleNo;
	private String maximumPermittedMileage;
	private String currencyCode;
	private double baseAmount;
	private String error;
	private String tfcCalculationDate;
	private Object tfcFocusDate;
	private double yqyr;
	private double taxes;
	private double tfc;
	private double aif;
	private String itinerary;
	private boolean overrideIndicator;
	private Object effectiveDate;
	private Object discontinueDate;
	private String gfsReference;
	private Object gfsDate;
	private String globalIndicator;
	private Object focusDate;
	private boolean sellable;
	private List<String> minStay = new ArrayList<>();
	private List<String> maxStay = new ArrayList<>();
	private List<String> advancePurchase = new ArrayList<>();
	private String dowIn;
	private String dowOut;
	private String season;
	private String dayOfWeekType;
	private String travelDateRanges;
	private String fareType;
	private List<String> paxType = new ArrayList<>();
	private String wpObjectId;
	private String wpId;
	private String wpName;
	private List<String> tourCode = new ArrayList<>();
	private List<String> cat50Title = new ArrayList<>();
	private String groupFare;
	private double refAmount;
	private double refYqyr;
	private double refTaxes;
	private double refTfc;
	private double refAif;
	private Object firstSeasonDate;
	private Object lastSeasonDate;
	private List<AtpcoDateWrapper> seasonDates = new ArrayList<>();
	private Object firstTravelDate;
	private Object lastTravelDate;
	private List<AtpcoDateWrapper> travelDates = new ArrayList<>();
	private Object firstSaleDate;
	private Object lastSaleDate;
	private Object firstResDate;
	private Object lastResDate;
	private List<AtpcoDateWrapper> saleDates = new ArrayList<>();
	
	public String getFareId() {
		return fareId;
	}

	public void setFareId(String fareId) {
		this.fareId = fareId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSc() {
		return sc;
	}

	public void setSc(String sc) {
		this.sc = sc;
	}

	public String getTariffNo() {
		return tariffNo;
	}

	public void setTariffNo(String tariffNo) {
		this.tariffNo = tariffNo;
	}

	public String getTariffCode() {
		return tariffCode;
	}

	public void setTariffCode(String tariffCode) {
		this.tariffCode = tariffCode;
	}

	public String getCarrierCode() {
		return carrierCode;
	}

	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}

	public String getOriginCity() {
		return originCity;
	}

	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	public String getDestinationCountry() {
		return destinationCountry;
	}

	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}

	public String getFareClassCode() {
		return fareClassCode;
	}

	public void setFareClassCode(String fareClassCode) {
		this.fareClassCode = fareClassCode;
	}

	public List<String> getBookingClass() {
		return bookingClass;
	}

	public void setBookingClass(List<String> bookingClass) {
		this.bookingClass = bookingClass;
	}

	public String getCabin() {
		return cabin;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	public String getOwrt() {
		return owrt;
	}

	public void setOwrt(String owrt) {
		this.owrt = owrt;
	}

	public String getFootnote() {
		return footnote;
	}

	public void setFootnote(String footnote) {
		this.footnote = footnote;
	}

	public String getRoutingNo() {
		return routingNo;
	}

	public void setRoutingNo(String routingNo) {
		this.routingNo = routingNo;
	}

	public String getRuleNo() {
		return ruleNo;
	}

	public void setRuleNo(String ruleNo) {
		this.ruleNo = ruleNo;
	}

	public String getMaximumPermittedMileage() {
		return maximumPermittedMileage;
	}

	public void setMaximumPermittedMileage(String maximumPermittedMileage) {
		this.maximumPermittedMileage = maximumPermittedMileage;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public double getBaseAmount() {
		return baseAmount;
	}

	public void setBaseAmount(double baseAmount) {
		this.baseAmount = baseAmount;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getTfcCalculationDate() {
		return tfcCalculationDate;
	}

	public void setTfcCalculationDate(String tfcCalculationDate) {
		this.tfcCalculationDate = tfcCalculationDate;
	}

	public Object getTfcFocusDate() {
		return tfcFocusDate;
	}

	public void setTfcFocusDate(Object tfcFocusDate) {
		this.tfcFocusDate = tfcFocusDate;
	}

	public double getYqyr() {
		return yqyr;
	}

	public void setYqyr(double yqyr) {
		this.yqyr = yqyr;
	}

	public double getTaxes() {
		return taxes;
	}

	public void setTaxes(double taxes) {
		this.taxes = taxes;
	}

	public double getTfc() {
		return tfc;
	}

	public void setTfc(double tfc) {
		this.tfc = tfc;
	}

	public double getAif() {
		return aif;
	}

	public void setAif(double aif) {
		this.aif = aif;
	}

	public String getItinerary() {
		return itinerary;
	}

	public void setItinerary(String itinerary) {
		this.itinerary = itinerary;
	}

	public boolean isOverrideIndicator() {
		return overrideIndicator;
	}

	public void setOverrideIndicator(boolean overrideIndicator) {
		this.overrideIndicator = overrideIndicator;
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

	public String getGfsReference() {
		return gfsReference;
	}

	public void setGfsReference(String gfsReference) {
		this.gfsReference = gfsReference;
	}

	public Object getGfsDate() {
		return gfsDate;
	}

	public void setGfsDate(Object gfsDate) {
		this.gfsDate = gfsDate;
	}

	public String getGlobalIndicator() {
		return globalIndicator;
	}

	public void setGlobalIndicator(String globalIndicator) {
		this.globalIndicator = globalIndicator;
	}

	public Object getFocusDate() {
		return focusDate;
	}

	public void setFocusDate(Object focusDate) {
		this.focusDate = focusDate;
	}

	public boolean isSellable() {
		return sellable;
	}

	public void setSellable(boolean sellable) {
		this.sellable = sellable;
	}

	public List<String> getMinStay() {
		return minStay;
	}

	public void setMinStay(List<String> minStay) {
		this.minStay = minStay;
	}

	public List<String> getMaxStay() {
		return maxStay;
	}

	public void setMaxStay(List<String> maxStay) {
		this.maxStay = maxStay;
	}

	public List<String> getAdvancePurchase() {
		return advancePurchase;
	}

	public void setAdvancePurchase(List<String> advancePurchase) {
		this.advancePurchase = advancePurchase;
	}

	public String getDowIn() {
		return dowIn;
	}

	public void setDowIn(String dowIn) {
		this.dowIn = dowIn;
	}

	public String getDowOut() {
		return dowOut;
	}

	public void setDowOut(String dowOut) {
		this.dowOut = dowOut;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getDayOfWeekType() {
		return dayOfWeekType;
	}

	public void setDayOfWeekType(String dayOfWeekType) {
		this.dayOfWeekType = dayOfWeekType;
	}

	public String getTravelDateRanges() {
		return travelDateRanges;
	}

	public void setTravelDateRanges(String travelDateRanges) {
		this.travelDateRanges = travelDateRanges;
	}

	public String getFareType() {
		return fareType;
	}

	public void setFareType(String fareType) {
		this.fareType = fareType;
	}

	public List<String> getPaxType() {
		return paxType;
	}

	public void setPaxType(List<String> paxType) {
		this.paxType = paxType;
	}

	public String getWpObjectId() {
		return wpObjectId;
	}

	public void setWpObjectId(String wpObjectId) {
		this.wpObjectId = wpObjectId;
	}

	public String getWpId() {
		return wpId;
	}

	public void setWpId(String wpId) {
		this.wpId = wpId;
	}

	public String getWpName() {
		return wpName;
	}

	public void setWpName(String wpName) {
		this.wpName = wpName;
	}

	public List<String> getTourCode() {
		return tourCode;
	}

	public void setTourCode(List<String> tourCode) {
		this.tourCode = tourCode;
	}

	public List<String> getCat50Title() {
		return cat50Title;
	}

	public void setCat50Title(List<String> cat50Title) {
		this.cat50Title = cat50Title;
	}

	public String getGroupFare() {
		return groupFare;
	}

	public void setGroupFare(String groupFare) {
		this.groupFare = groupFare;
	}

	public double getRefAmount() {
		return refAmount;
	}

	public void setRefAmount(double refAmount) {
		this.refAmount = refAmount;
	}

	public double getRefYqyr() {
		return refYqyr;
	}

	public void setRefYqyr(double refYqyr) {
		this.refYqyr = refYqyr;
	}

	public double getRefTaxes() {
		return refTaxes;
	}

	public void setRefTaxes(double refTaxes) {
		this.refTaxes = refTaxes;
	}

	public double getRefTfc() {
		return refTfc;
	}

	public void setRefTfc(double refTfc) {
		this.refTfc = refTfc;
	}

	public double getRefAif() {
		return refAif;
	}

	public void setRefAif(double refAif) {
		this.refAif = refAif;
	}

	public Object getFirstSeasonDate() {
		return firstSeasonDate;
	}

	public void setFirstSeasonDate(Object firstSeasonDate) {
		this.firstSeasonDate = firstSeasonDate;
	}

	public Object getLastSeasonDate() {
		return lastSeasonDate;
	}

	public void setLastSeasonDate(Object lastSeasonDate) {
		this.lastSeasonDate = lastSeasonDate;
	}

	public List<AtpcoDateWrapper> getSeasonDates() {
		return seasonDates;
	}

	public void setSeasonDates(List<AtpcoDateWrapper> seasonDates) {
		this.seasonDates = seasonDates;
	}

	public Object getFirstTravelDate() {
		return firstTravelDate;
	}

	public void setFirstTravelDate(Object firstTravelDate) {
		this.firstTravelDate = firstTravelDate;
	}

	public Object getLastTravelDate() {
		return lastTravelDate;
	}

	public void setLastTravelDate(Object lastTravelDate) {
		this.lastTravelDate = lastTravelDate;
	}

	public List<AtpcoDateWrapper> getTravelDates() {
		return travelDates;
	}

	public void setTravelDates(List<AtpcoDateWrapper> travelDates) {
		this.travelDates = travelDates;
	}

	public Object getFirstSaleDate() {
		return firstSaleDate;
	}

	public void setFirstSaleDate(Object firstSaleDate) {
		this.firstSaleDate = firstSaleDate;
	}

	public Object getLastSaleDate() {
		return lastSaleDate;
	}

	public void setLastSaleDate(Object lastSaleDate) {
		this.lastSaleDate = lastSaleDate;
	}

	public Object getFirstResDate() {
		return firstResDate;
	}

	public void setFirstResDate(Object firstResDate) {
		this.firstResDate = firstResDate;
	}

	public Object getLastResDate() {
		return lastResDate;
	}

	public void setLastResDate(Object lastResDate) {
		this.lastResDate = lastResDate;
	}

	public List<AtpcoDateWrapper> getSaleDates() {
		return saleDates;
	}

	public void setSaleDates(List<AtpcoDateWrapper> saleDates) {
		this.saleDates = saleDates;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((advancePurchase == null) ? 0 : advancePurchase.hashCode());
		long temp;
		temp = Double.doubleToLongBits(aif);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(baseAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((bookingClass == null) ? 0 : bookingClass.hashCode());
		result = prime * result + ((cabin == null) ? 0 : cabin.hashCode());
		result = prime * result + ((carrierCode == null) ? 0 : carrierCode.hashCode());
		result = prime * result + ((cat50Title == null) ? 0 : cat50Title.hashCode());
		result = prime * result + ((currencyCode == null) ? 0 : currencyCode.hashCode());
		result = prime * result + ((dayOfWeekType == null) ? 0 : dayOfWeekType.hashCode());
		result = prime * result + ((destinationCity == null) ? 0 : destinationCity.hashCode());
		result = prime * result + ((destinationCountry == null) ? 0 : destinationCountry.hashCode());
		result = prime * result + ((discontinueDate == null) ? 0 : discontinueDate.hashCode());
		result = prime * result + ((dowIn == null) ? 0 : dowIn.hashCode());
		result = prime * result + ((dowOut == null) ? 0 : dowOut.hashCode());
		result = prime * result + ((effectiveDate == null) ? 0 : effectiveDate.hashCode());
		result = prime * result + ((error == null) ? 0 : error.hashCode());
		result = prime * result + ((fareClassCode == null) ? 0 : fareClassCode.hashCode());
		result = prime * result + ((fareId == null) ? 0 : fareId.hashCode());
		result = prime * result + ((fareType == null) ? 0 : fareType.hashCode());
		result = prime * result + ((firstResDate == null) ? 0 : firstResDate.hashCode());
		result = prime * result + ((firstSaleDate == null) ? 0 : firstSaleDate.hashCode());
		result = prime * result + ((firstSeasonDate == null) ? 0 : firstSeasonDate.hashCode());
		result = prime * result + ((firstTravelDate == null) ? 0 : firstTravelDate.hashCode());
		result = prime * result + ((focusDate == null) ? 0 : focusDate.hashCode());
		result = prime * result + ((footnote == null) ? 0 : footnote.hashCode());
		result = prime * result + ((gfsDate == null) ? 0 : gfsDate.hashCode());
		result = prime * result + ((gfsReference == null) ? 0 : gfsReference.hashCode());
		result = prime * result + ((globalIndicator == null) ? 0 : globalIndicator.hashCode());
		result = prime * result + ((groupFare == null) ? 0 : groupFare.hashCode());
		result = prime * result + ((itinerary == null) ? 0 : itinerary.hashCode());
		result = prime * result + ((lastResDate == null) ? 0 : lastResDate.hashCode());
		result = prime * result + ((lastSaleDate == null) ? 0 : lastSaleDate.hashCode());
		result = prime * result + ((lastSeasonDate == null) ? 0 : lastSeasonDate.hashCode());
		result = prime * result + ((lastTravelDate == null) ? 0 : lastTravelDate.hashCode());
		result = prime * result + ((maxStay == null) ? 0 : maxStay.hashCode());
		result = prime * result + ((maximumPermittedMileage == null) ? 0 : maximumPermittedMileage.hashCode());
		result = prime * result + ((minStay == null) ? 0 : minStay.hashCode());
		result = prime * result + ((originCity == null) ? 0 : originCity.hashCode());
		result = prime * result + ((originCountry == null) ? 0 : originCountry.hashCode());
		result = prime * result + (overrideIndicator ? 1231 : 1237);
		result = prime * result + ((owrt == null) ? 0 : owrt.hashCode());
		result = prime * result + ((paxType == null) ? 0 : paxType.hashCode());
		temp = Double.doubleToLongBits(refAif);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(refAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(refTaxes);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(refTfc);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(refYqyr);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((routingNo == null) ? 0 : routingNo.hashCode());
		result = prime * result + ((ruleNo == null) ? 0 : ruleNo.hashCode());
		result = prime * result + ((saleDates == null) ? 0 : saleDates.hashCode());
		result = prime * result + ((sc == null) ? 0 : sc.hashCode());
		result = prime * result + ((season == null) ? 0 : season.hashCode());
		result = prime * result + ((seasonDates == null) ? 0 : seasonDates.hashCode());
		result = prime * result + (sellable ? 1231 : 1237);
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((tariffCode == null) ? 0 : tariffCode.hashCode());
		result = prime * result + ((tariffNo == null) ? 0 : tariffNo.hashCode());
		temp = Double.doubleToLongBits(taxes);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(tfc);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((tfcCalculationDate == null) ? 0 : tfcCalculationDate.hashCode());
		result = prime * result + ((tfcFocusDate == null) ? 0 : tfcFocusDate.hashCode());
		result = prime * result + ((tourCode == null) ? 0 : tourCode.hashCode());
		result = prime * result + ((travelDateRanges == null) ? 0 : travelDateRanges.hashCode());
		result = prime * result + ((travelDates == null) ? 0 : travelDates.hashCode());
		result = prime * result + ((wpId == null) ? 0 : wpId.hashCode());
		result = prime * result + ((wpName == null) ? 0 : wpName.hashCode());
		result = prime * result + ((wpObjectId == null) ? 0 : wpObjectId.hashCode());
		temp = Double.doubleToLongBits(yqyr);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AfdQuery other = (AfdQuery) obj;
		if (advancePurchase == null) {
			if (other.advancePurchase != null) {
				return false;
			}
		} else if (!advancePurchase.equals(other.advancePurchase)) {
			return false;
		}
		if (Double.doubleToLongBits(aif) != Double.doubleToLongBits(other.aif)) {
			return false;
		}
		if (Double.doubleToLongBits(baseAmount) != Double.doubleToLongBits(other.baseAmount)) {
			return false;
		}
		if (bookingClass == null) {
			if (other.bookingClass != null) {
				return false;
			}
		} else if (!bookingClass.equals(other.bookingClass)) {
			return false;
		}
		if (cabin == null) {
			if (other.cabin != null) {
				return false;
			}
		} else if (!cabin.equals(other.cabin)) {
			return false;
		}
		if (carrierCode == null) {
			if (other.carrierCode != null) {
				return false;
			}
		} else if (!carrierCode.equals(other.carrierCode)) {
			return false;
		}
		if (cat50Title == null) {
			if (other.cat50Title != null) {
				return false;
			}
		} else if (!cat50Title.equals(other.cat50Title)) {
			return false;
		}
		if (currencyCode == null) {
			if (other.currencyCode != null) {
				return false;
			}
		} else if (!currencyCode.equals(other.currencyCode)) {
			return false;
		}
		if (dayOfWeekType == null) {
			if (other.dayOfWeekType != null) {
				return false;
			}
		} else if (!dayOfWeekType.equals(other.dayOfWeekType)) {
			return false;
		}
		if (destinationCity == null) {
			if (other.destinationCity != null) {
				return false;
			}
		} else if (!destinationCity.equals(other.destinationCity)) {
			return false;
		}
		if (destinationCountry == null) {
			if (other.destinationCountry != null) {
				return false;
			}
		} else if (!destinationCountry.equals(other.destinationCountry)) {
			return false;
		}
		if (discontinueDate == null) {
			if (other.discontinueDate != null) {
				return false;
			}
		} else if (!discontinueDate.equals(other.discontinueDate)) {
			return false;
		}
		if (dowIn == null) {
			if (other.dowIn != null) {
				return false;
			}
		} else if (!dowIn.equals(other.dowIn)) {
			return false;
		}
		if (dowOut == null) {
			if (other.dowOut != null) {
				return false;
			}
		} else if (!dowOut.equals(other.dowOut)) {
			return false;
		}
		if (effectiveDate == null) {
			if (other.effectiveDate != null) {
				return false;
			}
		} else if (!effectiveDate.equals(other.effectiveDate)) {
			return false;
		}
		if (error == null) {
			if (other.error != null) {
				return false;
			}
		} else if (!error.equals(other.error)) {
			return false;
		}
		if (fareClassCode == null) {
			if (other.fareClassCode != null) {
				return false;
			}
		} else if (!fareClassCode.equals(other.fareClassCode)) {
			return false;
		}
		if (fareId == null) {
			if (other.fareId != null) {
				return false;
			}
		} else if (!fareId.equals(other.fareId)) {
			return false;
		}
		if (fareType == null) {
			if (other.fareType != null) {
				return false;
			}
		} else if (!fareType.equals(other.fareType)) {
			return false;
		}
		if (firstResDate == null) {
			if (other.firstResDate != null) {
				return false;
			}
		} else if (!firstResDate.equals(other.firstResDate)) {
			return false;
		}
		if (firstSaleDate == null) {
			if (other.firstSaleDate != null) {
				return false;
			}
		} else if (!firstSaleDate.equals(other.firstSaleDate)) {
			return false;
		}
		if (firstSeasonDate == null) {
			if (other.firstSeasonDate != null) {
				return false;
			}
		} else if (!firstSeasonDate.equals(other.firstSeasonDate)) {
			return false;
		}
		if (firstTravelDate == null) {
			if (other.firstTravelDate != null) {
				return false;
			}
		} else if (!firstTravelDate.equals(other.firstTravelDate)) {
			return false;
		}
		if (focusDate == null) {
			if (other.focusDate != null) {
				return false;
			}
		} else if (!focusDate.equals(other.focusDate)) {
			return false;
		}
		if (footnote == null) {
			if (other.footnote != null) {
				return false;
			}
		} else if (!footnote.equals(other.footnote)) {
			return false;
		}
		if (gfsDate == null) {
			if (other.gfsDate != null) {
				return false;
			}
		} else if (!gfsDate.equals(other.gfsDate)) {
			return false;
		}
		if (gfsReference == null) {
			if (other.gfsReference != null) {
				return false;
			}
		} else if (!gfsReference.equals(other.gfsReference)) {
			return false;
		}
		if (globalIndicator == null) {
			if (other.globalIndicator != null) {
				return false;
			}
		} else if (!globalIndicator.equals(other.globalIndicator)) {
			return false;
		}
		if (groupFare == null) {
			if (other.groupFare != null) {
				return false;
			}
		} else if (!groupFare.equals(other.groupFare)) {
			return false;
		}
		if (itinerary == null) {
			if (other.itinerary != null) {
				return false;
			}
		} else if (!itinerary.equals(other.itinerary)) {
			return false;
		}
		if (lastResDate == null) {
			if (other.lastResDate != null) {
				return false;
			}
		} else if (!lastResDate.equals(other.lastResDate)) {
			return false;
		}
		if (lastSaleDate == null) {
			if (other.lastSaleDate != null) {
				return false;
			}
		} else if (!lastSaleDate.equals(other.lastSaleDate)) {
			return false;
		}
		if (lastSeasonDate == null) {
			if (other.lastSeasonDate != null) {
				return false;
			}
		} else if (!lastSeasonDate.equals(other.lastSeasonDate)) {
			return false;
		}
		if (lastTravelDate == null) {
			if (other.lastTravelDate != null) {
				return false;
			}
		} else if (!lastTravelDate.equals(other.lastTravelDate)) {
			return false;
		}
		if (maxStay == null) {
			if (other.maxStay != null) {
				return false;
			}
		} else if (!maxStay.equals(other.maxStay)) {
			return false;
		}
		if (maximumPermittedMileage == null) {
			if (other.maximumPermittedMileage != null) {
				return false;
			}
		} else if (!maximumPermittedMileage.equals(other.maximumPermittedMileage)) {
			return false;
		}
		if (minStay == null) {
			if (other.minStay != null) {
				return false;
			}
		} else if (!minStay.equals(other.minStay)) {
			return false;
		}
		if (originCity == null) {
			if (other.originCity != null) {
				return false;
			}
		} else if (!originCity.equals(other.originCity)) {
			return false;
		}
		if (originCountry == null) {
			if (other.originCountry != null) {
				return false;
			}
		} else if (!originCountry.equals(other.originCountry)) {
			return false;
		}
		if (overrideIndicator != other.overrideIndicator) {
			return false;
		}
		if (owrt == null) {
			if (other.owrt != null) {
				return false;
			}
		} else if (!owrt.equals(other.owrt)) {
			return false;
		}
		if (paxType == null) {
			if (other.paxType != null) {
				return false;
			}
		} else if (!paxType.equals(other.paxType)) {
			return false;
		}
		if (Double.doubleToLongBits(refAif) != Double.doubleToLongBits(other.refAif)) {
			return false;
		}
		if (Double.doubleToLongBits(refAmount) != Double.doubleToLongBits(other.refAmount)) {
			return false;
		}
		if (Double.doubleToLongBits(refTaxes) != Double.doubleToLongBits(other.refTaxes)) {
			return false;
		}
		if (Double.doubleToLongBits(refTfc) != Double.doubleToLongBits(other.refTfc)) {
			return false;
		}
		if (Double.doubleToLongBits(refYqyr) != Double.doubleToLongBits(other.refYqyr)) {
			return false;
		}
		if (routingNo == null) {
			if (other.routingNo != null) {
				return false;
			}
		} else if (!routingNo.equals(other.routingNo)) {
			return false;
		}
		if (ruleNo == null) {
			if (other.ruleNo != null) {
				return false;
			}
		} else if (!ruleNo.equals(other.ruleNo)) {
			return false;
		}
		if (saleDates == null) {
			if (other.saleDates != null) {
				return false;
			}
		} else if (!saleDates.equals(other.saleDates)) {
			return false;
		}
		if (sc == null) {
			if (other.sc != null) {
				return false;
			}
		} else if (!sc.equals(other.sc)) {
			return false;
		}
		if (season == null) {
			if (other.season != null) {
				return false;
			}
		} else if (!season.equals(other.season)) {
			return false;
		}
		if (seasonDates == null) {
			if (other.seasonDates != null) {
				return false;
			}
		} else if (!seasonDates.equals(other.seasonDates)) {
			return false;
		}
		if (sellable != other.sellable) {
			return false;
		}
		if (source == null) {
			if (other.source != null) {
				return false;
			}
		} else if (!source.equals(other.source)) {
			return false;
		}
		if (tariffCode == null) {
			if (other.tariffCode != null) {
				return false;
			}
		} else if (!tariffCode.equals(other.tariffCode)) {
			return false;
		}
		if (tariffNo == null) {
			if (other.tariffNo != null) {
				return false;
			}
		} else if (!tariffNo.equals(other.tariffNo)) {
			return false;
		}
		if (Double.doubleToLongBits(taxes) != Double.doubleToLongBits(other.taxes)) {
			return false;
		}
		if (Double.doubleToLongBits(tfc) != Double.doubleToLongBits(other.tfc)) {
			return false;
		}
		if (tfcCalculationDate == null) {
			if (other.tfcCalculationDate != null) {
				return false;
			}
		} else if (!tfcCalculationDate.equals(other.tfcCalculationDate)) {
			return false;
		}
		if (tfcFocusDate == null) {
			if (other.tfcFocusDate != null) {
				return false;
			}
		} else if (!tfcFocusDate.equals(other.tfcFocusDate)) {
			return false;
		}
		if (tourCode == null) {
			if (other.tourCode != null) {
				return false;
			}
		} else if (!tourCode.equals(other.tourCode)) {
			return false;
		}
		if (travelDateRanges == null) {
			if (other.travelDateRanges != null) {
				return false;
			}
		} else if (!travelDateRanges.equals(other.travelDateRanges)) {
			return false;
		}
		if (travelDates == null) {
			if (other.travelDates != null) {
				return false;
			}
		} else if (!travelDates.equals(other.travelDates)) {
			return false;
		}
		if (wpId == null) {
			if (other.wpId != null) {
				return false;
			}
		} else if (!wpId.equals(other.wpId)) {
			return false;
		}
		if (wpName == null) {
			if (other.wpName != null) {
				return false;
			}
		} else if (!wpName.equals(other.wpName)) {
			return false;
		}
		if (wpObjectId == null) {
			if (other.wpObjectId != null) {
				return false;
			}
		} else if (!wpObjectId.equals(other.wpObjectId)) {
			return false;
		}
		if (Double.doubleToLongBits(yqyr) != Double.doubleToLongBits(other.yqyr)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AfdQuery [fareId=" + fareId + ", source=" + source + ", sc=" + sc + ", tariffNo=" + tariffNo
				+ ", tariffCode=" + tariffCode + ", carrierCode=" + carrierCode + ", originCity=" + originCity
				+ ", originCountry=" + originCountry + ", destinationCity=" + destinationCity + ", destinationCountry="
				+ destinationCountry + ", fareClassCode=" + fareClassCode + ", bookingClass=" + bookingClass
				+ ", cabin=" + cabin + ", owrt=" + owrt + ", footnote=" + footnote + ", routingNo=" + routingNo
				+ ", ruleNo=" + ruleNo + ", maximumPermittedMileage=" + maximumPermittedMileage + ", currencyCode="
				+ currencyCode + ", baseAmount=" + baseAmount + ", error=" + error + ", tfcCalculationDate="
				+ tfcCalculationDate + ", tfcFocusDate=" + tfcFocusDate + ", yqyr=" + yqyr + ", taxes=" + taxes
				+ ", tfc=" + tfc + ", aif=" + aif + ", itinerary=" + itinerary + ", overrideIndicator="
				+ overrideIndicator + ", effectiveDate=" + effectiveDate + ", discontinueDate=" + discontinueDate
				+ ", gfsReference=" + gfsReference + ", gfsDate=" + gfsDate + ", globalIndicator=" + globalIndicator
				+ ", focusDate=" + focusDate + ", sellable=" + sellable + ", minStay=" + minStay + ", maxStay="
				+ maxStay + ", advancePurchase=" + advancePurchase + ", dowIn=" + dowIn + ", dowOut=" + dowOut
				+ ", season=" + season + ", dayOfWeekType=" + dayOfWeekType + ", travelDateRanges=" + travelDateRanges
				+ ", fareType=" + fareType + ", paxType=" + paxType + ", wpObjectId=" + wpObjectId + ", wpId=" + wpId
				+ ", wpName=" + wpName + ", tourCode=" + tourCode + ", cat50Title=" + cat50Title + ", groupFare="
				+ groupFare + ", refAmount=" + refAmount + ", refYqyr=" + refYqyr + ", refTaxes=" + refTaxes
				+ ", refTfc=" + refTfc + ", refAif=" + refAif + ", firstSeasonDate=" + firstSeasonDate
				+ ", lastSeasonDate=" + lastSeasonDate + ", seasonDates=" + seasonDates + ", firstTravelDate="
				+ firstTravelDate + ", lastTravelDate=" + lastTravelDate + ", travelDates=" + travelDates
				+ ", firstSaleDate=" + firstSaleDate + ", lastSaleDate=" + lastSaleDate + ", firstResDate="
				+ firstResDate + ", lastResDate=" + lastResDate + ", saleDates=" + saleDates + "]";
	}
}
