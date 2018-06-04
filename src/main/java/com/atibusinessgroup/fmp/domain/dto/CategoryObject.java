package com.atibusinessgroup.fmp.domain.dto;

public class CategoryObject {
	private String relationship;
	private Object category;
	
	public String getRelationship() {
		return relationship;
	}
	
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	
	public Object getCategory() {
		return category;
	}
	
	public void setCategory(Object category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((relationship == null) ? 0 : relationship.hashCode());
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
		CategoryObject other = (CategoryObject) obj;
		if (category == null) {
			if (other.category != null) {
				return false;
			}
		} else if (!category.equals(other.category)) {
			return false;
		}
		if (relationship == null) {
			if (other.relationship != null) {
				return false;
			}
		} else if (!relationship.equals(other.relationship)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "CategoryFootnote [relationship=" + relationship + ", category=" + category + "]";
	}
}
