package com.atibusinessgroup.fmp.domain.atpco;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.atibusinessgroup.fmp.domain.dto.AtpcoAddOnCurrencyAmount;
import com.atibusinessgroup.fmp.domain.dto.AtpcoAddOnTypeTag;

@Document(collection = "atpco_addon")
public class AtpcoAddOn {

	@Field("add_on_zone")
	private String add_on_zone;

	@Field("geo_zones_destination")
    private String geo_zones_destination;

	@Field("type_tag")
    private AtpcoAddOnTypeTag type_tag;

	@Field("country_code_destination")
    private String country_code_destination;

	@Field("rtg_no")
    private String rtg_no;

	@Field("cxr_code")
    private String cxr_code;

	@Field("mcn")
    private String mcn;

	@Field("dates_disc")
    private String dates_disc;

    @Id
    private String _id;

    @Field("action")
    private String action;

    @Field("batch_ci")
    private String batch_ci;

    @Field("filler")
    private String filler;

    @Field("batch_no")
    private String batch_no;

    @Field("fare_class_cd")
    private String fare_class_cd;

    @Field("batch_number")
    private int batch_number;

    @Field("dates_eff")
    private Object dates_eff;

    @Field("dates_tar_eff")
    private Object dates_tar_eff;

    @Field("prop")
    private String prop;

    @Field("market_origin")
    private String market_origin;

    @Field("link_no")
    private String link_no;

    @Field("batch_date")
    private Object batch_date;

    @Field("tar_no")
    private String tar_no;

    @Field("recs")
    private String recs;

    @Field("market_destination")
    private String market_destination;

    @Field("gfs_date")
    private String gfs_date;

    @Field("ftnt")
    private String ftnt;

    @Field("ow_rt")
    private String ow_rt;

    @Field("add_on")
    private AtpcoAddOnCurrencyAmount add_on;

    @Field("link_seq")
    private String link_seq;

    @Field("gfs_number")
    private String gfs_number;

    @Field("geo_zones_origin")
    private String geo_zones_origin;

    @Field("country_code_origin")
    private String country_code_origin;

	public String getAdd_on_zone() {
		return add_on_zone;
	}

	public void setAdd_on_zone(String add_on_zone) {
		this.add_on_zone = add_on_zone;
	}

	public String getGeo_zones_destination() {
		return geo_zones_destination;
	}

	public void setGeo_zones_destination(String geo_zones_destination) {
		this.geo_zones_destination = geo_zones_destination;
	}

	public AtpcoAddOnTypeTag getType_tag() {
		return type_tag;
	}

	public void setType_tag(AtpcoAddOnTypeTag type_tag) {
		this.type_tag = type_tag;
	}

	public String getCountry_code_destination() {
		return country_code_destination;
	}

	public void setCountry_code_destination(String country_code_destination) {
		this.country_code_destination = country_code_destination;
	}

	public String getRtg_no() {
		return rtg_no;
	}

	public void setRtg_no(String rtg_no) {
		this.rtg_no = rtg_no;
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

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
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

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	public String getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}

	public String getFare_class_cd() {
		return fare_class_cd;
	}

	public void setFare_class_cd(String fare_class_cd) {
		this.fare_class_cd = fare_class_cd;
	}

	public int getBatch_number() {
		return batch_number;
	}

	public void setBatch_number(int batch_number) {
		this.batch_number = batch_number;
	}

	public Object getDates_eff() {
		return dates_eff;
	}

	public void setDates_eff(Object dates_eff) {
		this.dates_eff = dates_eff;
	}

	public Object getDates_tar_eff() {
		return dates_tar_eff;
	}

	public void setDates_tar_eff(Object dates_tar_eff) {
		this.dates_tar_eff = dates_tar_eff;
	}

	public String getProp() {
		return prop;
	}

	public void setProp(String prop) {
		this.prop = prop;
	}

	public String getMarket_origin() {
		return market_origin;
	}

	public void setMarket_origin(String market_origin) {
		this.market_origin = market_origin;
	}

	public String getLink_no() {
		return link_no;
	}

	public void setLink_no(String link_no) {
		this.link_no = link_no;
	}

	public Object getBatch_date() {
		return batch_date;
	}

	public void setBatch_date(Object batch_date) {
		this.batch_date = batch_date;
	}

	public String getTar_no() {
		return tar_no;
	}

	public void setTar_no(String tar_no) {
		this.tar_no = tar_no;
	}

	public String getRecs() {
		return recs;
	}

	public void setRecs(String recs) {
		this.recs = recs;
	}

	public String getMarket_destination() {
		return market_destination;
	}

	public void setMarket_destination(String market_destination) {
		this.market_destination = market_destination;
	}

	public String getGfs_date() {
		return gfs_date;
	}

	public void setGfs_date(String gfs_date) {
		this.gfs_date = gfs_date;
	}

	public String getFtnt() {
		return ftnt;
	}

	public void setFtnt(String ftnt) {
		this.ftnt = ftnt;
	}

	public String getOw_rt() {
		return ow_rt;
	}

	public void setOw_rt(String ow_rt) {
		this.ow_rt = ow_rt;
	}

	public AtpcoAddOnCurrencyAmount getAdd_on() {
		return add_on;
	}

	public void setAdd_on(AtpcoAddOnCurrencyAmount add_on) {
		this.add_on = add_on;
	}

	public String getLink_seq() {
		return link_seq;
	}

	public void setLink_seq(String link_seq) {
		this.link_seq = link_seq;
	}

	public String getGfs_number() {
		return gfs_number;
	}

	public void setGfs_number(String gfs_number) {
		this.gfs_number = gfs_number;
	}

	public String getGeo_zones_origin() {
		return geo_zones_origin;
	}

	public void setGeo_zones_origin(String geo_zones_origin) {
		this.geo_zones_origin = geo_zones_origin;
	}

	public String getCountry_code_origin() {
		return country_code_origin;
	}

	public void setCountry_code_origin(String country_code_origin) {
		this.country_code_origin = country_code_origin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((add_on == null) ? 0 : add_on.hashCode());
		result = prime * result + ((add_on_zone == null) ? 0 : add_on_zone.hashCode());
		result = prime * result + ((batch_ci == null) ? 0 : batch_ci.hashCode());
		result = prime * result + ((batch_date == null) ? 0 : batch_date.hashCode());
		result = prime * result + ((batch_no == null) ? 0 : batch_no.hashCode());
		result = prime * result + batch_number;
		result = prime * result + ((country_code_destination == null) ? 0 : country_code_destination.hashCode());
		result = prime * result + ((country_code_origin == null) ? 0 : country_code_origin.hashCode());
		result = prime * result + ((cxr_code == null) ? 0 : cxr_code.hashCode());
		result = prime * result + ((dates_disc == null) ? 0 : dates_disc.hashCode());
		result = prime * result + ((dates_eff == null) ? 0 : dates_eff.hashCode());
		result = prime * result + ((dates_tar_eff == null) ? 0 : dates_tar_eff.hashCode());
		result = prime * result + ((fare_class_cd == null) ? 0 : fare_class_cd.hashCode());
		result = prime * result + ((filler == null) ? 0 : filler.hashCode());
		result = prime * result + ((ftnt == null) ? 0 : ftnt.hashCode());
		result = prime * result + ((geo_zones_destination == null) ? 0 : geo_zones_destination.hashCode());
		result = prime * result + ((geo_zones_origin == null) ? 0 : geo_zones_origin.hashCode());
		result = prime * result + ((gfs_date == null) ? 0 : gfs_date.hashCode());
		result = prime * result + ((gfs_number == null) ? 0 : gfs_number.hashCode());
		result = prime * result + ((link_no == null) ? 0 : link_no.hashCode());
		result = prime * result + ((link_seq == null) ? 0 : link_seq.hashCode());
		result = prime * result + ((market_destination == null) ? 0 : market_destination.hashCode());
		result = prime * result + ((market_origin == null) ? 0 : market_origin.hashCode());
		result = prime * result + ((mcn == null) ? 0 : mcn.hashCode());
		result = prime * result + ((ow_rt == null) ? 0 : ow_rt.hashCode());
		result = prime * result + ((prop == null) ? 0 : prop.hashCode());
		result = prime * result + ((recs == null) ? 0 : recs.hashCode());
		result = prime * result + ((rtg_no == null) ? 0 : rtg_no.hashCode());
		result = prime * result + ((tar_no == null) ? 0 : tar_no.hashCode());
		result = prime * result + ((type_tag == null) ? 0 : type_tag.hashCode());
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
		AtpcoAddOn other = (AtpcoAddOn) obj;
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
		if (add_on == null) {
			if (other.add_on != null) {
				return false;
			}
		} else if (!add_on.equals(other.add_on)) {
			return false;
		}
		if (add_on_zone == null) {
			if (other.add_on_zone != null) {
				return false;
			}
		} else if (!add_on_zone.equals(other.add_on_zone)) {
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
		if (country_code_destination == null) {
			if (other.country_code_destination != null) {
				return false;
			}
		} else if (!country_code_destination.equals(other.country_code_destination)) {
			return false;
		}
		if (country_code_origin == null) {
			if (other.country_code_origin != null) {
				return false;
			}
		} else if (!country_code_origin.equals(other.country_code_origin)) {
			return false;
		}
		if (cxr_code == null) {
			if (other.cxr_code != null) {
				return false;
			}
		} else if (!cxr_code.equals(other.cxr_code)) {
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
		if (dates_tar_eff == null) {
			if (other.dates_tar_eff != null) {
				return false;
			}
		} else if (!dates_tar_eff.equals(other.dates_tar_eff)) {
			return false;
		}
		if (fare_class_cd == null) {
			if (other.fare_class_cd != null) {
				return false;
			}
		} else if (!fare_class_cd.equals(other.fare_class_cd)) {
			return false;
		}
		if (filler == null) {
			if (other.filler != null) {
				return false;
			}
		} else if (!filler.equals(other.filler)) {
			return false;
		}
		if (ftnt == null) {
			if (other.ftnt != null) {
				return false;
			}
		} else if (!ftnt.equals(other.ftnt)) {
			return false;
		}
		if (geo_zones_destination == null) {
			if (other.geo_zones_destination != null) {
				return false;
			}
		} else if (!geo_zones_destination.equals(other.geo_zones_destination)) {
			return false;
		}
		if (geo_zones_origin == null) {
			if (other.geo_zones_origin != null) {
				return false;
			}
		} else if (!geo_zones_origin.equals(other.geo_zones_origin)) {
			return false;
		}
		if (gfs_date == null) {
			if (other.gfs_date != null) {
				return false;
			}
		} else if (!gfs_date.equals(other.gfs_date)) {
			return false;
		}
		if (gfs_number == null) {
			if (other.gfs_number != null) {
				return false;
			}
		} else if (!gfs_number.equals(other.gfs_number)) {
			return false;
		}
		if (link_no == null) {
			if (other.link_no != null) {
				return false;
			}
		} else if (!link_no.equals(other.link_no)) {
			return false;
		}
		if (link_seq == null) {
			if (other.link_seq != null) {
				return false;
			}
		} else if (!link_seq.equals(other.link_seq)) {
			return false;
		}
		if (market_destination == null) {
			if (other.market_destination != null) {
				return false;
			}
		} else if (!market_destination.equals(other.market_destination)) {
			return false;
		}
		if (market_origin == null) {
			if (other.market_origin != null) {
				return false;
			}
		} else if (!market_origin.equals(other.market_origin)) {
			return false;
		}
		if (mcn == null) {
			if (other.mcn != null) {
				return false;
			}
		} else if (!mcn.equals(other.mcn)) {
			return false;
		}
		if (ow_rt == null) {
			if (other.ow_rt != null) {
				return false;
			}
		} else if (!ow_rt.equals(other.ow_rt)) {
			return false;
		}
		if (prop == null) {
			if (other.prop != null) {
				return false;
			}
		} else if (!prop.equals(other.prop)) {
			return false;
		}
		if (recs == null) {
			if (other.recs != null) {
				return false;
			}
		} else if (!recs.equals(other.recs)) {
			return false;
		}
		if (rtg_no == null) {
			if (other.rtg_no != null) {
				return false;
			}
		} else if (!rtg_no.equals(other.rtg_no)) {
			return false;
		}
		if (tar_no == null) {
			if (other.tar_no != null) {
				return false;
			}
		} else if (!tar_no.equals(other.tar_no)) {
			return false;
		}
		if (type_tag == null) {
			if (other.type_tag != null) {
				return false;
			}
		} else if (!type_tag.equals(other.type_tag)) {
			return false;
		}
		return true;
	}
}

