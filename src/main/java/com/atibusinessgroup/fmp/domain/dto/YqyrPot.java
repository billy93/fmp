package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class YqyrPot {

	@Field("pot_geo_spec_value")
	private String pot_geo_spec_value;

	@Field("pot_geo_spec_type")
    private String pot_geo_spec_type;

	public String getPot_geo_spec_value() {
		return pot_geo_spec_value;
	}

	public void setPot_geo_spec_value(String pot_geo_spec_value) {
		this.pot_geo_spec_value = pot_geo_spec_value;
	}

	public String getPot_geo_spec_type() {
		return pot_geo_spec_type;
	}

	public void setPot_geo_spec_type(String pot_geo_spec_type) {
		this.pot_geo_spec_type = pot_geo_spec_type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pot_geo_spec_type == null) ? 0 : pot_geo_spec_type.hashCode());
		result = prime * result + ((pot_geo_spec_value == null) ? 0 : pot_geo_spec_value.hashCode());
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
		YqyrPot other = (YqyrPot) obj;
		if (pot_geo_spec_type == null) {
			if (other.pot_geo_spec_type != null) {
				return false;
			}
		} else if (!pot_geo_spec_type.equals(other.pot_geo_spec_type)) {
			return false;
		}
		if (pot_geo_spec_value == null) {
			if (other.pot_geo_spec_value != null) {
				return false;
			}
		} else if (!pot_geo_spec_value.equals(other.pot_geo_spec_value)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "YqyrPot [pot_geo_spec_value=" + pot_geo_spec_value + ", pot_geo_spec_type=" + pot_geo_spec_type + "]";
	}
}
