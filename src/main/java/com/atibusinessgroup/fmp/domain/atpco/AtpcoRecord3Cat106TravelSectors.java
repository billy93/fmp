package com.atibusinessgroup.fmp.domain.atpco;

import org.springframework.data.mongodb.core.mapping.Field;

public class AtpcoRecord3Cat106TravelSectors {
	
	@Field("intl")
	private String intl;
	
	@Field("geo_loc_1")
    private String geo_loc_1;

	@Field("tsi")
    private String tsi;

	@Field("geo_loc_2")
    private String geo_loc_2;

	@Field("geo_type_1")
    private String geo_type_1;

	@Field("geo_type_2")
    private String geo_type_2;

	@Field("filler_2")
    private String filler_2;

	@Field("filler_1")
    private String filler_1;

	@Field("ow")
    private String ow;

	public String getIntl() {
		return intl;
	}

	public void setIntl(String intl) {
		this.intl = intl;
	}

	public String getGeo_loc_1() {
		return geo_loc_1;
	}

	public void setGeo_loc_1(String geo_loc_1) {
		this.geo_loc_1 = geo_loc_1;
	}

	public String getTsi() {
		return tsi;
	}

	public void setTsi(String tsi) {
		this.tsi = tsi;
	}

	public String getGeo_loc_2() {
		return geo_loc_2;
	}

	public void setGeo_loc_2(String geo_loc_2) {
		this.geo_loc_2 = geo_loc_2;
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

	public String getFiller_2() {
		return filler_2;
	}

	public void setFiller_2(String filler_2) {
		this.filler_2 = filler_2;
	}

	public String getFiller_1() {
		return filler_1;
	}

	public void setFiller_1(String filler_1) {
		this.filler_1 = filler_1;
	}

	public String getOw() {
		return ow;
	}

	public void setOw(String ow) {
		this.ow = ow;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filler_1 == null) ? 0 : filler_1.hashCode());
		result = prime * result + ((filler_2 == null) ? 0 : filler_2.hashCode());
		result = prime * result + ((geo_loc_1 == null) ? 0 : geo_loc_1.hashCode());
		result = prime * result + ((geo_loc_2 == null) ? 0 : geo_loc_2.hashCode());
		result = prime * result + ((geo_type_1 == null) ? 0 : geo_type_1.hashCode());
		result = prime * result + ((geo_type_2 == null) ? 0 : geo_type_2.hashCode());
		result = prime * result + ((intl == null) ? 0 : intl.hashCode());
		result = prime * result + ((ow == null) ? 0 : ow.hashCode());
		result = prime * result + ((tsi == null) ? 0 : tsi.hashCode());
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
		AtpcoRecord3Cat106TravelSectors other = (AtpcoRecord3Cat106TravelSectors) obj;
		if (filler_1 == null) {
			if (other.filler_1 != null)
				return false;
		} else if (!filler_1.equals(other.filler_1))
			return false;
		if (filler_2 == null) {
			if (other.filler_2 != null)
				return false;
		} else if (!filler_2.equals(other.filler_2))
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
		if (geo_type_1 == null) {
			if (other.geo_type_1 != null)
				return false;
		} else if (!geo_type_1.equals(other.geo_type_1))
			return false;
		if (geo_type_2 == null) {
			if (other.geo_type_2 != null)
				return false;
		} else if (!geo_type_2.equals(other.geo_type_2))
			return false;
		if (intl == null) {
			if (other.intl != null)
				return false;
		} else if (!intl.equals(other.intl))
			return false;
		if (ow == null) {
			if (other.ow != null)
				return false;
		} else if (!ow.equals(other.ow))
			return false;
		if (tsi == null) {
			if (other.tsi != null)
				return false;
		} else if (!tsi.equals(other.tsi))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoRecord3Cat106TravelSectors [intl=" + intl + ", geo_loc_1=" + geo_loc_1 + ", tsi=" + tsi
				+ ", geo_loc_2=" + geo_loc_2 + ", geo_type_1=" + geo_type_1 + ", geo_type_2=" + geo_type_2
				+ ", filler_2=" + filler_2 + ", filler_1=" + filler_1 + ", ow=" + ow + "]";
	}
	
	


}
