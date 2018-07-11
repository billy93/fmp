package com.atibusinessgroup.fmp.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class AfdQueryAddOns {
	private String id;
	private String source;
	private String sc;
	private String tariffNo;
	private String tariffCode;
	private String zone;
	private String carrierCode;
	private String originCity;
	private String originCountry;
	private String destinationCity;
	private String destinationCountry;
	private String fareClassCode;
	private List<String> bookingClass = new ArrayList<>();
	private String owrt;
	private String footnote1;
	private String footnote2;
	private String routingNo;
	private String currencyCode;
	private double baseAmount;
	private Object effectiveDate;
	private Object discontinueDate;
	private String gfsReference;
	private Object gfsDate;
	private Object focusDate;
	private Object firstTravelDate;
	private Object lastTravelDate;
	private List<AtpcoDateWrapper> travelDates = new ArrayList<>();
	private Object firstSaleDate;
	private Object lastSaleDate;
	private List<AtpcoDateWrapper> saleDates = new ArrayList<>();
	private String wpId;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
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
	
	public String getZone() {
		return zone;
	}
	
	public void setZone(String zone) {
		this.zone = zone;
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
	
	public String getOwrt() {
		return owrt;
	}
	
	public void setOwrt(String owrt) {
		this.owrt = owrt;
	}
	
	public String getFootnote1() {
		return footnote1;
	}
	
	public void setFootnote1(String footnote1) {
		this.footnote1 = footnote1;
	}
	
	public String getFootnote2() {
		return footnote2;
	}
	
	public void setFootnote2(String footnote2) {
		this.footnote2 = footnote2;
	}
	
	public String getRoutingNo() {
		return routingNo;
	}
	
	public void setRoutingNo(String routingNo) {
		this.routingNo = routingNo;
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
	
	public Object getFocusDate() {
		return focusDate;
	}
	
	public void setFocusDate(Object focusDate) {
		this.focusDate = focusDate;
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
	
	public List<AtpcoDateWrapper> getSaleDates() {
		return saleDates;
	}
	
	public void setSaleDates(List<AtpcoDateWrapper> saleDates) {
		this.saleDates = saleDates;
	}
	
	public String getWpId() {
		return wpId;
	}
	
	public void setWpId(String wpId) {
		this.wpId = wpId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(baseAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((bookingClass == null) ? 0 : bookingClass.hashCode());
		result = prime * result + ((carrierCode == null) ? 0 : carrierCode.hashCode());
		result = prime * result + ((currencyCode == null) ? 0 : currencyCode.hashCode());
		result = prime * result + ((destinationCity == null) ? 0 : destinationCity.hashCode());
		result = prime * result + ((destinationCountry == null) ? 0 : destinationCountry.hashCode());
		result = prime * result + ((discontinueDate == null) ? 0 : discontinueDate.hashCode());
		result = prime * result + ((effectiveDate == null) ? 0 : effectiveDate.hashCode());
		result = prime * result + ((fareClassCode == null) ? 0 : fareClassCode.hashCode());
		result = prime * result + ((firstSaleDate == null) ? 0 : firstSaleDate.hashCode());
		result = prime * result + ((firstTravelDate == null) ? 0 : firstTravelDate.hashCode());
		result = prime * result + ((focusDate == null) ? 0 : focusDate.hashCode());
		result = prime * result + ((footnote1 == null) ? 0 : footnote1.hashCode());
		result = prime * result + ((footnote2 == null) ? 0 : footnote2.hashCode());
		result = prime * result + ((gfsDate == null) ? 0 : gfsDate.hashCode());
		result = prime * result + ((gfsReference == null) ? 0 : gfsReference.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastSaleDate == null) ? 0 : lastSaleDate.hashCode());
		result = prime * result + ((lastTravelDate == null) ? 0 : lastTravelDate.hashCode());
		result = prime * result + ((originCity == null) ? 0 : originCity.hashCode());
		result = prime * result + ((originCountry == null) ? 0 : originCountry.hashCode());
		result = prime * result + ((owrt == null) ? 0 : owrt.hashCode());
		result = prime * result + ((routingNo == null) ? 0 : routingNo.hashCode());
		result = prime * result + ((saleDates == null) ? 0 : saleDates.hashCode());
		result = prime * result + ((sc == null) ? 0 : sc.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((tariffCode == null) ? 0 : tariffCode.hashCode());
		result = prime * result + ((tariffNo == null) ? 0 : tariffNo.hashCode());
		result = prime * result + ((travelDates == null) ? 0 : travelDates.hashCode());
		result = prime * result + ((wpId == null) ? 0 : wpId.hashCode());
		result = prime * result + ((zone == null) ? 0 : zone.hashCode());
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
		AfdQueryAddOns other = (AfdQueryAddOns) obj;
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
		if (carrierCode == null) {
			if (other.carrierCode != null) {
				return false;
			}
		} else if (!carrierCode.equals(other.carrierCode)) {
			return false;
		}
		if (currencyCode == null) {
			if (other.currencyCode != null) {
				return false;
			}
		} else if (!currencyCode.equals(other.currencyCode)) {
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
		if (effectiveDate == null) {
			if (other.effectiveDate != null) {
				return false;
			}
		} else if (!effectiveDate.equals(other.effectiveDate)) {
			return false;
		}
		if (fareClassCode == null) {
			if (other.fareClassCode != null) {
				return false;
			}
		} else if (!fareClassCode.equals(other.fareClassCode)) {
			return false;
		}
		if (firstSaleDate == null) {
			if (other.firstSaleDate != null) {
				return false;
			}
		} else if (!firstSaleDate.equals(other.firstSaleDate)) {
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
		if (footnote1 == null) {
			if (other.footnote1 != null) {
				return false;
			}
		} else if (!footnote1.equals(other.footnote1)) {
			return false;
		}
		if (footnote2 == null) {
			if (other.footnote2 != null) {
				return false;
			}
		} else if (!footnote2.equals(other.footnote2)) {
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
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (lastSaleDate == null) {
			if (other.lastSaleDate != null) {
				return false;
			}
		} else if (!lastSaleDate.equals(other.lastSaleDate)) {
			return false;
		}
		if (lastTravelDate == null) {
			if (other.lastTravelDate != null) {
				return false;
			}
		} else if (!lastTravelDate.equals(other.lastTravelDate)) {
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
		if (owrt == null) {
			if (other.owrt != null) {
				return false;
			}
		} else if (!owrt.equals(other.owrt)) {
			return false;
		}
		if (routingNo == null) {
			if (other.routingNo != null) {
				return false;
			}
		} else if (!routingNo.equals(other.routingNo)) {
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
		if (zone == null) {
			if (other.zone != null) {
				return false;
			}
		} else if (!zone.equals(other.zone)) {
			return false;
		}
		return true;
	}
}
