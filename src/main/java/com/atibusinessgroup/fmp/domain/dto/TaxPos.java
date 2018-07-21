package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class TaxPos {

	@Field("pos_geo_value")
	private String pos_geo_value;

	@Field("pos_geo_type")
    private String pos_geo_type;

	public String getPos_geo_value() {
		return pos_geo_value;
	}

	public void setPos_geo_value(String pos_geo_value) {
		this.pos_geo_value = pos_geo_value;
	}

	public String getPos_geo_type() {
		return pos_geo_type;
	}

	public void setPos_geo_type(String pos_geo_type) {
		this.pos_geo_type = pos_geo_type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pos_geo_type == null) ? 0 : pos_geo_type.hashCode());
		result = prime * result + ((pos_geo_value == null) ? 0 : pos_geo_value.hashCode());
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
		TaxPos other = (TaxPos) obj;
		if (pos_geo_type == null) {
			if (other.pos_geo_type != null) {
				return false;
			}
		} else if (!pos_geo_type.equals(other.pos_geo_type)) {
			return false;
		}
		if (pos_geo_value == null) {
			if (other.pos_geo_value != null) {
				return false;
			}
		} else if (!pos_geo_value.equals(other.pos_geo_value)) {
			return false;
		}
		return true;
	}
}
