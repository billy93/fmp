package com.atibusinessgroup.fmp.domain.atpco;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.atibusinessgroup.fmp.constant.CollectionName;

@Document(collection = CollectionName.ATPCO_MASTER_G16)
public class AtpcoMasterG16 {
	
	@Field("general_rule_tariff_cd")
	private String general_rule_tariff_cd;

	@Field("routing_2_tariff_no")
    private String routing_2_tariff_no;

	@Field("area_code")
    private String area_code;

	@Field("disc_date")
    private Object disc_date;

	@Field("fare_rule_tariff_no")
    private String fare_rule_tariff_no;

	@Field("arbitary_tariff")
    private List<AtpcoMasterG16ArbitaryTariff> arbitary_tariff;

	@Field("areas_code")
    private AtpcoMasterG16AreaCode areas_code;

	@Field("constructed_data_tariff_no")
    private String constructed_data_tariff_no;

	@Field("type")
    private String type;

	@Field("level_id")
    private String level_id;

	@Field("fare_class_tariff_no")
    private String fare_class_tariff_no;

	@Field("routing_2_tariff_cd")
    private String routing_2_tariff_cd;

	@Field("cxr_code")
    private String cxr_code;

	@Field("routing_1_tariff_no")
    private String routing_1_tariff_no;

	@Field("fare_rule_tariff_cd")
    private String fare_rule_tariff_cd;

	@Id
    private String _id;

	@Field("general_rule_tariff_no")
    private String general_rule_tariff_no;

	@Field("fare_class_tariff_cd")
    private String fare_class_tariff_cd;

	@Field("routing_1_tariff_cd")
    private String routing_1_tariff_cd;

	@Field("constructed_data_tariff_cd")
    private String constructed_data_tariff_cd;

	@Field("date_stamp")
    private Object date_stamp;

	@Field("time_stamp")
    private Object time_stamp;

	@Field("eff_date")
    private Object eff_date;

	public String getGeneral_rule_tariff_cd() {
		return general_rule_tariff_cd;
	}

	public void setGeneral_rule_tariff_cd(String general_rule_tariff_cd) {
		this.general_rule_tariff_cd = general_rule_tariff_cd;
	}

	public String getRouting_2_tariff_no() {
		return routing_2_tariff_no;
	}

	public void setRouting_2_tariff_no(String routing_2_tariff_no) {
		this.routing_2_tariff_no = routing_2_tariff_no;
	}

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	public Object getDisc_date() {
		return disc_date;
	}

	public void setDisc_date(Object disc_date) {
		this.disc_date = disc_date;
	}

	public String getFare_rule_tariff_no() {
		return fare_rule_tariff_no;
	}

	public void setFare_rule_tariff_no(String fare_rule_tariff_no) {
		this.fare_rule_tariff_no = fare_rule_tariff_no;
	}

	public List<AtpcoMasterG16ArbitaryTariff> getArbitary_tariff() {
		return arbitary_tariff;
	}

	public void setArbitary_tariff(List<AtpcoMasterG16ArbitaryTariff> arbitary_tariff) {
		this.arbitary_tariff = arbitary_tariff;
	}

	public AtpcoMasterG16AreaCode getAreas_code() {
		return areas_code;
	}

	public void setAreas_code(AtpcoMasterG16AreaCode areas_code) {
		this.areas_code = areas_code;
	}

	public String getConstructed_data_tariff_no() {
		return constructed_data_tariff_no;
	}

	public void setConstructed_data_tariff_no(String constructed_data_tariff_no) {
		this.constructed_data_tariff_no = constructed_data_tariff_no;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLevel_id() {
		return level_id;
	}

	public void setLevel_id(String level_id) {
		this.level_id = level_id;
	}

	public String getFare_class_tariff_no() {
		return fare_class_tariff_no;
	}

	public void setFare_class_tariff_no(String fare_class_tariff_no) {
		this.fare_class_tariff_no = fare_class_tariff_no;
	}

	public String getRouting_2_tariff_cd() {
		return routing_2_tariff_cd;
	}

	public void setRouting_2_tariff_cd(String routing_2_tariff_cd) {
		this.routing_2_tariff_cd = routing_2_tariff_cd;
	}

	public String getCxr_code() {
		return cxr_code;
	}

	public void setCxr_code(String cxr_code) {
		this.cxr_code = cxr_code;
	}

	public String getRouting_1_tariff_no() {
		return routing_1_tariff_no;
	}

	public void setRouting_1_tariff_no(String routing_1_tariff_no) {
		this.routing_1_tariff_no = routing_1_tariff_no;
	}

	public String getFare_rule_tariff_cd() {
		return fare_rule_tariff_cd;
	}

	public void setFare_rule_tariff_cd(String fare_rule_tariff_cd) {
		this.fare_rule_tariff_cd = fare_rule_tariff_cd;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getGeneral_rule_tariff_no() {
		return general_rule_tariff_no;
	}

	public void setGeneral_rule_tariff_no(String general_rule_tariff_no) {
		this.general_rule_tariff_no = general_rule_tariff_no;
	}

	public String getFare_class_tariff_cd() {
		return fare_class_tariff_cd;
	}

	public void setFare_class_tariff_cd(String fare_class_tariff_cd) {
		this.fare_class_tariff_cd = fare_class_tariff_cd;
	}

	public String getRouting_1_tariff_cd() {
		return routing_1_tariff_cd;
	}

	public void setRouting_1_tariff_cd(String routing_1_tariff_cd) {
		this.routing_1_tariff_cd = routing_1_tariff_cd;
	}

	public String getConstructed_data_tariff_cd() {
		return constructed_data_tariff_cd;
	}

	public void setConstructed_data_tariff_cd(String constructed_data_tariff_cd) {
		this.constructed_data_tariff_cd = constructed_data_tariff_cd;
	}

	public Object getDate_stamp() {
		return date_stamp;
	}

	public void setDate_stamp(Object date_stamp) {
		this.date_stamp = date_stamp;
	}

	public Object getTime_stamp() {
		return time_stamp;
	}

	public void setTime_stamp(Object time_stamp) {
		this.time_stamp = time_stamp;
	}

	public Object getEff_date() {
		return eff_date;
	}

	public void setEff_date(Object eff_date) {
		this.eff_date = eff_date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((arbitary_tariff == null) ? 0 : arbitary_tariff.hashCode());
		result = prime * result + ((area_code == null) ? 0 : area_code.hashCode());
		result = prime * result + ((areas_code == null) ? 0 : areas_code.hashCode());
		result = prime * result + ((constructed_data_tariff_cd == null) ? 0 : constructed_data_tariff_cd.hashCode());
		result = prime * result + ((constructed_data_tariff_no == null) ? 0 : constructed_data_tariff_no.hashCode());
		result = prime * result + ((cxr_code == null) ? 0 : cxr_code.hashCode());
		result = prime * result + ((date_stamp == null) ? 0 : date_stamp.hashCode());
		result = prime * result + ((disc_date == null) ? 0 : disc_date.hashCode());
		result = prime * result + ((eff_date == null) ? 0 : eff_date.hashCode());
		result = prime * result + ((fare_class_tariff_cd == null) ? 0 : fare_class_tariff_cd.hashCode());
		result = prime * result + ((fare_class_tariff_no == null) ? 0 : fare_class_tariff_no.hashCode());
		result = prime * result + ((fare_rule_tariff_cd == null) ? 0 : fare_rule_tariff_cd.hashCode());
		result = prime * result + ((fare_rule_tariff_no == null) ? 0 : fare_rule_tariff_no.hashCode());
		result = prime * result + ((general_rule_tariff_cd == null) ? 0 : general_rule_tariff_cd.hashCode());
		result = prime * result + ((general_rule_tariff_no == null) ? 0 : general_rule_tariff_no.hashCode());
		result = prime * result + ((level_id == null) ? 0 : level_id.hashCode());
		result = prime * result + ((routing_1_tariff_cd == null) ? 0 : routing_1_tariff_cd.hashCode());
		result = prime * result + ((routing_1_tariff_no == null) ? 0 : routing_1_tariff_no.hashCode());
		result = prime * result + ((routing_2_tariff_cd == null) ? 0 : routing_2_tariff_cd.hashCode());
		result = prime * result + ((routing_2_tariff_no == null) ? 0 : routing_2_tariff_no.hashCode());
		result = prime * result + ((time_stamp == null) ? 0 : time_stamp.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		AtpcoMasterG16 other = (AtpcoMasterG16) obj;
		if (_id == null) {
			if (other._id != null) {
				return false;
			}
		} else if (!_id.equals(other._id)) {
			return false;
		}
		if (arbitary_tariff == null) {
			if (other.arbitary_tariff != null) {
				return false;
			}
		} else if (!arbitary_tariff.equals(other.arbitary_tariff)) {
			return false;
		}
		if (area_code == null) {
			if (other.area_code != null) {
				return false;
			}
		} else if (!area_code.equals(other.area_code)) {
			return false;
		}
		if (areas_code == null) {
			if (other.areas_code != null) {
				return false;
			}
		} else if (!areas_code.equals(other.areas_code)) {
			return false;
		}
		if (constructed_data_tariff_cd == null) {
			if (other.constructed_data_tariff_cd != null) {
				return false;
			}
		} else if (!constructed_data_tariff_cd.equals(other.constructed_data_tariff_cd)) {
			return false;
		}
		if (constructed_data_tariff_no == null) {
			if (other.constructed_data_tariff_no != null) {
				return false;
			}
		} else if (!constructed_data_tariff_no.equals(other.constructed_data_tariff_no)) {
			return false;
		}
		if (cxr_code == null) {
			if (other.cxr_code != null) {
				return false;
			}
		} else if (!cxr_code.equals(other.cxr_code)) {
			return false;
		}
		if (date_stamp == null) {
			if (other.date_stamp != null) {
				return false;
			}
		} else if (!date_stamp.equals(other.date_stamp)) {
			return false;
		}
		if (disc_date == null) {
			if (other.disc_date != null) {
				return false;
			}
		} else if (!disc_date.equals(other.disc_date)) {
			return false;
		}
		if (eff_date == null) {
			if (other.eff_date != null) {
				return false;
			}
		} else if (!eff_date.equals(other.eff_date)) {
			return false;
		}
		if (fare_class_tariff_cd == null) {
			if (other.fare_class_tariff_cd != null) {
				return false;
			}
		} else if (!fare_class_tariff_cd.equals(other.fare_class_tariff_cd)) {
			return false;
		}
		if (fare_class_tariff_no == null) {
			if (other.fare_class_tariff_no != null) {
				return false;
			}
		} else if (!fare_class_tariff_no.equals(other.fare_class_tariff_no)) {
			return false;
		}
		if (fare_rule_tariff_cd == null) {
			if (other.fare_rule_tariff_cd != null) {
				return false;
			}
		} else if (!fare_rule_tariff_cd.equals(other.fare_rule_tariff_cd)) {
			return false;
		}
		if (fare_rule_tariff_no == null) {
			if (other.fare_rule_tariff_no != null) {
				return false;
			}
		} else if (!fare_rule_tariff_no.equals(other.fare_rule_tariff_no)) {
			return false;
		}
		if (general_rule_tariff_cd == null) {
			if (other.general_rule_tariff_cd != null) {
				return false;
			}
		} else if (!general_rule_tariff_cd.equals(other.general_rule_tariff_cd)) {
			return false;
		}
		if (general_rule_tariff_no == null) {
			if (other.general_rule_tariff_no != null) {
				return false;
			}
		} else if (!general_rule_tariff_no.equals(other.general_rule_tariff_no)) {
			return false;
		}
		if (level_id == null) {
			if (other.level_id != null) {
				return false;
			}
		} else if (!level_id.equals(other.level_id)) {
			return false;
		}
		if (routing_1_tariff_cd == null) {
			if (other.routing_1_tariff_cd != null) {
				return false;
			}
		} else if (!routing_1_tariff_cd.equals(other.routing_1_tariff_cd)) {
			return false;
		}
		if (routing_1_tariff_no == null) {
			if (other.routing_1_tariff_no != null) {
				return false;
			}
		} else if (!routing_1_tariff_no.equals(other.routing_1_tariff_no)) {
			return false;
		}
		if (routing_2_tariff_cd == null) {
			if (other.routing_2_tariff_cd != null) {
				return false;
			}
		} else if (!routing_2_tariff_cd.equals(other.routing_2_tariff_cd)) {
			return false;
		}
		if (routing_2_tariff_no == null) {
			if (other.routing_2_tariff_no != null) {
				return false;
			}
		} else if (!routing_2_tariff_no.equals(other.routing_2_tariff_no)) {
			return false;
		}
		if (time_stamp == null) {
			if (other.time_stamp != null) {
				return false;
			}
		} else if (!time_stamp.equals(other.time_stamp)) {
			return false;
		}
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		return true;
	}
}

