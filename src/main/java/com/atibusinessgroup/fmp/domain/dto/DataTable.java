package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class DataTable {
	
	@Field("ri")
	private String relationalIndicator;
	
	@Field("cat_no")
	private String catNo;
	
	@Field("tbl_no")
	private String tableNo;
	
	@Field("io")
	private String inboundOutboundIndicator;
	
	@Field("di")
	private String directionalIndicator;

	public String getRelationalIndicator() {
		return relationalIndicator;
	}

	public void setRelationalIndicator(String relationalIndicator) {
		this.relationalIndicator = relationalIndicator;
	}

	public String getCatNo() {
		return catNo;
	}

	public void setCatNo(String catNo) {
		this.catNo = catNo;
	}

	public String getTableNo() {
		return tableNo;
	}

	public void setTableNo(String tableNo) {
		this.tableNo = tableNo;
	}

	public String getInboundOutboundIndicator() {
		return inboundOutboundIndicator;
	}

	public void setInboundOutboundIndicator(String inboundOutboundIndicator) {
		this.inboundOutboundIndicator = inboundOutboundIndicator;
	}

	public String getDirectionalIndicator() {
		return directionalIndicator;
	}

	public void setDirectionalIndicator(String directionalIndicator) {
		this.directionalIndicator = directionalIndicator;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((catNo == null) ? 0 : catNo.hashCode());
		result = prime * result + ((directionalIndicator == null) ? 0 : directionalIndicator.hashCode());
		result = prime * result + ((inboundOutboundIndicator == null) ? 0 : inboundOutboundIndicator.hashCode());
		result = prime * result + ((relationalIndicator == null) ? 0 : relationalIndicator.hashCode());
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
		DataTable other = (DataTable) obj;
		if (catNo == null) {
			if (other.catNo != null) {
				return false;
			}
		} else if (!catNo.equals(other.catNo)) {
			return false;
		}
		if (directionalIndicator == null) {
			if (other.directionalIndicator != null) {
				return false;
			}
		} else if (!directionalIndicator.equals(other.directionalIndicator)) {
			return false;
		}
		if (inboundOutboundIndicator == null) {
			if (other.inboundOutboundIndicator != null) {
				return false;
			}
		} else if (!inboundOutboundIndicator.equals(other.inboundOutboundIndicator)) {
			return false;
		}
		if (relationalIndicator == null) {
			if (other.relationalIndicator != null) {
				return false;
			}
		} else if (!relationalIndicator.equals(other.relationalIndicator)) {
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
		return "DataTable [relationalIndicator=" + relationalIndicator + ", catNo=" + catNo + ", tableNo=" + tableNo
				+ ", inboundOutboundIndicator=" + inboundOutboundIndicator + ", directionalIndicator="
				+ directionalIndicator + "]";
	}
}
