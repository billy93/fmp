package com.atibusinessgroup.fmp.domain.atpco;

import org.springframework.data.mongodb.core.mapping.Field;

public class AtpcoRecord3Cat15Locale {
	
	@Field("geo_type")
	private String geo_type;

	@Field("geo_loc_1")
    private String geo_loc_1;

	@Field("geo_loc_2")
    private String geo_loc_2;

	@Field("appl")
    private String appl;

	public String getGeo_type() {
		return geo_type;
	}

	public void setGeo_type(String geo_type) {
		this.geo_type = geo_type;
	}

	public String getGeo_loc_1() {
		return geo_loc_1;
	}

	public void setGeo_loc_1(String geo_loc_1) {
		this.geo_loc_1 = geo_loc_1;
	}

	public String getGeo_loc_2() {
		return geo_loc_2;
	}

	public void setGeo_loc_2(String geo_loc_2) {
		this.geo_loc_2 = geo_loc_2;
	}

	public String getAppl() {
		return appl;
	}

	public void setAppl(String appl) {
		this.appl = appl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appl == null) ? 0 : appl.hashCode());
		result = prime * result + ((geo_loc_1 == null) ? 0 : geo_loc_1.hashCode());
		result = prime * result + ((geo_loc_2 == null) ? 0 : geo_loc_2.hashCode());
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
		AtpcoRecord3Cat15Locale other = (AtpcoRecord3Cat15Locale) obj;
		if (appl == null) {
			if (other.appl != null)
				return false;
		} else if (!appl.equals(other.appl))
			return false;
		if (geo_loc_1 == null) {
			if (other.geo_loc_1 != null)
				return false;
		} else if (!geo_loc_1.equals(other.geo_loc_1))
			return false;
		if (geo_loc_2 == null) {
			if (other.geo_loc_2 != null)
				return false;
		} else if (!geo_loc_2.equals(other.geo_loc_2))
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
		return "AtpcoRecord3Cat15Locale [geo_type=" + geo_type + ", geo_loc_1=" + geo_loc_1 + ", geo_loc_2=" + geo_loc_2
				+ ", appl=" + appl + "]";
	}
	
	

}
