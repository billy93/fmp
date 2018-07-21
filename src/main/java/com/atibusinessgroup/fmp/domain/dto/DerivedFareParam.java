package com.atibusinessgroup.fmp.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class DerivedFareParam {

	private String paxType;
	private int paxAge;
	private String accountCode;
	private String ticketDesignator;
	private List<SpecifiedConstructed> fares = new ArrayList<>();
	
	public DerivedFareParam() {
		
	}

	public String getPaxType() {
		return paxType;
	}

	public void setPaxType(String paxType) {
		this.paxType = paxType;
	}

	public int getPaxAge() {
		return paxAge;
	}

	public void setPaxAge(int paxAge) {
		this.paxAge = paxAge;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getTicketDesignator() {
		return ticketDesignator;
	}

	public void setTicketDesignator(String ticketDesignator) {
		this.ticketDesignator = ticketDesignator;
	}

	public List<SpecifiedConstructed> getFares() {
		return fares;
	}

	public void setFares(List<SpecifiedConstructed> fares) {
		this.fares = fares;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountCode == null) ? 0 : accountCode.hashCode());
		result = prime * result + ((fares == null) ? 0 : fares.hashCode());
		result = prime * result + paxAge;
		result = prime * result + ((paxType == null) ? 0 : paxType.hashCode());
		result = prime * result + ((ticketDesignator == null) ? 0 : ticketDesignator.hashCode());
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
		DerivedFareParam other = (DerivedFareParam) obj;
		if (accountCode == null) {
			if (other.accountCode != null) {
				return false;
			}
		} else if (!accountCode.equals(other.accountCode)) {
			return false;
		}
		if (fares == null) {
			if (other.fares != null) {
				return false;
			}
		} else if (!fares.equals(other.fares)) {
			return false;
		}
		if (paxAge != other.paxAge) {
			return false;
		}
		if (paxType == null) {
			if (other.paxType != null) {
				return false;
			}
		} else if (!paxType.equals(other.paxType)) {
			return false;
		}
		if (ticketDesignator == null) {
			if (other.ticketDesignator != null) {
				return false;
			}
		} else if (!ticketDesignator.equals(other.ticketDesignator)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "DerivedFareParam [paxType=" + paxType + ", paxAge=" + paxAge + ", accountCode=" + accountCode
				+ ", ticketDesignator=" + ticketDesignator + ", fares=" + fares + "]";
	}
}
