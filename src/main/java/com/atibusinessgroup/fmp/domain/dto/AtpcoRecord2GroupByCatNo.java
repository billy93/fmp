package com.atibusinessgroup.fmp.domain.dto;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord2;

public class AtpcoRecord2GroupByCatNo {
	
	@Field("_id")
    private String catNo;
	
	@Field("record_2")
    private List<AtpcoRecord2> records2;

	public String getCatNo() {
		return catNo;
	}

	public void setCatNo(String catNo) {
		this.catNo = catNo;
	}

	public List<AtpcoRecord2> getRecords2() {
		return records2;
	}

	public void setRecords2(List<AtpcoRecord2> records2) {
		this.records2 = records2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((catNo == null) ? 0 : catNo.hashCode());
		result = prime * result + ((records2 == null) ? 0 : records2.hashCode());
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
		AtpcoRecord2GroupByCatNo other = (AtpcoRecord2GroupByCatNo) obj;
		if (catNo == null) {
			if (other.catNo != null) {
				return false;
			}
		} else if (!catNo.equals(other.catNo)) {
			return false;
		}
		if (records2 == null) {
			if (other.records2 != null) {
				return false;
			}
		} else if (!records2.equals(other.records2)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoRecord2GroupByCatNo [catNo=" + catNo + ", records2=" + records2 + "]";
	}
}
