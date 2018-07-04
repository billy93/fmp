package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A ExchangeRatesQuery.
 */
@Document(collection = "exchange_rates")
public class ExchangeRatesQuery implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	@Id
    private String id;
    
    @Field("batch_number")
    private int batchNumber;
    
    @Field("batch_date")
    private Date batchDate;
    
    @Field("Country")
    private String country;
    
    @Field("alpha_code")
    private String alphaCode;
    
    @Field("rate_exchange")
    private String rateExchange;
    
    @Field("note")
    private String note;
    
    private String currencyCodeFrom;
	private String currencyCodeTo;
	private String currencyNameFrom;
	private String currencyNameTo;
	private Date dateFrom;
	private Date dateTo;
	private String rateExchangeValue;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(int batchNumber) {
		this.batchNumber = batchNumber;
	}

	public Date getBatchDate() {
		return batchDate;
	}

	public void setBatchDate(Date batchDate) {
		this.batchDate = batchDate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAlphaCode() {
		return alphaCode;
	}

	public void setAlphaCode(String alphaCode) {
		this.alphaCode = alphaCode;
	}

	public String getRateExchange() {
		return rateExchange;
	}

	public void setRateExchange(String rateExchange) {
		this.rateExchange = rateExchange;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public String getCurrencyCodeFrom() {
		return currencyCodeFrom;
	}

	public void setCurrencyCodeFrom(String currencyCodeFrom) {
		this.currencyCodeFrom = currencyCodeFrom;
	}

	public String getCurrencyCodeTo() {
		return currencyCodeTo;
	}

	public void setCurrencyCodeTo(String currencyCodeTo) {
		this.currencyCodeTo = currencyCodeTo;
	}

	public String getCurrencyNameFrom() {
		return currencyNameFrom;
	}

	public void setCurrencyNameFrom(String currencyNameFrom) {
		this.currencyNameFrom = currencyNameFrom;
	}

	public String getCurrencyNameTo() {
		return currencyNameTo;
	}

	public void setCurrencyNameTo(String currencyNameTo) {
		this.currencyNameTo = currencyNameTo;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getRateExchangeValue() {
		return rateExchangeValue;
	}

	public void setRateExchangeValue(String rateExchangeValue) {
		this.rateExchangeValue = rateExchangeValue;
	}

	@Override
	public String toString() {
		return "ExchangeRatesQuery [id=" + id + ", batchNumber=" + batchNumber + ", batchDate=" + batchDate
				+ ", country=" + country + ", alphaCode=" + alphaCode + ", rateExchange=" + rateExchange + ", note="
				+ note + ", currencyCodeFrom=" + currencyCodeFrom + ", currencyCodeTo=" + currencyCodeTo
				+ ", currencyNameFrom=" + currencyNameFrom + ", currencyNameTo=" + currencyNameTo + ", dateFrom="
				+ dateFrom + ", dateTo=" + dateTo + ", rateExchangeValue=" + rateExchangeValue + "]";
	}

    
}
