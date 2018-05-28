package com.atibusinessgroup.fmp.domain.atpco;

import org.springframework.data.mongodb.core.mapping.Field;

public class AtpcoRecord3Cat50GeoLocation {
	
	@Field("geo_type")
	private String geo_type;

	@Field("geo_loc")
    private String geo_loc;

	@Field("geo_appl")
    private String geo_appl;

	public String getGeo_type() {
		return geo_type;
	}

	public void setGeo_type(String geo_type) {
		this.geo_type = geo_type;
	}

	public String getGeo_loc() {
		return geo_loc;
	}

	public void setGeo_loc(String geo_loc) {
		this.geo_loc = geo_loc;
	}

	public String getGeo_appl() {
		return geo_appl;
	}

	public void setGeo_appl(String geo_appl) {
		this.geo_appl = geo_appl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((geo_appl == null) ? 0 : geo_appl.hashCode());
		result = prime * result + ((geo_loc == null) ? 0 : geo_loc.hashCode());
		result = prime * result + ((geo_type == null) ? 0 : geo_type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtpcoRecord3Cat50GeoLocation other = (AtpcoRecord3Cat50GeoLocation) obj;
		if (geo_appl == null) {
			if (other.geo_appl != null)
				return false;
		} else if (!geo_appl.equals(other.geo_appl))
			return false;
		if (geo_loc == null) {
			if (other.geo_loc != null)
				return false;
		} else if (!geo_loc.equals(other.geo_loc))
			return false;
		if (geo_type == null) {
			if (other.geo_type != null)
				return false;
		} else if (!geo_type.equals(other.geo_type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoRecord3Cat50GeoLocation [geo_type=" + geo_type + ", geo_loc=" + geo_loc + ", geo_appl=" + geo_appl
				+ "]";
	}
	
	


}
