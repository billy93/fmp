package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "data_feed_pfc_rates")
public class DataFeedScheduler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;

	@Field("output_file")
	private String outputFile;
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
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

	@Override
	public String toString() {
		return "DataFeedScheduler [id=" + id + ", outputFile=" + outputFile + ", delayDays=" + delayDays
				+ ", delayHours=" + delayHours + ", delayMinutes=" + delayMinutes + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}
	
	


}
