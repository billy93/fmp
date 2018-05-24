package com.atibusinessgroup.fmp.domain.atpco;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.atibusinessgroup.fmp.domain.dto.DataTable;

@Document(collection = "atpco_record_2")
public class AtpcoRecord2 {

	@Id
    private String id;
	
	@Field("record_batch")
    private String recordBatch;
	
	@Field("record_sequence")
    private int recordSequence;
	
	@Field("record_type")
    private int recordType;
	
	@Field("action")
    private String action;
	
	@Field("rule_tar_no")
    private String ruleTariffNo;
	
	@Field("cxr_code")
    private String carrierCode;
	
	@Field("rule_no")
    private String ruleNo;
	
	@Field("cat_no")
    private String catNo;
	
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
	
	@Field("fare_class")
    private String fareClass;
	
	@Field("fare_type")
    private String fareType;
	
	@Field("season_type")
    private String seasonType;
	
	@Field("day_of_week_type")
    private String dayOfWeekType;
	
	@Field("owrt")
    private String owrt;
	
	@Field("rtg_no")
    private String routingNo;
	
	@Field("ft_nt")
    private String footnote;
	
	@Field("jt_cxr_tbl_no_997")
    private String jointCarrierTable997;
	
	@Field("dates_eff")
    private Object effectiveDateObject;
	
	@Field("dates_disc")
    private Object discontinueDateObject;
	
	@Field("batch_ci")
    private String batchCi;
	
	@Field("batch_no")
    private String batchNo;
	
	@Field("no_appl")
    private String categoryNotApplicable;
	
	@Field("gen_rule_src_tar")
    private String generalRuleSourceTariff;
	
	@Field("gen_rule_rule_no")
    private String generalRuleNo;
	
	@Field("gen_appl")
    private String generalRuleApplication;
	
	@Field("data_table_string")
	private List<DataTable> dataTables;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRecordBatch() {
		return recordBatch;
	}

	public void setRecordBatch(String recordBatch) {
		this.recordBatch = recordBatch;
	}

	public int getRecordSequence() {
		return recordSequence;
	}

	public void setRecordSequence(int recordSequence) {
		this.recordSequence = recordSequence;
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

	public String getCatNo() {
		return catNo;
	}

	public void setCatNo(String catNo) {
		this.catNo = catNo;
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

	public String getJointCarrierTable997() {
		return jointCarrierTable997;
	}

	public void setJointCarrierTable997(String jointCarrierTable997) {
		this.jointCarrierTable997 = jointCarrierTable997;
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

	public String getCategoryNotApplicable() {
		return categoryNotApplicable;
	}

	public void setCategoryNotApplicable(String categoryNotApplicable) {
		this.categoryNotApplicable = categoryNotApplicable;
	}

	public String getGeneralRuleSourceTariff() {
		return generalRuleSourceTariff;
	}

	public void setGeneralRuleSourceTariff(String generalRuleSourceTariff) {
		this.generalRuleSourceTariff = generalRuleSourceTariff;
	}

	public String getGeneralRuleNo() {
		return generalRuleNo;
	}

	public void setGeneralRuleNo(String generalRuleNo) {
		this.generalRuleNo = generalRuleNo;
	}

	public String getGeneralRuleApplication() {
		return generalRuleApplication;
	}

	public void setGeneralRuleApplication(String generalRuleApplication) {
		this.generalRuleApplication = generalRuleApplication;
	}

	public List<DataTable> getDataTables() {
		return dataTables;
	}

	public void setDataTables(List<DataTable> dataTables) {
		this.dataTables = dataTables;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((batchCi == null) ? 0 : batchCi.hashCode());
		result = prime * result + ((batchNo == null) ? 0 : batchNo.hashCode());
		result = prime * result + ((carrierCode == null) ? 0 : carrierCode.hashCode());
		result = prime * result + ((catNo == null) ? 0 : catNo.hashCode());
		result = prime * result + ((categoryNotApplicable == null) ? 0 : categoryNotApplicable.hashCode());
		result = prime * result + ((dataTables == null) ? 0 : dataTables.hashCode());
		result = prime * result + ((dayOfWeekType == null) ? 0 : dayOfWeekType.hashCode());
		result = prime * result + ((discontinueDateObject == null) ? 0 : discontinueDateObject.hashCode());
		result = prime * result + ((effectiveDateObject == null) ? 0 : effectiveDateObject.hashCode());
		result = prime * result + ((fareClass == null) ? 0 : fareClass.hashCode());
		result = prime * result + ((fareType == null) ? 0 : fareType.hashCode());
		result = prime * result + ((footnote == null) ? 0 : footnote.hashCode());
		result = prime * result + ((generalRuleApplication == null) ? 0 : generalRuleApplication.hashCode());
		result = prime * result + ((generalRuleNo == null) ? 0 : generalRuleNo.hashCode());
		result = prime * result + ((generalRuleSourceTariff == null) ? 0 : generalRuleSourceTariff.hashCode());
		result = prime * result + ((geoLoc1 == null) ? 0 : geoLoc1.hashCode());
		result = prime * result + ((geoLoc2 == null) ? 0 : geoLoc2.hashCode());
		result = prime * result + ((geoType1 == null) ? 0 : geoType1.hashCode());
		result = prime * result + ((geoType2 == null) ? 0 : geoType2.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((jointCarrierTable997 == null) ? 0 : jointCarrierTable997.hashCode());
		result = prime * result + ((mcn == null) ? 0 : mcn.hashCode());
		result = prime * result + ((owrt == null) ? 0 : owrt.hashCode());
		result = prime * result + ((recordBatch == null) ? 0 : recordBatch.hashCode());
		result = prime * result + recordSequence;
		result = prime * result + recordType;
		result = prime * result + ((routingNo == null) ? 0 : routingNo.hashCode());
		result = prime * result + ((ruleNo == null) ? 0 : ruleNo.hashCode());
		result = prime * result + ((ruleTariffNo == null) ? 0 : ruleTariffNo.hashCode());
		result = prime * result + ((seasonType == null) ? 0 : seasonType.hashCode());
		result = prime * result + ((sequenceNo == null) ? 0 : sequenceNo.hashCode());
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
		AtpcoRecord2 other = (AtpcoRecord2) obj;
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
		if (catNo == null) {
			if (other.catNo != null) {
				return false;
			}
		} else if (!catNo.equals(other.catNo)) {
			return false;
		}
		if (categoryNotApplicable == null) {
			if (other.categoryNotApplicable != null) {
				return false;
			}
		} else if (!categoryNotApplicable.equals(other.categoryNotApplicable)) {
			return false;
		}
		if (dataTables == null) {
			if (other.dataTables != null) {
				return false;
			}
		} else if (!dataTables.equals(other.dataTables)) {
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
		if (effectiveDateObject == null) {
			if (other.effectiveDateObject != null) {
				return false;
			}
		} else if (!effectiveDateObject.equals(other.effectiveDateObject)) {
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
		if (generalRuleApplication == null) {
			if (other.generalRuleApplication != null) {
				return false;
			}
		} else if (!generalRuleApplication.equals(other.generalRuleApplication)) {
			return false;
		}
		if (generalRuleNo == null) {
			if (other.generalRuleNo != null) {
				return false;
			}
		} else if (!generalRuleNo.equals(other.generalRuleNo)) {
			return false;
		}
		if (generalRuleSourceTariff == null) {
			if (other.generalRuleSourceTariff != null) {
				return false;
			}
		} else if (!generalRuleSourceTariff.equals(other.generalRuleSourceTariff)) {
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
		if (jointCarrierTable997 == null) {
			if (other.jointCarrierTable997 != null) {
				return false;
			}
		} else if (!jointCarrierTable997.equals(other.jointCarrierTable997)) {
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
		if (recordBatch == null) {
			if (other.recordBatch != null) {
				return false;
			}
		} else if (!recordBatch.equals(other.recordBatch)) {
			return false;
		}
		if (recordSequence != other.recordSequence) {
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
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoRecord2 [id=" + id + ", recordBatch=" + recordBatch + ", recordSequence=" + recordSequence
				+ ", recordType=" + recordType + ", action=" + action + ", ruleTariffNo=" + ruleTariffNo
				+ ", carrierCode=" + carrierCode + ", ruleNo=" + ruleNo + ", catNo=" + catNo + ", mcn=" + mcn
				+ ", sequenceNo=" + sequenceNo + ", geoType1=" + geoType1 + ", geoLoc1=" + geoLoc1 + ", geoType2="
				+ geoType2 + ", geoLoc2=" + geoLoc2 + ", fareClass=" + fareClass + ", fareType=" + fareType
				+ ", seasonType=" + seasonType + ", dayOfWeekType=" + dayOfWeekType + ", owrt=" + owrt + ", routingNo="
				+ routingNo + ", footnote=" + footnote + ", jointCarrierTable997=" + jointCarrierTable997
				+ ", effectiveDateObject=" + effectiveDateObject + ", discontinueDateObject=" + discontinueDateObject
				+ ", batchCi=" + batchCi + ", batchNo=" + batchNo + ", categoryNotApplicable=" + categoryNotApplicable
				+ ", generalRuleSourceTariff=" + generalRuleSourceTariff + ", generalRuleNo=" + generalRuleNo
				+ ", generalRuleApplication=" + generalRuleApplication + ", dataTables=" + dataTables + "]";
	}
}
