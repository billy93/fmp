package com.atibusinessgroup.fmp.domain.atpco;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_record_3_cat_109")
public class AtpcoRecord3Cat109 {

	@Id
    private String id;
	
	@Field("cat_no")
	private String cat_no;

	@Field("record_sequence")
    private String record_sequence;

	@Field("tbl_no")
    private String tbl_no;

	@Field("rec_type")
    private String rec_type;

	@Field("action")
    private String action;

	@Field("rules_type")
    private String rules_type;

	@Field("record_batch")
    private String record_batch;

	@Field("open_jaw_sets")
    private List<AtpcoRecord3Cat109OpenJaw> open_jaw_sets = new ArrayList<>();

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

	public List<AtpcoRecord3Cat109OpenJaw> getOpen_jaw_sets() {
		return open_jaw_sets;
	}

	public void setOpen_jaw_sets(List<AtpcoRecord3Cat109OpenJaw> open_jaw_sets) {
		this.open_jaw_sets = open_jaw_sets;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((cat_no == null) ? 0 : cat_no.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((open_jaw_sets == null) ? 0 : open_jaw_sets.hashCode());
		result = prime * result + ((rec_type == null) ? 0 : rec_type.hashCode());
		result = prime * result + ((record_batch == null) ? 0 : record_batch.hashCode());
		result = prime * result + ((record_sequence == null) ? 0 : record_sequence.hashCode());
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
		AtpcoRecord3Cat109 other = (AtpcoRecord3Cat109) obj;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (open_jaw_sets == null) {
			if (other.open_jaw_sets != null)
				return false;
		} else if (!open_jaw_sets.equals(other.open_jaw_sets))
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
		return "AtpcoRecord3Cat109 [id=" + id + ", cat_no=" + cat_no + ", record_sequence=" + record_sequence
				+ ", tbl_no=" + tbl_no + ", rec_type=" + rec_type + ", action=" + action + ", rules_type=" + rules_type
				+ ", record_batch=" + record_batch + ", open_jaw_sets=" + open_jaw_sets + "]";
	}
	
	
    
} 
