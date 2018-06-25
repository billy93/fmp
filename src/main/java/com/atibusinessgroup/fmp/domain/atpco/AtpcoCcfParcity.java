package com.atibusinessgroup.fmp.domain.atpco;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_master_fare_matrix")
public class AtpcoCcfParcity implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("batch_number")
    private String batchNo;
    
    @Field("batch_date")
    private Object batchDate;
    
    @Field("rec_type")
    private String recordType;
    
    @Field("filler")
    private String filler;
    
    @Field("city_area")
    private String area;
    
    @Field("city_zone")
    private String zone;
    
    @Field("city_country")
    private String country;
    
    @Field("city_state")
    private String state;
    
    @Field("city_code")
    private String city;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Object getBatchDate() {
		return batchDate;
	}

	public void setBatchDate(Object batchDate) {
		this.batchDate = batchDate;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((batchDate == null) ? 0 : batchDate.hashCode());
		result = prime * result + ((batchNo == null) ? 0 : batchNo.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((filler == null) ? 0 : filler.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((recordType == null) ? 0 : recordType.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((zone == null) ? 0 : zone.hashCode());
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
		AtpcoCcfParcity other = (AtpcoCcfParcity) obj;
		if (area == null) {
			if (other.area != null) {
				return false;
			}
		} else if (!area.equals(other.area)) {
			return false;
		}
		if (batchDate == null) {
			if (other.batchDate != null) {
				return false;
			}
		} else if (!batchDate.equals(other.batchDate)) {
			return false;
		}
		if (batchNo == null) {
			if (other.batchNo != null) {
				return false;
			}
		} else if (!batchNo.equals(other.batchNo)) {
			return false;
		}
		if (city == null) {
			if (other.city != null) {
				return false;
			}
		} else if (!city.equals(other.city)) {
			return false;
		}
		if (country == null) {
			if (other.country != null) {
				return false;
			}
		} else if (!country.equals(other.country)) {
			return false;
		}
		if (filler == null) {
			if (other.filler != null) {
				return false;
			}
		} else if (!filler.equals(other.filler)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (recordType == null) {
			if (other.recordType != null) {
				return false;
			}
		} else if (!recordType.equals(other.recordType)) {
			return false;
		}
		if (state == null) {
			if (other.state != null) {
				return false;
			}
		} else if (!state.equals(other.state)) {
			return false;
		}
		if (zone == null) {
			if (other.zone != null) {
				return false;
			}
		} else if (!zone.equals(other.zone)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoCcfParcity [id=" + id + ", batchNo=" + batchNo + ", batchDate=" + batchDate + ", recordType="
				+ recordType + ", filler=" + filler + ", area=" + area + ", zone=" + zone + ", country=" + country
				+ ", state=" + state + ", city=" + city + "]";
	}
}
