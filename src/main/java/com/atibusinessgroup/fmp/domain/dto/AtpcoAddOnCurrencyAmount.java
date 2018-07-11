package com.atibusinessgroup.fmp.domain.dto;

import org.bson.types.Decimal128;
import org.springframework.data.mongodb.core.mapping.Field;

public class AtpcoAddOnCurrencyAmount {

	@Field("add_on_cur_cd")
	private String add_on_cur_cd;

	@Field("add_on_dec")
    private int add_on_dec;

	@Field("add_on_amount")
    private Decimal128 add_on_amount;

	public String getAdd_on_cur_cd() {
		return add_on_cur_cd;
	}

	public void setAdd_on_cur_cd(String add_on_cur_cd) {
		this.add_on_cur_cd = add_on_cur_cd;
	}

	public int getAdd_on_dec() {
		return add_on_dec;
	}

	public void setAdd_on_dec(int add_on_dec) {
		this.add_on_dec = add_on_dec;
	}

	public Decimal128 getAdd_on_amount() {
		return add_on_amount;
	}

	public void setAdd_on_amount(Decimal128 add_on_amount) {
		this.add_on_amount = add_on_amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((add_on_amount == null) ? 0 : add_on_amount.hashCode());
		result = prime * result + ((add_on_cur_cd == null) ? 0 : add_on_cur_cd.hashCode());
		result = prime * result + add_on_dec;
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
		AtpcoAddOnCurrencyAmount other = (AtpcoAddOnCurrencyAmount) obj;
		if (add_on_amount == null) {
			if (other.add_on_amount != null) {
				return false;
			}
		} else if (!add_on_amount.equals(other.add_on_amount)) {
			return false;
		}
		if (add_on_cur_cd == null) {
			if (other.add_on_cur_cd != null) {
				return false;
			}
		} else if (!add_on_cur_cd.equals(other.add_on_cur_cd)) {
			return false;
		}
		if (add_on_dec != other.add_on_dec) {
			return false;
		}
		return true;
	}
}
