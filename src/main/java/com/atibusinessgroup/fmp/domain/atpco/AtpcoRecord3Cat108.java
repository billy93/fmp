package com.atibusinessgroup.fmp.domain.atpco;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_record_3_cat_108")
public class AtpcoRecord3Cat108 {

	@Id
	private String id;

	@Field("cat_no")
	private String cat_no;

	@Field("fare_class_type")
	private List<AtpcoRecord3Cat108FareClassType> fare_class_type = new ArrayList<>();

	@Field("record_sequence")
	private String record_sequence;

	@Field("tbl_no")
	private String tbl_no;

	@Field("rec_type")
	private String rec_type;

	@Field("reserved_1")
	private String reserved_1;

	@Field("action")
	private String action;

	@Field("rules_type")
	private String rules_type;

	@Field("record_batch")
	private String record_batch;

	@Field("pen_service_chg_appl")
	private List<AtpcoRecord3Cat108PenServiceChg> pen_service_chg_appl = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCat_no() {
		return cat_no;
	}

	public void setCat_no(String cat_no) {
		this.cat_no = cat_no;
	}

	public List<AtpcoRecord3Cat108FareClassType> getFare_class_type() {
		return fare_class_type;
	}

	public void setFare_class_type(List<AtpcoRecord3Cat108FareClassType> fare_class_type) {
		this.fare_class_type = fare_class_type;
	}

	public String getRecord_sequence() {
		return record_sequence;
	}

	public void setRecord_sequence(String record_sequence) {
		this.record_sequence = record_sequence;
	}

	public String getTbl_no() {
		return tbl_no;
	}

	public void setTbl_no(String tbl_no) {
		this.tbl_no = tbl_no;
	}

	public String getRec_type() {
		return rec_type;
	}

	public void setRec_type(String rec_type) {
		this.rec_type = rec_type;
	}

	public String getReserved_1() {
		return reserved_1;
	}

	public void setReserved_1(String reserved_1) {
		this.reserved_1 = reserved_1;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getRules_type() {
		return rules_type;
	}

	public void setRules_type(String rules_type) {
		this.rules_type = rules_type;
	}

	public String getRecord_batch() {
		return record_batch;
	}

	public void setRecord_batch(String record_batch) {
		this.record_batch = record_batch;
	}

	public List<AtpcoRecord3Cat108PenServiceChg> getPen_service_chg_appl() {
		return pen_service_chg_appl;
	}

	public void setPen_service_chg_appl(List<AtpcoRecord3Cat108PenServiceChg> pen_service_chg_appl) {
		this.pen_service_chg_appl = pen_service_chg_appl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((cat_no == null) ? 0 : cat_no.hashCode());
		result = prime * result + ((fare_class_type == null) ? 0 : fare_class_type.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pen_service_chg_appl == null) ? 0 : pen_service_chg_appl.hashCode());
		result = prime * result + ((rec_type == null) ? 0 : rec_type.hashCode());
		result = prime * result + ((record_batch == null) ? 0 : record_batch.hashCode());
		result = prime * result + ((record_sequence == null) ? 0 : record_sequence.hashCode());
		result = prime * result + ((reserved_1 == null) ? 0 : reserved_1.hashCode());
		result = prime * result + ((rules_type == null) ? 0 : rules_type.hashCode());
		result = prime * result + ((tbl_no == null) ? 0 : tbl_no.hashCode());
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
		AtpcoRecord3Cat108 other = (AtpcoRecord3Cat108) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (cat_no == null) {
			if (other.cat_no != null)
				return false;
		} else if (!cat_no.equals(other.cat_no))
			return false;
		if (fare_class_type == null) {
			if (other.fare_class_type != null)
				return false;
		} else if (!fare_class_type.equals(other.fare_class_type))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pen_service_chg_appl == null) {
			if (other.pen_service_chg_appl != null)
				return false;
		} else if (!pen_service_chg_appl.equals(other.pen_service_chg_appl))
			return false;
		if (rec_type == null) {
			if (other.rec_type != null)
				return false;
		} else if (!rec_type.equals(other.rec_type))
			return false;
		if (record_batch == null) {
			if (other.record_batch != null)
				return false;
		} else if (!record_batch.equals(other.record_batch))
			return false;
		if (record_sequence == null) {
			if (other.record_sequence != null)
				return false;
		} else if (!record_sequence.equals(other.record_sequence))
			return false;
		if (reserved_1 == null) {
			if (other.reserved_1 != null)
				return false;
		} else if (!reserved_1.equals(other.reserved_1))
			return false;
		if (rules_type == null) {
			if (other.rules_type != null)
				return false;
		} else if (!rules_type.equals(other.rules_type))
			return false;
		if (tbl_no == null) {
			if (other.tbl_no != null)
				return false;
		} else if (!tbl_no.equals(other.tbl_no))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoRecord3Cat108 [id=" + id + ", cat_no=" + cat_no + ", fare_class_type=" + fare_class_type
				+ ", record_sequence=" + record_sequence + ", tbl_no=" + tbl_no + ", rec_type=" + rec_type
				+ ", reserved_1=" + reserved_1 + ", action=" + action + ", rules_type=" + rules_type + ", record_batch="
				+ record_batch + ", pen_service_chg_appl=" + pen_service_chg_appl + "]";
	}
	
	

}
