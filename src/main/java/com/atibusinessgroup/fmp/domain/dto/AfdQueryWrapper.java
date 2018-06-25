package com.atibusinessgroup.fmp.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class AfdQueryWrapper {
	private boolean isLastPage = false;
	private List<AfdQuery> afdQueries = new ArrayList<>();
	
	public boolean isLastPage() {
		return isLastPage;
	}
	
	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}
	
	public List<AfdQuery> getAfdQueries() {
		return afdQueries;
	}
	
	public void setAfdQueries(List<AfdQuery> afdQueries) {
		this.afdQueries = afdQueries;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isLastPage ? 1231 : 1237);
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
		AfdQueryWrapper other = (AfdQueryWrapper) obj;
		if (isLastPage != other.isLastPage) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AfdQueryWrapper [isLastPage=" + isLastPage + "]";
	}
}
