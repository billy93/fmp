package com.atibusinessgroup.fmp.domain.dto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Category {
	private int catNo;
	private String catName;
	private String type;
	private List<CategoryAttribute> catAttributes = new ArrayList<>();
	
	public int getCatNo() {
		return catNo;
	}
	
	public void setCatNo(int catNo) {
		this.catNo = catNo;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public List<CategoryAttribute> getCatAttributes() {
		return catAttributes;
	}

	public void setCatAttributes(List<CategoryAttribute> catAttributes) {
		this.catAttributes = catAttributes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((catAttributes == null) ? 0 : catAttributes.hashCode());
		result = prime * result + ((catName == null) ? 0 : catName.hashCode());
		result = prime * result + catNo;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Category other = (Category) obj;
		if (catAttributes == null) {
			if (other.catAttributes != null) {
				return false;
			}
		} else if (!catAttributes.equals(other.catAttributes)) {
			return false;
		}
		if (catName == null) {
			if (other.catName != null) {
				return false;
			}
		} else if (!catName.equals(other.catName)) {
			return false;
		}
		if (catNo != other.catNo) {
			return false;
		}
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Category [catNo=" + catNo + ", catName=" + catName + ", type=" + type + ", catAttributes="
				+ catAttributes + "]";
	}
}
