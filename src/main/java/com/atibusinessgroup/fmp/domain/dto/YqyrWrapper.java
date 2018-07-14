package com.atibusinessgroup.fmp.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class YqyrWrapper {
	private boolean isLastPage = false;
	private int lastIndex = 0;
	private List<Yqyr> yqyr = new ArrayList<>();
	
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
	
	public List<Yqyr> getYqyr() {
		return yqyr;
	}
	
	public void setYqyr(List<Yqyr> yqyr) {
		this.yqyr = yqyr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isLastPage ? 1231 : 1237);
		result = prime * result + lastIndex;
		result = prime * result + ((yqyr == null) ? 0 : yqyr.hashCode());
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
		YqyrWrapper other = (YqyrWrapper) obj;
		if (isLastPage != other.isLastPage) {
			return false;
		}
		if (lastIndex != other.lastIndex) {
			return false;
		}
		if (yqyr == null) {
			if (other.yqyr != null) {
				return false;
			}
		} else if (!yqyr.equals(other.yqyr)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "YqyrWrapper [isLastPage=" + isLastPage + ", lastIndex=" + lastIndex + ", yqyr=" + yqyr + "]";
	}
}
