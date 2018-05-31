package com.atibusinessgroup.fmp.domain.atpco;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.Decimal128;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_record_3_cat_009")
public class AtpcoRecord3Cat09 {

	@Id
    private String id;
	
	@Field("currency_2_dec")
	private String currency_2_dec;

	@Field("no_of_transfers_min")
    private String no_of_transfers_min;

	@Field("oo_max")
    private String oo_max;

	@Field("record_sequence")
    private String record_sequence;

	@Field("oth_oth")
    private String oth_oth;

	@Field("transfer_types_prm_prm")
    private String transfer_types_prm_prm;

	@Field("transfer_types_ss_max")
    private String transfer_types_ss_max;

	@Field("currency_2_charge_1_amt")
    private Decimal128 currency_2_charge_1_amt;

	@Field("currency_1_charge_2_amt")
    private Decimal128 currency_1_charge_2_amt;

	@Field("embedded_surface_tbl_976")
    private String embedded_surface_tbl_976;

	@Field("currency_1_charge_2_no")
    private String currency_1_charge_2_no;

	@Field("transfer_types_pp_max")
    private String transfer_types_pp_max;

	@Field("currency_1_cur")
    private String currency_1_cur;

	@Field("tbl_no")
    private String tbl_no;

	@Field("unavail")
    private String unavail;

	@Field("action")
    private String action;

	@Field("rules_type")
    private String rules_type;

	@Field("fare_break_surface_tbl_976")
    private String fare_break_surface_tbl_976;

	@Field("no_of_transfers_or")
    private String no_of_transfers_or;

	@Field("no_of_transfers_max")
    private String no_of_transfers_max;

	@Field("prm_oth")
    private String prm_oth;

	@Field("transfers")
    private List<AtpcoRecord3Cat09Transfers> transfers = new ArrayList<>();

	@Field("currency_1_appl")
    private String currency_1_appl;

	@Field("no_of_transfers_filler_1")
    private String no_of_transfers_filler_1;

	@Field("date_tbl_no_994")
    private String date_tbl_no_994;

	@Field("currency_1_charge_1_amt")
    private Decimal128 currency_1_charge_1_amt;

	@Field("text_tbl_no_996")
    private String text_tbl_no_996;

	@Field("currency_1_charge_1_no")
    private String currency_1_charge_1_no;

	@Field("fare_break_surface_tag")
    private String fare_break_surface_tag;

	@Field("record_batch")
    private String record_batch;

	@Field("no_of_transfers_in")
    private String no_of_transfers_in;

	@Field("currency_2_charge_2_amt")
    private Decimal128 currency_2_charge_2_amt;

	@Field("embedded_surface_tag")
    private String embedded_surface_tag;

	@Field("currency_2_cur")
    private String currency_2_cur;

	@Field("cat_no")
    private String cat_no;

	@Field("currency_1_dec")
    private String currency_1_dec;

	@Field("rec_type")
    private String rec_type;

	@Field("no_of_transfers_out")
    private String no_of_transfers_out;

	@Field("filler_4")
    private String filler_4;

	@Field("filler_3")
    private String filler_3;

	@Field("transfer_types_sme_sme")
    private String transfer_types_sme_sme;

	@Field("po_max")
    private String po_max;

	@Field("filler_2")
    private String filler_2;
	
	@Field("filler_1")
    private String filler_1;

}
