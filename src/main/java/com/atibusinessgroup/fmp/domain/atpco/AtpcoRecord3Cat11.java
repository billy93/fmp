package com.atibusinessgroup.fmp.domain.atpco;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_record_3_cat_011")
public class AtpcoRecord3Cat11 {

	@Id
    private String id;
	
	@Field("record_sequence")
	private String record_sequence;

	@Field("date_start_dd")
    private String date_start_dd;

	@Field("date_tbl_no_994")
    private String date_tbl_no_994;

	@Field("intl_cnx")
    private String intl_cnx;

	@Field("text_tbl_no_996")
    private String text_tbl_no_996;

	@Field("btw_geo_tbl_no_995")
    private String btw_geo_tbl_no_995;

	@Field("date_start_yy")
    private String date_start_yy;

	@Field("record_batch")
    private String record_batch;

	@Field("date_stop_mm")
    private String date_stop_mm;
	
	@Field("appl")
    private String appl;

	@Field("cat_no")
    private String cat_no;

	@Field("date_start_mm")
    private String date_start_mm;

	@Field("rec_type")
    private String rec_type;

	@Field("tbl_no")
    private String tbl_no;

	@Field("unavail")
    private String unavail;

	@Field("and_geo_tbl_no_995")
    private String and_geo_tbl_no_995;

	@Field("rules_type")
    private String rules_type;

	@Field("action")
    private String action;

	@Field("date_stop_yy")
    private String date_stop_yy;

	@Field("date_stop_dd")
    private String date_stop_dd;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRecord_sequence() {
		return record_sequence;
	}

	public void setRecord_sequence(String record_sequence) {
		this.record_sequence = record_sequence;
	}

	public String getDate_start_dd() {
		return date_start_dd;
	}

	public void setDate_start_dd(String date_start_dd) {
		this.date_start_dd = date_start_dd;
	}

	public String getDate_tbl_no_994() {
		return date_tbl_no_994;
	}

	public void setDate_tbl_no_994(String date_tbl_no_994) {
		this.date_tbl_no_994 = date_tbl_no_994;
	}

	public String getIntl_cnx() {
		return intl_cnx;
	}

	public void setIntl_cnx(String intl_cnx) {
		this.intl_cnx = intl_cnx;
	}

	public String getText_tbl_no_996() {
		return text_tbl_no_996;
	}

	public void setText_tbl_no_996(String text_tbl_no_996) {
		this.text_tbl_no_996 = text_tbl_no_996;
	}

	public String getBtw_geo_tbl_no_995() {
		return btw_geo_tbl_no_995;
	}

	public void setBtw_geo_tbl_no_995(String btw_geo_tbl_no_995) {
		this.btw_geo_tbl_no_995 = btw_geo_tbl_no_995;
	}

	public String getDate_start_yy() {
		return date_start_yy;
	}

	public void setDate_start_yy(String date_start_yy) {
		this.date_start_yy = date_start_yy;
	}

	public String getRecord_batch() {
		return record_batch;
	}

	public void setRecord_batch(String record_batch) {
		this.record_batch = record_batch;
	}

	public String getDate_stop_mm() {
		return date_stop_mm;
	}

	public void setDate_stop_mm(String date_stop_mm) {
		this.date_stop_mm = date_stop_mm;
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

	public String getDate_start_mm() {
		return date_start_mm;
	}

	public void setDate_start_mm(String date_start_mm) {
		this.date_start_mm = date_start_mm;
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

	public String getAnd_geo_tbl_no_995() {
		return and_geo_tbl_no_995;
	}

	public void setAnd_geo_tbl_no_995(String and_geo_tbl_no_995) {
		this.and_geo_tbl_no_995 = and_geo_tbl_no_995;
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

	public String getDate_stop_yy() {
		return date_stop_yy;
	}

	public void setDate_stop_yy(String date_stop_yy) {
		this.date_stop_yy = date_stop_yy;
	}

	public String getDate_stop_dd() {
		return date_stop_dd;
	}

	public void setDate_stop_dd(String date_stop_dd) {
		this.date_stop_dd = date_stop_dd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((and_geo_tbl_no_995 == null) ? 0 : and_geo_tbl_no_995.hashCode());
		result = prime * result + ((appl == null) ? 0 : appl.hashCode());
		result = prime * result + ((btw_geo_tbl_no_995 == null) ? 0 : btw_geo_tbl_no_995.hashCode());
		result = prime * result + ((cat_no == null) ? 0 : cat_no.hashCode());
		result = prime * result + ((date_start_dd == null) ? 0 : date_start_dd.hashCode());
		result = prime * result + ((date_start_mm == null) ? 0 : date_start_mm.hashCode());
		result = prime * result + ((date_start_yy == null) ? 0 : date_start_yy.hashCode());
		result = prime * result + ((date_stop_dd == null) ? 0 : date_stop_dd.hashCode());
		result = prime * result + ((date_stop_mm == null) ? 0 : date_stop_mm.hashCode());
		result = prime * result + ((date_stop_yy == null) ? 0 : date_stop_yy.hashCode());
		result = prime * result + ((date_tbl_no_994 == null) ? 0 : date_tbl_no_994.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((intl_cnx == null) ? 0 : intl_cnx.hashCode());
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
		AtpcoRecord3Cat11 other = (AtpcoRecord3Cat11) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (and_geo_tbl_no_995 == null) {
			if (other.and_geo_tbl_no_995 != null)
				return false;
		} else if (!and_geo_tbl_no_995.equals(other.and_geo_tbl_no_995))
			return false;
		if (appl == null) {
			if (other.appl != null)
				return false;
		} else if (!appl.equals(other.appl))
			return false;
		if (btw_geo_tbl_no_995 == null) {
			if (other.btw_geo_tbl_no_995 != null)
				return false;
		} else if (!btw_geo_tbl_no_995.equals(other.btw_geo_tbl_no_995))
			return false;
		if (cat_no == null) {
			if (other.cat_no != null)
				return false;
		} else if (!cat_no.equals(other.cat_no))
			return false;
		if (date_start_dd == null) {
			if (other.date_start_dd != null)
				return false;
		} else if (!date_start_dd.equals(other.date_start_dd))
			return false;
		if (date_start_mm == null) {
			if (other.date_start_mm != null)
				return false;
		} else if (!date_start_mm.equals(other.date_start_mm))
			return false;
		if (date_start_yy == null) {
			if (other.date_start_yy != null)
				return false;
		} else if (!date_start_yy.equals(other.date_start_yy))
			return false;
		if (date_stop_dd == null) {
			if (other.date_stop_dd != null)
				return false;
		} else if (!date_stop_dd.equals(other.date_stop_dd))
			return false;
		if (date_stop_mm == null) {
			if (other.date_stop_mm != null)
				return false;
		} else if (!date_stop_mm.equals(other.date_stop_mm))
			return false;
		if (date_stop_yy == null) {
			if (other.date_stop_yy != null)
				return false;
		} else if (!date_stop_yy.equals(other.date_stop_yy))
			return false;
		if (date_tbl_no_994 == null) {
			if (other.date_tbl_no_994 != null)
				return false;
		} else if (!date_tbl_no_994.equals(other.date_tbl_no_994))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (intl_cnx == null) {
			if (other.intl_cnx != null)
				return false;
		} else if (!intl_cnx.equals(other.intl_cnx))
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
		return "AtpcoRecord3Cat11 [id=" + id + ", record_sequence=" + record_sequence + ", date_start_dd="
				+ date_start_dd + ", date_tbl_no_994=" + date_tbl_no_994 + ", intl_cnx=" + intl_cnx
				+ ", text_tbl_no_996=" + text_tbl_no_996 + ", btw_geo_tbl_no_995=" + btw_geo_tbl_no_995
				+ ", date_start_yy=" + date_start_yy + ", record_batch=" + record_batch + ", date_stop_mm="
				+ date_stop_mm + ", appl=" + appl + ", cat_no=" + cat_no + ", date_start_mm=" + date_start_mm
				+ ", rec_type=" + rec_type + ", tbl_no=" + tbl_no + ", unavail=" + unavail + ", and_geo_tbl_no_995="
				+ and_geo_tbl_no_995 + ", rules_type=" + rules_type + ", action=" + action + ", date_stop_yy="
				+ date_stop_yy + ", date_stop_dd=" + date_stop_dd + "]";
	}

	
}
