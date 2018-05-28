package com.atibusinessgroup.fmp.domain.atpco;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_record_3_cat_025")
public class AtpcoRecord3Cat25 {

	@Id
    private String id;
	
	@Field("fare_comp_fare_class")
	private String fare_comp_fare_class;

	@Field("resulting_fare_type_season")
    private String resulting_fare_type_season;

	@Field("record_sequence")
    private String record_sequence;

	@Field("fare_calc_reserved_1")
    private String fare_calc_reserved_1;

	@Field("fare_calc_mileage_max")
    private String fare_calc_mileage_max;

	@Field("psgr_travel_origin_reserved_1")
    private String psgr_travel_origin_reserved_1;

	@Field("resulting_fare_ticket_designator")
    private String resulting_fare_ticket_designator;

	@Field("fare_comp_carrier_code")
    private String fare_comp_carrier_code;

	@Field("psgr_occ_first")
    private String psgr_occ_first;

	@Field("tbl_no")
    private String tbl_no;
	
	@Field("fare_comp_min_fare_1")
    private BigDecimal fare_comp_min_fare_1;

	@Field("unavail")
    private String unavail;

	@Field("fare_calc_mileage_find")
    private String fare_calc_mileage_find;

	@Field("fare_comp_min_fare_2")
    private BigDecimal fare_comp_min_fare_2;

	@Field("resulting_fare_type_fare")
    private String resulting_fare_type_fare;

	@Field("rules_type")
    private String rules_type;

	@Field("action")
    private String action;

	@Field("flt_segs")
    private String flt_segs;

	@Field("resulting_fare_disc_cat")
    private String resulting_fare_disc_cat;

	@Field("resulting_fare_routing_number")
    private String resulting_fare_routing_number;

	@Field("resulting_fare_global")
    private String resulting_fare_global;

	@Field("resulting_fare_reserved_2")
    private String resulting_fare_reserved_2;

	@Field("psgr_travel_origin_geo_type")
    private String psgr_travel_origin_geo_type;

	@Field("resulting_fare_fare_class")
    private String resulting_fare_fare_class;

	@Field("resulting_fare_reserved_1")
    private String resulting_fare_reserved_1;

	@Field("fare_calc_fare_dec_2")
    private String fare_calc_fare_dec_2;

	@Field("fare_calc_fare_dec_1")
    private String fare_calc_fare_dec_1;

	@Field("fare_calc_fare_cur_1")
    private String fare_calc_fare_cur_1;

	@Field("fare_calc_fare_amt_2")
    private BigDecimal fare_calc_fare_amt_2;

	@Field("fare_calc_fare_amt_1")
    private BigDecimal fare_calc_fare_amt_1;

	@Field("fare_calc_fare_cur_2")
    private String fare_calc_fare_cur_2;

	@Field("fare_comp_max_fare_2")
    private BigDecimal fare_comp_max_fare_2;

	@Field("fare_calc_mileage_min")
    private String fare_calc_mileage_min;

	@Field("fare_comp_max_fare_1")
    private BigDecimal fare_comp_max_fare_1;

	@Field("resulting_fare_tdm")
    private String resulting_fare_tdm;

	@Field("date_tbl_no_994")
    private String date_tbl_no_994;

	@Field("base_tbl_no_989")
    private String base_tbl_no_989;

	@Field("text_tbl_no_996")
    private String text_tbl_no_996;

	@Field("resulting_fare_owrt")
    private String resulting_fare_owrt;

	@Field("tarrif_rule")
    private String tarrif_rule;

	@Field("fare_calc_base_fares")
    private String fare_calc_base_fares;

	@Field("psgr_status_geo_type")
    private String psgr_status_geo_type;

	@Field("psgr_occ_last")
    private String psgr_occ_last;

	@Field("psgr_status_appl")
    private String psgr_status_appl;

	@Field("rec_type")
    private String rec_type;

	@Field("resulting_fare_rbd_validation")
    private String resulting_fare_rbd_validation;

	@Field("psgr_travel_origin_reserved_2")
    private String psgr_travel_origin_reserved_2;

	@Field("fare_calc_mileage_percent")
    private String fare_calc_mileage_percent;

	@Field("resulting_fare_tcm")
    private String resulting_fare_tcm;

	@Field("resulting_fare_prc_cat")
    private String resulting_fare_prc_cat;

	@Field("resulting_fare_routing_tarrif")
    private String resulting_fare_routing_tarrif;

	@Field("resulting_fare_ticketing_code")
    private String resulting_fare_ticketing_code;

	@Field("psgr_status_psgr")
    private String psgr_status_psgr;

	@Field("highest")
    private String highest;

	@Field("age_min")
    private String age_min;

	@Field("resulting_fare_type_day_of_week")
    private String resulting_fare_type_day_of_week;

	@Field("psgr_type")
    private String psgr_type;

	@Field("resulting_fare_rbd_999")
    private String resulting_fare_rbd_999;

    @Field("id")
    private String id_;

    @Field("no_disc")
    private String no_disc;

    @Field("category_override_tags")
    private AtpcoRecord3Cat25CategoryOverride category_override_tags;

    @Field("resulting_fare_prime_sector")
    private String resulting_fare_prime_sector;

    @Field("psgr_status_geo_loc")
    private String psgr_status_geo_loc;

    @Field("fare_comp_dec_2")
    private String fare_comp_dec_2;

    @Field("fare_comp_fare_type")
    private String fare_comp_fare_type;

    @Field("fare_comp_dec_1")
    private String fare_comp_dec_1;

    @Field("psgr_travel_origin_tsi")
    private String psgr_travel_origin_tsi;

    @Field("resulting_fare_prime_rbd_6")
    private String resulting_fare_prime_rbd_6;

    @Field("resulting_fare_prime_rbd_7")
    private String resulting_fare_prime_rbd_7;

    @Field("resulting_fare_prime_rbd_8")
    private String resulting_fare_prime_rbd_8;

    @Field("resulting_fare_prime_rbd_2")
    private String resulting_fare_prime_rbd_2;

    @Field("record_batch")
    private String record_batch;

    @Field("resulting_fare_prime_rbd_3")
    private String resulting_fare_prime_rbd_3;

    @Field("resulting_fare_prime_rbd_4")
    private String resulting_fare_prime_rbd_4;

    @Field("age_max")
    private String age_max;

    @Field("resulting_fare_prime_rbd_5")
    private String resulting_fare_prime_rbd_5;

    @Field("cat_no")
    private String cat_no;

    @Field("resulting_fare_prime_rbd_1")
    private String resulting_fare_prime_rbd_1;

    @Field("fare_comp_rules_tarrif")
    private String fare_comp_rules_tarrif;

    @Field("fare_comp_cur_1")
    private String fare_comp_cur_1;

    @Field("fare_comp_cur_2")
    private String fare_comp_cur_2;

    @Field("reserved_1")
    private String reserved_1;

    @Field("trvl_wholly_within_geo_loc")
    private String trvl_wholly_within_geo_loc;

    @Field("trvl_wholly_within_geo_type")
    private String trvl_wholly_within_geo_type;

    @Field("psgr_travel_origin_geo_loc")
    private String psgr_travel_origin_geo_loc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFare_comp_fare_class() {
		return fare_comp_fare_class;
	}

	public void setFare_comp_fare_class(String fare_comp_fare_class) {
		this.fare_comp_fare_class = fare_comp_fare_class;
	}

	public String getResulting_fare_type_season() {
		return resulting_fare_type_season;
	}

	public void setResulting_fare_type_season(String resulting_fare_type_season) {
		this.resulting_fare_type_season = resulting_fare_type_season;
	}

	public String getRecord_sequence() {
		return record_sequence;
	}

	public void setRecord_sequence(String record_sequence) {
		this.record_sequence = record_sequence;
	}

	public String getFare_calc_reserved_1() {
		return fare_calc_reserved_1;
	}

	public void setFare_calc_reserved_1(String fare_calc_reserved_1) {
		this.fare_calc_reserved_1 = fare_calc_reserved_1;
	}

	public String getFare_calc_mileage_max() {
		return fare_calc_mileage_max;
	}

	public void setFare_calc_mileage_max(String fare_calc_mileage_max) {
		this.fare_calc_mileage_max = fare_calc_mileage_max;
	}

	public String getPsgr_travel_origin_reserved_1() {
		return psgr_travel_origin_reserved_1;
	}

	public void setPsgr_travel_origin_reserved_1(String psgr_travel_origin_reserved_1) {
		this.psgr_travel_origin_reserved_1 = psgr_travel_origin_reserved_1;
	}

	public String getResulting_fare_ticket_designator() {
		return resulting_fare_ticket_designator;
	}

	public void setResulting_fare_ticket_designator(String resulting_fare_ticket_designator) {
		this.resulting_fare_ticket_designator = resulting_fare_ticket_designator;
	}

	public String getFare_comp_carrier_code() {
		return fare_comp_carrier_code;
	}

	public void setFare_comp_carrier_code(String fare_comp_carrier_code) {
		this.fare_comp_carrier_code = fare_comp_carrier_code;
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

	public BigDecimal getFare_comp_min_fare_1() {
		return fare_comp_min_fare_1;
	}

	public void setFare_comp_min_fare_1(BigDecimal fare_comp_min_fare_1) {
		this.fare_comp_min_fare_1 = fare_comp_min_fare_1;
	}

	public String getUnavail() {
		return unavail;
	}

	public void setUnavail(String unavail) {
		this.unavail = unavail;
	}

	public String getFare_calc_mileage_find() {
		return fare_calc_mileage_find;
	}

	public void setFare_calc_mileage_find(String fare_calc_mileage_find) {
		this.fare_calc_mileage_find = fare_calc_mileage_find;
	}

	public BigDecimal getFare_comp_min_fare_2() {
		return fare_comp_min_fare_2;
	}

	public void setFare_comp_min_fare_2(BigDecimal fare_comp_min_fare_2) {
		this.fare_comp_min_fare_2 = fare_comp_min_fare_2;
	}

	public String getResulting_fare_type_fare() {
		return resulting_fare_type_fare;
	}

	public void setResulting_fare_type_fare(String resulting_fare_type_fare) {
		this.resulting_fare_type_fare = resulting_fare_type_fare;
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

	public String getFlt_segs() {
		return flt_segs;
	}

	public void setFlt_segs(String flt_segs) {
		this.flt_segs = flt_segs;
	}

	public String getResulting_fare_disc_cat() {
		return resulting_fare_disc_cat;
	}

	public void setResulting_fare_disc_cat(String resulting_fare_disc_cat) {
		this.resulting_fare_disc_cat = resulting_fare_disc_cat;
	}

	public String getResulting_fare_routing_number() {
		return resulting_fare_routing_number;
	}

	public void setResulting_fare_routing_number(String resulting_fare_routing_number) {
		this.resulting_fare_routing_number = resulting_fare_routing_number;
	}

	public String getResulting_fare_global() {
		return resulting_fare_global;
	}

	public void setResulting_fare_global(String resulting_fare_global) {
		this.resulting_fare_global = resulting_fare_global;
	}

	public String getResulting_fare_reserved_2() {
		return resulting_fare_reserved_2;
	}

	public void setResulting_fare_reserved_2(String resulting_fare_reserved_2) {
		this.resulting_fare_reserved_2 = resulting_fare_reserved_2;
	}

	public String getPsgr_travel_origin_geo_type() {
		return psgr_travel_origin_geo_type;
	}

	public void setPsgr_travel_origin_geo_type(String psgr_travel_origin_geo_type) {
		this.psgr_travel_origin_geo_type = psgr_travel_origin_geo_type;
	}

	public String getResulting_fare_fare_class() {
		return resulting_fare_fare_class;
	}

	public void setResulting_fare_fare_class(String resulting_fare_fare_class) {
		this.resulting_fare_fare_class = resulting_fare_fare_class;
	}

	public String getResulting_fare_reserved_1() {
		return resulting_fare_reserved_1;
	}

	public void setResulting_fare_reserved_1(String resulting_fare_reserved_1) {
		this.resulting_fare_reserved_1 = resulting_fare_reserved_1;
	}

	public String getFare_calc_fare_dec_2() {
		return fare_calc_fare_dec_2;
	}

	public void setFare_calc_fare_dec_2(String fare_calc_fare_dec_2) {
		this.fare_calc_fare_dec_2 = fare_calc_fare_dec_2;
	}

	public String getFare_calc_fare_dec_1() {
		return fare_calc_fare_dec_1;
	}

	public void setFare_calc_fare_dec_1(String fare_calc_fare_dec_1) {
		this.fare_calc_fare_dec_1 = fare_calc_fare_dec_1;
	}

	public String getFare_calc_fare_cur_1() {
		return fare_calc_fare_cur_1;
	}

	public void setFare_calc_fare_cur_1(String fare_calc_fare_cur_1) {
		this.fare_calc_fare_cur_1 = fare_calc_fare_cur_1;
	}

	public BigDecimal getFare_calc_fare_amt_2() {
		return fare_calc_fare_amt_2;
	}

	public void setFare_calc_fare_amt_2(BigDecimal fare_calc_fare_amt_2) {
		this.fare_calc_fare_amt_2 = fare_calc_fare_amt_2;
	}

	public BigDecimal getFare_calc_fare_amt_1() {
		return fare_calc_fare_amt_1;
	}

	public void setFare_calc_fare_amt_1(BigDecimal fare_calc_fare_amt_1) {
		this.fare_calc_fare_amt_1 = fare_calc_fare_amt_1;
	}

	public String getFare_calc_fare_cur_2() {
		return fare_calc_fare_cur_2;
	}

	public void setFare_calc_fare_cur_2(String fare_calc_fare_cur_2) {
		this.fare_calc_fare_cur_2 = fare_calc_fare_cur_2;
	}

	public BigDecimal getFare_comp_max_fare_2() {
		return fare_comp_max_fare_2;
	}

	public void setFare_comp_max_fare_2(BigDecimal fare_comp_max_fare_2) {
		this.fare_comp_max_fare_2 = fare_comp_max_fare_2;
	}

	public String getFare_calc_mileage_min() {
		return fare_calc_mileage_min;
	}

	public void setFare_calc_mileage_min(String fare_calc_mileage_min) {
		this.fare_calc_mileage_min = fare_calc_mileage_min;
	}

	public BigDecimal getFare_comp_max_fare_1() {
		return fare_comp_max_fare_1;
	}

	public void setFare_comp_max_fare_1(BigDecimal fare_comp_max_fare_1) {
		this.fare_comp_max_fare_1 = fare_comp_max_fare_1;
	}

	public String getResulting_fare_tdm() {
		return resulting_fare_tdm;
	}

	public void setResulting_fare_tdm(String resulting_fare_tdm) {
		this.resulting_fare_tdm = resulting_fare_tdm;
	}

	public String getDate_tbl_no_994() {
		return date_tbl_no_994;
	}

	public void setDate_tbl_no_994(String date_tbl_no_994) {
		this.date_tbl_no_994 = date_tbl_no_994;
	}

	public String getBase_tbl_no_989() {
		return base_tbl_no_989;
	}

	public void setBase_tbl_no_989(String base_tbl_no_989) {
		this.base_tbl_no_989 = base_tbl_no_989;
	}

	public String getText_tbl_no_996() {
		return text_tbl_no_996;
	}

	public void setText_tbl_no_996(String text_tbl_no_996) {
		this.text_tbl_no_996 = text_tbl_no_996;
	}

	public String getResulting_fare_owrt() {
		return resulting_fare_owrt;
	}

	public void setResulting_fare_owrt(String resulting_fare_owrt) {
		this.resulting_fare_owrt = resulting_fare_owrt;
	}

	public String getTarrif_rule() {
		return tarrif_rule;
	}

	public void setTarrif_rule(String tarrif_rule) {
		this.tarrif_rule = tarrif_rule;
	}

	public String getFare_calc_base_fares() {
		return fare_calc_base_fares;
	}

	public void setFare_calc_base_fares(String fare_calc_base_fares) {
		this.fare_calc_base_fares = fare_calc_base_fares;
	}

	public String getPsgr_status_geo_type() {
		return psgr_status_geo_type;
	}

	public void setPsgr_status_geo_type(String psgr_status_geo_type) {
		this.psgr_status_geo_type = psgr_status_geo_type;
	}

	public String getPsgr_occ_last() {
		return psgr_occ_last;
	}

	public void setPsgr_occ_last(String psgr_occ_last) {
		this.psgr_occ_last = psgr_occ_last;
	}

	public String getPsgr_status_appl() {
		return psgr_status_appl;
	}

	public void setPsgr_status_appl(String psgr_status_appl) {
		this.psgr_status_appl = psgr_status_appl;
	}

	public String getRec_type() {
		return rec_type;
	}

	public void setRec_type(String rec_type) {
		this.rec_type = rec_type;
	}

	public String getResulting_fare_rbd_validation() {
		return resulting_fare_rbd_validation;
	}

	public void setResulting_fare_rbd_validation(String resulting_fare_rbd_validation) {
		this.resulting_fare_rbd_validation = resulting_fare_rbd_validation;
	}

	public String getPsgr_travel_origin_reserved_2() {
		return psgr_travel_origin_reserved_2;
	}

	public void setPsgr_travel_origin_reserved_2(String psgr_travel_origin_reserved_2) {
		this.psgr_travel_origin_reserved_2 = psgr_travel_origin_reserved_2;
	}

	public String getFare_calc_mileage_percent() {
		return fare_calc_mileage_percent;
	}

	public void setFare_calc_mileage_percent(String fare_calc_mileage_percent) {
		this.fare_calc_mileage_percent = fare_calc_mileage_percent;
	}

	public String getResulting_fare_tcm() {
		return resulting_fare_tcm;
	}

	public void setResulting_fare_tcm(String resulting_fare_tcm) {
		this.resulting_fare_tcm = resulting_fare_tcm;
	}

	public String getResulting_fare_prc_cat() {
		return resulting_fare_prc_cat;
	}

	public void setResulting_fare_prc_cat(String resulting_fare_prc_cat) {
		this.resulting_fare_prc_cat = resulting_fare_prc_cat;
	}

	public String getResulting_fare_routing_tarrif() {
		return resulting_fare_routing_tarrif;
	}

	public void setResulting_fare_routing_tarrif(String resulting_fare_routing_tarrif) {
		this.resulting_fare_routing_tarrif = resulting_fare_routing_tarrif;
	}

	public String getResulting_fare_ticketing_code() {
		return resulting_fare_ticketing_code;
	}

	public void setResulting_fare_ticketing_code(String resulting_fare_ticketing_code) {
		this.resulting_fare_ticketing_code = resulting_fare_ticketing_code;
	}

	public String getPsgr_status_psgr() {
		return psgr_status_psgr;
	}

	public void setPsgr_status_psgr(String psgr_status_psgr) {
		this.psgr_status_psgr = psgr_status_psgr;
	}

	public String getHighest() {
		return highest;
	}

	public void setHighest(String highest) {
		this.highest = highest;
	}

	public String getAge_min() {
		return age_min;
	}

	public void setAge_min(String age_min) {
		this.age_min = age_min;
	}

	public String getResulting_fare_type_day_of_week() {
		return resulting_fare_type_day_of_week;
	}

	public void setResulting_fare_type_day_of_week(String resulting_fare_type_day_of_week) {
		this.resulting_fare_type_day_of_week = resulting_fare_type_day_of_week;
	}

	public String getPsgr_type() {
		return psgr_type;
	}

	public void setPsgr_type(String psgr_type) {
		this.psgr_type = psgr_type;
	}

	public String getResulting_fare_rbd_999() {
		return resulting_fare_rbd_999;
	}

	public void setResulting_fare_rbd_999(String resulting_fare_rbd_999) {
		this.resulting_fare_rbd_999 = resulting_fare_rbd_999;
	}

	public String getId_() {
		return id_;
	}

	public void setId_(String id_) {
		this.id_ = id_;
	}

	public String getNo_disc() {
		return no_disc;
	}

	public void setNo_disc(String no_disc) {
		this.no_disc = no_disc;
	}

	public AtpcoRecord3Cat25CategoryOverride getCategory_override_tags() {
		return category_override_tags;
	}

	public void setCategory_override_tags(AtpcoRecord3Cat25CategoryOverride category_override_tags) {
		this.category_override_tags = category_override_tags;
	}

	public String getResulting_fare_prime_sector() {
		return resulting_fare_prime_sector;
	}

	public void setResulting_fare_prime_sector(String resulting_fare_prime_sector) {
		this.resulting_fare_prime_sector = resulting_fare_prime_sector;
	}

	public String getPsgr_status_geo_loc() {
		return psgr_status_geo_loc;
	}

	public void setPsgr_status_geo_loc(String psgr_status_geo_loc) {
		this.psgr_status_geo_loc = psgr_status_geo_loc;
	}

	public String getFare_comp_dec_2() {
		return fare_comp_dec_2;
	}

	public void setFare_comp_dec_2(String fare_comp_dec_2) {
		this.fare_comp_dec_2 = fare_comp_dec_2;
	}

	public String getFare_comp_fare_type() {
		return fare_comp_fare_type;
	}

	public void setFare_comp_fare_type(String fare_comp_fare_type) {
		this.fare_comp_fare_type = fare_comp_fare_type;
	}

	public String getFare_comp_dec_1() {
		return fare_comp_dec_1;
	}

	public void setFare_comp_dec_1(String fare_comp_dec_1) {
		this.fare_comp_dec_1 = fare_comp_dec_1;
	}

	public String getPsgr_travel_origin_tsi() {
		return psgr_travel_origin_tsi;
	}

	public void setPsgr_travel_origin_tsi(String psgr_travel_origin_tsi) {
		this.psgr_travel_origin_tsi = psgr_travel_origin_tsi;
	}

	public String getResulting_fare_prime_rbd_6() {
		return resulting_fare_prime_rbd_6;
	}

	public void setResulting_fare_prime_rbd_6(String resulting_fare_prime_rbd_6) {
		this.resulting_fare_prime_rbd_6 = resulting_fare_prime_rbd_6;
	}

	public String getResulting_fare_prime_rbd_7() {
		return resulting_fare_prime_rbd_7;
	}

	public void setResulting_fare_prime_rbd_7(String resulting_fare_prime_rbd_7) {
		this.resulting_fare_prime_rbd_7 = resulting_fare_prime_rbd_7;
	}

	public String getResulting_fare_prime_rbd_8() {
		return resulting_fare_prime_rbd_8;
	}

	public void setResulting_fare_prime_rbd_8(String resulting_fare_prime_rbd_8) {
		this.resulting_fare_prime_rbd_8 = resulting_fare_prime_rbd_8;
	}

	public String getResulting_fare_prime_rbd_2() {
		return resulting_fare_prime_rbd_2;
	}

	public void setResulting_fare_prime_rbd_2(String resulting_fare_prime_rbd_2) {
		this.resulting_fare_prime_rbd_2 = resulting_fare_prime_rbd_2;
	}

	public String getRecord_batch() {
		return record_batch;
	}

	public void setRecord_batch(String record_batch) {
		this.record_batch = record_batch;
	}

	public String getResulting_fare_prime_rbd_3() {
		return resulting_fare_prime_rbd_3;
	}

	public void setResulting_fare_prime_rbd_3(String resulting_fare_prime_rbd_3) {
		this.resulting_fare_prime_rbd_3 = resulting_fare_prime_rbd_3;
	}

	public String getResulting_fare_prime_rbd_4() {
		return resulting_fare_prime_rbd_4;
	}

	public void setResulting_fare_prime_rbd_4(String resulting_fare_prime_rbd_4) {
		this.resulting_fare_prime_rbd_4 = resulting_fare_prime_rbd_4;
	}

	public String getAge_max() {
		return age_max;
	}

	public void setAge_max(String age_max) {
		this.age_max = age_max;
	}

	public String getResulting_fare_prime_rbd_5() {
		return resulting_fare_prime_rbd_5;
	}

	public void setResulting_fare_prime_rbd_5(String resulting_fare_prime_rbd_5) {
		this.resulting_fare_prime_rbd_5 = resulting_fare_prime_rbd_5;
	}

	public String getCat_no() {
		return cat_no;
	}

	public void setCat_no(String cat_no) {
		this.cat_no = cat_no;
	}

	public String getResulting_fare_prime_rbd_1() {
		return resulting_fare_prime_rbd_1;
	}

	public void setResulting_fare_prime_rbd_1(String resulting_fare_prime_rbd_1) {
		this.resulting_fare_prime_rbd_1 = resulting_fare_prime_rbd_1;
	}

	public String getFare_comp_rules_tarrif() {
		return fare_comp_rules_tarrif;
	}

	public void setFare_comp_rules_tarrif(String fare_comp_rules_tarrif) {
		this.fare_comp_rules_tarrif = fare_comp_rules_tarrif;
	}

	public String getFare_comp_cur_1() {
		return fare_comp_cur_1;
	}

	public void setFare_comp_cur_1(String fare_comp_cur_1) {
		this.fare_comp_cur_1 = fare_comp_cur_1;
	}

	public String getFare_comp_cur_2() {
		return fare_comp_cur_2;
	}

	public void setFare_comp_cur_2(String fare_comp_cur_2) {
		this.fare_comp_cur_2 = fare_comp_cur_2;
	}

	public String getReserved_1() {
		return reserved_1;
	}

	public void setReserved_1(String reserved_1) {
		this.reserved_1 = reserved_1;
	}

	public String getTrvl_wholly_within_geo_loc() {
		return trvl_wholly_within_geo_loc;
	}

	public void setTrvl_wholly_within_geo_loc(String trvl_wholly_within_geo_loc) {
		this.trvl_wholly_within_geo_loc = trvl_wholly_within_geo_loc;
	}

	public String getTrvl_wholly_within_geo_type() {
		return trvl_wholly_within_geo_type;
	}

	public void setTrvl_wholly_within_geo_type(String trvl_wholly_within_geo_type) {
		this.trvl_wholly_within_geo_type = trvl_wholly_within_geo_type;
	}

	public String getPsgr_travel_origin_geo_loc() {
		return psgr_travel_origin_geo_loc;
	}

	public void setPsgr_travel_origin_geo_loc(String psgr_travel_origin_geo_loc) {
		this.psgr_travel_origin_geo_loc = psgr_travel_origin_geo_loc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((age_max == null) ? 0 : age_max.hashCode());
		result = prime * result + ((age_min == null) ? 0 : age_min.hashCode());
		result = prime * result + ((base_tbl_no_989 == null) ? 0 : base_tbl_no_989.hashCode());
		result = prime * result + ((cat_no == null) ? 0 : cat_no.hashCode());
		result = prime * result + ((category_override_tags == null) ? 0 : category_override_tags.hashCode());
		result = prime * result + ((date_tbl_no_994 == null) ? 0 : date_tbl_no_994.hashCode());
		result = prime * result + ((fare_calc_base_fares == null) ? 0 : fare_calc_base_fares.hashCode());
		result = prime * result + ((fare_calc_fare_amt_1 == null) ? 0 : fare_calc_fare_amt_1.hashCode());
		result = prime * result + ((fare_calc_fare_amt_2 == null) ? 0 : fare_calc_fare_amt_2.hashCode());
		result = prime * result + ((fare_calc_fare_cur_1 == null) ? 0 : fare_calc_fare_cur_1.hashCode());
		result = prime * result + ((fare_calc_fare_cur_2 == null) ? 0 : fare_calc_fare_cur_2.hashCode());
		result = prime * result + ((fare_calc_fare_dec_1 == null) ? 0 : fare_calc_fare_dec_1.hashCode());
		result = prime * result + ((fare_calc_fare_dec_2 == null) ? 0 : fare_calc_fare_dec_2.hashCode());
		result = prime * result + ((fare_calc_mileage_find == null) ? 0 : fare_calc_mileage_find.hashCode());
		result = prime * result + ((fare_calc_mileage_max == null) ? 0 : fare_calc_mileage_max.hashCode());
		result = prime * result + ((fare_calc_mileage_min == null) ? 0 : fare_calc_mileage_min.hashCode());
		result = prime * result + ((fare_calc_mileage_percent == null) ? 0 : fare_calc_mileage_percent.hashCode());
		result = prime * result + ((fare_calc_reserved_1 == null) ? 0 : fare_calc_reserved_1.hashCode());
		result = prime * result + ((fare_comp_carrier_code == null) ? 0 : fare_comp_carrier_code.hashCode());
		result = prime * result + ((fare_comp_cur_1 == null) ? 0 : fare_comp_cur_1.hashCode());
		result = prime * result + ((fare_comp_cur_2 == null) ? 0 : fare_comp_cur_2.hashCode());
		result = prime * result + ((fare_comp_dec_1 == null) ? 0 : fare_comp_dec_1.hashCode());
		result = prime * result + ((fare_comp_dec_2 == null) ? 0 : fare_comp_dec_2.hashCode());
		result = prime * result + ((fare_comp_fare_class == null) ? 0 : fare_comp_fare_class.hashCode());
		result = prime * result + ((fare_comp_fare_type == null) ? 0 : fare_comp_fare_type.hashCode());
		result = prime * result + ((fare_comp_max_fare_1 == null) ? 0 : fare_comp_max_fare_1.hashCode());
		result = prime * result + ((fare_comp_max_fare_2 == null) ? 0 : fare_comp_max_fare_2.hashCode());
		result = prime * result + ((fare_comp_min_fare_1 == null) ? 0 : fare_comp_min_fare_1.hashCode());
		result = prime * result + ((fare_comp_min_fare_2 == null) ? 0 : fare_comp_min_fare_2.hashCode());
		result = prime * result + ((fare_comp_rules_tarrif == null) ? 0 : fare_comp_rules_tarrif.hashCode());
		result = prime * result + ((flt_segs == null) ? 0 : flt_segs.hashCode());
		result = prime * result + ((highest == null) ? 0 : highest.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_ == null) ? 0 : id_.hashCode());
		result = prime * result + ((no_disc == null) ? 0 : no_disc.hashCode());
		result = prime * result + ((psgr_occ_first == null) ? 0 : psgr_occ_first.hashCode());
		result = prime * result + ((psgr_occ_last == null) ? 0 : psgr_occ_last.hashCode());
		result = prime * result + ((psgr_status_appl == null) ? 0 : psgr_status_appl.hashCode());
		result = prime * result + ((psgr_status_geo_loc == null) ? 0 : psgr_status_geo_loc.hashCode());
		result = prime * result + ((psgr_status_geo_type == null) ? 0 : psgr_status_geo_type.hashCode());
		result = prime * result + ((psgr_status_psgr == null) ? 0 : psgr_status_psgr.hashCode());
		result = prime * result + ((psgr_travel_origin_geo_loc == null) ? 0 : psgr_travel_origin_geo_loc.hashCode());
		result = prime * result + ((psgr_travel_origin_geo_type == null) ? 0 : psgr_travel_origin_geo_type.hashCode());
		result = prime * result
				+ ((psgr_travel_origin_reserved_1 == null) ? 0 : psgr_travel_origin_reserved_1.hashCode());
		result = prime * result
				+ ((psgr_travel_origin_reserved_2 == null) ? 0 : psgr_travel_origin_reserved_2.hashCode());
		result = prime * result + ((psgr_travel_origin_tsi == null) ? 0 : psgr_travel_origin_tsi.hashCode());
		result = prime * result + ((psgr_type == null) ? 0 : psgr_type.hashCode());
		result = prime * result + ((rec_type == null) ? 0 : rec_type.hashCode());
		result = prime * result + ((record_batch == null) ? 0 : record_batch.hashCode());
		result = prime * result + ((record_sequence == null) ? 0 : record_sequence.hashCode());
		result = prime * result + ((reserved_1 == null) ? 0 : reserved_1.hashCode());
		result = prime * result + ((resulting_fare_disc_cat == null) ? 0 : resulting_fare_disc_cat.hashCode());
		result = prime * result + ((resulting_fare_fare_class == null) ? 0 : resulting_fare_fare_class.hashCode());
		result = prime * result + ((resulting_fare_global == null) ? 0 : resulting_fare_global.hashCode());
		result = prime * result + ((resulting_fare_owrt == null) ? 0 : resulting_fare_owrt.hashCode());
		result = prime * result + ((resulting_fare_prc_cat == null) ? 0 : resulting_fare_prc_cat.hashCode());
		result = prime * result + ((resulting_fare_prime_rbd_1 == null) ? 0 : resulting_fare_prime_rbd_1.hashCode());
		result = prime * result + ((resulting_fare_prime_rbd_2 == null) ? 0 : resulting_fare_prime_rbd_2.hashCode());
		result = prime * result + ((resulting_fare_prime_rbd_3 == null) ? 0 : resulting_fare_prime_rbd_3.hashCode());
		result = prime * result + ((resulting_fare_prime_rbd_4 == null) ? 0 : resulting_fare_prime_rbd_4.hashCode());
		result = prime * result + ((resulting_fare_prime_rbd_5 == null) ? 0 : resulting_fare_prime_rbd_5.hashCode());
		result = prime * result + ((resulting_fare_prime_rbd_6 == null) ? 0 : resulting_fare_prime_rbd_6.hashCode());
		result = prime * result + ((resulting_fare_prime_rbd_7 == null) ? 0 : resulting_fare_prime_rbd_7.hashCode());
		result = prime * result + ((resulting_fare_prime_rbd_8 == null) ? 0 : resulting_fare_prime_rbd_8.hashCode());
		result = prime * result + ((resulting_fare_prime_sector == null) ? 0 : resulting_fare_prime_sector.hashCode());
		result = prime * result + ((resulting_fare_rbd_999 == null) ? 0 : resulting_fare_rbd_999.hashCode());
		result = prime * result
				+ ((resulting_fare_rbd_validation == null) ? 0 : resulting_fare_rbd_validation.hashCode());
		result = prime * result + ((resulting_fare_reserved_1 == null) ? 0 : resulting_fare_reserved_1.hashCode());
		result = prime * result + ((resulting_fare_reserved_2 == null) ? 0 : resulting_fare_reserved_2.hashCode());
		result = prime * result
				+ ((resulting_fare_routing_number == null) ? 0 : resulting_fare_routing_number.hashCode());
		result = prime * result
				+ ((resulting_fare_routing_tarrif == null) ? 0 : resulting_fare_routing_tarrif.hashCode());
		result = prime * result + ((resulting_fare_tcm == null) ? 0 : resulting_fare_tcm.hashCode());
		result = prime * result + ((resulting_fare_tdm == null) ? 0 : resulting_fare_tdm.hashCode());
		result = prime * result
				+ ((resulting_fare_ticket_designator == null) ? 0 : resulting_fare_ticket_designator.hashCode());
		result = prime * result
				+ ((resulting_fare_ticketing_code == null) ? 0 : resulting_fare_ticketing_code.hashCode());
		result = prime * result
				+ ((resulting_fare_type_day_of_week == null) ? 0 : resulting_fare_type_day_of_week.hashCode());
		result = prime * result + ((resulting_fare_type_fare == null) ? 0 : resulting_fare_type_fare.hashCode());
		result = prime * result + ((resulting_fare_type_season == null) ? 0 : resulting_fare_type_season.hashCode());
		result = prime * result + ((rules_type == null) ? 0 : rules_type.hashCode());
		result = prime * result + ((tarrif_rule == null) ? 0 : tarrif_rule.hashCode());
		result = prime * result + ((tbl_no == null) ? 0 : tbl_no.hashCode());
		result = prime * result + ((text_tbl_no_996 == null) ? 0 : text_tbl_no_996.hashCode());
		result = prime * result + ((trvl_wholly_within_geo_loc == null) ? 0 : trvl_wholly_within_geo_loc.hashCode());
		result = prime * result + ((trvl_wholly_within_geo_type == null) ? 0 : trvl_wholly_within_geo_type.hashCode());
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
		AtpcoRecord3Cat25 other = (AtpcoRecord3Cat25) obj;
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
		if (base_tbl_no_989 == null) {
			if (other.base_tbl_no_989 != null)
				return false;
		} else if (!base_tbl_no_989.equals(other.base_tbl_no_989))
			return false;
		if (cat_no == null) {
			if (other.cat_no != null)
				return false;
		} else if (!cat_no.equals(other.cat_no))
			return false;
		if (category_override_tags == null) {
			if (other.category_override_tags != null)
				return false;
		} else if (!category_override_tags.equals(other.category_override_tags))
			return false;
		if (date_tbl_no_994 == null) {
			if (other.date_tbl_no_994 != null)
				return false;
		} else if (!date_tbl_no_994.equals(other.date_tbl_no_994))
			return false;
		if (fare_calc_base_fares == null) {
			if (other.fare_calc_base_fares != null)
				return false;
		} else if (!fare_calc_base_fares.equals(other.fare_calc_base_fares))
			return false;
		if (fare_calc_fare_amt_1 == null) {
			if (other.fare_calc_fare_amt_1 != null)
				return false;
		} else if (!fare_calc_fare_amt_1.equals(other.fare_calc_fare_amt_1))
			return false;
		if (fare_calc_fare_amt_2 == null) {
			if (other.fare_calc_fare_amt_2 != null)
				return false;
		} else if (!fare_calc_fare_amt_2.equals(other.fare_calc_fare_amt_2))
			return false;
		if (fare_calc_fare_cur_1 == null) {
			if (other.fare_calc_fare_cur_1 != null)
				return false;
		} else if (!fare_calc_fare_cur_1.equals(other.fare_calc_fare_cur_1))
			return false;
		if (fare_calc_fare_cur_2 == null) {
			if (other.fare_calc_fare_cur_2 != null)
				return false;
		} else if (!fare_calc_fare_cur_2.equals(other.fare_calc_fare_cur_2))
			return false;
		if (fare_calc_fare_dec_1 == null) {
			if (other.fare_calc_fare_dec_1 != null)
				return false;
		} else if (!fare_calc_fare_dec_1.equals(other.fare_calc_fare_dec_1))
			return false;
		if (fare_calc_fare_dec_2 == null) {
			if (other.fare_calc_fare_dec_2 != null)
				return false;
		} else if (!fare_calc_fare_dec_2.equals(other.fare_calc_fare_dec_2))
			return false;
		if (fare_calc_mileage_find == null) {
			if (other.fare_calc_mileage_find != null)
				return false;
		} else if (!fare_calc_mileage_find.equals(other.fare_calc_mileage_find))
			return false;
		if (fare_calc_mileage_max == null) {
			if (other.fare_calc_mileage_max != null)
				return false;
		} else if (!fare_calc_mileage_max.equals(other.fare_calc_mileage_max))
			return false;
		if (fare_calc_mileage_min == null) {
			if (other.fare_calc_mileage_min != null)
				return false;
		} else if (!fare_calc_mileage_min.equals(other.fare_calc_mileage_min))
			return false;
		if (fare_calc_mileage_percent == null) {
			if (other.fare_calc_mileage_percent != null)
				return false;
		} else if (!fare_calc_mileage_percent.equals(other.fare_calc_mileage_percent))
			return false;
		if (fare_calc_reserved_1 == null) {
			if (other.fare_calc_reserved_1 != null)
				return false;
		} else if (!fare_calc_reserved_1.equals(other.fare_calc_reserved_1))
			return false;
		if (fare_comp_carrier_code == null) {
			if (other.fare_comp_carrier_code != null)
				return false;
		} else if (!fare_comp_carrier_code.equals(other.fare_comp_carrier_code))
			return false;
		if (fare_comp_cur_1 == null) {
			if (other.fare_comp_cur_1 != null)
				return false;
		} else if (!fare_comp_cur_1.equals(other.fare_comp_cur_1))
			return false;
		if (fare_comp_cur_2 == null) {
			if (other.fare_comp_cur_2 != null)
				return false;
		} else if (!fare_comp_cur_2.equals(other.fare_comp_cur_2))
			return false;
		if (fare_comp_dec_1 == null) {
			if (other.fare_comp_dec_1 != null)
				return false;
		} else if (!fare_comp_dec_1.equals(other.fare_comp_dec_1))
			return false;
		if (fare_comp_dec_2 == null) {
			if (other.fare_comp_dec_2 != null)
				return false;
		} else if (!fare_comp_dec_2.equals(other.fare_comp_dec_2))
			return false;
		if (fare_comp_fare_class == null) {
			if (other.fare_comp_fare_class != null)
				return false;
		} else if (!fare_comp_fare_class.equals(other.fare_comp_fare_class))
			return false;
		if (fare_comp_fare_type == null) {
			if (other.fare_comp_fare_type != null)
				return false;
		} else if (!fare_comp_fare_type.equals(other.fare_comp_fare_type))
			return false;
		if (fare_comp_max_fare_1 == null) {
			if (other.fare_comp_max_fare_1 != null)
				return false;
		} else if (!fare_comp_max_fare_1.equals(other.fare_comp_max_fare_1))
			return false;
		if (fare_comp_max_fare_2 == null) {
			if (other.fare_comp_max_fare_2 != null)
				return false;
		} else if (!fare_comp_max_fare_2.equals(other.fare_comp_max_fare_2))
			return false;
		if (fare_comp_min_fare_1 == null) {
			if (other.fare_comp_min_fare_1 != null)
				return false;
		} else if (!fare_comp_min_fare_1.equals(other.fare_comp_min_fare_1))
			return false;
		if (fare_comp_min_fare_2 == null) {
			if (other.fare_comp_min_fare_2 != null)
				return false;
		} else if (!fare_comp_min_fare_2.equals(other.fare_comp_min_fare_2))
			return false;
		if (fare_comp_rules_tarrif == null) {
			if (other.fare_comp_rules_tarrif != null)
				return false;
		} else if (!fare_comp_rules_tarrif.equals(other.fare_comp_rules_tarrif))
			return false;
		if (flt_segs == null) {
			if (other.flt_segs != null)
				return false;
		} else if (!flt_segs.equals(other.flt_segs))
			return false;
		if (highest == null) {
			if (other.highest != null)
				return false;
		} else if (!highest.equals(other.highest))
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
		if (no_disc == null) {
			if (other.no_disc != null)
				return false;
		} else if (!no_disc.equals(other.no_disc))
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
		if (psgr_status_appl == null) {
			if (other.psgr_status_appl != null)
				return false;
		} else if (!psgr_status_appl.equals(other.psgr_status_appl))
			return false;
		if (psgr_status_geo_loc == null) {
			if (other.psgr_status_geo_loc != null)
				return false;
		} else if (!psgr_status_geo_loc.equals(other.psgr_status_geo_loc))
			return false;
		if (psgr_status_geo_type == null) {
			if (other.psgr_status_geo_type != null)
				return false;
		} else if (!psgr_status_geo_type.equals(other.psgr_status_geo_type))
			return false;
		if (psgr_status_psgr == null) {
			if (other.psgr_status_psgr != null)
				return false;
		} else if (!psgr_status_psgr.equals(other.psgr_status_psgr))
			return false;
		if (psgr_travel_origin_geo_loc == null) {
			if (other.psgr_travel_origin_geo_loc != null)
				return false;
		} else if (!psgr_travel_origin_geo_loc.equals(other.psgr_travel_origin_geo_loc))
			return false;
		if (psgr_travel_origin_geo_type == null) {
			if (other.psgr_travel_origin_geo_type != null)
				return false;
		} else if (!psgr_travel_origin_geo_type.equals(other.psgr_travel_origin_geo_type))
			return false;
		if (psgr_travel_origin_reserved_1 == null) {
			if (other.psgr_travel_origin_reserved_1 != null)
				return false;
		} else if (!psgr_travel_origin_reserved_1.equals(other.psgr_travel_origin_reserved_1))
			return false;
		if (psgr_travel_origin_reserved_2 == null) {
			if (other.psgr_travel_origin_reserved_2 != null)
				return false;
		} else if (!psgr_travel_origin_reserved_2.equals(other.psgr_travel_origin_reserved_2))
			return false;
		if (psgr_travel_origin_tsi == null) {
			if (other.psgr_travel_origin_tsi != null)
				return false;
		} else if (!psgr_travel_origin_tsi.equals(other.psgr_travel_origin_tsi))
			return false;
		if (psgr_type == null) {
			if (other.psgr_type != null)
				return false;
		} else if (!psgr_type.equals(other.psgr_type))
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
		if (resulting_fare_disc_cat == null) {
			if (other.resulting_fare_disc_cat != null)
				return false;
		} else if (!resulting_fare_disc_cat.equals(other.resulting_fare_disc_cat))
			return false;
		if (resulting_fare_fare_class == null) {
			if (other.resulting_fare_fare_class != null)
				return false;
		} else if (!resulting_fare_fare_class.equals(other.resulting_fare_fare_class))
			return false;
		if (resulting_fare_global == null) {
			if (other.resulting_fare_global != null)
				return false;
		} else if (!resulting_fare_global.equals(other.resulting_fare_global))
			return false;
		if (resulting_fare_owrt == null) {
			if (other.resulting_fare_owrt != null)
				return false;
		} else if (!resulting_fare_owrt.equals(other.resulting_fare_owrt))
			return false;
		if (resulting_fare_prc_cat == null) {
			if (other.resulting_fare_prc_cat != null)
				return false;
		} else if (!resulting_fare_prc_cat.equals(other.resulting_fare_prc_cat))
			return false;
		if (resulting_fare_prime_rbd_1 == null) {
			if (other.resulting_fare_prime_rbd_1 != null)
				return false;
		} else if (!resulting_fare_prime_rbd_1.equals(other.resulting_fare_prime_rbd_1))
			return false;
		if (resulting_fare_prime_rbd_2 == null) {
			if (other.resulting_fare_prime_rbd_2 != null)
				return false;
		} else if (!resulting_fare_prime_rbd_2.equals(other.resulting_fare_prime_rbd_2))
			return false;
		if (resulting_fare_prime_rbd_3 == null) {
			if (other.resulting_fare_prime_rbd_3 != null)
				return false;
		} else if (!resulting_fare_prime_rbd_3.equals(other.resulting_fare_prime_rbd_3))
			return false;
		if (resulting_fare_prime_rbd_4 == null) {
			if (other.resulting_fare_prime_rbd_4 != null)
				return false;
		} else if (!resulting_fare_prime_rbd_4.equals(other.resulting_fare_prime_rbd_4))
			return false;
		if (resulting_fare_prime_rbd_5 == null) {
			if (other.resulting_fare_prime_rbd_5 != null)
				return false;
		} else if (!resulting_fare_prime_rbd_5.equals(other.resulting_fare_prime_rbd_5))
			return false;
		if (resulting_fare_prime_rbd_6 == null) {
			if (other.resulting_fare_prime_rbd_6 != null)
				return false;
		} else if (!resulting_fare_prime_rbd_6.equals(other.resulting_fare_prime_rbd_6))
			return false;
		if (resulting_fare_prime_rbd_7 == null) {
			if (other.resulting_fare_prime_rbd_7 != null)
				return false;
		} else if (!resulting_fare_prime_rbd_7.equals(other.resulting_fare_prime_rbd_7))
			return false;
		if (resulting_fare_prime_rbd_8 == null) {
			if (other.resulting_fare_prime_rbd_8 != null)
				return false;
		} else if (!resulting_fare_prime_rbd_8.equals(other.resulting_fare_prime_rbd_8))
			return false;
		if (resulting_fare_prime_sector == null) {
			if (other.resulting_fare_prime_sector != null)
				return false;
		} else if (!resulting_fare_prime_sector.equals(other.resulting_fare_prime_sector))
			return false;
		if (resulting_fare_rbd_999 == null) {
			if (other.resulting_fare_rbd_999 != null)
				return false;
		} else if (!resulting_fare_rbd_999.equals(other.resulting_fare_rbd_999))
			return false;
		if (resulting_fare_rbd_validation == null) {
			if (other.resulting_fare_rbd_validation != null)
				return false;
		} else if (!resulting_fare_rbd_validation.equals(other.resulting_fare_rbd_validation))
			return false;
		if (resulting_fare_reserved_1 == null) {
			if (other.resulting_fare_reserved_1 != null)
				return false;
		} else if (!resulting_fare_reserved_1.equals(other.resulting_fare_reserved_1))
			return false;
		if (resulting_fare_reserved_2 == null) {
			if (other.resulting_fare_reserved_2 != null)
				return false;
		} else if (!resulting_fare_reserved_2.equals(other.resulting_fare_reserved_2))
			return false;
		if (resulting_fare_routing_number == null) {
			if (other.resulting_fare_routing_number != null)
				return false;
		} else if (!resulting_fare_routing_number.equals(other.resulting_fare_routing_number))
			return false;
		if (resulting_fare_routing_tarrif == null) {
			if (other.resulting_fare_routing_tarrif != null)
				return false;
		} else if (!resulting_fare_routing_tarrif.equals(other.resulting_fare_routing_tarrif))
			return false;
		if (resulting_fare_tcm == null) {
			if (other.resulting_fare_tcm != null)
				return false;
		} else if (!resulting_fare_tcm.equals(other.resulting_fare_tcm))
			return false;
		if (resulting_fare_tdm == null) {
			if (other.resulting_fare_tdm != null)
				return false;
		} else if (!resulting_fare_tdm.equals(other.resulting_fare_tdm))
			return false;
		if (resulting_fare_ticket_designator == null) {
			if (other.resulting_fare_ticket_designator != null)
				return false;
		} else if (!resulting_fare_ticket_designator.equals(other.resulting_fare_ticket_designator))
			return false;
		if (resulting_fare_ticketing_code == null) {
			if (other.resulting_fare_ticketing_code != null)
				return false;
		} else if (!resulting_fare_ticketing_code.equals(other.resulting_fare_ticketing_code))
			return false;
		if (resulting_fare_type_day_of_week == null) {
			if (other.resulting_fare_type_day_of_week != null)
				return false;
		} else if (!resulting_fare_type_day_of_week.equals(other.resulting_fare_type_day_of_week))
			return false;
		if (resulting_fare_type_fare == null) {
			if (other.resulting_fare_type_fare != null)
				return false;
		} else if (!resulting_fare_type_fare.equals(other.resulting_fare_type_fare))
			return false;
		if (resulting_fare_type_season == null) {
			if (other.resulting_fare_type_season != null)
				return false;
		} else if (!resulting_fare_type_season.equals(other.resulting_fare_type_season))
			return false;
		if (rules_type == null) {
			if (other.rules_type != null)
				return false;
		} else if (!rules_type.equals(other.rules_type))
			return false;
		if (tarrif_rule == null) {
			if (other.tarrif_rule != null)
				return false;
		} else if (!tarrif_rule.equals(other.tarrif_rule))
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
		if (trvl_wholly_within_geo_loc == null) {
			if (other.trvl_wholly_within_geo_loc != null)
				return false;
		} else if (!trvl_wholly_within_geo_loc.equals(other.trvl_wholly_within_geo_loc))
			return false;
		if (trvl_wholly_within_geo_type == null) {
			if (other.trvl_wholly_within_geo_type != null)
				return false;
		} else if (!trvl_wholly_within_geo_type.equals(other.trvl_wholly_within_geo_type))
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
		return "AtpcoRecord3Cat25 [id=" + id + ", fare_comp_fare_class=" + fare_comp_fare_class
				+ ", resulting_fare_type_season=" + resulting_fare_type_season + ", record_sequence=" + record_sequence
				+ ", fare_calc_reserved_1=" + fare_calc_reserved_1 + ", fare_calc_mileage_max=" + fare_calc_mileage_max
				+ ", psgr_travel_origin_reserved_1=" + psgr_travel_origin_reserved_1
				+ ", resulting_fare_ticket_designator=" + resulting_fare_ticket_designator + ", fare_comp_carrier_code="
				+ fare_comp_carrier_code + ", psgr_occ_first=" + psgr_occ_first + ", tbl_no=" + tbl_no
				+ ", fare_comp_min_fare_1=" + fare_comp_min_fare_1 + ", unavail=" + unavail
				+ ", fare_calc_mileage_find=" + fare_calc_mileage_find + ", fare_comp_min_fare_2="
				+ fare_comp_min_fare_2 + ", resulting_fare_type_fare=" + resulting_fare_type_fare + ", rules_type="
				+ rules_type + ", action=" + action + ", flt_segs=" + flt_segs + ", resulting_fare_disc_cat="
				+ resulting_fare_disc_cat + ", resulting_fare_routing_number=" + resulting_fare_routing_number
				+ ", resulting_fare_global=" + resulting_fare_global + ", resulting_fare_reserved_2="
				+ resulting_fare_reserved_2 + ", psgr_travel_origin_geo_type=" + psgr_travel_origin_geo_type
				+ ", resulting_fare_fare_class=" + resulting_fare_fare_class + ", resulting_fare_reserved_1="
				+ resulting_fare_reserved_1 + ", fare_calc_fare_dec_2=" + fare_calc_fare_dec_2
				+ ", fare_calc_fare_dec_1=" + fare_calc_fare_dec_1 + ", fare_calc_fare_cur_1=" + fare_calc_fare_cur_1
				+ ", fare_calc_fare_amt_2=" + fare_calc_fare_amt_2 + ", fare_calc_fare_amt_1=" + fare_calc_fare_amt_1
				+ ", fare_calc_fare_cur_2=" + fare_calc_fare_cur_2 + ", fare_comp_max_fare_2=" + fare_comp_max_fare_2
				+ ", fare_calc_mileage_min=" + fare_calc_mileage_min + ", fare_comp_max_fare_1=" + fare_comp_max_fare_1
				+ ", resulting_fare_tdm=" + resulting_fare_tdm + ", date_tbl_no_994=" + date_tbl_no_994
				+ ", base_tbl_no_989=" + base_tbl_no_989 + ", text_tbl_no_996=" + text_tbl_no_996
				+ ", resulting_fare_owrt=" + resulting_fare_owrt + ", tarrif_rule=" + tarrif_rule
				+ ", fare_calc_base_fares=" + fare_calc_base_fares + ", psgr_status_geo_type=" + psgr_status_geo_type
				+ ", psgr_occ_last=" + psgr_occ_last + ", psgr_status_appl=" + psgr_status_appl + ", rec_type="
				+ rec_type + ", resulting_fare_rbd_validation=" + resulting_fare_rbd_validation
				+ ", psgr_travel_origin_reserved_2=" + psgr_travel_origin_reserved_2 + ", fare_calc_mileage_percent="
				+ fare_calc_mileage_percent + ", resulting_fare_tcm=" + resulting_fare_tcm + ", resulting_fare_prc_cat="
				+ resulting_fare_prc_cat + ", resulting_fare_routing_tarrif=" + resulting_fare_routing_tarrif
				+ ", resulting_fare_ticketing_code=" + resulting_fare_ticketing_code + ", psgr_status_psgr="
				+ psgr_status_psgr + ", highest=" + highest + ", age_min=" + age_min
				+ ", resulting_fare_type_day_of_week=" + resulting_fare_type_day_of_week + ", psgr_type=" + psgr_type
				+ ", resulting_fare_rbd_999=" + resulting_fare_rbd_999 + ", id_=" + id_ + ", no_disc=" + no_disc
				+ ", category_override_tags=" + category_override_tags + ", resulting_fare_prime_sector="
				+ resulting_fare_prime_sector + ", psgr_status_geo_loc=" + psgr_status_geo_loc + ", fare_comp_dec_2="
				+ fare_comp_dec_2 + ", fare_comp_fare_type=" + fare_comp_fare_type + ", fare_comp_dec_1="
				+ fare_comp_dec_1 + ", psgr_travel_origin_tsi=" + psgr_travel_origin_tsi
				+ ", resulting_fare_prime_rbd_6=" + resulting_fare_prime_rbd_6 + ", resulting_fare_prime_rbd_7="
				+ resulting_fare_prime_rbd_7 + ", resulting_fare_prime_rbd_8=" + resulting_fare_prime_rbd_8
				+ ", resulting_fare_prime_rbd_2=" + resulting_fare_prime_rbd_2 + ", record_batch=" + record_batch
				+ ", resulting_fare_prime_rbd_3=" + resulting_fare_prime_rbd_3 + ", resulting_fare_prime_rbd_4="
				+ resulting_fare_prime_rbd_4 + ", age_max=" + age_max + ", resulting_fare_prime_rbd_5="
				+ resulting_fare_prime_rbd_5 + ", cat_no=" + cat_no + ", resulting_fare_prime_rbd_1="
				+ resulting_fare_prime_rbd_1 + ", fare_comp_rules_tarrif=" + fare_comp_rules_tarrif
				+ ", fare_comp_cur_1=" + fare_comp_cur_1 + ", fare_comp_cur_2=" + fare_comp_cur_2 + ", reserved_1="
				+ reserved_1 + ", trvl_wholly_within_geo_loc=" + trvl_wholly_within_geo_loc
				+ ", trvl_wholly_within_geo_type=" + trvl_wholly_within_geo_type + ", psgr_travel_origin_geo_loc="
				+ psgr_travel_origin_geo_loc + "]";
	}
    
    
}
