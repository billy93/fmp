package com.atibusinessgroup.fmp.domain.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoFare;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord1;

public class AtpcoFareWithRecord1 {

	@Field("atpco_fare")
	private AtpcoFare atpcoFare;
	
	@Field("atpco_record_1")
	private List<AtpcoRecord1> atpcoRecord1 = new ArrayList<>();

	public AtpcoFare getAtpcoFare() {
		return atpcoFare;
	}

	public void setAtpcoFare(AtpcoFare atpcoFare) {
		this.atpcoFare = atpcoFare;
	}

	public List<AtpcoRecord1> getAtpcoRecord1() {
		return atpcoRecord1;
	}

	public void setAtpcoRecord1(List<AtpcoRecord1> atpcoRecord1) {
		this.atpcoRecord1 = atpcoRecord1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((atpcoFare == null) ? 0 : atpcoFare.hashCode());
		result = prime * result + ((atpcoRecord1 == null) ? 0 : atpcoRecord1.hashCode());
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
		AtpcoFareWithRecord1 other = (AtpcoFareWithRecord1) obj;
		if (atpcoFare == null) {
			if (other.atpcoFare != null) {
				return false;
			}
		} else if (!atpcoFare.equals(other.atpcoFare)) {
			return false;
		}
		if (atpcoRecord1 == null) {
			if (other.atpcoRecord1 != null) {
				return false;
			}
		} else if (!atpcoRecord1.equals(other.atpcoRecord1)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoFareWithRecord1 [atpcoFare=" + atpcoFare + ", atpcoRecord1=" + atpcoRecord1 + "]";
	}
}
