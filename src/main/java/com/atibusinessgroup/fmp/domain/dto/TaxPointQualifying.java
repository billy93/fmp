package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class TaxPointQualifying {

	@Field("tax_point_qualifying_stopover_time_tag")
	private String tax_point_qualifying_stopover_time_tag;

	@Field("tax_point_qualifying_connection_tag")
    private String tax_point_qualifying_connection_tag;

	@Field("tax_point_qualifying_stopover_time_unit")
    private String tax_point_qualifying_stopover_time_unit;

	public String getTax_point_qualifying_stopover_time_tag() {
		return tax_point_qualifying_stopover_time_tag;
	}

	public void setTax_point_qualifying_stopover_time_tag(String tax_point_qualifying_stopover_time_tag) {
		this.tax_point_qualifying_stopover_time_tag = tax_point_qualifying_stopover_time_tag;
	}

	public String getTax_point_qualifying_connection_tag() {
		return tax_point_qualifying_connection_tag;
	}

	public void setTax_point_qualifying_connection_tag(String tax_point_qualifying_connection_tag) {
		this.tax_point_qualifying_connection_tag = tax_point_qualifying_connection_tag;
	}

	public String getTax_point_qualifying_stopover_time_unit() {
		return tax_point_qualifying_stopover_time_unit;
	}

	public void setTax_point_qualifying_stopover_time_unit(String tax_point_qualifying_stopover_time_unit) {
		this.tax_point_qualifying_stopover_time_unit = tax_point_qualifying_stopover_time_unit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((tax_point_qualifying_connection_tag == null) ? 0 : tax_point_qualifying_connection_tag.hashCode());
		result = prime * result + ((tax_point_qualifying_stopover_time_tag == null) ? 0
				: tax_point_qualifying_stopover_time_tag.hashCode());
		result = prime * result + ((tax_point_qualifying_stopover_time_unit == null) ? 0
				: tax_point_qualifying_stopover_time_unit.hashCode());
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
		TaxPointQualifying other = (TaxPointQualifying) obj;
		if (tax_point_qualifying_connection_tag == null) {
			if (other.tax_point_qualifying_connection_tag != null) {
				return false;
			}
		} else if (!tax_point_qualifying_connection_tag.equals(other.tax_point_qualifying_connection_tag)) {
			return false;
		}
		if (tax_point_qualifying_stopover_time_tag == null) {
			if (other.tax_point_qualifying_stopover_time_tag != null) {
				return false;
			}
		} else if (!tax_point_qualifying_stopover_time_tag.equals(other.tax_point_qualifying_stopover_time_tag)) {
			return false;
		}
		if (tax_point_qualifying_stopover_time_unit == null) {
			if (other.tax_point_qualifying_stopover_time_unit != null) {
				return false;
			}
		} else if (!tax_point_qualifying_stopover_time_unit.equals(other.tax_point_qualifying_stopover_time_unit)) {
			return false;
		}
		return true;
	}
}
