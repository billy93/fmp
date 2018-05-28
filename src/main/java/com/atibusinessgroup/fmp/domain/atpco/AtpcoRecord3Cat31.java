package com.atibusinessgroup.fmp.domain.atpco;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_record_3_cat_031")
public class AtpcoRecord3Cat31 {

	@Id
    private String id;
	
	@Field("reis_tbl_no_988")
	private String reis_tbl_no_988;

	@Field("record_sequence")
    private String record_sequence;

	@Field("tkt_val")
    private String tkt_val;

	@Field("cur_1")
    private String cur_1;

	@Field("cur_2")
    private String cur_2;

	@Field("h_l")
    private String h_l;

	@Field("ptc")
    private String ptc;

	@Field("disc")
    private String disc;

	@Field("chg_ind")
    private String chg_ind;

	@Field("typ_tkt")
    private String typ_tkt;

	@Field("fee_appl")
    private String fee_appl;

	@Field("adv_rsvn_to_pt")
    private String adv_rsvn_to_pt;

	@Field("adv_rsvn_from_pt")
    private String adv_rsvn_from_pt;

	@Field("psgr_occ_first")
    private String psgr_occ_first;

	@Field("tbl_no")
    private String tbl_no;

	@Field("unavail")
    private String unavail;

	@Field("dom_intl_comb")
    private String dom_intl_comb;

	@Field("action")
    private String action;

	@Field("rules_type")
    private String rules_type;

	@Field("adv_rsvn_reserved_1")
    private String adv_rsvn_reserved_1;

	@Field("end")
    private String end;

	@Field("from_of_ref")
    private String from_of_ref;

	@Field("jny")
    private String jny;

	@Field("res_pen")
    private String res_pen;

	@Field("f_cmp")
    private String f_cmp;

	@Field("min_cur")
    private String min_cur;

	@Field("adv_rsvn_period")
    private String adv_rsvn_period;

	@Field("date_tbl_no_994")
    private String date_tbl_no_994;

	@Field("text_tbl_no_996")
    private String text_tbl_no_996;

	@Field("record_batch")
    private String record_batch;

	@Field("amt_1")
    private BigDecimal amt_1;

	@Field("amt_2")
    private BigDecimal amt_2;

	@Field("psgr_occ_last")
    private String psgr_occ_last;

	@Field("reserved_4")
    private String reserved_4;

	@Field("adv_rsvn_unit")
    private String adv_rsvn_unit;

	@Field("reserved_3")
    private String reserved_3;

	@Field("cat_no")
    private String cat_no;

	@Field("min_amt")
    private BigDecimal min_amt;

	@Field("reserved_5")
    private String reserved_5;

	@Field("fee_appl_j_pu_fc")
    private String fee_appl_j_pu_fc;

	@Field("per")
    private String per;

	@Field("res_pen_hierarchy")
    private String res_pen_hierarchy;

	@Field("reserved_2")
    private String reserved_2;

	@Field("rec_type")
    private String rec_type;

	@Field("reserved_1")
    private String reserved_1;

	@Field("dec_1")
    private String dec_1;

	@Field("dom_intl_comb_cxr_tbl_no_990")
    private String dom_intl_comb_cxr_tbl_no_990;

	@Field("dec_2")
    private String dec_2;

	@Field("pu")
    private String pu;

	@Field("waiv_tbl_no_987")
    private String waiv_tbl_no_987;

	@Field("min_dec")
    private String min_dec;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReis_tbl_no_988() {
		return reis_tbl_no_988;
	}

	public void setReis_tbl_no_988(String reis_tbl_no_988) {
		this.reis_tbl_no_988 = reis_tbl_no_988;
	}

	public String getRecord_sequence() {
		return record_sequence;
	}

	public void setRecord_sequence(String record_sequence) {
		this.record_sequence = record_sequence;
	}

	public String getTkt_val() {
		return tkt_val;
	}

	public void setTkt_val(String tkt_val) {
		this.tkt_val = tkt_val;
	}

	public String getCur_1() {
		return cur_1;
	}

	public void setCur_1(String cur_1) {
		this.cur_1 = cur_1;
	}

	public String getCur_2() {
		return cur_2;
	}

	public void setCur_2(String cur_2) {
		this.cur_2 = cur_2;
	}

	public String getH_l() {
		return h_l;
	}

	public void setH_l(String h_l) {
		this.h_l = h_l;
	}

	public String getPtc() {
		return ptc;
	}

	public void setPtc(String ptc) {
		this.ptc = ptc;
	}

	public String getDisc() {
		return disc;
	}

	public void setDisc(String disc) {
		this.disc = disc;
	}

	public String getChg_ind() {
		return chg_ind;
	}

	public void setChg_ind(String chg_ind) {
		this.chg_ind = chg_ind;
	}

	public String getTyp_tkt() {
		return typ_tkt;
	}

	public void setTyp_tkt(String typ_tkt) {
		this.typ_tkt = typ_tkt;
	}

	public String getFee_appl() {
		return fee_appl;
	}

	public void setFee_appl(String fee_appl) {
		this.fee_appl = fee_appl;
	}

	public String getAdv_rsvn_to_pt() {
		return adv_rsvn_to_pt;
	}

	public void setAdv_rsvn_to_pt(String adv_rsvn_to_pt) {
		this.adv_rsvn_to_pt = adv_rsvn_to_pt;
	}

	public String getAdv_rsvn_from_pt() {
		return adv_rsvn_from_pt;
	}

	public void setAdv_rsvn_from_pt(String adv_rsvn_from_pt) {
		this.adv_rsvn_from_pt = adv_rsvn_from_pt;
	}

	public String getPsgr_occ_first() {
		return psgr_occ_first;
	}

	public void setPsgr_occ_first(String psgr_occ_first) {
		this.psgr_occ_first = psgr_occ_first;
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

	public String getDom_intl_comb() {
		return dom_intl_comb;
	}

	public void setDom_intl_comb(String dom_intl_comb) {
		this.dom_intl_comb = dom_intl_comb;
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

	public String getAdv_rsvn_reserved_1() {
		return adv_rsvn_reserved_1;
	}

	public void setAdv_rsvn_reserved_1(String adv_rsvn_reserved_1) {
		this.adv_rsvn_reserved_1 = adv_rsvn_reserved_1;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getFrom_of_ref() {
		return from_of_ref;
	}

	public void setFrom_of_ref(String from_of_ref) {
		this.from_of_ref = from_of_ref;
	}

	public String getJny() {
		return jny;
	}

	public void setJny(String jny) {
		this.jny = jny;
	}

	public String getRes_pen() {
		return res_pen;
	}

	public void setRes_pen(String res_pen) {
		this.res_pen = res_pen;
	}

	public String getF_cmp() {
		return f_cmp;
	}

	public void setF_cmp(String f_cmp) {
		this.f_cmp = f_cmp;
	}

	public String getMin_cur() {
		return min_cur;
	}

	public void setMin_cur(String min_cur) {
		this.min_cur = min_cur;
	}

	public String getAdv_rsvn_period() {
		return adv_rsvn_period;
	}

	public void setAdv_rsvn_period(String adv_rsvn_period) {
		this.adv_rsvn_period = adv_rsvn_period;
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

	public BigDecimal getAmt_1() {
		return amt_1;
	}

	public void setAmt_1(BigDecimal amt_1) {
		this.amt_1 = amt_1;
	}

	public BigDecimal getAmt_2() {
		return amt_2;
	}

	public void setAmt_2(BigDecimal amt_2) {
		this.amt_2 = amt_2;
	}

	public String getPsgr_occ_last() {
		return psgr_occ_last;
	}

	public void setPsgr_occ_last(String psgr_occ_last) {
		this.psgr_occ_last = psgr_occ_last;
	}

	public String getReserved_4() {
		return reserved_4;
	}

	public void setReserved_4(String reserved_4) {
		this.reserved_4 = reserved_4;
	}

	public String getAdv_rsvn_unit() {
		return adv_rsvn_unit;
	}

	public void setAdv_rsvn_unit(String adv_rsvn_unit) {
		this.adv_rsvn_unit = adv_rsvn_unit;
	}

	public String getReserved_3() {
		return reserved_3;
	}

	public void setReserved_3(String reserved_3) {
		this.reserved_3 = reserved_3;
	}

	public String getCat_no() {
		return cat_no;
	}

	public void setCat_no(String cat_no) {
		this.cat_no = cat_no;
	}

	public BigDecimal getMin_amt() {
		return min_amt;
	}

	public void setMin_amt(BigDecimal min_amt) {
		this.min_amt = min_amt;
	}

	public String getReserved_5() {
		return reserved_5;
	}

	public void setReserved_5(String reserved_5) {
		this.reserved_5 = reserved_5;
	}

	public String getFee_appl_j_pu_fc() {
		return fee_appl_j_pu_fc;
	}

	public void setFee_appl_j_pu_fc(String fee_appl_j_pu_fc) {
		this.fee_appl_j_pu_fc = fee_appl_j_pu_fc;
	}

	public String getPer() {
		return per;
	}

	public void setPer(String per) {
		this.per = per;
	}

	public String getRes_pen_hierarchy() {
		return res_pen_hierarchy;
	}

	public void setRes_pen_hierarchy(String res_pen_hierarchy) {
		this.res_pen_hierarchy = res_pen_hierarchy;
	}

	public String getReserved_2() {
		return reserved_2;
	}

	public void setReserved_2(String reserved_2) {
		this.reserved_2 = reserved_2;
	}

	public String getRec_type() {
		return rec_type;
	}

	public void setRec_type(String rec_type) {
		this.rec_type = rec_type;
	}

	public String getReserved_1() {
		return reserved_1;
	}

	public void setReserved_1(String reserved_1) {
		this.reserved_1 = reserved_1;
	}

	public String getDec_1() {
		return dec_1;
	}

	public void setDec_1(String dec_1) {
		this.dec_1 = dec_1;
	}

	public String getDom_intl_comb_cxr_tbl_no_990() {
		return dom_intl_comb_cxr_tbl_no_990;
	}

	public void setDom_intl_comb_cxr_tbl_no_990(String dom_intl_comb_cxr_tbl_no_990) {
		this.dom_intl_comb_cxr_tbl_no_990 = dom_intl_comb_cxr_tbl_no_990;
	}

	public String getDec_2() {
		return dec_2;
	}

	public void setDec_2(String dec_2) {
		this.dec_2 = dec_2;
	}

	public String getPu() {
		return pu;
	}

	public void setPu(String pu) {
		this.pu = pu;
	}

	public String getWaiv_tbl_no_987() {
		return waiv_tbl_no_987;
	}

	public void setWaiv_tbl_no_987(String waiv_tbl_no_987) {
		this.waiv_tbl_no_987 = waiv_tbl_no_987;
	}

	public String getMin_dec() {
		return min_dec;
	}

	public void setMin_dec(String min_dec) {
		this.min_dec = min_dec;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((adv_rsvn_from_pt == null) ? 0 : adv_rsvn_from_pt.hashCode());
		result = prime * result + ((adv_rsvn_period == null) ? 0 : adv_rsvn_period.hashCode());
		result = prime * result + ((adv_rsvn_reserved_1 == null) ? 0 : adv_rsvn_reserved_1.hashCode());
		result = prime * result + ((adv_rsvn_to_pt == null) ? 0 : adv_rsvn_to_pt.hashCode());
		result = prime * result + ((adv_rsvn_unit == null) ? 0 : adv_rsvn_unit.hashCode());
		result = prime * result + ((amt_1 == null) ? 0 : amt_1.hashCode());
		result = prime * result + ((amt_2 == null) ? 0 : amt_2.hashCode());
		result = prime * result + ((cat_no == null) ? 0 : cat_no.hashCode());
		result = prime * result + ((chg_ind == null) ? 0 : chg_ind.hashCode());
		result = prime * result + ((cur_1 == null) ? 0 : cur_1.hashCode());
		result = prime * result + ((cur_2 == null) ? 0 : cur_2.hashCode());
		result = prime * result + ((date_tbl_no_994 == null) ? 0 : date_tbl_no_994.hashCode());
		result = prime * result + ((dec_1 == null) ? 0 : dec_1.hashCode());
		result = prime * result + ((dec_2 == null) ? 0 : dec_2.hashCode());
		result = prime * result + ((disc == null) ? 0 : disc.hashCode());
		result = prime * result + ((dom_intl_comb == null) ? 0 : dom_intl_comb.hashCode());
		result = prime * result
				+ ((dom_intl_comb_cxr_tbl_no_990 == null) ? 0 : dom_intl_comb_cxr_tbl_no_990.hashCode());
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((f_cmp == null) ? 0 : f_cmp.hashCode());
		result = prime * result + ((fee_appl == null) ? 0 : fee_appl.hashCode());
		result = prime * result + ((fee_appl_j_pu_fc == null) ? 0 : fee_appl_j_pu_fc.hashCode());
		result = prime * result + ((from_of_ref == null) ? 0 : from_of_ref.hashCode());
		result = prime * result + ((h_l == null) ? 0 : h_l.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((jny == null) ? 0 : jny.hashCode());
		result = prime * result + ((min_amt == null) ? 0 : min_amt.hashCode());
		result = prime * result + ((min_cur == null) ? 0 : min_cur.hashCode());
		result = prime * result + ((min_dec == null) ? 0 : min_dec.hashCode());
		result = prime * result + ((per == null) ? 0 : per.hashCode());
		result = prime * result + ((psgr_occ_first == null) ? 0 : psgr_occ_first.hashCode());
		result = prime * result + ((psgr_occ_last == null) ? 0 : psgr_occ_last.hashCode());
		result = prime * result + ((ptc == null) ? 0 : ptc.hashCode());
		result = prime * result + ((pu == null) ? 0 : pu.hashCode());
		result = prime * result + ((rec_type == null) ? 0 : rec_type.hashCode());
		result = prime * result + ((record_batch == null) ? 0 : record_batch.hashCode());
		result = prime * result + ((record_sequence == null) ? 0 : record_sequence.hashCode());
		result = prime * result + ((reis_tbl_no_988 == null) ? 0 : reis_tbl_no_988.hashCode());
		result = prime * result + ((res_pen == null) ? 0 : res_pen.hashCode());
		result = prime * result + ((res_pen_hierarchy == null) ? 0 : res_pen_hierarchy.hashCode());
		result = prime * result + ((reserved_1 == null) ? 0 : reserved_1.hashCode());
		result = prime * result + ((reserved_2 == null) ? 0 : reserved_2.hashCode());
		result = prime * result + ((reserved_3 == null) ? 0 : reserved_3.hashCode());
		result = prime * result + ((reserved_4 == null) ? 0 : reserved_4.hashCode());
		result = prime * result + ((reserved_5 == null) ? 0 : reserved_5.hashCode());
		result = prime * result + ((rules_type == null) ? 0 : rules_type.hashCode());
		result = prime * result + ((tbl_no == null) ? 0 : tbl_no.hashCode());
		result = prime * result + ((text_tbl_no_996 == null) ? 0 : text_tbl_no_996.hashCode());
		result = prime * result + ((tkt_val == null) ? 0 : tkt_val.hashCode());
		result = prime * result + ((typ_tkt == null) ? 0 : typ_tkt.hashCode());
		result = prime * result + ((unavail == null) ? 0 : unavail.hashCode());
		result = prime * result + ((waiv_tbl_no_987 == null) ? 0 : waiv_tbl_no_987.hashCode());
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
		AtpcoRecord3Cat31 other = (AtpcoRecord3Cat31) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (adv_rsvn_from_pt == null) {
			if (other.adv_rsvn_from_pt != null)
				return false;
		} else if (!adv_rsvn_from_pt.equals(other.adv_rsvn_from_pt))
			return false;
		if (adv_rsvn_period == null) {
			if (other.adv_rsvn_period != null)
				return false;
		} else if (!adv_rsvn_period.equals(other.adv_rsvn_period))
			return false;
		if (adv_rsvn_reserved_1 == null) {
			if (other.adv_rsvn_reserved_1 != null)
				return false;
		} else if (!adv_rsvn_reserved_1.equals(other.adv_rsvn_reserved_1))
			return false;
		if (adv_rsvn_to_pt == null) {
			if (other.adv_rsvn_to_pt != null)
				return false;
		} else if (!adv_rsvn_to_pt.equals(other.adv_rsvn_to_pt))
			return false;
		if (adv_rsvn_unit == null) {
			if (other.adv_rsvn_unit != null)
				return false;
		} else if (!adv_rsvn_unit.equals(other.adv_rsvn_unit))
			return false;
		if (amt_1 == null) {
			if (other.amt_1 != null)
				return false;
		} else if (!amt_1.equals(other.amt_1))
			return false;
		if (amt_2 == null) {
			if (other.amt_2 != null)
				return false;
		} else if (!amt_2.equals(other.amt_2))
			return false;
		if (cat_no == null) {
			if (other.cat_no != null)
				return false;
		} else if (!cat_no.equals(other.cat_no))
			return false;
		if (chg_ind == null) {
			if (other.chg_ind != null)
				return false;
		} else if (!chg_ind.equals(other.chg_ind))
			return false;
		if (cur_1 == null) {
			if (other.cur_1 != null)
				return false;
		} else if (!cur_1.equals(other.cur_1))
			return false;
		if (cur_2 == null) {
			if (other.cur_2 != null)
				return false;
		} else if (!cur_2.equals(other.cur_2))
			return false;
		if (date_tbl_no_994 == null) {
			if (other.date_tbl_no_994 != null)
				return false;
		} else if (!date_tbl_no_994.equals(other.date_tbl_no_994))
			return false;
		if (dec_1 == null) {
			if (other.dec_1 != null)
				return false;
		} else if (!dec_1.equals(other.dec_1))
			return false;
		if (dec_2 == null) {
			if (other.dec_2 != null)
				return false;
		} else if (!dec_2.equals(other.dec_2))
			return false;
		if (disc == null) {
			if (other.disc != null)
				return false;
		} else if (!disc.equals(other.disc))
			return false;
		if (dom_intl_comb == null) {
			if (other.dom_intl_comb != null)
				return false;
		} else if (!dom_intl_comb.equals(other.dom_intl_comb))
			return false;
		if (dom_intl_comb_cxr_tbl_no_990 == null) {
			if (other.dom_intl_comb_cxr_tbl_no_990 != null)
				return false;
		} else if (!dom_intl_comb_cxr_tbl_no_990.equals(other.dom_intl_comb_cxr_tbl_no_990))
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (f_cmp == null) {
			if (other.f_cmp != null)
				return false;
		} else if (!f_cmp.equals(other.f_cmp))
			return false;
		if (fee_appl == null) {
			if (other.fee_appl != null)
				return false;
		} else if (!fee_appl.equals(other.fee_appl))
			return false;
		if (fee_appl_j_pu_fc == null) {
			if (other.fee_appl_j_pu_fc != null)
				return false;
		} else if (!fee_appl_j_pu_fc.equals(other.fee_appl_j_pu_fc))
			return false;
		if (from_of_ref == null) {
			if (other.from_of_ref != null)
				return false;
		} else if (!from_of_ref.equals(other.from_of_ref))
			return false;
		if (h_l == null) {
			if (other.h_l != null)
				return false;
		} else if (!h_l.equals(other.h_l))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (jny == null) {
			if (other.jny != null)
				return false;
		} else if (!jny.equals(other.jny))
			return false;
		if (min_amt == null) {
			if (other.min_amt != null)
				return false;
		} else if (!min_amt.equals(other.min_amt))
			return false;
		if (min_cur == null) {
			if (other.min_cur != null)
				return false;
		} else if (!min_cur.equals(other.min_cur))
			return false;
		if (min_dec == null) {
			if (other.min_dec != null)
				return false;
		} else if (!min_dec.equals(other.min_dec))
			return false;
		if (per == null) {
			if (other.per != null)
				return false;
		} else if (!per.equals(other.per))
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
		if (ptc == null) {
			if (other.ptc != null)
				return false;
		} else if (!ptc.equals(other.ptc))
			return false;
		if (pu == null) {
			if (other.pu != null)
				return false;
		} else if (!pu.equals(other.pu))
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
		if (reis_tbl_no_988 == null) {
			if (other.reis_tbl_no_988 != null)
				return false;
		} else if (!reis_tbl_no_988.equals(other.reis_tbl_no_988))
			return false;
		if (res_pen == null) {
			if (other.res_pen != null)
				return false;
		} else if (!res_pen.equals(other.res_pen))
			return false;
		if (res_pen_hierarchy == null) {
			if (other.res_pen_hierarchy != null)
				return false;
		} else if (!res_pen_hierarchy.equals(other.res_pen_hierarchy))
			return false;
		if (reserved_1 == null) {
			if (other.reserved_1 != null)
				return false;
		} else if (!reserved_1.equals(other.reserved_1))
			return false;
		if (reserved_2 == null) {
			if (other.reserved_2 != null)
				return false;
		} else if (!reserved_2.equals(other.reserved_2))
			return false;
		if (reserved_3 == null) {
			if (other.reserved_3 != null)
				return false;
		} else if (!reserved_3.equals(other.reserved_3))
			return false;
		if (reserved_4 == null) {
			if (other.reserved_4 != null)
				return false;
		} else if (!reserved_4.equals(other.reserved_4))
			return false;
		if (reserved_5 == null) {
			if (other.reserved_5 != null)
				return false;
		} else if (!reserved_5.equals(other.reserved_5))
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
		if (tkt_val == null) {
			if (other.tkt_val != null)
				return false;
		} else if (!tkt_val.equals(other.tkt_val))
			return false;
		if (typ_tkt == null) {
			if (other.typ_tkt != null)
				return false;
		} else if (!typ_tkt.equals(other.typ_tkt))
			return false;
		if (unavail == null) {
			if (other.unavail != null)
				return false;
		} else if (!unavail.equals(other.unavail))
			return false;
		if (waiv_tbl_no_987 == null) {
			if (other.waiv_tbl_no_987 != null)
				return false;
		} else if (!waiv_tbl_no_987.equals(other.waiv_tbl_no_987))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoRecord3Cat31 [id=" + id + ", reis_tbl_no_988=" + reis_tbl_no_988 + ", record_sequence="
				+ record_sequence + ", tkt_val=" + tkt_val + ", cur_1=" + cur_1 + ", cur_2=" + cur_2 + ", h_l=" + h_l
				+ ", ptc=" + ptc + ", disc=" + disc + ", chg_ind=" + chg_ind + ", typ_tkt=" + typ_tkt + ", fee_appl="
				+ fee_appl + ", adv_rsvn_to_pt=" + adv_rsvn_to_pt + ", adv_rsvn_from_pt=" + adv_rsvn_from_pt
				+ ", psgr_occ_first=" + psgr_occ_first + ", tbl_no=" + tbl_no + ", unavail=" + unavail
				+ ", dom_intl_comb=" + dom_intl_comb + ", action=" + action + ", rules_type=" + rules_type
				+ ", adv_rsvn_reserved_1=" + adv_rsvn_reserved_1 + ", end=" + end + ", from_of_ref=" + from_of_ref
				+ ", jny=" + jny + ", res_pen=" + res_pen + ", f_cmp=" + f_cmp + ", min_cur=" + min_cur
				+ ", adv_rsvn_period=" + adv_rsvn_period + ", date_tbl_no_994=" + date_tbl_no_994 + ", text_tbl_no_996="
				+ text_tbl_no_996 + ", record_batch=" + record_batch + ", amt_1=" + amt_1 + ", amt_2=" + amt_2
				+ ", psgr_occ_last=" + psgr_occ_last + ", reserved_4=" + reserved_4 + ", adv_rsvn_unit=" + adv_rsvn_unit
				+ ", reserved_3=" + reserved_3 + ", cat_no=" + cat_no + ", min_amt=" + min_amt + ", reserved_5="
				+ reserved_5 + ", fee_appl_j_pu_fc=" + fee_appl_j_pu_fc + ", per=" + per + ", res_pen_hierarchy="
				+ res_pen_hierarchy + ", reserved_2=" + reserved_2 + ", rec_type=" + rec_type + ", reserved_1="
				+ reserved_1 + ", dec_1=" + dec_1 + ", dom_intl_comb_cxr_tbl_no_990=" + dom_intl_comb_cxr_tbl_no_990
				+ ", dec_2=" + dec_2 + ", pu=" + pu + ", waiv_tbl_no_987=" + waiv_tbl_no_987 + ", min_dec=" + min_dec
				+ "]";
	}
	
	
}
