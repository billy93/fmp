package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class TaxJourneyGeoSpec {

	@Field("jrny_geo_spec_1_type")
	private String jrny_geo_spec_1_type;

	@Field("jrny_geo_spec_journey_value")
    private String jrny_geo_spec_journey_value;

	@Field("jrny_geo_spec_journey_type")
    private String jrny_geo_spec_journey_type;

	@Field("jrny_geo_spec_indicator")
    private String jrny_geo_spec_indicator;

	@Field("jrny_geo_spec_travel_wholly_type")
    private String jrny_geo_spec_travel_wholly_type;

	@Field("jrny_geo_spec_travel_wholly_value")
    private String jrny_geo_spec_travel_wholly_value;

	@Field("jrny_geo_spec_2_type")
    private String jrny_geo_spec_2_type;

	@Field("jrny_geo_spec_1_value")
    private String jrny_geo_spec_1_value;

	@Field("jrny_geo_spec_2_value")
    private String jrny_geo_spec_2_value;

	public String getJrny_geo_spec_1_type() {
		return jrny_geo_spec_1_type;
	}

	public void setJrny_geo_spec_1_type(String jrny_geo_spec_1_type) {
		this.jrny_geo_spec_1_type = jrny_geo_spec_1_type;
	}

	public String getJrny_geo_spec_journey_value() {
		return jrny_geo_spec_journey_value;
	}

	public void setJrny_geo_spec_journey_value(String jrny_geo_spec_journey_value) {
		this.jrny_geo_spec_journey_value = jrny_geo_spec_journey_value;
	}

	public String getJrny_geo_spec_journey_type() {
		return jrny_geo_spec_journey_type;
	}

	public void setJrny_geo_spec_journey_type(String jrny_geo_spec_journey_type) {
		this.jrny_geo_spec_journey_type = jrny_geo_spec_journey_type;
	}

	public String getJrny_geo_spec_indicator() {
		return jrny_geo_spec_indicator;
	}

	public void setJrny_geo_spec_indicator(String jrny_geo_spec_indicator) {
		this.jrny_geo_spec_indicator = jrny_geo_spec_indicator;
	}

	public String getJrny_geo_spec_travel_wholly_type() {
		return jrny_geo_spec_travel_wholly_type;
	}

	public void setJrny_geo_spec_travel_wholly_type(String jrny_geo_spec_travel_wholly_type) {
		this.jrny_geo_spec_travel_wholly_type = jrny_geo_spec_travel_wholly_type;
	}

	public String getJrny_geo_spec_travel_wholly_value() {
		return jrny_geo_spec_travel_wholly_value;
	}

	public void setJrny_geo_spec_travel_wholly_value(String jrny_geo_spec_travel_wholly_value) {
		this.jrny_geo_spec_travel_wholly_value = jrny_geo_spec_travel_wholly_value;
	}

	public String getJrny_geo_spec_2_type() {
		return jrny_geo_spec_2_type;
	}

	public void setJrny_geo_spec_2_type(String jrny_geo_spec_2_type) {
		this.jrny_geo_spec_2_type = jrny_geo_spec_2_type;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jrny_geo_spec_1_type == null) ? 0 : jrny_geo_spec_1_type.hashCode());
		result = prime * result + ((jrny_geo_spec_1_value == null) ? 0 : jrny_geo_spec_1_value.hashCode());
		result = prime * result + ((jrny_geo_spec_2_type == null) ? 0 : jrny_geo_spec_2_type.hashCode());
		result = prime * result + ((jrny_geo_spec_2_value == null) ? 0 : jrny_geo_spec_2_value.hashCode());
		result = prime * result + ((jrny_geo_spec_indicator == null) ? 0 : jrny_geo_spec_indicator.hashCode());
		result = prime * result + ((jrny_geo_spec_journey_type == null) ? 0 : jrny_geo_spec_journey_type.hashCode());
		result = prime * result + ((jrny_geo_spec_journey_value == null) ? 0 : jrny_geo_spec_journey_value.hashCode());
		result = prime * result
				+ ((jrny_geo_spec_travel_wholly_type == null) ? 0 : jrny_geo_spec_travel_wholly_type.hashCode());
		result = prime * result
				+ ((jrny_geo_spec_travel_wholly_value == null) ? 0 : jrny_geo_spec_travel_wholly_value.hashCode());
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
		TaxJourneyGeoSpec other = (TaxJourneyGeoSpec) obj;
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
		if (jrny_geo_spec_journey_type == null) {
			if (other.jrny_geo_spec_journey_type != null) {
				return false;
			}
		} else if (!jrny_geo_spec_journey_type.equals(other.jrny_geo_spec_journey_type)) {
			return false;
		}
		if (jrny_geo_spec_journey_value == null) {
			if (other.jrny_geo_spec_journey_value != null) {
				return false;
			}
		} else if (!jrny_geo_spec_journey_value.equals(other.jrny_geo_spec_journey_value)) {
			return false;
		}
		if (jrny_geo_spec_travel_wholly_type == null) {
			if (other.jrny_geo_spec_travel_wholly_type != null) {
				return false;
			}
		} else if (!jrny_geo_spec_travel_wholly_type.equals(other.jrny_geo_spec_travel_wholly_type)) {
			return false;
		}
		if (jrny_geo_spec_travel_wholly_value == null) {
			if (other.jrny_geo_spec_travel_wholly_value != null) {
				return false;
			}
		} else if (!jrny_geo_spec_travel_wholly_value.equals(other.jrny_geo_spec_travel_wholly_value)) {
			return false;
		}
		return true;
	}
}
