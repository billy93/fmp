package com.atibusinessgroup.fmp.domain.atpco;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_record_3_cat_028")
public class AtpcoRecord3Cat28 {

	@Id
    private String id;
	
	@Field("res")
	private String res;

	@Field("record_sequence")
    private String record_sequence;

	@Field("date_tbl_no_994")
    private String date_tbl_no_994;

	@Field("issue")
    private String issue;

	@Field("age_min")
    private String age_min;

	@Field("text_tbl_no_996")
    private String text_tbl_no_996;

	@Field("miles")
    private String miles;

	@Field("record_batch")
    private String record_batch;

	@Field("ptc")
    private String ptc;

	@Field("age_max")
    private String age_max;

	@Field("trvl")
    private String trvl;

	@Field("id")
    private String id_;

	@Field("geo_tbl_2_995")
    private String geo_tbl_2_995;

	@Field("cat_no")
    private String cat_no;

	@Field("geo_tbl_1_995")
    private String geo_tbl_1_995;

	@Field("ticket")
    private String ticket;

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

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
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

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
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

	public String getMiles() {
		return miles;
	}

	public void setMiles(String miles) {
		this.miles = miles;
	}

	public String getRecord_batch() {
		return record_batch;
	}

	public void setRecord_batch(String record_batch) {
		this.record_batch = record_batch;
	}

	public String getPtc() {
		return ptc;
	}

	public void setPtc(String ptc) {
		this.ptc = ptc;
	}

	public String getAge_max() {
		return age_max;
	}

	public void setAge_max(String age_max) {
		this.age_max = age_max;
	}

	public String getTrvl() {
		return trvl;
	}

	public void setTrvl(String trvl) {
		this.trvl = trvl;
	}

	public String getId_() {
		return id_;
	}

	public void setId_(String id_) {
		this.id_ = id_;
	}

	public String getGeo_tbl_2_995() {
		return geo_tbl_2_995;
	}

	public void setGeo_tbl_2_995(String geo_tbl_2_995) {
		this.geo_tbl_2_995 = geo_tbl_2_995;
	}

	public String getCat_no() {
		return cat_no;
	}

	public void setCat_no(String cat_no) {
		this.cat_no = cat_no;
	}

	public String getGeo_tbl_1_995() {
		return geo_tbl_1_995;
	}

	public void setGeo_tbl_1_995(String geo_tbl_1_995) {
		this.geo_tbl_1_995 = geo_tbl_1_995;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
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
		result = prime * result + ((age_max == null) ? 0 : age_max.hashCode());
		result = prime * result + ((age_min == null) ? 0 : age_min.hashCode());
		result = prime * result + ((cat_no == null) ? 0 : cat_no.hashCode());
		result = prime * result + ((date_tbl_no_994 == null) ? 0 : date_tbl_no_994.hashCode());
		result = prime * result + ((filler_1 == null) ? 0 : filler_1.hashCode());
		result = prime * result + ((filler_2 == null) ? 0 : filler_2.hashCode());
		result = prime * result + ((geo_tbl_1_995 == null) ? 0 : geo_tbl_1_995.hashCode());
		result = prime * result + ((geo_tbl_2_995 == null) ? 0 : geo_tbl_2_995.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_ == null) ? 0 : id_.hashCode());
		result = prime * result + ((issue == null) ? 0 : issue.hashCode());
		result = prime * result + ((miles == null) ? 0 : miles.hashCode());
		result = prime * result + ((ptc == null) ? 0 : ptc.hashCode());
		result = prime * result + ((rec_type == null) ? 0 : rec_type.hashCode());
		result = prime * result + ((record_batch == null) ? 0 : record_batch.hashCode());
		result = prime * result + ((record_sequence == null) ? 0 : record_sequence.hashCode());
		result = prime * result + ((res == null) ? 0 : res.hashCode());
		result = prime * result + ((rules_type == null) ? 0 : rules_type.hashCode());
		result = prime * result + ((tbl_no == null) ? 0 : tbl_no.hashCode());
		result = prime * result + ((text_tbl_no_996 == null) ? 0 : text_tbl_no_996.hashCode());
		result = prime * result + ((ticket == null) ? 0 : ticket.hashCode());
		result = prime * result + ((trvl == null) ? 0 : trvl.hashCode());
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
		AtpcoRecord3Cat28 other = (AtpcoRecord3Cat28) obj;
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
		if (geo_tbl_1_995 == null) {
			if (other.geo_tbl_1_995 != null)
				return false;
		} else if (!geo_tbl_1_995.equals(other.geo_tbl_1_995))
			return false;
		if (geo_tbl_2_995 == null) {
			if (other.geo_tbl_2_995 != null)
				return false;
		} else if (!geo_tbl_2_995.equals(other.geo_tbl_2_995))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_ == null) {
			if (other.id_ != null)
				return false;
		} else if (!id_.equals(other.id_))
			return false;
		if (issue == null) {
			if (other.issue != null)
				return false;
		} else if (!issue.equals(other.issue))
			return false;
		if (miles == null) {
			if (other.miles != null)
				return false;
		} else if (!miles.equals(other.miles))
			return false;
		if (ptc == null) {
			if (other.ptc != null)
				return false;
		} else if (!ptc.equals(other.ptc))
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
		if (res == null) {
			if (other.res != null)
				return false;
		} else if (!res.equals(other.res))
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
		if (ticket == null) {
			if (other.ticket != null)
				return false;
		} else if (!ticket.equals(other.ticket))
			return false;
		if (trvl == null) {
			if (other.trvl != null)
				return false;
		} else if (!trvl.equals(other.trvl))
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
		return "AtpcoRecord3Cat28 [id=" + id + ", res=" + res + ", record_sequence=" + record_sequence
				+ ", date_tbl_no_994=" + date_tbl_no_994 + ", issue=" + issue + ", age_min=" + age_min
				+ ", text_tbl_no_996=" + text_tbl_no_996 + ", miles=" + miles + ", record_batch=" + record_batch
				+ ", ptc=" + ptc + ", age_max=" + age_max + ", trvl=" + trvl + ", id_=" + id_ + ", geo_tbl_2_995="
				+ geo_tbl_2_995 + ", cat_no=" + cat_no + ", geo_tbl_1_995=" + geo_tbl_1_995 + ", ticket=" + ticket
				+ ", rec_type=" + rec_type + ", tbl_no=" + tbl_no + ", unavail=" + unavail + ", rules_type="
				+ rules_type + ", action=" + action + ", filler_2=" + filler_2 + ", filler_1=" + filler_1 + "]";
	}
	
	
}
