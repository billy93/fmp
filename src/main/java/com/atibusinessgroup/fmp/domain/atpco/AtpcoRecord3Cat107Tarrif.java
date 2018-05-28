package com.atibusinessgroup.fmp.domain.atpco;

import org.springframework.data.mongodb.core.mapping.Field;

public class AtpcoRecord3Cat107Tarrif {

	@Field("prime")
	private String prime;

	@Field("def")
    private String def;

	@Field("global")
    private String global;

	@Field("rule_no")
    private String rule_no;

	@Field("tar_no")
    private String tar_no;

	@Field("same")
    private String same;

	@Field("appl")
    private String appl;

	public String getPrime() {
		return prime;
	}

	public void setPrime(String prime) {
		this.prime = prime;
	}

	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}

	public String getGlobal() {
		return global;
	}

	public void setGlobal(String global) {
		this.global = global;
	}

	public String getRule_no() {
		return rule_no;
	}

	public void setRule_no(String rule_no) {
		this.rule_no = rule_no;
	}

	public String getTar_no() {
		return tar_no;
	}

	public void setTar_no(String tar_no) {
		this.tar_no = tar_no;
	}

	public String getSame() {
		return same;
	}

	public void setSame(String same) {
		this.same = same;
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
		result = prime * result + ((appl == null) ? 0 : appl.hashCode());
		result = prime * result + ((def == null) ? 0 : def.hashCode());
		result = prime * result + ((global == null) ? 0 : global.hashCode());
		result = prime * result + ((this.prime == null) ? 0 : this.prime.hashCode());
		result = prime * result + ((rule_no == null) ? 0 : rule_no.hashCode());
		result = prime * result + ((same == null) ? 0 : same.hashCode());
		result = prime * result + ((tar_no == null) ? 0 : tar_no.hashCode());
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
		AtpcoRecord3Cat107Tarrif other = (AtpcoRecord3Cat107Tarrif) obj;
		if (appl == null) {
			if (other.appl != null)
				return false;
		} else if (!appl.equals(other.appl))
			return false;
		if (def == null) {
			if (other.def != null)
				return false;
		} else if (!def.equals(other.def))
			return false;
		if (global == null) {
			if (other.global != null)
				return false;
		} else if (!global.equals(other.global))
			return false;
		if (prime == null) {
			if (other.prime != null)
				return false;
		} else if (!prime.equals(other.prime))
			return false;
		if (rule_no == null) {
			if (other.rule_no != null)
				return false;
		} else if (!rule_no.equals(other.rule_no))
			return false;
		if (same == null) {
			if (other.same != null)
				return false;
		} else if (!same.equals(other.same))
			return false;
		if (tar_no == null) {
			if (other.tar_no != null)
				return false;
		} else if (!tar_no.equals(other.tar_no))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoRecord3Cat107Tarrif [prime=" + prime + ", def=" + def + ", global=" + global + ", rule_no="
				+ rule_no + ", tar_no=" + tar_no + ", same=" + same + ", appl=" + appl + "]";
	}
	
	
}
