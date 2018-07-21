package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class TaxTravelDate {

	@Field("travel_dates_tag")
	private Object travel_dates_tag;

	@Field("travel_dates_first")
    private Object travel_dates_first;

	@Field("travel_dates_last")
    private Object travel_dates_last;

	public Object getTravel_dates_tag() {
		return travel_dates_tag;
	}

	public void setTravel_dates_tag(Object travel_dates_tag) {
		this.travel_dates_tag = travel_dates_tag;
	}

	public Object getTravel_dates_first() {
		return travel_dates_first;
	}

	public void setTravel_dates_first(Object travel_dates_first) {
		this.travel_dates_first = travel_dates_first;
	}

	public Object getTravel_dates_last() {
		return travel_dates_last;
	}

	public void setTravel_dates_last(Object travel_dates_last) {
		this.travel_dates_last = travel_dates_last;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((travel_dates_first == null) ? 0 : travel_dates_first.hashCode());
		result = prime * result + ((travel_dates_last == null) ? 0 : travel_dates_last.hashCode());
		result = prime * result + ((travel_dates_tag == null) ? 0 : travel_dates_tag.hashCode());
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
		TaxTravelDate other = (TaxTravelDate) obj;
		if (travel_dates_first == null) {
			if (other.travel_dates_first != null) {
				return false;
			}
		} else if (!travel_dates_first.equals(other.travel_dates_first)) {
			return false;
		}
		if (travel_dates_last == null) {
			if (other.travel_dates_last != null) {
				return false;
			}
		} else if (!travel_dates_last.equals(other.travel_dates_last)) {
			return false;
		}
		if (travel_dates_tag == null) {
			if (other.travel_dates_tag != null) {
				return false;
			}
		} else if (!travel_dates_tag.equals(other.travel_dates_tag)) {
			return false;
		}
		return true;
	}
}
