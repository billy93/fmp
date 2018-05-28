package com.atibusinessgroup.fmp.domain.atpco;

import org.springframework.data.mongodb.core.mapping.Field;

public class AtpcoRecord3Cat108PenServiceChg {

	@Field("append")
	private String append;

	@Field("mrph")
    private String mrph;

	@Field("p_sc_appl")
    private String p_sc_appl;

	public String getAppend() {
		return append;
	}

	public void setAppend(String append) {
		this.append = append;
	}

	public String getMrph() {
		return mrph;
	}

	public void setMrph(String mrph) {
		this.mrph = mrph;
	}

	public String getP_sc_appl() {
		return p_sc_appl;
	}

	public void setP_sc_appl(String p_sc_appl) {
		this.p_sc_appl = p_sc_appl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((append == null) ? 0 : append.hashCode());
		result = prime * result + ((mrph == null) ? 0 : mrph.hashCode());
		result = prime * result + ((p_sc_appl == null) ? 0 : p_sc_appl.hashCode());
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
		AtpcoRecord3Cat108PenServiceChg other = (AtpcoRecord3Cat108PenServiceChg) obj;
		if (append == null) {
			if (other.append != null)
				return false;
		} else if (!append.equals(other.append))
			return false;
		if (mrph == null) {
			if (other.mrph != null)
				return false;
		} else if (!mrph.equals(other.mrph))
			return false;
		if (p_sc_appl == null) {
			if (other.p_sc_appl != null)
				return false;
		} else if (!p_sc_appl.equals(other.p_sc_appl))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoRecord3Cat108PenServiceChg [append=" + append + ", mrph=" + mrph + ", p_sc_appl=" + p_sc_appl
				+ "]";
	}
	

    
}
