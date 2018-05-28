 package com.atibusinessgroup.fmp.domain.atpco;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_record_3_cat_008")
public class AtpcoRecord3Cat08 {

	@Id
    private String id;

	@Field("rules_type")
    private String rules_type;

	@Field("record_batch")
    private String record_batch;

	@Field("record_sequence")
    private String record_sequence;

	@Field("rec_type")
    private String rec_type;

	@Field("action")
    private String action;

	@Field("cat_no")
    private String cat_no;

	@Field("tbl_no")
    private String tbl_no;

	@Field("no_of_stops_min")
    private String no_of_stops_min;

	@Field("no_of_stops_max")
    private String no_of_stops_max;

	@Field("no_of_stops_out")
    private String no_of_stops_out;

	@Field("no_of_stops_in")
    private String no_of_stops_in;

	@Field("no_of_stops_or")
    private String no_of_stops_or;

	@Field("no_of_stops_filler_1")
    private String no_of_stops_filler_1;

	@Field("oj")
    private String oj;

	@Field("ct2")
    private String ct2;

	@Field("ct2_plus")
    private String ct2_plus;

	@Field("gtwy_perm")
    private String gtwy_perm;

	@Field("gtwy_geo_tbl_995")
    private String gtwy_geo_tbl_995;

	@Field("time_min")
    private String time_min;

	@Field("time_unit_min")
    private String time_unit_min;
	
	@Field("time_max")
	private String time_max;

	@Field("time_unit_max")
    private String time_unit_max;

	@Field("same_point_stp")
    private String same_point_stp;

	@Field("same_point_cnx")
    private String same_point_cnx;

	@Field("same_point_table_993")
    private String same_point_table_993;

	@Field("charges_1_appl")
    private String charges_1_appl;

	@Field("charges_1_filler_1")
    private String charges_1_filler_1;

	@Field("charges_1_first_no")
    private String charges_1_first_no;

	@Field("charges_1_first_amt")
    private BigDecimal charges_1_first_amt;

	@Field("charges_1_addl_no")
    private String charges_1_addl_no;

	@Field("charges_1_addl_amt")
    private BigDecimal charges_1_addl_amt;
    
	@Field("charges_1_cur")
    private String charges_1_cur;

	@Field("charges_1_dec")
    private String charges_1_dec;

	@Field("charges_2_first_filler_1")
    private String charges_2_first_filler_1;

	@Field("charges_2_first_amt")
    private BigDecimal charges_2_first_amt;

	@Field("charges_2_addl_filler_1")
    private String charges_2_addl_filler_1;

	@Field("charges_2_addl_amt")
    private BigDecimal charges_2_addl_amt;

	@Field("charges_2_addl_cur")
    private String charges_2_addl_cur;

	@Field("charges_2_dec")
    private String charges_2_dec;

	@Field("date_tbl_no_994")
    private String date_tbl_no_994;

	@Field("text_tbl_no_996")
    private String text_tbl_no_996;
    
	@Field("unavail")
    private String unavail;

	@Field("stopovers")
    private List<AtpcoRecord3Cat08Stopovers> stopovers = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getRecord_sequence() {
		return record_sequence;
	}

	public void setRecord_sequence(String record_sequence) {
		this.record_sequence = record_sequence;
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

	public String getCat_no() {
		return cat_no;
	}

	public void setCat_no(String cat_no) {
		this.cat_no = cat_no;
	}

	public String getTbl_no() {
		return tbl_no;
	}

	public void setTbl_no(String tbl_no) {
		this.tbl_no = tbl_no;
	}

	public String getNo_of_stops_min() {
		return no_of_stops_min;
	}

	public void setNo_of_stops_min(String no_of_stops_min) {
		this.no_of_stops_min = no_of_stops_min;
	}

	public String getNo_of_stops_max() {
		return no_of_stops_max;
	}

	public void setNo_of_stops_max(String no_of_stops_max) {
		this.no_of_stops_max = no_of_stops_max;
	}

	public String getNo_of_stops_out() {
		return no_of_stops_out;
	}

	public void setNo_of_stops_out(String no_of_stops_out) {
		this.no_of_stops_out = no_of_stops_out;
	}

	public String getNo_of_stops_in() {
		return no_of_stops_in;
	}

	public void setNo_of_stops_in(String no_of_stops_in) {
		this.no_of_stops_in = no_of_stops_in;
	}

	public String getNo_of_stops_or() {
		return no_of_stops_or;
	}

	public void setNo_of_stops_or(String no_of_stops_or) {
		this.no_of_stops_or = no_of_stops_or;
	}

	public String getNo_of_stops_filler_1() {
		return no_of_stops_filler_1;
	}

	public void setNo_of_stops_filler_1(String no_of_stops_filler_1) {
		this.no_of_stops_filler_1 = no_of_stops_filler_1;
	}

	public String getOj() {
		return oj;
	}

	public void setOj(String oj) {
		this.oj = oj;
	}

	public String getCt2() {
		return ct2;
	}

	public void setCt2(String ct2) {
		this.ct2 = ct2;
	}

	public String getCt2_plus() {
		return ct2_plus;
	}

	public void setCt2_plus(String ct2_plus) {
		this.ct2_plus = ct2_plus;
	}

	public String getGtwy_perm() {
		return gtwy_perm;
	}

	public void setGtwy_perm(String gtwy_perm) {
		this.gtwy_perm = gtwy_perm;
	}

	public String getGtwy_geo_tbl_995() {
		return gtwy_geo_tbl_995;
	}

	public void setGtwy_geo_tbl_995(String gtwy_geo_tbl_995) {
		this.gtwy_geo_tbl_995 = gtwy_geo_tbl_995;
	}

	public String getTime_min() {
		return time_min;
	}

	public void setTime_min(String time_min) {
		this.time_min = time_min;
	}

	public String getTime_unit_min() {
		return time_unit_min;
	}

	public void setTime_unit_min(String time_unit_min) {
		this.time_unit_min = time_unit_min;
	}

	public String getTime_max() {
		return time_max;
	}

	public void setTime_max(String time_max) {
		this.time_max = time_max;
	}

	public String getTime_unit_max() {
		return time_unit_max;
	}

	public void setTime_unit_max(String time_unit_max) {
		this.time_unit_max = time_unit_max;
	}

	public String getSame_point_stp() {
		return same_point_stp;
	}

	public void setSame_point_stp(String same_point_stp) {
		this.same_point_stp = same_point_stp;
	}

	public String getSame_point_cnx() {
		return same_point_cnx;
	}

	public void setSame_point_cnx(String same_point_cnx) {
		this.same_point_cnx = same_point_cnx;
	}

	public String getSame_point_table_993() {
		return same_point_table_993;
	}

	public void setSame_point_table_993(String same_point_table_993) {
		this.same_point_table_993 = same_point_table_993;
	}

	public String getCharges_1_appl() {
		return charges_1_appl;
	}

	public void setCharges_1_appl(String charges_1_appl) {
		this.charges_1_appl = charges_1_appl;
	}

	public String getCharges_1_filler_1() {
		return charges_1_filler_1;
	}

	public void setCharges_1_filler_1(String charges_1_filler_1) {
		this.charges_1_filler_1 = charges_1_filler_1;
	}

	public String getCharges_1_first_no() {
		return charges_1_first_no;
	}

	public void setCharges_1_first_no(String charges_1_first_no) {
		this.charges_1_first_no = charges_1_first_no;
	}

	public BigDecimal getCharges_1_first_amt() {
		return charges_1_first_amt;
	}

	public void setCharges_1_first_amt(BigDecimal charges_1_first_amt) {
		this.charges_1_first_amt = charges_1_first_amt;
	}

	public String getCharges_1_addl_no() {
		return charges_1_addl_no;
	}

	public void setCharges_1_addl_no(String charges_1_addl_no) {
		this.charges_1_addl_no = charges_1_addl_no;
	}

	public BigDecimal getCharges_1_addl_amt() {
		return charges_1_addl_amt;
	}

	public void setCharges_1_addl_amt(BigDecimal charges_1_addl_amt) {
		this.charges_1_addl_amt = charges_1_addl_amt;
	}

	public String getCharges_1_cur() {
		return charges_1_cur;
	}

	public void setCharges_1_cur(String charges_1_cur) {
		this.charges_1_cur = charges_1_cur;
	}

	public String getCharges_1_dec() {
		return charges_1_dec;
	}

	public void setCharges_1_dec(String charges_1_dec) {
		this.charges_1_dec = charges_1_dec;
	}

	public String getCharges_2_first_filler_1() {
		return charges_2_first_filler_1;
	}

	public void setCharges_2_first_filler_1(String charges_2_first_filler_1) {
		this.charges_2_first_filler_1 = charges_2_first_filler_1;
	}

	public BigDecimal getCharges_2_first_amt() {
		return charges_2_first_amt;
	}

	public void setCharges_2_first_amt(BigDecimal charges_2_first_amt) {
		this.charges_2_first_amt = charges_2_first_amt;
	}

	public String getCharges_2_addl_filler_1() {
		return charges_2_addl_filler_1;
	}

	public void setCharges_2_addl_filler_1(String charges_2_addl_filler_1) {
		this.charges_2_addl_filler_1 = charges_2_addl_filler_1;
	}

	public BigDecimal getCharges_2_addl_amt() {
		return charges_2_addl_amt;
	}

	public void setCharges_2_addl_amt(BigDecimal charges_2_addl_amt) {
		this.charges_2_addl_amt = charges_2_addl_amt;
	}

	public String getCharges_2_addl_cur() {
		return charges_2_addl_cur;
	}

	public void setCharges_2_addl_cur(String charges_2_addl_cur) {
		this.charges_2_addl_cur = charges_2_addl_cur;
	}

	public String getCharges_2_dec() {
		return charges_2_dec;
	}

	public void setCharges_2_dec(String charges_2_dec) {
		this.charges_2_dec = charges_2_dec;
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

	public String getUnavail() {
		return unavail;
	}

	public void setUnavail(String unavail) {
		this.unavail = unavail;
	}

	public List<AtpcoRecord3Cat08Stopovers> getStopovers() {
		return stopovers;
	}

	public void setStopovers(List<AtpcoRecord3Cat08Stopovers> stopovers) {
		this.stopovers = stopovers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((cat_no == null) ? 0 : cat_no.hashCode());
		result = prime * result + ((charges_1_addl_amt == null) ? 0 : charges_1_addl_amt.hashCode());
		result = prime * result + ((charges_1_addl_no == null) ? 0 : charges_1_addl_no.hashCode());
		result = prime * result + ((charges_1_appl == null) ? 0 : charges_1_appl.hashCode());
		result = prime * result + ((charges_1_cur == null) ? 0 : charges_1_cur.hashCode());
		result = prime * result + ((charges_1_dec == null) ? 0 : charges_1_dec.hashCode());
		result = prime * result + ((charges_1_filler_1 == null) ? 0 : charges_1_filler_1.hashCode());
		result = prime * result + ((charges_1_first_amt == null) ? 0 : charges_1_first_amt.hashCode());
		result = prime * result + ((charges_1_first_no == null) ? 0 : charges_1_first_no.hashCode());
		result = prime * result + ((charges_2_addl_amt == null) ? 0 : charges_2_addl_amt.hashCode());
		result = prime * result + ((charges_2_addl_cur == null) ? 0 : charges_2_addl_cur.hashCode());
		result = prime * result + ((charges_2_addl_filler_1 == null) ? 0 : charges_2_addl_filler_1.hashCode());
		result = prime * result + ((charges_2_dec == null) ? 0 : charges_2_dec.hashCode());
		result = prime * result + ((charges_2_first_amt == null) ? 0 : charges_2_first_amt.hashCode());
		result = prime * result + ((charges_2_first_filler_1 == null) ? 0 : charges_2_first_filler_1.hashCode());
		result = prime * result + ((ct2 == null) ? 0 : ct2.hashCode());
		result = prime * result + ((ct2_plus == null) ? 0 : ct2_plus.hashCode());
		result = prime * result + ((date_tbl_no_994 == null) ? 0 : date_tbl_no_994.hashCode());
		result = prime * result + ((gtwy_geo_tbl_995 == null) ? 0 : gtwy_geo_tbl_995.hashCode());
		result = prime * result + ((gtwy_perm == null) ? 0 : gtwy_perm.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((no_of_stops_filler_1 == null) ? 0 : no_of_stops_filler_1.hashCode());
		result = prime * result + ((no_of_stops_in == null) ? 0 : no_of_stops_in.hashCode());
		result = prime * result + ((no_of_stops_max == null) ? 0 : no_of_stops_max.hashCode());
		result = prime * result + ((no_of_stops_min == null) ? 0 : no_of_stops_min.hashCode());
		result = prime * result + ((no_of_stops_or == null) ? 0 : no_of_stops_or.hashCode());
		result = prime * result + ((no_of_stops_out == null) ? 0 : no_of_stops_out.hashCode());
		result = prime * result + ((oj == null) ? 0 : oj.hashCode());
		result = prime * result + ((rec_type == null) ? 0 : rec_type.hashCode());
		result = prime * result + ((record_batch == null) ? 0 : record_batch.hashCode());
		result = prime * result + ((record_sequence == null) ? 0 : record_sequence.hashCode());
		result = prime * result + ((rules_type == null) ? 0 : rules_type.hashCode());
		result = prime * result + ((same_point_cnx == null) ? 0 : same_point_cnx.hashCode());
		result = prime * result + ((same_point_stp == null) ? 0 : same_point_stp.hashCode());
		result = prime * result + ((same_point_table_993 == null) ? 0 : same_point_table_993.hashCode());
		result = prime * result + ((stopovers == null) ? 0 : stopovers.hashCode());
		result = prime * result + ((tbl_no == null) ? 0 : tbl_no.hashCode());
		result = prime * result + ((text_tbl_no_996 == null) ? 0 : text_tbl_no_996.hashCode());
		result = prime * result + ((time_max == null) ? 0 : time_max.hashCode());
		result = prime * result + ((time_min == null) ? 0 : time_min.hashCode());
		result = prime * result + ((time_unit_max == null) ? 0 : time_unit_max.hashCode());
		result = prime * result + ((time_unit_min == null) ? 0 : time_unit_min.hashCode());
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
		AtpcoRecord3Cat08 other = (AtpcoRecord3Cat08) obj;
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
		if (charges_1_addl_amt == null) {
			if (other.charges_1_addl_amt != null)
				return false;
		} else if (!charges_1_addl_amt.equals(other.charges_1_addl_amt))
			return false;
		if (charges_1_addl_no == null) {
			if (other.charges_1_addl_no != null)
				return false;
		} else if (!charges_1_addl_no.equals(other.charges_1_addl_no))
			return false;
		if (charges_1_appl == null) {
			if (other.charges_1_appl != null)
				return false;
		} else if (!charges_1_appl.equals(other.charges_1_appl))
			return false;
		if (charges_1_cur == null) {
			if (other.charges_1_cur != null)
				return false;
		} else if (!charges_1_cur.equals(other.charges_1_cur))
			return false;
		if (charges_1_dec == null) {
			if (other.charges_1_dec != null)
				return false;
		} else if (!charges_1_dec.equals(other.charges_1_dec))
			return false;
		if (charges_1_filler_1 == null) {
			if (other.charges_1_filler_1 != null)
				return false;
		} else if (!charges_1_filler_1.equals(other.charges_1_filler_1))
			return false;
		if (charges_1_first_amt == null) {
			if (other.charges_1_first_amt != null)
				return false;
		} else if (!charges_1_first_amt.equals(other.charges_1_first_amt))
			return false;
		if (charges_1_first_no == null) {
			if (other.charges_1_first_no != null)
				return false;
		} else if (!charges_1_first_no.equals(other.charges_1_first_no))
			return false;
		if (charges_2_addl_amt == null) {
			if (other.charges_2_addl_amt != null)
				return false;
		} else if (!charges_2_addl_amt.equals(other.charges_2_addl_amt))
			return false;
		if (charges_2_addl_cur == null) {
			if (other.charges_2_addl_cur != null)
				return false;
		} else if (!charges_2_addl_cur.equals(other.charges_2_addl_cur))
			return false;
		if (charges_2_addl_filler_1 == null) {
			if (other.charges_2_addl_filler_1 != null)
				return false;
		} else if (!charges_2_addl_filler_1.equals(other.charges_2_addl_filler_1))
			return false;
		if (charges_2_dec == null) {
			if (other.charges_2_dec != null)
				return false;
		} else if (!charges_2_dec.equals(other.charges_2_dec))
			return false;
		if (charges_2_first_amt == null) {
			if (other.charges_2_first_amt != null)
				return false;
		} else if (!charges_2_first_amt.equals(other.charges_2_first_amt))
			return false;
		if (charges_2_first_filler_1 == null) {
			if (other.charges_2_first_filler_1 != null)
				return false;
		} else if (!charges_2_first_filler_1.equals(other.charges_2_first_filler_1))
			return false;
		if (ct2 == null) {
			if (other.ct2 != null)
				return false;
		} else if (!ct2.equals(other.ct2))
			return false;
		if (ct2_plus == null) {
			if (other.ct2_plus != null)
				return false;
		} else if (!ct2_plus.equals(other.ct2_plus))
			return false;
		if (date_tbl_no_994 == null) {
			if (other.date_tbl_no_994 != null)
				return false;
		} else if (!date_tbl_no_994.equals(other.date_tbl_no_994))
			return false;
		if (gtwy_geo_tbl_995 == null) {
			if (other.gtwy_geo_tbl_995 != null)
				return false;
		} else if (!gtwy_geo_tbl_995.equals(other.gtwy_geo_tbl_995))
			return false;
		if (gtwy_perm == null) {
			if (other.gtwy_perm != null)
				return false;
		} else if (!gtwy_perm.equals(other.gtwy_perm))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (no_of_stops_filler_1 == null) {
			if (other.no_of_stops_filler_1 != null)
				return false;
		} else if (!no_of_stops_filler_1.equals(other.no_of_stops_filler_1))
			return false;
		if (no_of_stops_in == null) {
			if (other.no_of_stops_in != null)
				return false;
		} else if (!no_of_stops_in.equals(other.no_of_stops_in))
			return false;
		if (no_of_stops_max == null) {
			if (other.no_of_stops_max != null)
				return false;
		} else if (!no_of_stops_max.equals(other.no_of_stops_max))
			return false;
		if (no_of_stops_min == null) {
			if (other.no_of_stops_min != null)
				return false;
		} else if (!no_of_stops_min.equals(other.no_of_stops_min))
			return false;
		if (no_of_stops_or == null) {
			if (other.no_of_stops_or != null)
				return false;
		} else if (!no_of_stops_or.equals(other.no_of_stops_or))
			return false;
		if (no_of_stops_out == null) {
			if (other.no_of_stops_out != null)
				return false;
		} else if (!no_of_stops_out.equals(other.no_of_stops_out))
			return false;
		if (oj == null) {
			if (other.oj != null)
				return false;
		} else if (!oj.equals(other.oj))
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
		if (same_point_cnx == null) {
			if (other.same_point_cnx != null)
				return false;
		} else if (!same_point_cnx.equals(other.same_point_cnx))
			return false;
		if (same_point_stp == null) {
			if (other.same_point_stp != null)
				return false;
		} else if (!same_point_stp.equals(other.same_point_stp))
			return false;
		if (same_point_table_993 == null) {
			if (other.same_point_table_993 != null)
				return false;
		} else if (!same_point_table_993.equals(other.same_point_table_993))
			return false;
		if (stopovers == null) {
			if (other.stopovers != null)
				return false;
		} else if (!stopovers.equals(other.stopovers))
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
		if (time_max == null) {
			if (other.time_max != null)
				return false;
		} else if (!time_max.equals(other.time_max))
			return false;
		if (time_min == null) {
			if (other.time_min != null)
				return false;
		} else if (!time_min.equals(other.time_min))
			return false;
		if (time_unit_max == null) {
			if (other.time_unit_max != null)
				return false;
		} else if (!time_unit_max.equals(other.time_unit_max))
			return false;
		if (time_unit_min == null) {
			if (other.time_unit_min != null)
				return false;
		} else if (!time_unit_min.equals(other.time_unit_min))
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
		return "AtpcoRecord3Cat08 [id=" + id + ", rules_type=" + rules_type + ", record_batch=" + record_batch
				+ ", record_sequence=" + record_sequence + ", rec_type=" + rec_type + ", action=" + action + ", cat_no="
				+ cat_no + ", tbl_no=" + tbl_no + ", no_of_stops_min=" + no_of_stops_min + ", no_of_stops_max="
				+ no_of_stops_max + ", no_of_stops_out=" + no_of_stops_out + ", no_of_stops_in=" + no_of_stops_in
				+ ", no_of_stops_or=" + no_of_stops_or + ", no_of_stops_filler_1=" + no_of_stops_filler_1 + ", oj=" + oj
				+ ", ct2=" + ct2 + ", ct2_plus=" + ct2_plus + ", gtwy_perm=" + gtwy_perm + ", gtwy_geo_tbl_995="
				+ gtwy_geo_tbl_995 + ", time_min=" + time_min + ", time_unit_min=" + time_unit_min + ", time_max="
				+ time_max + ", time_unit_max=" + time_unit_max + ", same_point_stp=" + same_point_stp
				+ ", same_point_cnx=" + same_point_cnx + ", same_point_table_993=" + same_point_table_993
				+ ", charges_1_appl=" + charges_1_appl + ", charges_1_filler_1=" + charges_1_filler_1
				+ ", charges_1_first_no=" + charges_1_first_no + ", charges_1_first_amt=" + charges_1_first_amt
				+ ", charges_1_addl_no=" + charges_1_addl_no + ", charges_1_addl_amt=" + charges_1_addl_amt
				+ ", charges_1_cur=" + charges_1_cur + ", charges_1_dec=" + charges_1_dec
				+ ", charges_2_first_filler_1=" + charges_2_first_filler_1 + ", charges_2_first_amt="
				+ charges_2_first_amt + ", charges_2_addl_filler_1=" + charges_2_addl_filler_1 + ", charges_2_addl_amt="
				+ charges_2_addl_amt + ", charges_2_addl_cur=" + charges_2_addl_cur + ", charges_2_dec=" + charges_2_dec
				+ ", date_tbl_no_994=" + date_tbl_no_994 + ", text_tbl_no_996=" + text_tbl_no_996 + ", unavail="
				+ unavail + ", stopovers=" + stopovers + "]";
	}
	
	
}
