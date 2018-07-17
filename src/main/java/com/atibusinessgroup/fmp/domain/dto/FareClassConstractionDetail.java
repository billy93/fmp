package com.atibusinessgroup.fmp.domain.dto;

import com.atibusinessgroup.fmp.domain.ConstractionDataP02;

public class FareClassConstractionDetail {

	private FareClassQuery fareClassQuery;
	private ConstractionDataP02 constractionDataP02;
	
	public FareClassQuery getFareClassQuery() {
		return fareClassQuery;
	}
	public void setFareClassQuery(FareClassQuery fareClassQuery) {
		this.fareClassQuery = fareClassQuery;
	}
	public ConstractionDataP02 getConstractionDataP02() {
		return constractionDataP02;
	}
	public void setConstractionDataP02(ConstractionDataP02 constractionDataP02) {
		this.constractionDataP02 = constractionDataP02;
	}
	
	@Override
	public String toString() {
		return "FareClassConstractionDetail [fareClassQuery=" + fareClassQuery + ", constractionDataP02="
				+ constractionDataP02 + "]";
	}
	
	
	
}
