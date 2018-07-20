package com.atibusinessgroup.fmp.domain.atpco;

import org.springframework.data.mongodb.core.mapping.Field;

public class AtpcoRecord2Cat25DataSeg {

	@Field("cat_no")
	private String cat_no;

	@Field("di")
    private String di;

	@Field("tbl_no")
    private String tbl_no;

	@Field("ri")
    private String ri;

	@Field("io")
    private String io;

	public String getCat_no() {
		return cat_no;
	}

	public void setCat_no(String cat_no) {
		this.cat_no = cat_no;
	}

	public String getDi() {
		return di;
	}

	public void setDi(String di) {
		this.di = di;
	}

	public String getTbl_no() {
		return tbl_no;
	}

	public void setTbl_no(String tbl_no) {
		this.tbl_no = tbl_no;
	}

	public String getRi() {
		return ri;
	}

	public void setRi(String ri) {
		this.ri = ri;
	}

	public String getIo() {
		return io;
	}

	public void setIo(String io) {
		this.io = io;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cat_no == null) ? 0 : cat_no.hashCode());
		result = prime * result + ((di == null) ? 0 : di.hashCode());
		result = prime * result + ((io == null) ? 0 : io.hashCode());
		result = prime * result + ((ri == null) ? 0 : ri.hashCode());
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
		AtpcoRecord2Cat25DataSeg other = (AtpcoRecord2Cat25DataSeg) obj;
		if (cat_no == null) {
			if (other.cat_no != null) {
				return false;
			}
		} else if (!cat_no.equals(other.cat_no)) {
			return false;
		}
		if (di == null) {
			if (other.di != null) {
				return false;
			}
		} else if (!di.equals(other.di)) {
			return false;
		}
		if (io == null) {
			if (other.io != null) {
				return false;
			}
		} else if (!io.equals(other.io)) {
			return false;
		}
		if (ri == null) {
			if (other.ri != null) {
				return false;
			}
		} else if (!ri.equals(other.ri)) {
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
