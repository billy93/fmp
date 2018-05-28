package com.atibusinessgroup.fmp.domain.atpco;

import org.springframework.data.mongodb.core.mapping.Field;

public class AtpcoRecord3Cat22AccompanyingPassengers {

	@Field("min_no")
	private String min_no;

	@Field("max_no")
	private String max_no;

	@Field("age_min")
	private String age_min;

	@Field("fare_class_rbd")
	private String fare_class_rbd;

	@Field("age_max")
	private String age_max;

	@Field("pass_type")
	private String pass_type;

	public String getMin_no() {
		return min_no;
	}

	public void setMin_no(String min_no) {
		this.min_no = min_no;
	}

	public String getMax_no() {
		return max_no;
	}

	public void setMax_no(String max_no) {
		this.max_no = max_no;
	}

	public String getAge_min() {
		return age_min;
	}

	public void setAge_min(String age_min) {
		this.age_min = age_min;
	}

	public String getFare_class_rbd() {
		return fare_class_rbd;
	}

	public void setFare_class_rbd(String fare_class_rbd) {
		this.fare_class_rbd = fare_class_rbd;
	}

	public String getAge_max() {
		return age_max;
	}

	public void setAge_max(String age_max) {
		this.age_max = age_max;
	}

	public String getPass_type() {
		return pass_type;
	}

	public void setPass_type(String pass_type) {
		this.pass_type = pass_type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age_max == null) ? 0 : age_max.hashCode());
		result = prime * result + ((age_min == null) ? 0 : age_min.hashCode());
		result = prime * result + ((fare_class_rbd == null) ? 0 : fare_class_rbd.hashCode());
		result = prime * result + ((max_no == null) ? 0 : max_no.hashCode());
		result = prime * result + ((min_no == null) ? 0 : min_no.hashCode());
		result = prime * result + ((pass_type == null) ? 0 : pass_type.hashCode());
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
		AtpcoRecord3Cat22AccompanyingPassengers other = (AtpcoRecord3Cat22AccompanyingPassengers) obj;
		if (age_max == null) {
			if (other.age_max != null)
				return false;
		} else if (!age_max.equals(other.age_max))
			return false;
		if (age_min == null) {
			if (other.age_min != null)
				return false;
		} else if (!age_min.equals(other.age_min))
			return false;
		if (fare_class_rbd == null) {
			if (other.fare_class_rbd != null)
				return false;
		} else if (!fare_class_rbd.equals(other.fare_class_rbd))
			return false;
		if (max_no == null) {
			if (other.max_no != null)
				return false;
		} else if (!max_no.equals(other.max_no))
			return false;
		if (min_no == null) {
			if (other.min_no != null)
				return false;
		} else if (!min_no.equals(other.min_no))
			return false;
		if (pass_type == null) {
			if (other.pass_type != null)
				return false;
		} else if (!pass_type.equals(other.pass_type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoRecord3Cat22AccompanyingPassengers [min_no=" + min_no + ", max_no=" + max_no + ", age_min="
				+ age_min + ", fare_class_rbd=" + fare_class_rbd + ", age_max=" + age_max + ", pass_type=" + pass_type
				+ "]";
	}
	
	
}
