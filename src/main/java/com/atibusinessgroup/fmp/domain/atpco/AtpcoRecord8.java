package com.atibusinessgroup.fmp.domain.atpco;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "atpco_record_8")
public class AtpcoRecord8 {
	
	@Id
    private String id;
	
	@Field("fare_geo_scope_loc_1")
	private String fare_geo_scope_loc_1;

	@Field("record_sequence")
    private String record_sequence;

	@Field("fare_geo_scope_loc_2")
    private String fare_geo_scope_loc_2;

	@Field("psgr_status_psgr")
    private String psgr_status_psgr;

	@Field("fare_geo_scope_loc_3")
    private String fare_geo_scope_loc_3;

	@Field("fare_geo_scope_loc_1_tbl_978")
    private String fare_geo_scope_loc_1_tbl_978;

	@Field("age_min")
    private String age_min;

	@Field("account_code")
    private String account_code;

	@Field("fare_geo_scope_di")
    private String fare_geo_scope_di;

	@Field("psgr_travel_origin_loc")
    private String psgr_travel_origin_loc;

	@Field("joint_cxr_tbl_no_997")
    private String joint_cxr_tbl_no_997;

	@Field("carrier_tbl_986")
    private String carrier_tbl_986;

	@Field("cxr_code")
    private String cxr_code;

	@Field("mcn")
    private String mcn;

	@Field("secondary_type_of_passenger")
    private List<AtpcoRecord8SecondaryTypePsgr> secondary_type_of_passenger;

	@Field("dates_disc")
    private Object dates_disc;

	@Field("same_carrier")
    private String same_carrier;

	@Field("fare_geo_scope_loc_2_tbl_978")
    private String fare_geo_scope_loc_2_tbl_978;

	@Field("psgr_occ_first")
    private String psgr_occ_first;

	@Field("tariff")
    private String tariff;

	@Field("rule_no")
    private String rule_no;

	@Field("batch_ci")
    private String batch_ci;

	@Field("action")
    private String action;

	@Field("psgr_status_id")
    private String psgr_status_id;

	@Field("cat_35")
    private String cat_35;

	@Field("psgr_status_type")
    private String psgr_status_type;

	@Field("batch_no")
    private String batch_no;

	@Field("psgr_status_loc")
    private String psgr_status_loc;

	@Field("dates_eff")
    private Object dates_eff;

	@Field("psgr_travel_origin_tsi")
    private String psgr_travel_origin_tsi;

	@Field("record_batch")
    private String record_batch;

	@Field("fare_geo_scope_global")
    private String fare_geo_scope_global;

	@Field("age_max")
    private String age_max;

	@Field("psgr_travel_origin_type")
    private String psgr_travel_origin_type;

	@Field("psgr_occ_last")
    private String psgr_occ_last;

	@Field("psgr_status_appl")
    private String psgr_status_appl;

	@Field("ticket_designator")
    private String ticket_designator;

	@Field("prim_pass_type")
    private String prim_pass_type;

	@Field("reserved_2")
    private String reserved_2;

	@Field("fare_geo_scope_type_1")
    private String fare_geo_scope_type_1;

	@Field("rec_type")
    private String rec_type;

	@Field("fare_geo_scope_type_2")
    private String fare_geo_scope_type_2;

	@Field("reserved_1")
    private String reserved_1;

	@Field("fare_geo_scope_type_3")
    private String fare_geo_scope_type_3;
	
	@Field("rec_id")
    private String rec_id;
	
	@Field("tar_cd")
    private String tar_cd;
	
	@Field("description")
    private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFare_geo_scope_loc_1() {
		return fare_geo_scope_loc_1;
	}

	public void setFare_geo_scope_loc_1(String fare_geo_scope_loc_1) {
		this.fare_geo_scope_loc_1 = fare_geo_scope_loc_1;
	}

	public String getRecord_sequence() {
		return record_sequence;
	}

	public void setRecord_sequence(String record_sequence) {
		this.record_sequence = record_sequence;
	}

	public String getFare_geo_scope_loc_2() {
		return fare_geo_scope_loc_2;
	}

	public void setFare_geo_scope_loc_2(String fare_geo_scope_loc_2) {
		this.fare_geo_scope_loc_2 = fare_geo_scope_loc_2;
	}

	public String getPsgr_status_psgr() {
		return psgr_status_psgr;
	}

	public void setPsgr_status_psgr(String psgr_status_psgr) {
		this.psgr_status_psgr = psgr_status_psgr;
	}

	public String getFare_geo_scope_loc_3() {
		return fare_geo_scope_loc_3;
	}

	public void setFare_geo_scope_loc_3(String fare_geo_scope_loc_3) {
		this.fare_geo_scope_loc_3 = fare_geo_scope_loc_3;
	}

	public String getFare_geo_scope_loc_1_tbl_978() {
		return fare_geo_scope_loc_1_tbl_978;
	}

	public void setFare_geo_scope_loc_1_tbl_978(String fare_geo_scope_loc_1_tbl_978) {
		this.fare_geo_scope_loc_1_tbl_978 = fare_geo_scope_loc_1_tbl_978;
	}

	public String getAge_min() {
		return age_min;
	}

	public void setAge_min(String age_min) {
		this.age_min = age_min;
	}

	public String getAccount_code() {
		return account_code;
	}

	public void setAccount_code(String account_code) {
		this.account_code = account_code;
	}

	public String getFare_geo_scope_di() {
		return fare_geo_scope_di;
	}

	public void setFare_geo_scope_di(String fare_geo_scope_di) {
		this.fare_geo_scope_di = fare_geo_scope_di;
	}

	public String getPsgr_travel_origin_loc() {
		return psgr_travel_origin_loc;
	}

	public void setPsgr_travel_origin_loc(String psgr_travel_origin_loc) {
		this.psgr_travel_origin_loc = psgr_travel_origin_loc;
	}

	public String getJoint_cxr_tbl_no_997() {
		return joint_cxr_tbl_no_997;
	}

	public void setJoint_cxr_tbl_no_997(String joint_cxr_tbl_no_997) {
		this.joint_cxr_tbl_no_997 = joint_cxr_tbl_no_997;
	}

	public String getCarrier_tbl_986() {
		return carrier_tbl_986;
	}

	public void setCarrier_tbl_986(String carrier_tbl_986) {
		this.carrier_tbl_986 = carrier_tbl_986;
	}

	public String getCxr_code() {
		return cxr_code;
	}

	public void setCxr_code(String cxr_code) {
		this.cxr_code = cxr_code;
	}

	public String getMcn() {
		return mcn;
	}

	public void setMcn(String mcn) {
		this.mcn = mcn;
	}

	public List<AtpcoRecord8SecondaryTypePsgr> getSecondary_type_of_passenger() {
		return secondary_type_of_passenger;
	}

	public void setSecondary_type_of_passenger(List<AtpcoRecord8SecondaryTypePsgr> secondary_type_of_passenger) {
		this.secondary_type_of_passenger = secondary_type_of_passenger;
	}

	public Object getDates_disc() {
		return dates_disc;
	}

	public void setDates_disc(Object dates_disc) {
		this.dates_disc = dates_disc;
	}

	public String getSame_carrier() {
		return same_carrier;
	}

	public void setSame_carrier(String same_carrier) {
		this.same_carrier = same_carrier;
	}

	public String getFare_geo_scope_loc_2_tbl_978() {
		return fare_geo_scope_loc_2_tbl_978;
	}

	public void setFare_geo_scope_loc_2_tbl_978(String fare_geo_scope_loc_2_tbl_978) {
		this.fare_geo_scope_loc_2_tbl_978 = fare_geo_scope_loc_2_tbl_978;
	}

	public String getPsgr_occ_first() {
		return psgr_occ_first;
	}

	public void setPsgr_occ_first(String psgr_occ_first) {
		this.psgr_occ_first = psgr_occ_first;
	}

	public String getTariff() {
		return tariff;
	}

	public void setTariff(String tariff) {
		this.tariff = tariff;
	}

	public String getRule_no() {
		return rule_no;
	}

	public void setRule_no(String rule_no) {
		this.rule_no = rule_no;
	}

	public String getBatch_ci() {
		return batch_ci;
	}

	public void setBatch_ci(String batch_ci) {
		this.batch_ci = batch_ci;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getPsgr_status_id() {
		return psgr_status_id;
	}

	public void setPsgr_status_id(String psgr_status_id) {
		this.psgr_status_id = psgr_status_id;
	}

	public String getCat_35() {
		return cat_35;
	}

	public void setCat_35(String cat_35) {
		this.cat_35 = cat_35;
	}

	public String getPsgr_status_type() {
		return psgr_status_type;
	}

	public void setPsgr_status_type(String psgr_status_type) {
		this.psgr_status_type = psgr_status_type;
	}

	public String getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}

	public String getPsgr_status_loc() {
		return psgr_status_loc;
	}

	public void setPsgr_status_loc(String psgr_status_loc) {
		this.psgr_status_loc = psgr_status_loc;
	}

	public Object getDates_eff() {
		return dates_eff;
	}

	public void setDates_eff(Object dates_eff) {
		this.dates_eff = dates_eff;
	}

	public String getPsgr_travel_origin_tsi() {
		return psgr_travel_origin_tsi;
	}

	public void setPsgr_travel_origin_tsi(String psgr_travel_origin_tsi) {
		this.psgr_travel_origin_tsi = psgr_travel_origin_tsi;
	}

	public String getRecord_batch() {
		return record_batch;
	}

	public void setRecord_batch(String record_batch) {
		this.record_batch = record_batch;
	}

	public String getFare_geo_scope_global() {
		return fare_geo_scope_global;
	}

	public void setFare_geo_scope_global(String fare_geo_scope_global) {
		this.fare_geo_scope_global = fare_geo_scope_global;
	}

	public String getAge_max() {
		return age_max;
	}

	public void setAge_max(String age_max) {
		this.age_max = age_max;
	}

	public String getPsgr_travel_origin_type() {
		return psgr_travel_origin_type;
	}

	public void setPsgr_travel_origin_type(String psgr_travel_origin_type) {
		this.psgr_travel_origin_type = psgr_travel_origin_type;
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

	public String getTicket_designator() {
		return ticket_designator;
	}

	public void setTicket_designator(String ticket_designator) {
		this.ticket_designator = ticket_designator;
	}

	public String getPrim_pass_type() {
		return prim_pass_type;
	}

	public void setPrim_pass_type(String prim_pass_type) {
		this.prim_pass_type = prim_pass_type;
	}

	public String getReserved_2() {
		return reserved_2;
	}

	public void setReserved_2(String reserved_2) {
		this.reserved_2 = reserved_2;
	}

	public String getFare_geo_scope_type_1() {
		return fare_geo_scope_type_1;
	}

	public void setFare_geo_scope_type_1(String fare_geo_scope_type_1) {
		this.fare_geo_scope_type_1 = fare_geo_scope_type_1;
	}

	public String getRec_type() {
		return rec_type;
	}

	public void setRec_type(String rec_type) {
		this.rec_type = rec_type;
	}

	public String getFare_geo_scope_type_2() {
		return fare_geo_scope_type_2;
	}

	public void setFare_geo_scope_type_2(String fare_geo_scope_type_2) {
		this.fare_geo_scope_type_2 = fare_geo_scope_type_2;
	}

	public String getReserved_1() {
		return reserved_1;
	}

	public void setReserved_1(String reserved_1) {
		this.reserved_1 = reserved_1;
	}

	public String getFare_geo_scope_type_3() {
		return fare_geo_scope_type_3;
	}

	public void setFare_geo_scope_type_3(String fare_geo_scope_type_3) {
		this.fare_geo_scope_type_3 = fare_geo_scope_type_3;
	}

	public String getRec_id() {
		return rec_id;
	}

	public void setRec_id(String rec_id) {
		this.rec_id = rec_id;
	}

	public String getTar_cd() {
		return tar_cd;
	}

	public void setTar_cd(String tar_cd) {
		this.tar_cd = tar_cd;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	


}
