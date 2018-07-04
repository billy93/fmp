package com.atibusinessgroup.fmp.domain.dto;

import java.util.Date;

public class ExchangeRatesQueryParam {

	private String currencyCodeFrom;
	private String currencyCodeTo;
	private String currencyNameFrom;
	private String currencyNameTo;
	private Date dateFrom;
	private Date dateTo;
	private int page;
	private int size;
	
	public ExchangeRatesQueryParam() {
		
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
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	@Override
	public String toString() {
		return "ExchangeRatesQueryParam [currencyCodeFrom=" + currencyCodeFrom + ", currencyCodeTo=" + currencyCodeTo
				+ ", currencyNameFrom=" + currencyNameFrom + ", currencyNameTo=" + currencyNameTo + ", dateFrom="
				+ dateFrom + ", dateTo=" + dateTo + ", page=" + page + ", size=" + size + "]";
	}
	
	
}
