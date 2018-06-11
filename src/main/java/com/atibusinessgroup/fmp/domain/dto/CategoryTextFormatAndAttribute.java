package com.atibusinessgroup.fmp.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class CategoryTextFormatAndAttribute {
	private String textFormat;
	private List<CategoryAttribute> attributes = new ArrayList<>();
	
	public String getTextFormat() {
		return textFormat;
	}
	
	public void setTextFormat(String textFormat) {
		this.textFormat = textFormat;
	}
	
	public List<CategoryAttribute> getAttributes() {
		return attributes;
	}
	
	public void setAttributes(List<CategoryAttribute> attributes) {
		this.attributes = attributes;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attributes == null) ? 0 : attributes.hashCode());
		result = prime * result + ((textFormat == null) ? 0 : textFormat.hashCode());
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
		CategoryTextFormatAndAttribute other = (CategoryTextFormatAndAttribute) obj;
		if (attributes == null) {
			if (other.attributes != null) {
				return false;
			}
		} else if (!attributes.equals(other.attributes)) {
			return false;
		}
		if (textFormat == null) {
			if (other.textFormat != null) {
				return false;
			}
		} else if (!textFormat.equals(other.textFormat)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "CategoryTextFormatAndAttribute [textFormat=" + textFormat + ", attributes=" + attributes + "]";
	}
}
