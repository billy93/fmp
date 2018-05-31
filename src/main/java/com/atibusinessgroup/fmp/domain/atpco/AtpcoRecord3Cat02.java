package com.atibusinessgroup.fmp.domain.atpco;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_record_3_cat_002")
public class AtpcoRecord3Cat02 {

	@Id
    private String id;
	
	@Field("neg")
	private String neg;

	@Field("time_of_day_appl")
    private String time_of_day_appl;

	@Field("record_sequence")
    private String record_sequence;

	@Field("date_tbl_no_994")
    private String date_tbl_no_994;

	@Field("text_tbl_no_996")
    private String text_tbl_no_996;

	@Field("record_batch")
    private String record_batch;

	@Field("day_of_week_same")
    private String day_of_week_same;

	@Field("appl")
    private String appl;

	@Field("cat_no")
    private String cat_no;

	@Field("time_of_day_stop")
    private String time_of_day_stop;

	@Field("day_of_week")
    private String day_of_week;

	@Field("day_of_week_occur")
    private String day_of_week_occur;

	@Field("rec_type")
    private String rec_type;

	@Field("tbl_no")
    private String tbl_no;

	@Field("unavail")
    private String unavail;

	@Field("geo_tbl_no_995")
    private String geo_tbl_no_995;

	@Field("rules_type")
    private String rules_type;

	@Field("action")
    private String action;

	@Field("time_of_day_start")
    private String time_of_day_start;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNeg() {
		return neg;
	}

	public void setNeg(String neg) {
		this.neg = neg;
	}

	public String getTime_of_day_appl() {
		return time_of_day_appl;
	}

	public void setTime_of_day_appl(String time_of_day_appl) {
		this.time_of_day_appl = time_of_day_appl;
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

	public String getDay_of_week_same() {
		return day_of_week_same;
	}

	public void setDay_of_week_same(String day_of_week_same) {
		this.day_of_week_same = day_of_week_same;
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

	public String getTime_of_day_stop() {
		return time_of_day_stop;
	}

	public void setTime_of_day_stop(String time_of_day_stop) {
		this.time_of_day_stop = time_of_day_stop;
	}

	public String getDay_of_week() {
		return day_of_week;
	}

	public void setDay_of_week(String day_of_week) {
		this.day_of_week = day_of_week;
	}

	public String getDay_of_week_occur() {
		return day_of_week_occur;
	}

	public void setDay_of_week_occur(String day_of_week_occur) {
		this.day_of_week_occur = day_of_week_occur;
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

	public String getGeo_tbl_no_995() {
		return geo_tbl_no_995;
	}

	public void setGeo_tbl_no_995(String geo_tbl_no_995) {
		this.geo_tbl_no_995 = geo_tbl_no_995;
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

	public String getTime_of_day_start() {
		return time_of_day_start;
	}

	public void setTime_of_day_start(String time_of_day_start) {
		this.time_of_day_start = time_of_day_start;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((appl == null) ? 0 : appl.hashCode());
		result = prime * result + ((cat_no == null) ? 0 : cat_no.hashCode());
		result = prime * result + ((date_tbl_no_994 == null) ? 0 : date_tbl_no_994.hashCode());
		result = prime * result + ((day_of_week == null) ? 0 : day_of_week.hashCode());
		result = prime * result + ((day_of_week_occur == null) ? 0 : day_of_week_occur.hashCode());
		result = prime * result + ((day_of_week_same == null) ? 0 : day_of_week_same.hashCode());
		result = prime * result + ((geo_tbl_no_995 == null) ? 0 : geo_tbl_no_995.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((neg == null) ? 0 : neg.hashCode());
		result = prime * result + ((rec_type == null) ? 0 : rec_type.hashCode());
		result = prime * result + ((record_batch == null) ? 0 : record_batch.hashCode());
		result = prime * result + ((record_sequence == null) ? 0 : record_sequence.hashCode());
		result = prime * result + ((rules_type == null) ? 0 : rules_type.hashCode());
		result = prime * result + ((tbl_no == null) ? 0 : tbl_no.hashCode());
		result = prime * result + ((text_tbl_no_996 == null) ? 0 : text_tbl_no_996.hashCode());
		result = prime * result + ((time_of_day_appl == null) ? 0 : time_of_day_appl.hashCode());
		result = prime * result + ((time_of_day_start == null) ? 0 : time_of_day_start.hashCode());
		result = prime * result + ((time_of_day_stop == null) ? 0 : time_of_day_stop.hashCode());
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
		AtpcoRecord3Cat02 other = (AtpcoRecord3Cat02) obj;
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
		if (day_of_week == null) {
			if (other.day_of_week != null)
				return false;
		} else if (!day_of_week.equals(other.day_of_week))
			return false;
		if (day_of_week_occur == null) {
			if (other.day_of_week_occur != null)
				return false;
		} else if (!day_of_week_occur.equals(other.day_of_week_occur))
			return false;
		if (day_of_week_same == null) {
			if (other.day_of_week_same != null)
				return false;
		} else if (!day_of_week_same.equals(other.day_of_week_same))
			return false;
		if (geo_tbl_no_995 == null) {
			if (other.geo_tbl_no_995 != null)
				return false;
		} else if (!geo_tbl_no_995.equals(other.geo_tbl_no_995))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (neg == null) {
			if (other.neg != null)
				return false;
		} else if (!neg.equals(other.neg))
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
		if (time_of_day_appl == null) {
			if (other.time_of_day_appl != null)
				return false;
		} else if (!time_of_day_appl.equals(other.time_of_day_appl))
			return false;
		if (time_of_day_start == null) {
			if (other.time_of_day_start != null)
				return false;
		} else if (!time_of_day_start.equals(other.time_of_day_start))
			return false;
		if (time_of_day_stop == null) {
			if (other.time_of_day_stop != null)
				return false;
		} else if (!time_of_day_stop.equals(other.time_of_day_stop))
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
		return "AtpcoRecord3Cat02 [id=" + id + ", neg=" + neg + ", time_of_day_appl=" + time_of_day_appl
				+ ", record_sequence=" + record_sequence + ", date_tbl_no_994=" + date_tbl_no_994 + ", text_tbl_no_996="
				+ text_tbl_no_996 + ", record_batch=" + record_batch + ", day_of_week_same=" + day_of_week_same
				+ ", appl=" + appl + ", cat_no=" + cat_no + ", time_of_day_stop=" + time_of_day_stop + ", day_of_week="
				+ day_of_week + ", day_of_week_occur=" + day_of_week_occur + ", rec_type=" + rec_type + ", tbl_no="
				+ tbl_no + ", unavail=" + unavail + ", geo_tbl_no_995=" + geo_tbl_no_995 + ", rules_type=" + rules_type
				+ ", action=" + action + ", time_of_day_start=" + time_of_day_start + "]";
	}
	
	
}
