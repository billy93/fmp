package com.atibusinessgroup.fmp.domain;

public class PermittedCarrier {

	private Airline carrier;
	private String bookingCode;

	public Airline getCarrier() {
		return carrier;
	}

	public void setCarrier(Airline carrier) {
		this.carrier = carrier;
	}

	public String getBookingCode() {
		return bookingCode;
	}

	public void setBookingCode(String bookingCode) {
		this.bookingCode = bookingCode;
	}

}
