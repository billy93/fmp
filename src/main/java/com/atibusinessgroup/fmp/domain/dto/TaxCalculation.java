package com.atibusinessgroup.fmp.domain.dto;

import org.bson.types.Decimal128;
import org.springframework.data.mongodb.core.mapping.Field;

public class TaxCalculation {

	@Field("tax_calculation_percent")
	private Decimal128 tax_calculation_percent;

	@Field("tax_calculation_cur")
    private String tax_calculation_cur;

	@Field("tax_calculation_applies_to_tag")
    private String tax_calculation_applies_to_tag;

	@Field("tax_calculation_net_remit_app_tag")
    private String tax_calculation_net_remit_app_tag;

	@Field("tax_calculation_vat_inclusive_indicator")
    private String tax_calculation_vat_inclusive_indicator;

	@Field("tax_calculation_min_max_cur")
    private String tax_calculation_min_max_cur;

	@Field("tax_calculation_amount")
    private Decimal128 tax_calculation_amount;

	@Field("tax_calculation_dec")
    private int tax_calculation_dec;

	@Field("tax_calculation_min_max_dec")
    private int tax_calculation_min_max_dec;

	@Field("tax_calculation_app_limit")
    private String tax_calculation_app_limit;

	@Field("tax_calculation_min")
    private Decimal128 tax_calculation_min;

	@Field("tax_calculation_max")
    private Decimal128 tax_calculation_max;

	public Decimal128 getTax_calculation_percent() {
		return tax_calculation_percent;
	}

	public void setTax_calculation_percent(Decimal128 tax_calculation_percent) {
		this.tax_calculation_percent = tax_calculation_percent;
	}

	public String getTax_calculation_cur() {
		return tax_calculation_cur;
	}

	public void setTax_calculation_cur(String tax_calculation_cur) {
		this.tax_calculation_cur = tax_calculation_cur;
	}

	public String getTax_calculation_applies_to_tag() {
		return tax_calculation_applies_to_tag;
	}

	public void setTax_calculation_applies_to_tag(String tax_calculation_applies_to_tag) {
		this.tax_calculation_applies_to_tag = tax_calculation_applies_to_tag;
	}

	public String getTax_calculation_net_remit_app_tag() {
		return tax_calculation_net_remit_app_tag;
	}

	public void setTax_calculation_net_remit_app_tag(String tax_calculation_net_remit_app_tag) {
		this.tax_calculation_net_remit_app_tag = tax_calculation_net_remit_app_tag;
	}

	public String getTax_calculation_vat_inclusive_indicator() {
		return tax_calculation_vat_inclusive_indicator;
	}

	public void setTax_calculation_vat_inclusive_indicator(String tax_calculation_vat_inclusive_indicator) {
		this.tax_calculation_vat_inclusive_indicator = tax_calculation_vat_inclusive_indicator;
	}

	public String getTax_calculation_min_max_cur() {
		return tax_calculation_min_max_cur;
	}

	public void setTax_calculation_min_max_cur(String tax_calculation_min_max_cur) {
		this.tax_calculation_min_max_cur = tax_calculation_min_max_cur;
	}

	public Decimal128 getTax_calculation_amount() {
		return tax_calculation_amount;
	}

	public void setTax_calculation_amount(Decimal128 tax_calculation_amount) {
		this.tax_calculation_amount = tax_calculation_amount;
	}

	public int getTax_calculation_dec() {
		return tax_calculation_dec;
	}

	public void setTax_calculation_dec(int tax_calculation_dec) {
		this.tax_calculation_dec = tax_calculation_dec;
	}

	public int getTax_calculation_min_max_dec() {
		return tax_calculation_min_max_dec;
	}

	public void setTax_calculation_min_max_dec(int tax_calculation_min_max_dec) {
		this.tax_calculation_min_max_dec = tax_calculation_min_max_dec;
	}

	public String getTax_calculation_app_limit() {
		return tax_calculation_app_limit;
	}

	public void setTax_calculation_app_limit(String tax_calculation_app_limit) {
		this.tax_calculation_app_limit = tax_calculation_app_limit;
	}

	public Decimal128 getTax_calculation_min() {
		return tax_calculation_min;
	}

	public void setTax_calculation_min(Decimal128 tax_calculation_min) {
		this.tax_calculation_min = tax_calculation_min;
	}

	public Decimal128 getTax_calculation_max() {
		return tax_calculation_max;
	}

	public void setTax_calculation_max(Decimal128 tax_calculation_max) {
		this.tax_calculation_max = tax_calculation_max;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tax_calculation_amount == null) ? 0 : tax_calculation_amount.hashCode());
		result = prime * result + ((tax_calculation_app_limit == null) ? 0 : tax_calculation_app_limit.hashCode());
		result = prime * result
				+ ((tax_calculation_applies_to_tag == null) ? 0 : tax_calculation_applies_to_tag.hashCode());
		result = prime * result + ((tax_calculation_cur == null) ? 0 : tax_calculation_cur.hashCode());
		result = prime * result + tax_calculation_dec;
		result = prime * result + ((tax_calculation_max == null) ? 0 : tax_calculation_max.hashCode());
		result = prime * result + ((tax_calculation_min == null) ? 0 : tax_calculation_min.hashCode());
		result = prime * result + ((tax_calculation_min_max_cur == null) ? 0 : tax_calculation_min_max_cur.hashCode());
		result = prime * result + tax_calculation_min_max_dec;
		result = prime * result
				+ ((tax_calculation_net_remit_app_tag == null) ? 0 : tax_calculation_net_remit_app_tag.hashCode());
		result = prime * result + ((tax_calculation_percent == null) ? 0 : tax_calculation_percent.hashCode());
		result = prime * result + ((tax_calculation_vat_inclusive_indicator == null) ? 0
				: tax_calculation_vat_inclusive_indicator.hashCode());
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
		TaxCalculation other = (TaxCalculation) obj;
		if (tax_calculation_amount == null) {
			if (other.tax_calculation_amount != null) {
				return false;
			}
		} else if (!tax_calculation_amount.equals(other.tax_calculation_amount)) {
			return false;
		}
		if (tax_calculation_app_limit == null) {
			if (other.tax_calculation_app_limit != null) {
				return false;
			}
		} else if (!tax_calculation_app_limit.equals(other.tax_calculation_app_limit)) {
			return false;
		}
		if (tax_calculation_applies_to_tag == null) {
			if (other.tax_calculation_applies_to_tag != null) {
				return false;
			}
		} else if (!tax_calculation_applies_to_tag.equals(other.tax_calculation_applies_to_tag)) {
			return false;
		}
		if (tax_calculation_cur == null) {
			if (other.tax_calculation_cur != null) {
				return false;
			}
		} else if (!tax_calculation_cur.equals(other.tax_calculation_cur)) {
			return false;
		}
		if (tax_calculation_dec != other.tax_calculation_dec) {
			return false;
		}
		if (tax_calculation_max == null) {
			if (other.tax_calculation_max != null) {
				return false;
			}
		} else if (!tax_calculation_max.equals(other.tax_calculation_max)) {
			return false;
		}
		if (tax_calculation_min == null) {
			if (other.tax_calculation_min != null) {
				return false;
			}
		} else if (!tax_calculation_min.equals(other.tax_calculation_min)) {
			return false;
		}
		if (tax_calculation_min_max_cur == null) {
			if (other.tax_calculation_min_max_cur != null) {
				return false;
			}
		} else if (!tax_calculation_min_max_cur.equals(other.tax_calculation_min_max_cur)) {
			return false;
		}
		if (tax_calculation_min_max_dec != other.tax_calculation_min_max_dec) {
			return false;
		}
		if (tax_calculation_net_remit_app_tag == null) {
			if (other.tax_calculation_net_remit_app_tag != null) {
				return false;
			}
		} else if (!tax_calculation_net_remit_app_tag.equals(other.tax_calculation_net_remit_app_tag)) {
			return false;
		}
		if (tax_calculation_percent == null) {
			if (other.tax_calculation_percent != null) {
				return false;
			}
		} else if (!tax_calculation_percent.equals(other.tax_calculation_percent)) {
			return false;
		}
		if (tax_calculation_vat_inclusive_indicator == null) {
			if (other.tax_calculation_vat_inclusive_indicator != null) {
				return false;
			}
		} else if (!tax_calculation_vat_inclusive_indicator.equals(other.tax_calculation_vat_inclusive_indicator)) {
			return false;
		}
		return true;
	}
}
