package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class YqyrJourneyGeoSpec {

	@Field("jrny_geo_spec_1_type")
	private String jrny_geo_spec_1_type;

	@Field("jrny_geo_spec_via_loc_value")
    private String jrny_geo_spec_via_loc_value;

	@Field("jrny_geo_spec_indicator")
    private String jrny_geo_spec_indicator;

	@Field("jrny_geo_spec_within_loc_value")
    private String jrny_geo_spec_within_loc_value;

	@Field("jrny_geo_spec_2_type")
    private String jrny_geo_spec_2_type;

	@Field("jrny_geo_spec_within_loc_type")
    private String jrny_geo_spec_within_loc_type;

	@Field("jrny_geo_spec_1_value")
    private String jrny_geo_spec_1_value;

	@Field("jrny_geo_spec_2_value")
    private String jrny_geo_spec_2_value;

	@Field("jrny_geo_spec_via_loc_type")
    private String jrny_geo_spec_via_loc_type;

	public String getJrny_geo_spec_1_type() {
		return jrny_geo_spec_1_type;
	}

	public void setJrny_geo_spec_1_type(String jrny_geo_spec_1_type) {
		this.jrny_geo_spec_1_type = jrny_geo_spec_1_type;
	}

	public String getJrny_geo_spec_via_loc_value() {
		return jrny_geo_spec_via_loc_value;
	}

	public void setJrny_geo_spec_via_loc_value(String jrny_geo_spec_via_loc_value) {
		this.jrny_geo_spec_via_loc_value = jrny_geo_spec_via_loc_value;
	}

	public String getJrny_geo_spec_indicator() {
		return jrny_geo_spec_indicator;
	}

	public void setJrny_geo_spec_indicator(String jrny_geo_spec_indicator) {
		this.jrny_geo_spec_indicator = jrny_geo_spec_indicator;
	}

	public String getJrny_geo_spec_within_loc_value() {
		return jrny_geo_spec_within_loc_value;
	}

	public void setJrny_geo_spec_within_loc_value(String jrny_geo_spec_within_loc_value) {
		this.jrny_geo_spec_within_loc_value = jrny_geo_spec_within_loc_value;
	}

	public String getJrny_geo_spec_2_type() {
		return jrny_geo_spec_2_type;
	}

	public void setJrny_geo_spec_2_type(String jrny_geo_spec_2_type) {
		this.jrny_geo_spec_2_type = jrny_geo_spec_2_type;
	}

	public String getJrny_geo_spec_within_loc_type() {
		return jrny_geo_spec_within_loc_type;
	}

	public void setJrny_geo_spec_within_loc_type(String jrny_geo_spec_within_loc_type) {
		this.jrny_geo_spec_within_loc_type = jrny_geo_spec_within_loc_type;
	}

	public String getJrny_geo_spec_1_value() {
		return jrny_geo_spec_1_value;
	}

	public void setJrny_geo_spec_1_value(String jrny_geo_spec_1_value) {
		this.jrny_geo_spec_1_value = jrny_geo_spec_1_value;
	}

	public String getJrny_geo_spec_2_value() {
		return jrny_geo_spec_2_value;
	}

	public void setJrny_geo_spec_2_value(String jrny_geo_spec_2_value) {
		this.jrny_geo_spec_2_value = jrny_geo_spec_2_value;
	}

	public String getJrny_geo_spec_via_loc_type() {
		return jrny_geo_spec_via_loc_type;
	}

	public void setJrny_geo_spec_via_loc_type(String jrny_geo_spec_via_loc_type) {
		this.jrny_geo_spec_via_loc_type = jrny_geo_spec_via_loc_type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jrny_geo_spec_1_type == null) ? 0 : jrny_geo_spec_1_type.hashCode());
		result = prime * result + ((jrny_geo_spec_1_value == null) ? 0 : jrny_geo_spec_1_value.hashCode());
		result = prime * result + ((jrny_geo_spec_2_type == null) ? 0 : jrny_geo_spec_2_type.hashCode());
		result = prime * result + ((jrny_geo_spec_2_value == null) ? 0 : jrny_geo_spec_2_value.hashCode());
		result = prime * result + ((jrny_geo_spec_indicator == null) ? 0 : jrny_geo_spec_indicator.hashCode());
		result = prime * result + ((jrny_geo_spec_via_loc_type == null) ? 0 : jrny_geo_spec_via_loc_type.hashCode());
		result = prime * result + ((jrny_geo_spec_via_loc_value == null) ? 0 : jrny_geo_spec_via_loc_value.hashCode());
		result = prime * result
				+ ((jrny_geo_spec_within_loc_type == null) ? 0 : jrny_geo_spec_within_loc_type.hashCode());
		result = prime * result
				+ ((jrny_geo_spec_within_loc_value == null) ? 0 : jrny_geo_spec_within_loc_value.hashCode());
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
		YqyrJourneyGeoSpec other = (YqyrJourneyGeoSpec) obj;
		if (jrny_geo_spec_1_type == null) {
			if (other.jrny_geo_spec_1_type != null) {
				return false;
			}
		} else if (!jrny_geo_spec_1_type.equals(other.jrny_geo_spec_1_type)) {
			return false;
		}
		if (jrny_geo_spec_1_value == null) {
			if (other.jrny_geo_spec_1_value != null) {
				return false;
			}
		} else if (!jrny_geo_spec_1_value.equals(other.jrny_geo_spec_1_value)) {
			return false;
		}
		if (jrny_geo_spec_2_type == null) {
			if (other.jrny_geo_spec_2_type != null) {
				return false;
			}
		} else if (!jrny_geo_spec_2_type.equals(other.jrny_geo_spec_2_type)) {
			return false;
		}
		if (jrny_geo_spec_2_value == null) {
			if (other.jrny_geo_spec_2_value != null) {
				return false;
			}
		} else if (!jrny_geo_spec_2_value.equals(other.jrny_geo_spec_2_value)) {
			return false;
		}
		if (jrny_geo_spec_indicator == null) {
			if (other.jrny_geo_spec_indicator != null) {
				return false;
			}
		} else if (!jrny_geo_spec_indicator.equals(other.jrny_geo_spec_indicator)) {
			return false;
		}
		if (jrny_geo_spec_via_loc_type == null) {
			if (other.jrny_geo_spec_via_loc_type != null) {
				return false;
			}
		} else if (!jrny_geo_spec_via_loc_type.equals(other.jrny_geo_spec_via_loc_type)) {
			return false;
		}
		if (jrny_geo_spec_via_loc_value == null) {
			if (other.jrny_geo_spec_via_loc_value != null) {
				return false;
			}
		} else if (!jrny_geo_spec_via_loc_value.equals(other.jrny_geo_spec_via_loc_value)) {
			return false;
		}
		if (jrny_geo_spec_within_loc_type == null) {
			if (other.jrny_geo_spec_within_loc_type != null) {
				return false;
			}
		} else if (!jrny_geo_spec_within_loc_type.equals(other.jrny_geo_spec_within_loc_type)) {
			return false;
		}
		if (jrny_geo_spec_within_loc_value == null) {
			if (other.jrny_geo_spec_within_loc_value != null) {
				return false;
			}
		} else if (!jrny_geo_spec_within_loc_value.equals(other.jrny_geo_spec_within_loc_value)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "YqyrJourneyGeoSpec [jrny_geo_spec_1_type=" + jrny_geo_spec_1_type + ", jrny_geo_spec_via_loc_value="
				+ jrny_geo_spec_via_loc_value + ", jrny_geo_spec_indicator=" + jrny_geo_spec_indicator
				+ ", jrny_geo_spec_within_loc_value=" + jrny_geo_spec_within_loc_value + ", jrny_geo_spec_2_type="
				+ jrny_geo_spec_2_type + ", jrny_geo_spec_within_loc_type=" + jrny_geo_spec_within_loc_type
				+ ", jrny_geo_spec_1_value=" + jrny_geo_spec_1_value + ", jrny_geo_spec_2_value="
				+ jrny_geo_spec_2_value + ", jrny_geo_spec_via_loc_type=" + jrny_geo_spec_via_loc_type + "]";
	}
}
