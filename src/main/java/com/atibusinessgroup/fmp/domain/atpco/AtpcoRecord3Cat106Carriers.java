package com.atibusinessgroup.fmp.domain.atpco;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

public class AtpcoRecord3Cat106Carriers {
	
	@Field("carriers")
	private List<String> carriers = new ArrayList<>();

	@Field("appl")
    private String appl;

	public List<String> getCarriers() {
		return carriers;
	}

	public void setCarriers(List<String> carriers) {
		this.carriers = carriers;
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
		result = prime * result + ((carriers == null) ? 0 : carriers.hashCode());
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
		AtpcoRecord3Cat106Carriers other = (AtpcoRecord3Cat106Carriers) obj;
		if (appl == null) {
			if (other.appl != null) {
				return false;
			}
		} else if (!appl.equals(other.appl)) {
			return false;
		}
		if (carriers == null) {
			if (other.carriers != null) {
				return false;
			}
		} else if (!carriers.equals(other.carriers)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoRecord3Cat106Carriers [carriers=" + carriers + ", appl=" + appl + "]";
	}
}
