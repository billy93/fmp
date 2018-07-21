package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class TaxSaleDate {

	@Field("sale_dates_last")
	private Object sale_dates_last;

	@Field("sale_dates_first")
    private Object sale_dates_first;

	public Object getSale_dates_last() {
		return sale_dates_last;
	}

	public void setSale_dates_last(Object sale_dates_last) {
		this.sale_dates_last = sale_dates_last;
	}

	public Object getSale_dates_first() {
		return sale_dates_first;
	}

	public void setSale_dates_first(Object sale_dates_first) {
		this.sale_dates_first = sale_dates_first;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sale_dates_first == null) ? 0 : sale_dates_first.hashCode());
		result = prime * result + ((sale_dates_last == null) ? 0 : sale_dates_last.hashCode());
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
		TaxSaleDate other = (TaxSaleDate) obj;
		if (sale_dates_first == null) {
			if (other.sale_dates_first != null) {
				return false;
			}
		} else if (!sale_dates_first.equals(other.sale_dates_first)) {
			return false;
		}
		if (sale_dates_last == null) {
			if (other.sale_dates_last != null) {
				return false;
			}
		} else if (!sale_dates_last.equals(other.sale_dates_last)) {
			return false;
		}
		return true;
	}
}
