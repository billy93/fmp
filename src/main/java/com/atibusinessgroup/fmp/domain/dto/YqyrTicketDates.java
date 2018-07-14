package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class YqyrTicketDates {

	@Field("ticket_dates_first")
	private Object ticket_dates_first;

	@Field("travel_dates_eff")
    private Object travel_dates_eff;

	@Field("travel_dates_disc")
    private Object travel_dates_disc;

	@Field("ticket_dates_last")
    private Object ticket_dates_last;

	public Object getTicket_dates_first() {
		return ticket_dates_first;
	}

	public void setTicket_dates_first(Object ticket_dates_first) {
		this.ticket_dates_first = ticket_dates_first;
	}

	public Object getTravel_dates_eff() {
		return travel_dates_eff;
	}

	public void setTravel_dates_eff(Object travel_dates_eff) {
		this.travel_dates_eff = travel_dates_eff;
	}

	public Object getTravel_dates_disc() {
		return travel_dates_disc;
	}

	public void setTravel_dates_disc(Object travel_dates_disc) {
		this.travel_dates_disc = travel_dates_disc;
	}

	public Object getTicket_dates_last() {
		return ticket_dates_last;
	}

	public void setTicket_dates_last(Object ticket_dates_last) {
		this.ticket_dates_last = ticket_dates_last;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ticket_dates_first == null) ? 0 : ticket_dates_first.hashCode());
		result = prime * result + ((ticket_dates_last == null) ? 0 : ticket_dates_last.hashCode());
		result = prime * result + ((travel_dates_disc == null) ? 0 : travel_dates_disc.hashCode());
		result = prime * result + ((travel_dates_eff == null) ? 0 : travel_dates_eff.hashCode());
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
		YqyrTicketDates other = (YqyrTicketDates) obj;
		if (ticket_dates_first == null) {
			if (other.ticket_dates_first != null) {
				return false;
			}
		} else if (!ticket_dates_first.equals(other.ticket_dates_first)) {
			return false;
		}
		if (ticket_dates_last == null) {
			if (other.ticket_dates_last != null) {
				return false;
			}
		} else if (!ticket_dates_last.equals(other.ticket_dates_last)) {
			return false;
		}
		if (travel_dates_disc == null) {
			if (other.travel_dates_disc != null) {
				return false;
			}
		} else if (!travel_dates_disc.equals(other.travel_dates_disc)) {
			return false;
		}
		if (travel_dates_eff == null) {
			if (other.travel_dates_eff != null) {
				return false;
			}
		} else if (!travel_dates_eff.equals(other.travel_dates_eff)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "YqyrTicketDates [ticket_dates_first=" + ticket_dates_first + ", travel_dates_eff=" + travel_dates_eff
				+ ", travel_dates_disc=" + travel_dates_disc + ", ticket_dates_last=" + ticket_dates_last + "]";
	}
}
