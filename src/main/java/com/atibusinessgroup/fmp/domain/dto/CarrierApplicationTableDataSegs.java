package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class CarrierApplicationTableDataSegs {

	@Field("cxr")
	private String cxr;

	@Field("appl")
    private String appl;

	public String getCxr() {
		return cxr;
	}

	public void setCxr(String cxr) {
		this.cxr = cxr;
	}

	public String getAppl() {
		return appl;
	}

	public void setAppl(String appl) {
		this.appl = appl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appl == null) ? 0 : appl.hashCode());
		result = prime * result + ((cxr == null) ? 0 : cxr.hashCode());
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
		CarrierApplicationTableDataSegs other = (CarrierApplicationTableDataSegs) obj;
		if (appl == null) {
			if (other.appl != null) {
				return false;
			}
		} else if (!appl.equals(other.appl)) {
			return false;
		}
		if (cxr == null) {
			if (other.cxr != null) {
				return false;
			}
		} else if (!cxr.equals(other.cxr)) {
			return false;
		}
		return true;
	}
}
