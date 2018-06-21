package com.atibusinessgroup.fmp.domain.atpco;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.Decimal128;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_record_3_cat_019")
public class AtpcoRecord3Cat19 {

	@Id
    private String id;
	
	@Field("fbr_fare_currency_1")
	private String fbr_fare_currency_1;

	@Field("accompanying_passengers_appl")
    private String accompanying_passengers_appl;

	@Field("record_sequence")
    private String record_sequence;

	@Field("fbr_id")
    private String fbr_id;

	@Field("fbr_fare_currency_2")
    private String fbr_fare_currency_2;

	@Field("acc_trvl_sec_rule")
    private String acc_trvl_sec_rule;

	@Field("fbr_appl")
    private String fbr_appl;

	@Field("acc_trvl_sec_all")
    private String acc_trvl_sec_all;

	@Field("eor")
    private String eor;

	@Field("accompanying_passengers")
    private List<AtpcoRecord3Cat19AccompanyingPassengers> accompanying_passengers = new ArrayList<>();

    @Field("tbl_no")
    private String tbl_no;

    @Field("unavail")
    private String unavail;

    @Field("rules_type")
    private String rules_type;

    @Field("action")
    private String action;

    @Field("fbr_fare_class")
    private String fbr_fare_class;

    @Field("fbr_fare_amount_1")
    private Decimal128 fbr_fare_amount_1;

    @Field("fbr_fare_amount_2")
    private Decimal128 fbr_fare_amount_2;

    @Field("fbr_fare_number_of_decimals_2")
    private String fbr_fare_number_of_decimals_2;

    @Field("fbr_fare_number_of_decimals_1")
    private String fbr_fare_number_of_decimals_1;

    @Field("fbr_ticket_designator")
    private String fbr_ticket_designator;

    @Field("fbr_base_fare")
    private String fbr_base_fare;

    @Field("date_tbl_no_994")
    private String date_tbl_no_994;

    @Field("text_tbl_no_996")
    private String text_tbl_no_996;

    @Field("fbr_fare_indicator")
    private String fbr_fare_indicator;

    @Field("rec_type")
    private String rec_type;

    @Field("fbr_owrt_2")
    private String fbr_owrt_2;

    @Field("fbr_owrt_1")
    private String fbr_owrt_1;

    @Field("fbr_base_type")
    private String fbr_base_type;

    @Field("tcm")
    private String tcm;

    @Field("acc_trvl_sec_cmpt")
    private String acc_trvl_sec_cmpt;

    @Field("acc_trvl_sec_out")
    private String acc_trvl_sec_out;

    @Field("no_appl")
    private String no_appl;

    @Field("acc_trvl_sec_one")
    private String acc_trvl_sec_one;

    @Field("fbr_psgr_occ_last")
    private String fbr_psgr_occ_last;

    @Field("fbr_and_city")
    private String fbr_and_city;

    @Field("acc_trvl_sec_geo_table_no")
    private String acc_trvl_sec_geo_table_no;

    @Field("tdm")
    private String tdm;

    @Field("fbr_between_city")
    private String fbr_between_city;

    @Field("record_batch")
    private String record_batch;

    @Field("fbr_rbd_2")
    private String fbr_rbd_2;

    @Field("fbr_rbd_1")
    private String fbr_rbd_1;

    @Field("fbr_passenger_type")
    private String fbr_passenger_type;

    @Field("cat_no")
    private String cat_no;

    @Field("fbr_age_max")
    private String fbr_age_max;

    @Field("fbr_age_min")
    private String fbr_age_min;

    @Field("fbr_ticketing_code")
    private String fbr_ticketing_code;

    @Field("accompanying_passengers_fb")
    private String accompanying_passengers_fb;

    @Field("fbr_psgr_occ_first")
    private String fbr_psgr_occ_first;

    @Field("fbr_percent")
    private Decimal128 fbr_percent;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFbr_fare_currency_1() {
		return fbr_fare_currency_1;
	}

	public void setFbr_fare_currency_1(String fbr_fare_currency_1) {
		this.fbr_fare_currency_1 = fbr_fare_currency_1;
	}

	public String getAccompanying_passengers_appl() {
		return accompanying_passengers_appl;
	}

	public void setAccompanying_passengers_appl(String accompanying_passengers_appl) {
		this.accompanying_passengers_appl = accompanying_passengers_appl;
	}

	public String getRecord_sequence() {
		return record_sequence;
	}

	public void setRecord_sequence(String record_sequence) {
		this.record_sequence = record_sequence;
	}

	public String getFbr_id() {
		return fbr_id;
	}

	public void setFbr_id(String fbr_id) {
		this.fbr_id = fbr_id;
	}

	public String getFbr_fare_currency_2() {
		return fbr_fare_currency_2;
	}

	public void setFbr_fare_currency_2(String fbr_fare_currency_2) {
		this.fbr_fare_currency_2 = fbr_fare_currency_2;
	}

	public String getAcc_trvl_sec_rule() {
		return acc_trvl_sec_rule;
	}

	public void setAcc_trvl_sec_rule(String acc_trvl_sec_rule) {
		this.acc_trvl_sec_rule = acc_trvl_sec_rule;
	}

	public String getFbr_appl() {
		return fbr_appl;
	}

	public void setFbr_appl(String fbr_appl) {
		this.fbr_appl = fbr_appl;
	}

	public String getAcc_trvl_sec_all() {
		return acc_trvl_sec_all;
	}

	public void setAcc_trvl_sec_all(String acc_trvl_sec_all) {
		this.acc_trvl_sec_all = acc_trvl_sec_all;
	}

	public String getEor() {
		return eor;
	}

	public void setEor(String eor) {
		this.eor = eor;
	}

	public List<AtpcoRecord3Cat19AccompanyingPassengers> getAccompanying_passengers() {
		return accompanying_passengers;
	}

	public void setAccompanying_passengers(List<AtpcoRecord3Cat19AccompanyingPassengers> accompanying_passengers) {
		this.accompanying_passengers = accompanying_passengers;
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

	public String getFbr_fare_class() {
		return fbr_fare_class;
	}

	public void setFbr_fare_class(String fbr_fare_class) {
		this.fbr_fare_class = fbr_fare_class;
	}

	public Decimal128 getFbr_fare_amount_1() {
		return fbr_fare_amount_1;
	}

	public void setFbr_fare_amount_1(Decimal128 fbr_fare_amount_1) {
		this.fbr_fare_amount_1 = fbr_fare_amount_1;
	}

	public Decimal128 getFbr_fare_amount_2() {
		return fbr_fare_amount_2;
	}

	public void setFbr_fare_amount_2(Decimal128 fbr_fare_amount_2) {
		this.fbr_fare_amount_2 = fbr_fare_amount_2;
	}

	public String getFbr_fare_number_of_decimals_2() {
		return fbr_fare_number_of_decimals_2;
	}

	public void setFbr_fare_number_of_decimals_2(String fbr_fare_number_of_decimals_2) {
		this.fbr_fare_number_of_decimals_2 = fbr_fare_number_of_decimals_2;
	}

	public String getFbr_fare_number_of_decimals_1() {
		return fbr_fare_number_of_decimals_1;
	}

	public void setFbr_fare_number_of_decimals_1(String fbr_fare_number_of_decimals_1) {
		this.fbr_fare_number_of_decimals_1 = fbr_fare_number_of_decimals_1;
	}

	public String getFbr_ticket_designator() {
		return fbr_ticket_designator;
	}

	public void setFbr_ticket_designator(String fbr_ticket_designator) {
		this.fbr_ticket_designator = fbr_ticket_designator;
	}

	public String getFbr_base_fare() {
		return fbr_base_fare;
	}

	public void setFbr_base_fare(String fbr_base_fare) {
		this.fbr_base_fare = fbr_base_fare;
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

	public String getFbr_fare_indicator() {
		return fbr_fare_indicator;
	}

	public void setFbr_fare_indicator(String fbr_fare_indicator) {
		this.fbr_fare_indicator = fbr_fare_indicator;
	}

	public String getRec_type() {
		return rec_type;
	}

	public void setRec_type(String rec_type) {
		this.rec_type = rec_type;
	}

	public String getFbr_owrt_2() {
		return fbr_owrt_2;
	}

	public void setFbr_owrt_2(String fbr_owrt_2) {
		this.fbr_owrt_2 = fbr_owrt_2;
	}

	public String getFbr_owrt_1() {
		return fbr_owrt_1;
	}

	public void setFbr_owrt_1(String fbr_owrt_1) {
		this.fbr_owrt_1 = fbr_owrt_1;
	}

	public String getFbr_base_type() {
		return fbr_base_type;
	}

	public void setFbr_base_type(String fbr_base_type) {
		this.fbr_base_type = fbr_base_type;
	}

	public String getTcm() {
		return tcm;
	}

	public void setTcm(String tcm) {
		this.tcm = tcm;
	}

	public String getAcc_trvl_sec_cmpt() {
		return acc_trvl_sec_cmpt;
	}

	public void setAcc_trvl_sec_cmpt(String acc_trvl_sec_cmpt) {
		this.acc_trvl_sec_cmpt = acc_trvl_sec_cmpt;
	}

	public String getAcc_trvl_sec_out() {
		return acc_trvl_sec_out;
	}

	public void setAcc_trvl_sec_out(String acc_trvl_sec_out) {
		this.acc_trvl_sec_out = acc_trvl_sec_out;
	}

	public String getNo_appl() {
		return no_appl;
	}

	public void setNo_appl(String no_appl) {
		this.no_appl = no_appl;
	}

	public String getAcc_trvl_sec_one() {
		return acc_trvl_sec_one;
	}

	public void setAcc_trvl_sec_one(String acc_trvl_sec_one) {
		this.acc_trvl_sec_one = acc_trvl_sec_one;
	}

	public String getFbr_psgr_occ_last() {
		return fbr_psgr_occ_last;
	}

	public void setFbr_psgr_occ_last(String fbr_psgr_occ_last) {
		this.fbr_psgr_occ_last = fbr_psgr_occ_last;
	}

	public String getFbr_and_city() {
		return fbr_and_city;
	}

	public void setFbr_and_city(String fbr_and_city) {
		this.fbr_and_city = fbr_and_city;
	}

	public String getAcc_trvl_sec_geo_table_no() {
		return acc_trvl_sec_geo_table_no;
	}

	public void setAcc_trvl_sec_geo_table_no(String acc_trvl_sec_geo_table_no) {
		this.acc_trvl_sec_geo_table_no = acc_trvl_sec_geo_table_no;
	}

	public String getTdm() {
		return tdm;
	}

	public void setTdm(String tdm) {
		this.tdm = tdm;
	}

	public String getFbr_between_city() {
		return fbr_between_city;
	}

	public void setFbr_between_city(String fbr_between_city) {
		this.fbr_between_city = fbr_between_city;
	}

	public String getRecord_batch() {
		return record_batch;
	}

	public void setRecord_batch(String record_batch) {
		this.record_batch = record_batch;
	}

	public String getFbr_rbd_2() {
		return fbr_rbd_2;
	}

	public void setFbr_rbd_2(String fbr_rbd_2) {
		this.fbr_rbd_2 = fbr_rbd_2;
	}

	public String getFbr_rbd_1() {
		return fbr_rbd_1;
	}

	public void setFbr_rbd_1(String fbr_rbd_1) {
		this.fbr_rbd_1 = fbr_rbd_1;
	}

	public String getFbr_passenger_type() {
		return fbr_passenger_type;
	}

	public void setFbr_passenger_type(String fbr_passenger_type) {
		this.fbr_passenger_type = fbr_passenger_type;
	}

	public String getCat_no() {
		return cat_no;
	}

	public void setCat_no(String cat_no) {
		this.cat_no = cat_no;
	}

	public String getFbr_age_max() {
		return fbr_age_max;
	}

	public void setFbr_age_max(String fbr_age_max) {
		this.fbr_age_max = fbr_age_max;
	}

	public String getFbr_age_min() {
		return fbr_age_min;
	}

	public void setFbr_age_min(String fbr_age_min) {
		this.fbr_age_min = fbr_age_min;
	}

	public String getFbr_ticketing_code() {
		return fbr_ticketing_code;
	}

	public void setFbr_ticketing_code(String fbr_ticketing_code) {
		this.fbr_ticketing_code = fbr_ticketing_code;
	}

	public String getAccompanying_passengers_fb() {
		return accompanying_passengers_fb;
	}

	public void setAccompanying_passengers_fb(String accompanying_passengers_fb) {
		this.accompanying_passengers_fb = accompanying_passengers_fb;
	}

	public String getFbr_psgr_occ_first() {
		return fbr_psgr_occ_first;
	}

	public void setFbr_psgr_occ_first(String fbr_psgr_occ_first) {
		this.fbr_psgr_occ_first = fbr_psgr_occ_first;
	}

	public Decimal128 getFbr_percent() {
		return fbr_percent;
	}

	public void setFbr_percent(Decimal128 fbr_percent) {
		this.fbr_percent = fbr_percent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acc_trvl_sec_all == null) ? 0 : acc_trvl_sec_all.hashCode());
		result = prime * result + ((acc_trvl_sec_cmpt == null) ? 0 : acc_trvl_sec_cmpt.hashCode());
		result = prime * result + ((acc_trvl_sec_geo_table_no == null) ? 0 : acc_trvl_sec_geo_table_no.hashCode());
		result = prime * result + ((acc_trvl_sec_one == null) ? 0 : acc_trvl_sec_one.hashCode());
		result = prime * result + ((acc_trvl_sec_out == null) ? 0 : acc_trvl_sec_out.hashCode());
		result = prime * result + ((acc_trvl_sec_rule == null) ? 0 : acc_trvl_sec_rule.hashCode());
		result = prime * result + ((accompanying_passengers == null) ? 0 : accompanying_passengers.hashCode());
		result = prime * result
				+ ((accompanying_passengers_appl == null) ? 0 : accompanying_passengers_appl.hashCode());
		result = prime * result + ((accompanying_passengers_fb == null) ? 0 : accompanying_passengers_fb.hashCode());
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((cat_no == null) ? 0 : cat_no.hashCode());
		result = prime * result + ((date_tbl_no_994 == null) ? 0 : date_tbl_no_994.hashCode());
		result = prime * result + ((eor == null) ? 0 : eor.hashCode());
		result = prime * result + ((fbr_age_max == null) ? 0 : fbr_age_max.hashCode());
		result = prime * result + ((fbr_age_min == null) ? 0 : fbr_age_min.hashCode());
		result = prime * result + ((fbr_and_city == null) ? 0 : fbr_and_city.hashCode());
		result = prime * result + ((fbr_appl == null) ? 0 : fbr_appl.hashCode());
		result = prime * result + ((fbr_base_fare == null) ? 0 : fbr_base_fare.hashCode());
		result = prime * result + ((fbr_base_type == null) ? 0 : fbr_base_type.hashCode());
		result = prime * result + ((fbr_between_city == null) ? 0 : fbr_between_city.hashCode());
		result = prime * result + ((fbr_fare_amount_1 == null) ? 0 : fbr_fare_amount_1.hashCode());
		result = prime * result + ((fbr_fare_amount_2 == null) ? 0 : fbr_fare_amount_2.hashCode());
		result = prime * result + ((fbr_fare_class == null) ? 0 : fbr_fare_class.hashCode());
		result = prime * result + ((fbr_fare_currency_1 == null) ? 0 : fbr_fare_currency_1.hashCode());
		result = prime * result + ((fbr_fare_currency_2 == null) ? 0 : fbr_fare_currency_2.hashCode());
		result = prime * result + ((fbr_fare_indicator == null) ? 0 : fbr_fare_indicator.hashCode());
		result = prime * result
				+ ((fbr_fare_number_of_decimals_1 == null) ? 0 : fbr_fare_number_of_decimals_1.hashCode());
		result = prime * result
				+ ((fbr_fare_number_of_decimals_2 == null) ? 0 : fbr_fare_number_of_decimals_2.hashCode());
		result = prime * result + ((fbr_id == null) ? 0 : fbr_id.hashCode());
		result = prime * result + ((fbr_owrt_1 == null) ? 0 : fbr_owrt_1.hashCode());
		result = prime * result + ((fbr_owrt_2 == null) ? 0 : fbr_owrt_2.hashCode());
		result = prime * result + ((fbr_passenger_type == null) ? 0 : fbr_passenger_type.hashCode());
		result = prime * result + ((fbr_percent == null) ? 0 : fbr_percent.hashCode());
		result = prime * result + ((fbr_psgr_occ_first == null) ? 0 : fbr_psgr_occ_first.hashCode());
		result = prime * result + ((fbr_psgr_occ_last == null) ? 0 : fbr_psgr_occ_last.hashCode());
		result = prime * result + ((fbr_rbd_1 == null) ? 0 : fbr_rbd_1.hashCode());
		result = prime * result + ((fbr_rbd_2 == null) ? 0 : fbr_rbd_2.hashCode());
		result = prime * result + ((fbr_ticket_designator == null) ? 0 : fbr_ticket_designator.hashCode());
		result = prime * result + ((fbr_ticketing_code == null) ? 0 : fbr_ticketing_code.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((no_appl == null) ? 0 : no_appl.hashCode());
		result = prime * result + ((rec_type == null) ? 0 : rec_type.hashCode());
		result = prime * result + ((record_batch == null) ? 0 : record_batch.hashCode());
		result = prime * result + ((record_sequence == null) ? 0 : record_sequence.hashCode());
		result = prime * result + ((rules_type == null) ? 0 : rules_type.hashCode());
		result = prime * result + ((tbl_no == null) ? 0 : tbl_no.hashCode());
		result = prime * result + ((tcm == null) ? 0 : tcm.hashCode());
		result = prime * result + ((tdm == null) ? 0 : tdm.hashCode());
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
		AtpcoRecord3Cat19 other = (AtpcoRecord3Cat19) obj;
		if (acc_trvl_sec_all == null) {
			if (other.acc_trvl_sec_all != null)
				return false;
		} else if (!acc_trvl_sec_all.equals(other.acc_trvl_sec_all))
			return false;
		if (acc_trvl_sec_cmpt == null) {
			if (other.acc_trvl_sec_cmpt != null)
				return false;
		} else if (!acc_trvl_sec_cmpt.equals(other.acc_trvl_sec_cmpt))
			return false;
		if (acc_trvl_sec_geo_table_no == null) {
			if (other.acc_trvl_sec_geo_table_no != null)
				return false;
		} else if (!acc_trvl_sec_geo_table_no.equals(other.acc_trvl_sec_geo_table_no))
			return false;
		if (acc_trvl_sec_one == null) {
			if (other.acc_trvl_sec_one != null)
				return false;
		} else if (!acc_trvl_sec_one.equals(other.acc_trvl_sec_one))
			return false;
		if (acc_trvl_sec_out == null) {
			if (other.acc_trvl_sec_out != null)
				return false;
		} else if (!acc_trvl_sec_out.equals(other.acc_trvl_sec_out))
			return false;
		if (acc_trvl_sec_rule == null) {
			if (other.acc_trvl_sec_rule != null)
				return false;
		} else if (!acc_trvl_sec_rule.equals(other.acc_trvl_sec_rule))
			return false;
		if (accompanying_passengers == null) {
			if (other.accompanying_passengers != null)
				return false;
		} else if (!accompanying_passengers.equals(other.accompanying_passengers))
			return false;
		if (accompanying_passengers_appl == null) {
			if (other.accompanying_passengers_appl != null)
				return false;
		} else if (!accompanying_passengers_appl.equals(other.accompanying_passengers_appl))
			return false;
		if (accompanying_passengers_fb == null) {
			if (other.accompanying_passengers_fb != null)
				return false;
		} else if (!accompanying_passengers_fb.equals(other.accompanying_passengers_fb))
			return false;
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
		if (eor == null) {
			if (other.eor != null)
				return false;
		} else if (!eor.equals(other.eor))
			return false;
		if (fbr_age_max == null) {
			if (other.fbr_age_max != null)
				return false;
		} else if (!fbr_age_max.equals(other.fbr_age_max))
			return false;
		if (fbr_age_min == null) {
			if (other.fbr_age_min != null)
				return false;
		} else if (!fbr_age_min.equals(other.fbr_age_min))
			return false;
		if (fbr_and_city == null) {
			if (other.fbr_and_city != null)
				return false;
		} else if (!fbr_and_city.equals(other.fbr_and_city))
			return false;
		if (fbr_appl == null) {
			if (other.fbr_appl != null)
				return false;
		} else if (!fbr_appl.equals(other.fbr_appl))
			return false;
		if (fbr_base_fare == null) {
			if (other.fbr_base_fare != null)
				return false;
		} else if (!fbr_base_fare.equals(other.fbr_base_fare))
			return false;
		if (fbr_base_type == null) {
			if (other.fbr_base_type != null)
				return false;
		} else if (!fbr_base_type.equals(other.fbr_base_type))
			return false;
		if (fbr_between_city == null) {
			if (other.fbr_between_city != null)
				return false;
		} else if (!fbr_between_city.equals(other.fbr_between_city))
			return false;
		if (fbr_fare_amount_1 == null) {
			if (other.fbr_fare_amount_1 != null)
				return false;
		} else if (!fbr_fare_amount_1.equals(other.fbr_fare_amount_1))
			return false;
		if (fbr_fare_amount_2 == null) {
			if (other.fbr_fare_amount_2 != null)
				return false;
		} else if (!fbr_fare_amount_2.equals(other.fbr_fare_amount_2))
			return false;
		if (fbr_fare_class == null) {
			if (other.fbr_fare_class != null)
				return false;
		} else if (!fbr_fare_class.equals(other.fbr_fare_class))
			return false;
		if (fbr_fare_currency_1 == null) {
			if (other.fbr_fare_currency_1 != null)
				return false;
		} else if (!fbr_fare_currency_1.equals(other.fbr_fare_currency_1))
			return false;
		if (fbr_fare_currency_2 == null) {
			if (other.fbr_fare_currency_2 != null)
				return false;
		} else if (!fbr_fare_currency_2.equals(other.fbr_fare_currency_2))
			return false;
		if (fbr_fare_indicator == null) {
			if (other.fbr_fare_indicator != null)
				return false;
		} else if (!fbr_fare_indicator.equals(other.fbr_fare_indicator))
			return false;
		if (fbr_fare_number_of_decimals_1 == null) {
			if (other.fbr_fare_number_of_decimals_1 != null)
				return false;
		} else if (!fbr_fare_number_of_decimals_1.equals(other.fbr_fare_number_of_decimals_1))
			return false;
		if (fbr_fare_number_of_decimals_2 == null) {
			if (other.fbr_fare_number_of_decimals_2 != null)
				return false;
		} else if (!fbr_fare_number_of_decimals_2.equals(other.fbr_fare_number_of_decimals_2))
			return false;
		if (fbr_id == null) {
			if (other.fbr_id != null)
				return false;
		} else if (!fbr_id.equals(other.fbr_id))
			return false;
		if (fbr_owrt_1 == null) {
			if (other.fbr_owrt_1 != null)
				return false;
		} else if (!fbr_owrt_1.equals(other.fbr_owrt_1))
			return false;
		if (fbr_owrt_2 == null) {
			if (other.fbr_owrt_2 != null)
				return false;
		} else if (!fbr_owrt_2.equals(other.fbr_owrt_2))
			return false;
		if (fbr_passenger_type == null) {
			if (other.fbr_passenger_type != null)
				return false;
		} else if (!fbr_passenger_type.equals(other.fbr_passenger_type))
			return false;
		if (fbr_percent == null) {
			if (other.fbr_percent != null)
				return false;
		} else if (!fbr_percent.equals(other.fbr_percent))
			return false;
		if (fbr_psgr_occ_first == null) {
			if (other.fbr_psgr_occ_first != null)
				return false;
		} else if (!fbr_psgr_occ_first.equals(other.fbr_psgr_occ_first))
			return false;
		if (fbr_psgr_occ_last == null) {
			if (other.fbr_psgr_occ_last != null)
				return false;
		} else if (!fbr_psgr_occ_last.equals(other.fbr_psgr_occ_last))
			return false;
		if (fbr_rbd_1 == null) {
			if (other.fbr_rbd_1 != null)
				return false;
		} else if (!fbr_rbd_1.equals(other.fbr_rbd_1))
			return false;
		if (fbr_rbd_2 == null) {
			if (other.fbr_rbd_2 != null)
				return false;
		} else if (!fbr_rbd_2.equals(other.fbr_rbd_2))
			return false;
		if (fbr_ticket_designator == null) {
			if (other.fbr_ticket_designator != null)
				return false;
		} else if (!fbr_ticket_designator.equals(other.fbr_ticket_designator))
			return false;
		if (fbr_ticketing_code == null) {
			if (other.fbr_ticketing_code != null)
				return false;
		} else if (!fbr_ticketing_code.equals(other.fbr_ticketing_code))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (no_appl == null) {
			if (other.no_appl != null)
				return false;
		} else if (!no_appl.equals(other.no_appl))
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
		if (tcm == null) {
			if (other.tcm != null)
				return false;
		} else if (!tcm.equals(other.tcm))
			return false;
		if (tdm == null) {
			if (other.tdm != null)
				return false;
		} else if (!tdm.equals(other.tdm))
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
		return "AtpcoRecord3Cat19 [id=" + id + ", fbr_fare_currency_1=" + fbr_fare_currency_1
				+ ", accompanying_passengers_appl=" + accompanying_passengers_appl + ", record_sequence="
				+ record_sequence + ", fbr_id=" + fbr_id + ", fbr_fare_currency_2=" + fbr_fare_currency_2
				+ ", acc_trvl_sec_rule=" + acc_trvl_sec_rule + ", fbr_appl=" + fbr_appl + ", acc_trvl_sec_all="
				+ acc_trvl_sec_all + ", eor=" + eor + ", accompanying_passengers=" + accompanying_passengers
				+ ", tbl_no=" + tbl_no + ", unavail=" + unavail + ", rules_type=" + rules_type + ", action=" + action
				+ ", fbr_fare_class=" + fbr_fare_class + ", fbr_fare_amount_1=" + fbr_fare_amount_1
				+ ", fbr_fare_amount_2=" + fbr_fare_amount_2 + ", fbr_fare_number_of_decimals_2="
				+ fbr_fare_number_of_decimals_2 + ", fbr_fare_number_of_decimals_1=" + fbr_fare_number_of_decimals_1
				+ ", fbr_ticket_designator=" + fbr_ticket_designator + ", fbr_base_fare=" + fbr_base_fare
				+ ", date_tbl_no_994=" + date_tbl_no_994 + ", text_tbl_no_996=" + text_tbl_no_996
				+ ", fbr_fare_indicator=" + fbr_fare_indicator + ", rec_type=" + rec_type + ", fbr_owrt_2=" + fbr_owrt_2
				+ ", fbr_owrt_1=" + fbr_owrt_1 + ", fbr_base_type=" + fbr_base_type + ", tcm=" + tcm
				+ ", acc_trvl_sec_cmpt=" + acc_trvl_sec_cmpt + ", acc_trvl_sec_out=" + acc_trvl_sec_out + ", no_appl="
				+ no_appl + ", acc_trvl_sec_one=" + acc_trvl_sec_one + ", fbr_psgr_occ_last=" + fbr_psgr_occ_last
				+ ", fbr_and_city=" + fbr_and_city + ", acc_trvl_sec_geo_table_no=" + acc_trvl_sec_geo_table_no
				+ ", tdm=" + tdm + ", fbr_between_city=" + fbr_between_city + ", record_batch=" + record_batch
				+ ", fbr_rbd_2=" + fbr_rbd_2 + ", fbr_rbd_1=" + fbr_rbd_1 + ", fbr_passenger_type=" + fbr_passenger_type
				+ ", cat_no=" + cat_no + ", fbr_age_max=" + fbr_age_max + ", fbr_age_min=" + fbr_age_min
				+ ", fbr_ticketing_code=" + fbr_ticketing_code + ", accompanying_passengers_fb="
				+ accompanying_passengers_fb + ", fbr_psgr_occ_first=" + fbr_psgr_occ_first + ", fbr_percent="
				+ fbr_percent + "]";
	}
    
    

}
