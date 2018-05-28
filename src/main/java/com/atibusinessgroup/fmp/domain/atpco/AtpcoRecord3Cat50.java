package com.atibusinessgroup.fmp.domain.atpco;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_record_3_cat_050")
public class AtpcoRecord3Cat50 {

	@Id
    private String id;
	
	@Field("addl_filler_1")
	private String addl_filler_1;

	@Field("record_sequence")
    private String record_sequence;

	@Field("cap_text_table_no_996")
    private String cap_text_table_no_996;

	@Field("addl_iit")
    private String addl_iit;

	@Field("service_filler_1")
    private String service_filler_1;

	@Field("addl_group")
    private String addl_group;

	@Field("service_off_pk_econ")
    private String service_off_pk_econ;

	@Field("journeys_ct")
    private String journeys_ct;

	@Field("service_first")
    private String service_first;

	@Field("tbl_no")
    private String tbl_no;

	@Field("oth_text_table_no_996")
    private String oth_text_table_no_996;

	@Field("service_econ")
    private String service_econ;

	@Field("action")
    private String action;

	@Field("rules_type")
    private String rules_type;

	@Field("journeys_osoj")
    private String journeys_osoj;

	@Field("journeys_rtw")
    private String journeys_rtw;

	@Field("journeys_rt")
    private String journeys_rt;

	@Field("cap_dom_intl")
    private String cap_dom_intl;

	@Field("text_tbl_no_996")
    private String text_tbl_no_996;

	@Field("service_prem_econ")
    private String service_prem_econ;

	@Field("journeys_doj")
    private String journeys_doj;

	@Field("record_batch")
    private String record_batch;

	@Field("ow")
    private String ow;

	@Field("journeys_oj")
    private String journeys_oj;

	@Field("na_text_table_no_996")
    private String na_text_table_no_996;

	@Field("service_bus")
    private String service_bus;

	@Field("service_res")
    private String service_res;

	@Field("cat_no")
    private String cat_no;

	@Field("journeys_soj")
    private String journeys_soj;

	@Field("journeys_tsoj")
    private String journeys_tsoj;

	@Field("rec_type")
    private String rec_type;

	@Field("reserved_1")
    private String reserved_1;

	@Field("geo_location")
    private List<AtpcoRecord3Cat50GeoLocation> geo_location = new ArrayList<>();

    @Field("rt")
    private String rt;

    @Field("filler_3")
    private String filler_3;

    @Field("filler_2")
    private String filler_2;

    @Field("journeys_ow")
    private String journeys_ow;

    @Field("filler_1")
    private String filler_1;

    @Field("application_title")
    private String application_title;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddl_filler_1() {
		return addl_filler_1;
	}

	public void setAddl_filler_1(String addl_filler_1) {
		this.addl_filler_1 = addl_filler_1;
	}

	public String getRecord_sequence() {
		return record_sequence;
	}

	public void setRecord_sequence(String record_sequence) {
		this.record_sequence = record_sequence;
	}

	public String getCap_text_table_no_996() {
		return cap_text_table_no_996;
	}

	public void setCap_text_table_no_996(String cap_text_table_no_996) {
		this.cap_text_table_no_996 = cap_text_table_no_996;
	}

	public String getAddl_iit() {
		return addl_iit;
	}

	public void setAddl_iit(String addl_iit) {
		this.addl_iit = addl_iit;
	}

	public String getService_filler_1() {
		return service_filler_1;
	}

	public void setService_filler_1(String service_filler_1) {
		this.service_filler_1 = service_filler_1;
	}

	public String getAddl_group() {
		return addl_group;
	}

	public void setAddl_group(String addl_group) {
		this.addl_group = addl_group;
	}

	public String getService_off_pk_econ() {
		return service_off_pk_econ;
	}

	public void setService_off_pk_econ(String service_off_pk_econ) {
		this.service_off_pk_econ = service_off_pk_econ;
	}

	public String getJourneys_ct() {
		return journeys_ct;
	}

	public void setJourneys_ct(String journeys_ct) {
		this.journeys_ct = journeys_ct;
	}

	public String getService_first() {
		return service_first;
	}

	public void setService_first(String service_first) {
		this.service_first = service_first;
	}

	public String getTbl_no() {
		return tbl_no;
	}

	public void setTbl_no(String tbl_no) {
		this.tbl_no = tbl_no;
	}

	public String getOth_text_table_no_996() {
		return oth_text_table_no_996;
	}

	public void setOth_text_table_no_996(String oth_text_table_no_996) {
		this.oth_text_table_no_996 = oth_text_table_no_996;
	}

	public String getService_econ() {
		return service_econ;
	}

	public void setService_econ(String service_econ) {
		this.service_econ = service_econ;
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

	public String getJourneys_osoj() {
		return journeys_osoj;
	}

	public void setJourneys_osoj(String journeys_osoj) {
		this.journeys_osoj = journeys_osoj;
	}

	public String getJourneys_rtw() {
		return journeys_rtw;
	}

	public void setJourneys_rtw(String journeys_rtw) {
		this.journeys_rtw = journeys_rtw;
	}

	public String getJourneys_rt() {
		return journeys_rt;
	}

	public void setJourneys_rt(String journeys_rt) {
		this.journeys_rt = journeys_rt;
	}

	public String getCap_dom_intl() {
		return cap_dom_intl;
	}

	public void setCap_dom_intl(String cap_dom_intl) {
		this.cap_dom_intl = cap_dom_intl;
	}

	public String getText_tbl_no_996() {
		return text_tbl_no_996;
	}

	public void setText_tbl_no_996(String text_tbl_no_996) {
		this.text_tbl_no_996 = text_tbl_no_996;
	}

	public String getService_prem_econ() {
		return service_prem_econ;
	}

	public void setService_prem_econ(String service_prem_econ) {
		this.service_prem_econ = service_prem_econ;
	}

	public String getJourneys_doj() {
		return journeys_doj;
	}

	public void setJourneys_doj(String journeys_doj) {
		this.journeys_doj = journeys_doj;
	}

	public String getRecord_batch() {
		return record_batch;
	}

	public void setRecord_batch(String record_batch) {
		this.record_batch = record_batch;
	}

	public String getOw() {
		return ow;
	}

	public void setOw(String ow) {
		this.ow = ow;
	}

	public String getJourneys_oj() {
		return journeys_oj;
	}

	public void setJourneys_oj(String journeys_oj) {
		this.journeys_oj = journeys_oj;
	}

	public String getNa_text_table_no_996() {
		return na_text_table_no_996;
	}

	public void setNa_text_table_no_996(String na_text_table_no_996) {
		this.na_text_table_no_996 = na_text_table_no_996;
	}

	public String getService_bus() {
		return service_bus;
	}

	public void setService_bus(String service_bus) {
		this.service_bus = service_bus;
	}

	public String getService_res() {
		return service_res;
	}

	public void setService_res(String service_res) {
		this.service_res = service_res;
	}

	public String getCat_no() {
		return cat_no;
	}

	public void setCat_no(String cat_no) {
		this.cat_no = cat_no;
	}

	public String getJourneys_soj() {
		return journeys_soj;
	}

	public void setJourneys_soj(String journeys_soj) {
		this.journeys_soj = journeys_soj;
	}

	public String getJourneys_tsoj() {
		return journeys_tsoj;
	}

	public void setJourneys_tsoj(String journeys_tsoj) {
		this.journeys_tsoj = journeys_tsoj;
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

	public List<AtpcoRecord3Cat50GeoLocation> getGeo_location() {
		return geo_location;
	}

	public void setGeo_location(List<AtpcoRecord3Cat50GeoLocation> geo_location) {
		this.geo_location = geo_location;
	}

	public String getRt() {
		return rt;
	}

	public void setRt(String rt) {
		this.rt = rt;
	}

	public String getFiller_3() {
		return filler_3;
	}

	public void setFiller_3(String filler_3) {
		this.filler_3 = filler_3;
	}

	public String getFiller_2() {
		return filler_2;
	}

	public void setFiller_2(String filler_2) {
		this.filler_2 = filler_2;
	}

	public String getJourneys_ow() {
		return journeys_ow;
	}

	public void setJourneys_ow(String journeys_ow) {
		this.journeys_ow = journeys_ow;
	}

	public String getFiller_1() {
		return filler_1;
	}

	public void setFiller_1(String filler_1) {
		this.filler_1 = filler_1;
	}

	public String getApplication_title() {
		return application_title;
	}

	public void setApplication_title(String application_title) {
		this.application_title = application_title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((addl_filler_1 == null) ? 0 : addl_filler_1.hashCode());
		result = prime * result + ((addl_group == null) ? 0 : addl_group.hashCode());
		result = prime * result + ((addl_iit == null) ? 0 : addl_iit.hashCode());
		result = prime * result + ((application_title == null) ? 0 : application_title.hashCode());
		result = prime * result + ((cap_dom_intl == null) ? 0 : cap_dom_intl.hashCode());
		result = prime * result + ((cap_text_table_no_996 == null) ? 0 : cap_text_table_no_996.hashCode());
		result = prime * result + ((cat_no == null) ? 0 : cat_no.hashCode());
		result = prime * result + ((filler_1 == null) ? 0 : filler_1.hashCode());
		result = prime * result + ((filler_2 == null) ? 0 : filler_2.hashCode());
		result = prime * result + ((filler_3 == null) ? 0 : filler_3.hashCode());
		result = prime * result + ((geo_location == null) ? 0 : geo_location.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((journeys_ct == null) ? 0 : journeys_ct.hashCode());
		result = prime * result + ((journeys_doj == null) ? 0 : journeys_doj.hashCode());
		result = prime * result + ((journeys_oj == null) ? 0 : journeys_oj.hashCode());
		result = prime * result + ((journeys_osoj == null) ? 0 : journeys_osoj.hashCode());
		result = prime * result + ((journeys_ow == null) ? 0 : journeys_ow.hashCode());
		result = prime * result + ((journeys_rt == null) ? 0 : journeys_rt.hashCode());
		result = prime * result + ((journeys_rtw == null) ? 0 : journeys_rtw.hashCode());
		result = prime * result + ((journeys_soj == null) ? 0 : journeys_soj.hashCode());
		result = prime * result + ((journeys_tsoj == null) ? 0 : journeys_tsoj.hashCode());
		result = prime * result + ((na_text_table_no_996 == null) ? 0 : na_text_table_no_996.hashCode());
		result = prime * result + ((oth_text_table_no_996 == null) ? 0 : oth_text_table_no_996.hashCode());
		result = prime * result + ((ow == null) ? 0 : ow.hashCode());
		result = prime * result + ((rec_type == null) ? 0 : rec_type.hashCode());
		result = prime * result + ((record_batch == null) ? 0 : record_batch.hashCode());
		result = prime * result + ((record_sequence == null) ? 0 : record_sequence.hashCode());
		result = prime * result + ((reserved_1 == null) ? 0 : reserved_1.hashCode());
		result = prime * result + ((rt == null) ? 0 : rt.hashCode());
		result = prime * result + ((rules_type == null) ? 0 : rules_type.hashCode());
		result = prime * result + ((service_bus == null) ? 0 : service_bus.hashCode());
		result = prime * result + ((service_econ == null) ? 0 : service_econ.hashCode());
		result = prime * result + ((service_filler_1 == null) ? 0 : service_filler_1.hashCode());
		result = prime * result + ((service_first == null) ? 0 : service_first.hashCode());
		result = prime * result + ((service_off_pk_econ == null) ? 0 : service_off_pk_econ.hashCode());
		result = prime * result + ((service_prem_econ == null) ? 0 : service_prem_econ.hashCode());
		result = prime * result + ((service_res == null) ? 0 : service_res.hashCode());
		result = prime * result + ((tbl_no == null) ? 0 : tbl_no.hashCode());
		result = prime * result + ((text_tbl_no_996 == null) ? 0 : text_tbl_no_996.hashCode());
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
		AtpcoRecord3Cat50 other = (AtpcoRecord3Cat50) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (addl_filler_1 == null) {
			if (other.addl_filler_1 != null)
				return false;
		} else if (!addl_filler_1.equals(other.addl_filler_1))
			return false;
		if (addl_group == null) {
			if (other.addl_group != null)
				return false;
		} else if (!addl_group.equals(other.addl_group))
			return false;
		if (addl_iit == null) {
			if (other.addl_iit != null)
				return false;
		} else if (!addl_iit.equals(other.addl_iit))
			return false;
		if (application_title == null) {
			if (other.application_title != null)
				return false;
		} else if (!application_title.equals(other.application_title))
			return false;
		if (cap_dom_intl == null) {
			if (other.cap_dom_intl != null)
				return false;
		} else if (!cap_dom_intl.equals(other.cap_dom_intl))
			return false;
		if (cap_text_table_no_996 == null) {
			if (other.cap_text_table_no_996 != null)
				return false;
		} else if (!cap_text_table_no_996.equals(other.cap_text_table_no_996))
			return false;
		if (cat_no == null) {
			if (other.cat_no != null)
				return false;
		} else if (!cat_no.equals(other.cat_no))
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
		if (filler_3 == null) {
			if (other.filler_3 != null)
				return false;
		} else if (!filler_3.equals(other.filler_3))
			return false;
		if (geo_location == null) {
			if (other.geo_location != null)
				return false;
		} else if (!geo_location.equals(other.geo_location))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (journeys_ct == null) {
			if (other.journeys_ct != null)
				return false;
		} else if (!journeys_ct.equals(other.journeys_ct))
			return false;
		if (journeys_doj == null) {
			if (other.journeys_doj != null)
				return false;
		} else if (!journeys_doj.equals(other.journeys_doj))
			return false;
		if (journeys_oj == null) {
			if (other.journeys_oj != null)
				return false;
		} else if (!journeys_oj.equals(other.journeys_oj))
			return false;
		if (journeys_osoj == null) {
			if (other.journeys_osoj != null)
				return false;
		} else if (!journeys_osoj.equals(other.journeys_osoj))
			return false;
		if (journeys_ow == null) {
			if (other.journeys_ow != null)
				return false;
		} else if (!journeys_ow.equals(other.journeys_ow))
			return false;
		if (journeys_rt == null) {
			if (other.journeys_rt != null)
				return false;
		} else if (!journeys_rt.equals(other.journeys_rt))
			return false;
		if (journeys_rtw == null) {
			if (other.journeys_rtw != null)
				return false;
		} else if (!journeys_rtw.equals(other.journeys_rtw))
			return false;
		if (journeys_soj == null) {
			if (other.journeys_soj != null)
				return false;
		} else if (!journeys_soj.equals(other.journeys_soj))
			return false;
		if (journeys_tsoj == null) {
			if (other.journeys_tsoj != null)
				return false;
		} else if (!journeys_tsoj.equals(other.journeys_tsoj))
			return false;
		if (na_text_table_no_996 == null) {
			if (other.na_text_table_no_996 != null)
				return false;
		} else if (!na_text_table_no_996.equals(other.na_text_table_no_996))
			return false;
		if (oth_text_table_no_996 == null) {
			if (other.oth_text_table_no_996 != null)
				return false;
		} else if (!oth_text_table_no_996.equals(other.oth_text_table_no_996))
			return false;
		if (ow == null) {
			if (other.ow != null)
				return false;
		} else if (!ow.equals(other.ow))
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
		if (rt == null) {
			if (other.rt != null)
				return false;
		} else if (!rt.equals(other.rt))
			return false;
		if (rules_type == null) {
			if (other.rules_type != null)
				return false;
		} else if (!rules_type.equals(other.rules_type))
			return false;
		if (service_bus == null) {
			if (other.service_bus != null)
				return false;
		} else if (!service_bus.equals(other.service_bus))
			return false;
		if (service_econ == null) {
			if (other.service_econ != null)
				return false;
		} else if (!service_econ.equals(other.service_econ))
			return false;
		if (service_filler_1 == null) {
			if (other.service_filler_1 != null)
				return false;
		} else if (!service_filler_1.equals(other.service_filler_1))
			return false;
		if (service_first == null) {
			if (other.service_first != null)
				return false;
		} else if (!service_first.equals(other.service_first))
			return false;
		if (service_off_pk_econ == null) {
			if (other.service_off_pk_econ != null)
				return false;
		} else if (!service_off_pk_econ.equals(other.service_off_pk_econ))
			return false;
		if (service_prem_econ == null) {
			if (other.service_prem_econ != null)
				return false;
		} else if (!service_prem_econ.equals(other.service_prem_econ))
			return false;
		if (service_res == null) {
			if (other.service_res != null)
				return false;
		} else if (!service_res.equals(other.service_res))
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
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoRecord3Cat50 [id=" + id + ", addl_filler_1=" + addl_filler_1 + ", record_sequence="
				+ record_sequence + ", cap_text_table_no_996=" + cap_text_table_no_996 + ", addl_iit=" + addl_iit
				+ ", service_filler_1=" + service_filler_1 + ", addl_group=" + addl_group + ", service_off_pk_econ="
				+ service_off_pk_econ + ", journeys_ct=" + journeys_ct + ", service_first=" + service_first
				+ ", tbl_no=" + tbl_no + ", oth_text_table_no_996=" + oth_text_table_no_996 + ", service_econ="
				+ service_econ + ", action=" + action + ", rules_type=" + rules_type + ", journeys_osoj="
				+ journeys_osoj + ", journeys_rtw=" + journeys_rtw + ", journeys_rt=" + journeys_rt + ", cap_dom_intl="
				+ cap_dom_intl + ", text_tbl_no_996=" + text_tbl_no_996 + ", service_prem_econ=" + service_prem_econ
				+ ", journeys_doj=" + journeys_doj + ", record_batch=" + record_batch + ", ow=" + ow + ", journeys_oj="
				+ journeys_oj + ", na_text_table_no_996=" + na_text_table_no_996 + ", service_bus=" + service_bus
				+ ", service_res=" + service_res + ", cat_no=" + cat_no + ", journeys_soj=" + journeys_soj
				+ ", journeys_tsoj=" + journeys_tsoj + ", rec_type=" + rec_type + ", reserved_1=" + reserved_1
				+ ", geo_location=" + geo_location + ", rt=" + rt + ", filler_3=" + filler_3 + ", filler_2=" + filler_2
				+ ", journeys_ow=" + journeys_ow + ", filler_1=" + filler_1 + ", application_title=" + application_title
				+ "]";
	}
    
    
}
