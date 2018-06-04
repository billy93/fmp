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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCurrency_2_dec() {
		return currency_2_dec;
	}

	public void setCurrency_2_dec(String currency_2_dec) {
		this.currency_2_dec = currency_2_dec;
	}

	public String getNo_of_transfers_min() {
		return no_of_transfers_min;
	}

	public void setNo_of_transfers_min(String no_of_transfers_min) {
		this.no_of_transfers_min = no_of_transfers_min;
	}

	public String getOo_max() {
		return oo_max;
	}

	public void setOo_max(String oo_max) {
		this.oo_max = oo_max;
	}

	public String getRecord_sequence() {
		return record_sequence;
	}

	public void setRecord_sequence(String record_sequence) {
		this.record_sequence = record_sequence;
	}

	public String getOth_oth() {
		return oth_oth;
	}

	public void setOth_oth(String oth_oth) {
		this.oth_oth = oth_oth;
	}

	public String getTransfer_types_prm_prm() {
		return transfer_types_prm_prm;
	}

	public void setTransfer_types_prm_prm(String transfer_types_prm_prm) {
		this.transfer_types_prm_prm = transfer_types_prm_prm;
	}

	public String getTransfer_types_ss_max() {
		return transfer_types_ss_max;
	}

	public void setTransfer_types_ss_max(String transfer_types_ss_max) {
		this.transfer_types_ss_max = transfer_types_ss_max;
	}

	public Decimal128 getCurrency_2_charge_1_amt() {
		return currency_2_charge_1_amt;
	}

	public void setCurrency_2_charge_1_amt(Decimal128 currency_2_charge_1_amt) {
		this.currency_2_charge_1_amt = currency_2_charge_1_amt;
	}

	public Decimal128 getCurrency_1_charge_2_amt() {
		return currency_1_charge_2_amt;
	}

	public void setCurrency_1_charge_2_amt(Decimal128 currency_1_charge_2_amt) {
		this.currency_1_charge_2_amt = currency_1_charge_2_amt;
	}

	public String getEmbedded_surface_tbl_976() {
		return embedded_surface_tbl_976;
	}

	public void setEmbedded_surface_tbl_976(String embedded_surface_tbl_976) {
		this.embedded_surface_tbl_976 = embedded_surface_tbl_976;
	}

	public String getCurrency_1_charge_2_no() {
		return currency_1_charge_2_no;
	}

	public void setCurrency_1_charge_2_no(String currency_1_charge_2_no) {
		this.currency_1_charge_2_no = currency_1_charge_2_no;
	}

	public String getTransfer_types_pp_max() {
		return transfer_types_pp_max;
	}

	public void setTransfer_types_pp_max(String transfer_types_pp_max) {
		this.transfer_types_pp_max = transfer_types_pp_max;
	}

	public String getCurrency_1_cur() {
		return currency_1_cur;
	}

	public void setCurrency_1_cur(String currency_1_cur) {
		this.currency_1_cur = currency_1_cur;
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

	public String getFare_break_surface_tbl_976() {
		return fare_break_surface_tbl_976;
	}

	public void setFare_break_surface_tbl_976(String fare_break_surface_tbl_976) {
		this.fare_break_surface_tbl_976 = fare_break_surface_tbl_976;
	}

	public String getNo_of_transfers_or() {
		return no_of_transfers_or;
	}

	public void setNo_of_transfers_or(String no_of_transfers_or) {
		this.no_of_transfers_or = no_of_transfers_or;
	}

	public String getNo_of_transfers_max() {
		return no_of_transfers_max;
	}

	public void setNo_of_transfers_max(String no_of_transfers_max) {
		this.no_of_transfers_max = no_of_transfers_max;
	}

	public String getPrm_oth() {
		return prm_oth;
	}

	public void setPrm_oth(String prm_oth) {
		this.prm_oth = prm_oth;
	}

	public List<AtpcoRecord3Cat09Transfers> getTransfers() {
		return transfers;
	}

	public void setTransfers(List<AtpcoRecord3Cat09Transfers> transfers) {
		this.transfers = transfers;
	}

	public String getCurrency_1_appl() {
		return currency_1_appl;
	}

	public void setCurrency_1_appl(String currency_1_appl) {
		this.currency_1_appl = currency_1_appl;
	}

	public String getNo_of_transfers_filler_1() {
		return no_of_transfers_filler_1;
	}

	public void setNo_of_transfers_filler_1(String no_of_transfers_filler_1) {
		this.no_of_transfers_filler_1 = no_of_transfers_filler_1;
	}

	public String getDate_tbl_no_994() {
		return date_tbl_no_994;
	}

	public void setDate_tbl_no_994(String date_tbl_no_994) {
		this.date_tbl_no_994 = date_tbl_no_994;
	}

	public Decimal128 getCurrency_1_charge_1_amt() {
		return currency_1_charge_1_amt;
	}

	public void setCurrency_1_charge_1_amt(Decimal128 currency_1_charge_1_amt) {
		this.currency_1_charge_1_amt = currency_1_charge_1_amt;
	}

	public String getText_tbl_no_996() {
		return text_tbl_no_996;
	}

	public void setText_tbl_no_996(String text_tbl_no_996) {
		this.text_tbl_no_996 = text_tbl_no_996;
	}

	public String getCurrency_1_charge_1_no() {
		return currency_1_charge_1_no;
	}

	public void setCurrency_1_charge_1_no(String currency_1_charge_1_no) {
		this.currency_1_charge_1_no = currency_1_charge_1_no;
	}

	public String getFare_break_surface_tag() {
		return fare_break_surface_tag;
	}

	public void setFare_break_surface_tag(String fare_break_surface_tag) {
		this.fare_break_surface_tag = fare_break_surface_tag;
	}

	public String getRecord_batch() {
		return record_batch;
	}

	public void setRecord_batch(String record_batch) {
		this.record_batch = record_batch;
	}

	public String getNo_of_transfers_in() {
		return no_of_transfers_in;
	}

	public void setNo_of_transfers_in(String no_of_transfers_in) {
		this.no_of_transfers_in = no_of_transfers_in;
	}

	public Decimal128 getCurrency_2_charge_2_amt() {
		return currency_2_charge_2_amt;
	}

	public void setCurrency_2_charge_2_amt(Decimal128 currency_2_charge_2_amt) {
		this.currency_2_charge_2_amt = currency_2_charge_2_amt;
	}

	public String getEmbedded_surface_tag() {
		return embedded_surface_tag;
	}

	public void setEmbedded_surface_tag(String embedded_surface_tag) {
		this.embedded_surface_tag = embedded_surface_tag;
	}

	public String getCurrency_2_cur() {
		return currency_2_cur;
	}

	public void setCurrency_2_cur(String currency_2_cur) {
		this.currency_2_cur = currency_2_cur;
	}

	public String getCat_no() {
		return cat_no;
	}

	public void setCat_no(String cat_no) {
		this.cat_no = cat_no;
	}

	public String getCurrency_1_dec() {
		return currency_1_dec;
	}

	public void setCurrency_1_dec(String currency_1_dec) {
		this.currency_1_dec = currency_1_dec;
	}

	public String getRec_type() {
		return rec_type;
	}

	public void setRec_type(String rec_type) {
		this.rec_type = rec_type;
	}

	public String getNo_of_transfers_out() {
		return no_of_transfers_out;
	}

	public void setNo_of_transfers_out(String no_of_transfers_out) {
		this.no_of_transfers_out = no_of_transfers_out;
	}

	public String getFiller_4() {
		return filler_4;
	}

	public void setFiller_4(String filler_4) {
		this.filler_4 = filler_4;
	}

	public String getFiller_3() {
		return filler_3;
	}

	public void setFiller_3(String filler_3) {
		this.filler_3 = filler_3;
	}

	public String getTransfer_types_sme_sme() {
		return transfer_types_sme_sme;
	}

	public void setTransfer_types_sme_sme(String transfer_types_sme_sme) {
		this.transfer_types_sme_sme = transfer_types_sme_sme;
	}

	public String getPo_max() {
		return po_max;
	}

	public void setPo_max(String po_max) {
		this.po_max = po_max;
	}

	public String getFiller_2() {
		return filler_2;
	}

	public void setFiller_2(String filler_2) {
		this.filler_2 = filler_2;
	}

	public String getFiller_1() {
		return filler_1;
	}

	public void setFiller_1(String filler_1) {
		this.filler_1 = filler_1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((cat_no == null) ? 0 : cat_no.hashCode());
		result = prime * result + ((currency_1_appl == null) ? 0 : currency_1_appl.hashCode());
		result = prime * result + ((currency_1_charge_1_amt == null) ? 0 : currency_1_charge_1_amt.hashCode());
		result = prime * result + ((currency_1_charge_1_no == null) ? 0 : currency_1_charge_1_no.hashCode());
		result = prime * result + ((currency_1_charge_2_amt == null) ? 0 : currency_1_charge_2_amt.hashCode());
		result = prime * result + ((currency_1_charge_2_no == null) ? 0 : currency_1_charge_2_no.hashCode());
		result = prime * result + ((currency_1_cur == null) ? 0 : currency_1_cur.hashCode());
		result = prime * result + ((currency_1_dec == null) ? 0 : currency_1_dec.hashCode());
		result = prime * result + ((currency_2_charge_1_amt == null) ? 0 : currency_2_charge_1_amt.hashCode());
		result = prime * result + ((currency_2_charge_2_amt == null) ? 0 : currency_2_charge_2_amt.hashCode());
		result = prime * result + ((currency_2_cur == null) ? 0 : currency_2_cur.hashCode());
		result = prime * result + ((currency_2_dec == null) ? 0 : currency_2_dec.hashCode());
		result = prime * result + ((date_tbl_no_994 == null) ? 0 : date_tbl_no_994.hashCode());
		result = prime * result + ((embedded_surface_tag == null) ? 0 : embedded_surface_tag.hashCode());
		result = prime * result + ((embedded_surface_tbl_976 == null) ? 0 : embedded_surface_tbl_976.hashCode());
		result = prime * result + ((fare_break_surface_tag == null) ? 0 : fare_break_surface_tag.hashCode());
		result = prime * result + ((fare_break_surface_tbl_976 == null) ? 0 : fare_break_surface_tbl_976.hashCode());
		result = prime * result + ((filler_1 == null) ? 0 : filler_1.hashCode());
		result = prime * result + ((filler_2 == null) ? 0 : filler_2.hashCode());
		result = prime * result + ((filler_3 == null) ? 0 : filler_3.hashCode());
		result = prime * result + ((filler_4 == null) ? 0 : filler_4.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((no_of_transfers_filler_1 == null) ? 0 : no_of_transfers_filler_1.hashCode());
		result = prime * result + ((no_of_transfers_in == null) ? 0 : no_of_transfers_in.hashCode());
		result = prime * result + ((no_of_transfers_max == null) ? 0 : no_of_transfers_max.hashCode());
		result = prime * result + ((no_of_transfers_min == null) ? 0 : no_of_transfers_min.hashCode());
		result = prime * result + ((no_of_transfers_or == null) ? 0 : no_of_transfers_or.hashCode());
		result = prime * result + ((no_of_transfers_out == null) ? 0 : no_of_transfers_out.hashCode());
		result = prime * result + ((oo_max == null) ? 0 : oo_max.hashCode());
		result = prime * result + ((oth_oth == null) ? 0 : oth_oth.hashCode());
		result = prime * result + ((po_max == null) ? 0 : po_max.hashCode());
		result = prime * result + ((prm_oth == null) ? 0 : prm_oth.hashCode());
		result = prime * result + ((rec_type == null) ? 0 : rec_type.hashCode());
		result = prime * result + ((record_batch == null) ? 0 : record_batch.hashCode());
		result = prime * result + ((record_sequence == null) ? 0 : record_sequence.hashCode());
		result = prime * result + ((rules_type == null) ? 0 : rules_type.hashCode());
		result = prime * result + ((tbl_no == null) ? 0 : tbl_no.hashCode());
		result = prime * result + ((text_tbl_no_996 == null) ? 0 : text_tbl_no_996.hashCode());
		result = prime * result + ((transfer_types_pp_max == null) ? 0 : transfer_types_pp_max.hashCode());
		result = prime * result + ((transfer_types_prm_prm == null) ? 0 : transfer_types_prm_prm.hashCode());
		result = prime * result + ((transfer_types_sme_sme == null) ? 0 : transfer_types_sme_sme.hashCode());
		result = prime * result + ((transfer_types_ss_max == null) ? 0 : transfer_types_ss_max.hashCode());
		result = prime * result + ((transfers == null) ? 0 : transfers.hashCode());
		result = prime * result + ((unavail == null) ? 0 : unavail.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AtpcoRecord3Cat09 other = (AtpcoRecord3Cat09) obj;
		if (action == null) {
			if (other.action != null) {
				return false;
			}
		} else if (!action.equals(other.action)) {
			return false;
		}
		if (cat_no == null) {
			if (other.cat_no != null) {
				return false;
			}
		} else if (!cat_no.equals(other.cat_no)) {
			return false;
		}
		if (currency_1_appl == null) {
			if (other.currency_1_appl != null) {
				return false;
			}
		} else if (!currency_1_appl.equals(other.currency_1_appl)) {
			return false;
		}
		if (currency_1_charge_1_amt == null) {
			if (other.currency_1_charge_1_amt != null) {
				return false;
			}
		} else if (!currency_1_charge_1_amt.equals(other.currency_1_charge_1_amt)) {
			return false;
		}
		if (currency_1_charge_1_no == null) {
			if (other.currency_1_charge_1_no != null) {
				return false;
			}
		} else if (!currency_1_charge_1_no.equals(other.currency_1_charge_1_no)) {
			return false;
		}
		if (currency_1_charge_2_amt == null) {
			if (other.currency_1_charge_2_amt != null) {
				return false;
			}
		} else if (!currency_1_charge_2_amt.equals(other.currency_1_charge_2_amt)) {
			return false;
		}
		if (currency_1_charge_2_no == null) {
			if (other.currency_1_charge_2_no != null) {
				return false;
			}
		} else if (!currency_1_charge_2_no.equals(other.currency_1_charge_2_no)) {
			return false;
		}
		if (currency_1_cur == null) {
			if (other.currency_1_cur != null) {
				return false;
			}
		} else if (!currency_1_cur.equals(other.currency_1_cur)) {
			return false;
		}
		if (currency_1_dec == null) {
			if (other.currency_1_dec != null) {
				return false;
			}
		} else if (!currency_1_dec.equals(other.currency_1_dec)) {
			return false;
		}
		if (currency_2_charge_1_amt == null) {
			if (other.currency_2_charge_1_amt != null) {
				return false;
			}
		} else if (!currency_2_charge_1_amt.equals(other.currency_2_charge_1_amt)) {
			return false;
		}
		if (currency_2_charge_2_amt == null) {
			if (other.currency_2_charge_2_amt != null) {
				return false;
			}
		} else if (!currency_2_charge_2_amt.equals(other.currency_2_charge_2_amt)) {
			return false;
		}
		if (currency_2_cur == null) {
			if (other.currency_2_cur != null) {
				return false;
			}
		} else if (!currency_2_cur.equals(other.currency_2_cur)) {
			return false;
		}
		if (currency_2_dec == null) {
			if (other.currency_2_dec != null) {
				return false;
			}
		} else if (!currency_2_dec.equals(other.currency_2_dec)) {
			return false;
		}
		if (date_tbl_no_994 == null) {
			if (other.date_tbl_no_994 != null) {
				return false;
			}
		} else if (!date_tbl_no_994.equals(other.date_tbl_no_994)) {
			return false;
		}
		if (embedded_surface_tag == null) {
			if (other.embedded_surface_tag != null) {
				return false;
			}
		} else if (!embedded_surface_tag.equals(other.embedded_surface_tag)) {
			return false;
		}
		if (embedded_surface_tbl_976 == null) {
			if (other.embedded_surface_tbl_976 != null) {
				return false;
			}
		} else if (!embedded_surface_tbl_976.equals(other.embedded_surface_tbl_976)) {
			return false;
		}
		if (fare_break_surface_tag == null) {
			if (other.fare_break_surface_tag != null) {
				return false;
			}
		} else if (!fare_break_surface_tag.equals(other.fare_break_surface_tag)) {
			return false;
		}
		if (fare_break_surface_tbl_976 == null) {
			if (other.fare_break_surface_tbl_976 != null) {
				return false;
			}
		} else if (!fare_break_surface_tbl_976.equals(other.fare_break_surface_tbl_976)) {
			return false;
		}
		if (filler_1 == null) {
			if (other.filler_1 != null) {
				return false;
			}
		} else if (!filler_1.equals(other.filler_1)) {
			return false;
		}
		if (filler_2 == null) {
			if (other.filler_2 != null) {
				return false;
			}
		} else if (!filler_2.equals(other.filler_2)) {
			return false;
		}
		if (filler_3 == null) {
			if (other.filler_3 != null) {
				return false;
			}
		} else if (!filler_3.equals(other.filler_3)) {
			return false;
		}
		if (filler_4 == null) {
			if (other.filler_4 != null) {
				return false;
			}
		} else if (!filler_4.equals(other.filler_4)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (no_of_transfers_filler_1 == null) {
			if (other.no_of_transfers_filler_1 != null) {
				return false;
			}
		} else if (!no_of_transfers_filler_1.equals(other.no_of_transfers_filler_1)) {
			return false;
		}
		if (no_of_transfers_in == null) {
			if (other.no_of_transfers_in != null) {
				return false;
			}
		} else if (!no_of_transfers_in.equals(other.no_of_transfers_in)) {
			return false;
		}
		if (no_of_transfers_max == null) {
			if (other.no_of_transfers_max != null) {
				return false;
			}
		} else if (!no_of_transfers_max.equals(other.no_of_transfers_max)) {
			return false;
		}
		if (no_of_transfers_min == null) {
			if (other.no_of_transfers_min != null) {
				return false;
			}
		} else if (!no_of_transfers_min.equals(other.no_of_transfers_min)) {
			return false;
		}
		if (no_of_transfers_or == null) {
			if (other.no_of_transfers_or != null) {
				return false;
			}
		} else if (!no_of_transfers_or.equals(other.no_of_transfers_or)) {
			return false;
		}
		if (no_of_transfers_out == null) {
			if (other.no_of_transfers_out != null) {
				return false;
			}
		} else if (!no_of_transfers_out.equals(other.no_of_transfers_out)) {
			return false;
		}
		if (oo_max == null) {
			if (other.oo_max != null) {
				return false;
			}
		} else if (!oo_max.equals(other.oo_max)) {
			return false;
		}
		if (oth_oth == null) {
			if (other.oth_oth != null) {
				return false;
			}
		} else if (!oth_oth.equals(other.oth_oth)) {
			return false;
		}
		if (po_max == null) {
			if (other.po_max != null) {
				return false;
			}
		} else if (!po_max.equals(other.po_max)) {
			return false;
		}
		if (prm_oth == null) {
			if (other.prm_oth != null) {
				return false;
			}
		} else if (!prm_oth.equals(other.prm_oth)) {
			return false;
		}
		if (rec_type == null) {
			if (other.rec_type != null) {
				return false;
			}
		} else if (!rec_type.equals(other.rec_type)) {
			return false;
		}
		if (record_batch == null) {
			if (other.record_batch != null) {
				return false;
			}
		} else if (!record_batch.equals(other.record_batch)) {
			return false;
		}
		if (record_sequence == null) {
			if (other.record_sequence != null) {
				return false;
			}
		} else if (!record_sequence.equals(other.record_sequence)) {
			return false;
		}
		if (rules_type == null) {
			if (other.rules_type != null) {
				return false;
			}
		} else if (!rules_type.equals(other.rules_type)) {
			return false;
		}
		if (tbl_no == null) {
			if (other.tbl_no != null) {
				return false;
			}
		} else if (!tbl_no.equals(other.tbl_no)) {
			return false;
		}
		if (text_tbl_no_996 == null) {
			if (other.text_tbl_no_996 != null) {
				return false;
			}
		} else if (!text_tbl_no_996.equals(other.text_tbl_no_996)) {
			return false;
		}
		if (transfer_types_pp_max == null) {
			if (other.transfer_types_pp_max != null) {
				return false;
			}
		} else if (!transfer_types_pp_max.equals(other.transfer_types_pp_max)) {
			return false;
		}
		if (transfer_types_prm_prm == null) {
			if (other.transfer_types_prm_prm != null) {
				return false;
			}
		} else if (!transfer_types_prm_prm.equals(other.transfer_types_prm_prm)) {
			return false;
		}
		if (transfer_types_sme_sme == null) {
			if (other.transfer_types_sme_sme != null) {
				return false;
			}
		} else if (!transfer_types_sme_sme.equals(other.transfer_types_sme_sme)) {
			return false;
		}
		if (transfer_types_ss_max == null) {
			if (other.transfer_types_ss_max != null) {
				return false;
			}
		} else if (!transfer_types_ss_max.equals(other.transfer_types_ss_max)) {
			return false;
		}
		if (transfers == null) {
			if (other.transfers != null) {
				return false;
			}
		} else if (!transfers.equals(other.transfers)) {
			return false;
		}
		if (unavail == null) {
			if (other.unavail != null) {
				return false;
			}
		} else if (!unavail.equals(other.unavail)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoRecord3Cat09 [id=" + id + ", currency_2_dec=" + currency_2_dec + ", no_of_transfers_min="
				+ no_of_transfers_min + ", oo_max=" + oo_max + ", record_sequence=" + record_sequence + ", oth_oth="
				+ oth_oth + ", transfer_types_prm_prm=" + transfer_types_prm_prm + ", transfer_types_ss_max="
				+ transfer_types_ss_max + ", currency_2_charge_1_amt=" + currency_2_charge_1_amt
				+ ", currency_1_charge_2_amt=" + currency_1_charge_2_amt + ", embedded_surface_tbl_976="
				+ embedded_surface_tbl_976 + ", currency_1_charge_2_no=" + currency_1_charge_2_no
				+ ", transfer_types_pp_max=" + transfer_types_pp_max + ", currency_1_cur=" + currency_1_cur
				+ ", tbl_no=" + tbl_no + ", unavail=" + unavail + ", action=" + action + ", rules_type=" + rules_type
				+ ", fare_break_surface_tbl_976=" + fare_break_surface_tbl_976 + ", no_of_transfers_or="
				+ no_of_transfers_or + ", no_of_transfers_max=" + no_of_transfers_max + ", prm_oth=" + prm_oth
				+ ", transfers=" + transfers + ", currency_1_appl=" + currency_1_appl + ", no_of_transfers_filler_1="
				+ no_of_transfers_filler_1 + ", date_tbl_no_994=" + date_tbl_no_994 + ", currency_1_charge_1_amt="
				+ currency_1_charge_1_amt + ", text_tbl_no_996=" + text_tbl_no_996 + ", currency_1_charge_1_no="
				+ currency_1_charge_1_no + ", fare_break_surface_tag=" + fare_break_surface_tag + ", record_batch="
				+ record_batch + ", no_of_transfers_in=" + no_of_transfers_in + ", currency_2_charge_2_amt="
				+ currency_2_charge_2_amt + ", embedded_surface_tag=" + embedded_surface_tag + ", currency_2_cur="
				+ currency_2_cur + ", cat_no=" + cat_no + ", currency_1_dec=" + currency_1_dec + ", rec_type="
				+ rec_type + ", no_of_transfers_out=" + no_of_transfers_out + ", filler_4=" + filler_4 + ", filler_3="
				+ filler_3 + ", transfer_types_sme_sme=" + transfer_types_sme_sme + ", po_max=" + po_max + ", filler_2="
				+ filler_2 + ", filler_1=" + filler_1 + "]";
	}
}
