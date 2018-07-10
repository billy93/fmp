package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class SecurityDataTable {
	
	@Field("update")
	private String update;

	@Field("tbl_id")
    private String tbl_id;

	@Field("record_sequence")
    private int record_sequence;

	@Field("ticketing")
    private String ticketing;

	@Field("tvl_agency")
    private String tvl_agency;

	@Field("seq_no")
    private String seq_no;

    @Id
    private String _id;

    @Field("sell")
    private String sell;

    @Field("tbl_no")
    private String tbl_no;

    @Field("action")
    private String action;

    @Field("rules_type")
    private String rules_type;

    @Field("locales_code")
    private String locales_code;

    @Field("locales_type")
    private String locales_type;

    @Field("locales_geo_loc_2")
    private String locales_geo_loc_2;

    @Field("locales_geo_loc_1")
    private String locales_geo_loc_1;

    @Field("record_batch")
    private int record_batch;

    @Field("locales_geo_type_1")
    private String locales_geo_type_1;

    @Field("locales_geo_type_2")
    private String locales_geo_type_2;

    @Field("appl")
    private String appl;

    @Field("rec_type")
    private int rec_type;

    @Field("cxr_crs")
    private String cxr_crs;

    @Field("sec_seller_control_id")
    private String sec_seller_control_id;

    @Field("duty_func")
    private String duty_func;

    @Field("changes_only")
    private String changes_only;

    @Field("redistribute")
    private String redistribute;

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getTbl_id() {
		return tbl_id;
	}

	public void setTbl_id(String tbl_id) {
		this.tbl_id = tbl_id;
	}

	public int getRecord_sequence() {
		return record_sequence;
	}

	public void setRecord_sequence(int record_sequence) {
		this.record_sequence = record_sequence;
	}

	public String getTicketing() {
		return ticketing;
	}

	public void setTicketing(String ticketing) {
		this.ticketing = ticketing;
	}

	public String getTvl_agency() {
		return tvl_agency;
	}

	public void setTvl_agency(String tvl_agency) {
		this.tvl_agency = tvl_agency;
	}

	public String getSeq_no() {
		return seq_no;
	}

	public void setSeq_no(String seq_no) {
		this.seq_no = seq_no;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getSell() {
		return sell;
	}

	public void setSell(String sell) {
		this.sell = sell;
	}

	public String getTbl_no() {
		return tbl_no;
	}

	public void setTbl_no(String tbl_no) {
		this.tbl_no = tbl_no;
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

	public String getLocales_code() {
		return locales_code;
	}

	public void setLocales_code(String locales_code) {
		this.locales_code = locales_code;
	}

	public String getLocales_type() {
		return locales_type;
	}

	public void setLocales_type(String locales_type) {
		this.locales_type = locales_type;
	}

	public String getLocales_geo_loc_2() {
		return locales_geo_loc_2;
	}

	public void setLocales_geo_loc_2(String locales_geo_loc_2) {
		this.locales_geo_loc_2 = locales_geo_loc_2;
	}

	public String getLocales_geo_loc_1() {
		return locales_geo_loc_1;
	}

	public void setLocales_geo_loc_1(String locales_geo_loc_1) {
		this.locales_geo_loc_1 = locales_geo_loc_1;
	}

	public int getRecord_batch() {
		return record_batch;
	}

	public void setRecord_batch(int record_batch) {
		this.record_batch = record_batch;
	}

	public String getLocales_geo_type_1() {
		return locales_geo_type_1;
	}

	public void setLocales_geo_type_1(String locales_geo_type_1) {
		this.locales_geo_type_1 = locales_geo_type_1;
	}

	public String getLocales_geo_type_2() {
		return locales_geo_type_2;
	}

	public void setLocales_geo_type_2(String locales_geo_type_2) {
		this.locales_geo_type_2 = locales_geo_type_2;
	}

	public String getAppl() {
		return appl;
	}

	public void setAppl(String appl) {
		this.appl = appl;
	}

	public int getRec_type() {
		return rec_type;
	}

	public void setRec_type(int rec_type) {
		this.rec_type = rec_type;
	}

	public String getCxr_crs() {
		return cxr_crs;
	}

	public void setCxr_crs(String cxr_crs) {
		this.cxr_crs = cxr_crs;
	}

	public String getSec_seller_control_id() {
		return sec_seller_control_id;
	}

	public void setSec_seller_control_id(String sec_seller_control_id) {
		this.sec_seller_control_id = sec_seller_control_id;
	}

	public String getDuty_func() {
		return duty_func;
	}

	public void setDuty_func(String duty_func) {
		this.duty_func = duty_func;
	}

	public String getChanges_only() {
		return changes_only;
	}

	public void setChanges_only(String changes_only) {
		this.changes_only = changes_only;
	}

	public String getRedistribute() {
		return redistribute;
	}

	public void setRedistribute(String redistribute) {
		this.redistribute = redistribute;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((appl == null) ? 0 : appl.hashCode());
		result = prime * result + ((changes_only == null) ? 0 : changes_only.hashCode());
		result = prime * result + ((cxr_crs == null) ? 0 : cxr_crs.hashCode());
		result = prime * result + ((duty_func == null) ? 0 : duty_func.hashCode());
		result = prime * result + ((locales_code == null) ? 0 : locales_code.hashCode());
		result = prime * result + ((locales_geo_loc_1 == null) ? 0 : locales_geo_loc_1.hashCode());
		result = prime * result + ((locales_geo_loc_2 == null) ? 0 : locales_geo_loc_2.hashCode());
		result = prime * result + ((locales_geo_type_1 == null) ? 0 : locales_geo_type_1.hashCode());
		result = prime * result + ((locales_geo_type_2 == null) ? 0 : locales_geo_type_2.hashCode());
		result = prime * result + ((locales_type == null) ? 0 : locales_type.hashCode());
		result = prime * result + rec_type;
		result = prime * result + record_batch;
		result = prime * result + record_sequence;
		result = prime * result + ((redistribute == null) ? 0 : redistribute.hashCode());
		result = prime * result + ((rules_type == null) ? 0 : rules_type.hashCode());
		result = prime * result + ((sec_seller_control_id == null) ? 0 : sec_seller_control_id.hashCode());
		result = prime * result + ((sell == null) ? 0 : sell.hashCode());
		result = prime * result + ((seq_no == null) ? 0 : seq_no.hashCode());
		result = prime * result + ((tbl_id == null) ? 0 : tbl_id.hashCode());
		result = prime * result + ((tbl_no == null) ? 0 : tbl_no.hashCode());
		result = prime * result + ((ticketing == null) ? 0 : ticketing.hashCode());
		result = prime * result + ((tvl_agency == null) ? 0 : tvl_agency.hashCode());
		result = prime * result + ((update == null) ? 0 : update.hashCode());
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
		SecurityDataTable other = (SecurityDataTable) obj;
		if (_id == null) {
			if (other._id != null) {
				return false;
			}
		} else if (!_id.equals(other._id)) {
			return false;
		}
		if (action == null) {
			if (other.action != null) {
				return false;
			}
		} else if (!action.equals(other.action)) {
			return false;
		}
		if (appl == null) {
			if (other.appl != null) {
				return false;
			}
		} else if (!appl.equals(other.appl)) {
			return false;
		}
		if (changes_only == null) {
			if (other.changes_only != null) {
				return false;
			}
		} else if (!changes_only.equals(other.changes_only)) {
			return false;
		}
		if (cxr_crs == null) {
			if (other.cxr_crs != null) {
				return false;
			}
		} else if (!cxr_crs.equals(other.cxr_crs)) {
			return false;
		}
		if (duty_func == null) {
			if (other.duty_func != null) {
				return false;
			}
		} else if (!duty_func.equals(other.duty_func)) {
			return false;
		}
		if (locales_code == null) {
			if (other.locales_code != null) {
				return false;
			}
		} else if (!locales_code.equals(other.locales_code)) {
			return false;
		}
		if (locales_geo_loc_1 == null) {
			if (other.locales_geo_loc_1 != null) {
				return false;
			}
		} else if (!locales_geo_loc_1.equals(other.locales_geo_loc_1)) {
			return false;
		}
		if (locales_geo_loc_2 == null) {
			if (other.locales_geo_loc_2 != null) {
				return false;
			}
		} else if (!locales_geo_loc_2.equals(other.locales_geo_loc_2)) {
			return false;
		}
		if (locales_geo_type_1 == null) {
			if (other.locales_geo_type_1 != null) {
				return false;
			}
		} else if (!locales_geo_type_1.equals(other.locales_geo_type_1)) {
			return false;
		}
		if (locales_geo_type_2 == null) {
			if (other.locales_geo_type_2 != null) {
				return false;
			}
		} else if (!locales_geo_type_2.equals(other.locales_geo_type_2)) {
			return false;
		}
		if (locales_type == null) {
			if (other.locales_type != null) {
				return false;
			}
		} else if (!locales_type.equals(other.locales_type)) {
			return false;
		}
		if (rec_type != other.rec_type) {
			return false;
		}
		if (record_batch != other.record_batch) {
			return false;
		}
		if (record_sequence != other.record_sequence) {
			return false;
		}
		if (redistribute == null) {
			if (other.redistribute != null) {
				return false;
			}
		} else if (!redistribute.equals(other.redistribute)) {
			return false;
		}
		if (rules_type == null) {
			if (other.rules_type != null) {
				return false;
			}
		} else if (!rules_type.equals(other.rules_type)) {
			return false;
		}
		if (sec_seller_control_id == null) {
			if (other.sec_seller_control_id != null) {
				return false;
			}
		} else if (!sec_seller_control_id.equals(other.sec_seller_control_id)) {
			return false;
		}
		if (sell == null) {
			if (other.sell != null) {
				return false;
			}
		} else if (!sell.equals(other.sell)) {
			return false;
		}
		if (seq_no == null) {
			if (other.seq_no != null) {
				return false;
			}
		} else if (!seq_no.equals(other.seq_no)) {
			return false;
		}
		if (tbl_id == null) {
			if (other.tbl_id != null) {
				return false;
			}
		} else if (!tbl_id.equals(other.tbl_id)) {
			return false;
		}
		if (tbl_no == null) {
			if (other.tbl_no != null) {
				return false;
			}
		} else if (!tbl_no.equals(other.tbl_no)) {
			return false;
		}
		if (ticketing == null) {
			if (other.ticketing != null) {
				return false;
			}
		} else if (!ticketing.equals(other.ticketing)) {
			return false;
		}
		if (tvl_agency == null) {
			if (other.tvl_agency != null) {
				return false;
			}
		} else if (!tvl_agency.equals(other.tvl_agency)) {
			return false;
		}
		if (update == null) {
			if (other.update != null) {
				return false;
			}
		} else if (!update.equals(other.update)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "SecurityDataTable [update=" + update + ", tbl_id=" + tbl_id + ", record_sequence=" + record_sequence
				+ ", ticketing=" + ticketing + ", tvl_agency=" + tvl_agency + ", seq_no=" + seq_no + ", _id=" + _id
				+ ", sell=" + sell + ", tbl_no=" + tbl_no + ", action=" + action + ", rules_type=" + rules_type
				+ ", locales_code=" + locales_code + ", locales_type=" + locales_type + ", locales_geo_loc_2="
				+ locales_geo_loc_2 + ", locales_geo_loc_1=" + locales_geo_loc_1 + ", record_batch=" + record_batch
				+ ", locales_geo_type_1=" + locales_geo_type_1 + ", locales_geo_type_2=" + locales_geo_type_2
				+ ", appl=" + appl + ", rec_type=" + rec_type + ", cxr_crs=" + cxr_crs + ", sec_seller_control_id="
				+ sec_seller_control_id + ", duty_func=" + duty_func + ", changes_only=" + changes_only
				+ ", redistribute=" + redistribute + "]";
	}
}