package com.atibusinessgroup.fmp.domain.atpco;

import org.springframework.data.mongodb.core.mapping.Field;

public class AtpcoRecord3Cat13FareClassRbd {

	@Field("appl")
	private String appl;

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
		AtpcoRecord3Cat13FareClassRbd other = (AtpcoRecord3Cat13FareClassRbd) obj;
		if (appl == null) {
			if (other.appl != null)
				return false;
		} else if (!appl.equals(other.appl))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoRecord3Cat13FareClassRbd [appl=" + appl + "]";
	}
	
	
}
