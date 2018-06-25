package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class CarrierField {
	
	@Field("marketing_carrier")
	private String marketingCarrier;
	
	@Field("operating_carrier")
	private String operatingCarrier;
	
	@Field("flt_no_1")
	private String flightNo1;
	
	@Field("flt_no_2")
	private String flightNo2;

	public String getMarketingCarrier() {
		return marketingCarrier;
	}

	public void setMarketingCarrier(String marketingCarrier) {
		this.marketingCarrier = marketingCarrier;
	}

	public String getOperatingCarrier() {
		return operatingCarrier;
	}

	public void setOperatingCarrier(String operatingCarrier) {
		this.operatingCarrier = operatingCarrier;
	}

	public String getFlightNo1() {
		return flightNo1;
	}

	public void setFlightNo1(String flightNo1) {
		this.flightNo1 = flightNo1;
	}

	public String getFlightNo2() {
		return flightNo2;
	}

	public void setFlightNo2(String flightNo2) {
		this.flightNo2 = flightNo2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flightNo1 == null) ? 0 : flightNo1.hashCode());
		result = prime * result + ((flightNo2 == null) ? 0 : flightNo2.hashCode());
		result = prime * result + ((marketingCarrier == null) ? 0 : marketingCarrier.hashCode());
		result = prime * result + ((operatingCarrier == null) ? 0 : operatingCarrier.hashCode());
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
		CarrierField other = (CarrierField) obj;
		if (flightNo1 == null) {
			if (other.flightNo1 != null) {
				return false;
			}
		} else if (!flightNo1.equals(other.flightNo1)) {
			return false;
		}
		if (flightNo2 == null) {
			if (other.flightNo2 != null) {
				return false;
			}
		} else if (!flightNo2.equals(other.flightNo2)) {
			return false;
		}
		if (marketingCarrier == null) {
			if (other.marketingCarrier != null) {
				return false;
			}
		} else if (!marketingCarrier.equals(other.marketingCarrier)) {
			return false;
		}
		if (operatingCarrier == null) {
			if (other.operatingCarrier != null) {
				return false;
			}
		} else if (!operatingCarrier.equals(other.operatingCarrier)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "CarrierField [marketingCarrier=" + marketingCarrier + ", operatingCarrier=" + operatingCarrier
				+ ", flightNo1=" + flightNo1 + ", flightNo2=" + flightNo2 + "]";
	}
}
