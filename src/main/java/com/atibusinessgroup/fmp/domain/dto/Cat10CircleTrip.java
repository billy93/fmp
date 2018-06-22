package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class Cat10CircleTrip {
	
	@Field("circle_trip_plus_tr")
	private String circle_trip_plus_tr;

	@Field("circle_trip_filler_1")
    private String circle_trip_filler_1;

	@Field("circle_trip_plus_ct2")
    private String circle_trip_plus_ct2;

	@Field("circle_trip_filler_2")
    private String circle_trip_filler_2;

	@Field("circle_trip_plus_fc")
    private String circle_trip_plus_fc;

	@Field("circle_trip_ct2")
    private String circle_trip_ct2;

	@Field("circle_trip_fc")
    private String circle_trip_fc;

	@Field("circle_trip_tr")
    private String circle_trip_tr;

	@Field("circle_trip_plus_cxr")
    private String circle_trip_plus_cxr;

	@Field("circle_trip_cxr")
    private String circle_trip_cxr;

	public String getCircle_trip_plus_tr() {
		return circle_trip_plus_tr;
	}

	public void setCircle_trip_plus_tr(String circle_trip_plus_tr) {
		this.circle_trip_plus_tr = circle_trip_plus_tr;
	}

	public String getCircle_trip_filler_1() {
		return circle_trip_filler_1;
	}

	public void setCircle_trip_filler_1(String circle_trip_filler_1) {
		this.circle_trip_filler_1 = circle_trip_filler_1;
	}

	public String getCircle_trip_plus_ct2() {
		return circle_trip_plus_ct2;
	}

	public void setCircle_trip_plus_ct2(String circle_trip_plus_ct2) {
		this.circle_trip_plus_ct2 = circle_trip_plus_ct2;
	}

	public String getCircle_trip_filler_2() {
		return circle_trip_filler_2;
	}

	public void setCircle_trip_filler_2(String circle_trip_filler_2) {
		this.circle_trip_filler_2 = circle_trip_filler_2;
	}

	public String getCircle_trip_plus_fc() {
		return circle_trip_plus_fc;
	}

	public void setCircle_trip_plus_fc(String circle_trip_plus_fc) {
		this.circle_trip_plus_fc = circle_trip_plus_fc;
	}

	public String getCircle_trip_ct2() {
		return circle_trip_ct2;
	}

	public void setCircle_trip_ct2(String circle_trip_ct2) {
		this.circle_trip_ct2 = circle_trip_ct2;
	}

	public String getCircle_trip_fc() {
		return circle_trip_fc;
	}

	public void setCircle_trip_fc(String circle_trip_fc) {
		this.circle_trip_fc = circle_trip_fc;
	}

	public String getCircle_trip_tr() {
		return circle_trip_tr;
	}

	public void setCircle_trip_tr(String circle_trip_tr) {
		this.circle_trip_tr = circle_trip_tr;
	}

	public String getCircle_trip_plus_cxr() {
		return circle_trip_plus_cxr;
	}

	public void setCircle_trip_plus_cxr(String circle_trip_plus_cxr) {
		this.circle_trip_plus_cxr = circle_trip_plus_cxr;
	}

	public String getCircle_trip_cxr() {
		return circle_trip_cxr;
	}

	public void setCircle_trip_cxr(String circle_trip_cxr) {
		this.circle_trip_cxr = circle_trip_cxr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((circle_trip_ct2 == null) ? 0 : circle_trip_ct2.hashCode());
		result = prime * result + ((circle_trip_cxr == null) ? 0 : circle_trip_cxr.hashCode());
		result = prime * result + ((circle_trip_fc == null) ? 0 : circle_trip_fc.hashCode());
		result = prime * result + ((circle_trip_filler_1 == null) ? 0 : circle_trip_filler_1.hashCode());
		result = prime * result + ((circle_trip_filler_2 == null) ? 0 : circle_trip_filler_2.hashCode());
		result = prime * result + ((circle_trip_plus_ct2 == null) ? 0 : circle_trip_plus_ct2.hashCode());
		result = prime * result + ((circle_trip_plus_cxr == null) ? 0 : circle_trip_plus_cxr.hashCode());
		result = prime * result + ((circle_trip_plus_fc == null) ? 0 : circle_trip_plus_fc.hashCode());
		result = prime * result + ((circle_trip_plus_tr == null) ? 0 : circle_trip_plus_tr.hashCode());
		result = prime * result + ((circle_trip_tr == null) ? 0 : circle_trip_tr.hashCode());
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
		Cat10CircleTrip other = (Cat10CircleTrip) obj;
		if (circle_trip_ct2 == null) {
			if (other.circle_trip_ct2 != null) {
				return false;
			}
		} else if (!circle_trip_ct2.equals(other.circle_trip_ct2)) {
			return false;
		}
		if (circle_trip_cxr == null) {
			if (other.circle_trip_cxr != null) {
				return false;
			}
		} else if (!circle_trip_cxr.equals(other.circle_trip_cxr)) {
			return false;
		}
		if (circle_trip_fc == null) {
			if (other.circle_trip_fc != null) {
				return false;
			}
		} else if (!circle_trip_fc.equals(other.circle_trip_fc)) {
			return false;
		}
		if (circle_trip_filler_1 == null) {
			if (other.circle_trip_filler_1 != null) {
				return false;
			}
		} else if (!circle_trip_filler_1.equals(other.circle_trip_filler_1)) {
			return false;
		}
		if (circle_trip_filler_2 == null) {
			if (other.circle_trip_filler_2 != null) {
				return false;
			}
		} else if (!circle_trip_filler_2.equals(other.circle_trip_filler_2)) {
			return false;
		}
		if (circle_trip_plus_ct2 == null) {
			if (other.circle_trip_plus_ct2 != null) {
				return false;
			}
		} else if (!circle_trip_plus_ct2.equals(other.circle_trip_plus_ct2)) {
			return false;
		}
		if (circle_trip_plus_cxr == null) {
			if (other.circle_trip_plus_cxr != null) {
				return false;
			}
		} else if (!circle_trip_plus_cxr.equals(other.circle_trip_plus_cxr)) {
			return false;
		}
		if (circle_trip_plus_fc == null) {
			if (other.circle_trip_plus_fc != null) {
				return false;
			}
		} else if (!circle_trip_plus_fc.equals(other.circle_trip_plus_fc)) {
			return false;
		}
		if (circle_trip_plus_tr == null) {
			if (other.circle_trip_plus_tr != null) {
				return false;
			}
		} else if (!circle_trip_plus_tr.equals(other.circle_trip_plus_tr)) {
			return false;
		}
		if (circle_trip_tr == null) {
			if (other.circle_trip_tr != null) {
				return false;
			}
		} else if (!circle_trip_tr.equals(other.circle_trip_tr)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Cat10CircleTrip [circle_trip_plus_tr=" + circle_trip_plus_tr + ", circle_trip_filler_1="
				+ circle_trip_filler_1 + ", circle_trip_plus_ct2=" + circle_trip_plus_ct2 + ", circle_trip_filler_2="
				+ circle_trip_filler_2 + ", circle_trip_plus_fc=" + circle_trip_plus_fc + ", circle_trip_ct2="
				+ circle_trip_ct2 + ", circle_trip_fc=" + circle_trip_fc + ", circle_trip_tr=" + circle_trip_tr
				+ ", circle_trip_plus_cxr=" + circle_trip_plus_cxr + ", circle_trip_cxr=" + circle_trip_cxr + "]";
	}
}
