package com.atibusinessgroup.fmp.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class TaxWrapper {
	private boolean isLastPage = false;
	private int lastIndex = 0;
	private List<Tax> tax = new ArrayList<>();
	
	public boolean isLastPage() {
		return isLastPage;
	}
	
	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}
	
	public int getLastIndex() {
		return lastIndex;
	}
	
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}
	
	public List<Tax> getTax() {
		return tax;
	}
	
	public void setTax(List<Tax> tax) {
		this.tax = tax;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isLastPage ? 1231 : 1237);
		result = prime * result + lastIndex;
		result = prime * result + ((tax == null) ? 0 : tax.hashCode());
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
		TaxWrapper other = (TaxWrapper) obj;
		if (isLastPage != other.isLastPage) {
			return false;
		}
		if (lastIndex != other.lastIndex) {
			return false;
		}
		if (tax == null) {
			if (other.tax != null) {
				return false;
			}
		} else if (!tax.equals(other.tax)) {
			return false;
		}
		return true;
	}
}
