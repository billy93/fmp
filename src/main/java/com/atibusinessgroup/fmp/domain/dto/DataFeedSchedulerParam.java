package com.atibusinessgroup.fmp.domain.dto;

import java.time.ZonedDateTime;

public class DataFeedSchedulerParam {
	
	private int delayDays;
	private int delayHours;
	private int delayMinutes;
	private ZonedDateTime startDate;
	private ZonedDateTime endDate;
	private ZonedDateTime startTime;
	private boolean atpcoFares;
	private boolean marketFares;
	private String type;
	private String dayOfWeek;
	private String filepath;
	
	
	public int getDelayDays() {
		return delayDays;
	}
	public void setDelayDays(int delayDays) {
		this.delayDays = delayDays;
	}
	public int getDelayHours() {
		return delayHours;
	}
	public void setDelayHours(int delayHours) {
		this.delayHours = delayHours;
	}
	public int getDelayMinutes() {
		return delayMinutes;
	}
	public void setDelayMinutes(int delayMinutes) {
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
	public ZonedDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(ZonedDateTime startTime) {
		this.startTime = startTime;
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
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	@Override
	public String toString() {
		return "DataFeedSchedulerParam [delayDays=" + delayDays + ", delayHours=" + delayHours + ", delayMinutes="
				+ delayMinutes + ", startDate=" + startDate + ", endDate=" + endDate + ", startTime=" + startTime
				+ ", atpcoFares=" + atpcoFares + ", marketFares=" + marketFares + ", type=" + type + ", dayOfWeek="
				+ dayOfWeek + ", filepath=" + filepath + "]";
	}
	
}
