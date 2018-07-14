package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class YqyrSector {

	@Field("sector_via_geo_value")
	private String sector_via_geo_value;

	@Field("sector_via_cnx_exempt")
    private String sector_via_cnx_exempt;

	@Field("sector_loc_1_type")
    private String sector_loc_1_type;

	@Field("sector_via_exc_stop_time")
    private String sector_via_exc_stop_time;

	@Field("sector_via_exc_stop_unit")
    private String sector_via_exc_stop_unit;

	@Field("sector_indicator")
    private String sector_indicator;

	@Field("sector_via_stp")
    private String sector_via_stp;

	@Field("sector_loc_1_value")
    private String sector_loc_1_value;

	@Field("sector_loc_2_type")
    private String sector_loc_2_type;

	@Field("sector_via_geo_type")
    private String sector_via_geo_type;

	@Field("sector_loc_2_value")
    private String sector_loc_2_value;

	@Field("sector_via_intl")
    private String sector_via_intl;

	@Field("sector_from_to")
    private String sector_from_to;

	public String getSector_via_geo_value() {
		return sector_via_geo_value;
	}

	public void setSector_via_geo_value(String sector_via_geo_value) {
		this.sector_via_geo_value = sector_via_geo_value;
	}

	public String getSector_via_cnx_exempt() {
		return sector_via_cnx_exempt;
	}

	public void setSector_via_cnx_exempt(String sector_via_cnx_exempt) {
		this.sector_via_cnx_exempt = sector_via_cnx_exempt;
	}

	public String getSector_loc_1_type() {
		return sector_loc_1_type;
	}

	public void setSector_loc_1_type(String sector_loc_1_type) {
		this.sector_loc_1_type = sector_loc_1_type;
	}

	public String getSector_via_exc_stop_time() {
		return sector_via_exc_stop_time;
	}

	public void setSector_via_exc_stop_time(String sector_via_exc_stop_time) {
		this.sector_via_exc_stop_time = sector_via_exc_stop_time;
	}

	public String getSector_via_exc_stop_unit() {
		return sector_via_exc_stop_unit;
	}

	public void setSector_via_exc_stop_unit(String sector_via_exc_stop_unit) {
		this.sector_via_exc_stop_unit = sector_via_exc_stop_unit;
	}

	public String getSector_indicator() {
		return sector_indicator;
	}

	public void setSector_indicator(String sector_indicator) {
		this.sector_indicator = sector_indicator;
	}

	public String getSector_via_stp() {
		return sector_via_stp;
	}

	public void setSector_via_stp(String sector_via_stp) {
		this.sector_via_stp = sector_via_stp;
	}

	public String getSector_loc_1_value() {
		return sector_loc_1_value;
	}

	public void setSector_loc_1_value(String sector_loc_1_value) {
		this.sector_loc_1_value = sector_loc_1_value;
	}

	public String getSector_loc_2_type() {
		return sector_loc_2_type;
	}

	public void setSector_loc_2_type(String sector_loc_2_type) {
		this.sector_loc_2_type = sector_loc_2_type;
	}

	public String getSector_via_geo_type() {
		return sector_via_geo_type;
	}

	public void setSector_via_geo_type(String sector_via_geo_type) {
		this.sector_via_geo_type = sector_via_geo_type;
	}

	public String getSector_loc_2_value() {
		return sector_loc_2_value;
	}

	public void setSector_loc_2_value(String sector_loc_2_value) {
		this.sector_loc_2_value = sector_loc_2_value;
	}

	public String getSector_via_intl() {
		return sector_via_intl;
	}

	public void setSector_via_intl(String sector_via_intl) {
		this.sector_via_intl = sector_via_intl;
	}

	public String getSector_from_to() {
		return sector_from_to;
	}

	public void setSector_from_to(String sector_from_to) {
		this.sector_from_to = sector_from_to;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sector_from_to == null) ? 0 : sector_from_to.hashCode());
		result = prime * result + ((sector_indicator == null) ? 0 : sector_indicator.hashCode());
		result = prime * result + ((sector_loc_1_type == null) ? 0 : sector_loc_1_type.hashCode());
		result = prime * result + ((sector_loc_1_value == null) ? 0 : sector_loc_1_value.hashCode());
		result = prime * result + ((sector_loc_2_type == null) ? 0 : sector_loc_2_type.hashCode());
		result = prime * result + ((sector_loc_2_value == null) ? 0 : sector_loc_2_value.hashCode());
		result = prime * result + ((sector_via_cnx_exempt == null) ? 0 : sector_via_cnx_exempt.hashCode());
		result = prime * result + ((sector_via_exc_stop_time == null) ? 0 : sector_via_exc_stop_time.hashCode());
		result = prime * result + ((sector_via_exc_stop_unit == null) ? 0 : sector_via_exc_stop_unit.hashCode());
		result = prime * result + ((sector_via_geo_type == null) ? 0 : sector_via_geo_type.hashCode());
		result = prime * result + ((sector_via_geo_value == null) ? 0 : sector_via_geo_value.hashCode());
		result = prime * result + ((sector_via_intl == null) ? 0 : sector_via_intl.hashCode());
		result = prime * result + ((sector_via_stp == null) ? 0 : sector_via_stp.hashCode());
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
		YqyrSector other = (YqyrSector) obj;
		if (sector_from_to == null) {
			if (other.sector_from_to != null) {
				return false;
			}
		} else if (!sector_from_to.equals(other.sector_from_to)) {
			return false;
		}
		if (sector_indicator == null) {
			if (other.sector_indicator != null) {
				return false;
			}
		} else if (!sector_indicator.equals(other.sector_indicator)) {
			return false;
		}
		if (sector_loc_1_type == null) {
			if (other.sector_loc_1_type != null) {
				return false;
			}
		} else if (!sector_loc_1_type.equals(other.sector_loc_1_type)) {
			return false;
		}
		if (sector_loc_1_value == null) {
			if (other.sector_loc_1_value != null) {
				return false;
			}
		} else if (!sector_loc_1_value.equals(other.sector_loc_1_value)) {
			return false;
		}
		if (sector_loc_2_type == null) {
			if (other.sector_loc_2_type != null) {
				return false;
			}
		} else if (!sector_loc_2_type.equals(other.sector_loc_2_type)) {
			return false;
		}
		if (sector_loc_2_value == null) {
			if (other.sector_loc_2_value != null) {
				return false;
			}
		} else if (!sector_loc_2_value.equals(other.sector_loc_2_value)) {
			return false;
		}
		if (sector_via_cnx_exempt == null) {
			if (other.sector_via_cnx_exempt != null) {
				return false;
			}
		} else if (!sector_via_cnx_exempt.equals(other.sector_via_cnx_exempt)) {
			return false;
		}
		if (sector_via_exc_stop_time == null) {
			if (other.sector_via_exc_stop_time != null) {
				return false;
			}
		} else if (!sector_via_exc_stop_time.equals(other.sector_via_exc_stop_time)) {
			return false;
		}
		if (sector_via_exc_stop_unit == null) {
			if (other.sector_via_exc_stop_unit != null) {
				return false;
			}
		} else if (!sector_via_exc_stop_unit.equals(other.sector_via_exc_stop_unit)) {
			return false;
		}
		if (sector_via_geo_type == null) {
			if (other.sector_via_geo_type != null) {
				return false;
			}
		} else if (!sector_via_geo_type.equals(other.sector_via_geo_type)) {
			return false;
		}
		if (sector_via_geo_value == null) {
			if (other.sector_via_geo_value != null) {
				return false;
			}
		} else if (!sector_via_geo_value.equals(other.sector_via_geo_value)) {
			return false;
		}
		if (sector_via_intl == null) {
			if (other.sector_via_intl != null) {
				return false;
			}
		} else if (!sector_via_intl.equals(other.sector_via_intl)) {
			return false;
		}
		if (sector_via_stp == null) {
			if (other.sector_via_stp != null) {
				return false;
			}
		} else if (!sector_via_stp.equals(other.sector_via_stp)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "YqyrSector [sector_via_geo_value=" + sector_via_geo_value + ", sector_via_cnx_exempt="
				+ sector_via_cnx_exempt + ", sector_loc_1_type=" + sector_loc_1_type + ", sector_via_exc_stop_time="
				+ sector_via_exc_stop_time + ", sector_via_exc_stop_unit=" + sector_via_exc_stop_unit
				+ ", sector_indicator=" + sector_indicator + ", sector_via_stp=" + sector_via_stp
				+ ", sector_loc_1_value=" + sector_loc_1_value + ", sector_loc_2_type=" + sector_loc_2_type
				+ ", sector_via_geo_type=" + sector_via_geo_type + ", sector_loc_2_value=" + sector_loc_2_value
				+ ", sector_via_intl=" + sector_via_intl + ", sector_from_to=" + sector_from_to + "]";
	}
}
