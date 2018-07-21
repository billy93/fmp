package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class TaxSectorDetail {

	@Field("sector_detail_tbl_no_167")
	private String sector_detail_tbl_no_167;

	@Field("sector_detail_app_tag")
    private String sector_detail_app_tag;

	public String getSector_detail_tbl_no_167() {
		return sector_detail_tbl_no_167;
	}

	public void setSector_detail_tbl_no_167(String sector_detail_tbl_no_167) {
		this.sector_detail_tbl_no_167 = sector_detail_tbl_no_167;
	}

	public String getSector_detail_app_tag() {
		return sector_detail_app_tag;
	}

	public void setSector_detail_app_tag(String sector_detail_app_tag) {
		this.sector_detail_app_tag = sector_detail_app_tag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sector_detail_app_tag == null) ? 0 : sector_detail_app_tag.hashCode());
		result = prime * result + ((sector_detail_tbl_no_167 == null) ? 0 : sector_detail_tbl_no_167.hashCode());
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
		TaxSectorDetail other = (TaxSectorDetail) obj;
		if (sector_detail_app_tag == null) {
			if (other.sector_detail_app_tag != null) {
				return false;
			}
		} else if (!sector_detail_app_tag.equals(other.sector_detail_app_tag)) {
			return false;
		}
		if (sector_detail_tbl_no_167 == null) {
			if (other.sector_detail_tbl_no_167 != null) {
				return false;
			}
		} else if (!sector_detail_tbl_no_167.equals(other.sector_detail_tbl_no_167)) {
			return false;
		}
		return true;
	}
}
