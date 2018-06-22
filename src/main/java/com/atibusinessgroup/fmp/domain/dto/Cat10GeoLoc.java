package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class Cat10GeoLoc {

	@Field("geo_value_2")
	private String geo_value_2;

	@Field("geo_value_1")
    private String geo_value_1;

	@Field("geo_type_1")
    private String geo_type_1;

	@Field("geo_type_2")
    private String geo_type_2;

	public String getGeo_value_2() {
		return geo_value_2;
	}

	public void setGeo_value_2(String geo_value_2) {
		this.geo_value_2 = geo_value_2;
	}

	public String getGeo_value_1() {
		return geo_value_1;
	}

	public void setGeo_value_1(String geo_value_1) {
		this.geo_value_1 = geo_value_1;
	}

	public String getGeo_type_1() {
		return geo_type_1;
	}

	public void setGeo_type_1(String geo_type_1) {
		this.geo_type_1 = geo_type_1;
	}

	public String getGeo_type_2() {
		return geo_type_2;
	}

	public void setGeo_type_2(String geo_type_2) {
		this.geo_type_2 = geo_type_2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((geo_type_1 == null) ? 0 : geo_type_1.hashCode());
		result = prime * result + ((geo_type_2 == null) ? 0 : geo_type_2.hashCode());
		result = prime * result + ((geo_value_1 == null) ? 0 : geo_value_1.hashCode());
		result = prime * result + ((geo_value_2 == null) ? 0 : geo_value_2.hashCode());
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
		Cat10GeoLoc other = (Cat10GeoLoc) obj;
		if (geo_type_1 == null) {
			if (other.geo_type_1 != null) {
				return false;
			}
		} else if (!geo_type_1.equals(other.geo_type_1)) {
			return false;
		}
		if (geo_type_2 == null) {
			if (other.geo_type_2 != null) {
				return false;
			}
		} else if (!geo_type_2.equals(other.geo_type_2)) {
			return false;
		}
		if (geo_value_1 == null) {
			if (other.geo_value_1 != null) {
				return false;
			}
		} else if (!geo_value_1.equals(other.geo_value_1)) {
			return false;
		}
		if (geo_value_2 == null) {
			if (other.geo_value_2 != null) {
				return false;
			}
		} else if (!geo_value_2.equals(other.geo_value_2)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Cat10GeoLoc [geo_value_2=" + geo_value_2 + ", geo_value_1=" + geo_value_1 + ", geo_type_1=" + geo_type_1
				+ ", geo_type_2=" + geo_type_2 + "]";
	}
}
