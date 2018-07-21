package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class TaxFiller {

	@Field("filler_8")
	private String filler_8;

	@Field("filler_7")
    private String filler_7;

	@Field("filler_6")
    private String filler_6;

	@Field("filler_5")
    private String filler_5;

	@Field("filler_4")
    private String filler_4;

	@Field("filler_3")
    private String filler_3;

	@Field("filler_2")
    private String filler_2;

	@Field("filler_1")
    private String filler_1;

	public String getFiller_8() {
		return filler_8;
	}

	public void setFiller_8(String filler_8) {
		this.filler_8 = filler_8;
	}

	public String getFiller_7() {
		return filler_7;
	}

	public void setFiller_7(String filler_7) {
		this.filler_7 = filler_7;
	}

	public String getFiller_6() {
		return filler_6;
	}

	public void setFiller_6(String filler_6) {
		this.filler_6 = filler_6;
	}

	public String getFiller_5() {
		return filler_5;
	}

	public void setFiller_5(String filler_5) {
		this.filler_5 = filler_5;
	}

	public String getFiller_4() {
		return filler_4;
	}

	public void setFiller_4(String filler_4) {
		this.filler_4 = filler_4;
	}

	public String getFiller_3() {
		return filler_3;
	}

	public void setFiller_3(String filler_3) {
		this.filler_3 = filler_3;
	}

	public String getFiller_2() {
		return filler_2;
	}

	public void setFiller_2(String filler_2) {
		this.filler_2 = filler_2;
	}

	public String getFiller_1() {
		return filler_1;
	}

	public void setFiller_1(String filler_1) {
		this.filler_1 = filler_1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filler_1 == null) ? 0 : filler_1.hashCode());
		result = prime * result + ((filler_2 == null) ? 0 : filler_2.hashCode());
		result = prime * result + ((filler_3 == null) ? 0 : filler_3.hashCode());
		result = prime * result + ((filler_4 == null) ? 0 : filler_4.hashCode());
		result = prime * result + ((filler_5 == null) ? 0 : filler_5.hashCode());
		result = prime * result + ((filler_6 == null) ? 0 : filler_6.hashCode());
		result = prime * result + ((filler_7 == null) ? 0 : filler_7.hashCode());
		result = prime * result + ((filler_8 == null) ? 0 : filler_8.hashCode());
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
		TaxFiller other = (TaxFiller) obj;
		if (filler_1 == null) {
			if (other.filler_1 != null) {
				return false;
			}
		} else if (!filler_1.equals(other.filler_1)) {
			return false;
		}
		if (filler_2 == null) {
			if (other.filler_2 != null) {
				return false;
			}
		} else if (!filler_2.equals(other.filler_2)) {
			return false;
		}
		if (filler_3 == null) {
			if (other.filler_3 != null) {
				return false;
			}
		} else if (!filler_3.equals(other.filler_3)) {
			return false;
		}
		if (filler_4 == null) {
			if (other.filler_4 != null) {
				return false;
			}
		} else if (!filler_4.equals(other.filler_4)) {
			return false;
		}
		if (filler_5 == null) {
			if (other.filler_5 != null) {
				return false;
			}
		} else if (!filler_5.equals(other.filler_5)) {
			return false;
		}
		if (filler_6 == null) {
			if (other.filler_6 != null) {
				return false;
			}
		} else if (!filler_6.equals(other.filler_6)) {
			return false;
		}
		if (filler_7 == null) {
			if (other.filler_7 != null) {
				return false;
			}
		} else if (!filler_7.equals(other.filler_7)) {
			return false;
		}
		if (filler_8 == null) {
			if (other.filler_8 != null) {
				return false;
			}
		} else if (!filler_8.equals(other.filler_8)) {
			return false;
		}
		return true;
	}
}
