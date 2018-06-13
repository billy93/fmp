package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "work_package_filter")
public class WorkPackageFilter implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -892896828400954785L;
	
	 @Id
	 private String id;
	 
	 @Field("review_level")
	 public ReviewLevel reviewLevel;
	 
	 @Field("status")
	 public Status status;
	 
	 @Field("distribution_type")
	 public DistributionType distributionType;
	 
	 @Field("type")
	 public Type type;
	 
	 @Field("approval_reference")
	 public String approvalReference;
	 
	 @Field("created_time")
	 public String createdTime;
	 
	 @Field("login_name")
	 public String loginName;
	
	 
	 
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static class ReviewLevel{
		public boolean ho;
		public boolean lso;
		public boolean distribution;
		public boolean routeManagement;
		
		public ReviewLevel() {
			// TODO Auto-generated constructor stub
		}
		
		public boolean isHo() {
			return ho;
		}
		public void setHo(boolean ho) {
			this.ho = ho;
		}
		public boolean isLso() {
			return lso;
		}
		public void setLso(boolean lso) {
			this.lso = lso;
		}
		public boolean isDistribution() {
			return distribution;
		}
		public void setDistribution(boolean distribution) {
			this.distribution = distribution;
		}
		public boolean isRouteManagement() {
			return routeManagement;
		}
		public void setRouteManagement(boolean routeManagement) {
			this.routeManagement = routeManagement;
		}
		@Override
		public String toString() {
			return "ReviewLevel [ho=" + ho + ", lso=" + lso + ", distribution=" + distribution
					+ ", routeManagement=" + routeManagement + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (distribution ? 1231 : 1237);
			result = prime * result + (ho ? 1231 : 1237);
			result = prime * result + (lso ? 1231 : 1237);
			result = prime * result + (routeManagement ? 1231 : 1237);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ReviewLevel other = (ReviewLevel) obj;
			if (distribution != other.distribution)
				return false;
			if (ho != other.ho)
				return false;
			if (lso != other.lso)
				return false;
			if (routeManagement != other.routeManagement)
				return false;
			return true;
		}
		
		
	}

	public static class Status{
		public boolean newStatus;
		public boolean distributed;
		public boolean reviewing;
		public boolean readyToRelease;
		public boolean pending;
		public boolean completed;
		public boolean withdrawn;
    	public boolean replace;
    	public boolean reuse;
    	public boolean referred;
    	public boolean discontinued;
    	
    	public Status() {}
    	
    	
		public boolean isDiscontinued() {
			return discontinued;
		}


		public void setDiscontinued(boolean discontinued) {
			this.discontinued = discontinued;
		}


		public boolean isReferred() {
			return referred;
		}
		public void setReferred(boolean referred) {
			this.referred = referred;
		}
		public boolean isWithdrawn() {
			return withdrawn;
		}
		public void setWithdrawn(boolean withdrawn) {
			this.withdrawn = withdrawn;
		}
		public boolean isNewStatus() {
			return newStatus;
		}
		public void setNewStatus(boolean newStatus) {
			this.newStatus = newStatus;
		}
		public boolean isDistributed() {
			return distributed;
		}
		public void setDistributed(boolean distributed) {
			this.distributed = distributed;
		}
		public boolean isReviewing() {
			return reviewing;
		}
		public void setReviewing(boolean reviewing) {
			this.reviewing = reviewing;
		}
		public boolean isReadyToRelease() {
			return readyToRelease;
		}
		public void setReadyToRelease(boolean readyToRelease) {
			this.readyToRelease = readyToRelease;
		}
		public boolean isPending() {
			return pending;
		}
		public void setPending(boolean pending) {
			this.pending = pending;
		}
		public boolean isCompleted() {
			return completed;
		}
		public void setCompleted(boolean completed) {
			this.completed = completed;
		}
		public boolean isReplace() {
			return replace;
		}
		public void setReplace(boolean replace) {
			this.replace = replace;
		}
		public boolean isReuse() {
			return reuse;
		}
		public void setReuse(boolean reuse) {
			this.reuse = reuse;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (completed ? 1231 : 1237);
			result = prime * result + (distributed ? 1231 : 1237);
			result = prime * result + (newStatus ? 1231 : 1237);
			result = prime * result + (pending ? 1231 : 1237);
			result = prime * result + (readyToRelease ? 1231 : 1237);
			result = prime * result + (referred ? 1231 : 1237);
			result = prime * result + (replace ? 1231 : 1237);
			result = prime * result + (reuse ? 1231 : 1237);
			result = prime * result + (reviewing ? 1231 : 1237);
			result = prime * result + (withdrawn ? 1231 : 1237);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Status other = (Status) obj;
			if (completed != other.completed)
				return false;
			if (distributed != other.distributed)
				return false;
			if (newStatus != other.newStatus)
				return false;
			if (pending != other.pending)
				return false;
			if (readyToRelease != other.readyToRelease)
				return false;
			if (referred != other.referred)
				return false;
			if (replace != other.replace)
				return false;
			if (reuse != other.reuse)
				return false;
			if (reviewing != other.reviewing)
				return false;
			if (withdrawn != other.withdrawn)
				return false;
			return true;
		}
		
		
	}
	
	public static class DistributionType{
		public boolean atpco;
		public boolean market;
		public boolean waiver;
		
		public DistributionType() {}
		public boolean isAtpco() {
			return atpco;
		}
		public void setAtpco(boolean atpco) {
			this.atpco = atpco;
		}
		public boolean isMarket() {
			return market;
		}
		public void setMarket(boolean market) {
			this.market = market;
		}
		public boolean isWaiver() {
			return waiver;
		}
		public void setWaiver(boolean waiver) {
			this.waiver = waiver;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (atpco ? 1231 : 1237);
			result = prime * result + (market ? 1231 : 1237);
			result = prime * result + (waiver ? 1231 : 1237);
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			DistributionType other = (DistributionType) obj;
			if (atpco != other.atpco)
				return false;
			if (market != other.market)
				return false;
			if (waiver != other.waiver)
				return false;
			return true;
		}
		
		
	}

	public static class Type{
		public boolean regular;
		public boolean discount;
		public boolean waiver;
		
		public Type() {}
		public boolean isRegular() {
			return regular;
		}
		public void setRegular(boolean regular) {
			this.regular = regular;
		}
		public boolean isDiscount() {
			return discount;
		}
		public void setDiscount(boolean discount) {
			this.discount = discount;
		}
		public boolean isWaiver() {
			return waiver;
		}
		public void setWaiver(boolean waiver) {
			this.waiver = waiver;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (discount ? 1231 : 1237);
			result = prime * result + (regular ? 1231 : 1237);
			result = prime * result + (waiver ? 1231 : 1237);
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Type other = (Type) obj;
			if (discount != other.discount)
				return false;
			if (regular != other.regular)
				return false;
			if (waiver != other.waiver)
				return false;
			return true;
		}
		
		
	}
	
	
	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public ReviewLevel getReviewLevel() {
		return reviewLevel;
	}

	public void setReviewLevel(ReviewLevel reviewLevel) {
		this.reviewLevel = reviewLevel;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public DistributionType getDistributionType() {
		return distributionType;
	}

	public void setDistributionType(DistributionType distributionType) {
		this.distributionType = distributionType;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	public String getApprovalReference() {
		return approvalReference;
	}

	public void setApprovalReference(String approvalReference) {
		this.approvalReference = approvalReference;
	}
	
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
			

	@Override
	public String toString() {
		return "WorkPackageFilter [id=" + id + ", reviewLevel=" + reviewLevel + ", status=" + status
				+ ", distributionType=" + distributionType + ", type=" + type + ", approvalReference="
				+ approvalReference + ", createdTime=" + createdTime + ", loginName=" + loginName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approvalReference == null) ? 0 : approvalReference.hashCode());
		result = prime * result + ((createdTime == null) ? 0 : createdTime.hashCode());
		result = prime * result + ((distributionType == null) ? 0 : distributionType.hashCode());
		result = prime * result + ((reviewLevel == null) ? 0 : reviewLevel.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkPackageFilter other = (WorkPackageFilter) obj;
		if (approvalReference == null) {
			if (other.approvalReference != null)
				return false;
		} else if (!approvalReference.equals(other.approvalReference))
			return false;
		if (createdTime == null) {
			if (other.createdTime != null)
				return false;
		} else if (!createdTime.equals(other.createdTime))
			return false;
		if (distributionType == null) {
			if (other.distributionType != null)
				return false;
		} else if (!distributionType.equals(other.distributionType))
			return false;
		if (reviewLevel == null) {
			if (other.reviewLevel != null)
				return false;
		} else if (!reviewLevel.equals(other.reviewLevel))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}	
	
}