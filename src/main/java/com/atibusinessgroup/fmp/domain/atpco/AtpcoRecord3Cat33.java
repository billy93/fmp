package com.atibusinessgroup.fmp.domain.atpco;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_record_3_cat_033")
public class AtpcoRecord3Cat33 {

	@Id
    private String id;
	
	@Field("re_price_rule_fare_class_code")
    private String re_price_rule_fare_class_code;

	@Field("record_sequence")
	private String record_sequence;

	@Field("cust_first_period")
    private String cust_first_period;

	@Field("tkt_val_period")
    private String tkt_val_period;

	@Field("tkt_val_ticket_validity")
    private String tkt_val_ticket_validity;

	@Field("cur_1")
    private String cur_1;

	@Field("cur_2")
    private String cur_2;

	@Field("cxr_tbl_no_990")
    private String cxr_tbl_no_990;

	@Field("fully_flown")
    private String fully_flown;

	@Field("ptc")
    private String ptc;

	@Field("disc")
    private String disc;

	@Field("re_price_rule_ind")
    private String re_price_rule_ind;

	@Field("tbl_no")
    private String tbl_no;

	@Field("unavail")
    private String unavail;

	@Field("tkt_time_lmt")
    private String tkt_time_lmt;

	@Field("rules_type")
    private String rules_type;

	@Field("action")
    private String action;

	@Field("re_price_rule_owrt")
    private String re_price_rule_owrt;

	@Field("calc_option")
    private String calc_option;

	@Field("adv_cxl_from_to_pt")
    private String adv_cxl_from_to_pt;

	@Field("jny")
    private String jny;

	@Field("tkt_val_unit")
    private String tkt_val_unit;

	@Field("f_cmp")
    private String f_cmp;

	@Field("min_cur")
    private String min_cur;

	@Field("date_tbl_no_994")
    private String date_tbl_no_994;

	@Field("text_tbl_no_996")
    private String text_tbl_no_996;

	@Field("adv_cxl_unit")
    private String adv_cxl_unit;

	@Field("adv_cxl_reserved_1")
    private String adv_cxl_reserved_1;

	@Field("re_price_rule_nml_spcl")
    private String re_price_rule_nml_spcl;

	@Field("min_amt")
    private BigDecimal min_amt;

	@Field("pu_fc")
    private String pu_fc;

	@Field("rec_type")
    private String rec_type;

	@Field("orig_sched_unit")
    private String orig_sched_unit;

	@Field("dec_1")
    private String dec_1;

	@Field("dec_2")
    private String dec_2;

	@Field("waiv_tbl_no_987")
    private String waiv_tbl_no_987;

	@Field("re_price_rule_indicator")
    private String re_price_rule_indicator;

	@Field("re_price_rule_fare_ammount")
    private String re_price_rule_fare_ammount;

	@Field("re_price_rule_bkg_code")
    private String re_price_rule_bkg_code;

	@Field("cust_first_unit")
    private String cust_first_unit;

	@Field("orig_sched_period")
    private String orig_sched_period;

	@Field("re_price_rule_same")
    private String re_price_rule_same;

	@Field("h_l")
    private String h_l;

	@Field("adv_cxl_period")
    private String adv_cxl_period;

	@Field("adv_cxl_last_time_of_day")
    private String adv_cxl_last_time_of_day;

	@Field("tax_non_ref")
    private String tax_non_ref;

	@Field("adv_res_val")
    private String adv_res_val;

	@Field("re_price_rule_rule_no")
    private String re_price_rule_rule_no;

	@Field("re_price_rule_fare_type_tbl_no_974")
    private String re_price_rule_fare_type_tbl_no_974;

	@Field("form_of_fund")
    private String form_of_fund;

	@Field("cxl_ind")
    private String cxl_ind;

	@Field("fare_break_points")
    private String fare_break_points;

	@Field("record_batch")
    private String record_batch;

	@Field("amt_1")
    private BigDecimal amt_1;

	@Field("cust_first_res_tkt")
    private String cust_first_res_tkt;

	@Field("re_price_rule_tar_no")
    private String re_price_rule_tar_no;

	@Field("amt_2")
    private BigDecimal amt_2;

	@Field("re_price_ind")
    private String re_price_ind;

	@Field("cat_no")
    private String cat_no;

	@Field("per")
    private String per;

	@Field("reserved_1")
    private String reserved_1;

	@Field("pu")
    private String pu;

	@Field("orig_sched")
    private String orig_sched;

	@Field("min_dec")
    private String min_dec;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRe_price_rule_fare_class_code() {
		return re_price_rule_fare_class_code;
	}

	public void setRe_price_rule_fare_class_code(String re_price_rule_fare_class_code) {
		this.re_price_rule_fare_class_code = re_price_rule_fare_class_code;
	}

	public String getRecord_sequence() {
		return record_sequence;
	}

	public void setRecord_sequence(String record_sequence) {
		this.record_sequence = record_sequence;
	}

	public String getCust_first_period() {
		return cust_first_period;
	}

	public void setCust_first_period(String cust_first_period) {
		this.cust_first_period = cust_first_period;
	}

	public String getTkt_val_period() {
		return tkt_val_period;
	}

	public void setTkt_val_period(String tkt_val_period) {
		this.tkt_val_period = tkt_val_period;
	}

	public String getTkt_val_ticket_validity() {
		return tkt_val_ticket_validity;
	}

	public void setTkt_val_ticket_validity(String tkt_val_ticket_validity) {
		this.tkt_val_ticket_validity = tkt_val_ticket_validity;
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

	public String getCxr_tbl_no_990() {
		return cxr_tbl_no_990;
	}

	public void setCxr_tbl_no_990(String cxr_tbl_no_990) {
		this.cxr_tbl_no_990 = cxr_tbl_no_990;
	}

	public String getFully_flown() {
		return fully_flown;
	}

	public void setFully_flown(String fully_flown) {
		this.fully_flown = fully_flown;
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

	public String getRe_price_rule_ind() {
		return re_price_rule_ind;
	}

	public void setRe_price_rule_ind(String re_price_rule_ind) {
		this.re_price_rule_ind = re_price_rule_ind;
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

	public String getTkt_time_lmt() {
		return tkt_time_lmt;
	}

	public void setTkt_time_lmt(String tkt_time_lmt) {
		this.tkt_time_lmt = tkt_time_lmt;
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

	public String getRe_price_rule_owrt() {
		return re_price_rule_owrt;
	}

	public void setRe_price_rule_owrt(String re_price_rule_owrt) {
		this.re_price_rule_owrt = re_price_rule_owrt;
	}

	public String getCalc_option() {
		return calc_option;
	}

	public void setCalc_option(String calc_option) {
		this.calc_option = calc_option;
	}

	public String getAdv_cxl_from_to_pt() {
		return adv_cxl_from_to_pt;
	}

	public void setAdv_cxl_from_to_pt(String adv_cxl_from_to_pt) {
		this.adv_cxl_from_to_pt = adv_cxl_from_to_pt;
	}

	public String getJny() {
		return jny;
	}

	public void setJny(String jny) {
		this.jny = jny;
	}

	public String getTkt_val_unit() {
		return tkt_val_unit;
	}

	public void setTkt_val_unit(String tkt_val_unit) {
		this.tkt_val_unit = tkt_val_unit;
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

	public String getAdv_cxl_unit() {
		return adv_cxl_unit;
	}

	public void setAdv_cxl_unit(String adv_cxl_unit) {
		this.adv_cxl_unit = adv_cxl_unit;
	}

	public String getAdv_cxl_reserved_1() {
		return adv_cxl_reserved_1;
	}

	public void setAdv_cxl_reserved_1(String adv_cxl_reserved_1) {
		this.adv_cxl_reserved_1 = adv_cxl_reserved_1;
	}

	public String getRe_price_rule_nml_spcl() {
		return re_price_rule_nml_spcl;
	}

	public void setRe_price_rule_nml_spcl(String re_price_rule_nml_spcl) {
		this.re_price_rule_nml_spcl = re_price_rule_nml_spcl;
	}

	public BigDecimal getMin_amt() {
		return min_amt;
	}

	public void setMin_amt(BigDecimal min_amt) {
		this.min_amt = min_amt;
	}

	public String getPu_fc() {
		return pu_fc;
	}

	public void setPu_fc(String pu_fc) {
		this.pu_fc = pu_fc;
	}

	public String getRec_type() {
		return rec_type;
	}

	public void setRec_type(String rec_type) {
		this.rec_type = rec_type;
	}

	public String getOrig_sched_unit() {
		return orig_sched_unit;
	}

	public void setOrig_sched_unit(String orig_sched_unit) {
		this.orig_sched_unit = orig_sched_unit;
	}

	public String getDec_1() {
		return dec_1;
	}

	public void setDec_1(String dec_1) {
		this.dec_1 = dec_1;
	}

	public String getDec_2() {
		return dec_2;
	}

	public void setDec_2(String dec_2) {
		this.dec_2 = dec_2;
	}

	public String getWaiv_tbl_no_987() {
		return waiv_tbl_no_987;
	}

	public void setWaiv_tbl_no_987(String waiv_tbl_no_987) {
		this.waiv_tbl_no_987 = waiv_tbl_no_987;
	}

	public String getRe_price_rule_indicator() {
		return re_price_rule_indicator;
	}

	public void setRe_price_rule_indicator(String re_price_rule_indicator) {
		this.re_price_rule_indicator = re_price_rule_indicator;
	}

	public String getRe_price_rule_fare_ammount() {
		return re_price_rule_fare_ammount;
	}

	public void setRe_price_rule_fare_ammount(String re_price_rule_fare_ammount) {
		this.re_price_rule_fare_ammount = re_price_rule_fare_ammount;
	}

	public String getRe_price_rule_bkg_code() {
		return re_price_rule_bkg_code;
	}

	public void setRe_price_rule_bkg_code(String re_price_rule_bkg_code) {
		this.re_price_rule_bkg_code = re_price_rule_bkg_code;
	}

	public String getCust_first_unit() {
		return cust_first_unit;
	}

	public void setCust_first_unit(String cust_first_unit) {
		this.cust_first_unit = cust_first_unit;
	}

	public String getOrig_sched_period() {
		return orig_sched_period;
	}

	public void setOrig_sched_period(String orig_sched_period) {
		this.orig_sched_period = orig_sched_period;
	}

	public String getRe_price_rule_same() {
		return re_price_rule_same;
	}

	public void setRe_price_rule_same(String re_price_rule_same) {
		this.re_price_rule_same = re_price_rule_same;
	}

	public String getH_l() {
		return h_l;
	}

	public void setH_l(String h_l) {
		this.h_l = h_l;
	}

	public String getAdv_cxl_period() {
		return adv_cxl_period;
	}

	public void setAdv_cxl_period(String adv_cxl_period) {
		this.adv_cxl_period = adv_cxl_period;
	}

	public String getAdv_cxl_last_time_of_day() {
		return adv_cxl_last_time_of_day;
	}

	public void setAdv_cxl_last_time_of_day(String adv_cxl_last_time_of_day) {
		this.adv_cxl_last_time_of_day = adv_cxl_last_time_of_day;
	}

	public String getTax_non_ref() {
		return tax_non_ref;
	}

	public void setTax_non_ref(String tax_non_ref) {
		this.tax_non_ref = tax_non_ref;
	}

	public String getAdv_res_val() {
		return adv_res_val;
	}

	public void setAdv_res_val(String adv_res_val) {
		this.adv_res_val = adv_res_val;
	}

	public String getRe_price_rule_rule_no() {
		return re_price_rule_rule_no;
	}

	public void setRe_price_rule_rule_no(String re_price_rule_rule_no) {
		this.re_price_rule_rule_no = re_price_rule_rule_no;
	}

	public String getRe_price_rule_fare_type_tbl_no_974() {
		return re_price_rule_fare_type_tbl_no_974;
	}

	public void setRe_price_rule_fare_type_tbl_no_974(String re_price_rule_fare_type_tbl_no_974) {
		this.re_price_rule_fare_type_tbl_no_974 = re_price_rule_fare_type_tbl_no_974;
	}

	public String getForm_of_fund() {
		return form_of_fund;
	}

	public void setForm_of_fund(String form_of_fund) {
		this.form_of_fund = form_of_fund;
	}

	public String getCxl_ind() {
		return cxl_ind;
	}

	public void setCxl_ind(String cxl_ind) {
		this.cxl_ind = cxl_ind;
	}

	public String getFare_break_points() {
		return fare_break_points;
	}

	public void setFare_break_points(String fare_break_points) {
		this.fare_break_points = fare_break_points;
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

	public String getCust_first_res_tkt() {
		return cust_first_res_tkt;
	}

	public void setCust_first_res_tkt(String cust_first_res_tkt) {
		this.cust_first_res_tkt = cust_first_res_tkt;
	}

	public String getRe_price_rule_tar_no() {
		return re_price_rule_tar_no;
	}

	public void setRe_price_rule_tar_no(String re_price_rule_tar_no) {
		this.re_price_rule_tar_no = re_price_rule_tar_no;
	}

	public BigDecimal getAmt_2() {
		return amt_2;
	}

	public void setAmt_2(BigDecimal amt_2) {
		this.amt_2 = amt_2;
	}

	public String getRe_price_ind() {
		return re_price_ind;
	}

	public void setRe_price_ind(String re_price_ind) {
		this.re_price_ind = re_price_ind;
	}

	public String getCat_no() {
		return cat_no;
	}

	public void setCat_no(String cat_no) {
		this.cat_no = cat_no;
	}

	public String getPer() {
		return per;
	}

	public void setPer(String per) {
		this.per = per;
	}

	public String getReserved_1() {
		return reserved_1;
	}

	public void setReserved_1(String reserved_1) {
		this.reserved_1 = reserved_1;
	}

	public String getPu() {
		return pu;
	}

	public void setPu(String pu) {
		this.pu = pu;
	}

	public String getOrig_sched() {
		return orig_sched;
	}

	public void setOrig_sched(String orig_sched) {
		this.orig_sched = orig_sched;
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
		result = prime * result + ((adv_cxl_from_to_pt == null) ? 0 : adv_cxl_from_to_pt.hashCode());
		result = prime * result + ((adv_cxl_last_time_of_day == null) ? 0 : adv_cxl_last_time_of_day.hashCode());
		result = prime * result + ((adv_cxl_period == null) ? 0 : adv_cxl_period.hashCode());
		result = prime * result + ((adv_cxl_reserved_1 == null) ? 0 : adv_cxl_reserved_1.hashCode());
		result = prime * result + ((adv_cxl_unit == null) ? 0 : adv_cxl_unit.hashCode());
		result = prime * result + ((adv_res_val == null) ? 0 : adv_res_val.hashCode());
		result = prime * result + ((amt_1 == null) ? 0 : amt_1.hashCode());
		result = prime * result + ((amt_2 == null) ? 0 : amt_2.hashCode());
		result = prime * result + ((calc_option == null) ? 0 : calc_option.hashCode());
		result = prime * result + ((cat_no == null) ? 0 : cat_no.hashCode());
		result = prime * result + ((cur_1 == null) ? 0 : cur_1.hashCode());
		result = prime * result + ((cur_2 == null) ? 0 : cur_2.hashCode());
		result = prime * result + ((cust_first_period == null) ? 0 : cust_first_period.hashCode());
		result = prime * result + ((cust_first_res_tkt == null) ? 0 : cust_first_res_tkt.hashCode());
		result = prime * result + ((cust_first_unit == null) ? 0 : cust_first_unit.hashCode());
		result = prime * result + ((cxl_ind == null) ? 0 : cxl_ind.hashCode());
		result = prime * result + ((cxr_tbl_no_990 == null) ? 0 : cxr_tbl_no_990.hashCode());
		result = prime * result + ((date_tbl_no_994 == null) ? 0 : date_tbl_no_994.hashCode());
		result = prime * result + ((dec_1 == null) ? 0 : dec_1.hashCode());
		result = prime * result + ((dec_2 == null) ? 0 : dec_2.hashCode());
		result = prime * result + ((disc == null) ? 0 : disc.hashCode());
		result = prime * result + ((f_cmp == null) ? 0 : f_cmp.hashCode());
		result = prime * result + ((fare_break_points == null) ? 0 : fare_break_points.hashCode());
		result = prime * result + ((form_of_fund == null) ? 0 : form_of_fund.hashCode());
		result = prime * result + ((fully_flown == null) ? 0 : fully_flown.hashCode());
		result = prime * result + ((h_l == null) ? 0 : h_l.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((jny == null) ? 0 : jny.hashCode());
		result = prime * result + ((min_amt == null) ? 0 : min_amt.hashCode());
		result = prime * result + ((min_cur == null) ? 0 : min_cur.hashCode());
		result = prime * result + ((min_dec == null) ? 0 : min_dec.hashCode());
		result = prime * result + ((orig_sched == null) ? 0 : orig_sched.hashCode());
		result = prime * result + ((orig_sched_period == null) ? 0 : orig_sched_period.hashCode());
		result = prime * result + ((orig_sched_unit == null) ? 0 : orig_sched_unit.hashCode());
		result = prime * result + ((per == null) ? 0 : per.hashCode());
		result = prime * result + ((ptc == null) ? 0 : ptc.hashCode());
		result = prime * result + ((pu == null) ? 0 : pu.hashCode());
		result = prime * result + ((pu_fc == null) ? 0 : pu_fc.hashCode());
		result = prime * result + ((re_price_ind == null) ? 0 : re_price_ind.hashCode());
		result = prime * result + ((re_price_rule_bkg_code == null) ? 0 : re_price_rule_bkg_code.hashCode());
		result = prime * result + ((re_price_rule_fare_ammount == null) ? 0 : re_price_rule_fare_ammount.hashCode());
		result = prime * result
				+ ((re_price_rule_fare_class_code == null) ? 0 : re_price_rule_fare_class_code.hashCode());
		result = prime * result
				+ ((re_price_rule_fare_type_tbl_no_974 == null) ? 0 : re_price_rule_fare_type_tbl_no_974.hashCode());
		result = prime * result + ((re_price_rule_ind == null) ? 0 : re_price_rule_ind.hashCode());
		result = prime * result + ((re_price_rule_indicator == null) ? 0 : re_price_rule_indicator.hashCode());
		result = prime * result + ((re_price_rule_nml_spcl == null) ? 0 : re_price_rule_nml_spcl.hashCode());
		result = prime * result + ((re_price_rule_owrt == null) ? 0 : re_price_rule_owrt.hashCode());
		result = prime * result + ((re_price_rule_rule_no == null) ? 0 : re_price_rule_rule_no.hashCode());
		result = prime * result + ((re_price_rule_same == null) ? 0 : re_price_rule_same.hashCode());
		result = prime * result + ((re_price_rule_tar_no == null) ? 0 : re_price_rule_tar_no.hashCode());
		result = prime * result + ((rec_type == null) ? 0 : rec_type.hashCode());
		result = prime * result + ((record_batch == null) ? 0 : record_batch.hashCode());
		result = prime * result + ((record_sequence == null) ? 0 : record_sequence.hashCode());
		result = prime * result + ((reserved_1 == null) ? 0 : reserved_1.hashCode());
		result = prime * result + ((rules_type == null) ? 0 : rules_type.hashCode());
		result = prime * result + ((tax_non_ref == null) ? 0 : tax_non_ref.hashCode());
		result = prime * result + ((tbl_no == null) ? 0 : tbl_no.hashCode());
		result = prime * result + ((text_tbl_no_996 == null) ? 0 : text_tbl_no_996.hashCode());
		result = prime * result + ((tkt_time_lmt == null) ? 0 : tkt_time_lmt.hashCode());
		result = prime * result + ((tkt_val_period == null) ? 0 : tkt_val_period.hashCode());
		result = prime * result + ((tkt_val_ticket_validity == null) ? 0 : tkt_val_ticket_validity.hashCode());
		result = prime * result + ((tkt_val_unit == null) ? 0 : tkt_val_unit.hashCode());
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
		AtpcoRecord3Cat33 other = (AtpcoRecord3Cat33) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (adv_cxl_from_to_pt == null) {
			if (other.adv_cxl_from_to_pt != null)
				return false;
		} else if (!adv_cxl_from_to_pt.equals(other.adv_cxl_from_to_pt))
			return false;
		if (adv_cxl_last_time_of_day == null) {
			if (other.adv_cxl_last_time_of_day != null)
				return false;
		} else if (!adv_cxl_last_time_of_day.equals(other.adv_cxl_last_time_of_day))
			return false;
		if (adv_cxl_period == null) {
			if (other.adv_cxl_period != null)
				return false;
		} else if (!adv_cxl_period.equals(other.adv_cxl_period))
			return false;
		if (adv_cxl_reserved_1 == null) {
			if (other.adv_cxl_reserved_1 != null)
				return false;
		} else if (!adv_cxl_reserved_1.equals(other.adv_cxl_reserved_1))
			return false;
		if (adv_cxl_unit == null) {
			if (other.adv_cxl_unit != null)
				return false;
		} else if (!adv_cxl_unit.equals(other.adv_cxl_unit))
			return false;
		if (adv_res_val == null) {
			if (other.adv_res_val != null)
				return false;
		} else if (!adv_res_val.equals(other.adv_res_val))
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
		if (calc_option == null) {
			if (other.calc_option != null)
				return false;
		} else if (!calc_option.equals(other.calc_option))
			return false;
		if (cat_no == null) {
			if (other.cat_no != null)
				return false;
		} else if (!cat_no.equals(other.cat_no))
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
		if (cust_first_period == null) {
			if (other.cust_first_period != null)
				return false;
		} else if (!cust_first_period.equals(other.cust_first_period))
			return false;
		if (cust_first_res_tkt == null) {
			if (other.cust_first_res_tkt != null)
				return false;
		} else if (!cust_first_res_tkt.equals(other.cust_first_res_tkt))
			return false;
		if (cust_first_unit == null) {
			if (other.cust_first_unit != null)
				return false;
		} else if (!cust_first_unit.equals(other.cust_first_unit))
			return false;
		if (cxl_ind == null) {
			if (other.cxl_ind != null)
				return false;
		} else if (!cxl_ind.equals(other.cxl_ind))
			return false;
		if (cxr_tbl_no_990 == null) {
			if (other.cxr_tbl_no_990 != null)
				return false;
		} else if (!cxr_tbl_no_990.equals(other.cxr_tbl_no_990))
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
		if (f_cmp == null) {
			if (other.f_cmp != null)
				return false;
		} else if (!f_cmp.equals(other.f_cmp))
			return false;
		if (fare_break_points == null) {
			if (other.fare_break_points != null)
				return false;
		} else if (!fare_break_points.equals(other.fare_break_points))
			return false;
		if (form_of_fund == null) {
			if (other.form_of_fund != null)
				return false;
		} else if (!form_of_fund.equals(other.form_of_fund))
			return false;
		if (fully_flown == null) {
			if (other.fully_flown != null)
				return false;
		} else if (!fully_flown.equals(other.fully_flown))
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
		if (orig_sched == null) {
			if (other.orig_sched != null)
				return false;
		} else if (!orig_sched.equals(other.orig_sched))
			return false;
		if (orig_sched_period == null) {
			if (other.orig_sched_period != null)
				return false;
		} else if (!orig_sched_period.equals(other.orig_sched_period))
			return false;
		if (orig_sched_unit == null) {
			if (other.orig_sched_unit != null)
				return false;
		} else if (!orig_sched_unit.equals(other.orig_sched_unit))
			return false;
		if (per == null) {
			if (other.per != null)
				return false;
		} else if (!per.equals(other.per))
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
		if (pu_fc == null) {
			if (other.pu_fc != null)
				return false;
		} else if (!pu_fc.equals(other.pu_fc))
			return false;
		if (re_price_ind == null) {
			if (other.re_price_ind != null)
				return false;
		} else if (!re_price_ind.equals(other.re_price_ind))
			return false;
		if (re_price_rule_bkg_code == null) {
			if (other.re_price_rule_bkg_code != null)
				return false;
		} else if (!re_price_rule_bkg_code.equals(other.re_price_rule_bkg_code))
			return false;
		if (re_price_rule_fare_ammount == null) {
			if (other.re_price_rule_fare_ammount != null)
				return false;
		} else if (!re_price_rule_fare_ammount.equals(other.re_price_rule_fare_ammount))
			return false;
		if (re_price_rule_fare_class_code == null) {
			if (other.re_price_rule_fare_class_code != null)
				return false;
		} else if (!re_price_rule_fare_class_code.equals(other.re_price_rule_fare_class_code))
			return false;
		if (re_price_rule_fare_type_tbl_no_974 == null) {
			if (other.re_price_rule_fare_type_tbl_no_974 != null)
				return false;
		} else if (!re_price_rule_fare_type_tbl_no_974.equals(other.re_price_rule_fare_type_tbl_no_974))
			return false;
		if (re_price_rule_ind == null) {
			if (other.re_price_rule_ind != null)
				return false;
		} else if (!re_price_rule_ind.equals(other.re_price_rule_ind))
			return false;
		if (re_price_rule_indicator == null) {
			if (other.re_price_rule_indicator != null)
				return false;
		} else if (!re_price_rule_indicator.equals(other.re_price_rule_indicator))
			return false;
		if (re_price_rule_nml_spcl == null) {
			if (other.re_price_rule_nml_spcl != null)
				return false;
		} else if (!re_price_rule_nml_spcl.equals(other.re_price_rule_nml_spcl))
			return false;
		if (re_price_rule_owrt == null) {
			if (other.re_price_rule_owrt != null)
				return false;
		} else if (!re_price_rule_owrt.equals(other.re_price_rule_owrt))
			return false;
		if (re_price_rule_rule_no == null) {
			if (other.re_price_rule_rule_no != null)
				return false;
		} else if (!re_price_rule_rule_no.equals(other.re_price_rule_rule_no))
			return false;
		if (re_price_rule_same == null) {
			if (other.re_price_rule_same != null)
				return false;
		} else if (!re_price_rule_same.equals(other.re_price_rule_same))
			return false;
		if (re_price_rule_tar_no == null) {
			if (other.re_price_rule_tar_no != null)
				return false;
		} else if (!re_price_rule_tar_no.equals(other.re_price_rule_tar_no))
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
		if (tax_non_ref == null) {
			if (other.tax_non_ref != null)
				return false;
		} else if (!tax_non_ref.equals(other.tax_non_ref))
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
		if (tkt_time_lmt == null) {
			if (other.tkt_time_lmt != null)
				return false;
		} else if (!tkt_time_lmt.equals(other.tkt_time_lmt))
			return false;
		if (tkt_val_period == null) {
			if (other.tkt_val_period != null)
				return false;
		} else if (!tkt_val_period.equals(other.tkt_val_period))
			return false;
		if (tkt_val_ticket_validity == null) {
			if (other.tkt_val_ticket_validity != null)
				return false;
		} else if (!tkt_val_ticket_validity.equals(other.tkt_val_ticket_validity))
			return false;
		if (tkt_val_unit == null) {
			if (other.tkt_val_unit != null)
				return false;
		} else if (!tkt_val_unit.equals(other.tkt_val_unit))
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
		return "AtpcoRecord3Cat33 [id=" + id + ", re_price_rule_fare_class_code=" + re_price_rule_fare_class_code
				+ ", record_sequence=" + record_sequence + ", cust_first_period=" + cust_first_period
				+ ", tkt_val_period=" + tkt_val_period + ", tkt_val_ticket_validity=" + tkt_val_ticket_validity
				+ ", cur_1=" + cur_1 + ", cur_2=" + cur_2 + ", cxr_tbl_no_990=" + cxr_tbl_no_990 + ", fully_flown="
				+ fully_flown + ", ptc=" + ptc + ", disc=" + disc + ", re_price_rule_ind=" + re_price_rule_ind
				+ ", tbl_no=" + tbl_no + ", unavail=" + unavail + ", tkt_time_lmt=" + tkt_time_lmt + ", rules_type="
				+ rules_type + ", action=" + action + ", re_price_rule_owrt=" + re_price_rule_owrt + ", calc_option="
				+ calc_option + ", adv_cxl_from_to_pt=" + adv_cxl_from_to_pt + ", jny=" + jny + ", tkt_val_unit="
				+ tkt_val_unit + ", f_cmp=" + f_cmp + ", min_cur=" + min_cur + ", date_tbl_no_994=" + date_tbl_no_994
				+ ", text_tbl_no_996=" + text_tbl_no_996 + ", adv_cxl_unit=" + adv_cxl_unit + ", adv_cxl_reserved_1="
				+ adv_cxl_reserved_1 + ", re_price_rule_nml_spcl=" + re_price_rule_nml_spcl + ", min_amt=" + min_amt
				+ ", pu_fc=" + pu_fc + ", rec_type=" + rec_type + ", orig_sched_unit=" + orig_sched_unit + ", dec_1="
				+ dec_1 + ", dec_2=" + dec_2 + ", waiv_tbl_no_987=" + waiv_tbl_no_987 + ", re_price_rule_indicator="
				+ re_price_rule_indicator + ", re_price_rule_fare_ammount=" + re_price_rule_fare_ammount
				+ ", re_price_rule_bkg_code=" + re_price_rule_bkg_code + ", cust_first_unit=" + cust_first_unit
				+ ", orig_sched_period=" + orig_sched_period + ", re_price_rule_same=" + re_price_rule_same + ", h_l="
				+ h_l + ", adv_cxl_period=" + adv_cxl_period + ", adv_cxl_last_time_of_day=" + adv_cxl_last_time_of_day
				+ ", tax_non_ref=" + tax_non_ref + ", adv_res_val=" + adv_res_val + ", re_price_rule_rule_no="
				+ re_price_rule_rule_no + ", re_price_rule_fare_type_tbl_no_974=" + re_price_rule_fare_type_tbl_no_974
				+ ", form_of_fund=" + form_of_fund + ", cxl_ind=" + cxl_ind + ", fare_break_points=" + fare_break_points
				+ ", record_batch=" + record_batch + ", amt_1=" + amt_1 + ", cust_first_res_tkt=" + cust_first_res_tkt
				+ ", re_price_rule_tar_no=" + re_price_rule_tar_no + ", amt_2=" + amt_2 + ", re_price_ind="
				+ re_price_ind + ", cat_no=" + cat_no + ", per=" + per + ", reserved_1=" + reserved_1 + ", pu=" + pu
				+ ", orig_sched=" + orig_sched + ", min_dec=" + min_dec + "]";
	}
	
	
}
