package com.atibusinessgroup.fmp.domain.atpco;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "m_tour_type_codes")
public class AtpcoMasterTourTypeCode implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    private String id;

    @Field("code")
    private String code;
    
    @Field("tour_type")
    private String tourType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTourType() {
		return tourType;
	}

	public void setTourType(String tourType) {
		this.tourType = tourType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tourType == null) ? 0 : tourType.hashCode());
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
		AtpcoMasterTourTypeCode other = (AtpcoMasterTourTypeCode) obj;
		if (code == null) {
			if (other.code != null) {
				return false;
			}
		} else if (!code.equals(other.code)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (tourType == null) {
			if (other.tourType != null) {
				return false;
			}
		} else if (!tourType.equals(other.tourType)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoMasterTourTypeCode [id=" + id + ", code=" + code + ", tourType=" + tourType + "]";
	}
}
