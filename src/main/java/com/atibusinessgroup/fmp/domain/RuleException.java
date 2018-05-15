package com.atibusinessgroup.fmp.domain;

import java.util.List;

public class RuleException {
	List<String> fareBasisCode;
	SeasonType seasonType;
	String type;
	String owrt;
	String location1;
	String location2;

	public List<String> getFareBasisCode() {
		return fareBasisCode;
	}

	public void setFareBasisCode(List<String> fareBasisCode) {
		this.fareBasisCode = fareBasisCode;
	}

	public SeasonType getSeasonType() {
		return seasonType;
	}

	public void setSeasonType(SeasonType seasonType) {
		this.seasonType = seasonType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation1() {
		return location1;
	}

	public void setLocation1(String location1) {
		this.location1 = location1;
	}

	public String getLocation2() {
		return location2;
	}

	public void setLocation2(String location2) {
		this.location2 = location2;
	}

	public String getOwrt() {
		return owrt;
	}

	public void setOwrt(String owrt) {
		this.owrt = owrt;
	}
}
