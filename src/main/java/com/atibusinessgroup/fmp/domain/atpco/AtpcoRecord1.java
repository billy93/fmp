package com.atibusinessgroup.fmp.domain.atpco;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.atibusinessgroup.fmp.domain.dto.AtpcoRecord1FareClassInformation;

@Document(collection = "atpco_record_1")
public class AtpcoRecord1 {

	@Id
    private String id;
	
	@Field("rules_type")
    private String ruleType;
	
	@Field("record_id")
    private String recordId;
	
	@Field("record_id_map")
    private String recordIdMap;
	
	@Field("record_batch")
    private String recordBatch;
	
	@Field("rec_type")
    private int recordType;
	
	@Field("action")
    private String action;
	
	@Field("rule_tar_no")
    private String ruleTariffNo;
	
	@Field("cxr_code")
    private String carrierCode;
	
	@Field("rule_no")
    private String ruleNo;
	
	@Field("fare_class")
    private String fareClassCode;
	
	@Field("mcn")
    private String mcn;
	
	@Field("seq_no")
    private String sequenceNo;
	
	@Field("geo_type_1")
    private String geoType1;
	
	@Field("geo_loc_1")
    private String geoLoc1;
	
	@Field("geo_type_2")
    private String geoType2;
	
	@Field("geo_loc_2")
    private String geoLoc2;
	
	@Field("owrt")
    private String owrt;
	
	@Field("rtg_no")
    private String routingNo;
	
	@Field("ft_nt")
    private String footnote;
	
	@Field("dates_eff")
    private Object effectiveDateObject;
	
	@Field("dates_disc")
    private Object discontinueDateObject;
	
	@Field("batch_ci")
    private String batchCi;
	
	@Field("batch_no")
    private String batchNo;
	
	@Field("fare_type")
    private String fareType;
	
	@Field("season_type")
    private String seasonType;
	
	@Field("day_of_week_type")
    private String dayOfWeekType;
	
	@Field("prc_cat")
    private String pricingCategoryType;
	
	@Field("dis_cat")
    private String displayCategoryType;
	
	@Field("unavail")
    private String unavailableDataTag;
	
	@Field("text_tbl_no_996")
    private String textTableNo996;
	
	@Field("fare_class_information")
    private List<AtpcoRecord1FareClassInformation> fareClassInformation = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getRecordIdMap() {
		return recordIdMap;
	}

	public void setRecordIdMap(String recordIdMap) {
		this.recordIdMap = recordIdMap;
	}

	public String getRecordBatch() {
		return recordBatch;
	}

	public void setRecordBatch(String recordBatch) {
		this.recordBatch = recordBatch;
	}

	public int getRecordType() {
		return recordType;
	}

	public void setRecordType(int recordType) {
		this.recordType = recordType;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getRuleTariffNo() {
		return ruleTariffNo;
	}

	public void setRuleTariffNo(String ruleTariffNo) {
		this.ruleTariffNo = ruleTariffNo;
	}

	public String getCarrierCode() {
		return carrierCode;
	}

	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}

	public String getRuleNo() {
		return ruleNo;
	}

	public void setRuleNo(String ruleNo) {
		this.ruleNo = ruleNo;
	}

	public String getFareClassCode() {
		return fareClassCode;
	}

	public void setFareClassCode(String fareClassCode) {
		this.fareClassCode = fareClassCode;
	}

	public String getMcn() {
		return mcn;
	}

	public void setMcn(String mcn) {
		this.mcn = mcn;
	}

	public String getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(String sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public String getGeoType1() {
		return geoType1;
	}

	public void setGeoType1(String geoType1) {
		this.geoType1 = geoType1;
	}

	public String getGeoLoc1() {
		return geoLoc1;
	}

	public void setGeoLoc1(String geoLoc1) {
		this.geoLoc1 = geoLoc1;
	}

	public String getGeoType2() {
		return geoType2;
	}

	public void setGeoType2(String geoType2) {
		this.geoType2 = geoType2;
	}

	public String getGeoLoc2() {
		return geoLoc2;
	}

	public void setGeoLoc2(String geoLoc2) {
		this.geoLoc2 = geoLoc2;
	}

	public String getOwrt() {
		return owrt;
	}

	public void setOwrt(String owrt) {
		this.owrt = owrt;
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

	public Object getEffectiveDateObject() {
		return effectiveDateObject;
	}

	public void setEffectiveDateObject(Object effectiveDateObject) {
		this.effectiveDateObject = effectiveDateObject;
	}

	public Object getDiscontinueDateObject() {
		return discontinueDateObject;
	}

	public void setDiscontinueDateObject(Object discontinueDateObject) {
		this.discontinueDateObject = discontinueDateObject;
	}

	public String getBatchCi() {
		return batchCi;
	}

	public void setBatchCi(String batchCi) {
		this.batchCi = batchCi;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getFareType() {
		return fareType;
	}

	public void setFareType(String fareType) {
		this.fareType = fareType;
	}

	public String getSeasonType() {
		return seasonType;
	}

	public void setSeasonType(String seasonType) {
		this.seasonType = seasonType;
	}

	public String getDayOfWeekType() {
		return dayOfWeekType;
	}

	public void setDayOfWeekType(String dayOfWeekType) {
		this.dayOfWeekType = dayOfWeekType;
	}

	public String getPricingCategoryType() {
		return pricingCategoryType;
	}

	public void setPricingCategoryType(String pricingCategoryType) {
		this.pricingCategoryType = pricingCategoryType;
	}

	public String getDisplayCategoryType() {
		return displayCategoryType;
	}

	public void setDisplayCategoryType(String displayCategoryType) {
		this.displayCategoryType = displayCategoryType;
	}

	public String getUnavailableDataTag() {
		return unavailableDataTag;
	}

	public void setUnavailableDataTag(String unavailableDataTag) {
		this.unavailableDataTag = unavailableDataTag;
	}

	public String getTextTableNo996() {
		return textTableNo996;
	}

	public void setTextTableNo996(String textTableNo996) {
		this.textTableNo996 = textTableNo996;
	}

	public List<AtpcoRecord1FareClassInformation> getFareClassInformation() {
		return fareClassInformation;
	}

	public void setFareClassInformation(List<AtpcoRecord1FareClassInformation> fareClassInformation) {
		this.fareClassInformation = fareClassInformation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((batchCi == null) ? 0 : batchCi.hashCode());
		result = prime * result + ((batchNo == null) ? 0 : batchNo.hashCode());
		result = prime * result + ((carrierCode == null) ? 0 : carrierCode.hashCode());
		result = prime * result + ((dayOfWeekType == null) ? 0 : dayOfWeekType.hashCode());
		result = prime * result + ((discontinueDateObject == null) ? 0 : discontinueDateObject.hashCode());
		result = prime * result + ((displayCategoryType == null) ? 0 : displayCategoryType.hashCode());
		result = prime * result + ((effectiveDateObject == null) ? 0 : effectiveDateObject.hashCode());
		result = prime * result + ((fareClassCode == null) ? 0 : fareClassCode.hashCode());
		result = prime * result + ((fareClassInformation == null) ? 0 : fareClassInformation.hashCode());
		result = prime * result + ((fareType == null) ? 0 : fareType.hashCode());
		result = prime * result + ((footnote == null) ? 0 : footnote.hashCode());
		result = prime * result + ((geoLoc1 == null) ? 0 : geoLoc1.hashCode());
		result = prime * result + ((geoLoc2 == null) ? 0 : geoLoc2.hashCode());
		result = prime * result + ((geoType1 == null) ? 0 : geoType1.hashCode());
		result = prime * result + ((geoType2 == null) ? 0 : geoType2.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mcn == null) ? 0 : mcn.hashCode());
		result = prime * result + ((owrt == null) ? 0 : owrt.hashCode());
		result = prime * result + ((pricingCategoryType == null) ? 0 : pricingCategoryType.hashCode());
		result = prime * result + ((recordBatch == null) ? 0 : recordBatch.hashCode());
		result = prime * result + ((recordId == null) ? 0 : recordId.hashCode());
		result = prime * result + ((recordIdMap == null) ? 0 : recordIdMap.hashCode());
		result = prime * result + recordType;
		result = prime * result + ((routingNo == null) ? 0 : routingNo.hashCode());
		result = prime * result + ((ruleNo == null) ? 0 : ruleNo.hashCode());
		result = prime * result + ((ruleTariffNo == null) ? 0 : ruleTariffNo.hashCode());
		result = prime * result + ((ruleType == null) ? 0 : ruleType.hashCode());
		result = prime * result + ((seasonType == null) ? 0 : seasonType.hashCode());
		result = prime * result + ((sequenceNo == null) ? 0 : sequenceNo.hashCode());
		result = prime * result + ((textTableNo996 == null) ? 0 : textTableNo996.hashCode());
		result = prime * result + ((unavailableDataTag == null) ? 0 : unavailableDataTag.hashCode());
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
		AtpcoRecord1 other = (AtpcoRecord1) obj;
		if (action == null) {
			if (other.action != null) {
				return false;
			}
		} else if (!action.equals(other.action)) {
			return false;
		}
		if (batchCi == null) {
			if (other.batchCi != null) {
				return false;
			}
		} else if (!batchCi.equals(other.batchCi)) {
			return false;
		}
		if (batchNo == null) {
			if (other.batchNo != null) {
				return false;
			}
		} else if (!batchNo.equals(other.batchNo)) {
			return false;
		}
		if (carrierCode == null) {
			if (other.carrierCode != null) {
				return false;
			}
		} else if (!carrierCode.equals(other.carrierCode)) {
			return false;
		}
		if (dayOfWeekType == null) {
			if (other.dayOfWeekType != null) {
				return false;
			}
		} else if (!dayOfWeekType.equals(other.dayOfWeekType)) {
			return false;
		}
		if (discontinueDateObject == null) {
			if (other.discontinueDateObject != null) {
				return false;
			}
		} else if (!discontinueDateObject.equals(other.discontinueDateObject)) {
			return false;
		}
		if (displayCategoryType == null) {
			if (other.displayCategoryType != null) {
				return false;
			}
		} else if (!displayCategoryType.equals(other.displayCategoryType)) {
			return false;
		}
		if (effectiveDateObject == null) {
			if (other.effectiveDateObject != null) {
				return false;
			}
		} else if (!effectiveDateObject.equals(other.effectiveDateObject)) {
			return false;
		}
		if (fareClassCode == null) {
			if (other.fareClassCode != null) {
				return false;
			}
		} else if (!fareClassCode.equals(other.fareClassCode)) {
			return false;
		}
		if (fareClassInformation == null) {
			if (other.fareClassInformation != null) {
				return false;
			}
		} else if (!fareClassInformation.equals(other.fareClassInformation)) {
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
		if (geoLoc1 == null) {
			if (other.geoLoc1 != null) {
				return false;
			}
		} else if (!geoLoc1.equals(other.geoLoc1)) {
			return false;
		}
		if (geoLoc2 == null) {
			if (other.geoLoc2 != null) {
				return false;
			}
		} else if (!geoLoc2.equals(other.geoLoc2)) {
			return false;
		}
		if (geoType1 == null) {
			if (other.geoType1 != null) {
				return false;
			}
		} else if (!geoType1.equals(other.geoType1)) {
			return false;
		}
		if (geoType2 == null) {
			if (other.geoType2 != null) {
				return false;
			}
		} else if (!geoType2.equals(other.geoType2)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (mcn == null) {
			if (other.mcn != null) {
				return false;
			}
		} else if (!mcn.equals(other.mcn)) {
			return false;
		}
		if (owrt == null) {
			if (other.owrt != null) {
				return false;
			}
		} else if (!owrt.equals(other.owrt)) {
			return false;
		}
		if (pricingCategoryType == null) {
			if (other.pricingCategoryType != null) {
				return false;
			}
		} else if (!pricingCategoryType.equals(other.pricingCategoryType)) {
			return false;
		}
		if (recordBatch == null) {
			if (other.recordBatch != null) {
				return false;
			}
		} else if (!recordBatch.equals(other.recordBatch)) {
			return false;
		}
		if (recordId == null) {
			if (other.recordId != null) {
				return false;
			}
		} else if (!recordId.equals(other.recordId)) {
			return false;
		}
		if (recordIdMap == null) {
			if (other.recordIdMap != null) {
				return false;
			}
		} else if (!recordIdMap.equals(other.recordIdMap)) {
			return false;
		}
		if (recordType != other.recordType) {
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
		if (ruleTariffNo == null) {
			if (other.ruleTariffNo != null) {
				return false;
			}
		} else if (!ruleTariffNo.equals(other.ruleTariffNo)) {
			return false;
		}
		if (ruleType == null) {
			if (other.ruleType != null) {
				return false;
			}
		} else if (!ruleType.equals(other.ruleType)) {
			return false;
		}
		if (seasonType == null) {
			if (other.seasonType != null) {
				return false;
			}
		} else if (!seasonType.equals(other.seasonType)) {
			return false;
		}
		if (sequenceNo == null) {
			if (other.sequenceNo != null) {
				return false;
			}
		} else if (!sequenceNo.equals(other.sequenceNo)) {
			return false;
		}
		if (textTableNo996 == null) {
			if (other.textTableNo996 != null) {
				return false;
			}
		} else if (!textTableNo996.equals(other.textTableNo996)) {
			return false;
		}
		if (unavailableDataTag == null) {
			if (other.unavailableDataTag != null) {
				return false;
			}
		} else if (!unavailableDataTag.equals(other.unavailableDataTag)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoRecord1 [id=" + id + ", ruleType=" + ruleType + ", recordId=" + recordId + ", recordIdMap="
				+ recordIdMap + ", recordBatch=" + recordBatch + ", recordType="
				+ recordType + ", action=" + action + ", ruleTariffNo=" + ruleTariffNo + ", carrierCode=" + carrierCode
				+ ", ruleNo=" + ruleNo + ", fareClassCode=" + fareClassCode + ", mcn=" + mcn + ", sequenceNo="
				+ sequenceNo + ", geoType1=" + geoType1 + ", geoLoc1=" + geoLoc1 + ", geoType2=" + geoType2
				+ ", geoLoc2=" + geoLoc2 + ", owrt=" + owrt + ", routingNo=" + routingNo + ", footnote=" + footnote
				+ ", effectiveDateObject=" + effectiveDateObject + ", discontinueDateObject=" + discontinueDateObject
				+ ", batchCi=" + batchCi + ", batchNo=" + batchNo + ", fareType=" + fareType + ", seasonType="
				+ seasonType + ", dayOfWeekType=" + dayOfWeekType + ", pricingCategoryType=" + pricingCategoryType
				+ ", displayCategoryType=" + displayCategoryType + ", unavailableDataTag=" + unavailableDataTag
				+ ", textTableNo996=" + textTableNo996 + ", fareClassInformation=" + fareClassInformation + "]";
	}
}
