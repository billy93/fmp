package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class TaxServiceBaggage {

	@Field("service_baggage_app_tag")
	private String service_baggage_app_tag;

	@Field("service_baggage_tbl_no_168")
    private String service_baggage_tbl_no_168;

	public String getService_baggage_app_tag() {
		return service_baggage_app_tag;
	}

	public void setService_baggage_app_tag(String service_baggage_app_tag) {
		this.service_baggage_app_tag = service_baggage_app_tag;
	}

	public String getService_baggage_tbl_no_168() {
		return service_baggage_tbl_no_168;
	}

	public void setService_baggage_tbl_no_168(String service_baggage_tbl_no_168) {
		this.service_baggage_tbl_no_168 = service_baggage_tbl_no_168;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((service_baggage_app_tag == null) ? 0 : service_baggage_app_tag.hashCode());
		result = prime * result + ((service_baggage_tbl_no_168 == null) ? 0 : service_baggage_tbl_no_168.hashCode());
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
		TaxServiceBaggage other = (TaxServiceBaggage) obj;
		if (service_baggage_app_tag == null) {
			if (other.service_baggage_app_tag != null) {
				return false;
			}
		} else if (!service_baggage_app_tag.equals(other.service_baggage_app_tag)) {
			return false;
		}
		if (service_baggage_tbl_no_168 == null) {
			if (other.service_baggage_tbl_no_168 != null) {
				return false;
			}
		} else if (!service_baggage_tbl_no_168.equals(other.service_baggage_tbl_no_168)) {
			return false;
		}
		return true;
	}
}
