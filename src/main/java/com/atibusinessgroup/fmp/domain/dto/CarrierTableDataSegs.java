package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class CarrierTableDataSegs {

	@Field("flt_no_2")
	private String flt_no_2;

	@Field("flt_no_1")
    private String flt_no_1;

	@Field("operating_carrier")
    private String operating_carrier;

	@Field("marketing_carrier")
    private String marketing_carrier;

	public String getFlt_no_2() {
		return flt_no_2;
	}

	public void setFlt_no_2(String flt_no_2) {
		this.flt_no_2 = flt_no_2;
	}

	public String getFlt_no_1() {
		return flt_no_1;
	}

	public void setFlt_no_1(String flt_no_1) {
		this.flt_no_1 = flt_no_1;
	}

	public String getOperating_carrier() {
		return operating_carrier;
	}

	public void setOperating_carrier(String operating_carrier) {
		this.operating_carrier = operating_carrier;
	}

	public String getMarketing_carrier() {
		return marketing_carrier;
	}

	public void setMarketing_carrier(String marketing_carrier) {
		this.marketing_carrier = marketing_carrier;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flt_no_1 == null) ? 0 : flt_no_1.hashCode());
		result = prime * result + ((flt_no_2 == null) ? 0 : flt_no_2.hashCode());
		result = prime * result + ((marketing_carrier == null) ? 0 : marketing_carrier.hashCode());
		result = prime * result + ((operating_carrier == null) ? 0 : operating_carrier.hashCode());
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
		CarrierTableDataSegs other = (CarrierTableDataSegs) obj;
		if (flt_no_1 == null) {
			if (other.flt_no_1 != null) {
				return false;
			}
		} else if (!flt_no_1.equals(other.flt_no_1)) {
			return false;
		}
		if (flt_no_2 == null) {
			if (other.flt_no_2 != null) {
				return false;
			}
		} else if (!flt_no_2.equals(other.flt_no_2)) {
			return false;
		}
		if (marketing_carrier == null) {
			if (other.marketing_carrier != null) {
				return false;
			}
		} else if (!marketing_carrier.equals(other.marketing_carrier)) {
			return false;
		}
		if (operating_carrier == null) {
			if (other.operating_carrier != null) {
				return false;
			}
		} else if (!operating_carrier.equals(other.operating_carrier)) {
			return false;
		}
		return true;
	}
}
