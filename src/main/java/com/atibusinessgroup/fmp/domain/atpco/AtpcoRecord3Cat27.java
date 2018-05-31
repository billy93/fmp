package com.atibusinessgroup.fmp.domain.atpco;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.Decimal128;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_record_3_cat_027")
public class AtpcoRecord3Cat27 {

	@Id
    private String id;
	
	@Field("refunds_filler_10")
	private String refunds_filler_10;

	@Field("refunds_filler_11")
    private String refunds_filler_11;

	@Field("text_table_no_996")
    private String text_table_no_996;

	@Field("refunds_filler_14")
    private String refunds_filler_14;

	@Field("record_sequence")
    private String record_sequence;

	@Field("refunds_filler_15")
    private String refunds_filler_15;

	@Field("refunds_filler_12")
    private String refunds_filler_12;

	@Field("refunds_filler_13")
    private String refunds_filler_13;

	@Field("type")
    private String type;

	@Field("waiver_appl")
    private String waiver_appl;

	@Field("package_addl_appl_2")
    private String package_addl_appl_2;

	@Field("package_addl_appl_1")
    private String package_addl_appl_1;

	@Field("waiver_9")
    private String waiver_9;

	@Field("waiver_7")
    private String waiver_7;

	@Field("waiver_8")
    private String waiver_8;

	@Field("tbl_no")
    private String tbl_no;

	@Field("unavail")
    private String unavail;

	@Field("waiver_1")
    private String waiver_1;

	@Field("refunds_filler_19")
    private String refunds_filler_19;

	@Field("rules_type")
    private String rules_type;

	@Field("action")
    private String action;

	@Field("price_min_tour_1")
    private String price_min_tour_1;

	@Field("waiver_2")
    private String waiver_2;

	@Field("refunds_filler_18")
    private String refunds_filler_18;

	@Field("refunds_filler_17")
    private String refunds_filler_17;

	@Field("refunds_filler_16")
    private String refunds_filler_16;

	@Field("price_min_tour_2")
    private String price_min_tour_2;

	@Field("package_addl_amt_2")
    private Decimal128 package_addl_amt_2;

	@Field("refunds_filler_20")
    private String refunds_filler_20;

	@Field("price_filler_3")
    private String price_filler_3;

	@Field("refunds_filler_21")
    private String refunds_filler_21;

	@Field("price_filler_2")
    private String price_filler_2;

	@Field("refunds_filler_22")
    private String refunds_filler_22;

	@Field("price_filler_1")
    private String price_filler_1;

	@Field("refunds_filler_23")
    private String refunds_filler_23;

	@Field("refunds_filler_24")
    private String refunds_filler_24;

	@Field("tour_no")
    private String tour_no;

	@Field("waiver_11")
    private String waiver_11;

	@Field("waiver_10")
    private String waiver_10;

	@Field("date_table_no_994")
    private String date_table_no_994;

	@Field("waiver_13")
    private String waiver_13;

	@Field("waiver_12")
    private String waiver_12;

	@Field("package_dec_1")
    private String package_dec_1;

	@Field("package_cxr")
    private String package_cxr;

	@Field("package_filler_1")
    private String package_filler_1;

	@Field("waiver_reserved_1")
    private String waiver_reserved_1;

	@Field("package_dec_2")
    private String package_dec_2;

	@Field("rec_type")
    private String rec_type;

	@Field("price_psgr_type")
    private String price_psgr_type;

	@Field("package_nites")
    private String package_nites;

	@Field("ground_cruise")
    private List<AtpcoRecord3Cat27GroundCruise> ground_cruise = new ArrayList<>();

    @Field("package_cur_1")
    private String package_cur_1;

    @Field("price_cur_1")
    private String price_cur_1;

    @Field("price_cur_2")
    private String price_cur_2;

    @Field("package_cur_2")
    private String package_cur_2;

    @Field("waiver_filler_5")
    private String waiver_filler_5;

    @Field("waiver_filler_4")
    private String waiver_filler_4;

    @Field("price_appl")
    private String price_appl;

    @Field("price_dec_1")
    private String price_dec_1;

    @Field("price_dec_2")
    private String price_dec_2;

    @Field("package_min_appl_1")
    private String package_min_appl_1;

    @Field("package_min_appl_2")
    private String package_min_appl_2;

    @Field("package_min_amt_2")
    private Decimal128 package_min_amt_2;

    @Field("package_min_amt_1")
    private Decimal128 package_min_amt_1;

    @Field("package_addl_amt_1")
    private Decimal128 package_addl_amt_1;

    @Field("price_age_min")
    private String price_age_min;

    @Field("record_batch")
    private String record_batch;

    @Field("price_age_max")
    private String price_age_max;

    @Field("refunds_filler_4")
    private String refunds_filler_4;

    @Field("waiver_filler_1")
    private String waiver_filler_1;

    @Field("cat_no")
    private String cat_no;

    @Field("refunds_filler_3")
    private String refunds_filler_3;

    @Field("waiver_filler_2")
    private String waiver_filler_2;

    @Field("refunds_filler_6")
    private String refunds_filler_6;

    @Field("waiver_filler_3")
    private String waiver_filler_3;

    @Field("package_pct")
    private String package_pct;

    @Field("refunds_filler_5")
    private String refunds_filler_5;

    @Field("refunds_filler_8")
    private String refunds_filler_8;

    @Field("refunds_filler_7")
    private String refunds_filler_7;

    @Field("refunds_filler_9")
    private String refunds_filler_9;

    @Field("refunds_non_ref")
    private String refunds_non_ref;

    @Field("refunds_filler_2")
    private String refunds_filler_2;

    @Field("refunds_filler_1")
    private String refunds_filler_1;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRefunds_filler_10() {
		return refunds_filler_10;
	}

	public void setRefunds_filler_10(String refunds_filler_10) {
		this.refunds_filler_10 = refunds_filler_10;
	}

	public String getRefunds_filler_11() {
		return refunds_filler_11;
	}

	public void setRefunds_filler_11(String refunds_filler_11) {
		this.refunds_filler_11 = refunds_filler_11;
	}

	public String getText_table_no_996() {
		return text_table_no_996;
	}

	public void setText_table_no_996(String text_table_no_996) {
		this.text_table_no_996 = text_table_no_996;
	}

	public String getRefunds_filler_14() {
		return refunds_filler_14;
	}

	public void setRefunds_filler_14(String refunds_filler_14) {
		this.refunds_filler_14 = refunds_filler_14;
	}

	public String getRecord_sequence() {
		return record_sequence;
	}

	public void setRecord_sequence(String record_sequence) {
		this.record_sequence = record_sequence;
	}

	public String getRefunds_filler_15() {
		return refunds_filler_15;
	}

	public void setRefunds_filler_15(String refunds_filler_15) {
		this.refunds_filler_15 = refunds_filler_15;
	}

	public String getRefunds_filler_12() {
		return refunds_filler_12;
	}

	public void setRefunds_filler_12(String refunds_filler_12) {
		this.refunds_filler_12 = refunds_filler_12;
	}

	public String getRefunds_filler_13() {
		return refunds_filler_13;
	}

	public void setRefunds_filler_13(String refunds_filler_13) {
		this.refunds_filler_13 = refunds_filler_13;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWaiver_appl() {
		return waiver_appl;
	}

	public void setWaiver_appl(String waiver_appl) {
		this.waiver_appl = waiver_appl;
	}

	public String getPackage_addl_appl_2() {
		return package_addl_appl_2;
	}

	public void setPackage_addl_appl_2(String package_addl_appl_2) {
		this.package_addl_appl_2 = package_addl_appl_2;
	}

	public String getPackage_addl_appl_1() {
		return package_addl_appl_1;
	}

	public void setPackage_addl_appl_1(String package_addl_appl_1) {
		this.package_addl_appl_1 = package_addl_appl_1;
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

	public String getWaiver_1() {
		return waiver_1;
	}

	public void setWaiver_1(String waiver_1) {
		this.waiver_1 = waiver_1;
	}

	public String getRefunds_filler_19() {
		return refunds_filler_19;
	}

	public void setRefunds_filler_19(String refunds_filler_19) {
		this.refunds_filler_19 = refunds_filler_19;
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

	public String getPrice_min_tour_1() {
		return price_min_tour_1;
	}

	public void setPrice_min_tour_1(String price_min_tour_1) {
		this.price_min_tour_1 = price_min_tour_1;
	}

	public String getWaiver_2() {
		return waiver_2;
	}

	public void setWaiver_2(String waiver_2) {
		this.waiver_2 = waiver_2;
	}

	public String getRefunds_filler_18() {
		return refunds_filler_18;
	}

	public void setRefunds_filler_18(String refunds_filler_18) {
		this.refunds_filler_18 = refunds_filler_18;
	}

	public String getRefunds_filler_17() {
		return refunds_filler_17;
	}

	public void setRefunds_filler_17(String refunds_filler_17) {
		this.refunds_filler_17 = refunds_filler_17;
	}

	public String getRefunds_filler_16() {
		return refunds_filler_16;
	}

	public void setRefunds_filler_16(String refunds_filler_16) {
		this.refunds_filler_16 = refunds_filler_16;
	}

	public String getPrice_min_tour_2() {
		return price_min_tour_2;
	}

	public void setPrice_min_tour_2(String price_min_tour_2) {
		this.price_min_tour_2 = price_min_tour_2;
	}

	public Decimal128 getPackage_addl_amt_2() {
		return package_addl_amt_2;
	}

	public void setPackage_addl_amt_2(Decimal128 package_addl_amt_2) {
		this.package_addl_amt_2 = package_addl_amt_2;
	}

	public String getRefunds_filler_20() {
		return refunds_filler_20;
	}

	public void setRefunds_filler_20(String refunds_filler_20) {
		this.refunds_filler_20 = refunds_filler_20;
	}

	public String getPrice_filler_3() {
		return price_filler_3;
	}

	public void setPrice_filler_3(String price_filler_3) {
		this.price_filler_3 = price_filler_3;
	}

	public String getRefunds_filler_21() {
		return refunds_filler_21;
	}

	public void setRefunds_filler_21(String refunds_filler_21) {
		this.refunds_filler_21 = refunds_filler_21;
	}

	public String getPrice_filler_2() {
		return price_filler_2;
	}

	public void setPrice_filler_2(String price_filler_2) {
		this.price_filler_2 = price_filler_2;
	}

	public String getRefunds_filler_22() {
		return refunds_filler_22;
	}

	public void setRefunds_filler_22(String refunds_filler_22) {
		this.refunds_filler_22 = refunds_filler_22;
	}

	public String getPrice_filler_1() {
		return price_filler_1;
	}

	public void setPrice_filler_1(String price_filler_1) {
		this.price_filler_1 = price_filler_1;
	}

	public String getRefunds_filler_23() {
		return refunds_filler_23;
	}

	public void setRefunds_filler_23(String refunds_filler_23) {
		this.refunds_filler_23 = refunds_filler_23;
	}

	public String getRefunds_filler_24() {
		return refunds_filler_24;
	}

	public void setRefunds_filler_24(String refunds_filler_24) {
		this.refunds_filler_24 = refunds_filler_24;
	}

	public String getTour_no() {
		return tour_no;
	}

	public void setTour_no(String tour_no) {
		this.tour_no = tour_no;
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

	public String getDate_table_no_994() {
		return date_table_no_994;
	}

	public void setDate_table_no_994(String date_table_no_994) {
		this.date_table_no_994 = date_table_no_994;
	}

	public String getWaiver_13() {
		return waiver_13;
	}

	public void setWaiver_13(String waiver_13) {
		this.waiver_13 = waiver_13;
	}

	public String getWaiver_12() {
		return waiver_12;
	}

	public void setWaiver_12(String waiver_12) {
		this.waiver_12 = waiver_12;
	}

	public String getPackage_dec_1() {
		return package_dec_1;
	}

	public void setPackage_dec_1(String package_dec_1) {
		this.package_dec_1 = package_dec_1;
	}

	public String getPackage_cxr() {
		return package_cxr;
	}

	public void setPackage_cxr(String package_cxr) {
		this.package_cxr = package_cxr;
	}

	public String getPackage_filler_1() {
		return package_filler_1;
	}

	public void setPackage_filler_1(String package_filler_1) {
		this.package_filler_1 = package_filler_1;
	}

	public String getWaiver_reserved_1() {
		return waiver_reserved_1;
	}

	public void setWaiver_reserved_1(String waiver_reserved_1) {
		this.waiver_reserved_1 = waiver_reserved_1;
	}

	public String getPackage_dec_2() {
		return package_dec_2;
	}

	public void setPackage_dec_2(String package_dec_2) {
		this.package_dec_2 = package_dec_2;
	}

	public String getRec_type() {
		return rec_type;
	}

	public void setRec_type(String rec_type) {
		this.rec_type = rec_type;
	}

	public String getPrice_psgr_type() {
		return price_psgr_type;
	}

	public void setPrice_psgr_type(String price_psgr_type) {
		this.price_psgr_type = price_psgr_type;
	}

	public String getPackage_nites() {
		return package_nites;
	}

	public void setPackage_nites(String package_nites) {
		this.package_nites = package_nites;
	}

	public List<AtpcoRecord3Cat27GroundCruise> getGround_cruise() {
		return ground_cruise;
	}

	public void setGround_cruise(List<AtpcoRecord3Cat27GroundCruise> ground_cruise) {
		this.ground_cruise = ground_cruise;
	}

	public String getPackage_cur_1() {
		return package_cur_1;
	}

	public void setPackage_cur_1(String package_cur_1) {
		this.package_cur_1 = package_cur_1;
	}

	public String getPrice_cur_1() {
		return price_cur_1;
	}

	public void setPrice_cur_1(String price_cur_1) {
		this.price_cur_1 = price_cur_1;
	}

	public String getPrice_cur_2() {
		return price_cur_2;
	}

	public void setPrice_cur_2(String price_cur_2) {
		this.price_cur_2 = price_cur_2;
	}

	public String getPackage_cur_2() {
		return package_cur_2;
	}

	public void setPackage_cur_2(String package_cur_2) {
		this.package_cur_2 = package_cur_2;
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

	public String getPrice_appl() {
		return price_appl;
	}

	public void setPrice_appl(String price_appl) {
		this.price_appl = price_appl;
	}

	public String getPrice_dec_1() {
		return price_dec_1;
	}

	public void setPrice_dec_1(String price_dec_1) {
		this.price_dec_1 = price_dec_1;
	}

	public String getPrice_dec_2() {
		return price_dec_2;
	}

	public void setPrice_dec_2(String price_dec_2) {
		this.price_dec_2 = price_dec_2;
	}

	public String getPackage_min_appl_1() {
		return package_min_appl_1;
	}

	public void setPackage_min_appl_1(String package_min_appl_1) {
		this.package_min_appl_1 = package_min_appl_1;
	}

	public String getPackage_min_appl_2() {
		return package_min_appl_2;
	}

	public void setPackage_min_appl_2(String package_min_appl_2) {
		this.package_min_appl_2 = package_min_appl_2;
	}

	public Decimal128 getPackage_min_amt_2() {
		return package_min_amt_2;
	}

	public void setPackage_min_amt_2(Decimal128 package_min_amt_2) {
		this.package_min_amt_2 = package_min_amt_2;
	}

	public Decimal128 getPackage_min_amt_1() {
		return package_min_amt_1;
	}

	public void setPackage_min_amt_1(Decimal128 package_min_amt_1) {
		this.package_min_amt_1 = package_min_amt_1;
	}

	public Decimal128 getPackage_addl_amt_1() {
		return package_addl_amt_1;
	}

	public void setPackage_addl_amt_1(Decimal128 package_addl_amt_1) {
		this.package_addl_amt_1 = package_addl_amt_1;
	}

	public String getPrice_age_min() {
		return price_age_min;
	}

	public void setPrice_age_min(String price_age_min) {
		this.price_age_min = price_age_min;
	}

	public String getRecord_batch() {
		return record_batch;
	}

	public void setRecord_batch(String record_batch) {
		this.record_batch = record_batch;
	}

	public String getPrice_age_max() {
		return price_age_max;
	}

	public void setPrice_age_max(String price_age_max) {
		this.price_age_max = price_age_max;
	}

	public String getRefunds_filler_4() {
		return refunds_filler_4;
	}

	public void setRefunds_filler_4(String refunds_filler_4) {
		this.refunds_filler_4 = refunds_filler_4;
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

	public String getRefunds_filler_3() {
		return refunds_filler_3;
	}

	public void setRefunds_filler_3(String refunds_filler_3) {
		this.refunds_filler_3 = refunds_filler_3;
	}

	public String getWaiver_filler_2() {
		return waiver_filler_2;
	}

	public void setWaiver_filler_2(String waiver_filler_2) {
		this.waiver_filler_2 = waiver_filler_2;
	}

	public String getRefunds_filler_6() {
		return refunds_filler_6;
	}

	public void setRefunds_filler_6(String refunds_filler_6) {
		this.refunds_filler_6 = refunds_filler_6;
	}

	public String getWaiver_filler_3() {
		return waiver_filler_3;
	}

	public void setWaiver_filler_3(String waiver_filler_3) {
		this.waiver_filler_3 = waiver_filler_3;
	}

	public String getPackage_pct() {
		return package_pct;
	}

	public void setPackage_pct(String package_pct) {
		this.package_pct = package_pct;
	}

	public String getRefunds_filler_5() {
		return refunds_filler_5;
	}

	public void setRefunds_filler_5(String refunds_filler_5) {
		this.refunds_filler_5 = refunds_filler_5;
	}

	public String getRefunds_filler_8() {
		return refunds_filler_8;
	}

	public void setRefunds_filler_8(String refunds_filler_8) {
		this.refunds_filler_8 = refunds_filler_8;
	}

	public String getRefunds_filler_7() {
		return refunds_filler_7;
	}

	public void setRefunds_filler_7(String refunds_filler_7) {
		this.refunds_filler_7 = refunds_filler_7;
	}

	public String getRefunds_filler_9() {
		return refunds_filler_9;
	}

	public void setRefunds_filler_9(String refunds_filler_9) {
		this.refunds_filler_9 = refunds_filler_9;
	}

	public String getRefunds_non_ref() {
		return refunds_non_ref;
	}

	public void setRefunds_non_ref(String refunds_non_ref) {
		this.refunds_non_ref = refunds_non_ref;
	}

	public String getRefunds_filler_2() {
		return refunds_filler_2;
	}

	public void setRefunds_filler_2(String refunds_filler_2) {
		this.refunds_filler_2 = refunds_filler_2;
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
		result = prime * result + ((date_table_no_994 == null) ? 0 : date_table_no_994.hashCode());
		result = prime * result + ((ground_cruise == null) ? 0 : ground_cruise.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((package_addl_amt_1 == null) ? 0 : package_addl_amt_1.hashCode());
		result = prime * result + ((package_addl_amt_2 == null) ? 0 : package_addl_amt_2.hashCode());
		result = prime * result + ((package_addl_appl_1 == null) ? 0 : package_addl_appl_1.hashCode());
		result = prime * result + ((package_addl_appl_2 == null) ? 0 : package_addl_appl_2.hashCode());
		result = prime * result + ((package_cur_1 == null) ? 0 : package_cur_1.hashCode());
		result = prime * result + ((package_cur_2 == null) ? 0 : package_cur_2.hashCode());
		result = prime * result + ((package_cxr == null) ? 0 : package_cxr.hashCode());
		result = prime * result + ((package_dec_1 == null) ? 0 : package_dec_1.hashCode());
		result = prime * result + ((package_dec_2 == null) ? 0 : package_dec_2.hashCode());
		result = prime * result + ((package_filler_1 == null) ? 0 : package_filler_1.hashCode());
		result = prime * result + ((package_min_amt_1 == null) ? 0 : package_min_amt_1.hashCode());
		result = prime * result + ((package_min_amt_2 == null) ? 0 : package_min_amt_2.hashCode());
		result = prime * result + ((package_min_appl_1 == null) ? 0 : package_min_appl_1.hashCode());
		result = prime * result + ((package_min_appl_2 == null) ? 0 : package_min_appl_2.hashCode());
		result = prime * result + ((package_nites == null) ? 0 : package_nites.hashCode());
		result = prime * result + ((package_pct == null) ? 0 : package_pct.hashCode());
		result = prime * result + ((price_age_max == null) ? 0 : price_age_max.hashCode());
		result = prime * result + ((price_age_min == null) ? 0 : price_age_min.hashCode());
		result = prime * result + ((price_appl == null) ? 0 : price_appl.hashCode());
		result = prime * result + ((price_cur_1 == null) ? 0 : price_cur_1.hashCode());
		result = prime * result + ((price_cur_2 == null) ? 0 : price_cur_2.hashCode());
		result = prime * result + ((price_dec_1 == null) ? 0 : price_dec_1.hashCode());
		result = prime * result + ((price_dec_2 == null) ? 0 : price_dec_2.hashCode());
		result = prime * result + ((price_filler_1 == null) ? 0 : price_filler_1.hashCode());
		result = prime * result + ((price_filler_2 == null) ? 0 : price_filler_2.hashCode());
		result = prime * result + ((price_filler_3 == null) ? 0 : price_filler_3.hashCode());
		result = prime * result + ((price_min_tour_1 == null) ? 0 : price_min_tour_1.hashCode());
		result = prime * result + ((price_min_tour_2 == null) ? 0 : price_min_tour_2.hashCode());
		result = prime * result + ((price_psgr_type == null) ? 0 : price_psgr_type.hashCode());
		result = prime * result + ((rec_type == null) ? 0 : rec_type.hashCode());
		result = prime * result + ((record_batch == null) ? 0 : record_batch.hashCode());
		result = prime * result + ((record_sequence == null) ? 0 : record_sequence.hashCode());
		result = prime * result + ((refunds_filler_1 == null) ? 0 : refunds_filler_1.hashCode());
		result = prime * result + ((refunds_filler_10 == null) ? 0 : refunds_filler_10.hashCode());
		result = prime * result + ((refunds_filler_11 == null) ? 0 : refunds_filler_11.hashCode());
		result = prime * result + ((refunds_filler_12 == null) ? 0 : refunds_filler_12.hashCode());
		result = prime * result + ((refunds_filler_13 == null) ? 0 : refunds_filler_13.hashCode());
		result = prime * result + ((refunds_filler_14 == null) ? 0 : refunds_filler_14.hashCode());
		result = prime * result + ((refunds_filler_15 == null) ? 0 : refunds_filler_15.hashCode());
		result = prime * result + ((refunds_filler_16 == null) ? 0 : refunds_filler_16.hashCode());
		result = prime * result + ((refunds_filler_17 == null) ? 0 : refunds_filler_17.hashCode());
		result = prime * result + ((refunds_filler_18 == null) ? 0 : refunds_filler_18.hashCode());
		result = prime * result + ((refunds_filler_19 == null) ? 0 : refunds_filler_19.hashCode());
		result = prime * result + ((refunds_filler_2 == null) ? 0 : refunds_filler_2.hashCode());
		result = prime * result + ((refunds_filler_20 == null) ? 0 : refunds_filler_20.hashCode());
		result = prime * result + ((refunds_filler_21 == null) ? 0 : refunds_filler_21.hashCode());
		result = prime * result + ((refunds_filler_22 == null) ? 0 : refunds_filler_22.hashCode());
		result = prime * result + ((refunds_filler_23 == null) ? 0 : refunds_filler_23.hashCode());
		result = prime * result + ((refunds_filler_24 == null) ? 0 : refunds_filler_24.hashCode());
		result = prime * result + ((refunds_filler_3 == null) ? 0 : refunds_filler_3.hashCode());
		result = prime * result + ((refunds_filler_4 == null) ? 0 : refunds_filler_4.hashCode());
		result = prime * result + ((refunds_filler_5 == null) ? 0 : refunds_filler_5.hashCode());
		result = prime * result + ((refunds_filler_6 == null) ? 0 : refunds_filler_6.hashCode());
		result = prime * result + ((refunds_filler_7 == null) ? 0 : refunds_filler_7.hashCode());
		result = prime * result + ((refunds_filler_8 == null) ? 0 : refunds_filler_8.hashCode());
		result = prime * result + ((refunds_filler_9 == null) ? 0 : refunds_filler_9.hashCode());
		result = prime * result + ((refunds_non_ref == null) ? 0 : refunds_non_ref.hashCode());
		result = prime * result + ((rules_type == null) ? 0 : rules_type.hashCode());
		result = prime * result + ((tbl_no == null) ? 0 : tbl_no.hashCode());
		result = prime * result + ((text_table_no_996 == null) ? 0 : text_table_no_996.hashCode());
		result = prime * result + ((tour_no == null) ? 0 : tour_no.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		AtpcoRecord3Cat27 other = (AtpcoRecord3Cat27) obj;
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
		if (date_table_no_994 == null) {
			if (other.date_table_no_994 != null)
				return false;
		} else if (!date_table_no_994.equals(other.date_table_no_994))
			return false;
		if (ground_cruise == null) {
			if (other.ground_cruise != null)
				return false;
		} else if (!ground_cruise.equals(other.ground_cruise))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (package_addl_amt_1 == null) {
			if (other.package_addl_amt_1 != null)
				return false;
		} else if (!package_addl_amt_1.equals(other.package_addl_amt_1))
			return false;
		if (package_addl_amt_2 == null) {
			if (other.package_addl_amt_2 != null)
				return false;
		} else if (!package_addl_amt_2.equals(other.package_addl_amt_2))
			return false;
		if (package_addl_appl_1 == null) {
			if (other.package_addl_appl_1 != null)
				return false;
		} else if (!package_addl_appl_1.equals(other.package_addl_appl_1))
			return false;
		if (package_addl_appl_2 == null) {
			if (other.package_addl_appl_2 != null)
				return false;
		} else if (!package_addl_appl_2.equals(other.package_addl_appl_2))
			return false;
		if (package_cur_1 == null) {
			if (other.package_cur_1 != null)
				return false;
		} else if (!package_cur_1.equals(other.package_cur_1))
			return false;
		if (package_cur_2 == null) {
			if (other.package_cur_2 != null)
				return false;
		} else if (!package_cur_2.equals(other.package_cur_2))
			return false;
		if (package_cxr == null) {
			if (other.package_cxr != null)
				return false;
		} else if (!package_cxr.equals(other.package_cxr))
			return false;
		if (package_dec_1 == null) {
			if (other.package_dec_1 != null)
				return false;
		} else if (!package_dec_1.equals(other.package_dec_1))
			return false;
		if (package_dec_2 == null) {
			if (other.package_dec_2 != null)
				return false;
		} else if (!package_dec_2.equals(other.package_dec_2))
			return false;
		if (package_filler_1 == null) {
			if (other.package_filler_1 != null)
				return false;
		} else if (!package_filler_1.equals(other.package_filler_1))
			return false;
		if (package_min_amt_1 == null) {
			if (other.package_min_amt_1 != null)
				return false;
		} else if (!package_min_amt_1.equals(other.package_min_amt_1))
			return false;
		if (package_min_amt_2 == null) {
			if (other.package_min_amt_2 != null)
				return false;
		} else if (!package_min_amt_2.equals(other.package_min_amt_2))
			return false;
		if (package_min_appl_1 == null) {
			if (other.package_min_appl_1 != null)
				return false;
		} else if (!package_min_appl_1.equals(other.package_min_appl_1))
			return false;
		if (package_min_appl_2 == null) {
			if (other.package_min_appl_2 != null)
				return false;
		} else if (!package_min_appl_2.equals(other.package_min_appl_2))
			return false;
		if (package_nites == null) {
			if (other.package_nites != null)
				return false;
		} else if (!package_nites.equals(other.package_nites))
			return false;
		if (package_pct == null) {
			if (other.package_pct != null)
				return false;
		} else if (!package_pct.equals(other.package_pct))
			return false;
		if (price_age_max == null) {
			if (other.price_age_max != null)
				return false;
		} else if (!price_age_max.equals(other.price_age_max))
			return false;
		if (price_age_min == null) {
			if (other.price_age_min != null)
				return false;
		} else if (!price_age_min.equals(other.price_age_min))
			return false;
		if (price_appl == null) {
			if (other.price_appl != null)
				return false;
		} else if (!price_appl.equals(other.price_appl))
			return false;
		if (price_cur_1 == null) {
			if (other.price_cur_1 != null)
				return false;
		} else if (!price_cur_1.equals(other.price_cur_1))
			return false;
		if (price_cur_2 == null) {
			if (other.price_cur_2 != null)
				return false;
		} else if (!price_cur_2.equals(other.price_cur_2))
			return false;
		if (price_dec_1 == null) {
			if (other.price_dec_1 != null)
				return false;
		} else if (!price_dec_1.equals(other.price_dec_1))
			return false;
		if (price_dec_2 == null) {
			if (other.price_dec_2 != null)
				return false;
		} else if (!price_dec_2.equals(other.price_dec_2))
			return false;
		if (price_filler_1 == null) {
			if (other.price_filler_1 != null)
				return false;
		} else if (!price_filler_1.equals(other.price_filler_1))
			return false;
		if (price_filler_2 == null) {
			if (other.price_filler_2 != null)
				return false;
		} else if (!price_filler_2.equals(other.price_filler_2))
			return false;
		if (price_filler_3 == null) {
			if (other.price_filler_3 != null)
				return false;
		} else if (!price_filler_3.equals(other.price_filler_3))
			return false;
		if (price_min_tour_1 == null) {
			if (other.price_min_tour_1 != null)
				return false;
		} else if (!price_min_tour_1.equals(other.price_min_tour_1))
			return false;
		if (price_min_tour_2 == null) {
			if (other.price_min_tour_2 != null)
				return false;
		} else if (!price_min_tour_2.equals(other.price_min_tour_2))
			return false;
		if (price_psgr_type == null) {
			if (other.price_psgr_type != null)
				return false;
		} else if (!price_psgr_type.equals(other.price_psgr_type))
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
		if (refunds_filler_10 == null) {
			if (other.refunds_filler_10 != null)
				return false;
		} else if (!refunds_filler_10.equals(other.refunds_filler_10))
			return false;
		if (refunds_filler_11 == null) {
			if (other.refunds_filler_11 != null)
				return false;
		} else if (!refunds_filler_11.equals(other.refunds_filler_11))
			return false;
		if (refunds_filler_12 == null) {
			if (other.refunds_filler_12 != null)
				return false;
		} else if (!refunds_filler_12.equals(other.refunds_filler_12))
			return false;
		if (refunds_filler_13 == null) {
			if (other.refunds_filler_13 != null)
				return false;
		} else if (!refunds_filler_13.equals(other.refunds_filler_13))
			return false;
		if (refunds_filler_14 == null) {
			if (other.refunds_filler_14 != null)
				return false;
		} else if (!refunds_filler_14.equals(other.refunds_filler_14))
			return false;
		if (refunds_filler_15 == null) {
			if (other.refunds_filler_15 != null)
				return false;
		} else if (!refunds_filler_15.equals(other.refunds_filler_15))
			return false;
		if (refunds_filler_16 == null) {
			if (other.refunds_filler_16 != null)
				return false;
		} else if (!refunds_filler_16.equals(other.refunds_filler_16))
			return false;
		if (refunds_filler_17 == null) {
			if (other.refunds_filler_17 != null)
				return false;
		} else if (!refunds_filler_17.equals(other.refunds_filler_17))
			return false;
		if (refunds_filler_18 == null) {
			if (other.refunds_filler_18 != null)
				return false;
		} else if (!refunds_filler_18.equals(other.refunds_filler_18))
			return false;
		if (refunds_filler_19 == null) {
			if (other.refunds_filler_19 != null)
				return false;
		} else if (!refunds_filler_19.equals(other.refunds_filler_19))
			return false;
		if (refunds_filler_2 == null) {
			if (other.refunds_filler_2 != null)
				return false;
		} else if (!refunds_filler_2.equals(other.refunds_filler_2))
			return false;
		if (refunds_filler_20 == null) {
			if (other.refunds_filler_20 != null)
				return false;
		} else if (!refunds_filler_20.equals(other.refunds_filler_20))
			return false;
		if (refunds_filler_21 == null) {
			if (other.refunds_filler_21 != null)
				return false;
		} else if (!refunds_filler_21.equals(other.refunds_filler_21))
			return false;
		if (refunds_filler_22 == null) {
			if (other.refunds_filler_22 != null)
				return false;
		} else if (!refunds_filler_22.equals(other.refunds_filler_22))
			return false;
		if (refunds_filler_23 == null) {
			if (other.refunds_filler_23 != null)
				return false;
		} else if (!refunds_filler_23.equals(other.refunds_filler_23))
			return false;
		if (refunds_filler_24 == null) {
			if (other.refunds_filler_24 != null)
				return false;
		} else if (!refunds_filler_24.equals(other.refunds_filler_24))
			return false;
		if (refunds_filler_3 == null) {
			if (other.refunds_filler_3 != null)
				return false;
		} else if (!refunds_filler_3.equals(other.refunds_filler_3))
			return false;
		if (refunds_filler_4 == null) {
			if (other.refunds_filler_4 != null)
				return false;
		} else if (!refunds_filler_4.equals(other.refunds_filler_4))
			return false;
		if (refunds_filler_5 == null) {
			if (other.refunds_filler_5 != null)
				return false;
		} else if (!refunds_filler_5.equals(other.refunds_filler_5))
			return false;
		if (refunds_filler_6 == null) {
			if (other.refunds_filler_6 != null)
				return false;
		} else if (!refunds_filler_6.equals(other.refunds_filler_6))
			return false;
		if (refunds_filler_7 == null) {
			if (other.refunds_filler_7 != null)
				return false;
		} else if (!refunds_filler_7.equals(other.refunds_filler_7))
			return false;
		if (refunds_filler_8 == null) {
			if (other.refunds_filler_8 != null)
				return false;
		} else if (!refunds_filler_8.equals(other.refunds_filler_8))
			return false;
		if (refunds_filler_9 == null) {
			if (other.refunds_filler_9 != null)
				return false;
		} else if (!refunds_filler_9.equals(other.refunds_filler_9))
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
		if (text_table_no_996 == null) {
			if (other.text_table_no_996 != null)
				return false;
		} else if (!text_table_no_996.equals(other.text_table_no_996))
			return false;
		if (tour_no == null) {
			if (other.tour_no != null)
				return false;
		} else if (!tour_no.equals(other.tour_no))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
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
		return "AtpcoRecord3Cat27 [id=" + id + ", refunds_filler_10=" + refunds_filler_10 + ", refunds_filler_11="
				+ refunds_filler_11 + ", text_table_no_996=" + text_table_no_996 + ", refunds_filler_14="
				+ refunds_filler_14 + ", record_sequence=" + record_sequence + ", refunds_filler_15="
				+ refunds_filler_15 + ", refunds_filler_12=" + refunds_filler_12 + ", refunds_filler_13="
				+ refunds_filler_13 + ", type=" + type + ", waiver_appl=" + waiver_appl + ", package_addl_appl_2="
				+ package_addl_appl_2 + ", package_addl_appl_1=" + package_addl_appl_1 + ", waiver_9=" + waiver_9
				+ ", waiver_7=" + waiver_7 + ", waiver_8=" + waiver_8 + ", tbl_no=" + tbl_no + ", unavail=" + unavail
				+ ", waiver_1=" + waiver_1 + ", refunds_filler_19=" + refunds_filler_19 + ", rules_type=" + rules_type
				+ ", action=" + action + ", price_min_tour_1=" + price_min_tour_1 + ", waiver_2=" + waiver_2
				+ ", refunds_filler_18=" + refunds_filler_18 + ", refunds_filler_17=" + refunds_filler_17
				+ ", refunds_filler_16=" + refunds_filler_16 + ", price_min_tour_2=" + price_min_tour_2
				+ ", package_addl_amt_2=" + package_addl_amt_2 + ", refunds_filler_20=" + refunds_filler_20
				+ ", price_filler_3=" + price_filler_3 + ", refunds_filler_21=" + refunds_filler_21
				+ ", price_filler_2=" + price_filler_2 + ", refunds_filler_22=" + refunds_filler_22
				+ ", price_filler_1=" + price_filler_1 + ", refunds_filler_23=" + refunds_filler_23
				+ ", refunds_filler_24=" + refunds_filler_24 + ", tour_no=" + tour_no + ", waiver_11=" + waiver_11
				+ ", waiver_10=" + waiver_10 + ", date_table_no_994=" + date_table_no_994 + ", waiver_13=" + waiver_13
				+ ", waiver_12=" + waiver_12 + ", package_dec_1=" + package_dec_1 + ", package_cxr=" + package_cxr
				+ ", package_filler_1=" + package_filler_1 + ", waiver_reserved_1=" + waiver_reserved_1
				+ ", package_dec_2=" + package_dec_2 + ", rec_type=" + rec_type + ", price_psgr_type=" + price_psgr_type
				+ ", package_nites=" + package_nites + ", ground_cruise=" + ground_cruise + ", package_cur_1="
				+ package_cur_1 + ", price_cur_1=" + price_cur_1 + ", price_cur_2=" + price_cur_2 + ", package_cur_2="
				+ package_cur_2 + ", waiver_filler_5=" + waiver_filler_5 + ", waiver_filler_4=" + waiver_filler_4
				+ ", price_appl=" + price_appl + ", price_dec_1=" + price_dec_1 + ", price_dec_2=" + price_dec_2
				+ ", package_min_appl_1=" + package_min_appl_1 + ", package_min_appl_2=" + package_min_appl_2
				+ ", package_min_amt_2=" + package_min_amt_2 + ", package_min_amt_1=" + package_min_amt_1
				+ ", package_addl_amt_1=" + package_addl_amt_1 + ", price_age_min=" + price_age_min + ", record_batch="
				+ record_batch + ", price_age_max=" + price_age_max + ", refunds_filler_4=" + refunds_filler_4
				+ ", waiver_filler_1=" + waiver_filler_1 + ", cat_no=" + cat_no + ", refunds_filler_3="
				+ refunds_filler_3 + ", waiver_filler_2=" + waiver_filler_2 + ", refunds_filler_6=" + refunds_filler_6
				+ ", waiver_filler_3=" + waiver_filler_3 + ", package_pct=" + package_pct + ", refunds_filler_5="
				+ refunds_filler_5 + ", refunds_filler_8=" + refunds_filler_8 + ", refunds_filler_7=" + refunds_filler_7
				+ ", refunds_filler_9=" + refunds_filler_9 + ", refunds_non_ref=" + refunds_non_ref
				+ ", refunds_filler_2=" + refunds_filler_2 + ", refunds_filler_1=" + refunds_filler_1 + "]";
	}
    
    

}
