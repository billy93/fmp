package com.atibusinessgroup.fmp.domain.dto;

import java.time.ZonedDateTime;

public class DataFeedSchedulerParam {
	
	private String delayDays;
	private String delayHours;
	private String delayMinutes;
	private ZonedDateTime startDate;
	private ZonedDateTime endDate;
	private ZonedDateTime time;
	private String type;
	private boolean atpcoFares;
	private boolean marketFares;
	
	public String getDelayDays() {
		return delayDays;
	}
	public void setDelayDays(String delayDays) {
		this.delayDays = delayDays;
	}
	public String getDelayHours() {
		return delayHours;
	}
	public void setDelayHours(String delayHours) {
		this.delayHours = delayHours;
	}
	public String getDelayMinutes() {
		return delayMinutes;
	}
	public void setDelayMinutes(String delayMinutes) {
		this.delayMinutes = delayMinutes;
	}
	public ZonedDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(ZonedDateTime startDate) {
		this.startDate = startDate;
	}
	public ZonedDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(ZonedDateTime endDate) {
		this.endDate = endDate;
	}
	public ZonedDateTime getTime() {
		return time;
	}
	public void setTime(ZonedDateTime time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isAtpcoFares() {
		return atpcoFares;
	}
	public void setAtpcoFares(boolean atpcoFares) {
		this.atpcoFares = atpcoFares;
	}
	public boolean isMarketFares() {
		return marketFares;
	}
	public void setMarketFares(boolean marketFares) {
		this.marketFares = marketFares;
	}
	
	@Override
	public String toString() {
		return "DataFeedSchedulerParam [delayDays=" + delayDays + ", delayHours=" + delayHours + ", delayMinutes="
				+ delayMinutes + ", startDate=" + startDate + ", endDate=" + endDate + ", time=" + time + ", type="
				+ type + ", atpcoFares=" + atpcoFares + ", marketFares=" + marketFares + "]";
	}
	
}
