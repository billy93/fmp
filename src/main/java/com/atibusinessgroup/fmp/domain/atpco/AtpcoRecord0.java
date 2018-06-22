package com.atibusinessgroup.fmp.domain.atpco;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.atibusinessgroup.fmp.domain.dto.GeneralRuleApplication;

@Document(collection = "atpco_record_0")
public class AtpcoRecord0 {
	
	@Id
    private String id;
	
	@Field("dates_eff")
	private Object effectiveDate;

	@Field("dates_disc")
	private Object discontinueDate;
	
	@Field("record_sequence")
	private int recordSequence;
	
	@Field("record_batch")
	private int recordBatch;
	
	@Field("cxr_code")
	private String carrierCode;
	
	@Field("rec_type")
	private String recordType;
	
	@Field("rule_no")
	private String ruleNo;
	
	@Field("batch_ci")
	private String batchCi;
	
	@Field("batch_no")
	private String batchNo;
	
	@Field("filler_1")
	private String filler1;
	
	@Field("filler_2")
	private String filler2;
	
	@Field("record_id")
	private String recordId;
	
	@Field("rule_tar")
	private String ruleTariff;
	
	@Field("action")
	private String action;
	
	@Field("rules_type")
	private String ruleType;
	
	@Field("general_rule_application")
    private List<GeneralRuleApplication> generalRuleApplications = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Object getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Object effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Object getDiscontinueDate() {
		return discontinueDate;
	}

	public void setDiscontinueDate(Object discontinueDate) {
		this.discontinueDate = discontinueDate;
	}

	public int getRecordSequence() {
		return recordSequence;
	}

	public void setRecordSequence(int recordSequence) {
		this.recordSequence = recordSequence;
	}

	public int getRecordBatch() {
		return recordBatch;
	}

	public void setRecordBatch(int recordBatch) {
		this.recordBatch = recordBatch;
	}

	public String getCarrierCode() {
		return carrierCode;
	}

	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getRuleNo() {
		return ruleNo;
	}

	public void setRuleNo(String ruleNo) {
		this.ruleNo = ruleNo;
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

	public String getFiller1() {
		return filler1;
	}

	public void setFiller1(String filler1) {
		this.filler1 = filler1;
	}

	public String getFiller2() {
		return filler2;
	}

	public void setFiller2(String filler2) {
		this.filler2 = filler2;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getRuleTariff() {
		return ruleTariff;
	}

	public void setRuleTariff(String ruleTariff) {
		this.ruleTariff = ruleTariff;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	public List<GeneralRuleApplication> getGeneralRuleApplications() {
		return generalRuleApplications;
	}

	public void setGeneralRuleApplications(List<GeneralRuleApplication> generalRuleApplications) {
		this.generalRuleApplications = generalRuleApplications;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((batchCi == null) ? 0 : batchCi.hashCode());
		result = prime * result + ((batchNo == null) ? 0 : batchNo.hashCode());
		result = prime * result + ((carrierCode == null) ? 0 : carrierCode.hashCode());
		result = prime * result + ((discontinueDate == null) ? 0 : discontinueDate.hashCode());
		result = prime * result + ((effectiveDate == null) ? 0 : effectiveDate.hashCode());
		result = prime * result + ((filler1 == null) ? 0 : filler1.hashCode());
		result = prime * result + ((filler2 == null) ? 0 : filler2.hashCode());
		result = prime * result + ((generalRuleApplications == null) ? 0 : generalRuleApplications.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + recordBatch;
		result = prime * result + ((recordId == null) ? 0 : recordId.hashCode());
		result = prime * result + recordSequence;
		result = prime * result + ((recordType == null) ? 0 : recordType.hashCode());
		result = prime * result + ((ruleNo == null) ? 0 : ruleNo.hashCode());
		result = prime * result + ((ruleTariff == null) ? 0 : ruleTariff.hashCode());
		result = prime * result + ((ruleType == null) ? 0 : ruleType.hashCode());
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
		AtpcoRecord0 other = (AtpcoRecord0) obj;
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
		if (discontinueDate == null) {
			if (other.discontinueDate != null) {
				return false;
			}
		} else if (!discontinueDate.equals(other.discontinueDate)) {
			return false;
		}
		if (effectiveDate == null) {
			if (other.effectiveDate != null) {
				return false;
			}
		} else if (!effectiveDate.equals(other.effectiveDate)) {
			return false;
		}
		if (filler1 == null) {
			if (other.filler1 != null) {
				return false;
			}
		} else if (!filler1.equals(other.filler1)) {
			return false;
		}
		if (filler2 == null) {
			if (other.filler2 != null) {
				return false;
			}
		} else if (!filler2.equals(other.filler2)) {
			return false;
		}
		if (generalRuleApplications == null) {
			if (other.generalRuleApplications != null) {
				return false;
			}
		} else if (!generalRuleApplications.equals(other.generalRuleApplications)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (recordBatch != other.recordBatch) {
			return false;
		}
		if (recordId == null) {
			if (other.recordId != null) {
				return false;
			}
		} else if (!recordId.equals(other.recordId)) {
			return false;
		}
		if (recordSequence != other.recordSequence) {
			return false;
		}
		if (recordType == null) {
			if (other.recordType != null) {
				return false;
			}
		} else if (!recordType.equals(other.recordType)) {
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
		if (ruleType == null) {
			if (other.ruleType != null) {
				return false;
			}
		} else if (!ruleType.equals(other.ruleType)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoRecord0 [id=" + id + ", effectiveDate=" + effectiveDate + ", discontinueDate=" + discontinueDate
				+ ", recordSequence=" + recordSequence + ", recordBatch=" + recordBatch + ", carrierCode=" + carrierCode
				+ ", recordType=" + recordType + ", ruleNo=" + ruleNo + ", batchCi=" + batchCi + ", batchNo=" + batchNo
				+ ", filler1=" + filler1 + ", filler2=" + filler2 + ", recordId=" + recordId + ", ruleTariff="
				+ ruleTariff + ", action=" + action + ", ruleType=" + ruleType + ", generalRuleApplications="
				+ generalRuleApplications + "]";
	}
}
