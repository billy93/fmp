package com.atibusinessgroup.fmp.domain.dto;

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
	
	@Field("rbd_1")
    private String rbd1;
	
	@Field("rbd_2")
    private String rbd2;
	
	@Field("rbd_3")
    private String rbd3;
	
	@Field("rbd_4")
    private String rbd4;
	
	@Field("rbd_5")
    private String rbd5;
	
	@Field("rbd_6")
    private String rbd6;
	
	@Field("rbd_7")
    private String rbd7;
	
	@Field("rbd_8")
    private String rbd8;
	
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

	public String getRbd1() {
		return rbd1;
	}

	public void setRbd1(String rbd1) {
		this.rbd1 = rbd1;
	}

	public String getRbd2() {
		return rbd2;
	}

	public void setRbd2(String rbd2) {
		this.rbd2 = rbd2;
	}

	public String getRbd3() {
		return rbd3;
	}

	public void setRbd3(String rbd3) {
		this.rbd3 = rbd3;
	}

	public String getRbd4() {
		return rbd4;
	}

	public void setRbd4(String rbd4) {
		this.rbd4 = rbd4;
	}

	public String getRbd5() {
		return rbd5;
	}

	public void setRbd5(String rbd5) {
		this.rbd5 = rbd5;
	}

	public String getRbd6() {
		return rbd6;
	}

	public void setRbd6(String rbd6) {
		this.rbd6 = rbd6;
	}

	public String getRbd7() {
		return rbd7;
	}

	public void setRbd7(String rbd7) {
		this.rbd7 = rbd7;
	}

	public String getRbd8() {
		return rbd8;
	}

	public void setRbd8(String rbd8) {
		this.rbd8 = rbd8;
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
		result = prime * result + ((rbd1 == null) ? 0 : rbd1.hashCode());
		result = prime * result + ((rbd2 == null) ? 0 : rbd2.hashCode());
		result = prime * result + ((rbd3 == null) ? 0 : rbd3.hashCode());
		result = prime * result + ((rbd4 == null) ? 0 : rbd4.hashCode());
		result = prime * result + ((rbd5 == null) ? 0 : rbd5.hashCode());
		result = prime * result + ((rbd6 == null) ? 0 : rbd6.hashCode());
		result = prime * result + ((rbd7 == null) ? 0 : rbd7.hashCode());
		result = prime * result + ((rbd8 == null) ? 0 : rbd8.hashCode());
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
		if (rbd1 == null) {
			if (other.rbd1 != null) {
				return false;
			}
		} else if (!rbd1.equals(other.rbd1)) {
			return false;
		}
		if (rbd2 == null) {
			if (other.rbd2 != null) {
				return false;
			}
		} else if (!rbd2.equals(other.rbd2)) {
			return false;
		}
		if (rbd3 == null) {
			if (other.rbd3 != null) {
				return false;
			}
		} else if (!rbd3.equals(other.rbd3)) {
			return false;
		}
		if (rbd4 == null) {
			if (other.rbd4 != null) {
				return false;
			}
		} else if (!rbd4.equals(other.rbd4)) {
			return false;
		}
		if (rbd5 == null) {
			if (other.rbd5 != null) {
				return false;
			}
		} else if (!rbd5.equals(other.rbd5)) {
			return false;
		}
		if (rbd6 == null) {
			if (other.rbd6 != null) {
				return false;
			}
		} else if (!rbd6.equals(other.rbd6)) {
			return false;
		}
		if (rbd7 == null) {
			if (other.rbd7 != null) {
				return false;
			}
		} else if (!rbd7.equals(other.rbd7)) {
			return false;
		}
		if (rbd8 == null) {
			if (other.rbd8 != null) {
				return false;
			}
		} else if (!rbd8.equals(other.rbd8)) {
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
				+ ", filler=" + filler + ", dateTableNo994=" + dateTableNo994 + ", rbd1=" + rbd1 + ", rbd2=" + rbd2
				+ ", rbd3=" + rbd3 + ", rbd4=" + rbd4 + ", rbd5=" + rbd5 + ", rbd6=" + rbd6 + ", rbd7=" + rbd7
				+ ", rbd8=" + rbd8 + ", carrierTableNo990=" + carrierTableNo990 + ", rbdTableNo999=" + rbdTableNo999
				+ ", commercialName=" + commercialName + "]";
	}
}
