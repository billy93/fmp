package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class Cat10OpenJaw {

	@Field("open_jaw_cxr")
	private String open_jaw_cxr;

	@Field("open_jaw_fc")
    private String open_jaw_fc;

	@Field("open_jaw_soj")
    private String open_jaw_soj;

	@Field("open_jaw_doj")
    private String open_jaw_doj;

	@Field("open_jaw_filler_1")
    private String open_jaw_filler_1;

	@Field("open_jaw_od")
    private String open_jaw_od;

	@Field("open_jaw_tr")
    private String open_jaw_tr;

	public String getOpen_jaw_cxr() {
		return open_jaw_cxr;
	}

	public void setOpen_jaw_cxr(String open_jaw_cxr) {
		this.open_jaw_cxr = open_jaw_cxr;
	}

	public String getOpen_jaw_fc() {
		return open_jaw_fc;
	}

	public void setOpen_jaw_fc(String open_jaw_fc) {
		this.open_jaw_fc = open_jaw_fc;
	}

	public String getOpen_jaw_soj() {
		return open_jaw_soj;
	}

	public void setOpen_jaw_soj(String open_jaw_soj) {
		this.open_jaw_soj = open_jaw_soj;
	}

	public String getOpen_jaw_doj() {
		return open_jaw_doj;
	}

	public void setOpen_jaw_doj(String open_jaw_doj) {
		this.open_jaw_doj = open_jaw_doj;
	}

	public String getOpen_jaw_filler_1() {
		return open_jaw_filler_1;
	}

	public void setOpen_jaw_filler_1(String open_jaw_filler_1) {
		this.open_jaw_filler_1 = open_jaw_filler_1;
	}

	public String getOpen_jaw_od() {
		return open_jaw_od;
	}

	public void setOpen_jaw_od(String open_jaw_od) {
		this.open_jaw_od = open_jaw_od;
	}

	public String getOpen_jaw_tr() {
		return open_jaw_tr;
	}

	public void setOpen_jaw_tr(String open_jaw_tr) {
		this.open_jaw_tr = open_jaw_tr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((open_jaw_cxr == null) ? 0 : open_jaw_cxr.hashCode());
		result = prime * result + ((open_jaw_doj == null) ? 0 : open_jaw_doj.hashCode());
		result = prime * result + ((open_jaw_fc == null) ? 0 : open_jaw_fc.hashCode());
		result = prime * result + ((open_jaw_filler_1 == null) ? 0 : open_jaw_filler_1.hashCode());
		result = prime * result + ((open_jaw_od == null) ? 0 : open_jaw_od.hashCode());
		result = prime * result + ((open_jaw_soj == null) ? 0 : open_jaw_soj.hashCode());
		result = prime * result + ((open_jaw_tr == null) ? 0 : open_jaw_tr.hashCode());
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
		Cat10OpenJaw other = (Cat10OpenJaw) obj;
		if (open_jaw_cxr == null) {
			if (other.open_jaw_cxr != null) {
				return false;
			}
		} else if (!open_jaw_cxr.equals(other.open_jaw_cxr)) {
			return false;
		}
		if (open_jaw_doj == null) {
			if (other.open_jaw_doj != null) {
				return false;
			}
		} else if (!open_jaw_doj.equals(other.open_jaw_doj)) {
			return false;
		}
		if (open_jaw_fc == null) {
			if (other.open_jaw_fc != null) {
				return false;
			}
		} else if (!open_jaw_fc.equals(other.open_jaw_fc)) {
			return false;
		}
		if (open_jaw_filler_1 == null) {
			if (other.open_jaw_filler_1 != null) {
				return false;
			}
		} else if (!open_jaw_filler_1.equals(other.open_jaw_filler_1)) {
			return false;
		}
		if (open_jaw_od == null) {
			if (other.open_jaw_od != null) {
				return false;
			}
		} else if (!open_jaw_od.equals(other.open_jaw_od)) {
			return false;
		}
		if (open_jaw_soj == null) {
			if (other.open_jaw_soj != null) {
				return false;
			}
		} else if (!open_jaw_soj.equals(other.open_jaw_soj)) {
			return false;
		}
		if (open_jaw_tr == null) {
			if (other.open_jaw_tr != null) {
				return false;
			}
		} else if (!open_jaw_tr.equals(other.open_jaw_tr)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Cat10OpenJaw [open_jaw_cxr=" + open_jaw_cxr + ", open_jaw_fc=" + open_jaw_fc + ", open_jaw_soj="
				+ open_jaw_soj + ", open_jaw_doj=" + open_jaw_doj + ", open_jaw_filler_1=" + open_jaw_filler_1
				+ ", open_jaw_od=" + open_jaw_od + ", open_jaw_tr=" + open_jaw_tr + "]";
	}
}
