package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class Cat10EndOnEnd {

	@Field("end_on_end_cxr")
	private String end_on_end_cxr;

	@Field("end_on_end_fc")
    private String end_on_end_fc;

	@Field("end_on_end_tr")
    private String end_on_end_tr;

	@Field("end_on_end_filler_1")
    private String end_on_end_filler_1;

	@Field("end_on_end_end")
    private String end_on_end_end;

	public String getEnd_on_end_cxr() {
		return end_on_end_cxr;
	}

	public void setEnd_on_end_cxr(String end_on_end_cxr) {
		this.end_on_end_cxr = end_on_end_cxr;
	}

	public String getEnd_on_end_fc() {
		return end_on_end_fc;
	}

	public void setEnd_on_end_fc(String end_on_end_fc) {
		this.end_on_end_fc = end_on_end_fc;
	}

	public String getEnd_on_end_tr() {
		return end_on_end_tr;
	}

	public void setEnd_on_end_tr(String end_on_end_tr) {
		this.end_on_end_tr = end_on_end_tr;
	}

	public String getEnd_on_end_filler_1() {
		return end_on_end_filler_1;
	}

	public void setEnd_on_end_filler_1(String end_on_end_filler_1) {
		this.end_on_end_filler_1 = end_on_end_filler_1;
	}

	public String getEnd_on_end_end() {
		return end_on_end_end;
	}

	public void setEnd_on_end_end(String end_on_end_end) {
		this.end_on_end_end = end_on_end_end;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end_on_end_cxr == null) ? 0 : end_on_end_cxr.hashCode());
		result = prime * result + ((end_on_end_end == null) ? 0 : end_on_end_end.hashCode());
		result = prime * result + ((end_on_end_fc == null) ? 0 : end_on_end_fc.hashCode());
		result = prime * result + ((end_on_end_filler_1 == null) ? 0 : end_on_end_filler_1.hashCode());
		result = prime * result + ((end_on_end_tr == null) ? 0 : end_on_end_tr.hashCode());
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
		Cat10EndOnEnd other = (Cat10EndOnEnd) obj;
		if (end_on_end_cxr == null) {
			if (other.end_on_end_cxr != null) {
				return false;
			}
		} else if (!end_on_end_cxr.equals(other.end_on_end_cxr)) {
			return false;
		}
		if (end_on_end_end == null) {
			if (other.end_on_end_end != null) {
				return false;
			}
		} else if (!end_on_end_end.equals(other.end_on_end_end)) {
			return false;
		}
		if (end_on_end_fc == null) {
			if (other.end_on_end_fc != null) {
				return false;
			}
		} else if (!end_on_end_fc.equals(other.end_on_end_fc)) {
			return false;
		}
		if (end_on_end_filler_1 == null) {
			if (other.end_on_end_filler_1 != null) {
				return false;
			}
		} else if (!end_on_end_filler_1.equals(other.end_on_end_filler_1)) {
			return false;
		}
		if (end_on_end_tr == null) {
			if (other.end_on_end_tr != null) {
				return false;
			}
		} else if (!end_on_end_tr.equals(other.end_on_end_tr)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Cat10EndOnEnd [end_on_end_cxr=" + end_on_end_cxr + ", end_on_end_fc=" + end_on_end_fc
				+ ", end_on_end_tr=" + end_on_end_tr + ", end_on_end_filler_1=" + end_on_end_filler_1
				+ ", end_on_end_end=" + end_on_end_end + "]";
	}
}
