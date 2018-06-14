package com.atibusinessgroup.fmp.domain;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoMasterPassengerTypeCode;

public class SecondaryPassengerType {

	private AtpcoMasterPassengerTypeCode passengerType;
	private CurrencyAmount addOnCurrencyAmount;
	private Boolean applyFirst;
	private String increaseDecrease;
	private String increaseDecrease2;
	private String type;
	private int percent;
	private CurrencyAmount currencyAmount1;
	private CurrencyAmount currencyAmount2;

	public AtpcoMasterPassengerTypeCode getPassengerType() {
		return passengerType;
	}

	public void setPassengerType(AtpcoMasterPassengerTypeCode passengerType) {
		this.passengerType = passengerType;
	}

	public CurrencyAmount getAddOnCurrencyAmount() {
		return addOnCurrencyAmount;
	}

	public void setAddOnCurrencyAmount(CurrencyAmount addOnCurrencyAmount) {
		this.addOnCurrencyAmount = addOnCurrencyAmount;
	}

	public Boolean getApplyFirst() {
		return applyFirst;
	}

	public void setApplyFirst(Boolean applyFirst) {
		this.applyFirst = applyFirst;
	}

	public String getIncreaseDecrease() {
		return increaseDecrease;
	}

	public void setIncreaseDecrease(String increaseDecrease) {
		this.increaseDecrease = increaseDecrease;
	}

	
	public String getIncreaseDecrease2() {
		return increaseDecrease2;
	}

	public void setIncreaseDecrease2(String increaseDecrease2) {
		this.increaseDecrease2 = increaseDecrease2;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public CurrencyAmount getCurrencyAmount1() {
		return currencyAmount1;
	}

	public void setCurrencyAmount1(CurrencyAmount currencyAmount1) {
		this.currencyAmount1 = currencyAmount1;
	}

	public CurrencyAmount getCurrencyAmount2() {
		return currencyAmount2;
	}

	public void setCurrencyAmount2(CurrencyAmount currencyAmount2) {
		this.currencyAmount2 = currencyAmount2;
	}

}
