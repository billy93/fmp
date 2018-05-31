package com.atibusinessgroup.fmp.domain.atpco;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.Decimal128;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_record_3_cat_035")
public class AtpcoRecord3Cat35 {

	@Id
    private String id;
	
	@Field("ticketing_application")
	private String ticketing_application;

	@Field("record_sequence")
    private String record_sequence;

	@Field("commission_cur_2")
    private String commission_cur_2;

	@Field("commission_cur_1")
    private String commission_cur_1;

	@Field("sec_tbl_no_983")
    private String sec_tbl_no_983;

	@Field("ptc")
    private String ptc;

	@Field("ticketing")
    private List<AtpcoRecord3Cat35Ticketing> ticketing = new ArrayList<>();

	@Field("commission_amt_1")
    private Decimal128 commission_amt_1;

	@Field("commission_amt_2")
    private Decimal128 commission_amt_2;

	@Field("tbl_no")
    private String tbl_no;

	@Field("unavail")
    private String unavail;

	@Field("commission_net_gross")
    private String commission_net_gross;

	@Field("action")
    private String action;

	@Field("rules_type")
    private String rules_type;

	@Field("commission_dec_1")
    private String commission_dec_1;

	@Field("commission_dec_2")
    private String commission_dec_2;

	@Field("method_type")
    private String method_type;

	@Field("ticketing_ind")
    private String ticketing_ind;

	@Field("ticketing_cxr")
    private String ticketing_cxr;

	@Field("date_tbl_no_994")
    private String date_tbl_no_994;

	@Field("text_tbl_no_996")
    private String text_tbl_no_996;

	@Field("record_batch")
    private String record_batch;

	@Field("upgrade")
    private String upgrade;

	@Field("reserved_3")
    private String reserved_3;

	@Field("cat_no")
    private String cat_no;

	@Field("commission_percent")
    private String commission_percent;
	
	@Field("reserved_2")
    private String reserved_2;

	@Field("rec_type")
    private String rec_type;

	@Field("reserved_1")
    private String reserved_1;

	@Field("fare_crtr_tbl_no_979")
    private String fare_crtr_tbl_no_979;

	@Field("filler_1")
    private String filler_1;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTicketing_application() {
		return ticketing_application;
	}

	public void setTicketing_application(String ticketing_application) {
		this.ticketing_application = ticketing_application;
	}

	public String getRecord_sequence() {
		return record_sequence;
	}

	public void setRecord_sequence(String record_sequence) {
		this.record_sequence = record_sequence;
	}

	public String getCommission_cur_2() {
		return commission_cur_2;
	}

	public void setCommission_cur_2(String commission_cur_2) {
		this.commission_cur_2 = commission_cur_2;
	}

	public String getCommission_cur_1() {
		return commission_cur_1;
	}

	public void setCommission_cur_1(String commission_cur_1) {
		this.commission_cur_1 = commission_cur_1;
	}

	public String getSec_tbl_no_983() {
		return sec_tbl_no_983;
	}

	public void setSec_tbl_no_983(String sec_tbl_no_983) {
		this.sec_tbl_no_983 = sec_tbl_no_983;
	}

	public String getPtc() {
		return ptc;
	}

	public void setPtc(String ptc) {
		this.ptc = ptc;
	}

	public List<AtpcoRecord3Cat35Ticketing> getTicketing() {
		return ticketing;
	}

	public void setTicketing(List<AtpcoRecord3Cat35Ticketing> ticketing) {
		this.ticketing = ticketing;
	}

	public Decimal128 getCommission_amt_1() {
		return commission_amt_1;
	}

	public void setCommission_amt_1(Decimal128 commission_amt_1) {
		this.commission_amt_1 = commission_amt_1;
	}

	public Decimal128 getCommission_amt_2() {
		return commission_amt_2;
	}

	public void setCommission_amt_2(Decimal128 commission_amt_2) {
		this.commission_amt_2 = commission_amt_2;
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

	public String getCommission_net_gross() {
		return commission_net_gross;
	}

	public void setCommission_net_gross(String commission_net_gross) {
		this.commission_net_gross = commission_net_gross;
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

	public String getCommission_dec_1() {
		return commission_dec_1;
	}

	public void setCommission_dec_1(String commission_dec_1) {
		this.commission_dec_1 = commission_dec_1;
	}

	public String getCommission_dec_2() {
		return commission_dec_2;
	}

	public void setCommission_dec_2(String commission_dec_2) {
		this.commission_dec_2 = commission_dec_2;
	}

	public String getMethod_type() {
		return method_type;
	}

	public void setMethod_type(String method_type) {
		this.method_type = method_type;
	}

	public String getTicketing_ind() {
		return ticketing_ind;
	}

	public void setTicketing_ind(String ticketing_ind) {
		this.ticketing_ind = ticketing_ind;
	}

	public String getTicketing_cxr() {
		return ticketing_cxr;
	}

	public void setTicketing_cxr(String ticketing_cxr) {
		this.ticketing_cxr = ticketing_cxr;
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

	public String getRecord_batch() {
		return record_batch;
	}

	public void setRecord_batch(String record_batch) {
		this.record_batch = record_batch;
	}

	public String getUpgrade() {
		return upgrade;
	}

	public void setUpgrade(String upgrade) {
		this.upgrade = upgrade;
	}

	public String getReserved_3() {
		return reserved_3;
	}

	public void setReserved_3(String reserved_3) {
		this.reserved_3 = reserved_3;
	}

	public String getCat_no() {
		return cat_no;
	}

	public void setCat_no(String cat_no) {
		this.cat_no = cat_no;
	}

	public String getCommission_percent() {
		return commission_percent;
	}

	public void setCommission_percent(String commission_percent) {
		this.commission_percent = commission_percent;
	}

	public String getReserved_2() {
		return reserved_2;
	}

	public void setReserved_2(String reserved_2) {
		this.reserved_2 = reserved_2;
	}

	public String getRec_type() {
		return rec_type;
	}

	public void setRec_type(String rec_type) {
		this.rec_type = rec_type;
	}

	public String getReserved_1() {
		return reserved_1;
	}

	public void setReserved_1(String reserved_1) {
		this.reserved_1 = reserved_1;
	}

	public String getFare_crtr_tbl_no_979() {
		return fare_crtr_tbl_no_979;
	}

	public void setFare_crtr_tbl_no_979(String fare_crtr_tbl_no_979) {
		this.fare_crtr_tbl_no_979 = fare_crtr_tbl_no_979;
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
		result = prime * result + ((commission_amt_1 == null) ? 0 : commission_amt_1.hashCode());
		result = prime * result + ((commission_amt_2 == null) ? 0 : commission_amt_2.hashCode());
		result = prime * result + ((commission_cur_1 == null) ? 0 : commission_cur_1.hashCode());
		result = prime * result + ((commission_cur_2 == null) ? 0 : commission_cur_2.hashCode());
		result = prime * result + ((commission_dec_1 == null) ? 0 : commission_dec_1.hashCode());
		result = prime * result + ((commission_dec_2 == null) ? 0 : commission_dec_2.hashCode());
		result = prime * result + ((commission_net_gross == null) ? 0 : commission_net_gross.hashCode());
		result = prime * result + ((commission_percent == null) ? 0 : commission_percent.hashCode());
		result = prime * result + ((date_tbl_no_994 == null) ? 0 : date_tbl_no_994.hashCode());
		result = prime * result + ((fare_crtr_tbl_no_979 == null) ? 0 : fare_crtr_tbl_no_979.hashCode());
		result = prime * result + ((filler_1 == null) ? 0 : filler_1.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((method_type == null) ? 0 : method_type.hashCode());
		result = prime * result + ((ptc == null) ? 0 : ptc.hashCode());
		result = prime * result + ((rec_type == null) ? 0 : rec_type.hashCode());
		result = prime * result + ((record_batch == null) ? 0 : record_batch.hashCode());
		result = prime * result + ((record_sequence == null) ? 0 : record_sequence.hashCode());
		result = prime * result + ((reserved_1 == null) ? 0 : reserved_1.hashCode());
		result = prime * result + ((reserved_2 == null) ? 0 : reserved_2.hashCode());
		result = prime * result + ((reserved_3 == null) ? 0 : reserved_3.hashCode());
		result = prime * result + ((rules_type == null) ? 0 : rules_type.hashCode());
		result = prime * result + ((sec_tbl_no_983 == null) ? 0 : sec_tbl_no_983.hashCode());
		result = prime * result + ((tbl_no == null) ? 0 : tbl_no.hashCode());
		result = prime * result + ((text_tbl_no_996 == null) ? 0 : text_tbl_no_996.hashCode());
		result = prime * result + ((ticketing == null) ? 0 : ticketing.hashCode());
		result = prime * result + ((ticketing_application == null) ? 0 : ticketing_application.hashCode());
		result = prime * result + ((ticketing_cxr == null) ? 0 : ticketing_cxr.hashCode());
		result = prime * result + ((ticketing_ind == null) ? 0 : ticketing_ind.hashCode());
		result = prime * result + ((unavail == null) ? 0 : unavail.hashCode());
		result = prime * result + ((upgrade == null) ? 0 : upgrade.hashCode());
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
		AtpcoRecord3Cat35 other = (AtpcoRecord3Cat35) obj;
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
		if (commission_amt_1 == null) {
			if (other.commission_amt_1 != null)
				return false;
		} else if (!commission_amt_1.equals(other.commission_amt_1))
			return false;
		if (commission_amt_2 == null) {
			if (other.commission_amt_2 != null)
				return false;
		} else if (!commission_amt_2.equals(other.commission_amt_2))
			return false;
		if (commission_cur_1 == null) {
			if (other.commission_cur_1 != null)
				return false;
		} else if (!commission_cur_1.equals(other.commission_cur_1))
			return false;
		if (commission_cur_2 == null) {
			if (other.commission_cur_2 != null)
				return false;
		} else if (!commission_cur_2.equals(other.commission_cur_2))
			return false;
		if (commission_dec_1 == null) {
			if (other.commission_dec_1 != null)
				return false;
		} else if (!commission_dec_1.equals(other.commission_dec_1))
			return false;
		if (commission_dec_2 == null) {
			if (other.commission_dec_2 != null)
				return false;
		} else if (!commission_dec_2.equals(other.commission_dec_2))
			return false;
		if (commission_net_gross == null) {
			if (other.commission_net_gross != null)
				return false;
		} else if (!commission_net_gross.equals(other.commission_net_gross))
			return false;
		if (commission_percent == null) {
			if (other.commission_percent != null)
				return false;
		} else if (!commission_percent.equals(other.commission_percent))
			return false;
		if (date_tbl_no_994 == null) {
			if (other.date_tbl_no_994 != null)
				return false;
		} else if (!date_tbl_no_994.equals(other.date_tbl_no_994))
			return false;
		if (fare_crtr_tbl_no_979 == null) {
			if (other.fare_crtr_tbl_no_979 != null)
				return false;
		} else if (!fare_crtr_tbl_no_979.equals(other.fare_crtr_tbl_no_979))
			return false;
		if (filler_1 == null) {
			if (other.filler_1 != null)
				return false;
		} else if (!filler_1.equals(other.filler_1))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (method_type == null) {
			if (other.method_type != null)
				return false;
		} else if (!method_type.equals(other.method_type))
			return false;
		if (ptc == null) {
			if (other.ptc != null)
				return false;
		} else if (!ptc.equals(other.ptc))
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
		if (reserved_2 == null) {
			if (other.reserved_2 != null)
				return false;
		} else if (!reserved_2.equals(other.reserved_2))
			return false;
		if (reserved_3 == null) {
			if (other.reserved_3 != null)
				return false;
		} else if (!reserved_3.equals(other.reserved_3))
			return false;
		if (rules_type == null) {
			if (other.rules_type != null)
				return false;
		} else if (!rules_type.equals(other.rules_type))
			return false;
		if (sec_tbl_no_983 == null) {
			if (other.sec_tbl_no_983 != null)
				return false;
		} else if (!sec_tbl_no_983.equals(other.sec_tbl_no_983))
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
		if (ticketing == null) {
			if (other.ticketing != null)
				return false;
		} else if (!ticketing.equals(other.ticketing))
			return false;
		if (ticketing_application == null) {
			if (other.ticketing_application != null)
				return false;
		} else if (!ticketing_application.equals(other.ticketing_application))
			return false;
		if (ticketing_cxr == null) {
			if (other.ticketing_cxr != null)
				return false;
		} else if (!ticketing_cxr.equals(other.ticketing_cxr))
			return false;
		if (ticketing_ind == null) {
			if (other.ticketing_ind != null)
				return false;
		} else if (!ticketing_ind.equals(other.ticketing_ind))
			return false;
		if (unavail == null) {
			if (other.unavail != null)
				return false;
		} else if (!unavail.equals(other.unavail))
			return false;
		if (upgrade == null) {
			if (other.upgrade != null)
				return false;
		} else if (!upgrade.equals(other.upgrade))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoRecord3Cat35 [id=" + id + ", ticketing_application=" + ticketing_application + ", record_sequence="
				+ record_sequence + ", commission_cur_2=" + commission_cur_2 + ", commission_cur_1=" + commission_cur_1
				+ ", sec_tbl_no_983=" + sec_tbl_no_983 + ", ptc=" + ptc + ", ticketing=" + ticketing
				+ ", commission_amt_1=" + commission_amt_1 + ", commission_amt_2=" + commission_amt_2 + ", tbl_no="
				+ tbl_no + ", unavail=" + unavail + ", commission_net_gross=" + commission_net_gross + ", action="
				+ action + ", rules_type=" + rules_type + ", commission_dec_1=" + commission_dec_1
				+ ", commission_dec_2=" + commission_dec_2 + ", method_type=" + method_type + ", ticketing_ind="
				+ ticketing_ind + ", ticketing_cxr=" + ticketing_cxr + ", date_tbl_no_994=" + date_tbl_no_994
				+ ", text_tbl_no_996=" + text_tbl_no_996 + ", record_batch=" + record_batch + ", upgrade=" + upgrade
				+ ", reserved_3=" + reserved_3 + ", cat_no=" + cat_no + ", commission_percent=" + commission_percent
				+ ", reserved_2=" + reserved_2 + ", rec_type=" + rec_type + ", reserved_1=" + reserved_1
				+ ", fare_crtr_tbl_no_979=" + fare_crtr_tbl_no_979 + ", filler_1=" + filler_1 + "]";
	}
	
	

}
