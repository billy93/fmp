package com.atibusinessgroup.fmp.domain.atpco;

import org.springframework.data.mongodb.core.mapping.Field;

public class AtpcoMasterG16ArbitaryTariff {
	
	@Field("arbitary_tariff_no")
	private String arbitary_tariff_no;

	@Field("arbitary_tariff_cd")
    private String arbitary_tariff_cd;

	public String getArbitary_tariff_no() {
		return arbitary_tariff_no;
	}

	public void setArbitary_tariff_no(String arbitary_tariff_no) {
		this.arbitary_tariff_no = arbitary_tariff_no;
	}

	public String getArbitary_tariff_cd() {
		return arbitary_tariff_cd;
	}

	public void setArbitary_tariff_cd(String arbitary_tariff_cd) {
		this.arbitary_tariff_cd = arbitary_tariff_cd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arbitary_tariff_cd == null) ? 0 : arbitary_tariff_cd.hashCode());
		result = prime * result + ((arbitary_tariff_no == null) ? 0 : arbitary_tariff_no.hashCode());
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
		AtpcoMasterG16ArbitaryTariff other = (AtpcoMasterG16ArbitaryTariff) obj;
		if (arbitary_tariff_cd == null) {
			if (other.arbitary_tariff_cd != null) {
				return false;
			}
		} else if (!arbitary_tariff_cd.equals(other.arbitary_tariff_cd)) {
			return false;
		}
		if (arbitary_tariff_no == null) {
			if (other.arbitary_tariff_no != null) {
				return false;
			}
		} else if (!arbitary_tariff_no.equals(other.arbitary_tariff_no)) {
			return false;
		}
		return true;
	}
}
