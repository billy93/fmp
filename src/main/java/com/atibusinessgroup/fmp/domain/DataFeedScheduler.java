package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "data_feed_scheduler")
public class DataFeedScheduler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	@Field("delay_days")
	private String delayDays;
	
	@Field("delay_hours")
	private String delayHours;
	
	@Field("delay_minutes")
	private String delayMinutes;
	
	@Field("start_date")
	private Object startDate;
	
	@Field("end_date")
	private Object endDate;
	
	@Field("start_time")
	private Object startTime;
	
	@Field("atpco_fares")
	private boolean atpcoFares;
	
	@Field("market_fares")
	private boolean marketFares;
	
	@Field("type")
	private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public Object getStartTime() {
		return startTime;
	}

	public void setStartTime(Object startTime) {
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

	@Override
	public String toString() {
		return "DataFeedScheduler [id=" + id + ", delayDays=" + delayDays + ", delayHours=" + delayHours
				+ ", delayMinutes=" + delayMinutes + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", startTime=" + startTime + ", atpcoFares=" + atpcoFares + ", marketFares=" + marketFares + ", type="
				+ type + "]";
	}

}
