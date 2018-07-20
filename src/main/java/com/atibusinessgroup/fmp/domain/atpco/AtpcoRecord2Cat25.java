package com.atibusinessgroup.fmp.domain.atpco;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.atibusinessgroup.fmp.domain.dto.DataTable;

@Document(collection = "atpco_record_2_25")
public class AtpcoRecord2Cat25 {

	@Id
    private String id;
	
	@Field("reserved_2")
	private String reserved2;
	
	@Field("geo_zone_tbl_978_2")
	private String geoZoneTable9782;
	
	@Field("record_id_map")
	private String recordIdMap;
	
	@Field("batch_ci")
	private String batchCi;
	
	@Field("action")
	private String action;
	
	@Field("rec_type")
	private String recType;
	
	@Field("batch_no")
	private String batchNo;
	
	@Field("rule_tar_no")
	private String ruleTarNo;
	
	@Field("geo_value_2")
	private String geoValue2;
	
	@Field("reserved_1")
	private String reserved1;
	
	@Field("data_segs")
	private List<DataTable> dataTables;
	
	@Field("batch_date")
	private Object batchDate;
	
	@Field("jt_cxr_tbl_no_997")
	private String jointCarrierTable997;
	
	@Field("no_appl")
	private String categoryNotApplicable;
	
	@Field("geo_type_1")
	private String geoType1;
	
	@Field("fare_appl")
	private String fareAppl;
	
	@Field("geo_value_1")
	private String geoValue1;
	
	@Field("dates_eff")
	private Object datesEff;
	
	@Field("record_id")
	private String recordId;
	
	@Field("cxr_code")
	private String cxrCode;
	
	@Field("dates_disc")
	private Object datesDisc;
	
	@Field("seq_no")
	private String seqNo;
	
	@Field("geo_zone_tbl_978_1")
	private String geoZoneTable9781;
	
	@Field("cat_no")
	private String catNo;
	
	@Field("mcn")
	private String mcn;
	
	@Field("batch_number")
	private int batchNumber;
	
	@Field("rule_no")
	private String ruleNo;
	
	@Field("geo_type_2")
	private String geoType2;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReserved2() {
		return reserved2;
	}

	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}

	public String getGeoZoneTable9782() {
		return geoZoneTable9782;
	}

	public void setGeoZoneTable9782(String geoZoneTable9782) {
		this.geoZoneTable9782 = geoZoneTable9782;
	}

	public String getRecordIdMap() {
		return recordIdMap;
	}

	public void setRecordIdMap(String recordIdMap) {
		this.recordIdMap = recordIdMap;
	}

	public String getBatchCi() {
		return batchCi;
	}

	public void setBatchCi(String batchCi) {
		this.batchCi = batchCi;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getRecType() {
		return recType;
	}

	public void setRecType(String recType) {
		this.recType = recType;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getRuleTarNo() {
		return ruleTarNo;
	}

	public void setRuleTarNo(String ruleTarNo) {
		this.ruleTarNo = ruleTarNo;
	}

	public String getGeoValue2() {
		return geoValue2;
	}

	public void setGeoValue2(String geoValue2) {
		this.geoValue2 = geoValue2;
	}

	public String getReserved1() {
		return reserved1;
	}

	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

	public List<DataTable> getDataTables() {
		return dataTables;
	}

	public void setDataTables(List<DataTable> dataTables) {
		this.dataTables = dataTables;
	}

	public Object getBatchDate() {
		return batchDate;
	}

	public void setBatchDate(Object batchDate) {
		this.batchDate = batchDate;
	}

	public String getJointCarrierTable997() {
		return jointCarrierTable997;
	}

	public void setJointCarrierTable997(String jointCarrierTable997) {
		this.jointCarrierTable997 = jointCarrierTable997;
	}

	public String getCategoryNotApplicable() {
		return categoryNotApplicable;
	}

	public void setCategoryNotApplicable(String categoryNotApplicable) {
		this.categoryNotApplicable = categoryNotApplicable;
	}

	public String getGeoType1() {
		return geoType1;
	}

	public void setGeoType1(String geoType1) {
		this.geoType1 = geoType1;
	}

	public String getFareAppl() {
		return fareAppl;
	}

	public void setFareAppl(String fareAppl) {
		this.fareAppl = fareAppl;
	}

	public String getGeoValue1() {
		return geoValue1;
	}

	public void setGeoValue1(String geoValue1) {
		this.geoValue1 = geoValue1;
	}

	public Object getDatesEff() {
		return datesEff;
	}

	public void setDatesEff(Object datesEff) {
		this.datesEff = datesEff;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getCxrCode() {
		return cxrCode;
	}

	public void setCxrCode(String cxrCode) {
		this.cxrCode = cxrCode;
	}

	public Object getDatesDisc() {
		return datesDisc;
	}

	public void setDatesDisc(Object datesDisc) {
		this.datesDisc = datesDisc;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getGeoZoneTable9781() {
		return geoZoneTable9781;
	}

	public void setGeoZoneTable9781(String geoZoneTable9781) {
		this.geoZoneTable9781 = geoZoneTable9781;
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

	public int getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(int batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getRuleNo() {
		return ruleNo;
	}

	public void setRuleNo(String ruleNo) {
		this.ruleNo = ruleNo;
	}

	public String getGeoType2() {
		return geoType2;
	}

	public void setGeoType2(String geoType2) {
		this.geoType2 = geoType2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((batchCi == null) ? 0 : batchCi.hashCode());
		result = prime * result + ((batchDate == null) ? 0 : batchDate.hashCode());
		result = prime * result + ((batchNo == null) ? 0 : batchNo.hashCode());
		result = prime * result + batchNumber;
		result = prime * result + ((catNo == null) ? 0 : catNo.hashCode());
		result = prime * result + ((categoryNotApplicable == null) ? 0 : categoryNotApplicable.hashCode());
		result = prime * result + ((cxrCode == null) ? 0 : cxrCode.hashCode());
		result = prime * result + ((dataTables == null) ? 0 : dataTables.hashCode());
		result = prime * result + ((datesDisc == null) ? 0 : datesDisc.hashCode());
		result = prime * result + ((datesEff == null) ? 0 : datesEff.hashCode());
		result = prime * result + ((fareAppl == null) ? 0 : fareAppl.hashCode());
		result = prime * result + ((geoType1 == null) ? 0 : geoType1.hashCode());
		result = prime * result + ((geoType2 == null) ? 0 : geoType2.hashCode());
		result = prime * result + ((geoValue1 == null) ? 0 : geoValue1.hashCode());
		result = prime * result + ((geoValue2 == null) ? 0 : geoValue2.hashCode());
		result = prime * result + ((geoZoneTable9781 == null) ? 0 : geoZoneTable9781.hashCode());
		result = prime * result + ((geoZoneTable9782 == null) ? 0 : geoZoneTable9782.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((jointCarrierTable997 == null) ? 0 : jointCarrierTable997.hashCode());
		result = prime * result + ((mcn == null) ? 0 : mcn.hashCode());
		result = prime * result + ((recType == null) ? 0 : recType.hashCode());
		result = prime * result + ((recordId == null) ? 0 : recordId.hashCode());
		result = prime * result + ((recordIdMap == null) ? 0 : recordIdMap.hashCode());
		result = prime * result + ((reserved1 == null) ? 0 : reserved1.hashCode());
		result = prime * result + ((reserved2 == null) ? 0 : reserved2.hashCode());
		result = prime * result + ((ruleNo == null) ? 0 : ruleNo.hashCode());
		result = prime * result + ((ruleTarNo == null) ? 0 : ruleTarNo.hashCode());
		result = prime * result + ((seqNo == null) ? 0 : seqNo.hashCode());
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
		AtpcoRecord2Cat25 other = (AtpcoRecord2Cat25) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (batchCi == null) {
			if (other.batchCi != null)
				return false;
		} else if (!batchCi.equals(other.batchCi))
			return false;
		if (batchDate == null) {
			if (other.batchDate != null)
				return false;
		} else if (!batchDate.equals(other.batchDate))
			return false;
		if (batchNo == null) {
			if (other.batchNo != null)
				return false;
		} else if (!batchNo.equals(other.batchNo))
			return false;
		if (batchNumber != other.batchNumber)
			return false;
		if (catNo == null) {
			if (other.catNo != null)
				return false;
		} else if (!catNo.equals(other.catNo))
			return false;
		if (categoryNotApplicable == null) {
			if (other.categoryNotApplicable != null)
				return false;
		} else if (!categoryNotApplicable.equals(other.categoryNotApplicable))
			return false;
		if (cxrCode == null) {
			if (other.cxrCode != null)
				return false;
		} else if (!cxrCode.equals(other.cxrCode))
			return false;
		if (dataTables == null) {
			if (other.dataTables != null)
				return false;
		} else if (!dataTables.equals(other.dataTables))
			return false;
		if (datesDisc == null) {
			if (other.datesDisc != null)
				return false;
		} else if (!datesDisc.equals(other.datesDisc))
			return false;
		if (datesEff == null) {
			if (other.datesEff != null)
				return false;
		} else if (!datesEff.equals(other.datesEff))
			return false;
		if (fareAppl == null) {
			if (other.fareAppl != null)
				return false;
		} else if (!fareAppl.equals(other.fareAppl))
			return false;
		if (geoType1 == null) {
			if (other.geoType1 != null)
				return false;
		} else if (!geoType1.equals(other.geoType1))
			return false;
		if (geoType2 == null) {
			if (other.geoType2 != null)
				return false;
		} else if (!geoType2.equals(other.geoType2))
			return false;
		if (geoValue1 == null) {
			if (other.geoValue1 != null)
				return false;
		} else if (!geoValue1.equals(other.geoValue1))
			return false;
		if (geoValue2 == null) {
			if (other.geoValue2 != null)
				return false;
		} else if (!geoValue2.equals(other.geoValue2))
			return false;
		if (geoZoneTable9781 == null) {
			if (other.geoZoneTable9781 != null)
				return false;
		} else if (!geoZoneTable9781.equals(other.geoZoneTable9781))
			return false;
		if (geoZoneTable9782 == null) {
			if (other.geoZoneTable9782 != null)
				return false;
		} else if (!geoZoneTable9782.equals(other.geoZoneTable9782))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (jointCarrierTable997 == null) {
			if (other.jointCarrierTable997 != null)
				return false;
		} else if (!jointCarrierTable997.equals(other.jointCarrierTable997))
			return false;
		if (mcn == null) {
			if (other.mcn != null)
				return false;
		} else if (!mcn.equals(other.mcn))
			return false;
		if (recType == null) {
			if (other.recType != null)
				return false;
		} else if (!recType.equals(other.recType))
			return false;
		if (recordId == null) {
			if (other.recordId != null)
				return false;
		} else if (!recordId.equals(other.recordId))
			return false;
		if (recordIdMap == null) {
			if (other.recordIdMap != null)
				return false;
		} else if (!recordIdMap.equals(other.recordIdMap))
			return false;
		if (reserved1 == null) {
			if (other.reserved1 != null)
				return false;
		} else if (!reserved1.equals(other.reserved1))
			return false;
		if (reserved2 == null) {
			if (other.reserved2 != null)
				return false;
		} else if (!reserved2.equals(other.reserved2))
			return false;
		if (ruleNo == null) {
			if (other.ruleNo != null)
				return false;
		} else if (!ruleNo.equals(other.ruleNo))
			return false;
		if (ruleTarNo == null) {
			if (other.ruleTarNo != null)
				return false;
		} else if (!ruleTarNo.equals(other.ruleTarNo))
			return false;
		if (seqNo == null) {
			if (other.seqNo != null)
				return false;
		} else if (!seqNo.equals(other.seqNo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoRecord2Cat25 [id=" + id + ", reserved2=" + reserved2 + ", geoZoneTable9782=" + geoZoneTable9782
				+ ", recordIdMap=" + recordIdMap + ", batchCi=" + batchCi + ", action=" + action + ", recType="
				+ recType + ", batchNo=" + batchNo + ", ruleTarNo=" + ruleTarNo + ", geoValue2=" + geoValue2
				+ ", reserved1=" + reserved1 + ", dataTables=" + dataTables + ", batchDate=" + batchDate
				+ ", jointCarrierTable997=" + jointCarrierTable997 + ", categoryNotApplicable=" + categoryNotApplicable + ", geoType1=" + geoType1 + ", fareAppl="
				+ fareAppl + ", geoValue1=" + geoValue1 + ", datesEff=" + datesEff + ", recordId=" + recordId
				+ ", cxrCode=" + cxrCode + ", datesDisc=" + datesDisc + ", seqNo=" + seqNo + ", geoZoneTable9781="
				+ geoZoneTable9781 + ", catNo=" + catNo + ", mcn=" + mcn + ", batchNumber=" + batchNumber + ", ruleNo="
				+ ruleNo + ", geoType2=" + geoType2 + "]";
	}
	
	
}
