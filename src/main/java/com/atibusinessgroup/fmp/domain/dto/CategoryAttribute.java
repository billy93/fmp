package com.atibusinessgroup.fmp.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class CategoryAttribute {
	private String relationship;
	private List<CategoryAttributeObject> attributes = new ArrayList<>();
	
	public String getRelationship() {
		return relationship;
	}
	
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	
	public List<CategoryAttributeObject> getAttributes() {
		return attributes;
	}
	
	public void setAttributes(List<CategoryAttributeObject> attributes) {
		this.attributes = attributes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attributes == null) ? 0 : attributes.hashCode());
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
		CategoryAttribute other = (CategoryAttribute) obj;
		if (attributes == null) {
			if (other.attributes != null) {
				return false;
			}
		} else if (!attributes.equals(other.attributes)) {
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
		return "CategoryAttribute [relationship=" + relationship + ", attributes=" + attributes + "]";
	}	
}
