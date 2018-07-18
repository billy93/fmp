package com.atibusinessgroup.fmp.domain.dto;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class UserZoneTable {
	
	@Id
    private String _id;
	
	@Field("geo_loc_type")
	private String geo_loc_type;

	@Field("batch_number")
    private int batch_number;

	@Field("tbl_id")
    private String tbl_id;

	@Field("rec_type")
    private String rec_type;

	@Field("tbl_no")
    private String tbl_no;

	@Field("action")
    private String action;

	@Field("batch_date")
    private Date batch_date;

	@Field("geo_loc_value")
    private String geo_loc_value;

	@Field("appl")
    private String appl;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getGeo_loc_type() {
		return geo_loc_type;
	}

	public void setGeo_loc_type(String geo_loc_type) {
		this.geo_loc_type = geo_loc_type;
	}

	public int getBatch_number() {
		return batch_number;
	}

	public void setBatch_number(int batch_number) {
		this.batch_number = batch_number;
	}

	public String getTbl_id() {
		return tbl_id;
	}

	public void setTbl_id(String tbl_id) {
		this.tbl_id = tbl_id;
	}

	public String getRec_type() {
		return rec_type;
	}

	public void setRec_type(String rec_type) {
		this.rec_type = rec_type;
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

	public Date getBatch_date() {
		return batch_date;
	}

	public void setBatch_date(Date batch_date) {
		this.batch_date = batch_date;
	}

	public String getGeo_loc_value() {
		return geo_loc_value;
	}

	public void setGeo_loc_value(String geo_loc_value) {
		this.geo_loc_value = geo_loc_value;
	}

	public String getAppl() {
		return appl;
	}

	public void setAppl(String appl) {
		this.appl = appl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((appl == null) ? 0 : appl.hashCode());
		result = prime * result + ((batch_date == null) ? 0 : batch_date.hashCode());
		result = prime * result + batch_number;
		result = prime * result + ((geo_loc_type == null) ? 0 : geo_loc_type.hashCode());
		result = prime * result + ((geo_loc_value == null) ? 0 : geo_loc_value.hashCode());
		result = prime * result + ((rec_type == null) ? 0 : rec_type.hashCode());
		result = prime * result + ((tbl_id == null) ? 0 : tbl_id.hashCode());
		result = prime * result + ((tbl_no == null) ? 0 : tbl_no.hashCode());
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
		UserZoneTable other = (UserZoneTable) obj;
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
		if (batch_date == null) {
			if (other.batch_date != null) {
				return false;
			}
		} else if (!batch_date.equals(other.batch_date)) {
			return false;
		}
		if (batch_number != other.batch_number) {
			return false;
		}
		if (geo_loc_type == null) {
			if (other.geo_loc_type != null) {
				return false;
			}
		} else if (!geo_loc_type.equals(other.geo_loc_type)) {
			return false;
		}
		if (geo_loc_value == null) {
			if (other.geo_loc_value != null) {
				return false;
			}
		} else if (!geo_loc_value.equals(other.geo_loc_value)) {
			return false;
		}
		if (rec_type == null) {
			if (other.rec_type != null) {
				return false;
			}
		} else if (!rec_type.equals(other.rec_type)) {
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
		return true;
	}
}
