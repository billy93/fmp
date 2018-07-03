package com.atibusinessgroup.fmp.domain.atpco;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.atibusinessgroup.fmp.domain.dto.Cat10CircleTrip;
import com.atibusinessgroup.fmp.domain.dto.Cat10EndOnEnd;
import com.atibusinessgroup.fmp.domain.dto.Cat10GeoLoc;
import com.atibusinessgroup.fmp.domain.dto.Cat10OpenJaw;
import com.atibusinessgroup.fmp.domain.dto.DataTable;

@Document(collection = "atpco_record_2_10")
public class AtpcoRecord2Cat10 {
	
	@Id
    private String id;
	
	@Field("rule_type")
	private String rule_type;

	@Field("circle_trip")
    private Cat10CircleTrip circle_trip;

	@Field("rule_tar_no")
    private String rule_tar_no;

	@Field("rtg_no")
    private String rtg_no;

	@Field("record_id")
    private String record_id;

	@Field("cxr_code")
    private String cxr_code;

	@Field("end_on_end")
    private Cat10EndOnEnd end_on_end;

	@Field("mcn")
    private String mcn;

	@Field("open_jaw")
    private Cat10OpenJaw open_jaw;

	@Field("dates_disc")
    private String dates_disc;

	@Field("seq_no")
    private String seq_no;

	@Field("season_type")
    private String season_type;

	@Field("data_segs")
    private List<DataTable> data_segs;

	@Field("rule_no")
    private String rule_no;

	@Field("action")
    private String action;

	@Field("no_appl")
    private String no_appl;

	@Field("batch_ci")
    private String batch_ci;

	@Field("arbitraries")
    private String arbitraries;

	@Field("geo_loc")
    private Cat10GeoLoc geo_loc;

	@Field("batch_no")
    private String batch_no;

	@Field("record_id_map")
    private String record_id_map;

	@Field("same_pt_tbl_993")
    private String same_pt_tbl_993;

	@Field("dates_eff")
    private Object dates_eff;

	@Field("batch_number")
    private int batch_number;

	@Field("day_of_week_type")
    private String day_of_week_type;

	@Field("ft_nt")
    private String ft_nt;

	@Field("batch_date")
    private Object batch_date;

	@Field("cat_no")
    private String cat_no;

	@Field("fare_type")
    private String fare_type;

	@Field("fare_class")
    private String fare_class;

	@Field("rec_type")
    private String rec_type;

	@Field("owrt")
    private String owrt;

	@Field("jt_cxr_tbl_no_997")
    private String jt_cxr_tbl_no_997;

	@Field("filler_1")
    private String filler_1;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRule_type() {
		return rule_type;
	}

	public void setRule_type(String rule_type) {
		this.rule_type = rule_type;
	}

	public Cat10CircleTrip getCircle_trip() {
		return circle_trip;
	}

	public void setCircle_trip(Cat10CircleTrip circle_trip) {
		this.circle_trip = circle_trip;
	}

	public String getRule_tar_no() {
		return rule_tar_no;
	}

	public void setRule_tar_no(String rule_tar_no) {
		this.rule_tar_no = rule_tar_no;
	}

	public String getRtg_no() {
		return rtg_no;
	}

	public void setRtg_no(String rtg_no) {
		this.rtg_no = rtg_no;
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

	public Cat10EndOnEnd getEnd_on_end() {
		return end_on_end;
	}

	public void setEnd_on_end(Cat10EndOnEnd end_on_end) {
		this.end_on_end = end_on_end;
	}

	public String getMcn() {
		return mcn;
	}

	public void setMcn(String mcn) {
		this.mcn = mcn;
	}

	public Cat10OpenJaw getOpen_jaw() {
		return open_jaw;
	}

	public void setOpen_jaw(Cat10OpenJaw open_jaw) {
		this.open_jaw = open_jaw;
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

	public String getSeason_type() {
		return season_type;
	}

	public void setSeason_type(String season_type) {
		this.season_type = season_type;
	}

	public List<DataTable> getData_segs() {
		return data_segs;
	}

	public void setData_segs(List<DataTable> data_segs) {
		this.data_segs = data_segs;
	}

	public String getRule_no() {
		return rule_no;
	}

	public void setRule_no(String rule_no) {
		this.rule_no = rule_no;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getNo_appl() {
		return no_appl;
	}

	public void setNo_appl(String no_appl) {
		this.no_appl = no_appl;
	}

	public String getBatch_ci() {
		return batch_ci;
	}

	public void setBatch_ci(String batch_ci) {
		this.batch_ci = batch_ci;
	}

	public String getArbitraries() {
		return arbitraries;
	}

	public void setArbitraries(String arbitraries) {
		this.arbitraries = arbitraries;
	}

	public Cat10GeoLoc getGeo_loc() {
		return geo_loc;
	}

	public void setGeo_loc(Cat10GeoLoc geo_loc) {
		this.geo_loc = geo_loc;
	}

	public String getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}

	public String getRecord_id_map() {
		return record_id_map;
	}

	public void setRecord_id_map(String record_id_map) {
		this.record_id_map = record_id_map;
	}

	public String getSame_pt_tbl_993() {
		return same_pt_tbl_993;
	}

	public void setSame_pt_tbl_993(String same_pt_tbl_993) {
		this.same_pt_tbl_993 = same_pt_tbl_993;
	}

	public Object getDates_eff() {
		return dates_eff;
	}

	public void setDates_eff(Object dates_eff) {
		this.dates_eff = dates_eff;
	}

	public int getBatch_number() {
		return batch_number;
	}

	public void setBatch_number(int batch_number) {
		this.batch_number = batch_number;
	}

	public String getDay_of_week_type() {
		return day_of_week_type;
	}

	public void setDay_of_week_type(String day_of_week_type) {
		this.day_of_week_type = day_of_week_type;
	}

	public String getFt_nt() {
		return ft_nt;
	}

	public void setFt_nt(String ft_nt) {
		this.ft_nt = ft_nt;
	}

	public Object getBatch_date() {
		return batch_date;
	}

	public void setBatch_date(Object batch_date) {
		this.batch_date = batch_date;
	}

	public String getCat_no() {
		return cat_no;
	}

	public void setCat_no(String cat_no) {
		this.cat_no = cat_no;
	}

	public String getFare_type() {
		return fare_type;
	}

	public void setFare_type(String fare_type) {
		this.fare_type = fare_type;
	}

	public String getFare_class() {
		return fare_class;
	}

	public void setFare_class(String fare_class) {
		this.fare_class = fare_class;
	}

	public String getRec_type() {
		return rec_type;
	}

	public void setRec_type(String rec_type) {
		this.rec_type = rec_type;
	}

	public String getOwrt() {
		return owrt;
	}

	public void setOwrt(String owrt) {
		this.owrt = owrt;
	}

	public String getJt_cxr_tbl_no_997() {
		return jt_cxr_tbl_no_997;
	}

	public void setJt_cxr_tbl_no_997(String jt_cxr_tbl_no_997) {
		this.jt_cxr_tbl_no_997 = jt_cxr_tbl_no_997;
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
		result = prime * result + ((arbitraries == null) ? 0 : arbitraries.hashCode());
		result = prime * result + ((batch_ci == null) ? 0 : batch_ci.hashCode());
		result = prime * result + ((batch_date == null) ? 0 : batch_date.hashCode());
		result = prime * result + ((batch_no == null) ? 0 : batch_no.hashCode());
		result = prime * result + batch_number;
		result = prime * result + ((cat_no == null) ? 0 : cat_no.hashCode());
		result = prime * result + ((circle_trip == null) ? 0 : circle_trip.hashCode());
		result = prime * result + ((cxr_code == null) ? 0 : cxr_code.hashCode());
		result = prime * result + ((data_segs == null) ? 0 : data_segs.hashCode());
		result = prime * result + ((dates_disc == null) ? 0 : dates_disc.hashCode());
		result = prime * result + ((dates_eff == null) ? 0 : dates_eff.hashCode());
		result = prime * result + ((day_of_week_type == null) ? 0 : day_of_week_type.hashCode());
		result = prime * result + ((end_on_end == null) ? 0 : end_on_end.hashCode());
		result = prime * result + ((fare_class == null) ? 0 : fare_class.hashCode());
		result = prime * result + ((fare_type == null) ? 0 : fare_type.hashCode());
		result = prime * result + ((filler_1 == null) ? 0 : filler_1.hashCode());
		result = prime * result + ((ft_nt == null) ? 0 : ft_nt.hashCode());
		result = prime * result + ((geo_loc == null) ? 0 : geo_loc.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((jt_cxr_tbl_no_997 == null) ? 0 : jt_cxr_tbl_no_997.hashCode());
		result = prime * result + ((mcn == null) ? 0 : mcn.hashCode());
		result = prime * result + ((no_appl == null) ? 0 : no_appl.hashCode());
		result = prime * result + ((open_jaw == null) ? 0 : open_jaw.hashCode());
		result = prime * result + ((owrt == null) ? 0 : owrt.hashCode());
		result = prime * result + ((rec_type == null) ? 0 : rec_type.hashCode());
		result = prime * result + ((record_id == null) ? 0 : record_id.hashCode());
		result = prime * result + ((record_id_map == null) ? 0 : record_id_map.hashCode());
		result = prime * result + ((rtg_no == null) ? 0 : rtg_no.hashCode());
		result = prime * result + ((rule_no == null) ? 0 : rule_no.hashCode());
		result = prime * result + ((rule_tar_no == null) ? 0 : rule_tar_no.hashCode());
		result = prime * result + ((rule_type == null) ? 0 : rule_type.hashCode());
		result = prime * result + ((same_pt_tbl_993 == null) ? 0 : same_pt_tbl_993.hashCode());
		result = prime * result + ((season_type == null) ? 0 : season_type.hashCode());
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
		AtpcoRecord2Cat10 other = (AtpcoRecord2Cat10) obj;
		if (action == null) {
			if (other.action != null) {
				return false;
			}
		} else if (!action.equals(other.action)) {
			return false;
		}
		if (arbitraries == null) {
			if (other.arbitraries != null) {
				return false;
			}
		} else if (!arbitraries.equals(other.arbitraries)) {
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
		if (batch_no == null) {
			if (other.batch_no != null) {
				return false;
			}
		} else if (!batch_no.equals(other.batch_no)) {
			return false;
		}
		if (batch_number != other.batch_number) {
			return false;
		}
		if (cat_no == null) {
			if (other.cat_no != null) {
				return false;
			}
		} else if (!cat_no.equals(other.cat_no)) {
			return false;
		}
		if (circle_trip == null) {
			if (other.circle_trip != null) {
				return false;
			}
		} else if (!circle_trip.equals(other.circle_trip)) {
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
		if (day_of_week_type == null) {
			if (other.day_of_week_type != null) {
				return false;
			}
		} else if (!day_of_week_type.equals(other.day_of_week_type)) {
			return false;
		}
		if (end_on_end == null) {
			if (other.end_on_end != null) {
				return false;
			}
		} else if (!end_on_end.equals(other.end_on_end)) {
			return false;
		}
		if (fare_class == null) {
			if (other.fare_class != null) {
				return false;
			}
		} else if (!fare_class.equals(other.fare_class)) {
			return false;
		}
		if (fare_type == null) {
			if (other.fare_type != null) {
				return false;
			}
		} else if (!fare_type.equals(other.fare_type)) {
			return false;
		}
		if (filler_1 == null) {
			if (other.filler_1 != null) {
				return false;
			}
		} else if (!filler_1.equals(other.filler_1)) {
			return false;
		}
		if (ft_nt == null) {
			if (other.ft_nt != null) {
				return false;
			}
		} else if (!ft_nt.equals(other.ft_nt)) {
			return false;
		}
		if (geo_loc == null) {
			if (other.geo_loc != null) {
				return false;
			}
		} else if (!geo_loc.equals(other.geo_loc)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
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
		if (open_jaw == null) {
			if (other.open_jaw != null) {
				return false;
			}
		} else if (!open_jaw.equals(other.open_jaw)) {
			return false;
		}
		if (owrt == null) {
			if (other.owrt != null) {
				return false;
			}
		} else if (!owrt.equals(other.owrt)) {
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
		if (rtg_no == null) {
			if (other.rtg_no != null) {
				return false;
			}
		} else if (!rtg_no.equals(other.rtg_no)) {
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
		if (rule_type == null) {
			if (other.rule_type != null) {
				return false;
			}
		} else if (!rule_type.equals(other.rule_type)) {
			return false;
		}
		if (same_pt_tbl_993 == null) {
			if (other.same_pt_tbl_993 != null) {
				return false;
			}
		} else if (!same_pt_tbl_993.equals(other.same_pt_tbl_993)) {
			return false;
		}
		if (season_type == null) {
			if (other.season_type != null) {
				return false;
			}
		} else if (!season_type.equals(other.season_type)) {
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

	@Override
	public String toString() {
		return "AtpcoRecord2Cat10 [id=" + id + ", rule_type=" + rule_type + ", circle_trip=" + circle_trip
				+ ", rule_tar_no=" + rule_tar_no + ", rtg_no=" + rtg_no + ", record_id=" + record_id + ", cxr_code="
				+ cxr_code + ", end_on_end=" + end_on_end + ", mcn=" + mcn + ", open_jaw=" + open_jaw + ", dates_disc="
				+ dates_disc + ", seq_no=" + seq_no + ", season_type=" + season_type + ", data_segs=" + data_segs
				+ ", rule_no=" + rule_no + ", action=" + action + ", no_appl=" + no_appl + ", batch_ci=" + batch_ci
				+ ", arbitraries=" + arbitraries + ", geo_loc=" + geo_loc + ", batch_no=" + batch_no
				+ ", record_id_map=" + record_id_map + ", same_pt_tbl_993=" + same_pt_tbl_993 + ", dates_eff="
				+ dates_eff + ", batch_number=" + batch_number + ", day_of_week_type=" + day_of_week_type + ", ft_nt="
				+ ft_nt + ", batch_date=" + batch_date + ", cat_no=" + cat_no + ", fare_type=" + fare_type
				+ ", fare_class=" + fare_class + ", rec_type=" + rec_type + ", owrt=" + owrt + ", jt_cxr_tbl_no_997="
				+ jt_cxr_tbl_no_997 + ", filler_1=" + filler_1 + "]";
	}
}
