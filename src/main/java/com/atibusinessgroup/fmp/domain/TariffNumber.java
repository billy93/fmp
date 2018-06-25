package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TariffNumber.
 */
@Document(collection = "tariff_number")
public class TariffNumber implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("tar_no")
    private String tarNo;

    @Field("tar_cd")
    private String tarCd;

    @Field("global")
    private String global;

    @Field("description")
    private String description;
    
    @Field("type")
    private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTarNo() {
		return tarNo;
	}

	public void setTarNo(String tarNo) {
		this.tarNo = tarNo;
	}

	public String getTarCd() {
		return tarCd;
	}

	public void setTarCd(String tarCd) {
		this.tarCd = tarCd;
	}

	public String getGlobal() {
		return global;
	}

	public void setGlobal(String global) {
		this.global = global;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((global == null) ? 0 : global.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tarCd == null) ? 0 : tarCd.hashCode());
		result = prime * result + ((tarNo == null) ? 0 : tarNo.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		TariffNumber other = (TariffNumber) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (global == null) {
			if (other.global != null)
				return false;
		} else if (!global.equals(other.global))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tarCd == null) {
			if (other.tarCd != null)
				return false;
		} else if (!tarCd.equals(other.tarCd))
			return false;
		if (tarNo == null) {
			if (other.tarNo != null)
				return false;
		} else if (!tarNo.equals(other.tarNo))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TariffNumber [id=" + id + ", tarNo=" + tarNo + ", tarCd=" + tarCd + ", global=" + global
				+ ", description=" + description + ", type=" + type + "]";
	}

    
}
