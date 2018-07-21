package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class TaxPointLoc {

	@Field("tax_point_loc_3_type_matching")
	private String tax_point_loc_3_type_matching;

	@Field("tax_point_loc_2_intl_domestic")
    private String tax_point_loc_2_intl_domestic;

	@Field("tax_point_loc_2_comparison")
    private String tax_point_loc_2_comparison;

	@Field("tax_point_loc_3_type")
    private String tax_point_loc_3_type;

	@Field("tax_point_loc_1_adjacent")
    private String tax_point_loc_1_adjacent;

	@Field("tax_point_loc_2_type")
    private String tax_point_loc_2_type;

	@Field("tax_point_loc_1_stopover_tag")
    private String tax_point_loc_1_stopover_tag;

	@Field("tax_point_loc_1_transfer_type")
    private String tax_point_loc_1_transfer_type;

	@Field("tax_point_loc_3_value")
    private String tax_point_loc_3_value;

	@Field("tax_point_loc_2_value")
    private String tax_point_loc_2_value;

	@Field("tax_point_loc_1_value")
    private String tax_point_loc_1_value;

	@Field("tax_point_loc_1_type")
    private String tax_point_loc_1_type;

	@Field("tax_point_loc_2_stopover_tag")
    private String tax_point_loc_2_stopover_tag;

	public String getTax_point_loc_3_type_matching() {
		return tax_point_loc_3_type_matching;
	}

	public void setTax_point_loc_3_type_matching(String tax_point_loc_3_type_matching) {
		this.tax_point_loc_3_type_matching = tax_point_loc_3_type_matching;
	}

	public String getTax_point_loc_2_intl_domestic() {
		return tax_point_loc_2_intl_domestic;
	}

	public void setTax_point_loc_2_intl_domestic(String tax_point_loc_2_intl_domestic) {
		this.tax_point_loc_2_intl_domestic = tax_point_loc_2_intl_domestic;
	}

	public String getTax_point_loc_2_comparison() {
		return tax_point_loc_2_comparison;
	}

	public void setTax_point_loc_2_comparison(String tax_point_loc_2_comparison) {
		this.tax_point_loc_2_comparison = tax_point_loc_2_comparison;
	}

	public String getTax_point_loc_3_type() {
		return tax_point_loc_3_type;
	}

	public void setTax_point_loc_3_type(String tax_point_loc_3_type) {
		this.tax_point_loc_3_type = tax_point_loc_3_type;
	}

	public String getTax_point_loc_1_adjacent() {
		return tax_point_loc_1_adjacent;
	}

	public void setTax_point_loc_1_adjacent(String tax_point_loc_1_adjacent) {
		this.tax_point_loc_1_adjacent = tax_point_loc_1_adjacent;
	}

	public String getTax_point_loc_2_type() {
		return tax_point_loc_2_type;
	}

	public void setTax_point_loc_2_type(String tax_point_loc_2_type) {
		this.tax_point_loc_2_type = tax_point_loc_2_type;
	}

	public String getTax_point_loc_1_stopover_tag() {
		return tax_point_loc_1_stopover_tag;
	}

	public void setTax_point_loc_1_stopover_tag(String tax_point_loc_1_stopover_tag) {
		this.tax_point_loc_1_stopover_tag = tax_point_loc_1_stopover_tag;
	}

	public String getTax_point_loc_1_transfer_type() {
		return tax_point_loc_1_transfer_type;
	}

	public void setTax_point_loc_1_transfer_type(String tax_point_loc_1_transfer_type) {
		this.tax_point_loc_1_transfer_type = tax_point_loc_1_transfer_type;
	}

	public String getTax_point_loc_3_value() {
		return tax_point_loc_3_value;
	}

	public void setTax_point_loc_3_value(String tax_point_loc_3_value) {
		this.tax_point_loc_3_value = tax_point_loc_3_value;
	}

	public String getTax_point_loc_2_value() {
		return tax_point_loc_2_value;
	}

	public void setTax_point_loc_2_value(String tax_point_loc_2_value) {
		this.tax_point_loc_2_value = tax_point_loc_2_value;
	}

	public String getTax_point_loc_1_value() {
		return tax_point_loc_1_value;
	}

	public void setTax_point_loc_1_value(String tax_point_loc_1_value) {
		this.tax_point_loc_1_value = tax_point_loc_1_value;
	}

	public String getTax_point_loc_1_type() {
		return tax_point_loc_1_type;
	}

	public void setTax_point_loc_1_type(String tax_point_loc_1_type) {
		this.tax_point_loc_1_type = tax_point_loc_1_type;
	}

	public String getTax_point_loc_2_stopover_tag() {
		return tax_point_loc_2_stopover_tag;
	}

	public void setTax_point_loc_2_stopover_tag(String tax_point_loc_2_stopover_tag) {
		this.tax_point_loc_2_stopover_tag = tax_point_loc_2_stopover_tag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tax_point_loc_1_adjacent == null) ? 0 : tax_point_loc_1_adjacent.hashCode());
		result = prime * result
				+ ((tax_point_loc_1_stopover_tag == null) ? 0 : tax_point_loc_1_stopover_tag.hashCode());
		result = prime * result
				+ ((tax_point_loc_1_transfer_type == null) ? 0 : tax_point_loc_1_transfer_type.hashCode());
		result = prime * result + ((tax_point_loc_1_type == null) ? 0 : tax_point_loc_1_type.hashCode());
		result = prime * result + ((tax_point_loc_1_value == null) ? 0 : tax_point_loc_1_value.hashCode());
		result = prime * result + ((tax_point_loc_2_comparison == null) ? 0 : tax_point_loc_2_comparison.hashCode());
		result = prime * result
				+ ((tax_point_loc_2_intl_domestic == null) ? 0 : tax_point_loc_2_intl_domestic.hashCode());
		result = prime * result
				+ ((tax_point_loc_2_stopover_tag == null) ? 0 : tax_point_loc_2_stopover_tag.hashCode());
		result = prime * result + ((tax_point_loc_2_type == null) ? 0 : tax_point_loc_2_type.hashCode());
		result = prime * result + ((tax_point_loc_2_value == null) ? 0 : tax_point_loc_2_value.hashCode());
		result = prime * result + ((tax_point_loc_3_type == null) ? 0 : tax_point_loc_3_type.hashCode());
		result = prime * result
				+ ((tax_point_loc_3_type_matching == null) ? 0 : tax_point_loc_3_type_matching.hashCode());
		result = prime * result + ((tax_point_loc_3_value == null) ? 0 : tax_point_loc_3_value.hashCode());
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
		TaxPointLoc other = (TaxPointLoc) obj;
		if (tax_point_loc_1_adjacent == null) {
			if (other.tax_point_loc_1_adjacent != null) {
				return false;
			}
		} else if (!tax_point_loc_1_adjacent.equals(other.tax_point_loc_1_adjacent)) {
			return false;
		}
		if (tax_point_loc_1_stopover_tag == null) {
			if (other.tax_point_loc_1_stopover_tag != null) {
				return false;
			}
		} else if (!tax_point_loc_1_stopover_tag.equals(other.tax_point_loc_1_stopover_tag)) {
			return false;
		}
		if (tax_point_loc_1_transfer_type == null) {
			if (other.tax_point_loc_1_transfer_type != null) {
				return false;
			}
		} else if (!tax_point_loc_1_transfer_type.equals(other.tax_point_loc_1_transfer_type)) {
			return false;
		}
		if (tax_point_loc_1_type == null) {
			if (other.tax_point_loc_1_type != null) {
				return false;
			}
		} else if (!tax_point_loc_1_type.equals(other.tax_point_loc_1_type)) {
			return false;
		}
		if (tax_point_loc_1_value == null) {
			if (other.tax_point_loc_1_value != null) {
				return false;
			}
		} else if (!tax_point_loc_1_value.equals(other.tax_point_loc_1_value)) {
			return false;
		}
		if (tax_point_loc_2_comparison == null) {
			if (other.tax_point_loc_2_comparison != null) {
				return false;
			}
		} else if (!tax_point_loc_2_comparison.equals(other.tax_point_loc_2_comparison)) {
			return false;
		}
		if (tax_point_loc_2_intl_domestic == null) {
			if (other.tax_point_loc_2_intl_domestic != null) {
				return false;
			}
		} else if (!tax_point_loc_2_intl_domestic.equals(other.tax_point_loc_2_intl_domestic)) {
			return false;
		}
		if (tax_point_loc_2_stopover_tag == null) {
			if (other.tax_point_loc_2_stopover_tag != null) {
				return false;
			}
		} else if (!tax_point_loc_2_stopover_tag.equals(other.tax_point_loc_2_stopover_tag)) {
			return false;
		}
		if (tax_point_loc_2_type == null) {
			if (other.tax_point_loc_2_type != null) {
				return false;
			}
		} else if (!tax_point_loc_2_type.equals(other.tax_point_loc_2_type)) {
			return false;
		}
		if (tax_point_loc_2_value == null) {
			if (other.tax_point_loc_2_value != null) {
				return false;
			}
		} else if (!tax_point_loc_2_value.equals(other.tax_point_loc_2_value)) {
			return false;
		}
		if (tax_point_loc_3_type == null) {
			if (other.tax_point_loc_3_type != null) {
				return false;
			}
		} else if (!tax_point_loc_3_type.equals(other.tax_point_loc_3_type)) {
			return false;
		}
		if (tax_point_loc_3_type_matching == null) {
			if (other.tax_point_loc_3_type_matching != null) {
				return false;
			}
		} else if (!tax_point_loc_3_type_matching.equals(other.tax_point_loc_3_type_matching)) {
			return false;
		}
		if (tax_point_loc_3_value == null) {
			if (other.tax_point_loc_3_value != null) {
				return false;
			}
		} else if (!tax_point_loc_3_value.equals(other.tax_point_loc_3_value)) {
			return false;
		}
		return true;
	}
}
