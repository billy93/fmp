package com.atibusinessgroup.fmp.domain.dto;

public class FbrReportParam {
	
	private String carrier;
	private String tariff;
	private String ruleNo;
	private int page;
	private int size;
	private int lastIndex;
	
	public String getCarrier() {
		return carrier;
	}
	
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	
	public String getTariff() {
		return tariff;
	}
	
	public void setTariff(String tariff) {
		this.tariff = tariff;
	}
	
	public String getRuleNo() {
		return ruleNo;
	}
	
	public void setRuleNo(String ruleNo) {
		this.ruleNo = ruleNo;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getLastIndex() {
		return lastIndex;
	}
	
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carrier == null) ? 0 : carrier.hashCode());
		result = prime * result + lastIndex;
		result = prime * result + page;
		result = prime * result + ((ruleNo == null) ? 0 : ruleNo.hashCode());
		result = prime * result + size;
		result = prime * result + ((tariff == null) ? 0 : tariff.hashCode());
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
		FbrReportParam other = (FbrReportParam) obj;
		if (carrier == null) {
			if (other.carrier != null) {
				return false;
			}
		} else if (!carrier.equals(other.carrier)) {
			return false;
		}
		if (lastIndex != other.lastIndex) {
			return false;
		}
		if (page != other.page) {
			return false;
		}
		if (ruleNo == null) {
			if (other.ruleNo != null) {
				return false;
			}
		} else if (!ruleNo.equals(other.ruleNo)) {
			return false;
		}
		if (size != other.size) {
			return false;
		}
		if (tariff == null) {
			if (other.tariff != null) {
				return false;
			}
		} else if (!tariff.equals(other.tariff)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "FbrReportParam [carrier=" + carrier + ", tariff=" + tariff + ", ruleNo=" + ruleNo + ", page=" + page
				+ ", size=" + size + ", lastIndex=" + lastIndex + "]";
	}
}
