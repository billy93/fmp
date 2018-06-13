package com.atibusinessgroup.fmp.domain.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

public class FlightTable {
	
	@Field("tbl_no")
	private String tableNo;
	
	@Field("carrier_flight")
	private List<CarrierField> carriers = new ArrayList<>();

	public String getTableNo() {
		return tableNo;
	}

	public void setTableNo(String tableNo) {
		this.tableNo = tableNo;
	}

	public List<CarrierField> getCarriers() {
		return carriers;
	}

	public void setCarriers(List<CarrierField> carriers) {
		this.carriers = carriers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carriers == null) ? 0 : carriers.hashCode());
		result = prime * result + ((tableNo == null) ? 0 : tableNo.hashCode());
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
		FlightTable other = (FlightTable) obj;
		if (carriers == null) {
			if (other.carriers != null) {
				return false;
			}
		} else if (!carriers.equals(other.carriers)) {
			return false;
		}
		if (tableNo == null) {
			if (other.tableNo != null) {
				return false;
			}
		} else if (!tableNo.equals(other.tableNo)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "FlightTable [tableNo=" + tableNo + ", carriers=" + carriers + "]";
	}
}
