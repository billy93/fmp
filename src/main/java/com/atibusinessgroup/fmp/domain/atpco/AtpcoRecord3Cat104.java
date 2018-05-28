package com.atibusinessgroup.fmp.domain.atpco;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_record_3_cat_104")
public class AtpcoRecord3Cat104 {

	@Id
    private String id;
	
	@Field("record_sequence")
	private String record_sequence;

	@Field("dom")
    private String dom;

	@Field("normal")
    private String normal;

	@Field("same")
    private String same;

	@Field("tkt")
    private String tkt;

	@Field("all_segs")
    private String all_segs;

	@Field("sp_filler_1")
    private String sp_filler_1;

	@Field("tbl_no")
    private String tbl_no;

	@Field("intl")
    private String intl;

	@Field("geo_type")
    private String geo_type;

	@Field("unavail")
    private String unavail;

	@Field("fare_brk_stop")
    private String fare_brk_stop;

	@Field("action")
    private String action;

	@Field("rules_type")
    private String rules_type;

	@Field("sp_spec")
    private String sp_spec;

	@Field("geo_appl")
    private String geo_appl;

	@Field("aba_comb")
    private String aba_comb;

	@Field("us_can")
    private String us_can;

	@Field("date_tbl_no_994")
    private String date_tbl_no_994;

	@Field("geo_loc_1")
    private String geo_loc_1;

	@Field("restr")
    private String restr;

	@Field("geo_loc_2")
    private String geo_loc_2;

	@Field("text_tbl_no_996")
    private String text_tbl_no_996;

	@Field("record_batch")
    private String record_batch;

	@Field("const_point")
    private String const_point;

	@Field("cat_no")
    private String cat_no;

	@Field("rec_type")
    private String rec_type;

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

	public String getRecord_sequence() {
		return record_sequence;
	}

	public void setRecord_sequence(String record_sequence) {
		this.record_sequence = record_sequence;
	}

	public String getDom() {
		return dom;
	}

	public void setDom(String dom) {
		this.dom = dom;
	}

	public String getNormal() {
		return normal;
	}

	public void setNormal(String normal) {
		this.normal = normal;
	}

	public String getSame() {
		return same;
	}

	public void setSame(String same) {
		this.same = same;
	}

	public String getTkt() {
		return tkt;
	}

	public void setTkt(String tkt) {
		this.tkt = tkt;
	}

	public String getAll_segs() {
		return all_segs;
	}

	public void setAll_segs(String all_segs) {
		this.all_segs = all_segs;
	}

	public String getSp_filler_1() {
		return sp_filler_1;
	}

	public void setSp_filler_1(String sp_filler_1) {
		this.sp_filler_1 = sp_filler_1;
	}

	public String getTbl_no() {
		return tbl_no;
	}

	public void setTbl_no(String tbl_no) {
		this.tbl_no = tbl_no;
	}

	public String getIntl() {
		return intl;
	}

	public void setIntl(String intl) {
		this.intl = intl;
	}

	public String getGeo_type() {
		return geo_type;
	}

	public void setGeo_type(String geo_type) {
		this.geo_type = geo_type;
	}

	public String getUnavail() {
		return unavail;
	}

	public void setUnavail(String unavail) {
		this.unavail = unavail;
	}

	public String getFare_brk_stop() {
		return fare_brk_stop;
	}

	public void setFare_brk_stop(String fare_brk_stop) {
		this.fare_brk_stop = fare_brk_stop;
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

	public String getSp_spec() {
		return sp_spec;
	}

	public void setSp_spec(String sp_spec) {
		this.sp_spec = sp_spec;
	}

	public String getGeo_appl() {
		return geo_appl;
	}

	public void setGeo_appl(String geo_appl) {
		this.geo_appl = geo_appl;
	}

	public String getAba_comb() {
		return aba_comb;
	}

	public void setAba_comb(String aba_comb) {
		this.aba_comb = aba_comb;
	}

	public String getUs_can() {
		return us_can;
	}

	public void setUs_can(String us_can) {
		this.us_can = us_can;
	}

	public String getDate_tbl_no_994() {
		return date_tbl_no_994;
	}

	public void setDate_tbl_no_994(String date_tbl_no_994) {
		this.date_tbl_no_994 = date_tbl_no_994;
	}

	public String getGeo_loc_1() {
		return geo_loc_1;
	}

	public void setGeo_loc_1(String geo_loc_1) {
		this.geo_loc_1 = geo_loc_1;
	}

	public String getRestr() {
		return restr;
	}

	public void setRestr(String restr) {
		this.restr = restr;
	}

	public String getGeo_loc_2() {
		return geo_loc_2;
	}

	public void setGeo_loc_2(String geo_loc_2) {
		this.geo_loc_2 = geo_loc_2;
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

	public String getConst_point() {
		return const_point;
	}

	public void setConst_point(String const_point) {
		this.const_point = const_point;
	}

	public String getCat_no() {
		return cat_no;
	}

	public void setCat_no(String cat_no) {
		this.cat_no = cat_no;
	}

	public String getRec_type() {
		return rec_type;
	}

	public void setRec_type(String rec_type) {
		this.rec_type = rec_type;
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
		result = prime * result + ((aba_comb == null) ? 0 : aba_comb.hashCode());
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((all_segs == null) ? 0 : all_segs.hashCode());
		result = prime * result + ((cat_no == null) ? 0 : cat_no.hashCode());
		result = prime * result + ((const_point == null) ? 0 : const_point.hashCode());
		result = prime * result + ((date_tbl_no_994 == null) ? 0 : date_tbl_no_994.hashCode());
		result = prime * result + ((dom == null) ? 0 : dom.hashCode());
		result = prime * result + ((fare_brk_stop == null) ? 0 : fare_brk_stop.hashCode());
		result = prime * result + ((filler_1 == null) ? 0 : filler_1.hashCode());
		result = prime * result + ((filler_2 == null) ? 0 : filler_2.hashCode());
		result = prime * result + ((geo_appl == null) ? 0 : geo_appl.hashCode());
		result = prime * result + ((geo_loc_1 == null) ? 0 : geo_loc_1.hashCode());
		result = prime * result + ((geo_loc_2 == null) ? 0 : geo_loc_2.hashCode());
		result = prime * result + ((geo_type == null) ? 0 : geo_type.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((intl == null) ? 0 : intl.hashCode());
		result = prime * result + ((normal == null) ? 0 : normal.hashCode());
		result = prime * result + ((rec_type == null) ? 0 : rec_type.hashCode());
		result = prime * result + ((record_batch == null) ? 0 : record_batch.hashCode());
		result = prime * result + ((record_sequence == null) ? 0 : record_sequence.hashCode());
		result = prime * result + ((restr == null) ? 0 : restr.hashCode());
		result = prime * result + ((rules_type == null) ? 0 : rules_type.hashCode());
		result = prime * result + ((same == null) ? 0 : same.hashCode());
		result = prime * result + ((sp_filler_1 == null) ? 0 : sp_filler_1.hashCode());
		result = prime * result + ((sp_spec == null) ? 0 : sp_spec.hashCode());
		result = prime * result + ((tbl_no == null) ? 0 : tbl_no.hashCode());
		result = prime * result + ((text_tbl_no_996 == null) ? 0 : text_tbl_no_996.hashCode());
		result = prime * result + ((tkt == null) ? 0 : tkt.hashCode());
		result = prime * result + ((unavail == null) ? 0 : unavail.hashCode());
		result = prime * result + ((us_can == null) ? 0 : us_can.hashCode());
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
		AtpcoRecord3Cat104 other = (AtpcoRecord3Cat104) obj;
		if (aba_comb == null) {
			if (other.aba_comb != null)
				return false;
		} else if (!aba_comb.equals(other.aba_comb))
			return false;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (all_segs == null) {
			if (other.all_segs != null)
				return false;
		} else if (!all_segs.equals(other.all_segs))
			return false;
		if (cat_no == null) {
			if (other.cat_no != null)
				return false;
		} else if (!cat_no.equals(other.cat_no))
			return false;
		if (const_point == null) {
			if (other.const_point != null)
				return false;
		} else if (!const_point.equals(other.const_point))
			return false;
		if (date_tbl_no_994 == null) {
			if (other.date_tbl_no_994 != null)
				return false;
		} else if (!date_tbl_no_994.equals(other.date_tbl_no_994))
			return false;
		if (dom == null) {
			if (other.dom != null)
				return false;
		} else if (!dom.equals(other.dom))
			return false;
		if (fare_brk_stop == null) {
			if (other.fare_brk_stop != null)
				return false;
		} else if (!fare_brk_stop.equals(other.fare_brk_stop))
			return false;
		if (filler_1 == null) {
			if (other.filler_1 != null)
				return false;
		} else if (!filler_1.equals(other.filler_1))
			return false;
		if (filler_2 == null) {
			if (other.filler_2 != null)
				return false;
		} else if (!filler_2.equals(other.filler_2))
			return false;
		if (geo_appl == null) {
			if (other.geo_appl != null)
				return false;
		} else if (!geo_appl.equals(other.geo_appl))
			return false;
		if (geo_loc_1 == null) {
			if (other.geo_loc_1 != null)
				return false;
		} else if (!geo_loc_1.equals(other.geo_loc_1))
			return false;
		if (geo_loc_2 == null) {
			if (other.geo_loc_2 != null)
				return false;
		} else if (!geo_loc_2.equals(other.geo_loc_2))
			return false;
		if (geo_type == null) {
			if (other.geo_type != null)
				return false;
		} else if (!geo_type.equals(other.geo_type))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (intl == null) {
			if (other.intl != null)
				return false;
		} else if (!intl.equals(other.intl))
			return false;
		if (normal == null) {
			if (other.normal != null)
				return false;
		} else if (!normal.equals(other.normal))
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
		if (restr == null) {
			if (other.restr != null)
				return false;
		} else if (!restr.equals(other.restr))
			return false;
		if (rules_type == null) {
			if (other.rules_type != null)
				return false;
		} else if (!rules_type.equals(other.rules_type))
			return false;
		if (same == null) {
			if (other.same != null)
				return false;
		} else if (!same.equals(other.same))
			return false;
		if (sp_filler_1 == null) {
			if (other.sp_filler_1 != null)
				return false;
		} else if (!sp_filler_1.equals(other.sp_filler_1))
			return false;
		if (sp_spec == null) {
			if (other.sp_spec != null)
				return false;
		} else if (!sp_spec.equals(other.sp_spec))
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
		if (tkt == null) {
			if (other.tkt != null)
				return false;
		} else if (!tkt.equals(other.tkt))
			return false;
		if (unavail == null) {
			if (other.unavail != null)
				return false;
		} else if (!unavail.equals(other.unavail))
			return false;
		if (us_can == null) {
			if (other.us_can != null)
				return false;
		} else if (!us_can.equals(other.us_can))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoRecord3Cat104 [id=" + id + ", record_sequence=" + record_sequence + ", dom=" + dom + ", normal="
				+ normal + ", same=" + same + ", tkt=" + tkt + ", all_segs=" + all_segs + ", sp_filler_1=" + sp_filler_1
				+ ", tbl_no=" + tbl_no + ", intl=" + intl + ", geo_type=" + geo_type + ", unavail=" + unavail
				+ ", fare_brk_stop=" + fare_brk_stop + ", action=" + action + ", rules_type=" + rules_type
				+ ", sp_spec=" + sp_spec + ", geo_appl=" + geo_appl + ", aba_comb=" + aba_comb + ", us_can=" + us_can
				+ ", date_tbl_no_994=" + date_tbl_no_994 + ", geo_loc_1=" + geo_loc_1 + ", restr=" + restr
				+ ", geo_loc_2=" + geo_loc_2 + ", text_tbl_no_996=" + text_tbl_no_996 + ", record_batch=" + record_batch
				+ ", const_point=" + const_point + ", cat_no=" + cat_no + ", rec_type=" + rec_type + ", filler_2="
				+ filler_2 + ", filler_1=" + filler_1 + "]";
	}
	
	
}
