package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;

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
	private int delayDays;
	
	@Field("delay_hours")
	private int delayHours;
	
	@Field("delay_minutes")
	private int delayMinutes;
	
	@Field("start_date")
	private ZonedDateTime startDate;
	
	@Field("end_date")
	private ZonedDateTime endDate;
	
	@Field("start_time")
	private ZonedDateTime startTime;
	
	@Field("atpco_fares")
	private boolean atpcoFares;
	
	@Field("market_fares")
	private boolean marketFares;
	
	@Field("type")
	private String type;
	
	@Field("day_of_week")
	private String dayOfWeek;
	
	@Field("file_path")
	private String filepath;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
		return "DataFeedScheduler [id=" + id + ", delayDays=" + delayDays + ", delayHours=" + delayHours
				+ ", delayMinutes=" + delayMinutes + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", startTime=" + startTime + ", atpcoFares=" + atpcoFares + ", marketFares=" + marketFares + ", type="
				+ type + ", dayOfWeek=" + dayOfWeek + ", filepath=" + filepath + "]";
	}

}
