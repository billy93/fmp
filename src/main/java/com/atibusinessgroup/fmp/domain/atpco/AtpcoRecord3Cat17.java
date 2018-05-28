package com.atibusinessgroup.fmp.domain.atpco;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_record_3_cat_017")
public class AtpcoRecord3Cat17 {

	@Id
    private String id;
	
	@Field("record_sequence")
	private String record_sequence;

	@Field("date_tbl_no_994")
    private String date_tbl_no_994;

	@Field("no_hip")
    private String no_hip;

	@Field("geo_loc_1")
    private String geo_loc_1;

	@Field("geo_loc_2")
    private String geo_loc_2;

	@Field("text_tbl_no_996")
    private String text_tbl_no_996;

	@Field("record_batch")
    private String record_batch;

	@Field("cat_no")
    private String cat_no;

	@Field("rec_type")
    private String rec_type;

	@Field("tbl_no")
    private String tbl_no;

	@Field("geo_type")
    private String geo_type;

	@Field("unavail")
    private String unavail;

	@Field("rules_type")
    private String rules_type;

	@Field("action")
    private String action;

	@Field("cnx_stp")
    private String cnx_stp;

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

	public String getDate_tbl_no_994() {
		return date_tbl_no_994;
	}

	public void setDate_tbl_no_994(String date_tbl_no_994) {
		this.date_tbl_no_994 = date_tbl_no_994;
	}

	public String getNo_hip() {
		return no_hip;
	}

	public void setNo_hip(String no_hip) {
		this.no_hip = no_hip;
	}

	public String getGeo_loc_1() {
		return geo_loc_1;
	}

	public void setGeo_loc_1(String geo_loc_1) {
		this.geo_loc_1 = geo_loc_1;
	}

	public String getGeo_loc_2() {
		return geo_loc_2;
	}

	public void setGeo_loc_2(String geo_loc_2) {
		this.geo_loc_2 = geo_loc_2;
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

	public String getGeo_type() {
		return geo_type;
	}

	public void setGeo_type(String geo_type) {
		this.geo_type = geo_type;
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

	public String getCnx_stp() {
		return cnx_stp;
	}

	public void setCnx_stp(String cnx_stp) {
		this.cnx_stp = cnx_stp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((cat_no == null) ? 0 : cat_no.hashCode());
		result = prime * result + ((cnx_stp == null) ? 0 : cnx_stp.hashCode());
		result = prime * result + ((date_tbl_no_994 == null) ? 0 : date_tbl_no_994.hashCode());
		result = prime * result + ((geo_loc_1 == null) ? 0 : geo_loc_1.hashCode());
		result = prime * result + ((geo_loc_2 == null) ? 0 : geo_loc_2.hashCode());
		result = prime * result + ((geo_type == null) ? 0 : geo_type.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((no_hip == null) ? 0 : no_hip.hashCode());
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
		AtpcoRecord3Cat17 other = (AtpcoRecord3Cat17) obj;
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
		if (cnx_stp == null) {
			if (other.cnx_stp != null)
				return false;
		} else if (!cnx_stp.equals(other.cnx_stp))
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
		if (geo_loc_2 == null) {
			if (other.geo_loc_2 != null)
				return false;
		} else if (!geo_loc_2.equals(other.geo_loc_2))
			return false;
		if (geo_type == null) {
			if (other.geo_type != null)
				return false;
		} else if (!geo_type.equals(other.geo_type))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (no_hip == null) {
			if (other.no_hip != null)
				return false;
		} else if (!no_hip.equals(other.no_hip))
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
		return "AtpcoRecord3Cat17 [id=" + id + ", record_sequence=" + record_sequence + ", date_tbl_no_994="
				+ date_tbl_no_994 + ", no_hip=" + no_hip + ", geo_loc_1=" + geo_loc_1 + ", geo_loc_2=" + geo_loc_2
				+ ", text_tbl_no_996=" + text_tbl_no_996 + ", record_batch=" + record_batch + ", cat_no=" + cat_no
				+ ", rec_type=" + rec_type + ", tbl_no=" + tbl_no + ", geo_type=" + geo_type + ", unavail=" + unavail
				+ ", rules_type=" + rules_type + ", action=" + action + ", cnx_stp=" + cnx_stp + "]";
	}
	
	
}
