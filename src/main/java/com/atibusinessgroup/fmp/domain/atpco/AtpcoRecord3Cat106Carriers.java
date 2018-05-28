package com.atibusinessgroup.fmp.domain.atpco;

import org.springframework.data.mongodb.core.mapping.Field;

public class AtpcoRecord3Cat106Carriers {
	
	@Field("carrier_2")
	private String carrier_2;

	@Field("carrier_1")
    private String carrier_1;

	@Field("carrier_4")
    private String carrier_4;

	@Field("carrier_3")
    private String carrier_3;

	@Field("carrier_6")
    private String carrier_6;

	@Field("carrier_5")
    private String carrier_5;

	@Field("appl")
    private String appl;

	public String getCarrier_2() {
		return carrier_2;
	}

	public void setCarrier_2(String carrier_2) {
		this.carrier_2 = carrier_2;
	}

	public String getCarrier_1() {
		return carrier_1;
	}

	public void setCarrier_1(String carrier_1) {
		this.carrier_1 = carrier_1;
	}

	public String getCarrier_4() {
		return carrier_4;
	}

	public void setCarrier_4(String carrier_4) {
		this.carrier_4 = carrier_4;
	}

	public String getCarrier_3() {
		return carrier_3;
	}

	public void setCarrier_3(String carrier_3) {
		this.carrier_3 = carrier_3;
	}

	public String getCarrier_6() {
		return carrier_6;
	}

	public void setCarrier_6(String carrier_6) {
		this.carrier_6 = carrier_6;
	}

	public String getCarrier_5() {
		return carrier_5;
	}

	public void setCarrier_5(String carrier_5) {
		this.carrier_5 = carrier_5;
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
		result = prime * result + ((carrier_1 == null) ? 0 : carrier_1.hashCode());
		result = prime * result + ((carrier_2 == null) ? 0 : carrier_2.hashCode());
		result = prime * result + ((carrier_3 == null) ? 0 : carrier_3.hashCode());
		result = prime * result + ((carrier_4 == null) ? 0 : carrier_4.hashCode());
		result = prime * result + ((carrier_5 == null) ? 0 : carrier_5.hashCode());
		result = prime * result + ((carrier_6 == null) ? 0 : carrier_6.hashCode());
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
		AtpcoRecord3Cat106Carriers other = (AtpcoRecord3Cat106Carriers) obj;
		if (appl == null) {
			if (other.appl != null)
				return false;
		} else if (!appl.equals(other.appl))
			return false;
		if (carrier_1 == null) {
			if (other.carrier_1 != null)
				return false;
		} else if (!carrier_1.equals(other.carrier_1))
			return false;
		if (carrier_2 == null) {
			if (other.carrier_2 != null)
				return false;
		} else if (!carrier_2.equals(other.carrier_2))
			return false;
		if (carrier_3 == null) {
			if (other.carrier_3 != null)
				return false;
		} else if (!carrier_3.equals(other.carrier_3))
			return false;
		if (carrier_4 == null) {
			if (other.carrier_4 != null)
				return false;
		} else if (!carrier_4.equals(other.carrier_4))
			return false;
		if (carrier_5 == null) {
			if (other.carrier_5 != null)
				return false;
		} else if (!carrier_5.equals(other.carrier_5))
			return false;
		if (carrier_6 == null) {
			if (other.carrier_6 != null)
				return false;
		} else if (!carrier_6.equals(other.carrier_6))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoRecord3Cat106Carriers [carrier_2=" + carrier_2 + ", carrier_1=" + carrier_1 + ", carrier_4="
				+ carrier_4 + ", carrier_3=" + carrier_3 + ", carrier_6=" + carrier_6 + ", carrier_5=" + carrier_5
				+ ", appl=" + appl + "]";
	}
	
	

}
