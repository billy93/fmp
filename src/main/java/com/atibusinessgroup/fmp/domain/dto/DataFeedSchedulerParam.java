package com.atibusinessgroup.fmp.domain.dto;

public class DataFeedSchedulerParam {
	
	private String delayDays;
	private String delayHours;
	private String delayMinutes;
	private boolean faresAtpco;
	private boolean faresMarket;
	private Object startDate;
	private Object endDate;
	private String time;
	
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
	public boolean isFaresAtpco() {
		return faresAtpco;
	}
	public void setFaresAtpco(boolean faresAtpco) {
		this.faresAtpco = faresAtpco;
	}
	public boolean isFaresMarket() {
		return faresMarket;
	}
	public void setFaresMarket(boolean faresMarket) {
		this.faresMarket = faresMarket;
	}
	public Object getStartDate() {
		return startDate;
	}
	public void setStartDate(Object startDate) {
		this.startDate = startDate;
	}
	public Object getEndDate() {
		return endDate;
	}
	public void setEndDate(Object endDate) {
		this.endDate = endDate;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
	

}
