package com.atibusinessgroup.fmp.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.atibusinessgroup.fmp.domain.FbrReport;

public class FbrReportWrapper {
	private boolean isLastPage = false;
	private int lastIndex = 0;
	private List<FbrReport> fbrReport = new ArrayList<>();
	
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
	
	public List<FbrReport> getFbrReport() {
		return fbrReport;
	}
	
	public void setFbrReport(List<FbrReport> fbrReport) {
		this.fbrReport = fbrReport;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fbrReport == null) ? 0 : fbrReport.hashCode());
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
		FbrReportWrapper other = (FbrReportWrapper) obj;
		if (fbrReport == null) {
			if (other.fbrReport != null) {
				return false;
			}
		} else if (!fbrReport.equals(other.fbrReport)) {
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
