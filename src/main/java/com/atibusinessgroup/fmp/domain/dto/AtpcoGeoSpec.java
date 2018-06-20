package com.atibusinessgroup.fmp.domain.dto;

public class AtpcoGeoSpec {
	private String type1;
	private String loc1;
	private String type2;
	private String loc2;
	
	public String getType1() {
		return type1;
	}
	
	public void setType1(String type1) {
		this.type1 = type1;
	}
	
	public String getLoc1() {
		return loc1;
	}
	
	public void setLoc1(String loc1) {
		this.loc1 = loc1;
	}
	
	public String getType2() {
		return type2;
	}
	
	public void setType2(String type2) {
		this.type2 = type2;
	}
	
	public String getLoc2() {
		return loc2;
	}
	
	public void setLoc2(String loc2) {
		this.loc2 = loc2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((loc1 == null) ? 0 : loc1.hashCode());
		result = prime * result + ((loc2 == null) ? 0 : loc2.hashCode());
		result = prime * result + ((type1 == null) ? 0 : type1.hashCode());
		result = prime * result + ((type2 == null) ? 0 : type2.hashCode());
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
		AtpcoGeoSpec other = (AtpcoGeoSpec) obj;
		if (loc1 == null) {
			if (other.loc1 != null) {
				return false;
			}
		} else if (!loc1.equals(other.loc1)) {
			return false;
		}
		if (loc2 == null) {
			if (other.loc2 != null) {
				return false;
			}
		} else if (!loc2.equals(other.loc2)) {
			return false;
		}
		if (type1 == null) {
			if (other.type1 != null) {
				return false;
			}
		} else if (!type1.equals(other.type1)) {
			return false;
		}
		if (type2 == null) {
			if (other.type2 != null) {
				return false;
			}
		} else if (!type2.equals(other.type2)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoGeoSpec [type1=" + type1 + ", loc1=" + loc1 + ", type2=" + type2 + ", loc2=" + loc2 + "]";
	}
}
