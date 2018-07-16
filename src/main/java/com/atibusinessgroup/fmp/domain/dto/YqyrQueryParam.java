package com.atibusinessgroup.fmp.domain.dto;

import java.util.Date;

public class YqyrQueryParam {
	
	private String carrier;
	private String pointOfSale;	
	private String pointOfTicketing;
	private Date travelDate;
	private Date ticketingDate;
	private String origin;
	private String destination;
	private String via;
	private String applyAs;
	private String fareClass;
	private String bookingClass;
	private String paxType;
	private String posCode;
	private String ticketDesignator;
	private String valCarrier;
	private String opCarrier;
	private String flightNumber;
	private String equipment;
	private int page;
	private int size;
	private int lastIndex;
	
	public YqyrQueryParam() {
		
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getPointOfSale() {
		return pointOfSale;
	}

	public void setPointOfSale(String pointOfSale) {
		this.pointOfSale = pointOfSale;
	}

	public String getPointOfTicketing() {
		return pointOfTicketing;
	}

	public void setPointOfTicketing(String pointOfTicketing) {
		this.pointOfTicketing = pointOfTicketing;
	}

	public Date getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}

	public Date getTicketingDate() {
		return ticketingDate;
	}

	public void setTicketingDate(Date ticketingDate) {
		this.ticketingDate = ticketingDate;
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

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getApplyAs() {
		return applyAs;
	}

	public void setApplyAs(String applyAs) {
		this.applyAs = applyAs;
	}

	public String getFareClass() {
		return fareClass;
	}

	public void setFareClass(String fareClass) {
		this.fareClass = fareClass;
	}

	public String getBookingClass() {
		return bookingClass;
	}

	public void setBookingClass(String bookingClass) {
		this.bookingClass = bookingClass;
	}

	public String getPaxType() {
		return paxType;
	}

	public void setPaxType(String paxType) {
		this.paxType = paxType;
	}

	public String getPosCode() {
		return posCode;
	}

	public void setPosCode(String posCode) {
		this.posCode = posCode;
	}

	public String getTicketDesignator() {
		return ticketDesignator;
	}

	public void setTicketDesignator(String ticketDesignator) {
		this.ticketDesignator = ticketDesignator;
	}

	public String getValCarrier() {
		return valCarrier;
	}

	public void setValCarrier(String valCarrier) {
		this.valCarrier = valCarrier;
	}

	public String getOpCarrier() {
		return opCarrier;
	}

	public void setOpCarrier(String opCarrier) {
		this.opCarrier = opCarrier;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applyAs == null) ? 0 : applyAs.hashCode());
		result = prime * result + ((bookingClass == null) ? 0 : bookingClass.hashCode());
		result = prime * result + ((carrier == null) ? 0 : carrier.hashCode());
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((equipment == null) ? 0 : equipment.hashCode());
		result = prime * result + ((fareClass == null) ? 0 : fareClass.hashCode());
		result = prime * result + ((flightNumber == null) ? 0 : flightNumber.hashCode());
		result = prime * result + lastIndex;
		result = prime * result + ((opCarrier == null) ? 0 : opCarrier.hashCode());
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		result = prime * result + page;
		result = prime * result + ((paxType == null) ? 0 : paxType.hashCode());
		result = prime * result + ((pointOfSale == null) ? 0 : pointOfSale.hashCode());
		result = prime * result + ((pointOfTicketing == null) ? 0 : pointOfTicketing.hashCode());
		result = prime * result + ((posCode == null) ? 0 : posCode.hashCode());
		result = prime * result + size;
		result = prime * result + ((ticketDesignator == null) ? 0 : ticketDesignator.hashCode());
		result = prime * result + ((ticketingDate == null) ? 0 : ticketingDate.hashCode());
		result = prime * result + ((travelDate == null) ? 0 : travelDate.hashCode());
		result = prime * result + ((valCarrier == null) ? 0 : valCarrier.hashCode());
		result = prime * result + ((via == null) ? 0 : via.hashCode());
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
		YqyrQueryParam other = (YqyrQueryParam) obj;
		if (applyAs == null) {
			if (other.applyAs != null) {
				return false;
			}
		} else if (!applyAs.equals(other.applyAs)) {
			return false;
		}
		if (bookingClass == null) {
			if (other.bookingClass != null) {
				return false;
			}
		} else if (!bookingClass.equals(other.bookingClass)) {
			return false;
		}
		if (carrier == null) {
			if (other.carrier != null) {
				return false;
			}
		} else if (!carrier.equals(other.carrier)) {
			return false;
		}
		if (destination == null) {
			if (other.destination != null) {
				return false;
			}
		} else if (!destination.equals(other.destination)) {
			return false;
		}
		if (equipment == null) {
			if (other.equipment != null) {
				return false;
			}
		} else if (!equipment.equals(other.equipment)) {
			return false;
		}
		if (fareClass == null) {
			if (other.fareClass != null) {
				return false;
			}
		} else if (!fareClass.equals(other.fareClass)) {
			return false;
		}
		if (flightNumber == null) {
			if (other.flightNumber != null) {
				return false;
			}
		} else if (!flightNumber.equals(other.flightNumber)) {
			return false;
		}
		if (lastIndex != other.lastIndex) {
			return false;
		}
		if (opCarrier == null) {
			if (other.opCarrier != null) {
				return false;
			}
		} else if (!opCarrier.equals(other.opCarrier)) {
			return false;
		}
		if (origin == null) {
			if (other.origin != null) {
				return false;
			}
		} else if (!origin.equals(other.origin)) {
			return false;
		}
		if (page != other.page) {
			return false;
		}
		if (paxType == null) {
			if (other.paxType != null) {
				return false;
			}
		} else if (!paxType.equals(other.paxType)) {
			return false;
		}
		if (pointOfSale == null) {
			if (other.pointOfSale != null) {
				return false;
			}
		} else if (!pointOfSale.equals(other.pointOfSale)) {
			return false;
		}
		if (pointOfTicketing == null) {
			if (other.pointOfTicketing != null) {
				return false;
			}
		} else if (!pointOfTicketing.equals(other.pointOfTicketing)) {
			return false;
		}
		if (posCode == null) {
			if (other.posCode != null) {
				return false;
			}
		} else if (!posCode.equals(other.posCode)) {
			return false;
		}
		if (size != other.size) {
			return false;
		}
		if (ticketDesignator == null) {
			if (other.ticketDesignator != null) {
				return false;
			}
		} else if (!ticketDesignator.equals(other.ticketDesignator)) {
			return false;
		}
		if (ticketingDate == null) {
			if (other.ticketingDate != null) {
				return false;
			}
		} else if (!ticketingDate.equals(other.ticketingDate)) {
			return false;
		}
		if (travelDate == null) {
			if (other.travelDate != null) {
				return false;
			}
		} else if (!travelDate.equals(other.travelDate)) {
			return false;
		}
		if (valCarrier == null) {
			if (other.valCarrier != null) {
				return false;
			}
		} else if (!valCarrier.equals(other.valCarrier)) {
			return false;
		}
		if (via == null) {
			if (other.via != null) {
				return false;
			}
		} else if (!via.equals(other.via)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "YqyrQueryParam [carrier=" + carrier + ", pointOfSale=" + pointOfSale + ", pointOfTicketing="
				+ pointOfTicketing + ", travelDate=" + travelDate + ", ticketingDate=" + ticketingDate + ", origin="
				+ origin + ", destination=" + destination + ", via=" + via + ", applyAs=" + applyAs + ", fareClass="
				+ fareClass + ", bookingClass=" + bookingClass + ", paxType=" + paxType + ", posCode=" + posCode
				+ ", ticketDesignator=" + ticketDesignator + ", valCarrier=" + valCarrier + ", opCarrier=" + opCarrier
				+ ", flightNumber=" + flightNumber + ", equipment=" + equipment + ", page=" + page + ", size=" + size
				+ ", lastIndex=" + lastIndex + "]";
	}
}
