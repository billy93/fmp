package com.atibusinessgroup.fmp.domain.atpco;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class AtpcoRecord2Cat25 {

	@Id
	private String _id;
	
	@Field("fare_appl")
	private String fare_appl;

	@Field("rule_tar_no")
    private String rule_tar_no;

	@Field("record_id")
    private String record_id;

	@Field("cxr_code")
    private String cxr_code;

	@Field("mcn")
    private String mcn;

	@Field("dates_disc")
    private String dates_disc;

	@Field("seq_no")
    private String seq_no;

	@Field("rule_no")
    private String rule_no;

	@Field("data_segs")
    private List<AtpcoRecord2Cat25DataSeg> data_segs = new ArrayList<>();

	@Field("action")
    private String action;

	@Field("batch_ci")
    private String batch_ci;

	@Field("no_appl")
    private String no_appl;

	@Field("geo_type_1")
    private String geo_type_1;

	@Field("geo_type_2")
    private String geo_type_2;

	@Field("batch_no")
    private int batch_no;

	@Field("record_id_map")
    private String record_id_map;

	@Field("batch_number")
    private String batch_number;

	@Field("dates_eff")
    private Object dates_eff;

	@Field("batch_date")
    private Date batch_date;

	@Field("geo_zone_tbl_978_2")
    private String geo_zone_tbl_978_2;

	@Field("geo_zone_tbl_978_1")
    private String geo_zone_tbl_978_1;

	@Field("geo_value_2")
    private String geo_value_2;

	@Field("cat_no")
    private String cat_no;

	@Field("geo_value_1")
    private String geo_value_1;

	@Field("rec_type")
    private String rec_type;

	@Field("reserved_2")
    private String reserved_2;

	@Field("reserved_1")
    private String reserved_1;

	@Field("jt_cxr_tbl_no_997")
    private String jt_cxr_tbl_no_997;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getFare_appl() {
		return fare_appl;
	}

	public void setFare_appl(String fare_appl) {
		this.fare_appl = fare_appl;
	}

	public String getRule_tar_no() {
		return rule_tar_no;
	}

	public void setRule_tar_no(String rule_tar_no) {
		this.rule_tar_no = rule_tar_no;
	}

	public String getRecord_id() {
		return record_id;
	}

	public void setRecord_id(String record_id) {
		this.record_id = record_id;
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

	public String getDates_disc() {
		return dates_disc;
	}

	public void setDates_disc(String dates_disc) {
		this.dates_disc = dates_disc;
	}

	public String getSeq_no() {
		return seq_no;
	}

	public void setSeq_no(String seq_no) {
		this.seq_no = seq_no;
	}

	public String getRule_no() {
		return rule_no;
	}

	public void setRule_no(String rule_no) {
		this.rule_no = rule_no;
	}

	public List<AtpcoRecord2Cat25DataSeg> getData_segs() {
		return data_segs;
	}

	public void setData_segs(List<AtpcoRecord2Cat25DataSeg> data_segs) {
		this.data_segs = data_segs;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getBatch_ci() {
		return batch_ci;
	}

	public void setBatch_ci(String batch_ci) {
		this.batch_ci = batch_ci;
	}

	public String getNo_appl() {
		return no_appl;
	}

	public void setNo_appl(String no_appl) {
		this.no_appl = no_appl;
	}

	public String getGeo_type_1() {
		return geo_type_1;
	}

	public void setGeo_type_1(String geo_type_1) {
		this.geo_type_1 = geo_type_1;
	}

	public String getGeo_type_2() {
		return geo_type_2;
	}

	public void setGeo_type_2(String geo_type_2) {
		this.geo_type_2 = geo_type_2;
	}

	public int getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(int batch_no) {
		this.batch_no = batch_no;
	}

	public String getRecord_id_map() {
		return record_id_map;
	}

	public void setRecord_id_map(String record_id_map) {
		this.record_id_map = record_id_map;
	}

	public String getBatch_number() {
		return batch_number;
	}

	public void setBatch_number(String batch_number) {
		this.batch_number = batch_number;
	}

	public Object getDates_eff() {
		return dates_eff;
	}

	public void setDates_eff(Object dates_eff) {
		this.dates_eff = dates_eff;
	}

	public Date getBatch_date() {
		return batch_date;
	}

	public void setBatch_date(Date batch_date) {
		this.batch_date = batch_date;
	}

	public String getGeo_zone_tbl_978_2() {
		return geo_zone_tbl_978_2;
	}

	public void setGeo_zone_tbl_978_2(String geo_zone_tbl_978_2) {
		this.geo_zone_tbl_978_2 = geo_zone_tbl_978_2;
	}

	public String getGeo_zone_tbl_978_1() {
		return geo_zone_tbl_978_1;
	}

	public void setGeo_zone_tbl_978_1(String geo_zone_tbl_978_1) {
		this.geo_zone_tbl_978_1 = geo_zone_tbl_978_1;
	}

	public String getGeo_value_2() {
		return geo_value_2;
	}

	public void setGeo_value_2(String geo_value_2) {
		this.geo_value_2 = geo_value_2;
	}

	public String getCat_no() {
		return cat_no;
	}

	public void setCat_no(String cat_no) {
		this.cat_no = cat_no;
	}

	public String getGeo_value_1() {
		return geo_value_1;
	}

	public void setGeo_value_1(String geo_value_1) {
		this.geo_value_1 = geo_value_1;
	}

	public String getRec_type() {
		return rec_type;
	}

	public void setRec_type(String rec_type) {
		this.rec_type = rec_type;
	}

	public String getReserved_2() {
		return reserved_2;
	}

	public void setReserved_2(String reserved_2) {
		this.reserved_2 = reserved_2;
	}

	public String getReserved_1() {
		return reserved_1;
	}

	public void setReserved_1(String reserved_1) {
		this.reserved_1 = reserved_1;
	}

	public String getJt_cxr_tbl_no_997() {
		return jt_cxr_tbl_no_997;
	}

	public void setJt_cxr_tbl_no_997(String jt_cxr_tbl_no_997) {
		this.jt_cxr_tbl_no_997 = jt_cxr_tbl_no_997;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((batch_ci == null) ? 0 : batch_ci.hashCode());
		result = prime * result + ((batch_date == null) ? 0 : batch_date.hashCode());
		result = prime * result + batch_no;
		result = prime * result + ((batch_number == null) ? 0 : batch_number.hashCode());
		result = prime * result + ((cat_no == null) ? 0 : cat_no.hashCode());
		result = prime * result + ((cxr_code == null) ? 0 : cxr_code.hashCode());
		result = prime * result + ((data_segs == null) ? 0 : data_segs.hashCode());
		result = prime * result + ((dates_disc == null) ? 0 : dates_disc.hashCode());
		result = prime * result + ((dates_eff == null) ? 0 : dates_eff.hashCode());
		result = prime * result + ((fare_appl == null) ? 0 : fare_appl.hashCode());
		result = prime * result + ((geo_type_1 == null) ? 0 : geo_type_1.hashCode());
		result = prime * result + ((geo_type_2 == null) ? 0 : geo_type_2.hashCode());
		result = prime * result + ((geo_value_1 == null) ? 0 : geo_value_1.hashCode());
		result = prime * result + ((geo_value_2 == null) ? 0 : geo_value_2.hashCode());
		result = prime * result + ((geo_zone_tbl_978_1 == null) ? 0 : geo_zone_tbl_978_1.hashCode());
		result = prime * result + ((geo_zone_tbl_978_2 == null) ? 0 : geo_zone_tbl_978_2.hashCode());
		result = prime * result + ((jt_cxr_tbl_no_997 == null) ? 0 : jt_cxr_tbl_no_997.hashCode());
		result = prime * result + ((mcn == null) ? 0 : mcn.hashCode());
		result = prime * result + ((no_appl == null) ? 0 : no_appl.hashCode());
		result = prime * result + ((rec_type == null) ? 0 : rec_type.hashCode());
		result = prime * result + ((record_id == null) ? 0 : record_id.hashCode());
		result = prime * result + ((record_id_map == null) ? 0 : record_id_map.hashCode());
		result = prime * result + ((reserved_1 == null) ? 0 : reserved_1.hashCode());
		result = prime * result + ((reserved_2 == null) ? 0 : reserved_2.hashCode());
		result = prime * result + ((rule_no == null) ? 0 : rule_no.hashCode());
		result = prime * result + ((rule_tar_no == null) ? 0 : rule_tar_no.hashCode());
		result = prime * result + ((seq_no == null) ? 0 : seq_no.hashCode());
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
		AtpcoRecord2Cat25 other = (AtpcoRecord2Cat25) obj;
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
		if (batch_ci == null) {
			if (other.batch_ci != null) {
				return false;
			}
		} else if (!batch_ci.equals(other.batch_ci)) {
			return false;
		}
		if (batch_date == null) {
			if (other.batch_date != null) {
				return false;
			}
		} else if (!batch_date.equals(other.batch_date)) {
			return false;
		}
		if (batch_no != other.batch_no) {
			return false;
		}
		if (batch_number == null) {
			if (other.batch_number != null) {
				return false;
			}
		} else if (!batch_number.equals(other.batch_number)) {
			return false;
		}
		if (cat_no == null) {
			if (other.cat_no != null) {
				return false;
			}
		} else if (!cat_no.equals(other.cat_no)) {
			return false;
		}
		if (cxr_code == null) {
			if (other.cxr_code != null) {
				return false;
			}
		} else if (!cxr_code.equals(other.cxr_code)) {
			return false;
		}
		if (data_segs == null) {
			if (other.data_segs != null) {
				return false;
			}
		} else if (!data_segs.equals(other.data_segs)) {
			return false;
		}
		if (dates_disc == null) {
			if (other.dates_disc != null) {
				return false;
			}
		} else if (!dates_disc.equals(other.dates_disc)) {
			return false;
		}
		if (dates_eff == null) {
			if (other.dates_eff != null) {
				return false;
			}
		} else if (!dates_eff.equals(other.dates_eff)) {
			return false;
		}
		if (fare_appl == null) {
			if (other.fare_appl != null) {
				return false;
			}
		} else if (!fare_appl.equals(other.fare_appl)) {
			return false;
		}
		if (geo_type_1 == null) {
			if (other.geo_type_1 != null) {
				return false;
			}
		} else if (!geo_type_1.equals(other.geo_type_1)) {
			return false;
		}
		if (geo_type_2 == null) {
			if (other.geo_type_2 != null) {
				return false;
			}
		} else if (!geo_type_2.equals(other.geo_type_2)) {
			return false;
		}
		if (geo_value_1 == null) {
			if (other.geo_value_1 != null) {
				return false;
			}
		} else if (!geo_value_1.equals(other.geo_value_1)) {
			return false;
		}
		if (geo_value_2 == null) {
			if (other.geo_value_2 != null) {
				return false;
			}
		} else if (!geo_value_2.equals(other.geo_value_2)) {
			return false;
		}
		if (geo_zone_tbl_978_1 == null) {
			if (other.geo_zone_tbl_978_1 != null) {
				return false;
			}
		} else if (!geo_zone_tbl_978_1.equals(other.geo_zone_tbl_978_1)) {
			return false;
		}
		if (geo_zone_tbl_978_2 == null) {
			if (other.geo_zone_tbl_978_2 != null) {
				return false;
			}
		} else if (!geo_zone_tbl_978_2.equals(other.geo_zone_tbl_978_2)) {
			return false;
		}
		if (jt_cxr_tbl_no_997 == null) {
			if (other.jt_cxr_tbl_no_997 != null) {
				return false;
			}
		} else if (!jt_cxr_tbl_no_997.equals(other.jt_cxr_tbl_no_997)) {
			return false;
		}
		if (mcn == null) {
			if (other.mcn != null) {
				return false;
			}
		} else if (!mcn.equals(other.mcn)) {
			return false;
		}
		if (no_appl == null) {
			if (other.no_appl != null) {
				return false;
			}
		} else if (!no_appl.equals(other.no_appl)) {
			return false;
		}
		if (rec_type == null) {
			if (other.rec_type != null) {
				return false;
			}
		} else if (!rec_type.equals(other.rec_type)) {
			return false;
		}
		if (record_id == null) {
			if (other.record_id != null) {
				return false;
			}
		} else if (!record_id.equals(other.record_id)) {
			return false;
		}
		if (record_id_map == null) {
			if (other.record_id_map != null) {
				return false;
			}
		} else if (!record_id_map.equals(other.record_id_map)) {
			return false;
		}
		if (reserved_1 == null) {
			if (other.reserved_1 != null) {
				return false;
			}
		} else if (!reserved_1.equals(other.reserved_1)) {
			return false;
		}
		if (reserved_2 == null) {
			if (other.reserved_2 != null) {
				return false;
			}
		} else if (!reserved_2.equals(other.reserved_2)) {
			return false;
		}
		if (rule_no == null) {
			if (other.rule_no != null) {
				return false;
			}
		} else if (!rule_no.equals(other.rule_no)) {
			return false;
		}
		if (rule_tar_no == null) {
			if (other.rule_tar_no != null) {
				return false;
			}
		} else if (!rule_tar_no.equals(other.rule_tar_no)) {
			return false;
		}
		if (seq_no == null) {
			if (other.seq_no != null) {
				return false;
			}
		} else if (!seq_no.equals(other.seq_no)) {
			return false;
		}
		return true;
	}
}
