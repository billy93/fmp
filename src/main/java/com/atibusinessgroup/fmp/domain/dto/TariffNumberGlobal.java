package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class TariffNumberGlobal {
	
	@Field("global")
	private String global;

	public String getGlobal() {
		return global;
	}

	public void setGlobal(String global) {
		this.global = global;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((global == null) ? 0 : global.hashCode());
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
		TariffNumberGlobal other = (TariffNumberGlobal) obj;
		if (global == null) {
			if (other.global != null) {
				return false;
			}
		} else if (!global.equals(other.global)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TariffNumberGlobal [global=" + global + "]";
	}
}
