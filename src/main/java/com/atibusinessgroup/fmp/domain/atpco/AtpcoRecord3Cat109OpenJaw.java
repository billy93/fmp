package com.atibusinessgroup.fmp.domain.atpco;

import org.springframework.data.mongodb.core.mapping.Field;

public class AtpcoRecord3Cat109OpenJaw {
	
	@Field("set_2_appl")
	private String set_2_appl;

	@Field("set_2_geo_type_2")
    private String set_2_geo_type_2;

	@Field("set_1_geo_type_1")
    private String set_1_geo_type_1;

	@Field("set_2_geo_type_1")
    private String set_2_geo_type_1;

	@Field("set_1_appl")
    private String set_1_appl;

	@Field("set_1_geo_type_2")
    private String set_1_geo_type_2;

	@Field("set_1_geo_loc_2")
    private String set_1_geo_loc_2;

	@Field("set_2_geo_loc_1")
    private String set_2_geo_loc_1;

	@Field("set_1_geo_loc_1")
    private String set_1_geo_loc_1;

	@Field("set_2_geo_loc_2")
    private String set_2_geo_loc_2;

	public String getSet_2_appl() {
		return set_2_appl;
	}

	public void setSet_2_appl(String set_2_appl) {
		this.set_2_appl = set_2_appl;
	}

	public String getSet_2_geo_type_2() {
		return set_2_geo_type_2;
	}

	public void setSet_2_geo_type_2(String set_2_geo_type_2) {
		this.set_2_geo_type_2 = set_2_geo_type_2;
	}

	public String getSet_1_geo_type_1() {
		return set_1_geo_type_1;
	}

	public void setSet_1_geo_type_1(String set_1_geo_type_1) {
		this.set_1_geo_type_1 = set_1_geo_type_1;
	}

	public String getSet_2_geo_type_1() {
		return set_2_geo_type_1;
	}

	public void setSet_2_geo_type_1(String set_2_geo_type_1) {
		this.set_2_geo_type_1 = set_2_geo_type_1;
	}

	public String getSet_1_appl() {
		return set_1_appl;
	}

	public void setSet_1_appl(String set_1_appl) {
		this.set_1_appl = set_1_appl;
	}

	public String getSet_1_geo_type_2() {
		return set_1_geo_type_2;
	}

	public void setSet_1_geo_type_2(String set_1_geo_type_2) {
		this.set_1_geo_type_2 = set_1_geo_type_2;
	}

	public String getSet_1_geo_loc_2() {
		return set_1_geo_loc_2;
	}

	public void setSet_1_geo_loc_2(String set_1_geo_loc_2) {
		this.set_1_geo_loc_2 = set_1_geo_loc_2;
	}

	public String getSet_2_geo_loc_1() {
		return set_2_geo_loc_1;
	}

	public void setSet_2_geo_loc_1(String set_2_geo_loc_1) {
		this.set_2_geo_loc_1 = set_2_geo_loc_1;
	}

	public String getSet_1_geo_loc_1() {
		return set_1_geo_loc_1;
	}

	public void setSet_1_geo_loc_1(String set_1_geo_loc_1) {
		this.set_1_geo_loc_1 = set_1_geo_loc_1;
	}

	public String getSet_2_geo_loc_2() {
		return set_2_geo_loc_2;
	}

	public void setSet_2_geo_loc_2(String set_2_geo_loc_2) {
		this.set_2_geo_loc_2 = set_2_geo_loc_2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((set_1_appl == null) ? 0 : set_1_appl.hashCode());
		result = prime * result + ((set_1_geo_loc_1 == null) ? 0 : set_1_geo_loc_1.hashCode());
		result = prime * result + ((set_1_geo_loc_2 == null) ? 0 : set_1_geo_loc_2.hashCode());
		result = prime * result + ((set_1_geo_type_1 == null) ? 0 : set_1_geo_type_1.hashCode());
		result = prime * result + ((set_1_geo_type_2 == null) ? 0 : set_1_geo_type_2.hashCode());
		result = prime * result + ((set_2_appl == null) ? 0 : set_2_appl.hashCode());
		result = prime * result + ((set_2_geo_loc_1 == null) ? 0 : set_2_geo_loc_1.hashCode());
		result = prime * result + ((set_2_geo_loc_2 == null) ? 0 : set_2_geo_loc_2.hashCode());
		result = prime * result + ((set_2_geo_type_1 == null) ? 0 : set_2_geo_type_1.hashCode());
		result = prime * result + ((set_2_geo_type_2 == null) ? 0 : set_2_geo_type_2.hashCode());
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
		AtpcoRecord3Cat109OpenJaw other = (AtpcoRecord3Cat109OpenJaw) obj;
		if (set_1_appl == null) {
			if (other.set_1_appl != null)
				return false;
		} else if (!set_1_appl.equals(other.set_1_appl))
			return false;
		if (set_1_geo_loc_1 == null) {
			if (other.set_1_geo_loc_1 != null)
				return false;
		} else if (!set_1_geo_loc_1.equals(other.set_1_geo_loc_1))
			return false;
		if (set_1_geo_loc_2 == null) {
			if (other.set_1_geo_loc_2 != null)
				return false;
		} else if (!set_1_geo_loc_2.equals(other.set_1_geo_loc_2))
			return false;
		if (set_1_geo_type_1 == null) {
			if (other.set_1_geo_type_1 != null)
				return false;
		} else if (!set_1_geo_type_1.equals(other.set_1_geo_type_1))
			return false;
		if (set_1_geo_type_2 == null) {
			if (other.set_1_geo_type_2 != null)
				return false;
		} else if (!set_1_geo_type_2.equals(other.set_1_geo_type_2))
			return false;
		if (set_2_appl == null) {
			if (other.set_2_appl != null)
				return false;
		} else if (!set_2_appl.equals(other.set_2_appl))
			return false;
		if (set_2_geo_loc_1 == null) {
			if (other.set_2_geo_loc_1 != null)
				return false;
		} else if (!set_2_geo_loc_1.equals(other.set_2_geo_loc_1))
			return false;
		if (set_2_geo_loc_2 == null) {
			if (other.set_2_geo_loc_2 != null)
				return false;
		} else if (!set_2_geo_loc_2.equals(other.set_2_geo_loc_2))
			return false;
		if (set_2_geo_type_1 == null) {
			if (other.set_2_geo_type_1 != null)
				return false;
		} else if (!set_2_geo_type_1.equals(other.set_2_geo_type_1))
			return false;
		if (set_2_geo_type_2 == null) {
			if (other.set_2_geo_type_2 != null)
				return false;
		} else if (!set_2_geo_type_2.equals(other.set_2_geo_type_2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoRecord3Cat109OpenJaw [set_2_appl=" + set_2_appl + ", set_2_geo_type_2=" + set_2_geo_type_2
				+ ", set_1_geo_type_1=" + set_1_geo_type_1 + ", set_2_geo_type_1=" + set_2_geo_type_1 + ", set_1_appl="
				+ set_1_appl + ", set_1_geo_type_2=" + set_1_geo_type_2 + ", set_1_geo_loc_2=" + set_1_geo_loc_2
				+ ", set_2_geo_loc_1=" + set_2_geo_loc_1 + ", set_1_geo_loc_1=" + set_1_geo_loc_1 + ", set_2_geo_loc_2="
				+ set_2_geo_loc_2 + "]";
	}
	
	

}
