package com.atibusinessgroup.fmp.domain.atpco;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_record_3_cat_001")
public class AtpcoRecord3Cat01 {

	@Id
    private String id;
	
	@Field("psgr_status")
	private String psgr_status;

	@Field("record_sequence")
    private String record_sequence;

	@Field("date_tbl_no_994")
    private String date_tbl_no_994;

	@Field("geo_loc_1")
    private String geo_loc_1;

	@Field("account_code")
    private String account_code;

	@Field("age_min")
    private String age_min;

	@Field("text_tbl_no_996")
    private String text_tbl_no_996;

	@Field("record_batch")
    private String record_batch;

	@Field("psgr_type")
    private String psgr_type;

	@Field("age_max")
    private String age_max;

	@Field("appl")
    private String appl;

	@Field("psgr_occ_last")
    private String psgr_occ_last;

	@Field("cat_no")
    private String cat_no;

	@Field("id_tag")
    private String id_tag;
	
	@Field("psgr_occ_first")
    private String psgr_occ_first;

	@Field("rec_type")
    private String rec_type;

	@Field("tbl_no")
    private String tbl_no;

	@Field("unavail")
    private String unavail;

	@Field("rules_type")
    private String rules_type;

	@Field("action")
    private String action;

	@Field("geo_type_1")
    private String geo_type_1;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPsgr_status() {
		return psgr_status;
	}

	public void setPsgr_status(String psgr_status) {
		this.psgr_status = psgr_status;
	}

	public String getRecord_sequence() {
		return record_sequence;
	}

	public void setRecord_sequence(String record_sequence) {
		this.record_sequence = record_sequence;
	}

	public String getDate_tbl_no_994() {
		return date_tbl_no_994;
	}

	public void setDate_tbl_no_994(String date_tbl_no_994) {
		this.date_tbl_no_994 = date_tbl_no_994;
	}

	public String getGeo_loc_1() {
		return geo_loc_1;
	}

	public void setGeo_loc_1(String geo_loc_1) {
		this.geo_loc_1 = geo_loc_1;
	}

	public String getAccount_code() {
		return account_code;
	}

	public void setAccount_code(String account_code) {
		this.account_code = account_code;
	}

	public String getAge_min() {
		return age_min;
	}

	public void setAge_min(String age_min) {
		this.age_min = age_min;
	}

	public String getText_tbl_no_996() {
		return text_tbl_no_996;
	}

	public void setText_tbl_no_996(String text_tbl_no_996) {
		this.text_tbl_no_996 = text_tbl_no_996;
	}

	public String getRecord_batch() {
		return record_batch;
	}

	public void setRecord_batch(String record_batch) {
		this.record_batch = record_batch;
	}

	public String getPsgr_type() {
		return psgr_type;
	}

	public void setPsgr_type(String psgr_type) {
		this.psgr_type = psgr_type;
	}

	public String getAge_max() {
		return age_max;
	}

	public void setAge_max(String age_max) {
		this.age_max = age_max;
	}

	public String getAppl() {
		return appl;
	}

	public void setAppl(String appl) {
		this.appl = appl;
	}

	public String getPsgr_occ_last() {
		return psgr_occ_last;
	}

	public void setPsgr_occ_last(String psgr_occ_last) {
		this.psgr_occ_last = psgr_occ_last;
	}

	public String getCat_no() {
		return cat_no;
	}

	public void setCat_no(String cat_no) {
		this.cat_no = cat_no;
	}

	public String getId_tag() {
		return id_tag;
	}

	public void setId_tag(String id_tag) {
		this.id_tag = id_tag;
	}

	public String getPsgr_occ_first() {
		return psgr_occ_first;
	}

	public void setPsgr_occ_first(String psgr_occ_first) {
		this.psgr_occ_first = psgr_occ_first;
	}

	public String getRec_type() {
		return rec_type;
	}

	public void setRec_type(String rec_type) {
		this.rec_type = rec_type;
	}

	public String getTbl_no() {
		return tbl_no;
	}

	public void setTbl_no(String tbl_no) {
		this.tbl_no = tbl_no;
	}

	public String getUnavail() {
		return unavail;
	}

	public void setUnavail(String unavail) {
		this.unavail = unavail;
	}

	public String getRules_type() {
		return rules_type;
	}

	public void setRules_type(String rules_type) {
		this.rules_type = rules_type;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getGeo_type_1() {
		return geo_type_1;
	}

	public void setGeo_type_1(String geo_type_1) {
		this.geo_type_1 = geo_type_1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account_code == null) ? 0 : account_code.hashCode());
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((age_max == null) ? 0 : age_max.hashCode());
		result = prime * result + ((age_min == null) ? 0 : age_min.hashCode());
		result = prime * result + ((appl == null) ? 0 : appl.hashCode());
		result = prime * result + ((cat_no == null) ? 0 : cat_no.hashCode());
		result = prime * result + ((date_tbl_no_994 == null) ? 0 : date_tbl_no_994.hashCode());
		result = prime * result + ((geo_loc_1 == null) ? 0 : geo_loc_1.hashCode());
		result = prime * result + ((geo_type_1 == null) ? 0 : geo_type_1.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_tag == null) ? 0 : id_tag.hashCode());
		result = prime * result + ((psgr_occ_first == null) ? 0 : psgr_occ_first.hashCode());
		result = prime * result + ((psgr_occ_last == null) ? 0 : psgr_occ_last.hashCode());
		result = prime * result + ((psgr_status == null) ? 0 : psgr_status.hashCode());
		result = prime * result + ((psgr_type == null) ? 0 : psgr_type.hashCode());
		result = prime * result + ((rec_type == null) ? 0 : rec_type.hashCode());
		result = prime * result + ((record_batch == null) ? 0 : record_batch.hashCode());
		result = prime * result + ((record_sequence == null) ? 0 : record_sequence.hashCode());
		result = prime * result + ((rules_type == null) ? 0 : rules_type.hashCode());
		result = prime * result + ((tbl_no == null) ? 0 : tbl_no.hashCode());
		result = prime * result + ((text_tbl_no_996 == null) ? 0 : text_tbl_no_996.hashCode());
		result = prime * result + ((unavail == null) ? 0 : unavail.hashCode());
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
		AtpcoRecord3Cat01 other = (AtpcoRecord3Cat01) obj;
		if (account_code == null) {
			if (other.account_code != null)
				return false;
		} else if (!account_code.equals(other.account_code))
			return false;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (age_max == null) {
			if (other.age_max != null)
				return false;
		} else if (!age_max.equals(other.age_max))
			return false;
		if (age_min == null) {
			if (other.age_min != null)
				return false;
		} else if (!age_min.equals(other.age_min))
			return false;
		if (appl == null) {
			if (other.appl != null)
				return false;
		} else if (!appl.equals(other.appl))
			return false;
		if (cat_no == null) {
			if (other.cat_no != null)
				return false;
		} else if (!cat_no.equals(other.cat_no))
			return false;
		if (date_tbl_no_994 == null) {
			if (other.date_tbl_no_994 != null)
				return false;
		} else if (!date_tbl_no_994.equals(other.date_tbl_no_994))
			return false;
		if (geo_loc_1 == null) {
			if (other.geo_loc_1 != null)
				return false;
		} else if (!geo_loc_1.equals(other.geo_loc_1))
			return false;
		if (geo_type_1 == null) {
			if (other.geo_type_1 != null)
				return false;
		} else if (!geo_type_1.equals(other.geo_type_1))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_tag == null) {
			if (other.id_tag != null)
				return false;
		} else if (!id_tag.equals(other.id_tag))
			return false;
		if (psgr_occ_first == null) {
			if (other.psgr_occ_first != null)
				return false;
		} else if (!psgr_occ_first.equals(other.psgr_occ_first))
			return false;
		if (psgr_occ_last == null) {
			if (other.psgr_occ_last != null)
				return false;
		} else if (!psgr_occ_last.equals(other.psgr_occ_last))
			return false;
		if (psgr_status == null) {
			if (other.psgr_status != null)
				return false;
		} else if (!psgr_status.equals(other.psgr_status))
			return false;
		if (psgr_type == null) {
			if (other.psgr_type != null)
				return false;
		} else if (!psgr_type.equals(other.psgr_type))
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
		if (text_tbl_no_996 == null) {
			if (other.text_tbl_no_996 != null)
				return false;
		} else if (!text_tbl_no_996.equals(other.text_tbl_no_996))
			return false;
		if (unavail == null) {
			if (other.unavail != null)
				return false;
		} else if (!unavail.equals(other.unavail))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoRecord3Cat01 [id=" + id + ", psgr_status=" + psgr_status + ", record_sequence=" + record_sequence
				+ ", date_tbl_no_994=" + date_tbl_no_994 + ", geo_loc_1=" + geo_loc_1 + ", account_code=" + account_code
				+ ", age_min=" + age_min + ", text_tbl_no_996=" + text_tbl_no_996 + ", record_batch=" + record_batch
				+ ", psgr_type=" + psgr_type + ", age_max=" + age_max + ", appl=" + appl + ", psgr_occ_last="
				+ psgr_occ_last + ", cat_no=" + cat_no + ", id_tag=" + id_tag + ", psgr_occ_first=" + psgr_occ_first
				+ ", rec_type=" + rec_type + ", tbl_no=" + tbl_no + ", unavail=" + unavail + ", rules_type="
				+ rules_type + ", action=" + action + ", geo_type_1=" + geo_type_1 + "]";
	}
	
	

}
