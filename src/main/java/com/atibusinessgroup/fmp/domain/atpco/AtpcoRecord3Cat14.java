package com.atibusinessgroup.fmp.domain.atpco;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_record_3_cat_014")
public class AtpcoRecord3Cat14 {

	@Id
    private String id;
	
	@Field("text_table_no_996")
	private String text_table_no_996;

	@Field("record_sequence")
    private String record_sequence;

	@Field("travel_dates_appl")
    private String travel_dates_appl;

	@Field("travel_dates_time")
    private String travel_dates_time;

	@Field("record_batch")
    private String record_batch;

	@Field("travel_dates_comm")
    private Object travel_dates_comm;

	@Field("geo_table_no_995")
    private String geo_table_no_995;

	@Field("date_table_no_994")
    private String date_table_no_994;

	@Field("cat_no")
    private String cat_no;

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

	@Field("travel_dates_exp")
    private Object travel_dates_exp;

	@Field("travel_dates_commence_complete")
    private Object travel_dates_commence_complete;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText_table_no_996() {
		return text_table_no_996;
	}

	public void setText_table_no_996(String text_table_no_996) {
		this.text_table_no_996 = text_table_no_996;
	}

	public String getRecord_sequence() {
		return record_sequence;
	}

	public void setRecord_sequence(String record_sequence) {
		this.record_sequence = record_sequence;
	}

	public String getTravel_dates_appl() {
		return travel_dates_appl;
	}

	public void setTravel_dates_appl(String travel_dates_appl) {
		this.travel_dates_appl = travel_dates_appl;
	}

	public String getTravel_dates_time() {
		return travel_dates_time;
	}

	public void setTravel_dates_time(String travel_dates_time) {
		this.travel_dates_time = travel_dates_time;
	}

	public String getRecord_batch() {
		return record_batch;
	}

	public void setRecord_batch(String record_batch) {
		this.record_batch = record_batch;
	}

	public Object getTravel_dates_comm() {
		return travel_dates_comm;
	}

	public void setTravel_dates_comm(Object travel_dates_comm) {
		this.travel_dates_comm = travel_dates_comm;
	}

	public String getGeo_table_no_995() {
		return geo_table_no_995;
	}

	public void setGeo_table_no_995(String geo_table_no_995) {
		this.geo_table_no_995 = geo_table_no_995;
	}

	public String getDate_table_no_994() {
		return date_table_no_994;
	}

	public void setDate_table_no_994(String date_table_no_994) {
		this.date_table_no_994 = date_table_no_994;
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

	public Object getTravel_dates_exp() {
		return travel_dates_exp;
	}

	public void setTravel_dates_exp(Object travel_dates_exp) {
		this.travel_dates_exp = travel_dates_exp;
	}

	public Object getTravel_dates_commence_complete() {
		return travel_dates_commence_complete;
	}

	public void setTravel_dates_commence_complete(Object travel_dates_commence_complete) {
		this.travel_dates_commence_complete = travel_dates_commence_complete;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((cat_no == null) ? 0 : cat_no.hashCode());
		result = prime * result + ((date_table_no_994 == null) ? 0 : date_table_no_994.hashCode());
		result = prime * result + ((geo_table_no_995 == null) ? 0 : geo_table_no_995.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((rec_type == null) ? 0 : rec_type.hashCode());
		result = prime * result + ((record_batch == null) ? 0 : record_batch.hashCode());
		result = prime * result + ((record_sequence == null) ? 0 : record_sequence.hashCode());
		result = prime * result + ((rules_type == null) ? 0 : rules_type.hashCode());
		result = prime * result + ((tbl_no == null) ? 0 : tbl_no.hashCode());
		result = prime * result + ((text_table_no_996 == null) ? 0 : text_table_no_996.hashCode());
		result = prime * result + ((travel_dates_appl == null) ? 0 : travel_dates_appl.hashCode());
		result = prime * result + ((travel_dates_comm == null) ? 0 : travel_dates_comm.hashCode());
		result = prime * result
				+ ((travel_dates_commence_complete == null) ? 0 : travel_dates_commence_complete.hashCode());
		result = prime * result + ((travel_dates_exp == null) ? 0 : travel_dates_exp.hashCode());
		result = prime * result + ((travel_dates_time == null) ? 0 : travel_dates_time.hashCode());
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
		AtpcoRecord3Cat14 other = (AtpcoRecord3Cat14) obj;
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
		if (date_table_no_994 == null) {
			if (other.date_table_no_994 != null)
				return false;
		} else if (!date_table_no_994.equals(other.date_table_no_994))
			return false;
		if (geo_table_no_995 == null) {
			if (other.geo_table_no_995 != null)
				return false;
		} else if (!geo_table_no_995.equals(other.geo_table_no_995))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		if (text_table_no_996 == null) {
			if (other.text_table_no_996 != null)
				return false;
		} else if (!text_table_no_996.equals(other.text_table_no_996))
			return false;
		if (travel_dates_appl == null) {
			if (other.travel_dates_appl != null)
				return false;
		} else if (!travel_dates_appl.equals(other.travel_dates_appl))
			return false;
		if (travel_dates_comm == null) {
			if (other.travel_dates_comm != null)
				return false;
		} else if (!travel_dates_comm.equals(other.travel_dates_comm))
			return false;
		if (travel_dates_commence_complete == null) {
			if (other.travel_dates_commence_complete != null)
				return false;
		} else if (!travel_dates_commence_complete.equals(other.travel_dates_commence_complete))
			return false;
		if (travel_dates_exp == null) {
			if (other.travel_dates_exp != null)
				return false;
		} else if (!travel_dates_exp.equals(other.travel_dates_exp))
			return false;
		if (travel_dates_time == null) {
			if (other.travel_dates_time != null)
				return false;
		} else if (!travel_dates_time.equals(other.travel_dates_time))
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
		return "AtpcoRecord3Cat14 [id=" + id + ", text_table_no_996=" + text_table_no_996 + ", record_sequence="
				+ record_sequence + ", travel_dates_appl=" + travel_dates_appl + ", travel_dates_time="
				+ travel_dates_time + ", record_batch=" + record_batch + ", travel_dates_comm=" + travel_dates_comm
				+ ", geo_table_no_995=" + geo_table_no_995 + ", date_table_no_994=" + date_table_no_994 + ", cat_no="
				+ cat_no + ", rec_type=" + rec_type + ", tbl_no=" + tbl_no + ", unavail=" + unavail + ", rules_type="
				+ rules_type + ", action=" + action + ", travel_dates_exp=" + travel_dates_exp
				+ ", travel_dates_commence_complete=" + travel_dates_commence_complete + "]";
	}

	
}
