package com.atibusinessgroup.fmp.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class AddOnsWrapper {
	private boolean isLastPage = false;
	private int lastIndex = 0;
	private List<AfdQueryAddOns> addOns = new ArrayList<>();
	
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
	
	public List<AfdQueryAddOns> getAddOns() {
		return addOns;
	}
	
	public void setAddOns(List<AfdQueryAddOns> addOns) {
		this.addOns = addOns;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addOns == null) ? 0 : addOns.hashCode());
		result = prime * result + (isLastPage ? 1231 : 1237);
		result = prime * result + lastIndex;
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
		AddOnsWrapper other = (AddOnsWrapper) obj;
		if (addOns == null) {
			if (other.addOns != null) {
				return false;
			}
		} else if (!addOns.equals(other.addOns)) {
			return false;
		}
		if (isLastPage != other.isLastPage) {
			return false;
		}
		if (lastIndex != other.lastIndex) {
			return false;
		}
		return true;
	}
}
