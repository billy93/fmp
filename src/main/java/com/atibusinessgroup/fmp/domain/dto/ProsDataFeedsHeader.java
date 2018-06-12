package com.atibusinessgroup.fmp.domain.dto;

public class ProsDataFeedsHeader {

	private final String separator = " ";
	private String recordId;
	private String StartDate;
	private String EndDate;
	private String creationDate;

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getStartDate() {
		return StartDate;
	}

	public void setStartDate(String startDate) {
		StartDate = startDate;
	}

	public String getEndDate() {
		return EndDate;
	}

	public void setEndDate(String endDate) {
		EndDate = endDate;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((EndDate == null) ? 0 : EndDate.hashCode());
		result = prime * result + ((StartDate == null) ? 0 : StartDate.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((recordId == null) ? 0 : recordId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ProsDataFeedsHeader))
			return false;
		ProsDataFeedsHeader other = (ProsDataFeedsHeader) obj;
		if (EndDate == null) {
			if (other.EndDate != null)
				return false;
		} else if (!EndDate.equals(other.EndDate))
			return false;
		if (StartDate == null) {
			if (other.StartDate != null)
				return false;
		} else if (!StartDate.equals(other.StartDate))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (recordId == null) {
			if (other.recordId != null)
				return false;
		} else if (!recordId.equals(other.recordId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProsDataFeedsHeader [recordId=" + recordId + ", StartDate=" + StartDate + ", EndDate=" + EndDate
				+ ", creationDate=" + creationDate + "]";
	}

	public String build() {
		return recordId + separator + StartDate + separator + EndDate + separator + creationDate;
	}
}
