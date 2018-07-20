package com.atibusinessgroup.fmp.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class DerivedFareParam {

	private List<SpecifiedConstructed> fares = new ArrayList<>();
	
	public DerivedFareParam() {
		
	}

	public List<SpecifiedConstructed> getFares() {
		return fares;
	}

	public void setFares(List<SpecifiedConstructed> fares) {
		this.fares = fares;
	}
}
