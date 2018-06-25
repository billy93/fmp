package com.atibusinessgroup.fmp.domain.atpco;

import org.bson.types.Decimal128;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_record_3_cat_016")
public class AtpcoRecord3Cat16 {

	@Id
    private String id;
	
	@Field("penalties_tkt")
	private String penalties_tkt;

	@Field("penalties_chrg")
    private String penalties_chrg;

	@Field("waiver_schedule_change")
    private String waiver_schedule_change;

	@Field("record_sequence")
    private String record_sequence;

	@Field("penalties_chgn")
    private String penalties_chgn;

	@Field("penalties_fail")
    private String penalties_fail;

	@Field("penalties_geo_tbl_995")
    private String penalties_geo_tbl_995;

	@Field("charges_dec_1")
    private String charges_dec_1;

	@Field("charges_dec_2")
    private String charges_dec_2;

	@Field("waiver_ticket_upgrade")
    private String waiver_ticket_upgrade;

	@Field("charges_cur_1")
    private String charges_cur_1;

	@Field("charges_cur_2")
    private String charges_cur_2;

	@Field("waiver_death_of_passenger")
    private String waiver_death_of_passenger;

	@Field("tbl_no")
    private String tbl_no;

	@Field("unavail")
    private String unavail;

	@Field("action")
    private String action;

	@Field("rules_type")
    private String rules_type;

	@Field("charges_h_l")
    private String charges_h_l;

	@Field("charges_por")
    private String charges_por;

	@Field("penalties_rep")
    private String penalties_rep;

	@Field("charges_percent")
    private Decimal128 charges_percent;

	@Field("charges_appl")
    private String charges_appl;

	@Field("waiver_death_of_immediate_family_member")
    private String waiver_death_of_immediate_family_member;
	
	@Field("waiver_illness_of_immediate_family_member")
    private String waiver_illness_of_immediate_family_member;

	@Field("date_tbl_no_994")
    private String date_tbl_no_994;

	@Field("appl_canx")
    private String appl_canx;

	@Field("tkt_nrf")
    private String tkt_nrf;

	@Field("appl_inv")
    private String appl_inv;

	@Field("text_tbl_no_996")
    private String text_tbl_no_996;

	@Field("appl_vol")
    private String appl_vol;

	@Field("record_batch")
    private String record_batch;

	@Field("penalties_canx")
    private String penalties_canx;

	@Field("charges_amt_1")
    private Decimal128 charges_amt_1;

	@Field("charges_amt_2")
    private Decimal128 charges_amt_2;

	@Field("cat_no")
    private String cat_no;

	@Field("waiver_illness_of_passenger")
    private String waiver_illness_of_passenger;
	
	@Field("rec_type")
    private String rec_type;

	@Field("penalties_pta")
    private String penalties_pta;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPenalties_tkt() {
		return penalties_tkt;
	}

	public void setPenalties_tkt(String penalties_tkt) {
		this.penalties_tkt = penalties_tkt;
	}

	public String getPenalties_chrg() {
		return penalties_chrg;
	}

	public void setPenalties_chrg(String penalties_chrg) {
		this.penalties_chrg = penalties_chrg;
	}

	public String getWaiver_schedule_change() {
		return waiver_schedule_change;
	}

	public void setWaiver_schedule_change(String waiver_schedule_change) {
		this.waiver_schedule_change = waiver_schedule_change;
	}

	public String getRecord_sequence() {
		return record_sequence;
	}

	public void setRecord_sequence(String record_sequence) {
		this.record_sequence = record_sequence;
	}

	public String getPenalties_chgn() {
		return penalties_chgn;
	}

	public void setPenalties_chgn(String penalties_chgn) {
		this.penalties_chgn = penalties_chgn;
	}

	public String getPenalties_fail() {
		return penalties_fail;
	}

	public void setPenalties_fail(String penalties_fail) {
		this.penalties_fail = penalties_fail;
	}

	public String getPenalties_geo_tbl_995() {
		return penalties_geo_tbl_995;
	}

	public void setPenalties_geo_tbl_995(String penalties_geo_tbl_995) {
		this.penalties_geo_tbl_995 = penalties_geo_tbl_995;
	}

	public String getCharges_dec_1() {
		return charges_dec_1;
	}

	public void setCharges_dec_1(String charges_dec_1) {
		this.charges_dec_1 = charges_dec_1;
	}

	public String getCharges_dec_2() {
		return charges_dec_2;
	}

	public void setCharges_dec_2(String charges_dec_2) {
		this.charges_dec_2 = charges_dec_2;
	}

	public String getWaiver_ticket_upgrade() {
		return waiver_ticket_upgrade;
	}

	public void setWaiver_ticket_upgrade(String waiver_ticket_upgrade) {
		this.waiver_ticket_upgrade = waiver_ticket_upgrade;
	}

	public String getCharges_cur_1() {
		return charges_cur_1;
	}

	public void setCharges_cur_1(String charges_cur_1) {
		this.charges_cur_1 = charges_cur_1;
	}

	public String getCharges_cur_2() {
		return charges_cur_2;
	}

	public void setCharges_cur_2(String charges_cur_2) {
		this.charges_cur_2 = charges_cur_2;
	}

	public String getWaiver_death_of_passenger() {
		return waiver_death_of_passenger;
	}

	public void setWaiver_death_of_passenger(String waiver_death_of_passenger) {
		this.waiver_death_of_passenger = waiver_death_of_passenger;
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

	public String getCharges_h_l() {
		return charges_h_l;
	}

	public void setCharges_h_l(String charges_h_l) {
		this.charges_h_l = charges_h_l;
	}

	public String getCharges_por() {
		return charges_por;
	}

	public void setCharges_por(String charges_por) {
		this.charges_por = charges_por;
	}

	public String getPenalties_rep() {
		return penalties_rep;
	}

	public void setPenalties_rep(String penalties_rep) {
		this.penalties_rep = penalties_rep;
	}

	public Decimal128 getCharges_percent() {
		return charges_percent;
	}

	public void setCharges_percent(Decimal128 charges_percent) {
		this.charges_percent = charges_percent;
	}

	public String getCharges_appl() {
		return charges_appl;
	}

	public void setCharges_appl(String charges_appl) {
		this.charges_appl = charges_appl;
	}

	public String getWaiver_death_of_immediate_family_member() {
		return waiver_death_of_immediate_family_member;
	}

	public void setWaiver_death_of_immediate_family_member(String waiver_death_of_immediate_family_member) {
		this.waiver_death_of_immediate_family_member = waiver_death_of_immediate_family_member;
	}

	public String getDate_tbl_no_994() {
		return date_tbl_no_994;
	}

	public void setDate_tbl_no_994(String date_tbl_no_994) {
		this.date_tbl_no_994 = date_tbl_no_994;
	}

	public String getAppl_canx() {
		return appl_canx;
	}

	public void setAppl_canx(String appl_canx) {
		this.appl_canx = appl_canx;
	}

	public String getTkt_nrf() {
		return tkt_nrf;
	}

	public void setTkt_nrf(String tkt_nrf) {
		this.tkt_nrf = tkt_nrf;
	}

	public String getAppl_inv() {
		return appl_inv;
	}

	public void setAppl_inv(String appl_inv) {
		this.appl_inv = appl_inv;
	}

	public String getText_tbl_no_996() {
		return text_tbl_no_996;
	}

	public void setText_tbl_no_996(String text_tbl_no_996) {
		this.text_tbl_no_996 = text_tbl_no_996;
	}

	public String getAppl_vol() {
		return appl_vol;
	}

	public void setAppl_vol(String appl_vol) {
		this.appl_vol = appl_vol;
	}

	public String getRecord_batch() {
		return record_batch;
	}

	public void setRecord_batch(String record_batch) {
		this.record_batch = record_batch;
	}

	public String getPenalties_canx() {
		return penalties_canx;
	}

	public void setPenalties_canx(String penalties_canx) {
		this.penalties_canx = penalties_canx;
	}

	public Decimal128 getCharges_amt_1() {
		return charges_amt_1;
	}

	public void setCharges_amt_1(Decimal128 charges_amt_1) {
		this.charges_amt_1 = charges_amt_1;
	}

	public Decimal128 getCharges_amt_2() {
		return charges_amt_2;
	}

	public void setCharges_amt_2(Decimal128 charges_amt_2) {
		this.charges_amt_2 = charges_amt_2;
	}

	public String getCat_no() {
		return cat_no;
	}

	public void setCat_no(String cat_no) {
		this.cat_no = cat_no;
	}

	public String getWaiver_illness_of_passenger() {
		return waiver_illness_of_passenger;
	}

	public void setWaiver_illness_of_passenger(String waiver_illness_of_passenger) {
		this.waiver_illness_of_passenger = waiver_illness_of_passenger;
	}

	public String getRec_type() {
		return rec_type;
	}

	public void setRec_type(String rec_type) {
		this.rec_type = rec_type;
	}

	public String getPenalties_pta() {
		return penalties_pta;
	}

	public void setPenalties_pta(String penalties_pta) {
		this.penalties_pta = penalties_pta;
	}
	
	public String getWaiver_illness_of_immediate_family_member() {
		return waiver_illness_of_immediate_family_member;
	}

	public void setWaiver_illness_of_immediate_family_member(String waiver_illness_of_immediate_family_member) {
		this.waiver_illness_of_immediate_family_member = waiver_illness_of_immediate_family_member;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((appl_canx == null) ? 0 : appl_canx.hashCode());
		result = prime * result + ((appl_inv == null) ? 0 : appl_inv.hashCode());
		result = prime * result + ((appl_vol == null) ? 0 : appl_vol.hashCode());
		result = prime * result + ((cat_no == null) ? 0 : cat_no.hashCode());
		result = prime * result + ((charges_amt_1 == null) ? 0 : charges_amt_1.hashCode());
		result = prime * result + ((charges_amt_2 == null) ? 0 : charges_amt_2.hashCode());
		result = prime * result + ((charges_appl == null) ? 0 : charges_appl.hashCode());
		result = prime * result + ((charges_cur_1 == null) ? 0 : charges_cur_1.hashCode());
		result = prime * result + ((charges_cur_2 == null) ? 0 : charges_cur_2.hashCode());
		result = prime * result + ((charges_dec_1 == null) ? 0 : charges_dec_1.hashCode());
		result = prime * result + ((charges_dec_2 == null) ? 0 : charges_dec_2.hashCode());
		result = prime * result + ((charges_h_l == null) ? 0 : charges_h_l.hashCode());
		result = prime * result + ((charges_percent == null) ? 0 : charges_percent.hashCode());
		result = prime * result + ((charges_por == null) ? 0 : charges_por.hashCode());
		result = prime * result + ((date_tbl_no_994 == null) ? 0 : date_tbl_no_994.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((penalties_canx == null) ? 0 : penalties_canx.hashCode());
		result = prime * result + ((penalties_chgn == null) ? 0 : penalties_chgn.hashCode());
		result = prime * result + ((penalties_chrg == null) ? 0 : penalties_chrg.hashCode());
		result = prime * result + ((penalties_fail == null) ? 0 : penalties_fail.hashCode());
		result = prime * result + ((penalties_geo_tbl_995 == null) ? 0 : penalties_geo_tbl_995.hashCode());
		result = prime * result + ((penalties_pta == null) ? 0 : penalties_pta.hashCode());
		result = prime * result + ((penalties_rep == null) ? 0 : penalties_rep.hashCode());
		result = prime * result + ((penalties_tkt == null) ? 0 : penalties_tkt.hashCode());
		result = prime * result + ((rec_type == null) ? 0 : rec_type.hashCode());
		result = prime * result + ((record_batch == null) ? 0 : record_batch.hashCode());
		result = prime * result + ((record_sequence == null) ? 0 : record_sequence.hashCode());
		result = prime * result + ((rules_type == null) ? 0 : rules_type.hashCode());
		result = prime * result + ((tbl_no == null) ? 0 : tbl_no.hashCode());
		result = prime * result + ((text_tbl_no_996 == null) ? 0 : text_tbl_no_996.hashCode());
		result = prime * result + ((tkt_nrf == null) ? 0 : tkt_nrf.hashCode());
		result = prime * result + ((unavail == null) ? 0 : unavail.hashCode());
		result = prime * result + ((waiver_death_of_immediate_family_member == null) ? 0
				: waiver_death_of_immediate_family_member.hashCode());
		result = prime * result + ((waiver_death_of_passenger == null) ? 0 : waiver_death_of_passenger.hashCode());
		result = prime * result + ((waiver_illness_of_immediate_family_member == null) ? 0
				: waiver_illness_of_immediate_family_member.hashCode());
		result = prime * result + ((waiver_illness_of_passenger == null) ? 0 : waiver_illness_of_passenger.hashCode());
		result = prime * result + ((waiver_schedule_change == null) ? 0 : waiver_schedule_change.hashCode());
		result = prime * result + ((waiver_ticket_upgrade == null) ? 0 : waiver_ticket_upgrade.hashCode());
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
		AtpcoRecord3Cat16 other = (AtpcoRecord3Cat16) obj;
		if (action == null) {
			if (other.action != null) {
				return false;
			}
		} else if (!action.equals(other.action)) {
			return false;
		}
		if (appl_canx == null) {
			if (other.appl_canx != null) {
				return false;
			}
		} else if (!appl_canx.equals(other.appl_canx)) {
			return false;
		}
		if (appl_inv == null) {
			if (other.appl_inv != null) {
				return false;
			}
		} else if (!appl_inv.equals(other.appl_inv)) {
			return false;
		}
		if (appl_vol == null) {
			if (other.appl_vol != null) {
				return false;
			}
		} else if (!appl_vol.equals(other.appl_vol)) {
			return false;
		}
		if (cat_no == null) {
			if (other.cat_no != null) {
				return false;
			}
		} else if (!cat_no.equals(other.cat_no)) {
			return false;
		}
		if (charges_amt_1 == null) {
			if (other.charges_amt_1 != null) {
				return false;
			}
		} else if (!charges_amt_1.equals(other.charges_amt_1)) {
			return false;
		}
		if (charges_amt_2 == null) {
			if (other.charges_amt_2 != null) {
				return false;
			}
		} else if (!charges_amt_2.equals(other.charges_amt_2)) {
			return false;
		}
		if (charges_appl == null) {
			if (other.charges_appl != null) {
				return false;
			}
		} else if (!charges_appl.equals(other.charges_appl)) {
			return false;
		}
		if (charges_cur_1 == null) {
			if (other.charges_cur_1 != null) {
				return false;
			}
		} else if (!charges_cur_1.equals(other.charges_cur_1)) {
			return false;
		}
		if (charges_cur_2 == null) {
			if (other.charges_cur_2 != null) {
				return false;
			}
		} else if (!charges_cur_2.equals(other.charges_cur_2)) {
			return false;
		}
		if (charges_dec_1 == null) {
			if (other.charges_dec_1 != null) {
				return false;
			}
		} else if (!charges_dec_1.equals(other.charges_dec_1)) {
			return false;
		}
		if (charges_dec_2 == null) {
			if (other.charges_dec_2 != null) {
				return false;
			}
		} else if (!charges_dec_2.equals(other.charges_dec_2)) {
			return false;
		}
		if (charges_h_l == null) {
			if (other.charges_h_l != null) {
				return false;
			}
		} else if (!charges_h_l.equals(other.charges_h_l)) {
			return false;
		}
		if (charges_percent == null) {
			if (other.charges_percent != null) {
				return false;
			}
		} else if (!charges_percent.equals(other.charges_percent)) {
			return false;
		}
		if (charges_por == null) {
			if (other.charges_por != null) {
				return false;
			}
		} else if (!charges_por.equals(other.charges_por)) {
			return false;
		}
		if (date_tbl_no_994 == null) {
			if (other.date_tbl_no_994 != null) {
				return false;
			}
		} else if (!date_tbl_no_994.equals(other.date_tbl_no_994)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (penalties_canx == null) {
			if (other.penalties_canx != null) {
				return false;
			}
		} else if (!penalties_canx.equals(other.penalties_canx)) {
			return false;
		}
		if (penalties_chgn == null) {
			if (other.penalties_chgn != null) {
				return false;
			}
		} else if (!penalties_chgn.equals(other.penalties_chgn)) {
			return false;
		}
		if (penalties_chrg == null) {
			if (other.penalties_chrg != null) {
				return false;
			}
		} else if (!penalties_chrg.equals(other.penalties_chrg)) {
			return false;
		}
		if (penalties_fail == null) {
			if (other.penalties_fail != null) {
				return false;
			}
		} else if (!penalties_fail.equals(other.penalties_fail)) {
			return false;
		}
		if (penalties_geo_tbl_995 == null) {
			if (other.penalties_geo_tbl_995 != null) {
				return false;
			}
		} else if (!penalties_geo_tbl_995.equals(other.penalties_geo_tbl_995)) {
			return false;
		}
		if (penalties_pta == null) {
			if (other.penalties_pta != null) {
				return false;
			}
		} else if (!penalties_pta.equals(other.penalties_pta)) {
			return false;
		}
		if (penalties_rep == null) {
			if (other.penalties_rep != null) {
				return false;
			}
		} else if (!penalties_rep.equals(other.penalties_rep)) {
			return false;
		}
		if (penalties_tkt == null) {
			if (other.penalties_tkt != null) {
				return false;
			}
		} else if (!penalties_tkt.equals(other.penalties_tkt)) {
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
		if (tkt_nrf == null) {
			if (other.tkt_nrf != null) {
				return false;
			}
		} else if (!tkt_nrf.equals(other.tkt_nrf)) {
			return false;
		}
		if (unavail == null) {
			if (other.unavail != null) {
				return false;
			}
		} else if (!unavail.equals(other.unavail)) {
			return false;
		}
		if (waiver_death_of_immediate_family_member == null) {
			if (other.waiver_death_of_immediate_family_member != null) {
				return false;
			}
		} else if (!waiver_death_of_immediate_family_member.equals(other.waiver_death_of_immediate_family_member)) {
			return false;
		}
		if (waiver_death_of_passenger == null) {
			if (other.waiver_death_of_passenger != null) {
				return false;
			}
		} else if (!waiver_death_of_passenger.equals(other.waiver_death_of_passenger)) {
			return false;
		}
		if (waiver_illness_of_immediate_family_member == null) {
			if (other.waiver_illness_of_immediate_family_member != null) {
				return false;
			}
		} else if (!waiver_illness_of_immediate_family_member.equals(other.waiver_illness_of_immediate_family_member)) {
			return false;
		}
		if (waiver_illness_of_passenger == null) {
			if (other.waiver_illness_of_passenger != null) {
				return false;
			}
		} else if (!waiver_illness_of_passenger.equals(other.waiver_illness_of_passenger)) {
			return false;
		}
		if (waiver_schedule_change == null) {
			if (other.waiver_schedule_change != null) {
				return false;
			}
		} else if (!waiver_schedule_change.equals(other.waiver_schedule_change)) {
			return false;
		}
		if (waiver_ticket_upgrade == null) {
			if (other.waiver_ticket_upgrade != null) {
				return false;
			}
		} else if (!waiver_ticket_upgrade.equals(other.waiver_ticket_upgrade)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoRecord3Cat16 [id=" + id + ", penalties_tkt=" + penalties_tkt + ", penalties_chrg=" + penalties_chrg
				+ ", waiver_schedule_change=" + waiver_schedule_change + ", record_sequence=" + record_sequence
				+ ", penalties_chgn=" + penalties_chgn + ", penalties_fail=" + penalties_fail
				+ ", penalties_geo_tbl_995=" + penalties_geo_tbl_995 + ", charges_dec_1=" + charges_dec_1
				+ ", charges_dec_2=" + charges_dec_2 + ", waiver_ticket_upgrade=" + waiver_ticket_upgrade
				+ ", charges_cur_1=" + charges_cur_1 + ", charges_cur_2=" + charges_cur_2
				+ ", waiver_death_of_passenger=" + waiver_death_of_passenger + ", tbl_no=" + tbl_no + ", unavail="
				+ unavail + ", action=" + action + ", rules_type=" + rules_type + ", charges_h_l=" + charges_h_l
				+ ", charges_por=" + charges_por + ", penalties_rep=" + penalties_rep + ", charges_percent="
				+ charges_percent + ", charges_appl=" + charges_appl + ", waiver_death_of_immediate_family_member="
				+ waiver_death_of_immediate_family_member + ", waiver_illness_of_immediate_family_member="
				+ waiver_illness_of_immediate_family_member + ", date_tbl_no_994=" + date_tbl_no_994 + ", appl_canx="
				+ appl_canx + ", tkt_nrf=" + tkt_nrf + ", appl_inv=" + appl_inv + ", text_tbl_no_996=" + text_tbl_no_996
				+ ", appl_vol=" + appl_vol + ", record_batch=" + record_batch + ", penalties_canx=" + penalties_canx
				+ ", charges_amt_1=" + charges_amt_1 + ", charges_amt_2=" + charges_amt_2 + ", cat_no=" + cat_no
				+ ", waiver_illness_of_passenger=" + waiver_illness_of_passenger + ", rec_type=" + rec_type
				+ ", penalties_pta=" + penalties_pta + "]";
	}
}
