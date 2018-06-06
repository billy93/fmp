package com.atibusinessgroup.fmp.domain.dto;

public class Record3ReflectionObject {
	private String collectionName;
	private String className;
	private String getTableNoMethodName;
	private String getTextTable996NoMethodName;
	private String getDateTable994NoMethodName;
	
	public String getCollectionName() {
		return collectionName;
	}
	
	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
	
	public String getClassName() {
		return className;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}
	
	public String getGetTableNoMethodName() {
		return getTableNoMethodName;
	}
	
	public void setGetTableNoMethodName(String getTableNoMethodName) {
		this.getTableNoMethodName = getTableNoMethodName;
	}
	
	public String getGetTextTable996NoMethodName() {
		return getTextTable996NoMethodName;
	}
	
	public void setGetTextTable996NoMethodName(String getTextTable996NoMethodName) {
		this.getTextTable996NoMethodName = getTextTable996NoMethodName;
	}

	public String getGetDateTable994NoMethodName() {
		return getDateTable994NoMethodName;
	}

	public void setGetDateTable994NoMethodName(String getDateTable994NoMethodName) {
		this.getDateTable994NoMethodName = getDateTable994NoMethodName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((className == null) ? 0 : className.hashCode());
		result = prime * result + ((collectionName == null) ? 0 : collectionName.hashCode());
		result = prime * result + ((getDateTable994NoMethodName == null) ? 0 : getDateTable994NoMethodName.hashCode());
		result = prime * result + ((getTableNoMethodName == null) ? 0 : getTableNoMethodName.hashCode());
		result = prime * result + ((getTextTable996NoMethodName == null) ? 0 : getTextTable996NoMethodName.hashCode());
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
		Record3ReflectionObject other = (Record3ReflectionObject) obj;
		if (className == null) {
			if (other.className != null) {
				return false;
			}
		} else if (!className.equals(other.className)) {
			return false;
		}
		if (collectionName == null) {
			if (other.collectionName != null) {
				return false;
			}
		} else if (!collectionName.equals(other.collectionName)) {
			return false;
		}
		if (getDateTable994NoMethodName == null) {
			if (other.getDateTable994NoMethodName != null) {
				return false;
			}
		} else if (!getDateTable994NoMethodName.equals(other.getDateTable994NoMethodName)) {
			return false;
		}
		if (getTableNoMethodName == null) {
			if (other.getTableNoMethodName != null) {
				return false;
			}
		} else if (!getTableNoMethodName.equals(other.getTableNoMethodName)) {
			return false;
		}
		if (getTextTable996NoMethodName == null) {
			if (other.getTextTable996NoMethodName != null) {
				return false;
			}
		} else if (!getTextTable996NoMethodName.equals(other.getTextTable996NoMethodName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Record3ReflectionObject [collectionName=" + collectionName + ", className=" + className
				+ ", getTableNoMethodName=" + getTableNoMethodName + ", getTextTable996NoMethodName="
				+ getTextTable996NoMethodName + ", getDateTable994NoMethodName=" + getDateTable994NoMethodName + "]";
	}
}
