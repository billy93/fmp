package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class GeneralRuleApplication {
	
	@Field("cat_no")
	private String catNo;

	@Field("src_tar")
    private String sourceTariff;

	@Field("rule_no")
    private String ruleNo;

	public String getCatNo() {
		return catNo;
	}

	public void setCatNo(String catNo) {
		this.catNo = catNo;
	}

	public String getSourceTariff() {
		return sourceTariff;
	}

	public void setSourceTariff(String sourceTariff) {
		this.sourceTariff = sourceTariff;
	}

	public String getRuleNo() {
		return ruleNo;
	}

	public void setRuleNo(String ruleNo) {
		this.ruleNo = ruleNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((catNo == null) ? 0 : catNo.hashCode());
		result = prime * result + ((ruleNo == null) ? 0 : ruleNo.hashCode());
		result = prime * result + ((sourceTariff == null) ? 0 : sourceTariff.hashCode());
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
		GeneralRuleApplication other = (GeneralRuleApplication) obj;
		if (catNo == null) {
			if (other.catNo != null) {
				return false;
			}
		} else if (!catNo.equals(other.catNo)) {
			return false;
		}
		if (ruleNo == null) {
			if (other.ruleNo != null) {
				return false;
			}
		} else if (!ruleNo.equals(other.ruleNo)) {
			return false;
		}
		if (sourceTariff == null) {
			if (other.sourceTariff != null) {
				return false;
			}
		} else if (!sourceTariff.equals(other.sourceTariff)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "GeneralRuleApplication [catNo=" + catNo + ", sourceTariff=" + sourceTariff + ", ruleNo=" + ruleNo + "]";
	}
}
