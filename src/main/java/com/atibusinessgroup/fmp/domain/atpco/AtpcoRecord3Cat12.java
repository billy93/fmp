package com.atibusinessgroup.fmp.domain.atpco;


import org.bson.types.Decimal128;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_record_3_cat_012")
public class AtpcoRecord3Cat12 {

	@Id
    private String id;
	
	@Field("sect_prt")
	private String sect_prt;

	@Field("text_table_no_996")
    private String text_table_no_996;

	@Field("record_sequence")
    private String record_sequence;

	@Field("date_start_dd")
    private String date_start_dd;

	@Field("btw_geo_tbl_995")
    private String btw_geo_tbl_995;

	@Field("from_to_via")
    private String from_to_via;

	@Field("day_of_week")
    private String day_of_week;

	@Field("tbl_no")
    private String tbl_no;

	@Field("sur_type")
    private String sur_type;

	@Field("cxr_flt_tbl_no_986")
    private String cxr_flt_tbl_no_986;

	@Field("unavail")
    private String unavail;

	@Field("date_stop_yy")
    private String date_stop_yy;

	@Field("action")
    private String action;

	@Field("rules_type")
    private String rules_type;

	@Field("surcharge_appl")
    private String surcharge_appl;

	@Field("and_geo_tbl_995")
    private String and_geo_tbl_995;

	@Field("rbd")
    private String rbd;

	@Field("charges_percent")
    private Decimal128 charges_percent;

	@Field("charges_appl")
    private String charges_appl;

	@Field("date_stop_dd")
    private String date_stop_dd;

	@Field("charges_second_amt")
    private Decimal128 charges_second_amt;

	@Field("charges_base")
    private String charges_base;

	@Field("charges_first_cur")
    private String charges_first_cur;

	@Field("eqpt")
    private String eqpt;

	@Field("charges_second_dec")
    private String charges_second_dec;

	@Field("date_start_yy")
    private String date_start_yy;

	@Field("record_batch")
    private String record_batch;

	@Field("date_table_no_994")
    private String date_table_no_994;

	@Field("date_stop_mm")
    private String date_stop_mm;

	@Field("appl")
    private String appl;

	@Field("charges_second_cur")
    private String charges_second_cur;

	@Field("date_start_mm")
    private String date_start_mm;

	@Field("cat_no")
    private String cat_no;

	@Field("time_of_day_stop")
    private String time_of_day_stop;

	@Field("rec_type")
    private String rec_type;

	@Field("charges_first_dec")
    private String charges_first_dec;

	@Field("charges_first_amt")
    private Decimal128 charges_first_amt;

	@Field("time_of_day_start")
    private String time_of_day_start;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSect_prt() {
		return sect_prt;
	}

	public void setSect_prt(String sect_prt) {
		this.sect_prt = sect_prt;
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

	public String getDate_start_dd() {
		return date_start_dd;
	}

	public void setDate_start_dd(String date_start_dd) {
		this.date_start_dd = date_start_dd;
	}

	public String getBtw_geo_tbl_995() {
		return btw_geo_tbl_995;
	}

	public void setBtw_geo_tbl_995(String btw_geo_tbl_995) {
		this.btw_geo_tbl_995 = btw_geo_tbl_995;
	}

	public String getFrom_to_via() {
		return from_to_via;
	}

	public void setFrom_to_via(String from_to_via) {
		this.from_to_via = from_to_via;
	}

	public String getDay_of_week() {
		return day_of_week;
	}

	public void setDay_of_week(String day_of_week) {
		this.day_of_week = day_of_week;
	}

	public String getTbl_no() {
		return tbl_no;
	}

	public void setTbl_no(String tbl_no) {
		this.tbl_no = tbl_no;
	}

	public String getSur_type() {
		return sur_type;
	}

	public void setSur_type(String sur_type) {
		this.sur_type = sur_type;
	}

	public String getCxr_flt_tbl_no_986() {
		return cxr_flt_tbl_no_986;
	}

	public void setCxr_flt_tbl_no_986(String cxr_flt_tbl_no_986) {
		this.cxr_flt_tbl_no_986 = cxr_flt_tbl_no_986;
	}

	public String getUnavail() {
		return unavail;
	}

	public void setUnavail(String unavail) {
		this.unavail = unavail;
	}

	public String getDate_stop_yy() {
		return date_stop_yy;
	}

	public void setDate_stop_yy(String date_stop_yy) {
		this.date_stop_yy = date_stop_yy;
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

	public String getSurcharge_appl() {
		return surcharge_appl;
	}

	public void setSurcharge_appl(String surcharge_appl) {
		this.surcharge_appl = surcharge_appl;
	}

	public String getAnd_geo_tbl_995() {
		return and_geo_tbl_995;
	}

	public void setAnd_geo_tbl_995(String and_geo_tbl_995) {
		this.and_geo_tbl_995 = and_geo_tbl_995;
	}

	public String getRbd() {
		return rbd;
	}

	public void setRbd(String rbd) {
		this.rbd = rbd;
	}

	public Decimal128 getCharges_percent() {
		return charges_percent;
	}

	public void setCharges_percent(Decimal128 charges_percent) {
		this.charges_percent = charges_percent;
	}

	public String getCharges_appl() {
		return charges_appl;
	}

	public void setCharges_appl(String charges_appl) {
		this.charges_appl = charges_appl;
	}

	public String getDate_stop_dd() {
		return date_stop_dd;
	}

	public void setDate_stop_dd(String date_stop_dd) {
		this.date_stop_dd = date_stop_dd;
	}

	public Decimal128 getCharges_second_amt() {
		return charges_second_amt;
	}

	public void setCharges_second_amt(Decimal128 charges_second_amt) {
		this.charges_second_amt = charges_second_amt;
	}

	public String getCharges_base() {
		return charges_base;
	}

	public void setCharges_base(String charges_base) {
		this.charges_base = charges_base;
	}

	public String getCharges_first_cur() {
		return charges_first_cur;
	}

	public void setCharges_first_cur(String charges_first_cur) {
		this.charges_first_cur = charges_first_cur;
	}

	public String getEqpt() {
		return eqpt;
	}

	public void setEqpt(String eqpt) {
		this.eqpt = eqpt;
	}

	public String getCharges_second_dec() {
		return charges_second_dec;
	}

	public void setCharges_second_dec(String charges_second_dec) {
		this.charges_second_dec = charges_second_dec;
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

	public String getDate_table_no_994() {
		return date_table_no_994;
	}

	public void setDate_table_no_994(String date_table_no_994) {
		this.date_table_no_994 = date_table_no_994;
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

	public String getCharges_second_cur() {
		return charges_second_cur;
	}

	public void setCharges_second_cur(String charges_second_cur) {
		this.charges_second_cur = charges_second_cur;
	}

	public String getDate_start_mm() {
		return date_start_mm;
	}

	public void setDate_start_mm(String date_start_mm) {
		this.date_start_mm = date_start_mm;
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

	public String getRec_type() {
		return rec_type;
	}

	public void setRec_type(String rec_type) {
		this.rec_type = rec_type;
	}

	public String getCharges_first_dec() {
		return charges_first_dec;
	}

	public void setCharges_first_dec(String charges_first_dec) {
		this.charges_first_dec = charges_first_dec;
	}

	public Decimal128 getCharges_first_amt() {
		return charges_first_amt;
	}

	public void setCharges_first_amt(Decimal128 charges_first_amt) {
		this.charges_first_amt = charges_first_amt;
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
		result = prime * result + ((and_geo_tbl_995 == null) ? 0 : and_geo_tbl_995.hashCode());
		result = prime * result + ((appl == null) ? 0 : appl.hashCode());
		result = prime * result + ((btw_geo_tbl_995 == null) ? 0 : btw_geo_tbl_995.hashCode());
		result = prime * result + ((cat_no == null) ? 0 : cat_no.hashCode());
		result = prime * result + ((charges_appl == null) ? 0 : charges_appl.hashCode());
		result = prime * result + ((charges_base == null) ? 0 : charges_base.hashCode());
		result = prime * result + ((charges_first_amt == null) ? 0 : charges_first_amt.hashCode());
		result = prime * result + ((charges_first_cur == null) ? 0 : charges_first_cur.hashCode());
		result = prime * result + ((charges_first_dec == null) ? 0 : charges_first_dec.hashCode());
		result = prime * result + ((charges_percent == null) ? 0 : charges_percent.hashCode());
		result = prime * result + ((charges_second_amt == null) ? 0 : charges_second_amt.hashCode());
		result = prime * result + ((charges_second_cur == null) ? 0 : charges_second_cur.hashCode());
		result = prime * result + ((charges_second_dec == null) ? 0 : charges_second_dec.hashCode());
		result = prime * result + ((cxr_flt_tbl_no_986 == null) ? 0 : cxr_flt_tbl_no_986.hashCode());
		result = prime * result + ((date_start_dd == null) ? 0 : date_start_dd.hashCode());
		result = prime * result + ((date_start_mm == null) ? 0 : date_start_mm.hashCode());
		result = prime * result + ((date_start_yy == null) ? 0 : date_start_yy.hashCode());
		result = prime * result + ((date_stop_dd == null) ? 0 : date_stop_dd.hashCode());
		result = prime * result + ((date_stop_mm == null) ? 0 : date_stop_mm.hashCode());
		result = prime * result + ((date_stop_yy == null) ? 0 : date_stop_yy.hashCode());
		result = prime * result + ((date_table_no_994 == null) ? 0 : date_table_no_994.hashCode());
		result = prime * result + ((day_of_week == null) ? 0 : day_of_week.hashCode());
		result = prime * result + ((eqpt == null) ? 0 : eqpt.hashCode());
		result = prime * result + ((from_to_via == null) ? 0 : from_to_via.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((rbd == null) ? 0 : rbd.hashCode());
		result = prime * result + ((rec_type == null) ? 0 : rec_type.hashCode());
		result = prime * result + ((record_batch == null) ? 0 : record_batch.hashCode());
		result = prime * result + ((record_sequence == null) ? 0 : record_sequence.hashCode());
		result = prime * result + ((rules_type == null) ? 0 : rules_type.hashCode());
		result = prime * result + ((sect_prt == null) ? 0 : sect_prt.hashCode());
		result = prime * result + ((sur_type == null) ? 0 : sur_type.hashCode());
		result = prime * result + ((surcharge_appl == null) ? 0 : surcharge_appl.hashCode());
		result = prime * result + ((tbl_no == null) ? 0 : tbl_no.hashCode());
		result = prime * result + ((text_table_no_996 == null) ? 0 : text_table_no_996.hashCode());
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
		AtpcoRecord3Cat12 other = (AtpcoRecord3Cat12) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (and_geo_tbl_995 == null) {
			if (other.and_geo_tbl_995 != null)
				return false;
		} else if (!and_geo_tbl_995.equals(other.and_geo_tbl_995))
			return false;
		if (appl == null) {
			if (other.appl != null)
				return false;
		} else if (!appl.equals(other.appl))
			return false;
		if (btw_geo_tbl_995 == null) {
			if (other.btw_geo_tbl_995 != null)
				return false;
		} else if (!btw_geo_tbl_995.equals(other.btw_geo_tbl_995))
			return false;
		if (cat_no == null) {
			if (other.cat_no != null)
				return false;
		} else if (!cat_no.equals(other.cat_no))
			return false;
		if (charges_appl == null) {
			if (other.charges_appl != null)
				return false;
		} else if (!charges_appl.equals(other.charges_appl))
			return false;
		if (charges_base == null) {
			if (other.charges_base != null)
				return false;
		} else if (!charges_base.equals(other.charges_base))
			return false;
		if (charges_first_amt == null) {
			if (other.charges_first_amt != null)
				return false;
		} else if (!charges_first_amt.equals(other.charges_first_amt))
			return false;
		if (charges_first_cur == null) {
			if (other.charges_first_cur != null)
				return false;
		} else if (!charges_first_cur.equals(other.charges_first_cur))
			return false;
		if (charges_first_dec == null) {
			if (other.charges_first_dec != null)
				return false;
		} else if (!charges_first_dec.equals(other.charges_first_dec))
			return false;
		if (charges_percent == null) {
			if (other.charges_percent != null)
				return false;
		} else if (!charges_percent.equals(other.charges_percent))
			return false;
		if (charges_second_amt == null) {
			if (other.charges_second_amt != null)
				return false;
		} else if (!charges_second_amt.equals(other.charges_second_amt))
			return false;
		if (charges_second_cur == null) {
			if (other.charges_second_cur != null)
				return false;
		} else if (!charges_second_cur.equals(other.charges_second_cur))
			return false;
		if (charges_second_dec == null) {
			if (other.charges_second_dec != null)
				return false;
		} else if (!charges_second_dec.equals(other.charges_second_dec))
			return false;
		if (cxr_flt_tbl_no_986 == null) {
			if (other.cxr_flt_tbl_no_986 != null)
				return false;
		} else if (!cxr_flt_tbl_no_986.equals(other.cxr_flt_tbl_no_986))
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
		if (date_table_no_994 == null) {
			if (other.date_table_no_994 != null)
				return false;
		} else if (!date_table_no_994.equals(other.date_table_no_994))
			return false;
		if (day_of_week == null) {
			if (other.day_of_week != null)
				return false;
		} else if (!day_of_week.equals(other.day_of_week))
			return false;
		if (eqpt == null) {
			if (other.eqpt != null)
				return false;
		} else if (!eqpt.equals(other.eqpt))
			return false;
		if (from_to_via == null) {
			if (other.from_to_via != null)
				return false;
		} else if (!from_to_via.equals(other.from_to_via))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (rbd == null) {
			if (other.rbd != null)
				return false;
		} else if (!rbd.equals(other.rbd))
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
		if (sect_prt == null) {
			if (other.sect_prt != null)
				return false;
		} else if (!sect_prt.equals(other.sect_prt))
			return false;
		if (sur_type == null) {
			if (other.sur_type != null)
				return false;
		} else if (!sur_type.equals(other.sur_type))
			return false;
		if (surcharge_appl == null) {
			if (other.surcharge_appl != null)
				return false;
		} else if (!surcharge_appl.equals(other.surcharge_appl))
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
		return "AtpcoRecord3Cat12 [id=" + id + ", sect_prt=" + sect_prt + ", text_table_no_996=" + text_table_no_996
				+ ", record_sequence=" + record_sequence + ", date_start_dd=" + date_start_dd + ", btw_geo_tbl_995="
				+ btw_geo_tbl_995 + ", from_to_via=" + from_to_via + ", day_of_week=" + day_of_week + ", tbl_no="
				+ tbl_no + ", sur_type=" + sur_type + ", cxr_flt_tbl_no_986=" + cxr_flt_tbl_no_986 + ", unavail="
				+ unavail + ", date_stop_yy=" + date_stop_yy + ", action=" + action + ", rules_type=" + rules_type
				+ ", surcharge_appl=" + surcharge_appl + ", and_geo_tbl_995=" + and_geo_tbl_995 + ", rbd=" + rbd
				+ ", charges_percent=" + charges_percent + ", charges_appl=" + charges_appl + ", date_stop_dd="
				+ date_stop_dd + ", charges_second_amt=" + charges_second_amt + ", charges_base=" + charges_base
				+ ", charges_first_cur=" + charges_first_cur + ", eqpt=" + eqpt + ", charges_second_dec="
				+ charges_second_dec + ", date_start_yy=" + date_start_yy + ", record_batch=" + record_batch
				+ ", date_table_no_994=" + date_table_no_994 + ", date_stop_mm=" + date_stop_mm + ", appl=" + appl
				+ ", charges_second_cur=" + charges_second_cur + ", date_start_mm=" + date_start_mm + ", cat_no="
				+ cat_no + ", time_of_day_stop=" + time_of_day_stop + ", rec_type=" + rec_type + ", charges_first_dec="
				+ charges_first_dec + ", charges_first_amt=" + charges_first_amt + ", time_of_day_start="
				+ time_of_day_start + "]";
	}

	
}
