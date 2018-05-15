package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Section implements Serializable{
	private static final long serialVersionUID = 2621093256161667331L;
	private List<Page> pages = new ArrayList<Page>();
	private List<BookingCodeException> bookingCodeException;
	private boolean isValidated;
	private String error;
	
	public List<Page> getPages() {
		return pages;
	}
	
	public void setPages(List<Page> pages) {
		this.pages = pages;
	}
	
	public List<BookingCodeException> getBookingCodeException() {
		return bookingCodeException;
	}

	public void setBookingCodeException(List<BookingCodeException> bookingCodeException) {
		this.bookingCodeException = bookingCodeException;
	}

	public boolean isValidated() {
		return isValidated;
	}
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setValidated(boolean isValidated) {
		this.isValidated = isValidated;
	}
}
