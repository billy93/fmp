package com.atibusinessgroup.fmp.domain.atpco;

import org.springframework.data.mongodb.core.mapping.Field;

public class AtpcoRecord3Cat108FareClassType {
	
	@Field("val")
	private String val;

	@Field("fare_class_type")
    private String fare_class_type;

	@Field("norm_1")
    private String norm_1;

	@Field("owrt")
    private String owrt;

	@Field("type")
    private String type;

	@Field("same")
    private String same;

	@Field("appl")
    private String appl;

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getFare_class_type() {
		return fare_class_type;
	}

	public void setFare_class_type(String fare_class_type) {
		this.fare_class_type = fare_class_type;
	}

	public String getNorm_1() {
		return norm_1;
	}

	public void setNorm_1(String norm_1) {
		this.norm_1 = norm_1;
	}

	public String getOwrt() {
		return owrt;
	}

	public void setOwrt(String owrt) {
		this.owrt = owrt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSame() {
		return same;
	}

	public void setSame(String same) {
		this.same = same;
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
		result = prime * result + ((fare_class_type == null) ? 0 : fare_class_type.hashCode());
		result = prime * result + ((norm_1 == null) ? 0 : norm_1.hashCode());
		result = prime * result + ((owrt == null) ? 0 : owrt.hashCode());
		result = prime * result + ((same == null) ? 0 : same.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((val == null) ? 0 : val.hashCode());
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
		AtpcoRecord3Cat108FareClassType other = (AtpcoRecord3Cat108FareClassType) obj;
		if (appl == null) {
			if (other.appl != null)
				return false;
		} else if (!appl.equals(other.appl))
			return false;
		if (fare_class_type == null) {
			if (other.fare_class_type != null)
				return false;
		} else if (!fare_class_type.equals(other.fare_class_type))
			return false;
		if (norm_1 == null) {
			if (other.norm_1 != null)
				return false;
		} else if (!norm_1.equals(other.norm_1))
			return false;
		if (owrt == null) {
			if (other.owrt != null)
				return false;
		} else if (!owrt.equals(other.owrt))
			return false;
		if (same == null) {
			if (other.same != null)
				return false;
		} else if (!same.equals(other.same))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (val == null) {
			if (other.val != null)
				return false;
		} else if (!val.equals(other.val))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoRecord3Cat108FareClassType [val=" + val + ", fare_class_type=" + fare_class_type + ", norm_1="
				+ norm_1 + ", owrt=" + owrt + ", type=" + type + ", same=" + same + ", appl=" + appl + "]";
	}
	
	

}
