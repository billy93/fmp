package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class DateTable {
	
	@Field("tbl_no")
	private String tableNo;
	
	@Field("travel_dates_eff")
	private Object travelEffectiveDate;
	
	@Field("travel_dates_disc")
	private Object travelDiscontinueDate;

	@Field("ticketing_dates_eff")
	private Object ticketingEffectiveDate;
	
	@Field("ticketing_dates_disc")
	private Object ticketingDiscontinueDate;
	
	@Field("reservations_dates_eff")
	private Object reservationEffectiveDate;
	
	@Field("reservations_dates_disc")
	private Object reservationDiscontinueDate;

	public String getTableNo() {
		return tableNo;
	}

	public void setTableNo(String tableNo) {
		this.tableNo = tableNo;
	}

	public Object getTravelEffectiveDate() {
		return travelEffectiveDate;
	}

	public void setTravelEffectiveDate(Object travelEffectiveDate) {
		this.travelEffectiveDate = travelEffectiveDate;
	}

	public Object getTravelDiscontinueDate() {
		return travelDiscontinueDate;
	}

	public void setTravelDiscontinueDate(Object travelDiscontinueDate) {
		this.travelDiscontinueDate = travelDiscontinueDate;
	}

	public Object getTicketingEffectiveDate() {
		return ticketingEffectiveDate;
	}

	public void setTicketingEffectiveDate(Object ticketingEffectiveDate) {
		this.ticketingEffectiveDate = ticketingEffectiveDate;
	}

	public Object getTicketingDiscontinueDate() {
		return ticketingDiscontinueDate;
	}

	public void setTicketingDiscontinueDate(Object ticketingDiscontinueDate) {
		this.ticketingDiscontinueDate = ticketingDiscontinueDate;
	}

	public Object getReservationEffectiveDate() {
		return reservationEffectiveDate;
	}

	public void setReservationEffectiveDate(Object reservationEffectiveDate) {
		this.reservationEffectiveDate = reservationEffectiveDate;
	}

	public Object getReservationDiscontinueDate() {
		return reservationDiscontinueDate;
	}

	public void setReservationDiscontinueDate(Object reservationDiscontinueDate) {
		this.reservationDiscontinueDate = reservationDiscontinueDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reservationDiscontinueDate == null) ? 0 : reservationDiscontinueDate.hashCode());
		result = prime * result + ((reservationEffectiveDate == null) ? 0 : reservationEffectiveDate.hashCode());
		result = prime * result + ((tableNo == null) ? 0 : tableNo.hashCode());
		result = prime * result + ((ticketingDiscontinueDate == null) ? 0 : ticketingDiscontinueDate.hashCode());
		result = prime * result + ((ticketingEffectiveDate == null) ? 0 : ticketingEffectiveDate.hashCode());
		result = prime * result + ((travelDiscontinueDate == null) ? 0 : travelDiscontinueDate.hashCode());
		result = prime * result + ((travelEffectiveDate == null) ? 0 : travelEffectiveDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		DateTable other = (DateTable) obj;
		if (reservationDiscontinueDate == null) {
			if (other.reservationDiscontinueDate != null) {
				return false;
			}
		} else if (!reservationDiscontinueDate.equals(other.reservationDiscontinueDate)) {
			return false;
		}
		if (reservationEffectiveDate == null) {
			if (other.reservationEffectiveDate != null) {
				return false;
			}
		} else if (!reservationEffectiveDate.equals(other.reservationEffectiveDate)) {
			return false;
		}
		if (tableNo == null) {
			if (other.tableNo != null) {
				return false;
			}
		} else if (!tableNo.equals(other.tableNo)) {
			return false;
		}
		if (ticketingDiscontinueDate == null) {
			if (other.ticketingDiscontinueDate != null) {
				return false;
			}
		} else if (!ticketingDiscontinueDate.equals(other.ticketingDiscontinueDate)) {
			return false;
		}
		if (ticketingEffectiveDate == null) {
			if (other.ticketingEffectiveDate != null) {
				return false;
			}
		} else if (!ticketingEffectiveDate.equals(other.ticketingEffectiveDate)) {
			return false;
		}
		if (travelDiscontinueDate == null) {
			if (other.travelDiscontinueDate != null) {
				return false;
			}
		} else if (!travelDiscontinueDate.equals(other.travelDiscontinueDate)) {
			return false;
		}
		if (travelEffectiveDate == null) {
			if (other.travelEffectiveDate != null) {
				return false;
			}
		} else if (!travelEffectiveDate.equals(other.travelEffectiveDate)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "DateTable [tableNo=" + tableNo + ", travelEffectiveDate=" + travelEffectiveDate
				+ ", travelDiscontinueDate=" + travelDiscontinueDate + ", ticketingEffectiveDate="
				+ ticketingEffectiveDate + ", ticketingDiscontinueDate=" + ticketingDiscontinueDate
				+ ", reservationEffectiveDate=" + reservationEffectiveDate + ", reservationDiscontinueDate="
				+ reservationDiscontinueDate + "]";
	}
}
