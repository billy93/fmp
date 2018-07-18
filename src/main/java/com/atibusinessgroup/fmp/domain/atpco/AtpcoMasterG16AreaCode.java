package com.atibusinessgroup.fmp.domain.atpco;

import org.springframework.data.mongodb.core.mapping.Field;

public class AtpcoMasterG16AreaCode {
	
	@Field("areas_code_4")
	private String areas_code_4;

	@Field("areas_code_3")
    private String areas_code_3;

	@Field("areas_code_2")
    private String areas_code_2;

	@Field("areas_code_1")
    private String areas_code_1;

	public String getAreas_code_4() {
		return areas_code_4;
	}

	public void setAreas_code_4(String areas_code_4) {
		this.areas_code_4 = areas_code_4;
	}

	public String getAreas_code_3() {
		return areas_code_3;
	}

	public void setAreas_code_3(String areas_code_3) {
		this.areas_code_3 = areas_code_3;
	}

	public String getAreas_code_2() {
		return areas_code_2;
	}

	public void setAreas_code_2(String areas_code_2) {
		this.areas_code_2 = areas_code_2;
	}

	public String getAreas_code_1() {
		return areas_code_1;
	}

	public void setAreas_code_1(String areas_code_1) {
		this.areas_code_1 = areas_code_1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((areas_code_1 == null) ? 0 : areas_code_1.hashCode());
		result = prime * result + ((areas_code_2 == null) ? 0 : areas_code_2.hashCode());
		result = prime * result + ((areas_code_3 == null) ? 0 : areas_code_3.hashCode());
		result = prime * result + ((areas_code_4 == null) ? 0 : areas_code_4.hashCode());
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
		AtpcoMasterG16AreaCode other = (AtpcoMasterG16AreaCode) obj;
		if (areas_code_1 == null) {
			if (other.areas_code_1 != null) {
				return false;
			}
		} else if (!areas_code_1.equals(other.areas_code_1)) {
			return false;
		}
		if (areas_code_2 == null) {
			if (other.areas_code_2 != null) {
				return false;
			}
		} else if (!areas_code_2.equals(other.areas_code_2)) {
			return false;
		}
		if (areas_code_3 == null) {
			if (other.areas_code_3 != null) {
				return false;
			}
		} else if (!areas_code_3.equals(other.areas_code_3)) {
			return false;
		}
		if (areas_code_4 == null) {
			if (other.areas_code_4 != null) {
				return false;
			}
		} else if (!areas_code_4.equals(other.areas_code_4)) {
			return false;
		}
		return true;
	}
}
