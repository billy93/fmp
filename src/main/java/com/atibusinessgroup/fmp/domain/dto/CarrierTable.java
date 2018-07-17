package com.atibusinessgroup.fmp.domain.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class CarrierTable {

	@Field("batch_number")
	private int batch_number;

	@Field("tbl_id")
    private String tbl_id;

	@Id
    private String _id;

	@Field("rec_type")
    private String rec_type;

	@Field("tbl_no")
    private String tbl_no;

	@Field("data_segs")
    private List<CarrierTableDataSegs> data_segs = new ArrayList<>();

	@Field("action")
    private String action;

	@Field("batch_date")
    private Date batch_date;

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

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
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

	public List<CarrierTableDataSegs> getData_segs() {
		return data_segs;
	}

	public void setData_segs(List<CarrierTableDataSegs> data_segs) {
		this.data_segs = data_segs;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((batch_date == null) ? 0 : batch_date.hashCode());
		result = prime * result + batch_number;
		result = prime * result + ((data_segs == null) ? 0 : data_segs.hashCode());
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
		CarrierTable other = (CarrierTable) obj;
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
		if (data_segs == null) {
			if (other.data_segs != null) {
				return false;
			}
		} else if (!data_segs.equals(other.data_segs)) {
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
