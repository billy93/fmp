package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class TaxInformation {

	@Field("tax_processing_app")
	private String tax_processing_app;

	@Field("tax_rule_alternate_reff")
    private String tax_rule_alternate_reff;

	@Field("tax_round_dir")
    private String tax_round_dir;

	@Field("tax_matching_app")
    private String tax_matching_app;

	@Field("tax_round_unit")
    private String tax_round_unit;

	public String getTax_processing_app() {
		return tax_processing_app;
	}

	public void setTax_processing_app(String tax_processing_app) {
		this.tax_processing_app = tax_processing_app;
	}

	public String getTax_rule_alternate_reff() {
		return tax_rule_alternate_reff;
	}

	public void setTax_rule_alternate_reff(String tax_rule_alternate_reff) {
		this.tax_rule_alternate_reff = tax_rule_alternate_reff;
	}

	public String getTax_round_dir() {
		return tax_round_dir;
	}

	public void setTax_round_dir(String tax_round_dir) {
		this.tax_round_dir = tax_round_dir;
	}

	public String getTax_matching_app() {
		return tax_matching_app;
	}

	public void setTax_matching_app(String tax_matching_app) {
		this.tax_matching_app = tax_matching_app;
	}

	public String getTax_round_unit() {
		return tax_round_unit;
	}

	public void setTax_round_unit(String tax_round_unit) {
		this.tax_round_unit = tax_round_unit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tax_matching_app == null) ? 0 : tax_matching_app.hashCode());
		result = prime * result + ((tax_processing_app == null) ? 0 : tax_processing_app.hashCode());
		result = prime * result + ((tax_round_dir == null) ? 0 : tax_round_dir.hashCode());
		result = prime * result + ((tax_round_unit == null) ? 0 : tax_round_unit.hashCode());
		result = prime * result + ((tax_rule_alternate_reff == null) ? 0 : tax_rule_alternate_reff.hashCode());
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
		TaxInformation other = (TaxInformation) obj;
		if (tax_matching_app == null) {
			if (other.tax_matching_app != null) {
				return false;
			}
		} else if (!tax_matching_app.equals(other.tax_matching_app)) {
			return false;
		}
		if (tax_processing_app == null) {
			if (other.tax_processing_app != null) {
				return false;
			}
		} else if (!tax_processing_app.equals(other.tax_processing_app)) {
			return false;
		}
		if (tax_round_dir == null) {
			if (other.tax_round_dir != null) {
				return false;
			}
		} else if (!tax_round_dir.equals(other.tax_round_dir)) {
			return false;
		}
		if (tax_round_unit == null) {
			if (other.tax_round_unit != null) {
				return false;
			}
		} else if (!tax_round_unit.equals(other.tax_round_unit)) {
			return false;
		}
		if (tax_rule_alternate_reff == null) {
			if (other.tax_rule_alternate_reff != null) {
				return false;
			}
		} else if (!tax_rule_alternate_reff.equals(other.tax_rule_alternate_reff)) {
			return false;
		}
		return true;
	}
}
