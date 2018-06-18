package com.atibusinessgroup.fmp.domain.dto;

import org.bson.types.Decimal128;
import org.springframework.data.mongodb.core.mapping.Field;

public class BaseFareTable {
	
	@Field("_id")
	private String tableNo;
	
	@Field("base_fare_appl")
	private String applicableTag;

	@Field("base_fare_owrt")
	private String owrt;

	@Field("base_fare_global")
	private String global;
	
	@Field("base_fare_cxr_code")
	private String carrierCode;
	
	@Field("base_fare_pub_calc")
	private String pubCalc;
	
	@Field("base_fare_rule_tar")
	private String ruleTariff;
	
	@Field("base_fare_rule_no")
	private String ruleNo;
	
	@Field("base_fare_class")
	private String fareClass;
	
	@Field("base_fare_type")
	private String fareType;
	
	@Field("base_fare_psgr_type")
	private String passengerType;
	
	@Field("base_fare_season_type")
	private String seasonType;
	
	@Field("base_fare_day_type")
	private String dayType;
	
	@Field("base_fare_pcr_cat")
	private String pricingCategoryType;
	
	@Field("base_fare_rtg_no")
	private String routingNo;
	
	@Field("base_fare_ftnt")
	private String footnote;
	
	@Field("base_fare_rbd_1")
	private String rbd;
	
	@Field("base_fare_min_fare_1")
	private Decimal128 minFare;
	
	@Field("base_fare_max_fare_1")
	private Decimal128 maxFare;
	
	@Field("base_fare_cur_1")
	private String currency;
	
	@Field("base_fare_dec_1")
	private String decimal;

	public String getTableNo() {
		return tableNo;
	}

	public void setTableNo(String tableNo) {
		this.tableNo = tableNo;
	}

	public String getApplicableTag() {
		return applicableTag;
	}

	public void setApplicableTag(String applicableTag) {
		this.applicableTag = applicableTag;
	}

	public String getOwrt() {
		return owrt;
	}

	public void setOwrt(String owrt) {
		this.owrt = owrt;
	}

	public String getGlobal() {
		return global;
	}

	public void setGlobal(String global) {
		this.global = global;
	}

	public String getCarrierCode() {
		return carrierCode;
	}

	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}

	public String getPubCalc() {
		return pubCalc;
	}

	public void setPubCalc(String pubCalc) {
		this.pubCalc = pubCalc;
	}

	public String getRuleTariff() {
		return ruleTariff;
	}

	public void setRuleTariff(String ruleTariff) {
		this.ruleTariff = ruleTariff;
	}

	public String getRuleNo() {
		return ruleNo;
	}

	public void setRuleNo(String ruleNo) {
		this.ruleNo = ruleNo;
	}

	public String getFareClass() {
		return fareClass;
	}

	public void setFareClass(String fareClass) {
		this.fareClass = fareClass;
	}

	public String getFareType() {
		return fareType;
	}

	public void setFareType(String fareType) {
		this.fareType = fareType;
	}

	public String getPassengerType() {
		return passengerType;
	}

	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}

	public String getSeasonType() {
		return seasonType;
	}

	public void setSeasonType(String seasonType) {
		this.seasonType = seasonType;
	}

	public String getDayType() {
		return dayType;
	}

	public void setDayType(String dayType) {
		this.dayType = dayType;
	}

	public String getPricingCategoryType() {
		return pricingCategoryType;
	}

	public void setPricingCategoryType(String pricingCategoryType) {
		this.pricingCategoryType = pricingCategoryType;
	}

	public String getRoutingNo() {
		return routingNo;
	}

	public void setRoutingNo(String routingNo) {
		this.routingNo = routingNo;
	}

	public String getFootnote() {
		return footnote;
	}

	public void setFootnote(String footnote) {
		this.footnote = footnote;
	}

	public String getRbd() {
		return rbd;
	}

	public void setRbd(String rbd) {
		this.rbd = rbd;
	}

	public Decimal128 getMinFare() {
		return minFare;
	}

	public void setMinFare(Decimal128 minFare) {
		this.minFare = minFare;
	}

	public Decimal128 getMaxFare() {
		return maxFare;
	}

	public void setMaxFare(Decimal128 maxFare) {
		this.maxFare = maxFare;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDecimal() {
		return decimal;
	}

	public void setDecimal(String decimal) {
		this.decimal = decimal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applicableTag == null) ? 0 : applicableTag.hashCode());
		result = prime * result + ((carrierCode == null) ? 0 : carrierCode.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((dayType == null) ? 0 : dayType.hashCode());
		result = prime * result + ((decimal == null) ? 0 : decimal.hashCode());
		result = prime * result + ((fareClass == null) ? 0 : fareClass.hashCode());
		result = prime * result + ((fareType == null) ? 0 : fareType.hashCode());
		result = prime * result + ((footnote == null) ? 0 : footnote.hashCode());
		result = prime * result + ((global == null) ? 0 : global.hashCode());
		result = prime * result + ((maxFare == null) ? 0 : maxFare.hashCode());
		result = prime * result + ((minFare == null) ? 0 : minFare.hashCode());
		result = prime * result + ((owrt == null) ? 0 : owrt.hashCode());
		result = prime * result + ((passengerType == null) ? 0 : passengerType.hashCode());
		result = prime * result + ((pricingCategoryType == null) ? 0 : pricingCategoryType.hashCode());
		result = prime * result + ((pubCalc == null) ? 0 : pubCalc.hashCode());
		result = prime * result + ((rbd == null) ? 0 : rbd.hashCode());
		result = prime * result + ((routingNo == null) ? 0 : routingNo.hashCode());
		result = prime * result + ((ruleNo == null) ? 0 : ruleNo.hashCode());
		result = prime * result + ((ruleTariff == null) ? 0 : ruleTariff.hashCode());
		result = prime * result + ((seasonType == null) ? 0 : seasonType.hashCode());
		result = prime * result + ((tableNo == null) ? 0 : tableNo.hashCode());
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
		BaseFareTable other = (BaseFareTable) obj;
		if (applicableTag == null) {
			if (other.applicableTag != null) {
				return false;
			}
		} else if (!applicableTag.equals(other.applicableTag)) {
			return false;
		}
		if (carrierCode == null) {
			if (other.carrierCode != null) {
				return false;
			}
		} else if (!carrierCode.equals(other.carrierCode)) {
			return false;
		}
		if (currency == null) {
			if (other.currency != null) {
				return false;
			}
		} else if (!currency.equals(other.currency)) {
			return false;
		}
		if (dayType == null) {
			if (other.dayType != null) {
				return false;
			}
		} else if (!dayType.equals(other.dayType)) {
			return false;
		}
		if (decimal == null) {
			if (other.decimal != null) {
				return false;
			}
		} else if (!decimal.equals(other.decimal)) {
			return false;
		}
		if (fareClass == null) {
			if (other.fareClass != null) {
				return false;
			}
		} else if (!fareClass.equals(other.fareClass)) {
			return false;
		}
		if (fareType == null) {
			if (other.fareType != null) {
				return false;
			}
		} else if (!fareType.equals(other.fareType)) {
			return false;
		}
		if (footnote == null) {
			if (other.footnote != null) {
				return false;
			}
		} else if (!footnote.equals(other.footnote)) {
			return false;
		}
		if (global == null) {
			if (other.global != null) {
				return false;
			}
		} else if (!global.equals(other.global)) {
			return false;
		}
		if (maxFare == null) {
			if (other.maxFare != null) {
				return false;
			}
		} else if (!maxFare.equals(other.maxFare)) {
			return false;
		}
		if (minFare == null) {
			if (other.minFare != null) {
				return false;
			}
		} else if (!minFare.equals(other.minFare)) {
			return false;
		}
		if (owrt == null) {
			if (other.owrt != null) {
				return false;
			}
		} else if (!owrt.equals(other.owrt)) {
			return false;
		}
		if (passengerType == null) {
			if (other.passengerType != null) {
				return false;
			}
		} else if (!passengerType.equals(other.passengerType)) {
			return false;
		}
		if (pricingCategoryType == null) {
			if (other.pricingCategoryType != null) {
				return false;
			}
		} else if (!pricingCategoryType.equals(other.pricingCategoryType)) {
			return false;
		}
		if (pubCalc == null) {
			if (other.pubCalc != null) {
				return false;
			}
		} else if (!pubCalc.equals(other.pubCalc)) {
			return false;
		}
		if (rbd == null) {
			if (other.rbd != null) {
				return false;
			}
		} else if (!rbd.equals(other.rbd)) {
			return false;
		}
		if (routingNo == null) {
			if (other.routingNo != null) {
				return false;
			}
		} else if (!routingNo.equals(other.routingNo)) {
			return false;
		}
		if (ruleNo == null) {
			if (other.ruleNo != null) {
				return false;
			}
		} else if (!ruleNo.equals(other.ruleNo)) {
			return false;
		}
		if (ruleTariff == null) {
			if (other.ruleTariff != null) {
				return false;
			}
		} else if (!ruleTariff.equals(other.ruleTariff)) {
			return false;
		}
		if (seasonType == null) {
			if (other.seasonType != null) {
				return false;
			}
		} else if (!seasonType.equals(other.seasonType)) {
			return false;
		}
		if (tableNo == null) {
			if (other.tableNo != null) {
				return false;
			}
		} else if (!tableNo.equals(other.tableNo)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "BaseFareTable [tableNo=" + tableNo + ", applicableTag=" + applicableTag + ", owrt=" + owrt + ", global="
				+ global + ", carrierCode=" + carrierCode + ", pubCalc=" + pubCalc + ", ruleTariff=" + ruleTariff
				+ ", ruleNo=" + ruleNo + ", fareClass=" + fareClass + ", fareType=" + fareType + ", passengerType="
				+ passengerType + ", seasonType=" + seasonType + ", dayType=" + dayType + ", pricingCategoryType="
				+ pricingCategoryType + ", routingNo=" + routingNo + ", footnote=" + footnote + ", rbd=" + rbd
				+ ", minFare=" + minFare + ", maxFare=" + maxFare + ", currency=" + currency + ", decimal=" + decimal
				+ "]";
	}
}