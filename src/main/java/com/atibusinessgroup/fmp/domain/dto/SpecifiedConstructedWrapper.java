package com.atibusinessgroup.fmp.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class SpecifiedConstructedWrapper {
	private boolean isLastPage = false;
	private int lastIndex = 0;
	private List<SpecifiedConstructed> specifiedConstructed = new ArrayList<>();
	
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
	
	public List<SpecifiedConstructed> getSpecifiedConstructed() {
		return specifiedConstructed;
	}
	
	public void setSpecifiedConstructed(List<SpecifiedConstructed> specifiedConstructed) {
		this.specifiedConstructed = specifiedConstructed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isLastPage ? 1231 : 1237);
		result = prime * result + lastIndex;
		result = prime * result + ((specifiedConstructed == null) ? 0 : specifiedConstructed.hashCode());
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
		SpecifiedConstructedWrapper other = (SpecifiedConstructedWrapper) obj;
		if (isLastPage != other.isLastPage) {
			return false;
		}
		if (lastIndex != other.lastIndex) {
			return false;
		}
		if (specifiedConstructed == null) {
			if (other.specifiedConstructed != null) {
				return false;
			}
		} else if (!specifiedConstructed.equals(other.specifiedConstructed)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AfdQueryWrapper [isLastPage=" + isLastPage + ", lastIndex=" + lastIndex + ", specifiedConstructed="
				+ specifiedConstructed + "]";
	}
}
