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
    private String advencedReservationFirstPeriod;;

	@Field("adv_rsvn_first_unit")
    private String advencedReservationFirstUnit;
	
	@Field("time_of_day")
    private String advencedReservationLastTimeOfDay;

	@Field("adv_rsvn_last_period")
    private String advencedReservationLastPeriod;

	@Field("adv_rsvn_unit")
    private String advencedReservationLastUnit;

	@Field("adv_rsvn_tsi")
    private String advencedReservationTsi;

	@Field("adv_rsvn_perm")
    private String advencedReservationPerm;

	@Field("adv_rsvn_tktd")
    private String advencedReservationTktd;

	@Field("adv_rsvn_stand")
    private String advencedReservationStandby;

	@Field("confirmed_sector")
    private String confirmedSector;

	@Field("confirmed_geo_tbl_995")
    private String confirmedGeoTable995;

	@Field("confirmed_each")
    private String confirmedEachSector;

	@Field("adv_tktg_time_of_day")
    private String advencedTiketingTimeOfDay;

	@Field("adv_tktg_period")
    private String advencedTiketingPeriod;

	@Field("adv_tktg_unit_period")
    private String advencedTiketingUnit1;

	@Field("adv_tktg_opt")
    private String advencedTiketingOption;
	
	@Field("adv_tktg_dept")
    private String advencedTicketingBeforeDeparture;

	@Field("adv_tktg_unit_dept")
	private String advencedTicketingUnit2;

	@Field("adv_tktg_tsi")
    private String advencedTicketingTsi;

	@Field("adv_tktg_both")
    private String advencedTicketingBoth;

	@Field("adv_tktg_exc")
    private String advencedTicketingExc;

	@Field("adv_tktg_unit_exc")
    private String advencedTicketingUnit3;

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

	public String getAdvencedReservationFirstPeriod() {
		return advencedReservationFirstPeriod;
	}

	public void setAdvencedReservationFirstPeriod(String advencedReservationFirstPeriod) {
		this.advencedReservationFirstPeriod = advencedReservationFirstPeriod;
	}

	public String getAdvencedReservationFirstUnit() {
		return advencedReservationFirstUnit;
	}

	public void setAdvencedReservationFirstUnit(String advencedReservationFirstUnit) {
		this.advencedReservationFirstUnit = advencedReservationFirstUnit;
	}

	public String getAdvencedReservationLastTimeOfDay() {
		return advencedReservationLastTimeOfDay;
	}

	public void setAdvencedReservationLastTimeOfDay(String advencedReservationLastTimeOfDay) {
		this.advencedReservationLastTimeOfDay = advencedReservationLastTimeOfDay;
	}

	public String getAdvencedReservationLastPeriod() {
		return advencedReservationLastPeriod;
	}

	public void setAdvencedReservationLastPeriod(String advencedReservationLastPeriod) {
		this.advencedReservationLastPeriod = advencedReservationLastPeriod;
	}

	public String getAdvencedReservationLastUnit() {
		return advencedReservationLastUnit;
	}

	public void setAdvencedReservationLastUnit(String advencedReservationLastUnit) {
		this.advencedReservationLastUnit = advencedReservationLastUnit;
	}

	public String getAdvencedReservationTsi() {
		return advencedReservationTsi;
	}

	public void setAdvencedReservationTsi(String advencedReservationTsi) {
		this.advencedReservationTsi = advencedReservationTsi;
	}

	public String getAdvencedReservationPerm() {
		return advencedReservationPerm;
	}

	public void setAdvencedReservationPerm(String advencedReservationPerm) {
		this.advencedReservationPerm = advencedReservationPerm;
	}

	public String getAdvencedReservationTktd() {
		return advencedReservationTktd;
	}

	public void setAdvencedReservationTktd(String advencedReservationTktd) {
		this.advencedReservationTktd = advencedReservationTktd;
	}

	public String getAdvencedReservationStandby() {
		return advencedReservationStandby;
	}

	public void setAdvencedReservationStandby(String advencedReservationStandby) {
		this.advencedReservationStandby = advencedReservationStandby;
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

	public String getAdvencedTiketingTimeOfDay() {
		return advencedTiketingTimeOfDay;
	}

	public void setAdvencedTiketingTimeOfDay(String advencedTiketingTimeOfDay) {
		this.advencedTiketingTimeOfDay = advencedTiketingTimeOfDay;
	}

	public String getAdvencedTiketingPeriod() {
		return advencedTiketingPeriod;
	}

	public void setAdvencedTiketingPeriod(String advencedTiketingPeriod) {
		this.advencedTiketingPeriod = advencedTiketingPeriod;
	}

	public String getAdvencedTiketingUnit1() {
		return advencedTiketingUnit1;
	}

	public void setAdvencedTiketingUnit1(String advencedTiketingUnit1) {
		this.advencedTiketingUnit1 = advencedTiketingUnit1;
	}

	public String getAdvencedTiketingOption() {
		return advencedTiketingOption;
	}

	public void setAdvencedTiketingOption(String advencedTiketingOption) {
		this.advencedTiketingOption = advencedTiketingOption;
	}

	public String getAdvencedTicketingBeforeDeparture() {
		return advencedTicketingBeforeDeparture;
	}

	public void setAdvencedTicketingBeforeDeparture(String advencedTicketingBeforeDeparture) {
		this.advencedTicketingBeforeDeparture = advencedTicketingBeforeDeparture;
	}

	public String getAdvencedTicketingUnit2() {
		return advencedTicketingUnit2;
	}

	public void setAdvencedTicketingUnit2(String advencedTicketingUnit2) {
		this.advencedTicketingUnit2 = advencedTicketingUnit2;
	}

	public String getAdvencedTicketingTsi() {
		return advencedTicketingTsi;
	}

	public void setAdvencedTicketingTsi(String advencedTicketingTsi) {
		this.advencedTicketingTsi = advencedTicketingTsi;
	}

	public String getAdvencedTicketingBoth() {
		return advencedTicketingBoth;
	}

	public void setAdvencedTicketingBoth(String advencedTicketingBoth) {
		this.advencedTicketingBoth = advencedTicketingBoth;
	}

	public String getAdvencedTicketingExc() {
		return advencedTicketingExc;
	}

	public void setAdvencedTicketingExc(String advencedTicketingExc) {
		this.advencedTicketingExc = advencedTicketingExc;
	}

	public String getAdvencedTicketingUnit3() {
		return advencedTicketingUnit3;
	}

	public void setAdvencedTicketingUnit3(String advencedTicketingUnit3) {
		this.advencedTicketingUnit3 = advencedTicketingUnit3;
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
				+ ((advencedReservationFirstPeriod == null) ? 0 : advencedReservationFirstPeriod.hashCode());
		result = prime * result
				+ ((advencedReservationFirstUnit == null) ? 0 : advencedReservationFirstUnit.hashCode());
		result = prime * result
				+ ((advencedReservationLastPeriod == null) ? 0 : advencedReservationLastPeriod.hashCode());
		result = prime * result
				+ ((advencedReservationLastTimeOfDay == null) ? 0 : advencedReservationLastTimeOfDay.hashCode());
		result = prime * result + ((advencedReservationLastUnit == null) ? 0 : advencedReservationLastUnit.hashCode());
		result = prime * result + ((advencedReservationPerm == null) ? 0 : advencedReservationPerm.hashCode());
		result = prime * result + ((advencedReservationStandby == null) ? 0 : advencedReservationStandby.hashCode());
		result = prime * result + ((advencedReservationTktd == null) ? 0 : advencedReservationTktd.hashCode());
		result = prime * result + ((advencedReservationTsi == null) ? 0 : advencedReservationTsi.hashCode());
		result = prime * result
				+ ((advencedTicketingBeforeDeparture == null) ? 0 : advencedTicketingBeforeDeparture.hashCode());
		result = prime * result + ((advencedTicketingBoth == null) ? 0 : advencedTicketingBoth.hashCode());
		result = prime * result + ((advencedTicketingExc == null) ? 0 : advencedTicketingExc.hashCode());
		result = prime * result + ((advencedTicketingTsi == null) ? 0 : advencedTicketingTsi.hashCode());
		result = prime * result + ((advencedTicketingUnit2 == null) ? 0 : advencedTicketingUnit2.hashCode());
		result = prime * result + ((advencedTicketingUnit3 == null) ? 0 : advencedTicketingUnit3.hashCode());
		result = prime * result + ((advencedTiketingOption == null) ? 0 : advencedTiketingOption.hashCode());
		result = prime * result + ((advencedTiketingPeriod == null) ? 0 : advencedTiketingPeriod.hashCode());
		result = prime * result + ((advencedTiketingTimeOfDay == null) ? 0 : advencedTiketingTimeOfDay.hashCode());
		result = prime * result + ((advencedTiketingUnit1 == null) ? 0 : advencedTiketingUnit1.hashCode());
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
		if (advencedReservationFirstPeriod == null) {
			if (other.advencedReservationFirstPeriod != null)
				return false;
		} else if (!advencedReservationFirstPeriod.equals(other.advencedReservationFirstPeriod))
			return false;
		if (advencedReservationFirstUnit == null) {
			if (other.advencedReservationFirstUnit != null)
				return false;
		} else if (!advencedReservationFirstUnit.equals(other.advencedReservationFirstUnit))
			return false;
		if (advencedReservationLastPeriod == null) {
			if (other.advencedReservationLastPeriod != null)
				return false;
		} else if (!advencedReservationLastPeriod.equals(other.advencedReservationLastPeriod))
			return false;
		if (advencedReservationLastTimeOfDay == null) {
			if (other.advencedReservationLastTimeOfDay != null)
				return false;
		} else if (!advencedReservationLastTimeOfDay.equals(other.advencedReservationLastTimeOfDay))
			return false;
		if (advencedReservationLastUnit == null) {
			if (other.advencedReservationLastUnit != null)
				return false;
		} else if (!advencedReservationLastUnit.equals(other.advencedReservationLastUnit))
			return false;
		if (advencedReservationPerm == null) {
			if (other.advencedReservationPerm != null)
				return false;
		} else if (!advencedReservationPerm.equals(other.advencedReservationPerm))
			return false;
		if (advencedReservationStandby == null) {
			if (other.advencedReservationStandby != null)
				return false;
		} else if (!advencedReservationStandby.equals(other.advencedReservationStandby))
			return false;
		if (advencedReservationTktd == null) {
			if (other.advencedReservationTktd != null)
				return false;
		} else if (!advencedReservationTktd.equals(other.advencedReservationTktd))
			return false;
		if (advencedReservationTsi == null) {
			if (other.advencedReservationTsi != null)
				return false;
		} else if (!advencedReservationTsi.equals(other.advencedReservationTsi))
			return false;
		if (advencedTicketingBeforeDeparture == null) {
			if (other.advencedTicketingBeforeDeparture != null)
				return false;
		} else if (!advencedTicketingBeforeDeparture.equals(other.advencedTicketingBeforeDeparture))
			return false;
		if (advencedTicketingBoth == null) {
			if (other.advencedTicketingBoth != null)
				return false;
		} else if (!advencedTicketingBoth.equals(other.advencedTicketingBoth))
			return false;
		if (advencedTicketingExc == null) {
			if (other.advencedTicketingExc != null)
				return false;
		} else if (!advencedTicketingExc.equals(other.advencedTicketingExc))
			return false;
		if (advencedTicketingTsi == null) {
			if (other.advencedTicketingTsi != null)
				return false;
		} else if (!advencedTicketingTsi.equals(other.advencedTicketingTsi))
			return false;
		if (advencedTicketingUnit2 == null) {
			if (other.advencedTicketingUnit2 != null)
				return false;
		} else if (!advencedTicketingUnit2.equals(other.advencedTicketingUnit2))
			return false;
		if (advencedTicketingUnit3 == null) {
			if (other.advencedTicketingUnit3 != null)
				return false;
		} else if (!advencedTicketingUnit3.equals(other.advencedTicketingUnit3))
			return false;
		if (advencedTiketingOption == null) {
			if (other.advencedTiketingOption != null)
				return false;
		} else if (!advencedTiketingOption.equals(other.advencedTiketingOption))
			return false;
		if (advencedTiketingPeriod == null) {
			if (other.advencedTiketingPeriod != null)
				return false;
		} else if (!advencedTiketingPeriod.equals(other.advencedTiketingPeriod))
			return false;
		if (advencedTiketingTimeOfDay == null) {
			if (other.advencedTiketingTimeOfDay != null)
				return false;
		} else if (!advencedTiketingTimeOfDay.equals(other.advencedTiketingTimeOfDay))
			return false;
		if (advencedTiketingUnit1 == null) {
			if (other.advencedTiketingUnit1 != null)
				return false;
		} else if (!advencedTiketingUnit1.equals(other.advencedTiketingUnit1))
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
				+ ", advencedReservationFirstPeriod=" + advencedReservationFirstPeriod
				+ ", advencedReservationFirstUnit=" + advencedReservationFirstUnit
				+ ", advencedReservationLastTimeOfDay=" + advencedReservationLastTimeOfDay
				+ ", advencedReservationLastPeriod=" + advencedReservationLastPeriod + ", advencedReservationLastUnit="
				+ advencedReservationLastUnit + ", advencedReservationTsi=" + advencedReservationTsi
				+ ", advencedReservationPerm=" + advencedReservationPerm + ", advencedReservationTktd="
				+ advencedReservationTktd + ", advencedReservationStandby=" + advencedReservationStandby
				+ ", confirmedSector=" + confirmedSector + ", confirmedGeoTable995=" + confirmedGeoTable995
				+ ", confirmedEachSector=" + confirmedEachSector + ", advencedTiketingTimeOfDay="
				+ advencedTiketingTimeOfDay + ", advencedTiketingPeriod=" + advencedTiketingPeriod
				+ ", advencedTiketingUnit1=" + advencedTiketingUnit1 + ", advencedTiketingOption="
				+ advencedTiketingOption + ", advencedTicketingBeforeDeparture=" + advencedTicketingBeforeDeparture
				+ ", advencedTicketingUnit2=" + advencedTicketingUnit2 + ", advencedTicketingTsi="
				+ advencedTicketingTsi + ", advencedTicketingBoth=" + advencedTicketingBoth + ", advencedTicketingExc="
				+ advencedTicketingExc + ", advencedTicketingUnit3=" + advencedTicketingUnit3
				+ ", waiverReservationDate=" + waiverReservationDate + ", waiverTktdDate=" + waiverTktdDate
				+ ", dateTableNumber994=" + dateTableNumber994 + ", textTableNumber996=" + textTableNumber996
				+ ", unavail=" + unavail + "]";
	}
	
	

}
