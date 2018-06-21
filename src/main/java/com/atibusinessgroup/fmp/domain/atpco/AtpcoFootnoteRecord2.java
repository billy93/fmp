package com.atibusinessgroup.fmp.domain.atpco;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.atibusinessgroup.fmp.domain.dto.DataTable;

@Document(collection = "atpco_footnote_record_2")
public class AtpcoFootnoteRecord2 {

	@Id
    private String id;
	
	@Field("record_id")
    private String recordId;
	
	@Field("action")
    private String action;
	
	@Field("rec_type")
    private int recordType;
	
	@Field("batch_number")
    private int batchNumber;
	
	@Field("batch_ci")
    private String batchCi;
	
	@Field("batch_no")
    private String batchNo;
	
	@Field("mcn")
    private String mcn;
	
	@Field("fare_tar_no")
    private String fareTariffNo;
	
	@Field("cxr_code")
    private String carrierCode;
	
	@Field("geo_spec_1_type")
    private String geoType1;
	
	@Field("geo_spec_1_value")
    private String geoLoc1;
	
	@Field("geo_spec_2_type")
    private String geoType2;
	
	@Field("geo_spec_2_value")
    private String geoLoc2;
	
	@Field("fare_class")
    private String fareClass;
	
	@Field("ow_rt")
    private String owrt;
	
	@Field("rtg_no")
    private String routingNo;
	
	@Field("ftnt")
    private String footnote;
	
	@Field("jt_cxr_tbl_997")
    private String jointCarrierTable997;
	
	@Field("dates_eff")
    private Object effectiveDateObject;
	
	@Field("dates_disc")
    private Object discontinueDateObject;
	
	@Field("cat_no")
    private String catNo;
	
	@Field("seq_no")
    private String sequenceNo;
	
	@Field("no_appl")
    private String categoryNotApplicable;
	
	@Field("batch_date")
    private Object batchDateObject;
	
	@Field("data_segs")
	private List<DataTable> dataTables;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getRecordType() {
		return recordType;
	}

	public void setRecordType(int recordType) {
		this.recordType = recordType;
	}

	public int getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(int batchNumber) {
		this.batchNumber = batchNumber;
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

	public String getMcn() {
		return mcn;
	}

	public void setMcn(String mcn) {
		this.mcn = mcn;
	}

	public String getFareTariffNo() {
		return fareTariffNo;
	}

	public void setFareTariffNo(String fareTariffNo) {
		this.fareTariffNo = fareTariffNo;
	}

	public String getCarrierCode() {
		return carrierCode;
	}

	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
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

	public String getCatNo() {
		return catNo;
	}

	public void setCatNo(String catNo) {
		this.catNo = catNo;
	}

	public String getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(String sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public String getCategoryNotApplicable() {
		return categoryNotApplicable;
	}

	public void setCategoryNotApplicable(String categoryNotApplicable) {
		this.categoryNotApplicable = categoryNotApplicable;
	}

	public Object getBatchDateObject() {
		return batchDateObject;
	}

	public void setBatchDateObject(Object batchDateObject) {
		this.batchDateObject = batchDateObject;
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
		result = prime * result + ((batchDateObject == null) ? 0 : batchDateObject.hashCode());
		result = prime * result + ((batchNo == null) ? 0 : batchNo.hashCode());
		result = prime * result + batchNumber;
		result = prime * result + ((carrierCode == null) ? 0 : carrierCode.hashCode());
		result = prime * result + ((catNo == null) ? 0 : catNo.hashCode());
		result = prime * result + ((categoryNotApplicable == null) ? 0 : categoryNotApplicable.hashCode());
		result = prime * result + ((dataTables == null) ? 0 : dataTables.hashCode());
		result = prime * result + ((discontinueDateObject == null) ? 0 : discontinueDateObject.hashCode());
		result = prime * result + ((effectiveDateObject == null) ? 0 : effectiveDateObject.hashCode());
		result = prime * result + ((fareClass == null) ? 0 : fareClass.hashCode());
		result = prime * result + ((fareTariffNo == null) ? 0 : fareTariffNo.hashCode());
		result = prime * result + ((footnote == null) ? 0 : footnote.hashCode());
		result = prime * result + ((geoLoc1 == null) ? 0 : geoLoc1.hashCode());
		result = prime * result + ((geoLoc2 == null) ? 0 : geoLoc2.hashCode());
		result = prime * result + ((geoType1 == null) ? 0 : geoType1.hashCode());
		result = prime * result + ((geoType2 == null) ? 0 : geoType2.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((jointCarrierTable997 == null) ? 0 : jointCarrierTable997.hashCode());
		result = prime * result + ((mcn == null) ? 0 : mcn.hashCode());
		result = prime * result + ((owrt == null) ? 0 : owrt.hashCode());
		result = prime * result + ((recordId == null) ? 0 : recordId.hashCode());
		result = prime * result + recordType;
		result = prime * result + ((routingNo == null) ? 0 : routingNo.hashCode());
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
		AtpcoFootnoteRecord2 other = (AtpcoFootnoteRecord2) obj;
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
		if (batchDateObject == null) {
			if (other.batchDateObject != null) {
				return false;
			}
		} else if (!batchDateObject.equals(other.batchDateObject)) {
			return false;
		}
		if (batchNo == null) {
			if (other.batchNo != null) {
				return false;
			}
		} else if (!batchNo.equals(other.batchNo)) {
			return false;
		}
		if (batchNumber != other.batchNumber) {
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
		if (fareTariffNo == null) {
			if (other.fareTariffNo != null) {
				return false;
			}
		} else if (!fareTariffNo.equals(other.fareTariffNo)) {
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
		if (recordId == null) {
			if (other.recordId != null) {
				return false;
			}
		} else if (!recordId.equals(other.recordId)) {
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
		return "AtpcoFootnoteRecord2 [id=" + id + ", recordId=" + recordId + ", action=" + action + ", recordType="
				+ recordType + ", batchNumber=" + batchNumber + ", batchCi=" + batchCi + ", batchNo=" + batchNo
				+ ", mcn=" + mcn + ", fareTariffNo=" + fareTariffNo + ", carrierCode=" + carrierCode + ", geoType1="
				+ geoType1 + ", geoLoc1=" + geoLoc1 + ", geoType2=" + geoType2 + ", geoLoc2=" + geoLoc2 + ", fareClass="
				+ fareClass + ", owrt=" + owrt + ", routingNo=" + routingNo + ", footnote=" + footnote
				+ ", jointCarrierTable997=" + jointCarrierTable997 + ", effectiveDateObject=" + effectiveDateObject
				+ ", discontinueDateObject=" + discontinueDateObject + ", catNo=" + catNo + ", sequenceNo=" + sequenceNo
				+ ", categoryNotApplicable=" + categoryNotApplicable + ", batchDateObject=" + batchDateObject
				+ ", dataTables=" + dataTables + "]";
	}
}
