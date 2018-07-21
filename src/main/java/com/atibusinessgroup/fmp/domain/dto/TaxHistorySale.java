package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class TaxHistorySale {

	@Field("historic_sale_effective")
	private Object historic_sale_effective;

	@Field("historic_sale_discontinue")
    private Object historic_sale_discontinue;

	public Object getHistoric_sale_effective() {
		return historic_sale_effective;
	}

	public void setHistoric_sale_effective(Object historic_sale_effective) {
		this.historic_sale_effective = historic_sale_effective;
	}

	public Object getHistoric_sale_discontinue() {
		return historic_sale_discontinue;
	}

	public void setHistoric_sale_discontinue(Object historic_sale_discontinue) {
		this.historic_sale_discontinue = historic_sale_discontinue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((historic_sale_discontinue == null) ? 0 : historic_sale_discontinue.hashCode());
		result = prime * result + ((historic_sale_effective == null) ? 0 : historic_sale_effective.hashCode());
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
		TaxHistorySale other = (TaxHistorySale) obj;
		if (historic_sale_discontinue == null) {
			if (other.historic_sale_discontinue != null) {
				return false;
			}
		} else if (!historic_sale_discontinue.equals(other.historic_sale_discontinue)) {
			return false;
		}
		if (historic_sale_effective == null) {
			if (other.historic_sale_effective != null) {
				return false;
			}
		} else if (!historic_sale_effective.equals(other.historic_sale_effective)) {
			return false;
		}
		return true;
	}
}
