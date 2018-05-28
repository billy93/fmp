package com.atibusinessgroup.fmp.domain.atpco;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_record_3_cat_029")
public class AtpcoRecord3Cat29 {

	@Id
    private String id;
	
	@Field("record_sequence")
	private String record_sequence;

	@Field("deposit_first_appl")
    private String deposit_first_appl;

	@Field("deposit_filler_16")
    private String deposit_filler_16;

	@Field("deposit_filler_15")
    private String deposit_filler_15;

	@Field("deposit_filler_14")
    private String deposit_filler_14;

	@Field("deposit_filler_13")
    private String deposit_filler_13;

	@Field("deposit_filler_19")
    private String deposit_filler_19;

	@Field("waiver_appl")
    private String waiver_appl;

	@Field("deposit_filler_18")
    private String deposit_filler_18;

	@Field("deposit_filler_17")
    private String deposit_filler_17;

	@Field("waiver_9")
    private String waiver_9;

	@Field("waiver_7")
    private String waiver_7;

	@Field("waiver_8")
    private String waiver_8;

	@Field("deposit_waiver")
    private String deposit_waiver;

	@Field("deposit_filler_11")
    private String deposit_filler_11;

	@Field("deposit_filler_12")
    private String deposit_filler_12;

	@Field("tbl_no")
    private String tbl_no;

	@Field("unavail")
    private String unavail;

	@Field("deposit_filler_10")
    private String deposit_filler_10;

	@Field("waiver_1")
    private String waiver_1;

	@Field("rules_type")
    private String rules_type;

	@Field("action")
    private String action;

	@Field("waiver_2")
    private String waiver_2;

	@Field("deposit_first_last_date")
    private Object deposit_first_last_date;

	@Field("deposit_first_filler_1")
    private String deposit_first_filler_1;

	@Field("deposit_filler_7")
    private String deposit_filler_7;

	@Field("deposit_filler_6")
    private String deposit_filler_6;

	@Field("deposit_filler_9")
    private String deposit_filler_9;

	@Field("deposit_filler_8")
    private String deposit_filler_8;

	@Field("date_tbl_no_994")
    private String date_tbl_no_994;

	@Field("deposit_req")
    private String deposit_req;

	@Field("waiver_11")
    private String waiver_11;

	@Field("waiver_10")
    private String waiver_10;

	@Field("text_tbl_no_996")
    private String text_tbl_no_996;

	@Field("deposit_filler_1")
    private String deposit_filler_1;

	@Field("deposit_filler_3")
    private String deposit_filler_3;

	@Field("deposit_filler_2")
    private String deposit_filler_2;

	@Field("waiver_13")
    private String waiver_13;

	@Field("deposit_filler_5")
    private String deposit_filler_5;

	@Field("waiver_12")
    private String waiver_12;

	@Field("deposit_filler_4")
    private String deposit_filler_4;

	@Field("deposit_first_days")
    private String deposit_first_days;

	@Field("waiver_reserved_1")
    private String waiver_reserved_1;

	@Field("rec_type")
    private String rec_type;

	@Field("refunds_first_amt_1")
    private BigDecimal refunds_first_amt_1;

	@Field("refunds_first_amt_2")
    private BigDecimal refunds_first_amt_2;

	@Field("refunds_first_filler_10")
    private String refunds_first_filler_10;

	@Field("refunds_first_filler_13")
    private String refunds_first_filler_13;

	@Field("refunds_first_filler_11")
    private String refunds_first_filler_11;

	@Field("refunds_first_filler_12")
    private String refunds_first_filler_12;

	@Field("deposit_first_min_amt_2")
    private BigDecimal deposit_first_min_amt_2;

	@Field("deposit_first_min_amt_1")
    private BigDecimal deposit_first_min_amt_1;

	@Field("refunds_first_geo_tbl_no_995")
    private String refunds_first_geo_tbl_no_995;

	@Field("refunds_first_pct")
    private String refunds_first_pct;

	@Field("refunds_first_dec_1")
    private String refunds_first_dec_1;

	@Field("refunds_first_cur_1")
    private String refunds_first_cur_1;

	@Field("refunds_first_dec_2")
    private String refunds_first_dec_2;

	@Field("waiver_filler_5")
    private String waiver_filler_5;

	@Field("waiver_filler_4")
    private String waiver_filler_4;

	@Field("deposit_first_pct")
    private String deposit_first_pct;

	@Field("deposit_first_geo_tbl_no_995")
    private String deposit_first_geo_tbl_no_995;

	@Field("refunds_first_filler_8")
    private String refunds_first_filler_8;

	@Field("refunds_first_filler_9")
    private String refunds_first_filler_9;

	@Field("refunds_first_filler_6")
    private String refunds_first_filler_6;

	@Field("refunds_first_filler_7")
    private String refunds_first_filler_7;

	@Field("refunds_first_filler_4")
    private String refunds_first_filler_4;

	@Field("refunds_first_filler_5")
    private String refunds_first_filler_5;

	@Field("refunds_first_filler_2")
    private String refunds_first_filler_2;

	@Field("deposit_filler_23")
    private String deposit_filler_23;

	@Field("refunds_first_filler_3")
    private String refunds_first_filler_3;

	@Field("deposit_filler_22")
    private String deposit_filler_22;

	@Field("deposit_filler_21")
    private String deposit_filler_21;

	@Field("refunds_first_filler_1")
    private String refunds_first_filler_1;

	@Field("deposit_filler_20")
    private String deposit_filler_20;

	@Field("record_batch")
    private String record_batch;

	@Field("refunds_first_days")
    private String refunds_first_days;

	@Field("deposit_first_cur_2")
    private String deposit_first_cur_2;

	@Field("waiver_filler_1")
    private String waiver_filler_1;

	@Field("cat_no")
    private String cat_no;

	@Field("deposit_first_cur_1")
    private String deposit_first_cur_1;

	@Field("waiver_filler_2")
    private String waiver_filler_2;

	@Field("waiver_filler_3")
    private String waiver_filler_3;

	@Field("deposit_seat")
    private String deposit_seat;

	@Field("deposit_filler_26")
    private String deposit_filler_26;

	@Field("refunds_first_cur_2")
    private String refunds_first_cur_2;

	@Field("deposit_filler_24")
    private String deposit_filler_24;

	@Field("deposit_filler_25")
    private String deposit_filler_25;

	@Field("refunds_first_appl")
    private String refunds_first_appl;

	@Field("refunds_non_ref")
    private String refunds_non_ref;

	@Field("deposit_first_dec_1")
    private String deposit_first_dec_1;

	@Field("deposit_first_dec_2")
    private String deposit_first_dec_2;

	@Field("refunds_filler_1")
    private String refunds_filler_1;

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

	public String getDeposit_first_appl() {
		return deposit_first_appl;
	}

	public void setDeposit_first_appl(String deposit_first_appl) {
		this.deposit_first_appl = deposit_first_appl;
	}

	public String getDeposit_filler_16() {
		return deposit_filler_16;
	}

	public void setDeposit_filler_16(String deposit_filler_16) {
		this.deposit_filler_16 = deposit_filler_16;
	}

	public String getDeposit_filler_15() {
		return deposit_filler_15;
	}

	public void setDeposit_filler_15(String deposit_filler_15) {
		this.deposit_filler_15 = deposit_filler_15;
	}

	public String getDeposit_filler_14() {
		return deposit_filler_14;
	}

	public void setDeposit_filler_14(String deposit_filler_14) {
		this.deposit_filler_14 = deposit_filler_14;
	}

	public String getDeposit_filler_13() {
		return deposit_filler_13;
	}

	public void setDeposit_filler_13(String deposit_filler_13) {
		this.deposit_filler_13 = deposit_filler_13;
	}

	public String getDeposit_filler_19() {
		return deposit_filler_19;
	}

	public void setDeposit_filler_19(String deposit_filler_19) {
		this.deposit_filler_19 = deposit_filler_19;
	}

	public String getWaiver_appl() {
		return waiver_appl;
	}

	public void setWaiver_appl(String waiver_appl) {
		this.waiver_appl = waiver_appl;
	}

	public String getDeposit_filler_18() {
		return deposit_filler_18;
	}

	public void setDeposit_filler_18(String deposit_filler_18) {
		this.deposit_filler_18 = deposit_filler_18;
	}

	public String getDeposit_filler_17() {
		return deposit_filler_17;
	}

	public void setDeposit_filler_17(String deposit_filler_17) {
		this.deposit_filler_17 = deposit_filler_17;
	}

	public String getWaiver_9() {
		return waiver_9;
	}

	public void setWaiver_9(String waiver_9) {
		this.waiver_9 = waiver_9;
	}

	public String getWaiver_7() {
		return waiver_7;
	}

	public void setWaiver_7(String waiver_7) {
		this.waiver_7 = waiver_7;
	}

	public String getWaiver_8() {
		return waiver_8;
	}

	public void setWaiver_8(String waiver_8) {
		this.waiver_8 = waiver_8;
	}

	public String getDeposit_waiver() {
		return deposit_waiver;
	}

	public void setDeposit_waiver(String deposit_waiver) {
		this.deposit_waiver = deposit_waiver;
	}

	public String getDeposit_filler_11() {
		return deposit_filler_11;
	}

	public void setDeposit_filler_11(String deposit_filler_11) {
		this.deposit_filler_11 = deposit_filler_11;
	}

	public String getDeposit_filler_12() {
		return deposit_filler_12;
	}

	public void setDeposit_filler_12(String deposit_filler_12) {
		this.deposit_filler_12 = deposit_filler_12;
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

	public String getDeposit_filler_10() {
		return deposit_filler_10;
	}

	public void setDeposit_filler_10(String deposit_filler_10) {
		this.deposit_filler_10 = deposit_filler_10;
	}

	public String getWaiver_1() {
		return waiver_1;
	}

	public void setWaiver_1(String waiver_1) {
		this.waiver_1 = waiver_1;
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

	public String getWaiver_2() {
		return waiver_2;
	}

	public void setWaiver_2(String waiver_2) {
		this.waiver_2 = waiver_2;
	}

	public Object getDeposit_first_last_date() {
		return deposit_first_last_date;
	}

	public void setDeposit_first_last_date(Object deposit_first_last_date) {
		this.deposit_first_last_date = deposit_first_last_date;
	}

	public String getDeposit_first_filler_1() {
		return deposit_first_filler_1;
	}

	public void setDeposit_first_filler_1(String deposit_first_filler_1) {
		this.deposit_first_filler_1 = deposit_first_filler_1;
	}

	public String getDeposit_filler_7() {
		return deposit_filler_7;
	}

	public void setDeposit_filler_7(String deposit_filler_7) {
		this.deposit_filler_7 = deposit_filler_7;
	}

	public String getDeposit_filler_6() {
		return deposit_filler_6;
	}

	public void setDeposit_filler_6(String deposit_filler_6) {
		this.deposit_filler_6 = deposit_filler_6;
	}

	public String getDeposit_filler_9() {
		return deposit_filler_9;
	}

	public void setDeposit_filler_9(String deposit_filler_9) {
		this.deposit_filler_9 = deposit_filler_9;
	}

	public String getDeposit_filler_8() {
		return deposit_filler_8;
	}

	public void setDeposit_filler_8(String deposit_filler_8) {
		this.deposit_filler_8 = deposit_filler_8;
	}

	public String getDate_tbl_no_994() {
		return date_tbl_no_994;
	}

	public void setDate_tbl_no_994(String date_tbl_no_994) {
		this.date_tbl_no_994 = date_tbl_no_994;
	}

	public String getDeposit_req() {
		return deposit_req;
	}

	public void setDeposit_req(String deposit_req) {
		this.deposit_req = deposit_req;
	}

	public String getWaiver_11() {
		return waiver_11;
	}

	public void setWaiver_11(String waiver_11) {
		this.waiver_11 = waiver_11;
	}

	public String getWaiver_10() {
		return waiver_10;
	}

	public void setWaiver_10(String waiver_10) {
		this.waiver_10 = waiver_10;
	}

	public String getText_tbl_no_996() {
		return text_tbl_no_996;
	}

	public void setText_tbl_no_996(String text_tbl_no_996) {
		this.text_tbl_no_996 = text_tbl_no_996;
	}

	public String getDeposit_filler_1() {
		return deposit_filler_1;
	}

	public void setDeposit_filler_1(String deposit_filler_1) {
		this.deposit_filler_1 = deposit_filler_1;
	}

	public String getDeposit_filler_3() {
		return deposit_filler_3;
	}

	public void setDeposit_filler_3(String deposit_filler_3) {
		this.deposit_filler_3 = deposit_filler_3;
	}

	public String getDeposit_filler_2() {
		return deposit_filler_2;
	}

	public void setDeposit_filler_2(String deposit_filler_2) {
		this.deposit_filler_2 = deposit_filler_2;
	}

	public String getWaiver_13() {
		return waiver_13;
	}

	public void setWaiver_13(String waiver_13) {
		this.waiver_13 = waiver_13;
	}

	public String getDeposit_filler_5() {
		return deposit_filler_5;
	}

	public void setDeposit_filler_5(String deposit_filler_5) {
		this.deposit_filler_5 = deposit_filler_5;
	}

	public String getWaiver_12() {
		return waiver_12;
	}

	public void setWaiver_12(String waiver_12) {
		this.waiver_12 = waiver_12;
	}

	public String getDeposit_filler_4() {
		return deposit_filler_4;
	}

	public void setDeposit_filler_4(String deposit_filler_4) {
		this.deposit_filler_4 = deposit_filler_4;
	}

	public String getDeposit_first_days() {
		return deposit_first_days;
	}

	public void setDeposit_first_days(String deposit_first_days) {
		this.deposit_first_days = deposit_first_days;
	}

	public String getWaiver_reserved_1() {
		return waiver_reserved_1;
	}

	public void setWaiver_reserved_1(String waiver_reserved_1) {
		this.waiver_reserved_1 = waiver_reserved_1;
	}

	public String getRec_type() {
		return rec_type;
	}

	public void setRec_type(String rec_type) {
		this.rec_type = rec_type;
	}

	public BigDecimal getRefunds_first_amt_1() {
		return refunds_first_amt_1;
	}

	public void setRefunds_first_amt_1(BigDecimal refunds_first_amt_1) {
		this.refunds_first_amt_1 = refunds_first_amt_1;
	}

	public BigDecimal getRefunds_first_amt_2() {
		return refunds_first_amt_2;
	}

	public void setRefunds_first_amt_2(BigDecimal refunds_first_amt_2) {
		this.refunds_first_amt_2 = refunds_first_amt_2;
	}

	public String getRefunds_first_filler_10() {
		return refunds_first_filler_10;
	}

	public void setRefunds_first_filler_10(String refunds_first_filler_10) {
		this.refunds_first_filler_10 = refunds_first_filler_10;
	}

	public String getRefunds_first_filler_13() {
		return refunds_first_filler_13;
	}

	public void setRefunds_first_filler_13(String refunds_first_filler_13) {
		this.refunds_first_filler_13 = refunds_first_filler_13;
	}

	public String getRefunds_first_filler_11() {
		return refunds_first_filler_11;
	}

	public void setRefunds_first_filler_11(String refunds_first_filler_11) {
		this.refunds_first_filler_11 = refunds_first_filler_11;
	}

	public String getRefunds_first_filler_12() {
		return refunds_first_filler_12;
	}

	public void setRefunds_first_filler_12(String refunds_first_filler_12) {
		this.refunds_first_filler_12 = refunds_first_filler_12;
	}

	public BigDecimal getDeposit_first_min_amt_2() {
		return deposit_first_min_amt_2;
	}

	public void setDeposit_first_min_amt_2(BigDecimal deposit_first_min_amt_2) {
		this.deposit_first_min_amt_2 = deposit_first_min_amt_2;
	}

	public BigDecimal getDeposit_first_min_amt_1() {
		return deposit_first_min_amt_1;
	}

	public void setDeposit_first_min_amt_1(BigDecimal deposit_first_min_amt_1) {
		this.deposit_first_min_amt_1 = deposit_first_min_amt_1;
	}

	public String getRefunds_first_geo_tbl_no_995() {
		return refunds_first_geo_tbl_no_995;
	}

	public void setRefunds_first_geo_tbl_no_995(String refunds_first_geo_tbl_no_995) {
		this.refunds_first_geo_tbl_no_995 = refunds_first_geo_tbl_no_995;
	}

	public String getRefunds_first_pct() {
		return refunds_first_pct;
	}

	public void setRefunds_first_pct(String refunds_first_pct) {
		this.refunds_first_pct = refunds_first_pct;
	}

	public String getRefunds_first_dec_1() {
		return refunds_first_dec_1;
	}

	public void setRefunds_first_dec_1(String refunds_first_dec_1) {
		this.refunds_first_dec_1 = refunds_first_dec_1;
	}

	public String getRefunds_first_cur_1() {
		return refunds_first_cur_1;
	}

	public void setRefunds_first_cur_1(String refunds_first_cur_1) {
		this.refunds_first_cur_1 = refunds_first_cur_1;
	}

	public String getRefunds_first_dec_2() {
		return refunds_first_dec_2;
	}

	public void setRefunds_first_dec_2(String refunds_first_dec_2) {
		this.refunds_first_dec_2 = refunds_first_dec_2;
	}

	public String getWaiver_filler_5() {
		return waiver_filler_5;
	}

	public void setWaiver_filler_5(String waiver_filler_5) {
		this.waiver_filler_5 = waiver_filler_5;
	}

	public String getWaiver_filler_4() {
		return waiver_filler_4;
	}

	public void setWaiver_filler_4(String waiver_filler_4) {
		this.waiver_filler_4 = waiver_filler_4;
	}

	public String getDeposit_first_pct() {
		return deposit_first_pct;
	}

	public void setDeposit_first_pct(String deposit_first_pct) {
		this.deposit_first_pct = deposit_first_pct;
	}

	public String getDeposit_first_geo_tbl_no_995() {
		return deposit_first_geo_tbl_no_995;
	}

	public void setDeposit_first_geo_tbl_no_995(String deposit_first_geo_tbl_no_995) {
		this.deposit_first_geo_tbl_no_995 = deposit_first_geo_tbl_no_995;
	}

	public String getRefunds_first_filler_8() {
		return refunds_first_filler_8;
	}

	public void setRefunds_first_filler_8(String refunds_first_filler_8) {
		this.refunds_first_filler_8 = refunds_first_filler_8;
	}

	public String getRefunds_first_filler_9() {
		return refunds_first_filler_9;
	}

	public void setRefunds_first_filler_9(String refunds_first_filler_9) {
		this.refunds_first_filler_9 = refunds_first_filler_9;
	}

	public String getRefunds_first_filler_6() {
		return refunds_first_filler_6;
	}

	public void setRefunds_first_filler_6(String refunds_first_filler_6) {
		this.refunds_first_filler_6 = refunds_first_filler_6;
	}

	public String getRefunds_first_filler_7() {
		return refunds_first_filler_7;
	}

	public void setRefunds_first_filler_7(String refunds_first_filler_7) {
		this.refunds_first_filler_7 = refunds_first_filler_7;
	}

	public String getRefunds_first_filler_4() {
		return refunds_first_filler_4;
	}

	public void setRefunds_first_filler_4(String refunds_first_filler_4) {
		this.refunds_first_filler_4 = refunds_first_filler_4;
	}

	public String getRefunds_first_filler_5() {
		return refunds_first_filler_5;
	}

	public void setRefunds_first_filler_5(String refunds_first_filler_5) {
		this.refunds_first_filler_5 = refunds_first_filler_5;
	}

	public String getRefunds_first_filler_2() {
		return refunds_first_filler_2;
	}

	public void setRefunds_first_filler_2(String refunds_first_filler_2) {
		this.refunds_first_filler_2 = refunds_first_filler_2;
	}

	public String getDeposit_filler_23() {
		return deposit_filler_23;
	}

	public void setDeposit_filler_23(String deposit_filler_23) {
		this.deposit_filler_23 = deposit_filler_23;
	}

	public String getRefunds_first_filler_3() {
		return refunds_first_filler_3;
	}

	public void setRefunds_first_filler_3(String refunds_first_filler_3) {
		this.refunds_first_filler_3 = refunds_first_filler_3;
	}

	public String getDeposit_filler_22() {
		return deposit_filler_22;
	}

	public void setDeposit_filler_22(String deposit_filler_22) {
		this.deposit_filler_22 = deposit_filler_22;
	}

	public String getDeposit_filler_21() {
		return deposit_filler_21;
	}

	public void setDeposit_filler_21(String deposit_filler_21) {
		this.deposit_filler_21 = deposit_filler_21;
	}

	public String getRefunds_first_filler_1() {
		return refunds_first_filler_1;
	}

	public void setRefunds_first_filler_1(String refunds_first_filler_1) {
		this.refunds_first_filler_1 = refunds_first_filler_1;
	}

	public String getDeposit_filler_20() {
		return deposit_filler_20;
	}

	public void setDeposit_filler_20(String deposit_filler_20) {
		this.deposit_filler_20 = deposit_filler_20;
	}

	public String getRecord_batch() {
		return record_batch;
	}

	public void setRecord_batch(String record_batch) {
		this.record_batch = record_batch;
	}

	public String getRefunds_first_days() {
		return refunds_first_days;
	}

	public void setRefunds_first_days(String refunds_first_days) {
		this.refunds_first_days = refunds_first_days;
	}

	public String getDeposit_first_cur_2() {
		return deposit_first_cur_2;
	}

	public void setDeposit_first_cur_2(String deposit_first_cur_2) {
		this.deposit_first_cur_2 = deposit_first_cur_2;
	}

	public String getWaiver_filler_1() {
		return waiver_filler_1;
	}

	public void setWaiver_filler_1(String waiver_filler_1) {
		this.waiver_filler_1 = waiver_filler_1;
	}

	public String getCat_no() {
		return cat_no;
	}

	public void setCat_no(String cat_no) {
		this.cat_no = cat_no;
	}

	public String getDeposit_first_cur_1() {
		return deposit_first_cur_1;
	}

	public void setDeposit_first_cur_1(String deposit_first_cur_1) {
		this.deposit_first_cur_1 = deposit_first_cur_1;
	}

	public String getWaiver_filler_2() {
		return waiver_filler_2;
	}

	public void setWaiver_filler_2(String waiver_filler_2) {
		this.waiver_filler_2 = waiver_filler_2;
	}

	public String getWaiver_filler_3() {
		return waiver_filler_3;
	}

	public void setWaiver_filler_3(String waiver_filler_3) {
		this.waiver_filler_3 = waiver_filler_3;
	}

	public String getDeposit_seat() {
		return deposit_seat;
	}

	public void setDeposit_seat(String deposit_seat) {
		this.deposit_seat = deposit_seat;
	}

	public String getDeposit_filler_26() {
		return deposit_filler_26;
	}

	public void setDeposit_filler_26(String deposit_filler_26) {
		this.deposit_filler_26 = deposit_filler_26;
	}

	public String getRefunds_first_cur_2() {
		return refunds_first_cur_2;
	}

	public void setRefunds_first_cur_2(String refunds_first_cur_2) {
		this.refunds_first_cur_2 = refunds_first_cur_2;
	}

	public String getDeposit_filler_24() {
		return deposit_filler_24;
	}

	public void setDeposit_filler_24(String deposit_filler_24) {
		this.deposit_filler_24 = deposit_filler_24;
	}

	public String getDeposit_filler_25() {
		return deposit_filler_25;
	}

	public void setDeposit_filler_25(String deposit_filler_25) {
		this.deposit_filler_25 = deposit_filler_25;
	}

	public String getRefunds_first_appl() {
		return refunds_first_appl;
	}

	public void setRefunds_first_appl(String refunds_first_appl) {
		this.refunds_first_appl = refunds_first_appl;
	}

	public String getRefunds_non_ref() {
		return refunds_non_ref;
	}

	public void setRefunds_non_ref(String refunds_non_ref) {
		this.refunds_non_ref = refunds_non_ref;
	}

	public String getDeposit_first_dec_1() {
		return deposit_first_dec_1;
	}

	public void setDeposit_first_dec_1(String deposit_first_dec_1) {
		this.deposit_first_dec_1 = deposit_first_dec_1;
	}

	public String getDeposit_first_dec_2() {
		return deposit_first_dec_2;
	}

	public void setDeposit_first_dec_2(String deposit_first_dec_2) {
		this.deposit_first_dec_2 = deposit_first_dec_2;
	}

	public String getRefunds_filler_1() {
		return refunds_filler_1;
	}

	public void setRefunds_filler_1(String refunds_filler_1) {
		this.refunds_filler_1 = refunds_filler_1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((cat_no == null) ? 0 : cat_no.hashCode());
		result = prime * result + ((date_tbl_no_994 == null) ? 0 : date_tbl_no_994.hashCode());
		result = prime * result + ((deposit_filler_1 == null) ? 0 : deposit_filler_1.hashCode());
		result = prime * result + ((deposit_filler_10 == null) ? 0 : deposit_filler_10.hashCode());
		result = prime * result + ((deposit_filler_11 == null) ? 0 : deposit_filler_11.hashCode());
		result = prime * result + ((deposit_filler_12 == null) ? 0 : deposit_filler_12.hashCode());
		result = prime * result + ((deposit_filler_13 == null) ? 0 : deposit_filler_13.hashCode());
		result = prime * result + ((deposit_filler_14 == null) ? 0 : deposit_filler_14.hashCode());
		result = prime * result + ((deposit_filler_15 == null) ? 0 : deposit_filler_15.hashCode());
		result = prime * result + ((deposit_filler_16 == null) ? 0 : deposit_filler_16.hashCode());
		result = prime * result + ((deposit_filler_17 == null) ? 0 : deposit_filler_17.hashCode());
		result = prime * result + ((deposit_filler_18 == null) ? 0 : deposit_filler_18.hashCode());
		result = prime * result + ((deposit_filler_19 == null) ? 0 : deposit_filler_19.hashCode());
		result = prime * result + ((deposit_filler_2 == null) ? 0 : deposit_filler_2.hashCode());
		result = prime * result + ((deposit_filler_20 == null) ? 0 : deposit_filler_20.hashCode());
		result = prime * result + ((deposit_filler_21 == null) ? 0 : deposit_filler_21.hashCode());
		result = prime * result + ((deposit_filler_22 == null) ? 0 : deposit_filler_22.hashCode());
		result = prime * result + ((deposit_filler_23 == null) ? 0 : deposit_filler_23.hashCode());
		result = prime * result + ((deposit_filler_24 == null) ? 0 : deposit_filler_24.hashCode());
		result = prime * result + ((deposit_filler_25 == null) ? 0 : deposit_filler_25.hashCode());
		result = prime * result + ((deposit_filler_26 == null) ? 0 : deposit_filler_26.hashCode());
		result = prime * result + ((deposit_filler_3 == null) ? 0 : deposit_filler_3.hashCode());
		result = prime * result + ((deposit_filler_4 == null) ? 0 : deposit_filler_4.hashCode());
		result = prime * result + ((deposit_filler_5 == null) ? 0 : deposit_filler_5.hashCode());
		result = prime * result + ((deposit_filler_6 == null) ? 0 : deposit_filler_6.hashCode());
		result = prime * result + ((deposit_filler_7 == null) ? 0 : deposit_filler_7.hashCode());
		result = prime * result + ((deposit_filler_8 == null) ? 0 : deposit_filler_8.hashCode());
		result = prime * result + ((deposit_filler_9 == null) ? 0 : deposit_filler_9.hashCode());
		result = prime * result + ((deposit_first_appl == null) ? 0 : deposit_first_appl.hashCode());
		result = prime * result + ((deposit_first_cur_1 == null) ? 0 : deposit_first_cur_1.hashCode());
		result = prime * result + ((deposit_first_cur_2 == null) ? 0 : deposit_first_cur_2.hashCode());
		result = prime * result + ((deposit_first_days == null) ? 0 : deposit_first_days.hashCode());
		result = prime * result + ((deposit_first_dec_1 == null) ? 0 : deposit_first_dec_1.hashCode());
		result = prime * result + ((deposit_first_dec_2 == null) ? 0 : deposit_first_dec_2.hashCode());
		result = prime * result + ((deposit_first_filler_1 == null) ? 0 : deposit_first_filler_1.hashCode());
		result = prime * result
				+ ((deposit_first_geo_tbl_no_995 == null) ? 0 : deposit_first_geo_tbl_no_995.hashCode());
		result = prime * result + ((deposit_first_last_date == null) ? 0 : deposit_first_last_date.hashCode());
		result = prime * result + ((deposit_first_min_amt_1 == null) ? 0 : deposit_first_min_amt_1.hashCode());
		result = prime * result + ((deposit_first_min_amt_2 == null) ? 0 : deposit_first_min_amt_2.hashCode());
		result = prime * result + ((deposit_first_pct == null) ? 0 : deposit_first_pct.hashCode());
		result = prime * result + ((deposit_req == null) ? 0 : deposit_req.hashCode());
		result = prime * result + ((deposit_seat == null) ? 0 : deposit_seat.hashCode());
		result = prime * result + ((deposit_waiver == null) ? 0 : deposit_waiver.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((rec_type == null) ? 0 : rec_type.hashCode());
		result = prime * result + ((record_batch == null) ? 0 : record_batch.hashCode());
		result = prime * result + ((record_sequence == null) ? 0 : record_sequence.hashCode());
		result = prime * result + ((refunds_filler_1 == null) ? 0 : refunds_filler_1.hashCode());
		result = prime * result + ((refunds_first_amt_1 == null) ? 0 : refunds_first_amt_1.hashCode());
		result = prime * result + ((refunds_first_amt_2 == null) ? 0 : refunds_first_amt_2.hashCode());
		result = prime * result + ((refunds_first_appl == null) ? 0 : refunds_first_appl.hashCode());
		result = prime * result + ((refunds_first_cur_1 == null) ? 0 : refunds_first_cur_1.hashCode());
		result = prime * result + ((refunds_first_cur_2 == null) ? 0 : refunds_first_cur_2.hashCode());
		result = prime * result + ((refunds_first_days == null) ? 0 : refunds_first_days.hashCode());
		result = prime * result + ((refunds_first_dec_1 == null) ? 0 : refunds_first_dec_1.hashCode());
		result = prime * result + ((refunds_first_dec_2 == null) ? 0 : refunds_first_dec_2.hashCode());
		result = prime * result + ((refunds_first_filler_1 == null) ? 0 : refunds_first_filler_1.hashCode());
		result = prime * result + ((refunds_first_filler_10 == null) ? 0 : refunds_first_filler_10.hashCode());
		result = prime * result + ((refunds_first_filler_11 == null) ? 0 : refunds_first_filler_11.hashCode());
		result = prime * result + ((refunds_first_filler_12 == null) ? 0 : refunds_first_filler_12.hashCode());
		result = prime * result + ((refunds_first_filler_13 == null) ? 0 : refunds_first_filler_13.hashCode());
		result = prime * result + ((refunds_first_filler_2 == null) ? 0 : refunds_first_filler_2.hashCode());
		result = prime * result + ((refunds_first_filler_3 == null) ? 0 : refunds_first_filler_3.hashCode());
		result = prime * result + ((refunds_first_filler_4 == null) ? 0 : refunds_first_filler_4.hashCode());
		result = prime * result + ((refunds_first_filler_5 == null) ? 0 : refunds_first_filler_5.hashCode());
		result = prime * result + ((refunds_first_filler_6 == null) ? 0 : refunds_first_filler_6.hashCode());
		result = prime * result + ((refunds_first_filler_7 == null) ? 0 : refunds_first_filler_7.hashCode());
		result = prime * result + ((refunds_first_filler_8 == null) ? 0 : refunds_first_filler_8.hashCode());
		result = prime * result + ((refunds_first_filler_9 == null) ? 0 : refunds_first_filler_9.hashCode());
		result = prime * result
				+ ((refunds_first_geo_tbl_no_995 == null) ? 0 : refunds_first_geo_tbl_no_995.hashCode());
		result = prime * result + ((refunds_first_pct == null) ? 0 : refunds_first_pct.hashCode());
		result = prime * result + ((refunds_non_ref == null) ? 0 : refunds_non_ref.hashCode());
		result = prime * result + ((rules_type == null) ? 0 : rules_type.hashCode());
		result = prime * result + ((tbl_no == null) ? 0 : tbl_no.hashCode());
		result = prime * result + ((text_tbl_no_996 == null) ? 0 : text_tbl_no_996.hashCode());
		result = prime * result + ((unavail == null) ? 0 : unavail.hashCode());
		result = prime * result + ((waiver_1 == null) ? 0 : waiver_1.hashCode());
		result = prime * result + ((waiver_10 == null) ? 0 : waiver_10.hashCode());
		result = prime * result + ((waiver_11 == null) ? 0 : waiver_11.hashCode());
		result = prime * result + ((waiver_12 == null) ? 0 : waiver_12.hashCode());
		result = prime * result + ((waiver_13 == null) ? 0 : waiver_13.hashCode());
		result = prime * result + ((waiver_2 == null) ? 0 : waiver_2.hashCode());
		result = prime * result + ((waiver_7 == null) ? 0 : waiver_7.hashCode());
		result = prime * result + ((waiver_8 == null) ? 0 : waiver_8.hashCode());
		result = prime * result + ((waiver_9 == null) ? 0 : waiver_9.hashCode());
		result = prime * result + ((waiver_appl == null) ? 0 : waiver_appl.hashCode());
		result = prime * result + ((waiver_filler_1 == null) ? 0 : waiver_filler_1.hashCode());
		result = prime * result + ((waiver_filler_2 == null) ? 0 : waiver_filler_2.hashCode());
		result = prime * result + ((waiver_filler_3 == null) ? 0 : waiver_filler_3.hashCode());
		result = prime * result + ((waiver_filler_4 == null) ? 0 : waiver_filler_4.hashCode());
		result = prime * result + ((waiver_filler_5 == null) ? 0 : waiver_filler_5.hashCode());
		result = prime * result + ((waiver_reserved_1 == null) ? 0 : waiver_reserved_1.hashCode());
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
		AtpcoRecord3Cat29 other = (AtpcoRecord3Cat29) obj;
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
		if (date_tbl_no_994 == null) {
			if (other.date_tbl_no_994 != null)
				return false;
		} else if (!date_tbl_no_994.equals(other.date_tbl_no_994))
			return false;
		if (deposit_filler_1 == null) {
			if (other.deposit_filler_1 != null)
				return false;
		} else if (!deposit_filler_1.equals(other.deposit_filler_1))
			return false;
		if (deposit_filler_10 == null) {
			if (other.deposit_filler_10 != null)
				return false;
		} else if (!deposit_filler_10.equals(other.deposit_filler_10))
			return false;
		if (deposit_filler_11 == null) {
			if (other.deposit_filler_11 != null)
				return false;
		} else if (!deposit_filler_11.equals(other.deposit_filler_11))
			return false;
		if (deposit_filler_12 == null) {
			if (other.deposit_filler_12 != null)
				return false;
		} else if (!deposit_filler_12.equals(other.deposit_filler_12))
			return false;
		if (deposit_filler_13 == null) {
			if (other.deposit_filler_13 != null)
				return false;
		} else if (!deposit_filler_13.equals(other.deposit_filler_13))
			return false;
		if (deposit_filler_14 == null) {
			if (other.deposit_filler_14 != null)
				return false;
		} else if (!deposit_filler_14.equals(other.deposit_filler_14))
			return false;
		if (deposit_filler_15 == null) {
			if (other.deposit_filler_15 != null)
				return false;
		} else if (!deposit_filler_15.equals(other.deposit_filler_15))
			return false;
		if (deposit_filler_16 == null) {
			if (other.deposit_filler_16 != null)
				return false;
		} else if (!deposit_filler_16.equals(other.deposit_filler_16))
			return false;
		if (deposit_filler_17 == null) {
			if (other.deposit_filler_17 != null)
				return false;
		} else if (!deposit_filler_17.equals(other.deposit_filler_17))
			return false;
		if (deposit_filler_18 == null) {
			if (other.deposit_filler_18 != null)
				return false;
		} else if (!deposit_filler_18.equals(other.deposit_filler_18))
			return false;
		if (deposit_filler_19 == null) {
			if (other.deposit_filler_19 != null)
				return false;
		} else if (!deposit_filler_19.equals(other.deposit_filler_19))
			return false;
		if (deposit_filler_2 == null) {
			if (other.deposit_filler_2 != null)
				return false;
		} else if (!deposit_filler_2.equals(other.deposit_filler_2))
			return false;
		if (deposit_filler_20 == null) {
			if (other.deposit_filler_20 != null)
				return false;
		} else if (!deposit_filler_20.equals(other.deposit_filler_20))
			return false;
		if (deposit_filler_21 == null) {
			if (other.deposit_filler_21 != null)
				return false;
		} else if (!deposit_filler_21.equals(other.deposit_filler_21))
			return false;
		if (deposit_filler_22 == null) {
			if (other.deposit_filler_22 != null)
				return false;
		} else if (!deposit_filler_22.equals(other.deposit_filler_22))
			return false;
		if (deposit_filler_23 == null) {
			if (other.deposit_filler_23 != null)
				return false;
		} else if (!deposit_filler_23.equals(other.deposit_filler_23))
			return false;
		if (deposit_filler_24 == null) {
			if (other.deposit_filler_24 != null)
				return false;
		} else if (!deposit_filler_24.equals(other.deposit_filler_24))
			return false;
		if (deposit_filler_25 == null) {
			if (other.deposit_filler_25 != null)
				return false;
		} else if (!deposit_filler_25.equals(other.deposit_filler_25))
			return false;
		if (deposit_filler_26 == null) {
			if (other.deposit_filler_26 != null)
				return false;
		} else if (!deposit_filler_26.equals(other.deposit_filler_26))
			return false;
		if (deposit_filler_3 == null) {
			if (other.deposit_filler_3 != null)
				return false;
		} else if (!deposit_filler_3.equals(other.deposit_filler_3))
			return false;
		if (deposit_filler_4 == null) {
			if (other.deposit_filler_4 != null)
				return false;
		} else if (!deposit_filler_4.equals(other.deposit_filler_4))
			return false;
		if (deposit_filler_5 == null) {
			if (other.deposit_filler_5 != null)
				return false;
		} else if (!deposit_filler_5.equals(other.deposit_filler_5))
			return false;
		if (deposit_filler_6 == null) {
			if (other.deposit_filler_6 != null)
				return false;
		} else if (!deposit_filler_6.equals(other.deposit_filler_6))
			return false;
		if (deposit_filler_7 == null) {
			if (other.deposit_filler_7 != null)
				return false;
		} else if (!deposit_filler_7.equals(other.deposit_filler_7))
			return false;
		if (deposit_filler_8 == null) {
			if (other.deposit_filler_8 != null)
				return false;
		} else if (!deposit_filler_8.equals(other.deposit_filler_8))
			return false;
		if (deposit_filler_9 == null) {
			if (other.deposit_filler_9 != null)
				return false;
		} else if (!deposit_filler_9.equals(other.deposit_filler_9))
			return false;
		if (deposit_first_appl == null) {
			if (other.deposit_first_appl != null)
				return false;
		} else if (!deposit_first_appl.equals(other.deposit_first_appl))
			return false;
		if (deposit_first_cur_1 == null) {
			if (other.deposit_first_cur_1 != null)
				return false;
		} else if (!deposit_first_cur_1.equals(other.deposit_first_cur_1))
			return false;
		if (deposit_first_cur_2 == null) {
			if (other.deposit_first_cur_2 != null)
				return false;
		} else if (!deposit_first_cur_2.equals(other.deposit_first_cur_2))
			return false;
		if (deposit_first_days == null) {
			if (other.deposit_first_days != null)
				return false;
		} else if (!deposit_first_days.equals(other.deposit_first_days))
			return false;
		if (deposit_first_dec_1 == null) {
			if (other.deposit_first_dec_1 != null)
				return false;
		} else if (!deposit_first_dec_1.equals(other.deposit_first_dec_1))
			return false;
		if (deposit_first_dec_2 == null) {
			if (other.deposit_first_dec_2 != null)
				return false;
		} else if (!deposit_first_dec_2.equals(other.deposit_first_dec_2))
			return false;
		if (deposit_first_filler_1 == null) {
			if (other.deposit_first_filler_1 != null)
				return false;
		} else if (!deposit_first_filler_1.equals(other.deposit_first_filler_1))
			return false;
		if (deposit_first_geo_tbl_no_995 == null) {
			if (other.deposit_first_geo_tbl_no_995 != null)
				return false;
		} else if (!deposit_first_geo_tbl_no_995.equals(other.deposit_first_geo_tbl_no_995))
			return false;
		if (deposit_first_last_date == null) {
			if (other.deposit_first_last_date != null)
				return false;
		} else if (!deposit_first_last_date.equals(other.deposit_first_last_date))
			return false;
		if (deposit_first_min_amt_1 == null) {
			if (other.deposit_first_min_amt_1 != null)
				return false;
		} else if (!deposit_first_min_amt_1.equals(other.deposit_first_min_amt_1))
			return false;
		if (deposit_first_min_amt_2 == null) {
			if (other.deposit_first_min_amt_2 != null)
				return false;
		} else if (!deposit_first_min_amt_2.equals(other.deposit_first_min_amt_2))
			return false;
		if (deposit_first_pct == null) {
			if (other.deposit_first_pct != null)
				return false;
		} else if (!deposit_first_pct.equals(other.deposit_first_pct))
			return false;
		if (deposit_req == null) {
			if (other.deposit_req != null)
				return false;
		} else if (!deposit_req.equals(other.deposit_req))
			return false;
		if (deposit_seat == null) {
			if (other.deposit_seat != null)
				return false;
		} else if (!deposit_seat.equals(other.deposit_seat))
			return false;
		if (deposit_waiver == null) {
			if (other.deposit_waiver != null)
				return false;
		} else if (!deposit_waiver.equals(other.deposit_waiver))
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
		if (refunds_filler_1 == null) {
			if (other.refunds_filler_1 != null)
				return false;
		} else if (!refunds_filler_1.equals(other.refunds_filler_1))
			return false;
		if (refunds_first_amt_1 == null) {
			if (other.refunds_first_amt_1 != null)
				return false;
		} else if (!refunds_first_amt_1.equals(other.refunds_first_amt_1))
			return false;
		if (refunds_first_amt_2 == null) {
			if (other.refunds_first_amt_2 != null)
				return false;
		} else if (!refunds_first_amt_2.equals(other.refunds_first_amt_2))
			return false;
		if (refunds_first_appl == null) {
			if (other.refunds_first_appl != null)
				return false;
		} else if (!refunds_first_appl.equals(other.refunds_first_appl))
			return false;
		if (refunds_first_cur_1 == null) {
			if (other.refunds_first_cur_1 != null)
				return false;
		} else if (!refunds_first_cur_1.equals(other.refunds_first_cur_1))
			return false;
		if (refunds_first_cur_2 == null) {
			if (other.refunds_first_cur_2 != null)
				return false;
		} else if (!refunds_first_cur_2.equals(other.refunds_first_cur_2))
			return false;
		if (refunds_first_days == null) {
			if (other.refunds_first_days != null)
				return false;
		} else if (!refunds_first_days.equals(other.refunds_first_days))
			return false;
		if (refunds_first_dec_1 == null) {
			if (other.refunds_first_dec_1 != null)
				return false;
		} else if (!refunds_first_dec_1.equals(other.refunds_first_dec_1))
			return false;
		if (refunds_first_dec_2 == null) {
			if (other.refunds_first_dec_2 != null)
				return false;
		} else if (!refunds_first_dec_2.equals(other.refunds_first_dec_2))
			return false;
		if (refunds_first_filler_1 == null) {
			if (other.refunds_first_filler_1 != null)
				return false;
		} else if (!refunds_first_filler_1.equals(other.refunds_first_filler_1))
			return false;
		if (refunds_first_filler_10 == null) {
			if (other.refunds_first_filler_10 != null)
				return false;
		} else if (!refunds_first_filler_10.equals(other.refunds_first_filler_10))
			return false;
		if (refunds_first_filler_11 == null) {
			if (other.refunds_first_filler_11 != null)
				return false;
		} else if (!refunds_first_filler_11.equals(other.refunds_first_filler_11))
			return false;
		if (refunds_first_filler_12 == null) {
			if (other.refunds_first_filler_12 != null)
				return false;
		} else if (!refunds_first_filler_12.equals(other.refunds_first_filler_12))
			return false;
		if (refunds_first_filler_13 == null) {
			if (other.refunds_first_filler_13 != null)
				return false;
		} else if (!refunds_first_filler_13.equals(other.refunds_first_filler_13))
			return false;
		if (refunds_first_filler_2 == null) {
			if (other.refunds_first_filler_2 != null)
				return false;
		} else if (!refunds_first_filler_2.equals(other.refunds_first_filler_2))
			return false;
		if (refunds_first_filler_3 == null) {
			if (other.refunds_first_filler_3 != null)
				return false;
		} else if (!refunds_first_filler_3.equals(other.refunds_first_filler_3))
			return false;
		if (refunds_first_filler_4 == null) {
			if (other.refunds_first_filler_4 != null)
				return false;
		} else if (!refunds_first_filler_4.equals(other.refunds_first_filler_4))
			return false;
		if (refunds_first_filler_5 == null) {
			if (other.refunds_first_filler_5 != null)
				return false;
		} else if (!refunds_first_filler_5.equals(other.refunds_first_filler_5))
			return false;
		if (refunds_first_filler_6 == null) {
			if (other.refunds_first_filler_6 != null)
				return false;
		} else if (!refunds_first_filler_6.equals(other.refunds_first_filler_6))
			return false;
		if (refunds_first_filler_7 == null) {
			if (other.refunds_first_filler_7 != null)
				return false;
		} else if (!refunds_first_filler_7.equals(other.refunds_first_filler_7))
			return false;
		if (refunds_first_filler_8 == null) {
			if (other.refunds_first_filler_8 != null)
				return false;
		} else if (!refunds_first_filler_8.equals(other.refunds_first_filler_8))
			return false;
		if (refunds_first_filler_9 == null) {
			if (other.refunds_first_filler_9 != null)
				return false;
		} else if (!refunds_first_filler_9.equals(other.refunds_first_filler_9))
			return false;
		if (refunds_first_geo_tbl_no_995 == null) {
			if (other.refunds_first_geo_tbl_no_995 != null)
				return false;
		} else if (!refunds_first_geo_tbl_no_995.equals(other.refunds_first_geo_tbl_no_995))
			return false;
		if (refunds_first_pct == null) {
			if (other.refunds_first_pct != null)
				return false;
		} else if (!refunds_first_pct.equals(other.refunds_first_pct))
			return false;
		if (refunds_non_ref == null) {
			if (other.refunds_non_ref != null)
				return false;
		} else if (!refunds_non_ref.equals(other.refunds_non_ref))
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
		if (waiver_1 == null) {
			if (other.waiver_1 != null)
				return false;
		} else if (!waiver_1.equals(other.waiver_1))
			return false;
		if (waiver_10 == null) {
			if (other.waiver_10 != null)
				return false;
		} else if (!waiver_10.equals(other.waiver_10))
			return false;
		if (waiver_11 == null) {
			if (other.waiver_11 != null)
				return false;
		} else if (!waiver_11.equals(other.waiver_11))
			return false;
		if (waiver_12 == null) {
			if (other.waiver_12 != null)
				return false;
		} else if (!waiver_12.equals(other.waiver_12))
			return false;
		if (waiver_13 == null) {
			if (other.waiver_13 != null)
				return false;
		} else if (!waiver_13.equals(other.waiver_13))
			return false;
		if (waiver_2 == null) {
			if (other.waiver_2 != null)
				return false;
		} else if (!waiver_2.equals(other.waiver_2))
			return false;
		if (waiver_7 == null) {
			if (other.waiver_7 != null)
				return false;
		} else if (!waiver_7.equals(other.waiver_7))
			return false;
		if (waiver_8 == null) {
			if (other.waiver_8 != null)
				return false;
		} else if (!waiver_8.equals(other.waiver_8))
			return false;
		if (waiver_9 == null) {
			if (other.waiver_9 != null)
				return false;
		} else if (!waiver_9.equals(other.waiver_9))
			return false;
		if (waiver_appl == null) {
			if (other.waiver_appl != null)
				return false;
		} else if (!waiver_appl.equals(other.waiver_appl))
			return false;
		if (waiver_filler_1 == null) {
			if (other.waiver_filler_1 != null)
				return false;
		} else if (!waiver_filler_1.equals(other.waiver_filler_1))
			return false;
		if (waiver_filler_2 == null) {
			if (other.waiver_filler_2 != null)
				return false;
		} else if (!waiver_filler_2.equals(other.waiver_filler_2))
			return false;
		if (waiver_filler_3 == null) {
			if (other.waiver_filler_3 != null)
				return false;
		} else if (!waiver_filler_3.equals(other.waiver_filler_3))
			return false;
		if (waiver_filler_4 == null) {
			if (other.waiver_filler_4 != null)
				return false;
		} else if (!waiver_filler_4.equals(other.waiver_filler_4))
			return false;
		if (waiver_filler_5 == null) {
			if (other.waiver_filler_5 != null)
				return false;
		} else if (!waiver_filler_5.equals(other.waiver_filler_5))
			return false;
		if (waiver_reserved_1 == null) {
			if (other.waiver_reserved_1 != null)
				return false;
		} else if (!waiver_reserved_1.equals(other.waiver_reserved_1))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoRecord3Cat29 [id=" + id + ", record_sequence=" + record_sequence + ", deposit_first_appl="
				+ deposit_first_appl + ", deposit_filler_16=" + deposit_filler_16 + ", deposit_filler_15="
				+ deposit_filler_15 + ", deposit_filler_14=" + deposit_filler_14 + ", deposit_filler_13="
				+ deposit_filler_13 + ", deposit_filler_19=" + deposit_filler_19 + ", waiver_appl=" + waiver_appl
				+ ", deposit_filler_18=" + deposit_filler_18 + ", deposit_filler_17=" + deposit_filler_17
				+ ", waiver_9=" + waiver_9 + ", waiver_7=" + waiver_7 + ", waiver_8=" + waiver_8 + ", deposit_waiver="
				+ deposit_waiver + ", deposit_filler_11=" + deposit_filler_11 + ", deposit_filler_12="
				+ deposit_filler_12 + ", tbl_no=" + tbl_no + ", unavail=" + unavail + ", deposit_filler_10="
				+ deposit_filler_10 + ", waiver_1=" + waiver_1 + ", rules_type=" + rules_type + ", action=" + action
				+ ", waiver_2=" + waiver_2 + ", deposit_first_last_date=" + deposit_first_last_date
				+ ", deposit_first_filler_1=" + deposit_first_filler_1 + ", deposit_filler_7=" + deposit_filler_7
				+ ", deposit_filler_6=" + deposit_filler_6 + ", deposit_filler_9=" + deposit_filler_9
				+ ", deposit_filler_8=" + deposit_filler_8 + ", date_tbl_no_994=" + date_tbl_no_994 + ", deposit_req="
				+ deposit_req + ", waiver_11=" + waiver_11 + ", waiver_10=" + waiver_10 + ", text_tbl_no_996="
				+ text_tbl_no_996 + ", deposit_filler_1=" + deposit_filler_1 + ", deposit_filler_3=" + deposit_filler_3
				+ ", deposit_filler_2=" + deposit_filler_2 + ", waiver_13=" + waiver_13 + ", deposit_filler_5="
				+ deposit_filler_5 + ", waiver_12=" + waiver_12 + ", deposit_filler_4=" + deposit_filler_4
				+ ", deposit_first_days=" + deposit_first_days + ", waiver_reserved_1=" + waiver_reserved_1
				+ ", rec_type=" + rec_type + ", refunds_first_amt_1=" + refunds_first_amt_1 + ", refunds_first_amt_2="
				+ refunds_first_amt_2 + ", refunds_first_filler_10=" + refunds_first_filler_10
				+ ", refunds_first_filler_13=" + refunds_first_filler_13 + ", refunds_first_filler_11="
				+ refunds_first_filler_11 + ", refunds_first_filler_12=" + refunds_first_filler_12
				+ ", deposit_first_min_amt_2=" + deposit_first_min_amt_2 + ", deposit_first_min_amt_1="
				+ deposit_first_min_amt_1 + ", refunds_first_geo_tbl_no_995=" + refunds_first_geo_tbl_no_995
				+ ", refunds_first_pct=" + refunds_first_pct + ", refunds_first_dec_1=" + refunds_first_dec_1
				+ ", refunds_first_cur_1=" + refunds_first_cur_1 + ", refunds_first_dec_2=" + refunds_first_dec_2
				+ ", waiver_filler_5=" + waiver_filler_5 + ", waiver_filler_4=" + waiver_filler_4
				+ ", deposit_first_pct=" + deposit_first_pct + ", deposit_first_geo_tbl_no_995="
				+ deposit_first_geo_tbl_no_995 + ", refunds_first_filler_8=" + refunds_first_filler_8
				+ ", refunds_first_filler_9=" + refunds_first_filler_9 + ", refunds_first_filler_6="
				+ refunds_first_filler_6 + ", refunds_first_filler_7=" + refunds_first_filler_7
				+ ", refunds_first_filler_4=" + refunds_first_filler_4 + ", refunds_first_filler_5="
				+ refunds_first_filler_5 + ", refunds_first_filler_2=" + refunds_first_filler_2 + ", deposit_filler_23="
				+ deposit_filler_23 + ", refunds_first_filler_3=" + refunds_first_filler_3 + ", deposit_filler_22="
				+ deposit_filler_22 + ", deposit_filler_21=" + deposit_filler_21 + ", refunds_first_filler_1="
				+ refunds_first_filler_1 + ", deposit_filler_20=" + deposit_filler_20 + ", record_batch=" + record_batch
				+ ", refunds_first_days=" + refunds_first_days + ", deposit_first_cur_2=" + deposit_first_cur_2
				+ ", waiver_filler_1=" + waiver_filler_1 + ", cat_no=" + cat_no + ", deposit_first_cur_1="
				+ deposit_first_cur_1 + ", waiver_filler_2=" + waiver_filler_2 + ", waiver_filler_3=" + waiver_filler_3
				+ ", deposit_seat=" + deposit_seat + ", deposit_filler_26=" + deposit_filler_26
				+ ", refunds_first_cur_2=" + refunds_first_cur_2 + ", deposit_filler_24=" + deposit_filler_24
				+ ", deposit_filler_25=" + deposit_filler_25 + ", refunds_first_appl=" + refunds_first_appl
				+ ", refunds_non_ref=" + refunds_non_ref + ", deposit_first_dec_1=" + deposit_first_dec_1
				+ ", deposit_first_dec_2=" + deposit_first_dec_2 + ", refunds_filler_1=" + refunds_filler_1 + "]";
	}
	
	
}

