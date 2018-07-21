package com.atibusinessgroup.fmp.domain.dto;

public class TaxQueryParam {
	
	private int page;
	private int size;
	private int lastIndex;
	
	public TaxQueryParam() {
		
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}
}
