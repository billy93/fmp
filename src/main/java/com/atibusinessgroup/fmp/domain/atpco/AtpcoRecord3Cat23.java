package com.atibusinessgroup.fmp.domain.atpco;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_record_3_cat_023")
public class AtpcoRecord3Cat23 {

	@Id
    private String id;
	
	@Field("diff_calc")
	private String diff_calc;

	@Field("record_sequence")
    private String record_sequence;

	@Field("add_on_constr")
    private String add_on_constr;

	@Field("date_tbl_no_994")
    private String date_tbl_no_994;

	@Field("prorate")
    private String prorate;

	@Field("text_tbl_no_995")
    private String text_tbl_no_995;

	@Field("record_batch")
    private String record_batch;

	@Field("extension_fares")
    private String extension_fares;

	@Field("cat_no")
    private String cat_no;

	@Field("rec_type")
    private String rec_type;

	@Field("tbl_no")
    private String tbl_no;

	@Field("unavail")
    private String unavail;

	@Field("reserved_1")
    private String reserved_1;

	@Field("rules_type")
    private String rules_type;

	@Field("action")
    private String action;

	@Field("filler_4")
    private String filler_4;

	@Field("filler_3")
    private String filler_3;

	@Field("filler_2")
    private String filler_2;

	@Field("filler_1")
    private String filler_1;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDiff_calc() {
		return diff_calc;
	}

	public void setDiff_calc(String diff_calc) {
		this.diff_calc = diff_calc;
	}

	public String getRecord_sequence() {
		return record_sequence;
	}

	public void setRecord_sequence(String record_sequence) {
		this.record_sequence = record_sequence;
	}

	public String getAdd_on_constr() {
		return add_on_constr;
	}

	public void setAdd_on_constr(String add_on_constr) {
		this.add_on_constr = add_on_constr;
	}

	public String getDate_tbl_no_994() {
		return date_tbl_no_994;
	}

	public void setDate_tbl_no_994(String date_tbl_no_994) {
		this.date_tbl_no_994 = date_tbl_no_994;
	}

	public String getProrate() {
		return prorate;
	}

	public void setProrate(String prorate) {
		this.prorate = prorate;
	}

	public String getText_tbl_no_995() {
		return text_tbl_no_995;
	}

	public void setText_tbl_no_995(String text_tbl_no_995) {
		this.text_tbl_no_995 = text_tbl_no_995;
	}

	public String getRecord_batch() {
		return record_batch;
	}

	public void setRecord_batch(String record_batch) {
		this.record_batch = record_batch;
	}

	public String getExtension_fares() {
		return extension_fares;
	}

	public void setExtension_fares(String extension_fares) {
		this.extension_fares = extension_fares;
	}

	public String getCat_no() {
		return cat_no;
	}

	public void setCat_no(String cat_no) {
		this.cat_no = cat_no;
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

	public String getReserved_1() {
		return reserved_1;
	}

	public void setReserved_1(String reserved_1) {
		this.reserved_1 = reserved_1;
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

	public String getFiller_4() {
		return filler_4;
	}

	public void setFiller_4(String filler_4) {
		this.filler_4 = filler_4;
	}

	public String getFiller_3() {
		return filler_3;
	}

	public void setFiller_3(String filler_3) {
		this.filler_3 = filler_3;
	}

	public String getFiller_2() {
		return filler_2;
	}

	public void setFiller_2(String filler_2) {
		this.filler_2 = filler_2;
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
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((add_on_constr == null) ? 0 : add_on_constr.hashCode());
		result = prime * result + ((cat_no == null) ? 0 : cat_no.hashCode());
		result = prime * result + ((date_tbl_no_994 == null) ? 0 : date_tbl_no_994.hashCode());
		result = prime * result + ((diff_calc == null) ? 0 : diff_calc.hashCode());
		result = prime * result + ((extension_fares == null) ? 0 : extension_fares.hashCode());
		result = prime * result + ((filler_1 == null) ? 0 : filler_1.hashCode());
		result = prime * result + ((filler_2 == null) ? 0 : filler_2.hashCode());
		result = prime * result + ((filler_3 == null) ? 0 : filler_3.hashCode());
		result = prime * result + ((filler_4 == null) ? 0 : filler_4.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((prorate == null) ? 0 : prorate.hashCode());
		result = prime * result + ((rec_type == null) ? 0 : rec_type.hashCode());
		result = prime * result + ((record_batch == null) ? 0 : record_batch.hashCode());
		result = prime * result + ((record_sequence == null) ? 0 : record_sequence.hashCode());
		result = prime * result + ((reserved_1 == null) ? 0 : reserved_1.hashCode());
		result = prime * result + ((rules_type == null) ? 0 : rules_type.hashCode());
		result = prime * result + ((tbl_no == null) ? 0 : tbl_no.hashCode());
		result = prime * result + ((text_tbl_no_995 == null) ? 0 : text_tbl_no_995.hashCode());
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
		AtpcoRecord3Cat23 other = (AtpcoRecord3Cat23) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (add_on_constr == null) {
			if (other.add_on_constr != null)
				return false;
		} else if (!add_on_constr.equals(other.add_on_constr))
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
		if (diff_calc == null) {
			if (other.diff_calc != null)
				return false;
		} else if (!diff_calc.equals(other.diff_calc))
			return false;
		if (extension_fares == null) {
			if (other.extension_fares != null)
				return false;
		} else if (!extension_fares.equals(other.extension_fares))
			return false;
		if (filler_1 == null) {
			if (other.filler_1 != null)
				return false;
		} else if (!filler_1.equals(other.filler_1))
			return false;
		if (filler_2 == null) {
			if (other.filler_2 != null)
				return false;
		} else if (!filler_2.equals(other.filler_2))
			return false;
		if (filler_3 == null) {
			if (other.filler_3 != null)
				return false;
		} else if (!filler_3.equals(other.filler_3))
			return false;
		if (filler_4 == null) {
			if (other.filler_4 != null)
				return false;
		} else if (!filler_4.equals(other.filler_4))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (prorate == null) {
			if (other.prorate != null)
				return false;
		} else if (!prorate.equals(other.prorate))
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
		if (text_tbl_no_995 == null) {
			if (other.text_tbl_no_995 != null)
				return false;
		} else if (!text_tbl_no_995.equals(other.text_tbl_no_995))
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
		return "AtpcoRecord3Cat23 [id=" + id + ", diff_calc=" + diff_calc + ", record_sequence=" + record_sequence
				+ ", add_on_constr=" + add_on_constr + ", date_tbl_no_994=" + date_tbl_no_994 + ", prorate=" + prorate
				+ ", text_tbl_no_995=" + text_tbl_no_995 + ", record_batch=" + record_batch + ", extension_fares="
				+ extension_fares + ", cat_no=" + cat_no + ", rec_type=" + rec_type + ", tbl_no=" + tbl_no
				+ ", unavail=" + unavail + ", reserved_1=" + reserved_1 + ", rules_type=" + rules_type + ", action="
				+ action + ", filler_4=" + filler_4 + ", filler_3=" + filler_3 + ", filler_2=" + filler_2
				+ ", filler_1=" + filler_1 + "]";
	}
	
	
}
