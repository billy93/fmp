package com.atibusinessgroup.fmp.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class FbrQueryWrapper {

	private boolean isLastPage = false;
	private int lastIndex = 0;
	private List<FbrQuery> fbrQueries = new ArrayList<>();
	
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
	public List<FbrQuery> getFbrQueries() {
		return fbrQueries;
	}
	public void setFbrQueries(List<FbrQuery> fbrQueries) {
		this.fbrQueries = fbrQueries;
	}
	
	@Override
	public String toString() {
		return "FbrQueryWrapper [isLastPage=" + isLastPage + ", lastIndex=" + lastIndex + ", fbrQueries=" + fbrQueries
				+ "]";
	}
	
}
