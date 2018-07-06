package com.atibusinessgroup.fmp.domain.dto;

import java.util.Date;

public class AtpcoDateWrapper {
	private Date startDate;
	private Date endDate;
	private Date completeDate;
	private Date resStartDate;
	private Date resEndDate;
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Date getCompleteDate() {
		return completeDate;
	}
	
	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}
	
	public Date getResStartDate() {
		return resStartDate;
	}
	
	public void setResStartDate(Date resStartDate) {
		this.resStartDate = resStartDate;
	}
	
	public Date getResEndDate() {
		return resEndDate;
	}
	
	public void setResEndDate(Date resEndDate) {
		this.resEndDate = resEndDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((completeDate == null) ? 0 : completeDate.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((resEndDate == null) ? 0 : resEndDate.hashCode());
		result = prime * result + ((resStartDate == null) ? 0 : resStartDate.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		AtpcoDateWrapper other = (AtpcoDateWrapper) obj;
		if (completeDate == null) {
			if (other.completeDate != null) {
				return false;
			}
		} else if (!completeDate.equals(other.completeDate)) {
			return false;
		}
		if (endDate == null) {
			if (other.endDate != null) {
				return false;
			}
		} else if (!endDate.equals(other.endDate)) {
			return false;
		}
		if (resEndDate == null) {
			if (other.resEndDate != null) {
				return false;
			}
		} else if (!resEndDate.equals(other.resEndDate)) {
			return false;
		}
		if (resStartDate == null) {
			if (other.resStartDate != null) {
				return false;
			}
		} else if (!resStartDate.equals(other.resStartDate)) {
			return false;
		}
		if (startDate == null) {
			if (other.startDate != null) {
				return false;
			}
		} else if (!startDate.equals(other.startDate)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoDateWrapper [startDate=" + startDate + ", endDate=" + endDate + ", completeDate=" + completeDate
				+ ", resStartDate=" + resStartDate + ", resEndDate=" + resEndDate + "]";
	}
}
