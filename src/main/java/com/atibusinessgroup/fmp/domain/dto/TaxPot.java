package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class TaxPot {

	@Field("pot_geo_value")
	private String pot_geo_value;

	@Field("pot_geo_type")
    private String pot_geo_type;

	public String getPot_geo_value() {
		return pot_geo_value;
	}

	public void setPot_geo_value(String pot_geo_value) {
		this.pot_geo_value = pot_geo_value;
	}

	public String getPot_geo_type() {
		return pot_geo_type;
	}

	public void setPot_geo_type(String pot_geo_type) {
		this.pot_geo_type = pot_geo_type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pot_geo_type == null) ? 0 : pot_geo_type.hashCode());
		result = prime * result + ((pot_geo_value == null) ? 0 : pot_geo_value.hashCode());
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
		TaxPot other = (TaxPot) obj;
		if (pot_geo_type == null) {
			if (other.pot_geo_type != null) {
				return false;
			}
		} else if (!pot_geo_type.equals(other.pot_geo_type)) {
			return false;
		}
		if (pot_geo_value == null) {
			if (other.pot_geo_value != null) {
				return false;
			}
		} else if (!pot_geo_value.equals(other.pot_geo_value)) {
			return false;
		}
		return true;
	}
}
