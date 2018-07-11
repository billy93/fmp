package com.atibusinessgroup.fmp.domain.dto;

import java.util.Date;

public class AddOnParam {
	
	private String carrier;
	private String source;	
	private String publicPrivate;
	private String tariff;
	private String origin;
	private String destination;
	private String owrt;
	private String bucket;
	private String footnote1;
	private String footnote2;
	private String zoneNo;
	private String routeNo;
	private String woId;
	private Date effectiveDateFrom;
	private Date effectiveDateTo;
	private String effectiveDateOption;
	private Date saleDateFrom;
	private Date saleDateTo;
	private String saleDateOption;
	private Date travelDateFrom;
	private Date travelDateTo;
	private String travelDateOption;
	private String amountRange;
	private boolean appendResults;
	private boolean biDirectional;
	private int page;
	private int size;
	private int lastIndex;
	
	public AddOnParam() {
		
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getPublicPrivate() {
		return publicPrivate;
	}

	public void setPublicPrivate(String publicPrivate) {
		this.publicPrivate = publicPrivate;
	}

	public String getTariff() {
		return tariff;
	}

	public void setTariff(String tariff) {
		this.tariff = tariff;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getOwrt() {
		return owrt;
	}

	public void setOwrt(String owrt) {
		this.owrt = owrt;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public String getFootnote1() {
		return footnote1;
	}

	public void setFootnote1(String footnote1) {
		this.footnote1 = footnote1;
	}

	public String getFootnote2() {
		return footnote2;
	}

	public void setFootnote2(String footnote2) {
		this.footnote2 = footnote2;
	}

	public String getZoneNo() {
		return zoneNo;
	}

	public void setZoneNo(String zoneNo) {
		this.zoneNo = zoneNo;
	}

	public String getRouteNo() {
		return routeNo;
	}

	public void setRouteNo(String routeNo) {
		this.routeNo = routeNo;
	}

	public String getWoId() {
		return woId;
	}

	public void setWoId(String woId) {
		this.woId = woId;
	}

	public Date getEffectiveDateFrom() {
		return effectiveDateFrom;
	}

	public void setEffectiveDateFrom(Date effectiveDateFrom) {
		this.effectiveDateFrom = effectiveDateFrom;
	}

	public Date getEffectiveDateTo() {
		return effectiveDateTo;
	}

	public void setEffectiveDateTo(Date effectiveDateTo) {
		this.effectiveDateTo = effectiveDateTo;
	}

	public String getEffectiveDateOption() {
		return effectiveDateOption;
	}

	public void setEffectiveDateOption(String effectiveDateOption) {
		this.effectiveDateOption = effectiveDateOption;
	}

	public Date getSaleDateFrom() {
		return saleDateFrom;
	}

	public void setSaleDateFrom(Date saleDateFrom) {
		this.saleDateFrom = saleDateFrom;
	}

	public Date getSaleDateTo() {
		return saleDateTo;
	}

	public void setSaleDateTo(Date saleDateTo) {
		this.saleDateTo = saleDateTo;
	}

	public String getSaleDateOption() {
		return saleDateOption;
	}

	public void setSaleDateOption(String saleDateOption) {
		this.saleDateOption = saleDateOption;
	}

	public Date getTravelDateFrom() {
		return travelDateFrom;
	}

	public void setTravelDateFrom(Date travelDateFrom) {
		this.travelDateFrom = travelDateFrom;
	}

	public Date getTravelDateTo() {
		return travelDateTo;
	}

	public void setTravelDateTo(Date travelDateTo) {
		this.travelDateTo = travelDateTo;
	}

	public String getTravelDateOption() {
		return travelDateOption;
	}

	public void setTravelDateOption(String travelDateOption) {
		this.travelDateOption = travelDateOption;
	}

	public String getAmountRange() {
		return amountRange;
	}

	public void setAmountRange(String amountRange) {
		this.amountRange = amountRange;
	}

	public boolean isAppendResults() {
		return appendResults;
	}

	public void setAppendResults(boolean appendResults) {
		this.appendResults = appendResults;
	}

	public boolean isBiDirectional() {
		return biDirectional;
	}

	public void setBiDirectional(boolean biDirectional) {
		this.biDirectional = biDirectional;
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
		result = prime * result + ((bucket == null) ? 0 : bucket.hashCode());
		result = prime * result + ((amountRange == null) ? 0 : amountRange.hashCode());
		result = prime * result + (appendResults ? 1231 : 1237);
		result = prime * result + (biDirectional ? 1231 : 1237);
		result = prime * result + ((carrier == null) ? 0 : carrier.hashCode());
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((effectiveDateFrom == null) ? 0 : effectiveDateFrom.hashCode());
		result = prime * result + ((effectiveDateOption == null) ? 0 : effectiveDateOption.hashCode());
		result = prime * result + ((effectiveDateTo == null) ? 0 : effectiveDateTo.hashCode());
		result = prime * result + ((footnote1 == null) ? 0 : footnote1.hashCode());
		result = prime * result + ((footnote2 == null) ? 0 : footnote2.hashCode());
		result = prime * result + lastIndex;
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		result = prime * result + ((owrt == null) ? 0 : owrt.hashCode());
		result = prime * result + page;
		result = prime * result + ((publicPrivate == null) ? 0 : publicPrivate.hashCode());
		result = prime * result + ((routeNo == null) ? 0 : routeNo.hashCode());
		result = prime * result + ((saleDateFrom == null) ? 0 : saleDateFrom.hashCode());
		result = prime * result + ((saleDateOption == null) ? 0 : saleDateOption.hashCode());
		result = prime * result + ((saleDateTo == null) ? 0 : saleDateTo.hashCode());
		result = prime * result + size;
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((tariff == null) ? 0 : tariff.hashCode());
		result = prime * result + ((travelDateFrom == null) ? 0 : travelDateFrom.hashCode());
		result = prime * result + ((travelDateOption == null) ? 0 : travelDateOption.hashCode());
		result = prime * result + ((travelDateTo == null) ? 0 : travelDateTo.hashCode());
		result = prime * result + ((woId == null) ? 0 : woId.hashCode());
		result = prime * result + ((zoneNo == null) ? 0 : zoneNo.hashCode());
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
		AddOnParam other = (AddOnParam) obj;
		if (bucket == null) {
			if (other.bucket != null) {
				return false;
			}
		} else if (!bucket.equals(other.bucket)) {
			return false;
		}
		if (amountRange == null) {
			if (other.amountRange != null) {
				return false;
			}
		} else if (!amountRange.equals(other.amountRange)) {
			return false;
		}
		if (appendResults != other.appendResults) {
			return false;
		}
		if (biDirectional != other.biDirectional) {
			return false;
		}
		if (carrier == null) {
			if (other.carrier != null) {
				return false;
			}
		} else if (!carrier.equals(other.carrier)) {
			return false;
		}
		if (destination == null) {
			if (other.destination != null) {
				return false;
			}
		} else if (!destination.equals(other.destination)) {
			return false;
		}
		if (effectiveDateFrom == null) {
			if (other.effectiveDateFrom != null) {
				return false;
			}
		} else if (!effectiveDateFrom.equals(other.effectiveDateFrom)) {
			return false;
		}
		if (effectiveDateOption == null) {
			if (other.effectiveDateOption != null) {
				return false;
			}
		} else if (!effectiveDateOption.equals(other.effectiveDateOption)) {
			return false;
		}
		if (effectiveDateTo == null) {
			if (other.effectiveDateTo != null) {
				return false;
			}
		} else if (!effectiveDateTo.equals(other.effectiveDateTo)) {
			return false;
		}
		if (footnote1 == null) {
			if (other.footnote1 != null) {
				return false;
			}
		} else if (!footnote1.equals(other.footnote1)) {
			return false;
		}
		if (footnote2 == null) {
			if (other.footnote2 != null) {
				return false;
			}
		} else if (!footnote2.equals(other.footnote2)) {
			return false;
		}
		if (lastIndex != other.lastIndex) {
			return false;
		}
		if (origin == null) {
			if (other.origin != null) {
				return false;
			}
		} else if (!origin.equals(other.origin)) {
			return false;
		}
		if (owrt == null) {
			if (other.owrt != null) {
				return false;
			}
		} else if (!owrt.equals(other.owrt)) {
			return false;
		}
		if (page != other.page) {
			return false;
		}
		if (publicPrivate == null) {
			if (other.publicPrivate != null) {
				return false;
			}
		} else if (!publicPrivate.equals(other.publicPrivate)) {
			return false;
		}
		if (routeNo == null) {
			if (other.routeNo != null) {
				return false;
			}
		} else if (!routeNo.equals(other.routeNo)) {
			return false;
		}
		if (saleDateFrom == null) {
			if (other.saleDateFrom != null) {
				return false;
			}
		} else if (!saleDateFrom.equals(other.saleDateFrom)) {
			return false;
		}
		if (saleDateOption == null) {
			if (other.saleDateOption != null) {
				return false;
			}
		} else if (!saleDateOption.equals(other.saleDateOption)) {
			return false;
		}
		if (saleDateTo == null) {
			if (other.saleDateTo != null) {
				return false;
			}
		} else if (!saleDateTo.equals(other.saleDateTo)) {
			return false;
		}
		if (size != other.size) {
			return false;
		}
		if (source == null) {
			if (other.source != null) {
				return false;
			}
		} else if (!source.equals(other.source)) {
			return false;
		}
		if (tariff == null) {
			if (other.tariff != null) {
				return false;
			}
		} else if (!tariff.equals(other.tariff)) {
			return false;
		}
		if (travelDateFrom == null) {
			if (other.travelDateFrom != null) {
				return false;
			}
		} else if (!travelDateFrom.equals(other.travelDateFrom)) {
			return false;
		}
		if (travelDateOption == null) {
			if (other.travelDateOption != null) {
				return false;
			}
		} else if (!travelDateOption.equals(other.travelDateOption)) {
			return false;
		}
		if (travelDateTo == null) {
			if (other.travelDateTo != null) {
				return false;
			}
		} else if (!travelDateTo.equals(other.travelDateTo)) {
			return false;
		}
		if (woId == null) {
			if (other.woId != null) {
				return false;
			}
		} else if (!woId.equals(other.woId)) {
			return false;
		}
		if (zoneNo == null) {
			if (other.zoneNo != null) {
				return false;
			}
		} else if (!zoneNo.equals(other.zoneNo)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AddOnParam [carrier=" + carrier + ", source=" + source + ", publicPrivate=" + publicPrivate
				+ ", tariff=" + tariff + ", origin=" + origin + ", destination=" + destination + ", owrt=" + owrt
				+ ", bucket=" + bucket + ", footnote1=" + footnote1 + ", footnote2=" + footnote2 + ", zoneNo=" + zoneNo
				+ ", routeNo=" + routeNo + ", woId=" + woId + ", effectiveDateFrom=" + effectiveDateFrom
				+ ", effectiveDateTo=" + effectiveDateTo + ", effectiveDateOption=" + effectiveDateOption
				+ ", saleDateFrom=" + saleDateFrom + ", saleDateTo=" + saleDateTo + ", saleDateOption=" + saleDateOption
				+ ", travelDateFrom=" + travelDateFrom + ", travelDateTo=" + travelDateTo + ", travelDateOption="
				+ travelDateOption + ", amountRange=" + amountRange + ", appendResults=" + appendResults
				+ ", biDirectional=" + biDirectional + ", page=" + page + ", size=" + size + ", lastIndex=" + lastIndex
				+ "]";
	}
}
