package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class TaxHistoryTravel {

	@Field("historic_travel_discontinue")
	private Object historic_travel_discontinue;

	@Field("historic_travel_effective")
    private Object historic_travel_effective;

	public Object getHistoric_travel_discontinue() {
		return historic_travel_discontinue;
	}

	public void setHistoric_travel_discontinue(Object historic_travel_discontinue) {
		this.historic_travel_discontinue = historic_travel_discontinue;
	}

	public Object getHistoric_travel_effective() {
		return historic_travel_effective;
	}

	public void setHistoric_travel_effective(Object historic_travel_effective) {
		this.historic_travel_effective = historic_travel_effective;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((historic_travel_discontinue == null) ? 0 : historic_travel_discontinue.hashCode());
		result = prime * result + ((historic_travel_effective == null) ? 0 : historic_travel_effective.hashCode());
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
		TaxHistoryTravel other = (TaxHistoryTravel) obj;
		if (historic_travel_discontinue == null) {
			if (other.historic_travel_discontinue != null) {
				return false;
			}
		} else if (!historic_travel_discontinue.equals(other.historic_travel_discontinue)) {
			return false;
		}
		if (historic_travel_effective == null) {
			if (other.historic_travel_effective != null) {
				return false;
			}
		} else if (!historic_travel_effective.equals(other.historic_travel_effective)) {
			return false;
		}
		return true;
	}
}
