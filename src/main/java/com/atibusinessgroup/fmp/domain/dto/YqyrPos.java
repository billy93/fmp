package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class YqyrPos {

	@Field("pos_code")
	private String pos_code;

	@Field("pos_type")
    private String pos_type;

	@Field("pos_geo_spec_type")
    private String pos_geo_spec_type;

	@Field("pos_geo_spec_value")
    private String pos_geo_spec_value;

	@Field("pos_filler")
    private String pos_filler;

	public String getPos_code() {
		return pos_code;
	}

	public void setPos_code(String pos_code) {
		this.pos_code = pos_code;
	}

	public String getPos_type() {
		return pos_type;
	}

	public void setPos_type(String pos_type) {
		this.pos_type = pos_type;
	}

	public String getPos_geo_spec_type() {
		return pos_geo_spec_type;
	}

	public void setPos_geo_spec_type(String pos_geo_spec_type) {
		this.pos_geo_spec_type = pos_geo_spec_type;
	}

	public String getPos_geo_spec_value() {
		return pos_geo_spec_value;
	}

	public void setPos_geo_spec_value(String pos_geo_spec_value) {
		this.pos_geo_spec_value = pos_geo_spec_value;
	}

	public String getPos_filler() {
		return pos_filler;
	}

	public void setPos_filler(String pos_filler) {
		this.pos_filler = pos_filler;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pos_code == null) ? 0 : pos_code.hashCode());
		result = prime * result + ((pos_filler == null) ? 0 : pos_filler.hashCode());
		result = prime * result + ((pos_geo_spec_type == null) ? 0 : pos_geo_spec_type.hashCode());
		result = prime * result + ((pos_geo_spec_value == null) ? 0 : pos_geo_spec_value.hashCode());
		result = prime * result + ((pos_type == null) ? 0 : pos_type.hashCode());
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
		YqyrPos other = (YqyrPos) obj;
		if (pos_code == null) {
			if (other.pos_code != null) {
				return false;
			}
		} else if (!pos_code.equals(other.pos_code)) {
			return false;
		}
		if (pos_filler == null) {
			if (other.pos_filler != null) {
				return false;
			}
		} else if (!pos_filler.equals(other.pos_filler)) {
			return false;
		}
		if (pos_geo_spec_type == null) {
			if (other.pos_geo_spec_type != null) {
				return false;
			}
		} else if (!pos_geo_spec_type.equals(other.pos_geo_spec_type)) {
			return false;
		}
		if (pos_geo_spec_value == null) {
			if (other.pos_geo_spec_value != null) {
				return false;
			}
		} else if (!pos_geo_spec_value.equals(other.pos_geo_spec_value)) {
			return false;
		}
		if (pos_type == null) {
			if (other.pos_type != null) {
				return false;
			}
		} else if (!pos_type.equals(other.pos_type)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "YqyrPos [pos_code=" + pos_code + ", pos_type=" + pos_type + ", pos_geo_spec_type=" + pos_geo_spec_type
				+ ", pos_geo_spec_value=" + pos_geo_spec_value + ", pos_filler=" + pos_filler + "]";
	}
}
