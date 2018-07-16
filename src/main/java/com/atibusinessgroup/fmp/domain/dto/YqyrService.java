package com.atibusinessgroup.fmp.domain.dto;

import org.bson.types.Decimal128;
import org.springframework.data.mongodb.core.mapping.Field;

public class YqyrService {
	
	@Field("service_fee_cur")
	private String service_fee_cur;

	@Field("service_fee_application")
    private String service_fee_application;

	@Field("service_fee_tax_include")
    private String service_fee_tax_include;

	@Field("service_fee_dec")
    private int service_fee_dec;

	@Field("service_fee_percent")
    private Decimal128 service_fee_percent;

	@Field("service_fee_amount")
    private Decimal128 service_fee_amount;

	public String getService_fee_cur() {
		return service_fee_cur;
	}

	public void setService_fee_cur(String service_fee_cur) {
		this.service_fee_cur = service_fee_cur;
	}

	public String getService_fee_application() {
		return service_fee_application;
	}

	public void setService_fee_application(String service_fee_application) {
		this.service_fee_application = service_fee_application;
	}

	public String getService_fee_tax_include() {
		return service_fee_tax_include;
	}

	public void setService_fee_tax_include(String service_fee_tax_include) {
		this.service_fee_tax_include = service_fee_tax_include;
	}

	public int getService_fee_dec() {
		return service_fee_dec;
	}

	public void setService_fee_dec(int service_fee_dec) {
		this.service_fee_dec = service_fee_dec;
	}

	public Decimal128 getService_fee_percent() {
		return service_fee_percent;
	}

	public void setService_fee_percent(Decimal128 service_fee_percent) {
		this.service_fee_percent = service_fee_percent;
	}

	public Decimal128 getService_fee_amount() {
		return service_fee_amount;
	}

	public void setService_fee_amount(Decimal128 service_fee_amount) {
		this.service_fee_amount = service_fee_amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((service_fee_amount == null) ? 0 : service_fee_amount.hashCode());
		result = prime * result + ((service_fee_application == null) ? 0 : service_fee_application.hashCode());
		result = prime * result + ((service_fee_cur == null) ? 0 : service_fee_cur.hashCode());
		result = prime * result + service_fee_dec;
		result = prime * result + ((service_fee_percent == null) ? 0 : service_fee_percent.hashCode());
		result = prime * result + ((service_fee_tax_include == null) ? 0 : service_fee_tax_include.hashCode());
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
		YqyrService other = (YqyrService) obj;
		if (service_fee_amount == null) {
			if (other.service_fee_amount != null) {
				return false;
			}
		} else if (!service_fee_amount.equals(other.service_fee_amount)) {
			return false;
		}
		if (service_fee_application == null) {
			if (other.service_fee_application != null) {
				return false;
			}
		} else if (!service_fee_application.equals(other.service_fee_application)) {
			return false;
		}
		if (service_fee_cur == null) {
			if (other.service_fee_cur != null) {
				return false;
			}
		} else if (!service_fee_cur.equals(other.service_fee_cur)) {
			return false;
		}
		if (service_fee_dec != other.service_fee_dec) {
			return false;
		}
		if (service_fee_percent == null) {
			if (other.service_fee_percent != null) {
				return false;
			}
		} else if (!service_fee_percent.equals(other.service_fee_percent)) {
			return false;
		}
		if (service_fee_tax_include == null) {
			if (other.service_fee_tax_include != null) {
				return false;
			}
		} else if (!service_fee_tax_include.equals(other.service_fee_tax_include)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "YqyrService [service_fee_cur=" + service_fee_cur + ", service_fee_application="
				+ service_fee_application + ", service_fee_tax_include=" + service_fee_tax_include
				+ ", service_fee_dec=" + service_fee_dec + ", service_fee_percent=" + service_fee_percent
				+ ", service_fee_amount=" + service_fee_amount + "]";
	}
}
