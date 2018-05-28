package com.atibusinessgroup.fmp.domain.atpco;

import org.springframework.data.mongodb.core.mapping.Field;

public class AtpcoRecord3Cat27GroundCruise {

	@Field("car")
	private String car;

	@Field("other")
	private String other;

	@Field("ship")
	private String ship;

	@Field("cur_1")
	private String cur_1;

	@Field("min_grnd_amt_1")
	private String min_grnd_amt_1;

	@Field("appl")
	private String appl;

	@Field("free")
	private String free;

	@Field("trans_no")
	private String trans_no;

	@Field("filler_7")
	private String filler_7;

	@Field("filler_6")
	private String filler_6;
	
	@Field("dec_1")
	private String dec_1;

	@Field("filler_5")
	private String filler_5;

	@Field("filler_4")
	private String filler_4;

	@Field("resort")
	private String resort;

	@Field("filler_3")
	private String filler_3;

	@Field("filler_2")
	private String filler_2;

	@Field("filler_1")
	private String filler_1;

	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getShip() {
		return ship;
	}

	public void setShip(String ship) {
		this.ship = ship;
	}

	public String getCur_1() {
		return cur_1;
	}

	public void setCur_1(String cur_1) {
		this.cur_1 = cur_1;
	}

	public String getMin_grnd_amt_1() {
		return min_grnd_amt_1;
	}

	public void setMin_grnd_amt_1(String min_grnd_amt_1) {
		this.min_grnd_amt_1 = min_grnd_amt_1;
	}

	public String getAppl() {
		return appl;
	}

	public void setAppl(String appl) {
		this.appl = appl;
	}

	public String getFree() {
		return free;
	}

	public void setFree(String free) {
		this.free = free;
	}

	public String getTrans_no() {
		return trans_no;
	}

	public void setTrans_no(String trans_no) {
		this.trans_no = trans_no;
	}

	public String getFiller_7() {
		return filler_7;
	}

	public void setFiller_7(String filler_7) {
		this.filler_7 = filler_7;
	}

	public String getFiller_6() {
		return filler_6;
	}

	public void setFiller_6(String filler_6) {
		this.filler_6 = filler_6;
	}

	public String getDec_1() {
		return dec_1;
	}

	public void setDec_1(String dec_1) {
		this.dec_1 = dec_1;
	}

	public String getFiller_5() {
		return filler_5;
	}

	public void setFiller_5(String filler_5) {
		this.filler_5 = filler_5;
	}

	public String getFiller_4() {
		return filler_4;
	}

	public void setFiller_4(String filler_4) {
		this.filler_4 = filler_4;
	}

	public String getResort() {
		return resort;
	}

	public void setResort(String resort) {
		this.resort = resort;
	}

	public String getFiller_3() {
		return filler_3;
	}

	public void setFiller_3(String filler_3) {
		this.filler_3 = filler_3;
	}

	public String getFiller_2() {
		return filler_2;
	}

	public void setFiller_2(String filler_2) {
		this.filler_2 = filler_2;
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
		result = prime * result + ((appl == null) ? 0 : appl.hashCode());
		result = prime * result + ((car == null) ? 0 : car.hashCode());
		result = prime * result + ((cur_1 == null) ? 0 : cur_1.hashCode());
		result = prime * result + ((dec_1 == null) ? 0 : dec_1.hashCode());
		result = prime * result + ((filler_1 == null) ? 0 : filler_1.hashCode());
		result = prime * result + ((filler_2 == null) ? 0 : filler_2.hashCode());
		result = prime * result + ((filler_3 == null) ? 0 : filler_3.hashCode());
		result = prime * result + ((filler_4 == null) ? 0 : filler_4.hashCode());
		result = prime * result + ((filler_5 == null) ? 0 : filler_5.hashCode());
		result = prime * result + ((filler_6 == null) ? 0 : filler_6.hashCode());
		result = prime * result + ((filler_7 == null) ? 0 : filler_7.hashCode());
		result = prime * result + ((free == null) ? 0 : free.hashCode());
		result = prime * result + ((min_grnd_amt_1 == null) ? 0 : min_grnd_amt_1.hashCode());
		result = prime * result + ((other == null) ? 0 : other.hashCode());
		result = prime * result + ((resort == null) ? 0 : resort.hashCode());
		result = prime * result + ((ship == null) ? 0 : ship.hashCode());
		result = prime * result + ((trans_no == null) ? 0 : trans_no.hashCode());
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
		AtpcoRecord3Cat27GroundCruise other = (AtpcoRecord3Cat27GroundCruise) obj;
		if (appl == null) {
			if (other.appl != null)
				return false;
		} else if (!appl.equals(other.appl))
			return false;
		if (car == null) {
			if (other.car != null)
				return false;
		} else if (!car.equals(other.car))
			return false;
		if (cur_1 == null) {
			if (other.cur_1 != null)
				return false;
		} else if (!cur_1.equals(other.cur_1))
			return false;
		if (dec_1 == null) {
			if (other.dec_1 != null)
				return false;
		} else if (!dec_1.equals(other.dec_1))
			return false;
		if (filler_1 == null) {
			if (other.filler_1 != null)
				return false;
		} else if (!filler_1.equals(other.filler_1))
			return false;
		if (filler_2 == null) {
			if (other.filler_2 != null)
				return false;
		} else if (!filler_2.equals(other.filler_2))
			return false;
		if (filler_3 == null) {
			if (other.filler_3 != null)
				return false;
		} else if (!filler_3.equals(other.filler_3))
			return false;
		if (filler_4 == null) {
			if (other.filler_4 != null)
				return false;
		} else if (!filler_4.equals(other.filler_4))
			return false;
		if (filler_5 == null) {
			if (other.filler_5 != null)
				return false;
		} else if (!filler_5.equals(other.filler_5))
			return false;
		if (filler_6 == null) {
			if (other.filler_6 != null)
				return false;
		} else if (!filler_6.equals(other.filler_6))
			return false;
		if (filler_7 == null) {
			if (other.filler_7 != null)
				return false;
		} else if (!filler_7.equals(other.filler_7))
			return false;
		if (free == null) {
			if (other.free != null)
				return false;
		} else if (!free.equals(other.free))
			return false;
		if (min_grnd_amt_1 == null) {
			if (other.min_grnd_amt_1 != null)
				return false;
		} else if (!min_grnd_amt_1.equals(other.min_grnd_amt_1))
			return false;
		if (this.other == null) {
			if (other.other != null)
				return false;
		} else if (!this.other.equals(other.other))
			return false;
		if (resort == null) {
			if (other.resort != null)
				return false;
		} else if (!resort.equals(other.resort))
			return false;
		if (ship == null) {
			if (other.ship != null)
				return false;
		} else if (!ship.equals(other.ship))
			return false;
		if (trans_no == null) {
			if (other.trans_no != null)
				return false;
		} else if (!trans_no.equals(other.trans_no))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoRecord3Cat27GroundCruise [car=" + car + ", other=" + other + ", ship=" + ship + ", cur_1=" + cur_1
				+ ", min_grnd_amt_1=" + min_grnd_amt_1 + ", appl=" + appl + ", free=" + free + ", trans_no=" + trans_no
				+ ", filler_7=" + filler_7 + ", filler_6=" + filler_6 + ", dec_1=" + dec_1 + ", filler_5=" + filler_5
				+ ", filler_4=" + filler_4 + ", resort=" + resort + ", filler_3=" + filler_3 + ", filler_2=" + filler_2
				+ ", filler_1=" + filler_1 + "]";
	}
	
	
}
