package com.atibusinessgroup.fmp.domain.atpco;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_record_3_cat_005")
public class AtpcoRecord3Cat05 {
	
	@Id
    private String id;

	@Field("rules_type")
    private String rulesType;

	@Field("record_batch")
    private String recordBatch;

	@Field("record_sequence")
    private String recordSequence;

	@Field("rec_type")
    private String recordType;

	@Field("action")
    private String action;

	@Field("cat_no")
    private String categoryNumber;

	@Field("tbl_no")
    private String tableNumber;

	@Field("adv_rsvn_first_time_of_day")
    private String advancedReservationFirstTimeOfDay;

	@Field("adv_rsvn_first_period")
    private String advancedReservationFirstPeriod;;

	@Field("adv_rsvn_first_unit")
    private String advancedReservationFirstUnit;
	
	@Field("time_of_day")
    private String advancedReservationLastTimeOfDay;

	@Field("adv_rsvn_last_period")
    private String advancedReservationLastPeriod;

	@Field("adv_rsvn_unit")
	private String advancedReservationLastUnit;

	@Field("adv_rsvn_tsi")
    private String advancedReservationTsi;

	@Field("adv_rsvn_perm")
    private String advancedReservationPerm;

	@Field("adv_rsvn_tktd")
    private String advancedReservationTktd;

	@Field("adv_rsvn_stand")
    private String advancedReservationStandby;

	@Field("confirmed_sector")
    private String confirmedSector;

	@Field("confirmed_geo_tbl_995")
    private String confirmedGeoTable995;

	@Field("confirmed_each")
    private String confirmedEachSector;

	@Field("adv_tktg_time_of_day")
    private String advancedTicketingTimeOfDay;

	@Field("adv_tktg_period")
    private String advancedTicketingPeriod;

	@Field("adv_tktg_unit_period")
    private String advancedTicketingUnit1;

	@Field("adv_tktg_opt")
    private String advancedTicketingOption;
	
	@Field("adv_tktg_dept")
    private String advancedTicketingBeforeDeparture;

	@Field("adv_tktg_unit_dept")
	private String advancedTicketingUnit2;

	@Field("adv_tktg_tsi")
    private String advancedTicketingTsi;

	@Field("adv_tktg_both")
    private String advancedTicketingBoth;

	@Field("adv_tktg_exc")
    private String advancedTicketingExc;

	@Field("adv_tktg_unit_exc")
    private String advancedTicketingUnit3;

	@Field("waiver_rsvn_date")
    private Object waiverReservationDate;
	
	@Field("waiver_tktd_date")
    private Object waiverTktdDate;

	@Field("date_tbl_no_994")
    private String dateTableNumber994;

	@Field("text_tbl_no_996")
    private String textTableNumber996;

	@Field("unavail")
    private String unavail;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRulesType() {
		return rulesType;
	}

	public void setRulesType(String rulesType) {
		this.rulesType = rulesType;
	}

	public String getRecordBatch() {
		return recordBatch;
	}

	public void setRecordBatch(String recordBatch) {
		this.recordBatch = recordBatch;
	}

	public String getRecordSequence() {
		return recordSequence;
	}

	public void setRecordSequence(String recordSequence) {
		this.recordSequence = recordSequence;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getCategoryNumber() {
		return categoryNumber;
	}

	public void setCategoryNumber(String categoryNumber) {
		this.categoryNumber = categoryNumber;
	}

	public String getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}

	public String getAdvancedReservationFirstTimeOfDay() {
		return advancedReservationFirstTimeOfDay;
	}

	public void setAdvancedReservationFirstTimeOfDay(String advancedReservationFirstTimeOfDay) {
		this.advancedReservationFirstTimeOfDay = advancedReservationFirstTimeOfDay;
	}

	public String getAdvancedReservationFirstPeriod() {
		return advancedReservationFirstPeriod;
	}

	public void setadvancedReservationFirstPeriod(String advancedReservationFirstPeriod) {
		this.advancedReservationFirstPeriod = advancedReservationFirstPeriod;
	}

	public String getAdvancedReservationFirstUnit() {
		return advancedReservationFirstUnit;
	}

	public void setadvancedReservationFirstUnit(String advancedReservationFirstUnit) {
		this.advancedReservationFirstUnit = advancedReservationFirstUnit;
	}

	public String getAdvancedReservationLastTimeOfDay() {
		return advancedReservationLastTimeOfDay;
	}

	public void setadvancedReservationLastTimeOfDay(String advancedReservationLastTimeOfDay) {
		this.advancedReservationLastTimeOfDay = advancedReservationLastTimeOfDay;
	}

	public String getAdvancedReservationLastPeriod() {
		return advancedReservationLastPeriod;
	}

	public void setadvancedReservationLastPeriod(String advancedReservationLastPeriod) {
		this.advancedReservationLastPeriod = advancedReservationLastPeriod;
	}

	public String getAdvancedReservationLastUnit() {
		return advancedReservationLastUnit;
	}

	public void setadvancedReservationLastUnit(String advancedReservationLastUnit) {
		this.advancedReservationLastUnit = advancedReservationLastUnit;
	}

	public String getAdvancedReservationTsi() {
		return advancedReservationTsi;
	}

	public void setadvancedReservationTsi(String advancedReservationTsi) {
		this.advancedReservationTsi = advancedReservationTsi;
	}

	public String getAdvancedReservationPerm() {
		return advancedReservationPerm;
	}

	public void setadvancedReservationPerm(String advancedReservationPerm) {
		this.advancedReservationPerm = advancedReservationPerm;
	}

	public String getAdvancedReservationTktd() {
		return advancedReservationTktd;
	}

	public void setadvancedReservationTktd(String advancedReservationTktd) {
		this.advancedReservationTktd = advancedReservationTktd;
	}

	public String getAdvancedReservationStandby() {
		return advancedReservationStandby;
	}

	public void setadvancedReservationStandby(String advancedReservationStandby) {
		this.advancedReservationStandby = advancedReservationStandby;
	}

	public String getConfirmedSector() {
		return confirmedSector;
	}

	public void setConfirmedSector(String confirmedSector) {
		this.confirmedSector = confirmedSector;
	}

	public String getConfirmedGeoTable995() {
		return confirmedGeoTable995;
	}

	public void setConfirmedGeoTable995(String confirmedGeoTable995) {
		this.confirmedGeoTable995 = confirmedGeoTable995;
	}

	public String getConfirmedEachSector() {
		return confirmedEachSector;
	}

	public void setConfirmedEachSector(String confirmedEachSector) {
		this.confirmedEachSector = confirmedEachSector;
	}

	public String getAdvancedTicketingTimeOfDay() {
		return advancedTicketingTimeOfDay;
	}

	public void setadvancedTicketingTimeOfDay(String advancedTicketingTimeOfDay) {
		this.advancedTicketingTimeOfDay = advancedTicketingTimeOfDay;
	}

	public String getAdvancedTicketingPeriod() {
		return advancedTicketingPeriod;
	}

	public void setadvancedTicketingPeriod(String advancedTicketingPeriod) {
		this.advancedTicketingPeriod = advancedTicketingPeriod;
	}

	public String getAdvancedTicketingUnit1() {
		return advancedTicketingUnit1;
	}

	public void setadvancedTicketingUnit1(String advancedTicketingUnit1) {
		this.advancedTicketingUnit1 = advancedTicketingUnit1;
	}

	public String getAdvancedTicketingOption() {
		return advancedTicketingOption;
	}

	public void setadvancedTicketingOption(String advancedTicketingOption) {
		this.advancedTicketingOption = advancedTicketingOption;
	}

	public String getAdvancedTicketingBeforeDeparture() {
		return advancedTicketingBeforeDeparture;
	}

	public void setadvancedTicketingBeforeDeparture(String advancedTicketingBeforeDeparture) {
		this.advancedTicketingBeforeDeparture = advancedTicketingBeforeDeparture;
	}

	public String getAdvancedTicketingUnit2() {
		return advancedTicketingUnit2;
	}

	public void setadvancedTicketingUnit2(String advancedTicketingUnit2) {
		this.advancedTicketingUnit2 = advancedTicketingUnit2;
	}

	public String getAdvancedTicketingTsi() {
		return advancedTicketingTsi;
	}

	public void setadvancedTicketingTsi(String advancedTicketingTsi) {
		this.advancedTicketingTsi = advancedTicketingTsi;
	}

	public String getAdvancedTicketingBoth() {
		return advancedTicketingBoth;
	}

	public void setadvancedTicketingBoth(String advancedTicketingBoth) {
		this.advancedTicketingBoth = advancedTicketingBoth;
	}

	public String getAdvancedTicketingExc() {
		return advancedTicketingExc;
	}

	public void setadvancedTicketingExc(String advancedTicketingExc) {
		this.advancedTicketingExc = advancedTicketingExc;
	}

	public String getAdvancedTicketingUnit3() {
		return advancedTicketingUnit3;
	}

	public void setadvancedTicketingUnit3(String advancedTicketingUnit3) {
		this.advancedTicketingUnit3 = advancedTicketingUnit3;
	}

	public Object getWaiverReservationDate() {
		return waiverReservationDate;
	}

	public void setWaiverReservationDate(Object waiverReservationDate) {
		this.waiverReservationDate = waiverReservationDate;
	}

	public Object getWaiverTktdDate() {
		return waiverTktdDate;
	}

	public void setWaiverTktdDate(Object waiverTktdDate) {
		this.waiverTktdDate = waiverTktdDate;
	}

	public String getDateTableNumber994() {
		return dateTableNumber994;
	}

	public void setDateTableNumber994(String dateTableNumber994) {
		this.dateTableNumber994 = dateTableNumber994;
	}

	public String getTextTableNumber996() {
		return textTableNumber996;
	}

	public void setTextTableNumber996(String textTableNumber996) {
		this.textTableNumber996 = textTableNumber996;
	}

	public String getUnavail() {
		return unavail;
	}

	public void setUnavail(String unavail) {
		this.unavail = unavail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result
				+ ((advancedReservationFirstTimeOfDay == null) ? 0 : advancedReservationFirstTimeOfDay.hashCode());
		result = prime * result
				+ ((advancedReservationFirstPeriod == null) ? 0 : advancedReservationFirstPeriod.hashCode());
		result = prime * result
				+ ((advancedReservationFirstUnit == null) ? 0 : advancedReservationFirstUnit.hashCode());
		result = prime * result
				+ ((advancedReservationLastPeriod == null) ? 0 : advancedReservationLastPeriod.hashCode());
		result = prime * result
				+ ((advancedReservationLastTimeOfDay == null) ? 0 : advancedReservationLastTimeOfDay.hashCode());
		result = prime * result + ((advancedReservationLastUnit == null) ? 0 : advancedReservationLastUnit.hashCode());
		result = prime * result + ((advancedReservationPerm == null) ? 0 : advancedReservationPerm.hashCode());
		result = prime * result + ((advancedReservationStandby == null) ? 0 : advancedReservationStandby.hashCode());
		result = prime * result + ((advancedReservationTktd == null) ? 0 : advancedReservationTktd.hashCode());
		result = prime * result + ((advancedReservationTsi == null) ? 0 : advancedReservationTsi.hashCode());
		result = prime * result
				+ ((advancedTicketingBeforeDeparture == null) ? 0 : advancedTicketingBeforeDeparture.hashCode());
		result = prime * result + ((advancedTicketingBoth == null) ? 0 : advancedTicketingBoth.hashCode());
		result = prime * result + ((advancedTicketingExc == null) ? 0 : advancedTicketingExc.hashCode());
		result = prime * result + ((advancedTicketingTsi == null) ? 0 : advancedTicketingTsi.hashCode());
		result = prime * result + ((advancedTicketingUnit2 == null) ? 0 : advancedTicketingUnit2.hashCode());
		result = prime * result + ((advancedTicketingUnit3 == null) ? 0 : advancedTicketingUnit3.hashCode());
		result = prime * result + ((advancedTicketingOption == null) ? 0 : advancedTicketingOption.hashCode());
		result = prime * result + ((advancedTicketingPeriod == null) ? 0 : advancedTicketingPeriod.hashCode());
		result = prime * result + ((advancedTicketingTimeOfDay == null) ? 0 : advancedTicketingTimeOfDay.hashCode());
		result = prime * result + ((advancedTicketingUnit1 == null) ? 0 : advancedTicketingUnit1.hashCode());
		result = prime * result + ((categoryNumber == null) ? 0 : categoryNumber.hashCode());
		result = prime * result + ((confirmedEachSector == null) ? 0 : confirmedEachSector.hashCode());
		result = prime * result + ((confirmedGeoTable995 == null) ? 0 : confirmedGeoTable995.hashCode());
		result = prime * result + ((confirmedSector == null) ? 0 : confirmedSector.hashCode());
		result = prime * result + ((dateTableNumber994 == null) ? 0 : dateTableNumber994.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((recordBatch == null) ? 0 : recordBatch.hashCode());
		result = prime * result + ((recordSequence == null) ? 0 : recordSequence.hashCode());
		result = prime * result + ((recordType == null) ? 0 : recordType.hashCode());
		result = prime * result + ((rulesType == null) ? 0 : rulesType.hashCode());
		result = prime * result + ((tableNumber == null) ? 0 : tableNumber.hashCode());
		result = prime * result + ((textTableNumber996 == null) ? 0 : textTableNumber996.hashCode());
		result = prime * result + ((unavail == null) ? 0 : unavail.hashCode());
		result = prime * result + ((waiverReservationDate == null) ? 0 : waiverReservationDate.hashCode());
		result = prime * result + ((waiverTktdDate == null) ? 0 : waiverTktdDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtpcoRecord3Cat05 other = (AtpcoRecord3Cat05) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (advancedReservationFirstTimeOfDay == null) {
			if (other.advancedReservationFirstTimeOfDay != null)
				return false;
		} else if (!advancedReservationFirstTimeOfDay.equals(other.advancedReservationFirstTimeOfDay))
			return false;
		if (advancedReservationFirstPeriod == null) {
			if (other.advancedReservationFirstPeriod != null)
				return false;
		} else if (!advancedReservationFirstPeriod.equals(other.advancedReservationFirstPeriod))
			return false;
		if (advancedReservationFirstUnit == null) {
			if (other.advancedReservationFirstUnit != null)
				return false;
		} else if (!advancedReservationFirstUnit.equals(other.advancedReservationFirstUnit))
			return false;
		if (advancedReservationLastPeriod == null) {
			if (other.advancedReservationLastPeriod != null)
				return false;
		} else if (!advancedReservationLastPeriod.equals(other.advancedReservationLastPeriod))
			return false;
		if (advancedReservationLastTimeOfDay == null) {
			if (other.advancedReservationLastTimeOfDay != null)
				return false;
		} else if (!advancedReservationLastTimeOfDay.equals(other.advancedReservationLastTimeOfDay))
			return false;
		if (advancedReservationLastUnit == null) {
			if (other.advancedReservationLastUnit != null)
				return false;
		} else if (!advancedReservationLastUnit.equals(other.advancedReservationLastUnit))
			return false;
		if (advancedReservationPerm == null) {
			if (other.advancedReservationPerm != null)
				return false;
		} else if (!advancedReservationPerm.equals(other.advancedReservationPerm))
			return false;
		if (advancedReservationStandby == null) {
			if (other.advancedReservationStandby != null)
				return false;
		} else if (!advancedReservationStandby.equals(other.advancedReservationStandby))
			return false;
		if (advancedReservationTktd == null) {
			if (other.advancedReservationTktd != null)
				return false;
		} else if (!advancedReservationTktd.equals(other.advancedReservationTktd))
			return false;
		if (advancedReservationTsi == null) {
			if (other.advancedReservationTsi != null)
				return false;
		} else if (!advancedReservationTsi.equals(other.advancedReservationTsi))
			return false;
		if (advancedTicketingBeforeDeparture == null) {
			if (other.advancedTicketingBeforeDeparture != null)
				return false;
		} else if (!advancedTicketingBeforeDeparture.equals(other.advancedTicketingBeforeDeparture))
			return false;
		if (advancedTicketingBoth == null) {
			if (other.advancedTicketingBoth != null)
				return false;
		} else if (!advancedTicketingBoth.equals(other.advancedTicketingBoth))
			return false;
		if (advancedTicketingExc == null) {
			if (other.advancedTicketingExc != null)
				return false;
		} else if (!advancedTicketingExc.equals(other.advancedTicketingExc))
			return false;
		if (advancedTicketingTsi == null) {
			if (other.advancedTicketingTsi != null)
				return false;
		} else if (!advancedTicketingTsi.equals(other.advancedTicketingTsi))
			return false;
		if (advancedTicketingUnit2 == null) {
			if (other.advancedTicketingUnit2 != null)
				return false;
		} else if (!advancedTicketingUnit2.equals(other.advancedTicketingUnit2))
			return false;
		if (advancedTicketingUnit3 == null) {
			if (other.advancedTicketingUnit3 != null)
				return false;
		} else if (!advancedTicketingUnit3.equals(other.advancedTicketingUnit3))
			return false;
		if (advancedTicketingOption == null) {
			if (other.advancedTicketingOption != null)
				return false;
		} else if (!advancedTicketingOption.equals(other.advancedTicketingOption))
			return false;
		if (advancedTicketingPeriod == null) {
			if (other.advancedTicketingPeriod != null)
				return false;
		} else if (!advancedTicketingPeriod.equals(other.advancedTicketingPeriod))
			return false;
		if (advancedTicketingTimeOfDay == null) {
			if (other.advancedTicketingTimeOfDay != null)
				return false;
		} else if (!advancedTicketingTimeOfDay.equals(other.advancedTicketingTimeOfDay))
			return false;
		if (advancedTicketingUnit1 == null) {
			if (other.advancedTicketingUnit1 != null)
				return false;
		} else if (!advancedTicketingUnit1.equals(other.advancedTicketingUnit1))
			return false;
		if (categoryNumber == null) {
			if (other.categoryNumber != null)
				return false;
		} else if (!categoryNumber.equals(other.categoryNumber))
			return false;
		if (confirmedEachSector == null) {
			if (other.confirmedEachSector != null)
				return false;
		} else if (!confirmedEachSector.equals(other.confirmedEachSector))
			return false;
		if (confirmedGeoTable995 == null) {
			if (other.confirmedGeoTable995 != null)
				return false;
		} else if (!confirmedGeoTable995.equals(other.confirmedGeoTable995))
			return false;
		if (confirmedSector == null) {
			if (other.confirmedSector != null)
				return false;
		} else if (!confirmedSector.equals(other.confirmedSector))
			return false;
		if (dateTableNumber994 == null) {
			if (other.dateTableNumber994 != null)
				return false;
		} else if (!dateTableNumber994.equals(other.dateTableNumber994))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (recordBatch == null) {
			if (other.recordBatch != null)
				return false;
		} else if (!recordBatch.equals(other.recordBatch))
			return false;
		if (recordSequence == null) {
			if (other.recordSequence != null)
				return false;
		} else if (!recordSequence.equals(other.recordSequence))
			return false;
		if (recordType == null) {
			if (other.recordType != null)
				return false;
		} else if (!recordType.equals(other.recordType))
			return false;
		if (rulesType == null) {
			if (other.rulesType != null)
				return false;
		} else if (!rulesType.equals(other.rulesType))
			return false;
		if (tableNumber == null) {
			if (other.tableNumber != null)
				return false;
		} else if (!tableNumber.equals(other.tableNumber))
			return false;
		if (textTableNumber996 == null) {
			if (other.textTableNumber996 != null)
				return false;
		} else if (!textTableNumber996.equals(other.textTableNumber996))
			return false;
		if (unavail == null) {
			if (other.unavail != null)
				return false;
		} else if (!unavail.equals(other.unavail))
			return false;
		if (waiverReservationDate == null) {
			if (other.waiverReservationDate != null)
				return false;
		} else if (!waiverReservationDate.equals(other.waiverReservationDate))
			return false;
		if (waiverTktdDate == null) {
			if (other.waiverTktdDate != null)
				return false;
		} else if (!waiverTktdDate.equals(other.waiverTktdDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoRecord3Cat05 [id=" + id + ", rulesType=" + rulesType + ", recordBatch=" + recordBatch
				+ ", recordSequence=" + recordSequence + ", recordType=" + recordType + ", action=" + action
				+ ", categoryNumber=" + categoryNumber + ", tableNumber=" + tableNumber
				+ ", advancedReservationFirstTimeOfDay=" + advancedReservationFirstTimeOfDay
				+ ", advancedReservationFirstPeriod=" + advancedReservationFirstPeriod
				+ ", advancedReservationFirstUnit=" + advancedReservationFirstUnit
				+ ", advancedReservationLastTimeOfDay=" + advancedReservationLastTimeOfDay
				+ ", advancedReservationLastPeriod=" + advancedReservationLastPeriod + ", advancedReservationLastUnit="
				+ advancedReservationLastUnit + ", advancedReservationTsi=" + advancedReservationTsi
				+ ", advancedReservationPerm=" + advancedReservationPerm + ", advancedReservationTktd="
				+ advancedReservationTktd + ", advancedReservationStandby=" + advancedReservationStandby
				+ ", confirmedSector=" + confirmedSector + ", confirmedGeoTable995=" + confirmedGeoTable995
				+ ", confirmedEachSector=" + confirmedEachSector + ", advancedTicketingTimeOfDay="
				+ advancedTicketingTimeOfDay + ", advancedTicketingPeriod=" + advancedTicketingPeriod
				+ ", advancedTicketingUnit1=" + advancedTicketingUnit1 + ", advancedTicketingOption="
				+ advancedTicketingOption + ", advancedTicketingBeforeDeparture=" + advancedTicketingBeforeDeparture
				+ ", advancedTicketingUnit2=" + advancedTicketingUnit2 + ", advancedTicketingTsi="
				+ advancedTicketingTsi + ", advancedTicketingBoth=" + advancedTicketingBoth + ", advancedTicketingExc="
				+ advancedTicketingExc + ", advancedTicketingUnit3=" + advancedTicketingUnit3
				+ ", waiverReservationDate=" + waiverReservationDate + ", waiverTktdDate=" + waiverTktdDate
				+ ", dateTableNumber994=" + dateTableNumber994 + ", textTableNumber996=" + textTableNumber996
				+ ", unavail=" + unavail + "]";
	}
	
	

}
