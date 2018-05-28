package com.atibusinessgroup.fmp.domain.atpco;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_record_3_cat_013")
public class AtpcoRecord3Cat13 {

	@Id
    private String id;
	
	@Field("acc_trvl_cmpt")
	private String acc_trvl_cmpt;

	@Field("record_sequence")
    private String record_sequence;

	@Field("psgr_id")
    private String psgr_id;

	@Field("fare_class_rbd")
    private List<AtpcoRecord3Cat13FareClassRbd> fare_class_rbd = new ArrayList<>();

	@Field("acc_trvl_sect_out")
    private String acc_trvl_sect_out;

	@Field("psgr_type")
    private String psgr_type;

	@Field("psgr_age_max")
    private String psgr_age_max;

	@Field("acc_trvl_sect_one")
    private String acc_trvl_sect_one;

	@Field("tbl_no")
    private String tbl_no;
	
	@Field("unavail")
    private String unavail;

	@Field("action")
    private String action;

	@Field("rules_type")
    private String rules_type;

	@Field("psgr_min")
    private String psgr_min;

	@Field("date_tbl_no_994")
    private String date_tbl_no_994;

	@Field("f_b")
    private String f_b;

	@Field("text_tbl_no_996")
    private String text_tbl_no_996;

	@Field("record_batch")
    private String record_batch;

	@Field("acc_trvl_sect_all")
    private String acc_trvl_sect_all;

	@Field("appl")
    private String appl;

	@Field("cat_no")
    private String cat_no;

	@Field("psgr_max")
    private String psgr_max;

	@Field("psgr_age_min")
    private String psgr_age_min;

	@Field("rec_type")
    private String rec_type;

	@Field("acc_trvl_rule")
    private String acc_trvl_rule;

	@Field("filler_1")
    private String filler_1;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAcc_trvl_cmpt() {
		return acc_trvl_cmpt;
	}

	public void setAcc_trvl_cmpt(String acc_trvl_cmpt) {
		this.acc_trvl_cmpt = acc_trvl_cmpt;
	}

	public String getRecord_sequence() {
		return record_sequence;
	}

	public void setRecord_sequence(String record_sequence) {
		this.record_sequence = record_sequence;
	}

	public String getPsgr_id() {
		return psgr_id;
	}

	public void setPsgr_id(String psgr_id) {
		this.psgr_id = psgr_id;
	}

	public List<AtpcoRecord3Cat13FareClassRbd> getFare_class_rbd() {
		return fare_class_rbd;
	}

	public void setFare_class_rbd(List<AtpcoRecord3Cat13FareClassRbd> fare_class_rbd) {
		this.fare_class_rbd = fare_class_rbd;
	}

	public String getAcc_trvl_sect_out() {
		return acc_trvl_sect_out;
	}

	public void setAcc_trvl_sect_out(String acc_trvl_sect_out) {
		this.acc_trvl_sect_out = acc_trvl_sect_out;
	}

	public String getPsgr_type() {
		return psgr_type;
	}

	public void setPsgr_type(String psgr_type) {
		this.psgr_type = psgr_type;
	}

	public String getPsgr_age_max() {
		return psgr_age_max;
	}

	public void setPsgr_age_max(String psgr_age_max) {
		this.psgr_age_max = psgr_age_max;
	}

	public String getAcc_trvl_sect_one() {
		return acc_trvl_sect_one;
	}

	public void setAcc_trvl_sect_one(String acc_trvl_sect_one) {
		this.acc_trvl_sect_one = acc_trvl_sect_one;
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

	public String getPsgr_min() {
		return psgr_min;
	}

	public void setPsgr_min(String psgr_min) {
		this.psgr_min = psgr_min;
	}

	public String getDate_tbl_no_994() {
		return date_tbl_no_994;
	}

	public void setDate_tbl_no_994(String date_tbl_no_994) {
		this.date_tbl_no_994 = date_tbl_no_994;
	}

	public String getF_b() {
		return f_b;
	}

	public void setF_b(String f_b) {
		this.f_b = f_b;
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

	public String getAcc_trvl_sect_all() {
		return acc_trvl_sect_all;
	}

	public void setAcc_trvl_sect_all(String acc_trvl_sect_all) {
		this.acc_trvl_sect_all = acc_trvl_sect_all;
	}

	public String getAppl() {
		return appl;
	}

	public void setAppl(String appl) {
		this.appl = appl;
	}

	public String getCat_no() {
		return cat_no;
	}

	public void setCat_no(String cat_no) {
		this.cat_no = cat_no;
	}

	public String getPsgr_max() {
		return psgr_max;
	}

	public void setPsgr_max(String psgr_max) {
		this.psgr_max = psgr_max;
	}

	public String getPsgr_age_min() {
		return psgr_age_min;
	}

	public void setPsgr_age_min(String psgr_age_min) {
		this.psgr_age_min = psgr_age_min;
	}

	public String getRec_type() {
		return rec_type;
	}

	public void setRec_type(String rec_type) {
		this.rec_type = rec_type;
	}

	public String getAcc_trvl_rule() {
		return acc_trvl_rule;
	}

	public void setAcc_trvl_rule(String acc_trvl_rule) {
		this.acc_trvl_rule = acc_trvl_rule;
	}

	public String getFiller_1() {
		return filler_1;
	}

	public void setFiller_1(String filler_1) {
		this.filler_1 = filler_1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acc_trvl_cmpt == null) ? 0 : acc_trvl_cmpt.hashCode());
		result = prime * result + ((acc_trvl_rule == null) ? 0 : acc_trvl_rule.hashCode());
		result = prime * result + ((acc_trvl_sect_all == null) ? 0 : acc_trvl_sect_all.hashCode());
		result = prime * result + ((acc_trvl_sect_one == null) ? 0 : acc_trvl_sect_one.hashCode());
		result = prime * result + ((acc_trvl_sect_out == null) ? 0 : acc_trvl_sect_out.hashCode());
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((appl == null) ? 0 : appl.hashCode());
		result = prime * result + ((cat_no == null) ? 0 : cat_no.hashCode());
		result = prime * result + ((date_tbl_no_994 == null) ? 0 : date_tbl_no_994.hashCode());
		result = prime * result + ((f_b == null) ? 0 : f_b.hashCode());
		result = prime * result + ((fare_class_rbd == null) ? 0 : fare_class_rbd.hashCode());
		result = prime * result + ((filler_1 == null) ? 0 : filler_1.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((psgr_age_max == null) ? 0 : psgr_age_max.hashCode());
		result = prime * result + ((psgr_age_min == null) ? 0 : psgr_age_min.hashCode());
		result = prime * result + ((psgr_id == null) ? 0 : psgr_id.hashCode());
		result = prime * result + ((psgr_max == null) ? 0 : psgr_max.hashCode());
		result = prime * result + ((psgr_min == null) ? 0 : psgr_min.hashCode());
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
		AtpcoRecord3Cat13 other = (AtpcoRecord3Cat13) obj;
		if (acc_trvl_cmpt == null) {
			if (other.acc_trvl_cmpt != null)
				return false;
		} else if (!acc_trvl_cmpt.equals(other.acc_trvl_cmpt))
			return false;
		if (acc_trvl_rule == null) {
			if (other.acc_trvl_rule != null)
				return false;
		} else if (!acc_trvl_rule.equals(other.acc_trvl_rule))
			return false;
		if (acc_trvl_sect_all == null) {
			if (other.acc_trvl_sect_all != null)
				return false;
		} else if (!acc_trvl_sect_all.equals(other.acc_trvl_sect_all))
			return false;
		if (acc_trvl_sect_one == null) {
			if (other.acc_trvl_sect_one != null)
				return false;
		} else if (!acc_trvl_sect_one.equals(other.acc_trvl_sect_one))
			return false;
		if (acc_trvl_sect_out == null) {
			if (other.acc_trvl_sect_out != null)
				return false;
		} else if (!acc_trvl_sect_out.equals(other.acc_trvl_sect_out))
			return false;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
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
		if (f_b == null) {
			if (other.f_b != null)
				return false;
		} else if (!f_b.equals(other.f_b))
			return false;
		if (fare_class_rbd == null) {
			if (other.fare_class_rbd != null)
				return false;
		} else if (!fare_class_rbd.equals(other.fare_class_rbd))
			return false;
		if (filler_1 == null) {
			if (other.filler_1 != null)
				return false;
		} else if (!filler_1.equals(other.filler_1))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (psgr_age_max == null) {
			if (other.psgr_age_max != null)
				return false;
		} else if (!psgr_age_max.equals(other.psgr_age_max))
			return false;
		if (psgr_age_min == null) {
			if (other.psgr_age_min != null)
				return false;
		} else if (!psgr_age_min.equals(other.psgr_age_min))
			return false;
		if (psgr_id == null) {
			if (other.psgr_id != null)
				return false;
		} else if (!psgr_id.equals(other.psgr_id))
			return false;
		if (psgr_max == null) {
			if (other.psgr_max != null)
				return false;
		} else if (!psgr_max.equals(other.psgr_max))
			return false;
		if (psgr_min == null) {
			if (other.psgr_min != null)
				return false;
		} else if (!psgr_min.equals(other.psgr_min))
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
		return "AtpcoRecord3Cat13 [id=" + id + ", acc_trvl_cmpt=" + acc_trvl_cmpt + ", record_sequence="
				+ record_sequence + ", psgr_id=" + psgr_id + ", fare_class_rbd=" + fare_class_rbd
				+ ", acc_trvl_sect_out=" + acc_trvl_sect_out + ", psgr_type=" + psgr_type + ", psgr_age_max="
				+ psgr_age_max + ", acc_trvl_sect_one=" + acc_trvl_sect_one + ", tbl_no=" + tbl_no + ", unavail="
				+ unavail + ", action=" + action + ", rules_type=" + rules_type + ", psgr_min=" + psgr_min
				+ ", date_tbl_no_994=" + date_tbl_no_994 + ", f_b=" + f_b + ", text_tbl_no_996=" + text_tbl_no_996
				+ ", record_batch=" + record_batch + ", acc_trvl_sect_all=" + acc_trvl_sect_all + ", appl=" + appl
				+ ", cat_no=" + cat_no + ", psgr_max=" + psgr_max + ", psgr_age_min=" + psgr_age_min + ", rec_type="
				+ rec_type + ", acc_trvl_rule=" + acc_trvl_rule + ", filler_1=" + filler_1 + "]";
	}
	
	
}
