package com.atibusinessgroup.fmp.domain.atpco;

import org.springframework.data.mongodb.core.mapping.Field;

public class AtpcoRecord3Cat08Stopovers {
	
	@Field("cxr")
    private String cxr;

	@Field("no")
    private String no;

	@Field("in_cxr")
    private String in_cxr;
    
	@Field("ap")
	private String ap;

	@Field("geo_type")
    private String geo_type;

	@Field("geo_loc_1")
    private String geo_loc_1;

	@Field("geo_loc_2")
    private String geo_loc_2;

	@Field("out_cxr")
    private String out_cxr;

	@Field("io")
    private String io;

	@Field("ch")
    private String ch;

	public String getCxr() {
		return cxr;
	}

	public void setCxr(String cxr) {
		this.cxr = cxr;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getIn_cxr() {
		return in_cxr;
	}

	public void setIn_cxr(String in_cxr) {
		this.in_cxr = in_cxr;
	}

	public String getAp() {
		return ap;
	}

	public void setAp(String ap) {
		this.ap = ap;
	}

	public String getGeo_type() {
		return geo_type;
	}

	public void setGeo_type(String geo_type) {
		this.geo_type = geo_type;
	}

	public String getGeo_loc_1() {
		return geo_loc_1;
	}

	public void setGeo_loc_1(String geo_loc_1) {
		this.geo_loc_1 = geo_loc_1;
	}

	public String getGeo_loc_2() {
		return geo_loc_2;
	}

	public void setGeo_loc_2(String geo_loc_2) {
		this.geo_loc_2 = geo_loc_2;
	}

	public String getOut_cxr() {
		return out_cxr;
	}

	public void setOut_cxr(String out_cxr) {
		this.out_cxr = out_cxr;
	}

	public String getIo() {
		return io;
	}

	public void setIo(String io) {
		this.io = io;
	}

	public String getCh() {
		return ch;
	}

	public void setCh(String ch) {
		this.ch = ch;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ap == null) ? 0 : ap.hashCode());
		result = prime * result + ((ch == null) ? 0 : ch.hashCode());
		result = prime * result + ((cxr == null) ? 0 : cxr.hashCode());
		result = prime * result + ((geo_loc_1 == null) ? 0 : geo_loc_1.hashCode());
		result = prime * result + ((geo_loc_2 == null) ? 0 : geo_loc_2.hashCode());
		result = prime * result + ((geo_type == null) ? 0 : geo_type.hashCode());
		result = prime * result + ((in_cxr == null) ? 0 : in_cxr.hashCode());
		result = prime * result + ((io == null) ? 0 : io.hashCode());
		result = prime * result + ((no == null) ? 0 : no.hashCode());
		result = prime * result + ((out_cxr == null) ? 0 : out_cxr.hashCode());
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
		AtpcoRecord3Cat08Stopovers other = (AtpcoRecord3Cat08Stopovers) obj;
		if (ap == null) {
			if (other.ap != null)
				return false;
		} else if (!ap.equals(other.ap))
			return false;
		if (ch == null) {
			if (other.ch != null)
				return false;
		} else if (!ch.equals(other.ch))
			return false;
		if (cxr == null) {
			if (other.cxr != null)
				return false;
		} else if (!cxr.equals(other.cxr))
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
		if (in_cxr == null) {
			if (other.in_cxr != null)
				return false;
		} else if (!in_cxr.equals(other.in_cxr))
			return false;
		if (io == null) {
			if (other.io != null)
				return false;
		} else if (!io.equals(other.io))
			return false;
		if (no == null) {
			if (other.no != null)
				return false;
		} else if (!no.equals(other.no))
			return false;
		if (out_cxr == null) {
			if (other.out_cxr != null)
				return false;
		} else if (!out_cxr.equals(other.out_cxr))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoRecord3Cat08Stopovers [cxr=" + cxr + ", no=" + no + ", in_cxr=" + in_cxr + ", ap=" + ap
				+ ", geo_type=" + geo_type + ", geo_loc_1=" + geo_loc_1 + ", geo_loc_2=" + geo_loc_2 + ", out_cxr="
				+ out_cxr + ", io=" + io + ", ch=" + ch + "]";
	}
	
	


}
