package com.atibusinessgroup.fmp.domain.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

public class AtpcoRecord1FareClassInformation {
	
	@Field("di")
    private String directionalIndicator;
	
	@Field("ticketing_code")
    private String ticketingCode;
	
	@Field("tcm")
    private String ticketingCodeModifier;
	
	@Field("ticket_designator")
    private String ticketDesignator;
	
	@Field("tdm")
    private String ticketDesignatorModifier;
	
	@Field("psgr_type")
    private String passengerType;
	
	@Field("psgr_age_min")
    private String passengerMinAge;

	@Field("psgr_age_max")
    private String passengerMaxAge;
	
	@Field("filler_1")
    private String filler;
	
	@Field("date_tbl_no_994")
    private String dateTableNo994;
	
	@Field("rbd")
    private List<String> rbd = new ArrayList<>();
	
	@Field("carrier_tbl_990")
    private String carrierTableNo990;
	
	@Field("rbd_tbl_999")
    private String rbdTableNo999;
	
	@Field("commercial_name")
    private String commercialName;

	public String getDirectionalIndicator() {
		return directionalIndicator;
	}

	public void setDirectionalIndicator(String directionalIndicator) {
		this.directionalIndicator = directionalIndicator;
	}

	public String getTicketingCode() {
		return ticketingCode;
	}

	public void setTicketingCode(String ticketingCode) {
		this.ticketingCode = ticketingCode;
	}

	public String getTicketingCodeModifier() {
		return ticketingCodeModifier;
	}

	public void setTicketingCodeModifier(String ticketingCodeModifier) {
		this.ticketingCodeModifier = ticketingCodeModifier;
	}

	public String getTicketDesignator() {
		return ticketDesignator;
	}

	public void setTicketDesignator(String ticketDesignator) {
		this.ticketDesignator = ticketDesignator;
	}

	public String getTicketDesignatorModifier() {
		return ticketDesignatorModifier;
	}

	public void setTicketDesignatorModifier(String ticketDesignatorModifier) {
		this.ticketDesignatorModifier = ticketDesignatorModifier;
	}

	public String getPassengerType() {
		return passengerType;
	}

	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}

	public String getPassengerMinAge() {
		return passengerMinAge;
	}

	public void setPassengerMinAge(String passengerMinAge) {
		this.passengerMinAge = passengerMinAge;
	}

	public String getPassengerMaxAge() {
		return passengerMaxAge;
	}

	public void setPassengerMaxAge(String passengerMaxAge) {
		this.passengerMaxAge = passengerMaxAge;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	public String getDateTableNo994() {
		return dateTableNo994;
	}

	public void setDateTableNo994(String dateTableNo994) {
		this.dateTableNo994 = dateTableNo994;
	}

	public List<String> getRbd() {
		return rbd;
	}

	public void setRbd(List<String> rbd) {
		this.rbd = rbd;
	}

	public String getCarrierTableNo990() {
		return carrierTableNo990;
	}

	public void setCarrierTableNo990(String carrierTableNo990) {
		this.carrierTableNo990 = carrierTableNo990;
	}

	public String getRbdTableNo999() {
		return rbdTableNo999;
	}

	public void setRbdTableNo999(String rbdTableNo999) {
		this.rbdTableNo999 = rbdTableNo999;
	}

	public String getCommercialName() {
		return commercialName;
	}

	public void setCommercialName(String commercialName) {
		this.commercialName = commercialName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carrierTableNo990 == null) ? 0 : carrierTableNo990.hashCode());
		result = prime * result + ((commercialName == null) ? 0 : commercialName.hashCode());
		result = prime * result + ((dateTableNo994 == null) ? 0 : dateTableNo994.hashCode());
		result = prime * result + ((directionalIndicator == null) ? 0 : directionalIndicator.hashCode());
		result = prime * result + ((filler == null) ? 0 : filler.hashCode());
		result = prime * result + ((passengerMaxAge == null) ? 0 : passengerMaxAge.hashCode());
		result = prime * result + ((passengerMinAge == null) ? 0 : passengerMinAge.hashCode());
		result = prime * result + ((passengerType == null) ? 0 : passengerType.hashCode());
		result = prime * result + ((rbd == null) ? 0 : rbd.hashCode());
		result = prime * result + ((rbdTableNo999 == null) ? 0 : rbdTableNo999.hashCode());
		result = prime * result + ((ticketDesignator == null) ? 0 : ticketDesignator.hashCode());
		result = prime * result + ((ticketDesignatorModifier == null) ? 0 : ticketDesignatorModifier.hashCode());
		result = prime * result + ((ticketingCode == null) ? 0 : ticketingCode.hashCode());
		result = prime * result + ((ticketingCodeModifier == null) ? 0 : ticketingCodeModifier.hashCode());
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
		AtpcoRecord1FareClassInformation other = (AtpcoRecord1FareClassInformation) obj;
		if (carrierTableNo990 == null) {
			if (other.carrierTableNo990 != null) {
				return false;
			}
		} else if (!carrierTableNo990.equals(other.carrierTableNo990)) {
			return false;
		}
		if (commercialName == null) {
			if (other.commercialName != null) {
				return false;
			}
		} else if (!commercialName.equals(other.commercialName)) {
			return false;
		}
		if (dateTableNo994 == null) {
			if (other.dateTableNo994 != null) {
				return false;
			}
		} else if (!dateTableNo994.equals(other.dateTableNo994)) {
			return false;
		}
		if (directionalIndicator == null) {
			if (other.directionalIndicator != null) {
				return false;
			}
		} else if (!directionalIndicator.equals(other.directionalIndicator)) {
			return false;
		}
		if (filler == null) {
			if (other.filler != null) {
				return false;
			}
		} else if (!filler.equals(other.filler)) {
			return false;
		}
		if (passengerMaxAge == null) {
			if (other.passengerMaxAge != null) {
				return false;
			}
		} else if (!passengerMaxAge.equals(other.passengerMaxAge)) {
			return false;
		}
		if (passengerMinAge == null) {
			if (other.passengerMinAge != null) {
				return false;
			}
		} else if (!passengerMinAge.equals(other.passengerMinAge)) {
			return false;
		}
		if (passengerType == null) {
			if (other.passengerType != null) {
				return false;
			}
		} else if (!passengerType.equals(other.passengerType)) {
			return false;
		}
		if (rbd == null) {
			if (other.rbd != null) {
				return false;
			}
		} else if (!rbd.equals(other.rbd)) {
			return false;
		}
		if (rbdTableNo999 == null) {
			if (other.rbdTableNo999 != null) {
				return false;
			}
		} else if (!rbdTableNo999.equals(other.rbdTableNo999)) {
			return false;
		}
		if (ticketDesignator == null) {
			if (other.ticketDesignator != null) {
				return false;
			}
		} else if (!ticketDesignator.equals(other.ticketDesignator)) {
			return false;
		}
		if (ticketDesignatorModifier == null) {
			if (other.ticketDesignatorModifier != null) {
				return false;
			}
		} else if (!ticketDesignatorModifier.equals(other.ticketDesignatorModifier)) {
			return false;
		}
		if (ticketingCode == null) {
			if (other.ticketingCode != null) {
				return false;
			}
		} else if (!ticketingCode.equals(other.ticketingCode)) {
			return false;
		}
		if (ticketingCodeModifier == null) {
			if (other.ticketingCodeModifier != null) {
				return false;
			}
		} else if (!ticketingCodeModifier.equals(other.ticketingCodeModifier)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoRecord1FareClassInformation [directionalIndicator=" + directionalIndicator + ", ticketingCode="
				+ ticketingCode + ", ticketingCodeModifier=" + ticketingCodeModifier + ", ticketDesignator="
				+ ticketDesignator + ", ticketDesignatorModifier=" + ticketDesignatorModifier + ", passengerType="
				+ passengerType + ", passengerMinAge=" + passengerMinAge + ", passengerMaxAge=" + passengerMaxAge
				+ ", filler=" + filler + ", dateTableNo994=" + dateTableNo994 + ", rbd=" + rbd + ", carrierTableNo990="
				+ carrierTableNo990 + ", rbdTableNo999=" + rbdTableNo999 + ", commercialName=" + commercialName + "]";
	}
}
