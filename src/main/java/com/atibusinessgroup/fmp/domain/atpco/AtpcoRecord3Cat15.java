package com.atibusinessgroup.fmp.domain.atpco;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_record_3_cat_015")
public class AtpcoRecord3Cat15 {

	@Id
    private String id;
	
	@Field("res")
	private String res;

	@Field("text_table_no_996")
    private String text_table_no_996;

	@Field("record_sequence")
    private String record_sequence;

	@Field("fam_grp")
    private String fam_grp;

	@Field("locale")
    private List<AtpcoRecord3Cat15Locale> locale = new ArrayList<>();

	@Field("sales_dates_latest_res")
    private Object sales_dates_latest_res;

	@Field("sales_dates_latest_tktg")
    private Object sales_dates_latest_tktg;

	@Field("tkt_iss_reserved_2")
    private String tkt_iss_reserved_2;

	@Field("cxr_gds_sale")
    private String cxr_gds_sale;

	@Field("tkt_iss_reserved_1")
    private String tkt_iss_reserved_1;

	@Field("tkt_iss_sato_cato")
    private String tkt_iss_sato_cato;

	@Field("cxr_gds_oth")
    private String cxr_gds_oth;

	@Field("ta_sale")
    private String ta_sale;

	@Field("extens")
    private String extens;
	
	@Field("cur_country_restriction")
    private String cur_country_restriction;

	@Field("cxr_gds_val")
    private String cxr_gds_val;

	@Field("fop_ca")
    private String fop_ca;

	@Field("sales_dates_earliest_tktg")
    private Object sales_dates_earliest_tktg;

	@Field("cxr_gds_seg")
    private String cxr_gds_seg;

	@Field("tbl_no")
    private String tbl_no;

	@Field("unavail")
    private String unavail;

	@Field("action")
    private String action;

	@Field("rules_type")
    private String rules_type;

	@Field("sales_dates_earliest_res")
    private Object sales_dates_earliest_res;

	@Field("ctry")
    private String ctry;

	@Field("fop_tr")
    private String fop_tr;

	@Field("tkt_iss_siti")
    private String tkt_iss_siti;

	@Field("cur_currency")
    private String cur_currency;

	@Field("fop_cr")
    private String fop_cr;

	@Field("fop_ck")
    private String fop_ck;

	@Field("tkt_iss_sito")
    private String tkt_iss_sito;

	@Field("record_batch")
    private String record_batch;

	@Field("tkt_iss_mail")
    private String tkt_iss_mail;

	@Field("date_table_no_994")
    private String date_table_no_994;

	@Field("tkt_iss_et")
    private String tkt_iss_et;

	@Field("tkt_iss_pta")
    private String tkt_iss_pta;

	@Field("tkt_iss_soto")
    private String tkt_iss_soto;

	@Field("cat_no")
    private String cat_no;

	@Field("ta_filler_1")
    private String ta_filler_1;

	@Field("tkt_iss_soti")
    private String tkt_iss_soti;

	@Field("tkt_iss_self")
    private String tkt_iss_self;

	@Field("rec_type")
    private String rec_type;

	@Field("tkt_iss_automatic_ticketing_machine")
    private String tkt_iss_automatic_ticketing_machine;

	@Field("tkt_iss_filler_1")
    private String tkt_iss_filler_1;

	@Field("tkt_iss_filler_2")
    private String tkt_iss_filler_2;

	@Field("tkt_iss_pta_tkt")
    private String tkt_iss_pta_tkt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public String getText_table_no_996() {
		return text_table_no_996;
	}

	public void setText_table_no_996(String text_table_no_996) {
		this.text_table_no_996 = text_table_no_996;
	}

	public String getRecord_sequence() {
		return record_sequence;
	}

	public void setRecord_sequence(String record_sequence) {
		this.record_sequence = record_sequence;
	}

	public String getFam_grp() {
		return fam_grp;
	}

	public void setFam_grp(String fam_grp) {
		this.fam_grp = fam_grp;
	}

	public List<AtpcoRecord3Cat15Locale> getLocale() {
		return locale;
	}

	public String getExtens() {
		return extens;
	}

	public void setExtens(String extens) {
		this.extens = extens;
	}

	public void setLocale(List<AtpcoRecord3Cat15Locale> locale) {
		this.locale = locale;
	}

	public Object getSales_dates_latest_res() {
		return sales_dates_latest_res;
	}

	public void setSales_dates_latest_res(Object sales_dates_latest_res) {
		this.sales_dates_latest_res = sales_dates_latest_res;
	}

	public Object getSales_dates_latest_tktg() {
		return sales_dates_latest_tktg;
	}

	public void setSales_dates_latest_tktg(Object sales_dates_latest_tktg) {
		this.sales_dates_latest_tktg = sales_dates_latest_tktg;
	}

	public String getTkt_iss_reserved_2() {
		return tkt_iss_reserved_2;
	}

	public void setTkt_iss_reserved_2(String tkt_iss_reserved_2) {
		this.tkt_iss_reserved_2 = tkt_iss_reserved_2;
	}

	public String getCxr_gds_sale() {
		return cxr_gds_sale;
	}

	public void setCxr_gds_sale(String cxr_gds_sale) {
		this.cxr_gds_sale = cxr_gds_sale;
	}

	public String getTkt_iss_reserved_1() {
		return tkt_iss_reserved_1;
	}

	public void setTkt_iss_reserved_1(String tkt_iss_reserved_1) {
		this.tkt_iss_reserved_1 = tkt_iss_reserved_1;
	}

	public String getTkt_iss_sato_cato() {
		return tkt_iss_sato_cato;
	}

	public void setTkt_iss_sato_cato(String tkt_iss_sato_cato) {
		this.tkt_iss_sato_cato = tkt_iss_sato_cato;
	}

	public String getCxr_gds_oth() {
		return cxr_gds_oth;
	}

	public void setCxr_gds_oth(String cxr_gds_oth) {
		this.cxr_gds_oth = cxr_gds_oth;
	}

	public String getTa_sale() {
		return ta_sale;
	}

	public void setTa_sale(String ta_sale) {
		this.ta_sale = ta_sale;
	}

	public String getCur_country_restriction() {
		return cur_country_restriction;
	}

	public void setCur_country_restriction(String cur_country_restriction) {
		this.cur_country_restriction = cur_country_restriction;
	}

	public String getCxr_gds_val() {
		return cxr_gds_val;
	}

	public void setCxr_gds_val(String cxr_gds_val) {
		this.cxr_gds_val = cxr_gds_val;
	}

	public String getFop_ca() {
		return fop_ca;
	}

	public void setFop_ca(String fop_ca) {
		this.fop_ca = fop_ca;
	}

	public Object getSales_dates_earliest_tktg() {
		return sales_dates_earliest_tktg;
	}

	public void setSales_dates_earliest_tktg(Object sales_dates_earliest_tktg) {
		this.sales_dates_earliest_tktg = sales_dates_earliest_tktg;
	}

	public String getCxr_gds_seg() {
		return cxr_gds_seg;
	}

	public void setCxr_gds_seg(String cxr_gds_seg) {
		this.cxr_gds_seg = cxr_gds_seg;
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

	public Object getSales_dates_earliest_res() {
		return sales_dates_earliest_res;
	}

	public void setSales_dates_earliest_res(Object sales_dates_earliest_res) {
		this.sales_dates_earliest_res = sales_dates_earliest_res;
	}

	public String getCtry() {
		return ctry;
	}

	public void setCtry(String ctry) {
		this.ctry = ctry;
	}

	public String getFop_tr() {
		return fop_tr;
	}

	public void setFop_tr(String fop_tr) {
		this.fop_tr = fop_tr;
	}

	public String getTkt_iss_siti() {
		return tkt_iss_siti;
	}

	public void setTkt_iss_siti(String tkt_iss_siti) {
		this.tkt_iss_siti = tkt_iss_siti;
	}

	public String getCur_currency() {
		return cur_currency;
	}

	public void setCur_currency(String cur_currency) {
		this.cur_currency = cur_currency;
	}

	public String getFop_cr() {
		return fop_cr;
	}

	public void setFop_cr(String fop_cr) {
		this.fop_cr = fop_cr;
	}

	public String getFop_ck() {
		return fop_ck;
	}

	public void setFop_ck(String fop_ck) {
		this.fop_ck = fop_ck;
	}

	public String getTkt_iss_sito() {
		return tkt_iss_sito;
	}

	public void setTkt_iss_sito(String tkt_iss_sito) {
		this.tkt_iss_sito = tkt_iss_sito;
	}

	public String getRecord_batch() {
		return record_batch;
	}

	public void setRecord_batch(String record_batch) {
		this.record_batch = record_batch;
	}

	public String getTkt_iss_mail() {
		return tkt_iss_mail;
	}

	public void setTkt_iss_mail(String tkt_iss_mail) {
		this.tkt_iss_mail = tkt_iss_mail;
	}

	public String getDate_table_no_994() {
		return date_table_no_994;
	}

	public void setDate_table_no_994(String date_table_no_994) {
		this.date_table_no_994 = date_table_no_994;
	}

	public String getTkt_iss_et() {
		return tkt_iss_et;
	}

	public void setTkt_iss_et(String tkt_iss_et) {
		this.tkt_iss_et = tkt_iss_et;
	}

	public String getTkt_iss_pta() {
		return tkt_iss_pta;
	}

	public void setTkt_iss_pta(String tkt_iss_pta) {
		this.tkt_iss_pta = tkt_iss_pta;
	}

	public String getTkt_iss_soto() {
		return tkt_iss_soto;
	}

	public void setTkt_iss_soto(String tkt_iss_soto) {
		this.tkt_iss_soto = tkt_iss_soto;
	}

	public String getCat_no() {
		return cat_no;
	}

	public void setCat_no(String cat_no) {
		this.cat_no = cat_no;
	}

	public String getTa_filler_1() {
		return ta_filler_1;
	}

	public void setTa_filler_1(String ta_filler_1) {
		this.ta_filler_1 = ta_filler_1;
	}

	public String getTkt_iss_soti() {
		return tkt_iss_soti;
	}

	public void setTkt_iss_soti(String tkt_iss_soti) {
		this.tkt_iss_soti = tkt_iss_soti;
	}

	public String getTkt_iss_self() {
		return tkt_iss_self;
	}

	public void setTkt_iss_self(String tkt_iss_self) {
		this.tkt_iss_self = tkt_iss_self;
	}

	public String getRec_type() {
		return rec_type;
	}

	public void setRec_type(String rec_type) {
		this.rec_type = rec_type;
	}

	public String getTkt_iss_automatic_ticketing_machine() {
		return tkt_iss_automatic_ticketing_machine;
	}

	public void setTkt_iss_automatic_ticketing_machine(String tkt_iss_automatic_ticketing_machine) {
		this.tkt_iss_automatic_ticketing_machine = tkt_iss_automatic_ticketing_machine;
	}

	public String getTkt_iss_filler_1() {
		return tkt_iss_filler_1;
	}

	public void setTkt_iss_filler_1(String tkt_iss_filler_1) {
		this.tkt_iss_filler_1 = tkt_iss_filler_1;
	}

	public String getTkt_iss_filler_2() {
		return tkt_iss_filler_2;
	}

	public void setTkt_iss_filler_2(String tkt_iss_filler_2) {
		this.tkt_iss_filler_2 = tkt_iss_filler_2;
	}

	public String getTkt_iss_pta_tkt() {
		return tkt_iss_pta_tkt;
	}

	public void setTkt_iss_pta_tkt(String tkt_iss_pta_tkt) {
		this.tkt_iss_pta_tkt = tkt_iss_pta_tkt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((cat_no == null) ? 0 : cat_no.hashCode());
		result = prime * result + ((ctry == null) ? 0 : ctry.hashCode());
		result = prime * result + ((cur_country_restriction == null) ? 0 : cur_country_restriction.hashCode());
		result = prime * result + ((cur_currency == null) ? 0 : cur_currency.hashCode());
		result = prime * result + ((cxr_gds_oth == null) ? 0 : cxr_gds_oth.hashCode());
		result = prime * result + ((cxr_gds_sale == null) ? 0 : cxr_gds_sale.hashCode());
		result = prime * result + ((cxr_gds_seg == null) ? 0 : cxr_gds_seg.hashCode());
		result = prime * result + ((cxr_gds_val == null) ? 0 : cxr_gds_val.hashCode());
		result = prime * result + ((date_table_no_994 == null) ? 0 : date_table_no_994.hashCode());
		result = prime * result + ((extens == null) ? 0 : extens.hashCode());
		result = prime * result + ((fam_grp == null) ? 0 : fam_grp.hashCode());
		result = prime * result + ((fop_ca == null) ? 0 : fop_ca.hashCode());
		result = prime * result + ((fop_ck == null) ? 0 : fop_ck.hashCode());
		result = prime * result + ((fop_cr == null) ? 0 : fop_cr.hashCode());
		result = prime * result + ((fop_tr == null) ? 0 : fop_tr.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((locale == null) ? 0 : locale.hashCode());
		result = prime * result + ((rec_type == null) ? 0 : rec_type.hashCode());
		result = prime * result + ((record_batch == null) ? 0 : record_batch.hashCode());
		result = prime * result + ((record_sequence == null) ? 0 : record_sequence.hashCode());
		result = prime * result + ((res == null) ? 0 : res.hashCode());
		result = prime * result + ((rules_type == null) ? 0 : rules_type.hashCode());
		result = prime * result + ((sales_dates_earliest_res == null) ? 0 : sales_dates_earliest_res.hashCode());
		result = prime * result + ((sales_dates_earliest_tktg == null) ? 0 : sales_dates_earliest_tktg.hashCode());
		result = prime * result + ((sales_dates_latest_res == null) ? 0 : sales_dates_latest_res.hashCode());
		result = prime * result + ((sales_dates_latest_tktg == null) ? 0 : sales_dates_latest_tktg.hashCode());
		result = prime * result + ((ta_filler_1 == null) ? 0 : ta_filler_1.hashCode());
		result = prime * result + ((ta_sale == null) ? 0 : ta_sale.hashCode());
		result = prime * result + ((tbl_no == null) ? 0 : tbl_no.hashCode());
		result = prime * result + ((text_table_no_996 == null) ? 0 : text_table_no_996.hashCode());
		result = prime * result
				+ ((tkt_iss_automatic_ticketing_machine == null) ? 0 : tkt_iss_automatic_ticketing_machine.hashCode());
		result = prime * result + ((tkt_iss_et == null) ? 0 : tkt_iss_et.hashCode());
		result = prime * result + ((tkt_iss_filler_1 == null) ? 0 : tkt_iss_filler_1.hashCode());
		result = prime * result + ((tkt_iss_filler_2 == null) ? 0 : tkt_iss_filler_2.hashCode());
		result = prime * result + ((tkt_iss_mail == null) ? 0 : tkt_iss_mail.hashCode());
		result = prime * result + ((tkt_iss_pta == null) ? 0 : tkt_iss_pta.hashCode());
		result = prime * result + ((tkt_iss_pta_tkt == null) ? 0 : tkt_iss_pta_tkt.hashCode());
		result = prime * result + ((tkt_iss_reserved_1 == null) ? 0 : tkt_iss_reserved_1.hashCode());
		result = prime * result + ((tkt_iss_reserved_2 == null) ? 0 : tkt_iss_reserved_2.hashCode());
		result = prime * result + ((tkt_iss_sato_cato == null) ? 0 : tkt_iss_sato_cato.hashCode());
		result = prime * result + ((tkt_iss_self == null) ? 0 : tkt_iss_self.hashCode());
		result = prime * result + ((tkt_iss_siti == null) ? 0 : tkt_iss_siti.hashCode());
		result = prime * result + ((tkt_iss_sito == null) ? 0 : tkt_iss_sito.hashCode());
		result = prime * result + ((tkt_iss_soti == null) ? 0 : tkt_iss_soti.hashCode());
		result = prime * result + ((tkt_iss_soto == null) ? 0 : tkt_iss_soto.hashCode());
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
		AtpcoRecord3Cat15 other = (AtpcoRecord3Cat15) obj;
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
		if (ctry == null) {
			if (other.ctry != null) {
				return false;
			}
		} else if (!ctry.equals(other.ctry)) {
			return false;
		}
		if (cur_country_restriction == null) {
			if (other.cur_country_restriction != null) {
				return false;
			}
		} else if (!cur_country_restriction.equals(other.cur_country_restriction)) {
			return false;
		}
		if (cur_currency == null) {
			if (other.cur_currency != null) {
				return false;
			}
		} else if (!cur_currency.equals(other.cur_currency)) {
			return false;
		}
		if (cxr_gds_oth == null) {
			if (other.cxr_gds_oth != null) {
				return false;
			}
		} else if (!cxr_gds_oth.equals(other.cxr_gds_oth)) {
			return false;
		}
		if (cxr_gds_sale == null) {
			if (other.cxr_gds_sale != null) {
				return false;
			}
		} else if (!cxr_gds_sale.equals(other.cxr_gds_sale)) {
			return false;
		}
		if (cxr_gds_seg == null) {
			if (other.cxr_gds_seg != null) {
				return false;
			}
		} else if (!cxr_gds_seg.equals(other.cxr_gds_seg)) {
			return false;
		}
		if (cxr_gds_val == null) {
			if (other.cxr_gds_val != null) {
				return false;
			}
		} else if (!cxr_gds_val.equals(other.cxr_gds_val)) {
			return false;
		}
		if (date_table_no_994 == null) {
			if (other.date_table_no_994 != null) {
				return false;
			}
		} else if (!date_table_no_994.equals(other.date_table_no_994)) {
			return false;
		}
		if (extens == null) {
			if (other.extens != null) {
				return false;
			}
		} else if (!extens.equals(other.extens)) {
			return false;
		}
		if (fam_grp == null) {
			if (other.fam_grp != null) {
				return false;
			}
		} else if (!fam_grp.equals(other.fam_grp)) {
			return false;
		}
		if (fop_ca == null) {
			if (other.fop_ca != null) {
				return false;
			}
		} else if (!fop_ca.equals(other.fop_ca)) {
			return false;
		}
		if (fop_ck == null) {
			if (other.fop_ck != null) {
				return false;
			}
		} else if (!fop_ck.equals(other.fop_ck)) {
			return false;
		}
		if (fop_cr == null) {
			if (other.fop_cr != null) {
				return false;
			}
		} else if (!fop_cr.equals(other.fop_cr)) {
			return false;
		}
		if (fop_tr == null) {
			if (other.fop_tr != null) {
				return false;
			}
		} else if (!fop_tr.equals(other.fop_tr)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (locale == null) {
			if (other.locale != null) {
				return false;
			}
		} else if (!locale.equals(other.locale)) {
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
		if (res == null) {
			if (other.res != null) {
				return false;
			}
		} else if (!res.equals(other.res)) {
			return false;
		}
		if (rules_type == null) {
			if (other.rules_type != null) {
				return false;
			}
		} else if (!rules_type.equals(other.rules_type)) {
			return false;
		}
		if (sales_dates_earliest_res == null) {
			if (other.sales_dates_earliest_res != null) {
				return false;
			}
		} else if (!sales_dates_earliest_res.equals(other.sales_dates_earliest_res)) {
			return false;
		}
		if (sales_dates_earliest_tktg == null) {
			if (other.sales_dates_earliest_tktg != null) {
				return false;
			}
		} else if (!sales_dates_earliest_tktg.equals(other.sales_dates_earliest_tktg)) {
			return false;
		}
		if (sales_dates_latest_res == null) {
			if (other.sales_dates_latest_res != null) {
				return false;
			}
		} else if (!sales_dates_latest_res.equals(other.sales_dates_latest_res)) {
			return false;
		}
		if (sales_dates_latest_tktg == null) {
			if (other.sales_dates_latest_tktg != null) {
				return false;
			}
		} else if (!sales_dates_latest_tktg.equals(other.sales_dates_latest_tktg)) {
			return false;
		}
		if (ta_filler_1 == null) {
			if (other.ta_filler_1 != null) {
				return false;
			}
		} else if (!ta_filler_1.equals(other.ta_filler_1)) {
			return false;
		}
		if (ta_sale == null) {
			if (other.ta_sale != null) {
				return false;
			}
		} else if (!ta_sale.equals(other.ta_sale)) {
			return false;
		}
		if (tbl_no == null) {
			if (other.tbl_no != null) {
				return false;
			}
		} else if (!tbl_no.equals(other.tbl_no)) {
			return false;
		}
		if (text_table_no_996 == null) {
			if (other.text_table_no_996 != null) {
				return false;
			}
		} else if (!text_table_no_996.equals(other.text_table_no_996)) {
			return false;
		}
		if (tkt_iss_automatic_ticketing_machine == null) {
			if (other.tkt_iss_automatic_ticketing_machine != null) {
				return false;
			}
		} else if (!tkt_iss_automatic_ticketing_machine.equals(other.tkt_iss_automatic_ticketing_machine)) {
			return false;
		}
		if (tkt_iss_et == null) {
			if (other.tkt_iss_et != null) {
				return false;
			}
		} else if (!tkt_iss_et.equals(other.tkt_iss_et)) {
			return false;
		}
		if (tkt_iss_filler_1 == null) {
			if (other.tkt_iss_filler_1 != null) {
				return false;
			}
		} else if (!tkt_iss_filler_1.equals(other.tkt_iss_filler_1)) {
			return false;
		}
		if (tkt_iss_filler_2 == null) {
			if (other.tkt_iss_filler_2 != null) {
				return false;
			}
		} else if (!tkt_iss_filler_2.equals(other.tkt_iss_filler_2)) {
			return false;
		}
		if (tkt_iss_mail == null) {
			if (other.tkt_iss_mail != null) {
				return false;
			}
		} else if (!tkt_iss_mail.equals(other.tkt_iss_mail)) {
			return false;
		}
		if (tkt_iss_pta == null) {
			if (other.tkt_iss_pta != null) {
				return false;
			}
		} else if (!tkt_iss_pta.equals(other.tkt_iss_pta)) {
			return false;
		}
		if (tkt_iss_pta_tkt == null) {
			if (other.tkt_iss_pta_tkt != null) {
				return false;
			}
		} else if (!tkt_iss_pta_tkt.equals(other.tkt_iss_pta_tkt)) {
			return false;
		}
		if (tkt_iss_reserved_1 == null) {
			if (other.tkt_iss_reserved_1 != null) {
				return false;
			}
		} else if (!tkt_iss_reserved_1.equals(other.tkt_iss_reserved_1)) {
			return false;
		}
		if (tkt_iss_reserved_2 == null) {
			if (other.tkt_iss_reserved_2 != null) {
				return false;
			}
		} else if (!tkt_iss_reserved_2.equals(other.tkt_iss_reserved_2)) {
			return false;
		}
		if (tkt_iss_sato_cato == null) {
			if (other.tkt_iss_sato_cato != null) {
				return false;
			}
		} else if (!tkt_iss_sato_cato.equals(other.tkt_iss_sato_cato)) {
			return false;
		}
		if (tkt_iss_self == null) {
			if (other.tkt_iss_self != null) {
				return false;
			}
		} else if (!tkt_iss_self.equals(other.tkt_iss_self)) {
			return false;
		}
		if (tkt_iss_siti == null) {
			if (other.tkt_iss_siti != null) {
				return false;
			}
		} else if (!tkt_iss_siti.equals(other.tkt_iss_siti)) {
			return false;
		}
		if (tkt_iss_sito == null) {
			if (other.tkt_iss_sito != null) {
				return false;
			}
		} else if (!tkt_iss_sito.equals(other.tkt_iss_sito)) {
			return false;
		}
		if (tkt_iss_soti == null) {
			if (other.tkt_iss_soti != null) {
				return false;
			}
		} else if (!tkt_iss_soti.equals(other.tkt_iss_soti)) {
			return false;
		}
		if (tkt_iss_soto == null) {
			if (other.tkt_iss_soto != null) {
				return false;
			}
		} else if (!tkt_iss_soto.equals(other.tkt_iss_soto)) {
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
		return "AtpcoRecord3Cat15 [id=" + id + ", res=" + res + ", text_table_no_996=" + text_table_no_996
				+ ", record_sequence=" + record_sequence + ", fam_grp=" + fam_grp + ", locale=" + locale
				+ ", sales_dates_latest_res=" + sales_dates_latest_res + ", sales_dates_latest_tktg="
				+ sales_dates_latest_tktg + ", tkt_iss_reserved_2=" + tkt_iss_reserved_2 + ", cxr_gds_sale="
				+ cxr_gds_sale + ", tkt_iss_reserved_1=" + tkt_iss_reserved_1 + ", tkt_iss_sato_cato="
				+ tkt_iss_sato_cato + ", cxr_gds_oth=" + cxr_gds_oth + ", ta_sale=" + ta_sale + ", extens=" + extens
				+ ", cur_country_restriction=" + cur_country_restriction + ", cxr_gds_val=" + cxr_gds_val + ", fop_ca="
				+ fop_ca + ", sales_dates_earliest_tktg=" + sales_dates_earliest_tktg + ", cxr_gds_seg=" + cxr_gds_seg
				+ ", tbl_no=" + tbl_no + ", unavail=" + unavail + ", action=" + action + ", rules_type=" + rules_type
				+ ", sales_dates_earliest_res=" + sales_dates_earliest_res + ", ctry=" + ctry + ", fop_tr=" + fop_tr
				+ ", tkt_iss_siti=" + tkt_iss_siti + ", cur_currency=" + cur_currency + ", fop_cr=" + fop_cr
				+ ", fop_ck=" + fop_ck + ", tkt_iss_sito=" + tkt_iss_sito + ", record_batch=" + record_batch
				+ ", tkt_iss_mail=" + tkt_iss_mail + ", date_table_no_994=" + date_table_no_994 + ", tkt_iss_et="
				+ tkt_iss_et + ", tkt_iss_pta=" + tkt_iss_pta + ", tkt_iss_soto=" + tkt_iss_soto + ", cat_no=" + cat_no
				+ ", ta_filler_1=" + ta_filler_1 + ", tkt_iss_soti=" + tkt_iss_soti + ", tkt_iss_self=" + tkt_iss_self
				+ ", rec_type=" + rec_type + ", tkt_iss_automatic_ticketing_machine="
				+ tkt_iss_automatic_ticketing_machine + ", tkt_iss_filler_1=" + tkt_iss_filler_1 + ", tkt_iss_filler_2="
				+ tkt_iss_filler_2 + ", tkt_iss_pta_tkt=" + tkt_iss_pta_tkt + "]";
	}
}
