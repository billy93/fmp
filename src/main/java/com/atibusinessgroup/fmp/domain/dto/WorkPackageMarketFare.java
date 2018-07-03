package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import com.atibusinessgroup.fmp.domain.WorkPackageFare;

public class WorkPackageMarketFare {
	
	@Id
	private String id;
	
	@Field("fares")
	private WorkPackageFare fare;
	
	@Field("wpid")
	private String woId;

	@Field("wpname")
	private String woName;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public WorkPackageFare getFare() {
		return fare;
	}

	public void setFare(WorkPackageFare fare) {
		this.fare = fare;
	}

	public String getWoId() {
		return woId;
	}

	public void setWoId(String woId) {
		this.woId = woId;
	}
	
	public String getWoName() {
		return woName;
	}

	public void setWoName(String woName) {
		this.woName = woName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fare == null) ? 0 : fare.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((woId == null) ? 0 : woId.hashCode());
		result = prime * result + ((woName == null) ? 0 : woName.hashCode());
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
		WorkPackageMarketFare other = (WorkPackageMarketFare) obj;
		if (fare == null) {
			if (other.fare != null) {
				return false;
			}
		} else if (!fare.equals(other.fare)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (woId == null) {
			if (other.woId != null) {
				return false;
			}
		} else if (!woId.equals(other.woId)) {
			return false;
		}
		if (woName == null) {
			if (other.woName != null) {
				return false;
			}
		} else if (!woName.equals(other.woName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "WorkPackageMarketFare [id=" + id + ", fare=" + fare + ", woId=" + woId + ", woName=" + woName + "]";
	}
}
